package org.mihalic2040.smartnpc.items.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;

public class NpcCreatorItem extends Item {
    public NpcCreatorItem(Properties p_41383_) {
        super(p_41383_);
    }


//    @Override
//    public InteractionResult useOn(UseOnContext PContext) {
//        if (!PContext.getLevel().isClientSide()) {
//            BlockPos cords = PContext.getClickedPos();
//            Player player = PContext.getPlayer();
//            Level world = PContext.getLevel();
//
//            //player.sendSystemMessage(Component.literal("Hello from code!!! is Item clock RPC call"));
//
//
//            //Trace if we click on npc
//            player.sendSystemMessage(Component.literal("Check if YOU HIT BLOCK"));
//            HitResult rayTraceResult = minecraft.hitResult;
//            player.sendSystemMessage(Component.literal("Ray trace" + rayTraceResult.getType()));
//            if (rayTraceResult.getType().equals(HitResult.Type.BLOCK)) {
//                //Add new entity
//                player.sendSystemMessage(Component.literal("Check if YOU HIT BLOCK PASS"));
//                CompoundTag compoundtag = new CompoundTag();
//                compoundtag.putString("id", "smartnpc:npc");
//
//                Entity entity = EntityType.loadEntityRecursive(compoundtag, world, (e) -> {
//                    e.moveTo(cords.getX() + 0.500f, cords.getY()+1, cords.getZ() + 0.500f);
//                    return e;
//                });
//                world.addFreshEntity(entity);
//            }
////            else if (rayTraceResult.getType().equals(HitResult.Type.ENTITY)) {
//            else {
//
//                EntityHitResult result = (EntityHitResult) rayTraceResult;
//                Entity entity = result.getEntity();
//                player.sendSystemMessage(Component.literal("Entity is " + entity.getName()));
//                if (entity instanceof NpcEntity) {
//                    player.sendSystemMessage(Component.literal("This is NPC"));
//                }
//            }
//
//
//
//        }
//
//        return super.useOn(PContext);
//    }
}
