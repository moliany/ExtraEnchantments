package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EnchantmentSCN extends EnchantmentBase{
	public EnchantmentSCN()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".SCN");
		this.setRegistryName("SCN");
		
	}

	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.SCNEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.SCN;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return  10 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return  super.getMinEnchantability(par1) + 30;
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST) 
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(attacker == null)
    		return;
    	
    	ItemStack dmgSource = ((EntityLivingBase)fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		if(EnchantmentHelper.getEnchantmentLevel(reg.SCN, dmgSource) <= 0)
			return;
		
		if(this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
			return;

		EntityLivingBase victim = fEvent.getEntityLiving();
		int level = EnchantmentHelper.getEnchantmentLevel(reg.SCN, dmgSource);
		if(victim != null)
		{
			Random a = new Random();
			int i = a.nextInt(10);
			if(i==0)
			victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * level + 40, 1));
			if (i==1)
			victim.addPotionEffect(new PotionEffect(MobEffects.WITHER, 30 * level + 40, 1));
			if(i==2) {
				if (victim instanceof EntityZombie || victim instanceof EntitySkeleton || victim instanceof EntityWither || victim instanceof EntityWitherSkeleton) {
					victim.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 1));
				} else {
					victim.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 1));
				}
			}
				if(i==3)
					victim.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 30 * level + 40, 2));
				if(i==4) {
					if (victim instanceof EntityZombie || victim instanceof EntitySkeleton || victim instanceof EntityWither || victim instanceof EntityWitherSkeleton) {
						victim.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 30 * level + 40, 2));
					}else{
						victim.addPotionEffect(new PotionEffect(MobEffects.POISON, 30 * level + 40, 2));
					}
				}
				if(i==5)
					victim.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 30 * level + 40, 2));
				if (i==6)
					victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 30 * level + 40, 2));
				if(i==7)
					victim.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 30 * level + 40, 2));
				if(i==8)
					victim.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 30 * level + 40, 2));
				if(i==9)
					victim.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 30 * level + 40, 2));




		}
    }
    
}
