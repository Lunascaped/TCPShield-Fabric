package net.tcpshield.tcpshield.fabric.mixin;

import io.netty.channel.Channel;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.net.SocketAddress;

@Mixin(Connection.class)
public interface ConnectionAccessor {

    @Accessor
    void setAddress(SocketAddress address);

    @Accessor
    Channel getChannel();
}
