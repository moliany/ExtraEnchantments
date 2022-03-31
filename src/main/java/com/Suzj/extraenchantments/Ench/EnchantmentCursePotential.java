package com.Suzj.extraenchantments.Ench;

import java.util.Map;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentCursePotential extends EnchantmentBase
{

	public EnchantmentCursePotential()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.BREAKABLE , new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".CursePotential");
		this.setRegistryName("CursePotential");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.CursePotentialEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.CursePotential;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 25 + 25 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

	@Override
	public boolean isAllowedOnBooks(){return false;}

    @Override
    public boolean canApplyTogether(Enchantment fTest)
    {
    	return super.canApplyTogether(fTest) && fTest != Enchantments.UNBREAKING
				&& fTest != Enchantments.AQUA_AFFINITY
				&& fTest != Enchantments.BANE_OF_ARTHROPODS
				&& fTest != Enchantments.BINDING_CURSE
				&& fTest != Enchantments.BLAST_PROTECTION
				&& fTest != Enchantments.DEPTH_STRIDER
				&& fTest != Enchantments.EFFICIENCY
				&& fTest != Enchantments.FEATHER_FALLING
				&& fTest != Enchantments.FIRE_ASPECT
				&& fTest != Enchantments.FIRE_PROTECTION
				&& fTest != Enchantments.FLAME
				&& fTest != Enchantments.FORTUNE
				&& fTest != Enchantments.FROST_WALKER
				&& fTest != Enchantments.INFINITY
				&& fTest != Enchantments.KNOCKBACK
				&& fTest != Enchantments.LOOTING
				&& fTest != Enchantments.LUCK_OF_THE_SEA
				&& fTest != Enchantments.LURE
				&& fTest != Enchantments.MENDING
				&& fTest != Enchantments.POWER
				&& fTest != Enchantments.PROJECTILE_PROTECTION
				&& fTest != Enchantments.PROTECTION
				&& fTest != Enchantments.PUNCH
				&& fTest != Enchantments.RESPIRATION
				&& fTest != Enchantments.SHARPNESS
				&& fTest != Enchantments.SILK_TOUCH
				&& fTest != Enchantments.SMITE
				&& fTest != Enchantments.SWEEPING
				&& fTest != Enchantments.THORNS

		;
    }
    
    @Override
    public boolean canApply(ItemStack i)
    {
    	return super.canApply(i) && !i.getUnlocalizedName().contains("gold");
    }
    
    @Override
    public boolean isTreasureEnchantment()
    {
    	return true;
    }
    @Override
    public boolean isCurse()
    {
    	return true;
    }

	@SubscribeEvent
	public void onAnvilAttach(AnvilUpdateEvent event)
	{
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		if(left.isEmpty() || right.isEmpty())
		{
			return;
		}
		if(right.getItem()== Items.ENCHANTED_BOOK)
		{

			Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(right);
			if(enchantments.containsKey(this) && enchantments.get(this) >= 1)
			{
				if(left.isStackable())
				{
					event.setOutput(ItemStack.EMPTY);
					return;
				}


				if(EnchantmentHelper.getEnchantments(left).size()==0)
				{
					event.setOutput(ItemStack.EMPTY);
					return;
				}

				if(EnchantmentHelper.getEnchantmentLevel(this, left) >= 1)
				{
					event.setOutput(ItemStack.EMPTY);
					return;
				}

				ItemStack output = left.copy();
				output.setRepairCost(30);
				output.addEnchantment(this, 1);

				event.setOutput(output);
				event.setCost(30);
			}
		}
	}
}
