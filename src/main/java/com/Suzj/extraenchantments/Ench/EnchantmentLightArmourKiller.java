package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentLightArmourKiller extends EnchantmentBase{
	public EnchantmentLightArmourKiller() {
		super(Rarity.UNCOMMON, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".LightArmourKiller");
		this.setRegistryName("LightArmourKiller");

	}

	@Override
	public boolean isConfigEnabled() {
		return ModConfig.enabled.LightArmourKillerEnable;
	}

	@Override
	public int getMaxLevel() {
		return ModConfig.level.LightArmourKiller;
	}

	@Override
	public int getMinEnchantability(int par1) {
		return 15 + (par1 - 1) * 8;
	}

	@Override
	public int getMaxEnchantability(int par1) {
		return this.getMinEnchantability(par1) + 30;
	}


	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void HandleEnchant(LivingHurtEvent fEvent) {
		if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob" && fEvent.getSource().damageType == "arrow")
			return;

		if(!(fEvent.getEntity() instanceof EntityLivingBase))
			return;

		if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;

		ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();

		if(EnchantmentHelper.getEnchantmentLevel(reg.LightArmourKiller, dmgSource) <= 0)
			return;

		if(this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
			return;

		EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();
		int level = EnchantmentHelper.getEnchantmentLevel(reg.LightArmourKiller, dmgSource);

		if(attacker == null)
			return;

		int attackerTotalArmorValue = attacker.getTotalArmorValue();
		float seeDamage = fEvent.getAmount();
		float extraDamage;


		if (attackerTotalArmorValue==0){
			extraDamage = seeDamage * level * 0.3f;
			fEvent.setAmount(seeDamage + extraDamage);
		}
		if(attackerTotalArmorValue<=2 && attackerTotalArmorValue>0) {
			extraDamage = seeDamage * level * 0.25f;
			fEvent.setAmount(seeDamage + extraDamage);
		}
		if (attackerTotalArmorValue<=4 && attackerTotalArmorValue>2){
			extraDamage = seeDamage * level * 0.2f;
			fEvent.setAmount(seeDamage + extraDamage);
		}
		if (attackerTotalArmorValue<=6 && attackerTotalArmorValue>4){
			extraDamage = seeDamage * level * 0.15f;
			fEvent.setAmount(seeDamage + extraDamage);
		}
		if (attackerTotalArmorValue<=8 && attackerTotalArmorValue>6){
			extraDamage = seeDamage * level * 0.1f;
			fEvent.setAmount(seeDamage + extraDamage);
		}
	}
}
   