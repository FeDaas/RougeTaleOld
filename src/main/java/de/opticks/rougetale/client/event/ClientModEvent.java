package de.opticks.rougetale.client.event;

import com.mojang.blaze3d.systems.RenderSystem;
import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.client.KeyInit;
import de.opticks.rougetale.client.renderer.GhostEntityRenderer;
import de.opticks.rougetale.client.renderer.model.GhostEntityModel;
import de.opticks.rougetale.common.entity.GhostEntity;
import de.opticks.rougetale.core.init.ModBlocks;
import de.opticks.rougetale.core.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;

@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEvent {
    private  ClientModEvent(){}

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(GhostEntityModel.LAYER_LOCATION, GhostEntityModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ModEntities.GHOST_ENTITY.get(), GhostEntityRenderer::new);
    }

    @SubscribeEvent
    public static  void clientSetup(FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PORTAL_BLOCK.get(), RenderType.translucent());
        //ItemBlockRenderTypes.setRenderLayer(ModEntities.GHOST_ENTITY.get(), RenderType.translucent());

        KeyInit.init();
    }
}
