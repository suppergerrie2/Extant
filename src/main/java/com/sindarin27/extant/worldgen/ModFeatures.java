package com.sindarin27.extant.worldgen;

import com.sindarin27.extant.ExtantMod;
import com.sindarin27.extant.blocks.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {

    @SubscribeEvent
    public static void FMLLoadComplete(FMLLoadCompleteEvent event) {
        for (Biome biome : ForgeRegistries.BIOMES) {
            biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH
                    .withConfiguration(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.ROCK_GROUND.get().getDefaultState()), new SimpleBlockPlacer()).tries(64)
                            .func_227317_b_()
                            .build())
                    .withPlacement(Placement.COUNT_HEIGHTMAP_DOUBLE.configure(new FrequencyConfig(8))));
        }
    }
}
