package de.opticks.rougetale.client.event;

import de.opticks.rougetale.AssassinAbility;
import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.client.KeyInit;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Rougetale.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEvents {
    private ClientForgeEvents(){}

    //AssassinAbility assassinAbility = new AssassinAbility();
    @SubscribeEvent
    public  static void clientTick(TickEvent.ClientTickEvent event){
        if(KeyInit.actionSkillKeyMapping.isDown()) {
            //Minecraft.getInstance().player.displayClientMessage(new TextComponent("Cast Skill casted"), false);
        }
    }
}
