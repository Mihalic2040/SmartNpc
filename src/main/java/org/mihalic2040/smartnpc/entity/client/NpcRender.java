package org.mihalic2040.smartnpc.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.mihalic2040.smartnpc.SmartNPC;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

import java.util.function.Function;

public class NpcRender extends GeoEntityRenderer<NpcEntity> {

    public NpcRender(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NpcModel());
    }

    @Override
    public ResourceLocation getTextureLocation(NpcEntity animatable) {
        return new ResourceLocation(SmartNPC.MODID, "textures/entity/npc.png");
    }
}
