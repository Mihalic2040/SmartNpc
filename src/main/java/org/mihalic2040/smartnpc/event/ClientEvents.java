package org.mihalic2040.smartnpc.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.network.NetworkEvent;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;
import org.mihalic2040.smartnpc.items.custom.NpcCreatorItem;
import org.mihalic2040.smartnpc.network.PacketDispatcher;
import org.mihalic2040.smartnpc.network.client.CHandleNpcCreator;

import java.util.Arrays;
import java.util.logging.Level;

public class ClientEvents {
    private static final Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public void onClick(InputEvent.InteractionKeyMappingTriggered event) {
        if (minecraft.player != null && minecraft.player.isAlive()) {
            if (event.isUseItem()) {
                ItemStack stack = minecraft.player.getItemInHand(event.getHand());
                if (minecraft.player.isCreative()) {
                    if (stack.getItem() instanceof NpcCreatorItem) {
                        HitResult rayTraceResult = minecraft.hitResult;
                        if (rayTraceResult.getType().equals(HitResult.Type.MISS)) {
                            if (minecraft.player.isDiscrete()) {
                                // If we right-click on the air while sneaking, open the function builder
                                minecraft.player.sendSystemMessage(Component.literal("Open BUILDER"));
                            }  else {
                                // Otherwise open the dialogue builder.
                                minecraft.player.sendSystemMessage(Component.literal("Open DIALOGE"));
                            }
                        } else if (rayTraceResult.getType().equals(HitResult.Type.ENTITY)) {
                            // If we right-click on an entity and if it is an NPC, edit it
                            EntityHitResult result = (EntityHitResult) rayTraceResult;
                            Entity entity = result.getEntity();
                            if (entity instanceof NpcEntity) {
                                minecraft.player.sendSystemMessage(Component.literal("This is npc ENTITY"));
                            }
                        } else if (rayTraceResult.getType().equals(HitResult.Type.BLOCK)) {
                            // If we right-click on a block, create a new npc and edit it
                            minecraft.player.sendSystemMessage(Component.literal("Create new entity"));
                            HitResult result = (BlockHitResult) rayTraceResult;
                            BlockPos pos = new BlockPos((int) result.getLocation().x, (int) result.getLocation().y, (int) result.getLocation().z - 1);
                            PacketDispatcher.sendToServer(new CHandleNpcCreator(pos));

                        }
                    }
                }
            }
        }
    }

}
