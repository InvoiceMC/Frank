package com.invoice.frank.bukkit

import com.invoice.frank.common.duplex.Duplex
import com.invoice.frank.common.duplex.message.IncomingMessage
import com.invoice.frank.common.duplex.message.Message
import com.invoice.frank.common.duplex.message.OutgoingMessage
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

abstract class BukkitDuplex(
    private val plugin: JavaPlugin,
    private val stringInIdentifier: String,
    private val stringOutIdentifier: String
): Duplex, PluginMessageListener {
    override val messages = mutableListOf<Message>()

    init {
        register()
    }

    private fun register() {
        plugin.server.messenger.registerIncomingPluginChannel(plugin, stringInIdentifier, this)
        plugin.server.messenger.registerOutgoingPluginChannel(plugin, stringOutIdentifier)
    }

    override fun send(message: String) {
        sendObject(message)
    }

    override fun <K : Serializable> sendObject(obj: K) {
        val out = ByteArrayOutputStream()
        val objOut = ObjectOutputStream(out)
        val outgoingMessage = OutgoingMessage(obj)

        objOut.writeObject(outgoingMessage)
        objOut.flush()
        objOut.close()

        val byteArray = out.toByteArray()
        plugin.server.sendPluginMessage(plugin, stringOutIdentifier, byteArray)
        messages.add(outgoingMessage)
    }

    override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {
        if (channel != stringInIdentifier) return

        val inputStream = ByteArrayInputStream(message)
        val objIn = ObjectInputStream(inputStream)

        val incomingMessage = (objIn.readObject() as OutgoingMessage).toIncomingMessage()

        messages.add(incomingMessage)
        onMessageReceived(incomingMessage)
    }

    abstract fun onMessageReceived(message: IncomingMessage)
}