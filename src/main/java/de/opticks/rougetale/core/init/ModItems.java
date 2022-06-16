package de.opticks.rougetale.core.init;

import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.common.block.PortalBlock;
import de.opticks.rougetale.common.item.CoinItem;
import de.opticks.rougetale.common.item.HeartyDrink;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import  net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems
{
    private ModItems(){}

    public static final  DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Rougetale.MOD_ID);

    public static final RegistryObject<Item> COIN_ITEM = ITEMS.register("coin_item", ()-> new CoinItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(9999)));
    public static final RegistryObject<Item> HEARTY_DRINK = ITEMS.register("hearty_drink", ()-> new HeartyDrink(new Item.Properties().tab(CreativeModeTab.TAB_MISC).food(new FoodProperties.Builder().alwaysEat().build())));

    //Block Items
    public static final RegistryObject<BlockItem> COIN_ORE_ITEM = ITEMS.register("coinore_block", ()-> new BlockItem(ModBlocks.COINORE_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> PORTAL_BLOCK_ITEM = ITEMS.register("portal_block", ()-> new BlockItem(ModBlocks.PORTAL_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<BlockItem> CRYING_WOOD_BLOCK_ITEM = ITEMS.register("crying_wood_block", ()-> new BlockItem(ModBlocks.CRYING_WOOD_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
