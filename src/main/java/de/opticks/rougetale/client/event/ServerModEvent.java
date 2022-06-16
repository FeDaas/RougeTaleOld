package de.opticks.rougetale.client.event;

import com.sun.jna.platform.win32.WinBase;
import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.core.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;

@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ServerModEvent {
    private ServerModEvent() {
    }

    @SubscribeEvent
    public static void onKillEntity(LivingDeathEvent event){
        if(event.getEntity() instanceof ServerPlayer player){
            return;
        }
        ItemStack stack  = new ItemStack(ModItems.COIN_ITEM.get());
        event.getEntity().spawnAtLocation(stack);

        if(Math.random()<= 0.01) {
            ItemStack stackKey  = new ItemStack(Items.BAMBOO);
            event.getEntity().spawnAtLocation(stackKey);
        }

        if(Math.random()<= 0.05) {
            ItemStack stackKey  = new ItemStack(Items.DIAMOND);
            event.getEntity().spawnAtLocation(stackKey);
        }
    }
}