package org.mihalic2040.smartnpc.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.mihalic2040.smartnpc.SmartNPC;
import org.mihalic2040.smartnpc.entity.ModEntity;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;

@Mod.EventBusSubscriber(modid = SmartNPC.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntity.NPC.get(), NpcEntity.setAttributes());
    }
}
