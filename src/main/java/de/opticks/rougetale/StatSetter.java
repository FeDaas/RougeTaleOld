package de.opticks.rougetale;
import net.minecraft.client.Minecraft;
import net.minecraft.server.commands.SummonCommand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import  net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.tree.TryCatchBlockNode;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;

public class StatSetter
{
    @SubscribeEvent
    public void PlayerRespawned(PlayerEvent.PlayerRespawnEvent event)
    {

        setPlayerHealth(event.getPlayer());

    }

    @SubscribeEvent
    public void PlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        //setPlayerHealth(event.getPlayer());
        movePlayerstart(event.getPlayer());
    }

    public static void setPlayerHealth(Player player)
    {
        LogManager.getLogger().debug((Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH))).getValue());
        Objects.requireNonNull(player.getAttribute(Attributes.MAX_HEALTH)).addPermanentModifier(new AttributeModifier("MaxHealth", -18.0f, AttributeModifier.Operation.ADDITION));
        //player.setHealth(2.0f);
        player.moveTo(125, 226, 120);
    }

    public void movePlayerstart(@NotNull Player player){
    }

}
