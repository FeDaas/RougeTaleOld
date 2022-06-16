package de.opticks.rougetale.core.events;

import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.common.entity.GhostEntity;
import de.opticks.rougetale.core.init.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.swing.text.html.parser.Entity;

@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD) // <----------------------------
public class CommonModEvents {
    @SubscribeEvent
    public  static void registerAttributes(EntityAttributeCreationEvent evemt){
        evemt.put(ModEntities.GHOST_ENTITY.get(), GhostEntity.createAttributes().build());
    }
}
