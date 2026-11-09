package net.tcpshield.tcpshield.fabric.impl;

import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.tcpshield.tcpshield.fabric.mixin.ConnectionAccessor;
import net.tcpshield.tcpshield.provider.PlayerProvider;
//? if <1.19 {
/*import net.minecraft.network.chat.TextComponent;*/
//? }

import java.net.InetSocketAddress;

public class FabricPlayer implements PlayerProvider {

    private final Connection connection;
    private String ip;

    public FabricPlayer(ClientIntentionPacket packet, Connection connection) {
        this.connection = connection;
        this.ip = ((InetSocketAddress) ((ConnectionAccessor) connection).getChannel().remoteAddress()).getAddress().getHostAddress();
    }

    @Override
    public String getUUID() {
        return "unknown";
    }

    @Override
    public String getName() {
        return "unknown";
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public void setIP(InetSocketAddress ip) {
        // At this point, the IP/connection believe the player has the IP of TCPShield.
        // The ip passed into this method contains their CORRECT data, which we have to assign to the player network connection.
        ((ConnectionAccessor) connection).setAddress(ip);
        this.ip = ((InetSocketAddress) ((ConnectionAccessor) connection).getChannel().remoteAddress()).getAddress().getHostAddress();
    }

    @Override
    public void disconnect() {
        //? if 1.17 || 1.17.1 || 1.18 || 1.18.1 || 1.18.2 {
        /*connection.disconnect(new TextComponent("Connection failed. Please try again or contact an administrator."));*/
        //? }
        //? if >=1.19 {
        connection.disconnect(Component.literal("Connection failed. Please try again or contact an administrator."));
        //? }
    }
}
