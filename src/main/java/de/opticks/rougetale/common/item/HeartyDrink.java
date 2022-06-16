package de.opticks.rougetale.common.item;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;

public class HeartyDrink extends Item {

    public HeartyDrink(Properties p_41383_) {
        super(p_41383_);

    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        //Objects.requireNonNull(context.getPlayer().getAttribute(Attributes.MAX_HEALTH)).addPermanentModifier(new AttributeModifier("MaxHealth", -18.0f, AttributeModifier.Operation.ADDITION));
        return super.useOn(context);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack p_41409_, Level p_41410_, LivingEntity p_41411_) {
        LogManager.getLogger().debug("Drink Sound");
        Objects.requireNonNull( p_41411_.getAttribute(Attributes.MAX_HEALTH)).addPermanentModifier(new AttributeModifier("MaxHealth", +2.0f, AttributeModifier.Operation.ADDITION));
        return super.finishUsingItem(p_41409_, p_41410_, p_41411_);
    }
}
