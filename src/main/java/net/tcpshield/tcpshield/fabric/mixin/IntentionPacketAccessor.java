package net.tcpshield.tcpshield.fabric.mixin;

import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientIntentionPacket.class)
public interface IntentionPacketAccessor {
    @Accessor("hostName")
    String getHostName();
}
