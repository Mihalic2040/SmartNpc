package org.mihalic2040.smartnpc.network.client;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkEvent;
import org.mihalic2040.smartnpc.entity.ModEntity;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;

import java.util.function.Supplier;

public class CHandleNpcCreator {

    int handleType;

    BlockPos pos;

    int entityId;

    public CHandleNpcCreator() {
        this.handleType = HandleType.AIR_SHIFT.ordinal();
    }

    public CHandleNpcCreator(BlockPos posision){
        this.handleType = HandleType.BLOCK.ordinal();
        this.pos = posision;
    }

    public CHandleNpcCreator(int EntityId){
        this.handleType = HandleType.ENTITY.ordinal();
        this.entityId = EntityId;
    }



    public static void encode(CHandleNpcCreator msg, FriendlyByteBuf buf){
        buf.writeInt(msg.handleType);
        if (msg.handleType == HandleType.BLOCK.ordinal()){
            buf.writeBlockPos(msg.pos);
        } else if (msg.handleType == HandleType.AIR.ordinal()) {
            //PASS
        } else if (msg.handleType == HandleType.ENTITY.ordinal()) {
            buf.writeInt(msg.entityId);
        } else if (msg.handleType == HandleType.AIR_SHIFT.ordinal()) {
            //SMASH
        }
    }

    public static CHandleNpcCreator decode(FriendlyByteBuf buf) {
        int handleType = buf.readInt();
        if (handleType == HandleType.ENTITY.ordinal()) {
            return new CHandleNpcCreator(buf.readInt());
        } else if (handleType == HandleType.BLOCK.ordinal()){
            return new CHandleNpcCreator(buf.readBlockPos());
        } else if (handleType == HandleType.AIR.ordinal()){
            //fuck it
            return new CHandleNpcCreator();
        } else {
            return new CHandleNpcCreator();
        }
    }

    public static void handle(CHandleNpcCreator msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();

            if (msg.handleType == HandleType.BLOCK.ordinal()) {
                // If we right-click on a block, create a new NPC and start editing it
                NpcEntity newNpc = ModEntity.NPC.get().create(sender.level());
                BlockPos pos = msg.pos;
                VoxelShape collisionShape = sender.level().getBlockState(pos).getBlockSupportShape(sender.level(), pos);
                double blockHeight = collisionShape.isEmpty() ? 0 : collisionShape.bounds().maxY;
                newNpc.setPos(pos.getX()+0.5, pos.getY()+blockHeight, pos.getZ()+0.5);
                sender.level().addFreshEntity(newNpc);
            }
        });
    }
    public enum HandleType {
        AIR,
        ENTITY,

        AIR_SHIFT,
        BLOCK,

    }
}
