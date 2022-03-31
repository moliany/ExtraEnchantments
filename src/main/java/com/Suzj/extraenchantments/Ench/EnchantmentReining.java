package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentReining extends EnchantmentBase {
    public EnchantmentReining()
    {
        super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
        this.setName(extraenchantments.MODID + ".Reining");
        this.setRegistryName("Reining");

    }

    @Override
    public boolean isConfigEnabled()
    {
        return ModConfig.enabled.ReiningEnable;
    }

    @Override
    public int getMaxLevel()
    {
        return ModConfig.level.Reining;
    }

    @Override
    public int getMinEnchantability(int par1)
    {
        return 20 + 10 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 42;
    }

    @SubscribeEvent(priority= EventPriority.HIGH)
    public void HandleEnchant(PlayerInteractEvent.RightClickItem fEvent)
    {



        EntityPlayer living = fEvent.getEntityPlayer();
        ItemStack weapon = living.getHeldItemMainhand();
        Item item = living.getHeldItemMainhand().getItem();
        int level = EnchantmentHelper.getEnchantmentLevel(reg.Reining, weapon);
        if (level<=0)return;
        World world = living.getEntityWorld();
        double range =5;
        for (EntityLiving entityliving : world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(living.posX - range, living.posY - range, living.posZ - range, living.posX + range, living.posY + range, living.posZ + range))) {
            entityliving.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, level*50, level-1));

        }
        living.getCooldownTracker().setCooldown(item,250-(level*50));
        living.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, level*50, level-1));
    }
    @Override
    public String getPrefix() {
        return TextFormatting.LIGHT_PURPLE.toString();
    }
}
