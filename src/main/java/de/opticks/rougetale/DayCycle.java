package de.opticks.rougetale;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.List;

@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DayCycle {
    MinecraftServer server;
    float time;
    float oldTime;
    int dayCounter;
    boolean isDieEvent = false;
    boolean dieAble = false;

    Player player;

    @SubscribeEvent
    public void ServerTick(TickEvent.ServerTickEvent event)
    {
        if(event.side.isClient()){
            return;
        }
        if(player == null){
            player = Minecraft.getInstance().player;
            return;
        }


        oldTime = time;
        time = player.level.getDayTime();


        if(oldTime == time)
            return;

        if(time == 1050){
            dayCounter++;
            newDay();
        }
        if(time == 13050){
            newNight();
        }
        if(isDieEvent){
            dieEvent();
        }
    }

    public void dieEvent(){
        Player player = Minecraft.getInstance().player;
        if(player == null)
            return;
        List<ServerPlayer> players;
        try {
            PlayerList playerList = server.getPlayerList();
            players = playerList.getPlayers();
            //players = Minecraft.getInstance().player.getServer().getPlayerList().getPlayers();
        }
        catch (Exception e){
            return;
        }
        boolean allDeath = true;

        for(Player serverPlayer : players){
            player.setDeltaMovement(0,1,0);
            if(serverPlayer.position().y >= 300){
                dieAble = true;
                for(ServerPlayer serverPlayer2 : players){
                    serverPlayer2.kill();
                    serverPlayer2.setGameMode(GameType.ADVENTURE);
                }
                isDieEvent = false;
                dieAble = false;
                return;
            }
        }
    }

    public void newDay(){
        float rand = (float) Math.random();
        Player player = Minecraft.getInstance().player;
        if(player == null)
            return;
        player.displayClientMessage(new TextComponent("New Day"), false);

        if(rand < 0.30){
            player.displayClientMessage(new TextComponent("A normal Day"), false);
            return;
        }
        if(rand < 0.35){
            player.displayClientMessage(new TextComponent("The World is spoiling with it's goods"), false);
            return;
        }
        if(rand < 0.50){
            player.displayClientMessage(new TextComponent("The Sun heals your aches"), false);
            return;
        }
        if(rand < 0.60){
            player.displayClientMessage(new TextComponent("A new power flows thru you"), false);
            return;
        }
        player.displayClientMessage(new TextComponent("A tailwind raises"), false);


    }

    public void newNight(){
        float rand = (float) Math.random();
        Player player = Minecraft.getInstance().player;
        if(player == null)
            return;
        player.displayClientMessage(new TextComponent("New Night"), false);
    }

    @SubscribeEvent
    public void On(LivingSpawnEvent event){
        Player player = Minecraft.getInstance().player;
        if(player == null)
            return;;
        Entity entity = event.getEntity();
        if(entity.level.isClientSide())
            return;
        if(entity instanceof  Monster){
            if(!(entity.getName().getContents().equals("Extra") || entity.getName().getContents().equals("Spawner"))){
                entity.setCustomName(new TextComponent("Spawner"));
                entity.setCustomNameVisible(false);
                //player.displayClientMessage(new TextComponent("Day " + Integer.toString(dayCounter)), false);
                for(int i = 0; i < dayCounter; i++){
                    //player.displayClientMessage(new TextComponent("Try " + Integer.toString(i)), false);
                    float rand = (float) Math.random();
                    if(rand <  0.50){
                        continue;
                    }
                    if(rand < 0.70){
                        Zombie dummy = new Zombie(EntityType.ZOMBIE, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.80){
                        Skeleton dummy = new Skeleton(EntityType.SKELETON, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        ItemStack itemStack = new ItemStack(Items.BOW);
                        dummy.equipItemIfPossible(itemStack);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.85){
                        Spider dummy = new Spider(EntityType.SPIDER, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.90){
                        WitherSkeleton dummy = new WitherSkeleton(EntityType.WITHER_SKELETON, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.92){
                        Witch dummy = new Witch(EntityType.WITCH, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.94){
                        Silverfish dummy = new Silverfish(EntityType.SILVERFISH, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.96){
                        Blaze dummy = new Blaze(EntityType.BLAZE, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        continue;
                    }
                    if(rand < 0.98){
                        Phantom dummy = new Phantom(EntityType.PHANTOM, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(new Vec3(entity.position().x,entity.position().y+10,entity.position().z));
                        continue;
                    }
                    if(rand <= 1){
                        Vex dummy = new Vex(EntityType.VEX, entity.level);
                        dummy.setCustomName(new TextComponent("Extra"));
                        dummy.setCustomNameVisible(false);
                        entity.level.addFreshEntity(dummy);
                        dummy.moveTo(entity.position());
                        ItemStack itemStack = new ItemStack(Items.STONE_SWORD);
                        dummy.equipItemIfPossible(itemStack);
                        continue;
                    }

                }
            }
        }
    }

    @SubscribeEvent
    public void onHurtEntity(LivingHurtEvent event){
        if(event.getEntity() instanceof ServerPlayer player){
            if(player.getHealth() <= event.getAmount()){
                if(dieAble){return;}
                player.setGameMode(GameType.SPECTATOR);
                event.setCanceled(true);

                List<ServerPlayer> players;
                try {
                    player.displayClientMessage(new TextComponent("try"), false);
                    Player localPlayer = Minecraft.getInstance().player;
                    server = player.getServer();
                    PlayerList playerList = server.getPlayerList();
                    players = playerList.getPlayers();
                    //players = Minecraft.getInstance().player.getServer().getPlayerList().getPlayers();
                }
                catch (Exception e){
                    player.displayClientMessage(new TextComponent("catsh"), false);
                    return;
                }
                boolean allDeath = true;
                player.displayClientMessage(new TextComponent(Integer.toString(players.size())), false);
                for(ServerPlayer serverPlayer : players){
                    player.displayClientMessage(new TextComponent("Checking"), false);
                    if(serverPlayer.gameMode.getGameModeForPlayer() != GameType.SPECTATOR) {
                        allDeath = false;
                    }
                }
                if(allDeath){
                    player.displayClientMessage(new TextComponent("All dead"), false);
                    isDieEvent = true;
                }
            }
        }
    }

}
