package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;

public class EnchantmentStarRain extends EnchantmentBase{
	public EnchantmentStarRain() {
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".StarRain");
		this.setRegistryName("StarRain");
	}

	@Override
	public boolean isConfigEnabled() {
		return ModConfig.enabled.StarRainEnable;
	}

	@Override
	public int getMaxLevel() {
		return ModConfig.level.StarRain;
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 15 + 15 *par1;
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return super.getMinEnchantability(par1) + 40;
	}

	@SubscribeEvent
	public void onHurt(LivingHurtEvent fEvent) {
		if (!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
			return;

		if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;

		EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();

		if (attacker == null)
			return;

		ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if (dmgSource == null)
			return;

		if (EnchantmentHelper.getEnchantmentLevel(reg.StarRain, dmgSource) <= 0)
			return;

		if (this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
			return;

		Collection<PotionEffect> collectPotion = attacker.getActivePotionEffects();
		int size = collectPotion.size();
		attacker.clearActivePotions();

		//attacker.removePotionEffect();
		float seeDamage = fEvent.getAmount();
		float extraDamage = seeDamage * size;
		fEvent.setAmount(seeDamage + extraDamage);


	}
	@Override
	public String getPrefix() {
		return TextFormatting.LIGHT_PURPLE.toString();
	}
}
