package de.opticks.rougetale.core.init;

import de.opticks.rougetale.Rougetale;
import de.opticks.rougetale.common.entity.GhostEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities {
    private ModEntities(){}
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Rougetale.MOD_ID);

    public static  final RegistryObject<EntityType<GhostEntity>> GHOST_ENTITY = ENTITIES.register("ghost_entity", ()-> EntityType.Builder.of(GhostEntity::new, MobCategory.MONSTER).sized(0.8f,0.6f).build(new ResourceLocation(Rougetale.MOD_ID,"ghost_entity").toString()));
}
