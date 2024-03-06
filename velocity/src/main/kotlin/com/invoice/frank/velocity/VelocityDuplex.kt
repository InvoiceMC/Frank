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
        val message = event.data.inputStream()
        val bytes = message.readBytes()
        val messageString = String(bytes)

        if (event.identifier != inIdentifier) return

        onMessageReceived(IncomingMessage(messageString))
    }

    override fun send(message: String) {
        server.allServers.forEach { send(message, it) }
    }

    fun send(message: String, server: RegisteredServer) {
        server.sendPluginMessage(outIdentifier, OutgoingMessage(message).process().toByteArray())
    }

    abstract fun onMessageReceived(message: IncomingMessage)
}