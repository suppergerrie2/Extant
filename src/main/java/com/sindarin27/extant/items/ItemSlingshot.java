package com.sindarin27.extant.items;
import com.sindarin27.extant.ExtantMod;
import com.sindarin27.extant.entities.EntityRockThrown;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.*;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class ItemSlingshot extends ShootableItem {
    public static final ResourceLocation ROCKS_LOCATION = new ResourceLocation(ExtantMod.MOD_ID, "slingshot_rocks");

    public static final Predicate<ItemStack> ROCKS = (stack) -> {
        return stack.getItem().isIn(ItemTags.getCollection().getOrCreate(ROCKS_LOCATION));
    };

    public ItemSlingshot(Properties properties) {
        super(properties);

        this.addPropertyOverride(new ResourceLocation("pull"), (p_210310_0_, p_210310_1_, p_210310_2_) -> {
            if (p_210310_2_ == null) {
                return 0.0F;
            } else {
                return !(p_210310_2_.getActiveItemStack().getItem() instanceof ItemSlingshot) ? 0.0F : (float)(p_210310_0_.getUseDuration() - p_210310_2_.getItemInUseCount()) / 20.0F;
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), (p_210309_0_, p_210309_1_, p_210309_2_) -> {
            return p_210309_2_ != null && p_210309_2_.isHandActive() && p_210309_2_.getActiveItemStack() == p_210309_0_ ? 1.0F : 0.0F;
        });
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity)entityLiving;
            boolean flag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0; //Because rocks are expensive okay
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(ModItems.ROCK.get());
                }

                float f = getArrowVelocity(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        EntityRockThrown rockThrown = new EntityRockThrown(playerentity, worldIn);
                        rockThrown.setItem(itemstack);


                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            Vec3d motion = rockThrown.getMotion().mul(1.2f, 1.2f, 1.2f);
                            rockThrown.setVelocity(motion.x, motion.y, motion.z);
                        }

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });

                        rockThrown.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f, 3f);
                        worldIn.addEntity(rockThrown);
                        //worldIn.addEntity(abstractarrowentity);
                    }

                    //worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

        if (!playerIn.abilities.isCreativeMode && !flag) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    public static float getArrowVelocity(int charge) {
        float f = (float)charge / 40.0F; // 4 seconds for full charge
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public Predicate<ItemStack> getInventoryAmmoPredicate() { return ROCKS; }

    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}
