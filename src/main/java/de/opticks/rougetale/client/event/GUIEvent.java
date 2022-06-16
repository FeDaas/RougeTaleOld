package de.opticks.rougetale.client.event;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import de.opticks.rougetale.AssassinAbility;
import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.core.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

//https://github.com/ricksouth/serilum-mc-mods/blob/master/sources/GUI%20Compass/src/main/java/com/natamus/guicompass/events/GUIEvent.java
@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GUIEvent extends Gui {
    private static Minecraft mc;
    private AssassinAbility ability;

    private int coins;

    public GUIEvent(Minecraft mc, AssassinAbility ability){
        super(mc);
        GUIEvent.mc = mc;
        this.ability = ability;
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void renderOverlay(RenderGameOverlayEvent.Pre e) {
        ElementType type = e.getType();
        if (type != ElementType.TEXT) {
            return;
        }

        String abilityText = getAbilityText();
        String coinText = getCoinText();

        Font fontRender = mc.font;
        Window scaled = mc.getWindow();
        int width = scaled.getGuiScaledWidth();
        int height = scaled.getGuiScaledHeight();

        int coinStringWidth = fontRender.width(coinText);

        Color abilityColour = new Color(202, 10, 10, 255);
        Color coinColour = new Color(176, 165, 37, 255);

        PoseStack posestack = e.getMatrixStack();
        posestack.pushPose();

        int xcoordAbility = width/2 + 100;
        int xcoordCoin = width/2 - 100 - coinStringWidth;


        fontRender.draw(posestack, abilityText, xcoordAbility, height - 15, abilityColour.getRGB());
        fontRender.draw(posestack, coinText, xcoordCoin, height - 15, coinColour.getRGB());

        posestack.popPose();
    }

    private String getAbilityText() {
        return ability.getUITextRepresentation();
    }
    private  String  getCoinText(){
        return Integer.toString(coins) + "x";
    }

    @SubscribeEvent
    public void onItemPickUp(PlayerEvent.ItemPickupEvent event){
        ItemStack stack = event.getStack();
        if(stack.getItem() == ModItems.COIN_ITEM.get()){
            coins += stack.getCount();
        }
    }
}