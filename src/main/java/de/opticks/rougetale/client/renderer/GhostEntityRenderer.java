package de.opticks.rougetale.client.renderer;

import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.client.renderer.model.GhostEntityModel;
import de.opticks.rougetale.common.entity.GhostEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.lang.reflect.Type;

public class GhostEntityRenderer<Type extends GhostEntity> extends MobRenderer<Type, GhostEntityModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Rougetale.MOD_ID, "textures/entities/ghost_entity.png");

    public GhostEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new GhostEntityModel<>(context.bakeLayer(GhostEntityModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        return TEXTURE;
    }
}
