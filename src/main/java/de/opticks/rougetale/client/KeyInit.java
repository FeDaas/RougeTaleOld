package de.opticks.rougetale.client;

import com.mojang.blaze3d.platform.InputConstants;
import de.opticks.rougetale.Rougetale;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;

public final class KeyInit {
    private KeyInit(){}

    public static KeyMapping actionSkillKeyMapping;

    public static void init(){
        actionSkillKeyMapping = registerKey("action_skill", KeyMapping.CATEGORY_GAMEPLAY, InputConstants.KEY_F);
    }

    private static KeyMapping registerKey(String name, String category, int keycode){
        final var key = new KeyMapping("key." + Rougetale.MOD_ID + "." + name, keycode, category);
        ClientRegistry.registerKeyBinding(key);
        return key;
    }

}
