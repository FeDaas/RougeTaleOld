package de.opticks.rougetale.core.init;

import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.common.block.CryingWoodBlock;
import de.opticks.rougetale.common.block.PortalBlock;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBlocks
{
    private ModBlocks(){}
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Rougetale.MOD_ID);
    public static final RegistryObject<Block> COINORE_BLOCK = BLOCKS.register("coinore_block", ()-> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_ORE).lightLevel((p_50884_) -> {return 5;}).strength(2.0f,15f)));

    public static final RegistryObject<PortalBlock> PORTAL_BLOCK = BLOCKS.register("portal_block", ()-> new PortalBlock(BlockBehaviour.Properties.copy(Blocks.HAY_BLOCK).strength(2.0f,15f)));
    public static final RegistryObject<CryingWoodBlock> CRYING_WOOD_BLOCK = BLOCKS.register("crying_wood_block", ()-> new CryingWoodBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_WOOD).strength(2.0f,15f)));
}
