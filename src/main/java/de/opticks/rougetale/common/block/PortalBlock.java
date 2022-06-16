package de.opticks.rougetale.common.block;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.world.NoteBlockEvent;
import org.openjdk.nashorn.internal.ir.Statement;

import java.util.Objects;
import java.util.Properties;

public class PortalBlock extends Block {

    public int minBlock = 1000;
    public int maxBlock = 1000000;

    public PortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level p_152431_, BlockPos p_152432_, BlockState p_152433_, Entity entity) {
        if(p_152431_.isClientSide()){}
        else{
            var player = entity instanceof Player ? ((Player) entity) : null;
            if(player != null) {
                int x = (int)(Math.random() * (maxBlock - minBlock + 1) + minBlock);
                int y = (int)(Math.random() * (maxBlock - minBlock + 1) + minBlock);
                player.moveTo(x,300,y);
                player.addEffect(new MobEffectInstance(Objects.requireNonNull(MobEffect.byId(28)),600,1));//p2 = Level
            }
        }
    }
}

