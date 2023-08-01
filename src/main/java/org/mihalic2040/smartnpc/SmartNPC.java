package org.mihalic2040.smartnpc;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.mihalic2040.smartnpc.entity.client.NpcModel;
import org.mihalic2040.smartnpc.entity.client.NpcRender;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;
import org.mihalic2040.smartnpc.event.ClientEvents;
import org.mihalic2040.smartnpc.items.ModItems;
import org.mihalic2040.smartnpc.entity.ModEntity;
import org.mihalic2040.smartnpc.network.PacketDispatcher;
import org.slf4j.Logger;

import java.beans.EventHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SmartNPC.MODID)
public class SmartNPC {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "smartnpc";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SmartNPC() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();


        //REGISTER
        ModItems.register(modEventBus);
        ModEntity.register(modEventBus);
        PacketDispatcher.registryMessages();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.CREATENPC);

        }
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("SmartNPC v1.0");
    }

}
