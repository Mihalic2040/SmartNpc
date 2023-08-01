package org.mihalic2040.smartnpc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.mihalic2040.smartnpc.entity.ModEntity;
import org.mihalic2040.smartnpc.entity.client.NpcRender;
import org.mihalic2040.smartnpc.event.ClientEvents;

@Mod.EventBusSubscriber(modid = SmartNPC.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientRegisterEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {


        //CLIENT SIDE EVENTS
        MinecraftForge.EVENT_BUS.register(new ClientEvents());


        EntityRenderers.register(ModEntity.NPC.get(), NpcRender::new);

    }
}