package com.invoice.frank.bukkit

import com.google.common.io.ByteStreams
import com.invoice.frank.common.duplex.Duplex
import com.invoice.frank.common.duplex.message.IncomingMessage
import com.invoice.frank.common.duplex.message.Message
import com.invoice.frank.common.duplex.message.OutgoingMessage
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener

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
        val outgoingMessage = OutgoingMessage(message)
        val out = ByteStreams.newDataOutput()
        out.writeUTF(outgoingMessage.process())
        val byteArray = out.toByteArray()
        val trimmed = byteArray.copyOfRange(2, byteArray.size) // Remove the first two bytes (null and "o")
        plugin.server.sendPluginMessage(plugin, stringOutIdentifier, trimmed)
        messages.add(outgoingMessage)
    }

    override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {
        if (channel != stringInIdentifier) return
        val messageString = String(message)
        val incomingMessage = IncomingMessage(messageString)
        messages.add(incomingMessage)
        onMessageReceived(incomingMessage)
    }

    abstract fun onMessageReceived(message: IncomingMessage)
}