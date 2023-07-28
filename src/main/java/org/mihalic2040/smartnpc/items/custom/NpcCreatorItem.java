package org.mihalic2040.smartnpc.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class NpcCreatorItem extends Item {
    public NpcCreatorItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext PContext) {
        if (!PContext.getLevel().isClientSide()) {
            BlockPos cords = PContext.getClickedPos();
            Player player = PContext.getPlayer();
            Level world = PContext.getLevel();

            player.sendSystemMessage(Component.literal("Hello from code!!! is Item clock RPC call" + cords.toString()));






        }

        return super.useOn(PContext);
    }
}
