package org.mihalic2040.smartnpc.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.mihalic2040.smartnpc.SmartNPC;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;

public class ModEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SmartNPC.MODID);

    public static final RegistryObject<EntityType<NpcEntity>> NPC =
            ENTITY_TYPES.register("npc",
                    () -> EntityType.Builder.of(NpcEntity::new, MobCategory.MISC)
                            .build(new ResourceLocation(SmartNPC.MODID, "npc").toString()));

    public static void register(IEventBus event){
        ENTITY_TYPES.register(event);
    }

}
