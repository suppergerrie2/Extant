package com.sindarin27.extant.items;

import com.sindarin27.extant.ExtantMod;
import com.sindarin27.extant.blocks.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExtantMod.MOD_ID);

    public static final RegistryObject<Item>
        ROCK = ITEMS.register("stone_rock", () -> new ItemRock(ModBlocks.ROCK_GROUND.get(), new Item.Properties())),
        SLINGSHOT = ITEMS.register("slingshot", () -> new ItemSlingshot(new Item.Properties()));
}
