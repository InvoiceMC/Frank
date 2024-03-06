package com.invoice.frank.velocity

import com.invoice.frank.common.duplex.Duplex
import com.invoice.frank.common.duplex.message.IncomingMessage
import com.invoice.frank.common.duplex.message.Message
import com.invoice.frank.common.duplex.message.OutgoingMessage
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.connection.PluginMessageEvent
import com.velocitypowered.api.proxy.ProxyServer
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier
import com.velocitypowered.api.proxy.server.RegisteredServer
import java.io.*

abstract class VelocityDuplex (
    private val server: ProxyServer,
    private val inIdentifier: MinecraftChannelIdentifier,
    private val outIdentifier: MinecraftChannelIdentifier
): Duplex {
    override val messages: MutableList<Message> = mutableListOf()

    init {
        register()
    }

    private fun register() {
        server.channelRegistrar.register(inIdentifier)
        server.channelRegistrar.register(outIdentifier)
    }

    @Subscribe
    fun onPluginMessageReceived(event: PluginMessageEvent) {
        if (event.identifier != inIdentifier) return

        val inputStream = event.dataAsInputStream()
        val objIn = inputStream?.let { ObjectInputStream(it) } ?: return

        val incomingMessage = (objIn.readObject() as OutgoingMessage).toIncomingMessage()

        onMessageReceived(incomingMessage)
    }

    override fun send(message: String) {
        server.allServers.forEach { send(message, it) }
    }

    override fun <K: Serializable> sendObject(obj: K) {
        server.allServers.forEach { sendObject(obj, it) }
    }

    fun send(message: String, server: RegisteredServer) {
        sendObject(message, server)
    }

    fun <K: Serializable> sendObject(obj: K, server: RegisteredServer) {
        val out = ByteArrayOutputStream()
        val objOut = ObjectOutputStream(out)
        val outgoingMessage = OutgoingMessage(obj)

        objOut.writeObject(outgoingMessage)
        objOut.flush()
        objOut.close()

        val byteArray = out.toByteArray()
        server.sendPluginMessage(outIdentifier, byteArray)
        messages.add(outgoingMessage)
    }

    abstract fun onMessageReceived(message: IncomingMessage)
}