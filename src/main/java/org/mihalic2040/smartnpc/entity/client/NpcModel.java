package org.mihalic2040.smartnpc.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.mihalic2040.smartnpc.SmartNPC;
import org.mihalic2040.smartnpc.entity.custom.NpcEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;

public class NpcModel extends GeoModel<NpcEntity> {

    @Override
    public ResourceLocation getModelResource(NpcEntity animatable) {
        return new ResourceLocation(SmartNPC.MODID, "geo/npc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NpcEntity animatable) {
        return new ResourceLocation(SmartNPC.MODID, "textures/entity/npc.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NpcEntity animatable) {
        return new ResourceLocation(SmartNPC.MODID, "animations/npc.animation.json");
    }

}
