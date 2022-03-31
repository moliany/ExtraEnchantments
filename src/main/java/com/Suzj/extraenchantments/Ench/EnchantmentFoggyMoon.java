package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.TextFormatting;

public class EnchantmentFoggyMoon extends EnchantmentBase
{
	public EnchantmentFoggyMoon()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".FoggyMoon");
		this.setRegistryName("FoggyMoon");

	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.FoggyMoonEnable;
	}
		
	@Override
	public int getMaxLevel()
	{
		return ModConfig.level.FoggyMoon;
	}
		
	@Override
	public int getMinEnchantability(int par1)
	{
		return 30 + 12 * 1;
	}

	@Override
	public int getMaxEnchantability(int par1)
	{
		return super.getMinEnchantability(par1) + 50;
	}

	@Override
	public String getPrefix()
	{
		return TextFormatting.LIGHT_PURPLE.toString();
	}
}

