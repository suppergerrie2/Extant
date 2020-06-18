package com.sindarin27.extant.blocks;

import com.sindarin27.extant.ExtantMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExtantMod.MOD_ID);

    public static final RegistryObject<Block>
        ROCK_GROUND = BLOCKS.register("rock_ground", BlockRock::new);

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
//        RenderTypeLookup.setRenderLayer(ROCK_GROUND.get(), RenderType.getCutout());
    }
}
