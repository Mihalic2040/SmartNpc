package org.mihalic2040.smartnpc.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import org.mihalic2040.smartnpc.SmartNPC;
import org.mihalic2040.smartnpc.items.custom.NpcCreatorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SmartNPC.MODID);

    public static final RegistryObject<Item> CREATENPC = ITEMS.register("createnpc",
            () -> new NpcCreatorItem(new Item.Properties()));

    public static void register(IEventBus event) {
        ITEMS.register(event);
    }
}
