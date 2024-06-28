package org.mihalic2040.smartnpc.network.server;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;


import java.util.function.Supplier;

public class CHandleNpcOnClickScript {
    private final HandleType handleType;
    BlockPos pos;
    int entityId;

    public CHandleNpcOnClickScript(HandleType handleType, BlockPos pos, int entityId) {
        this.handleType = handleType;
        this.pos = pos;
        this.entityId = entityId;
    }

    // Default constructor for deserialization
    public CHandleNpcOnClickScript() {
        this.handleType = HandleType.HIT;
        this.pos = BlockPos.ZERO;
        this.entityId = -1;
    }




    public static void encode(CHandleNpcOnClickScript msg, FriendlyByteBuf buf) {
        buf.writeEnum(msg.handleType);
        buf.writeBlockPos(msg.pos);
        buf.writeInt(msg.entityId);
    }

    public static CHandleNpcOnClickScript decode(FriendlyByteBuf buf) {
        HandleType handleType = buf.readEnum(HandleType.class);
        BlockPos pos = buf.readBlockPos();
        int entityId = buf.readInt();
        return new CHandleNpcOnClickScript(handleType, pos, entityId);
    }


    public static void handle(CHandleNpcOnClickScript msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            // Handle the received message based on its type
            if (msg.handleType == HandleType.DIALOGE) {
                player.sendSystemMessage(Component.literal("NPC: Dialoge"));
            } else if (msg.handleType == HandleType.SELECT) {
                player.sendSystemMessage(Component.literal("NPC: Select"));
            } else if (msg.handleType == HandleType.HIT) {
                player.sendSystemMessage(Component.literal("NPC: Hit"));
            }
            // Additional handling as necessary
        });
        ctx.get().setPacketHandled(true);
    }

    public enum HandleType {
        DIALOGE,
        HIT,
        SELECT,
    }
}
