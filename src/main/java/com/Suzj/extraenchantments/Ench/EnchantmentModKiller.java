package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.Utility_Sector.EUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;





public class EnchantmentModKiller extends EnchantmentBase{
	public EnchantmentModKiller()
	{
		super(Rarity.RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".ModKiller");
		this.setRegistryName("ModKiller");
		
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.ModKillerEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.ModKiller;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
        return 12 + 12 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
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
		
		if(EnchantmentHelper.getEnchantmentLevel(reg.ModKiller, dmgSource) <= 0)
			return;
		
		if(this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
			return;
		
		int levelButchering = EnchantmentHelper.getEnchantmentLevel(reg.ModKiller, dmgSource);
		if(fEvent.getEntity() instanceof EntityPlayer
			||fEvent.getEntity() instanceof EntityBat
				||fEvent.getEntity() instanceof EntityChicken
				||fEvent.getEntity() instanceof EntityCow
				||fEvent.getEntity() instanceof EntityDonkey
				||fEvent.getEntity() instanceof EntityHorse
				||fEvent.getEntity() instanceof EntityMooshroom
				||fEvent.getEntity() instanceof EntityMule
				||fEvent.getEntity() instanceof EntityParrot
				||fEvent.getEntity() instanceof EntityPig
				||fEvent.getEntity() instanceof EntityRabbit
				||fEvent.getEntity() instanceof EntitySheep
				||fEvent.getEntity() instanceof EntitySkeletonHorse
				||fEvent.getEntity() instanceof EntityZombieHorse
				||fEvent.getEntity() instanceof EntitySquid
				||fEvent.getEntity() instanceof EntityVillager
				||fEvent.getEntity() instanceof EntityOcelot
				||fEvent.getEntity() instanceof EntitySnowman
				||fEvent.getEntity() instanceof EntityIronGolem
				||fEvent.getEntity() instanceof EntityWolf
				||fEvent.getEntity() instanceof EntityBlaze//敌对
				||fEvent.getEntity() instanceof EntityCreeper
				||fEvent.getEntity() instanceof EntityElderGuardian
				||fEvent.getEntity() instanceof EntityEndermite
				||fEvent.getEntity() instanceof EntityEvoker
				||fEvent.getEntity() instanceof EntityGhast
				||fEvent.getEntity() instanceof EntityGuardian
				||fEvent.getEntity() instanceof EntityHusk
				||fEvent.getEntity() instanceof EntityMagmaCube
				||fEvent.getEntity() instanceof EntityShulker
				||fEvent.getEntity() instanceof EntitySilverfish
				||fEvent.getEntity() instanceof EntitySkeleton
				||fEvent.getEntity() instanceof EntitySlime
				||fEvent.getEntity() instanceof EntityStray
				||fEvent.getEntity() instanceof EntityVex
				||fEvent.getEntity() instanceof EntityVindicator
				||fEvent.getEntity() instanceof EntityWitch
				||fEvent.getEntity() instanceof EntityWitherSkeleton
				||fEvent.getEntity() instanceof EntityZombie
				||fEvent.getEntity() instanceof EntityZombieVillager
				||fEvent.getEntity() instanceof EntityEnderman//有条件敌对
				||fEvent.getEntity() instanceof EntityPigZombie
				||fEvent.getEntity() instanceof EntitySpider
				||fEvent.getEntity() instanceof EntityCaveSpider
				||fEvent.getEntity() instanceof EntityDragon//boss
				||fEvent.getEntity() instanceof EntityWither
				||fEvent.getEntity() instanceof EntityGiantZombie//特殊
		)return;
		if(fEvent.getEntity() instanceof EntityLivingBase){
			EntityLivingBase entity = (EntityLivingBase) fEvent.getEntity();
			float Damage = (float) (entity.getHealth()*levelButchering*0.07);
			EUtil.damageTarget(fEvent.getEntityLiving(), extraenchantments.PhysicalDamage, Damage);//有用的标记
			fEvent.setAmount(fEvent.getAmount());
		}

				

    }
    
}