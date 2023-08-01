package org.mihalic2040.smartnpc.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.mihalic2040.smartnpc.SmartNPC;
import org.mihalic2040.smartnpc.network.client.CHandleNpcCreator;

public class PacketDispatcher {
    private static int packetId = 0;

    private static String PROTOCOL_VERSION = "1";

    private static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(SmartNPC.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static int nextID() {
        return packetId++;
    }

    public PacketDispatcher() {}

    public static void registryMessages() {
        PacketDispatcher.INSTANCE.registerMessage(nextID(), CHandleNpcCreator.class, CHandleNpcCreator::encode, CHandleNpcCreator::decode, CHandleNpcCreator::handle);

    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }



}
