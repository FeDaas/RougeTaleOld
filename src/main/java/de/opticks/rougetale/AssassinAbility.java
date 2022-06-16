package de.opticks.rougetale;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import de.opticks.rougetale.client.KeyInit;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.checkerframework.checker.units.qual.A;
import org.lwjgl.system.CallbackI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class AssassinAbility{
    boolean loadAdvancements;
    int counter;
    List<ResourceLocation> advancements;
    float vanishDuration = 100;
    float vanishDurationTimer;
    ArmorStand dummy;
    float lifestealHealthCooldown = 20;
    float lifestealHealthCooldownTimer;

    float dummyDuration= 60;
    float dummyDurationTimer;

    float dashCooldown= 10;
    float dashCooldownTimer;

    int maxDashes;
    int dashesRemaining;

    int dummyRadius = 5;

    List<Monster> monsters;

    public Player player;
    public float cooldownTimer;
    float cooldown = 300; // :20 = 30sec


    public AssassinAbility(){
    }

    @SubscribeEvent
    public void advancement(AdvancementEvent event){
        player.displayClientMessage(new TextComponent(event.getAdvancement().getId().toString()), false);
    }

    @SubscribeEvent
    public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event){
        if(!event.getPlayer().level.isClientSide()) {
            if (player == null) {
                player = event.getPlayer();
            }
        }
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ServerTickEvent event)
    {
        if(!event.side.isClient()) {
            tick();
            if (KeyInit.actionSkillKeyMapping.isDown()) {
                TryCast();
            }
        }
        /*
        if (player.level.getBlockState( new BlockPos(100, 100, 100)).getBlock() == Blocks.ACACIA_LOG){
            player.displayClientMessage(new TextComponent("Acacia"), false);
        }*/
    }

    public void TryCast()
    {
        if(cooldownTimer <= 0)
        {
            cooldownTimer = cooldown * 2;
            dashCooldownTimer = dashCooldown * 2;
            Cast();
        }
        if(vanishDurationTimer > 0){
            if(dashCooldownTimer <= 0 && dashesRemaining > 0){
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null)
                    return;
                Vec3 playerLook = mc.player.getViewVector(1);
                Vec3 dashVec = new Vec3(playerLook.x() *1.5, playerLook.y() + 0.3, playerLook.z() * 1.5);
                mc.player.setDeltaMovement(dashVec);
                mc.player.playSound(SoundEvents.PHANTOM_FLAP, 1.0F, 2.0F);
                dashCooldownTimer = dashCooldown * 2;
                --dashesRemaining;
            }
        }
    }

    public void Cast()
    {
        if(player == null){
            return;
        }

        player.displayClientMessage(new TextComponent("Casten"), false);
        player  = player.getServer().getPlayerList().getPlayer(player.getUUID());
        PlayerAdvancements playerAdvancements = player.getServer().getPlayerList().getPlayer(player.getUUID()).getAdvancements();
        var serverAdvancements = player.getServer().getAdvancements();

        if(playerAdvancements == null){
            return;
        }
        if(serverAdvancements == null){
            return;
        }

        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration"))).isDone()){
            vanishDuration = 110;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration1"))).isDone()){
            vanishDuration = 120;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration2"))).isDone()){
            vanishDuration = 130;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration3"))).isDone()){
            vanishDuration = 140;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration4"))).isDone()){
            vanishDuration = 150;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration5"))).isDone()){
            vanishDuration = 160;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration6"))).isDone()){
            vanishDuration = 170;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration7"))).isDone()){
            vanishDuration = 180;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"duration8"))).isDone()){
            vanishDuration = 190;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"dash"))).isDone()){
            maxDashes = 1;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"dashcount"))).isDone()){
            maxDashes = 2;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"dashcount2"))).isDone()){
            maxDashes = 3;
        }

        dashesRemaining = maxDashes;

        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"vanish"))).isDone()){
            player.addEffect((new MobEffectInstance(MobEffects.INVISIBILITY,(int)vanishDuration,0)));//p2 = Level
            vanishDurationTimer = vanishDuration*2;
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"jump"))).isDone()){
            player.addEffect((new MobEffectInstance(MobEffects.JUMP,(int)vanishDuration,0)));//p2 = Level
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"speed"))).isDone()){
            player.addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SPEED,(int)vanishDuration,0)));//p2 = Level
        }

        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"jump2"))).isDone()){
            player.addEffect((new MobEffectInstance(MobEffects.JUMP,(int)vanishDuration,1)));//p2 = Level
        }

        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"speed2"))).isDone()){
            player.addEffect((new MobEffectInstance(MobEffects.MOVEMENT_SPEED,(int)vanishDuration,1)));//p2 = Level
        }
        if(playerAdvancements.getOrStartProgress(serverAdvancements.getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"dummy"))).isDone()){
            ArmorStand dummy = new ArmorStand(EntityType.ARMOR_STAND, player.level);
            player.level.addFreshEntity(dummy);
            dummy.moveTo(player.position());
            //player.level.addFreshEntity(dummy);
            this.dummy = dummy;
            dummyDurationTimer = dummyDuration * 2;
            monsters = player.level.getEntitiesOfClass((Monster.class), new AABB(
                    player.position().x - dummyRadius, player.position().y - dummyRadius,
                    player.position().z - dummyRadius,player.position().x + dummyRadius,
                    player.position().y + dummyRadius,player.position().z + dummyRadius));
            for(int i = 0; i < monsters.size(); i++){
                if(monsters.get(i) != null){
                    monsters.get(i).setTarget(dummy);
                }
            }
        }

    }
    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event){
        if(event.getPlayer() == player){
            if(vanishDurationTimer > 0){
                if(player.getServer().getPlayerList().getPlayer(player.getUUID()).getAdvancements().getOrStartProgress(player.getServer().getAdvancements().getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"lifesteal"))).isDone()){
                    if(lifestealHealthCooldownTimer == 0)
                    {
                        player.heal(1);
                        lifestealHealthCooldownTimer = lifestealHealthCooldown * 2;
                        player.displayClientMessage(new TextComponent("Heal"), false);
                    }

                }
                if(Math.random()>= 0.9)
                {
                    player.displayClientMessage(new TextComponent("Very Effective"), false);
                    if(player.getServer().getPlayerList().getPlayer(player.getUUID()).getAdvancements().getOrStartProgress(player.getServer().getAdvancements().getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"crit"))).isDone()){
                        event.getTarget().hurt(DamageSource.playerAttack(player), 6);
                    }
                    if(player.getServer().getPlayerList().getPlayer(player.getUUID()).getAdvancements().getOrStartProgress(player.getServer().getAdvancements().getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"crit2"))).isDone()){
                        event.getTarget().hurt(DamageSource.playerAttack(player), 4);
                    }
                }
            }
        }
        event.getEntity().getName();
    }
    @SubscribeEvent
    public void onKillEntity(LivingDeathEvent event){
        if(player.getServer().getPlayerList().getPlayer(player.getUUID()).getAdvancements().getOrStartProgress(player.getServer().getAdvancements().getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"crit2"))).isDone()){
            if(event.getSource().getEntity() == player){
                if(vanishDurationTimer > 0){
                    player.heal(6);
                }

            }
        }
    }

    public void tick()
    {
        if(cooldownTimer > 0){
            cooldownTimer -= 1;
        }
        if(vanishDurationTimer > 0){
            vanishDurationTimer -=1;
        }
        if(lifestealHealthCooldownTimer > 0){
            lifestealHealthCooldownTimer -=1;
        }
        if(vanishDurationTimer == 1){
            player.displayClientMessage(new TextComponent("Vanish Over"), false);
            if(player.getServer().getPlayerList().getPlayer(player.getUUID()).getAdvancements().getOrStartProgress(player.getServer().getAdvancements().getAdvancement(new ResourceLocation(Rougetale.MOD_ID,"poison"))).isDone()) {
                AreaEffectCloud poison = new AreaEffectCloud(EntityType.AREA_EFFECT_CLOUD, player.level);
                poison.addEffect((new MobEffectInstance(MobEffects.POISON, (int) 100, 0)));
                player.level.addFreshEntity(poison);
                poison.moveTo(player.position());
            }
        }
        if(dummyDurationTimer > 0){
            dummyDurationTimer -=1;
        }
        if(dummyDurationTimer == 1){
            if(dummy != null){
                dummy.remove(Entity.RemovalReason.KILLED);
            }
            for(int i = 0; i < monsters.size(); i++){
                if(monsters.get(i) != null){
                    monsters.get(i).setDeltaMovement(new Vec3(monsters.get(i).getLookAngle().x * -1, 1,monsters.get(i).getLookAngle().z * -1));
                    monsters.get(i).hurt(DamageSource.playerAttack(player),6);
                }
            }
            player.playSound(SoundEvents.GENERIC_EXPLODE, 1.0F, 2.0F);
        }
        if(dashCooldownTimer > 0){
            dashCooldownTimer -=1;
        }
    }

    public String getUITextRepresentation(){
        if(vanishDurationTimer > 0){
            if(dashesRemaining > 0){
                return Integer.toString((int)(vanishDurationTimer/40)+1) + " - " + Integer.toString(dashesRemaining);
            }
            return Integer.toString((int)(vanishDurationTimer/40)+1);
        }
        if(cooldownTimer > 0){
            return Integer.toString((int)(cooldownTimer/40)+1);
        }
        return "";
    }
}
