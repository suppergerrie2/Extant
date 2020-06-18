package com.sindarin27.extant.entities;

import com.sindarin27.extant.ExtantMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, ExtantMod.MOD_ID);

    public static final RegistryObject<EntityType<EntityRockThrown>>
        ROCK_THROWN = ENTITY_TYPES.register("rock_thrown", () ->
            EntityType.Builder.<EntityRockThrown>create(EntityRockThrown::new, EntityClassification.MISC).build(ExtantMod.MOD_ID + ":rock_thrown"));

    @SubscribeEvent
    public static void registerClient(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ROCK_THROWN.get(), renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));
    }

}
