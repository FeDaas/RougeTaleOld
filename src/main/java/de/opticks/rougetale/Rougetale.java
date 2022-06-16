package de.opticks.rougetale;

import de.opticks.rougetale.client.event.GUIEvent;
import de.opticks.rougetale.core.init.ModBlocks;
import de.opticks.rougetale.core.init.ModEntities;
import de.opticks.rougetale.core.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.A;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("rougetale")
public class Rougetale {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "rougetale";
    public AssassinAbility assassinAbility = new AssassinAbility();
    public DayCycle dayCycle = new DayCycle();
    public Rougetale()
    {
        LogManager.getLogger().debug("RougeTaleJava !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new StatSetter());
        MinecraftForge.EVENT_BUS.register(assassinAbility);
        MinecraftForge.EVENT_BUS.register(dayCycle);
        MinecraftForge.EVENT_BUS.register(new GUIEvent(Minecraft.getInstance(), assassinAbility));

        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModEntities.ENTITIES.register(bus);
    }
//https://www.youtube.com/watch?v=Muwr3cDWIQU
}


