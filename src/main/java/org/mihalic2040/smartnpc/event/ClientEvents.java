package org.mihalic2040.smartnpc.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;
import org.mihalic2040.smartnpc.items.custom.NpcCreatorItem;
import org.mihalic2040.smartnpc.network.PacketDispatcher;
import org.mihalic2040.smartnpc.network.server.CHandleNpcCreator;
import org.mihalic2040.smartnpc.network.server.CHandleNpcOnClickScript;

public class ClientEvents {
    private static final Minecraft minecraft = Minecraft.getInstance();

    // Define a field to track the last time the event was handled
    private long lastEventTime = 0;
    private static final long EVENT_COOLDOWN = 100; // 100 milliseconds cooldown

    @SubscribeEvent
    public void onNpcClick(InputEvent.InteractionKeyMappingTriggered event) {
        long currentTime = System.currentTimeMillis();
        // Check if the cooldown period has passed since the last event handling
        if (currentTime - lastEventTime < EVENT_COOLDOWN) {
            return; // Ignore the event if within cooldown period
        }
        // Update the last event time
        lastEventTime = currentTime;


        if (minecraft.player != null && minecraft.player.isAlive()) {
            // Check if the player's main hand is empty (right-click)
            ItemStack stack = minecraft.player.getItemInHand(event.getHand());
            if (stack.isEmpty()) {
                HitResult rayTraceResult = minecraft.hitResult;

                if (rayTraceResult.getType().equals(HitResult.Type.ENTITY)) {
                    // If we right-click on an entity and if it is an NPC
                    EntityHitResult result = (EntityHitResult) rayTraceResult;
                    Entity entity = result.getEntity();
                    if (entity instanceof NpcEntity) {
                        // Call server event over network
//                        minecraft.player.sendSystemMessage(Component.literal("NPC: Local"));
                        BlockPos pos = new BlockPos((int) result.getLocation().x, (int) result.getLocation().y, (int) result.getLocation().z - 1);
                        if (event.isAttack()) {
                            PacketDispatcher.sendToServer(new CHandleNpcOnClickScript(CHandleNpcOnClickScript.HandleType.HIT, pos, entity.getId()));
                        } else if (event.isUseItem()) {
                            PacketDispatcher.sendToServer(new CHandleNpcOnClickScript(CHandleNpcOnClickScript.HandleType.DIALOGE, pos, entity.getId()));
                        } else if (event.isPickBlock()) {
                            PacketDispatcher.sendToServer(new CHandleNpcOnClickScript(CHandleNpcOnClickScript.HandleType.SELECT, pos, entity.getId()));
                        }
                    }
                }
            }
        }
    }



    @SubscribeEvent
    public void onToolClick(InputEvent.InteractionKeyMappingTriggered event) {
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
