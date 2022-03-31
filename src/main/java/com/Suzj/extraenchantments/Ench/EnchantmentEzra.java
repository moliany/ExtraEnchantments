package com.Suzj.extraenchantments.Ench;




import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;

import com.Suzj.extraenchantments.Main_Sector.ModConfig;


import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.potion.PotionRegistryHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentEzra extends EnchantmentBase{
	Potion potion = PotionRegistryHandler.POTION_JINGLINGSHUQING;
	public EnchantmentEzra()
	{
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".Ezra");
		this.setRegistryName("Ezra");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.EzraEnable;
	}
    
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Ezra;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 30 + 10 * par1;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

	@Override
	public boolean isTreasureEnchantment() {
		return true;
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

		if(EnchantmentHelper.getEnchantmentLevel(reg.Ezra, dmgSource) <= 0)
			return;

		if(this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
			return;

		EntityLivingBase victim = fEvent.getEntityLiving();
		victim.addPotionEffect(new PotionEffect(potion,50,1));

	}

	@SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = false)
	public void HandleEnchant2(LivingAttackEvent fEvent)
	{

		if(!(fEvent.getEntity() instanceof EntityLivingBase))
			return;

		if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
			return;

		EntityLivingBase victim = fEvent.getEntityLiving();

		if(victim == null)
			return;

		EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();

		if(attacker == null)
			return;


		if (attacker.isPotionActive(potion)) {
			if (!attacker.world.isRemote)
			victim.getEntityWorld().playSound(null, victim.posX, victim.posY, victim.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.3f, victim.getRNG().nextFloat() * 2.25f + 0.75f);
			fEvent.setCanceled(true);//取消苦力怕爆炸伤害
			if(victim instanceof EntityPlayer){
				EntityPlayer player = (EntityPlayer) victim;
				player.sendMessage(new TextComponentString("Ezra took you under her wing"));
			}

			victim.hurtResistantTime = 30;//无敌
		}
	}


	@Override
	public String getPrefix() {
		return TextFormatting.BLUE.toString();
	}
}
