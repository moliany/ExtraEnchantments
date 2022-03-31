package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class EnchantmentSNN extends EnchantmentBase{
	public EnchantmentSNN()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".SNN");
		this.setRegistryName("SNN");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.SNN;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.SNN;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 20 + par1 * 10;
    }


    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }

	@SubscribeEvent
	public void dosome(LivingHurtEvent fEvent) {
		if (!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
			return;

		if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;

		EntityLivingBase user = (EntityLivingBase) fEvent.getSource().getTrueSource();

		if (user == null)
			return;

		ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
		if (dmgSource == null)
			return;
		int level = EnchantmentHelper.getEnchantmentLevel(reg.SNN, dmgSource);
		if (level <= 0)
			return;
		Random r = new Random();
		int i = r.nextInt(15);
		//if (user instanceof EntityPlayer)
		//	EntityPlayer player = (EntityPlayer) user;
		if(i==0)
			user.addPotionEffect(new PotionEffect(MobEffects.SPEED, (level * 150), 1));
		if(i==1)
			user.addPotionEffect(new PotionEffect(MobEffects.HASTE, (level * 150), 1));
		if(i==2)
			user.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, (level * 150), 1));
		if(i==3)
			user.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, (level * 150), 1));
		if(i==4)
			user.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, (level * 150), 2));
		if(i==5)
			user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, (level * 150), 1));
		if(i==6)
			user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, (level * 150), 1));
		if(i==7)
			user.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, (level * 150), 1));
		if(i==8)
			user.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, (level * 150), 1));
		if(i==9)
			user.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, (level * 150), 1));
		if(i==10)
			user.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, (level * 150), 2));
		if(i==11)
			user.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, (level * 150), 1));
		if(i==12)
			user.addPotionEffect(new PotionEffect(MobEffects.LUCK, + (level * 150), 1));
		if(i==13)
			user.addPotionEffect(new PotionEffect(MobEffects.SATURATION, (level * 150), 1));

	}
}
