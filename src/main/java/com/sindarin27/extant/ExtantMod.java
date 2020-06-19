package com.sindarin27.extant;

import com.sindarin27.extant.blocks.ModBlocks;
import com.sindarin27.extant.entities.ModEntities;
import com.sindarin27.extant.items.ModItems;
import com.sindarin27.extant.worldgen.ModFeatures;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExtantMod.MOD_ID)
public class ExtantMod {
    public static final String MOD_ID = "extant";

    public ExtantMod() {
        //Register deferred registers
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModEntities.ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());

        //Register event subscribers
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModEntities::registerClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModBlocks::onClientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModFeatures::FMLLoadComplete);
    }
}
