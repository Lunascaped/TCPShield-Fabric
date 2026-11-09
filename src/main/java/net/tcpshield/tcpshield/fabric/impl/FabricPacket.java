package net.tcpshield.tcpshield.fabric.impl;

import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.tcpshield.tcpshield.fabric.mixin.IntentionPacketAccessor;
import net.tcpshield.tcpshield.provider.PacketProvider;
import net.tcpshield.tcpshield.util.exception.manipulate.PacketManipulationException;

public class FabricPacket implements PacketProvider {

    private final ClientIntentionPacket handshake;

    public FabricPacket(ClientIntentionPacket handshake) {
        this.handshake = handshake;
    }

    @Override
    public String getPayloadString() {
        return IntentionPacketAccessor.class.cast(handshake).getHostName();
    }

    @Override
    public void setPacketHostname(String hostname) throws PacketManipulationException {
        // NO OPERATION
    }
}
