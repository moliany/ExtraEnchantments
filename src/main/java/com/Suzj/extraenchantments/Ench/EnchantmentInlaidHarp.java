package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentInlaidHarp extends EnchantmentBase {

	public EnchantmentInlaidHarp()
	{
	super(Rarity.RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
    this.setName(extraenchantments.MODID + ".InlaidHarp");
	this.setRegistryName("InlaidHarp");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.InlaidHarpEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.InlaidHarp;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 30;
    }
    

	@SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = false)
	public void HandleEnchant(LivingHurtEvent fEvent) {

		if (!(fEvent.getEntity() instanceof EntityLivingBase))
			return;

		if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;
		EntityLivingBase victim = fEvent.getEntityLiving();
		int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.InlaidHarp, victim);
		if (level<=0)
		return;
		if(level==1) {
			if (fEvent.getAmount() > victim.getMaxHealth()) {
				victim.heal((victim.getMaxHealth() * 0.1f)*-1f);
				fEvent.setCanceled(true);
				victim.hurtResistantTime = 15 + level * 5;
			}
		}
		if (level==2){
			if (fEvent.getAmount() > victim.getMaxHealth()*0.75) {
				victim.heal((victim.getMaxHealth() * 0.25f)*-1f);
				fEvent.setCanceled(true);
				victim.hurtResistantTime = 15 + level * 5;
			}
		}
		if (level==3){
			if (fEvent.getAmount() > victim.getMaxHealth()*0.5) {
				victim.setHealth(victim.getHealth()-victim.getMaxHealth()*0.5f);
				fEvent.setCanceled(true);
				victim.hurtResistantTime = 15 + level * 5;
			}
		}
	}

	@Override
	public int calcModifierDamage(int level, DamageSource source)
	{
		if(Math.random() < 0.35f)
			return source.canHarmInCreative() ? 0 : (source == DamageSource.FALL ? level * 5 : 0);

		else return source.canHarmInCreative() ? 0 : (source == DamageSource.FALL ? level * 4 : 0);
	}


}