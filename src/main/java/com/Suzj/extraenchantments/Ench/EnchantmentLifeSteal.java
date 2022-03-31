package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;


public class EnchantmentLifeSteal extends EnchantmentBase
{
	public EnchantmentLifeSteal()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".LifeSteal");
		this.setRegistryName("LifeSteal");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.LifeStealEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.LifeSteal;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 6 + 8 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }

    


    }
 
    

