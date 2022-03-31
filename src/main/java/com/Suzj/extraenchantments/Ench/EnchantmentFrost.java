package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enum.EnumList;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class EnchantmentFrost extends EnchantmentBase{
	BlockPos pos;
	BlockPos pos1;
	BlockPos pos2;
	BlockPos pos3;
	BlockPos pos4;
	BlockPos pos5;
	BlockPos pos6;
	BlockPos pos7;
	BlockPos pos8;
	BlockPos pos9;
	BlockPos pos10;
	BlockPos pos11;
	BlockPos pos12;
	BlockPos pos13;
	BlockPos pos14;
	BlockPos pos15;
	BlockPos pos16;
	BlockPos pos17;
	BlockPos pos18;
	int patch;
	int patch1;
	int patch2;
	int patch3;
	int patch4;
	int patch5;
	int patch6;
	int patch7;
	int patch8;
	int patch9;
	int patch10;
	int patch11;
	int patch12;
	int patch13;
	int patch14;
	int patch15;
	int patch16;
	int patch17;
	int patch18;
	int count = 0;

	public EnchantmentFrost()
	{
	super(Rarity.RARE, EnumList.SWORD, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
    this.setName(extraenchantments.MODID + ".Frost");
	this.setRegistryName("Frost");

	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.FrostEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.Frost;
    }
	@Override
    public int getMinEnchantability(int par1)
    {
        return 24 + 13 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return this.getMinEnchantability(par1) + 40;
    }

	@Override
	public boolean isAllowedOnBooks()
	{
		return true;
	}
    
    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onEntityDamaged(LivingDamageEvent fEvent) throws InterruptedException {
    	if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
			return;
    	
    	if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
    		return;
    	
    	EntityLivingBase user = (EntityLivingBase)fEvent.getSource().getTrueSource();
    	if(user == null)
    		return;
    	
    	ItemStack dmgSource = user.getHeldItemMainhand();
		if(dmgSource == null)
			return;
		
		int level = EnchantmentHelper.getEnchantmentLevel(reg.Frost, dmgSource);
    	if(level <= 0)
    	    return;
    	
    	if(this.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
    		return;
    	
    	EntityLivingBase victim = fEvent.getEntityLiving();
    	
    	int ice = 0;
    	int numb = 0;
    	
    	
    	//Capping at 3 (previously was 7) for Roguelike Dungeons compatibility三级上限
    	int numbCap = 3;//疑似3次才能触发 验证：否 实际:冻结触发后不用再打3下
    	
    	int iceCap = 7;
        	
    	if(victim.isPotionActive(MobEffects.SLOWNESS) == true & victim.isPotionActive(MobEffects.MINING_FATIGUE) == true){
    
    		PotionEffect pot1 = victim.getActivePotionEffect(MobEffects.SLOWNESS);
        	PotionEffect pot2 = victim.getActivePotionEffect(MobEffects.MINING_FATIGUE);
    		
        ice = pot1.getAmplifier() + 1;
    	numb = pot2.getAmplifier() + 1;
    	
    	
    	if(ice > iceCap)
        	ice = iceCap;
        if(numb > numbCap)
        	numb = numbCap;
    	
        if(victim instanceof EntityPlayer){
    	victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * level + 40, ice));
    	victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 30 * level + 40, numb));
    	
        }
        else if(victim instanceof EntityLivingBase){
        	
        	ice += 1;
        	numb += 1;
        	
        	if(ice > iceCap)
            	ice = iceCap;
            if(numb > numbCap)
            	numb = numbCap;	
        	
        victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 30 * level + 40, ice));    	  
        victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 30 * level + 40, numb));
        }
    	}
    	
    	else if(victim instanceof EntityPlayer){
    		victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * level + 40, 0));
    		victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * level + 40, 0));
    		
    	}
    	else if(victim instanceof EntityLivingBase){
			victim.addPotionEffect(new PotionEffect(MobEffects.WITHER, 500, 1));
    		victim.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * level + 40, 1));
    		victim.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 20 * level + 40, 1));
    		
    	}
    	
    	//ice >= iceCap && numb >= numbCap && victim.getEntityWorld().getGameRules().getBoolean("mobGriefing") == true
    	//判断标记
    	if(ice >= iceCap && numb >= numbCap && victim.getEntityWorld().getGameRules().getBoolean("mobGriefing") == true){
    	
    	float chance = (float) Math.random() * 100f;
    		
    	if(chance < 10000){
    	victim.extinguish();
    	dmgSource.damageItem(6 - level, user);//
    	
    	//float damage = EnchantmentsUtility.CalculateDamageIgnoreSwipe(fEvent.getAmount(), 2.0f, 0.65f, 1.0f, user, Smc_040.freezing);
    
    	//if(!(user instanceof EntityPlayer)){
    	//EnchantmentsUtility.ImprovedKnockBack(user, 0.25f, user.posX - victim.posX, user.posZ - victim.posZ);
    	//}
    	
    	
    	victim.getEntityWorld().playSound(null, victim.posX, victim.posY, victim.posZ, SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE, SoundCategory.MASTER, 0.8f, -1f);
    	
    	pos =                  new BlockPos(victim.posX,victim.posY,victim.posZ);
    	pos1 =                 new BlockPos(victim.posX, victim.posY + 1,  victim.posZ);
    	pos2 =                 new BlockPos(victim.posX,  victim.posY + 2,  victim.posZ);
    	pos3 =                 new BlockPos( victim.posX + 1,  victim.posY,  victim.posZ);
    	pos4 =                 new BlockPos(victim.posX,  victim.posY, victim.posZ + 1);
    	pos5 =                 new BlockPos( victim.posX - 1,  victim.posY, victim.posZ);
    	pos6 =                 new BlockPos( victim.posX, victim.posY,  victim.posZ - 1);
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos, Blocks.PACKED_ICE.getDefaultState());
    	victim.getEntityWorld().scheduleUpdate(pos, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
		patch=1;
    	}
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos1, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos1, Blocks.PACKED_ICE.getDefaultState());
		patch1=1;
    	victim.getEntityWorld().scheduleUpdate(pos1, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos2, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos2, Blocks.PACKED_ICE.getDefaultState());
		patch2=1;
    	victim.getEntityWorld().scheduleUpdate(pos2, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos3, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos3, Blocks.PACKED_ICE.getDefaultState());
		patch3=1;
    	victim.getEntityWorld().scheduleUpdate(pos3, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos4, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos4, Blocks.PACKED_ICE.getDefaultState());
		patch4=1;
    	victim.getEntityWorld().scheduleUpdate(pos4, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos5, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos5, Blocks.PACKED_ICE.getDefaultState());
		patch5=1;
    	victim.getEntityWorld().scheduleUpdate(pos5, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos6, true, user.getAdjustedHorizontalFacing(), user)){
    	victim.getEntityWorld().setBlockState(pos6, Blocks.PACKED_ICE.getDefaultState());
		patch6=1;
    	victim.getEntityWorld().scheduleUpdate(pos6, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	}

    	
    	if(level >= 3){

    		
    	pos7  =                 new BlockPos( victim.posX - 1, victim.posY,  victim.posZ - 1);
    	pos8  =                 new BlockPos( victim.posX + 1, victim.posY,  victim.posZ + 1);
    	pos9  =                 new BlockPos( victim.posX - 1, victim.posY + 1,  victim.posZ);
    	pos10 =                 new BlockPos( victim.posX + 1, victim.posY + 1,  victim.posZ);
    	pos11 =                 new BlockPos( victim.posX, victim.posY + 1,  victim.posZ - 1);
    	pos12 =                 new BlockPos( victim.posX, victim.posY + 1,  victim.posZ + 1);
    	pos13 =                 new BlockPos( victim.posX + 2, victim.posY,  victim.posZ    );
    	pos14 =                 new BlockPos( victim.posX - 2, victim.posY,  victim.posZ    );
    	pos15 =                 new BlockPos( victim.posX, victim.posY,  victim.posZ - 2    );
    	pos16 =                 new BlockPos( victim.posX, victim.posY,  victim.posZ + 2    );
    	pos17 =                 new BlockPos( victim.posX + 1, victim.posY,  victim.posZ - 1    );
    	pos18 =                 new BlockPos( victim.posX - 1, victim.posY,  victim.posZ + 1    );
    		
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos7, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos7, Blocks.PACKED_ICE.getDefaultState());
			patch7=1;
    	    victim.getEntityWorld().scheduleUpdate(pos7, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    		
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos8, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos8, Blocks.PACKED_ICE.getDefaultState());
			patch8=1;
    	    victim.getEntityWorld().scheduleUpdate(pos8, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos9, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos9, Blocks.PACKED_ICE.getDefaultState());
			patch9=1;
    	    victim.getEntityWorld().scheduleUpdate(pos9, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos10, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos10, Blocks.PACKED_ICE.getDefaultState());
			patch10=1;
    	    victim.getEntityWorld().scheduleUpdate(pos10, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos11, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos11, Blocks.PACKED_ICE.getDefaultState());
			patch11=1;
    	    victim.getEntityWorld().scheduleUpdate(pos11, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos12, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos12, Blocks.PACKED_ICE.getDefaultState());
			patch12=1;
    	    victim.getEntityWorld().scheduleUpdate(pos12, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos13, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos13, Blocks.PACKED_ICE.getDefaultState());
			patch13=1;
    	    victim.getEntityWorld().scheduleUpdate(pos13, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos14, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos14, Blocks.PACKED_ICE.getDefaultState());
			patch14=1;
    	    victim.getEntityWorld().scheduleUpdate(pos14, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos15, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos15, Blocks.PACKED_ICE.getDefaultState());
			patch15=1;
    	    victim.getEntityWorld().scheduleUpdate(pos15, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos16, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos16, Blocks.PACKED_ICE.getDefaultState());
			patch16=1;
    	    victim.getEntityWorld().scheduleUpdate(pos16, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos17, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos17, Blocks.PACKED_ICE.getDefaultState());
			patch17=1;
    	    victim.getEntityWorld().scheduleUpdate(pos17, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}	
    	
    	if(victim.getEntityWorld().mayPlace(Blocks.PACKED_ICE, pos18, true, user.getAdjustedHorizontalFacing(), user)){
    	    victim.getEntityWorld().setBlockState(pos18, Blocks.PACKED_ICE.getDefaultState());
			patch18=1;
    	    victim.getEntityWorld().scheduleUpdate(pos18, Blocks.PACKED_ICE, MathHelper.getInt(user.getRNG(), 120, 240));
    	    	}

    	}

			count = 0;



    	}
    	
    	
    }
    }
	@SubscribeEvent
	public void doDestroy(PlayerInteractEvent.LeftClickBlock fEvent){
		EntityPlayer attacker =  fEvent.getEntityPlayer();
		if(attacker==null)return;
		ItemStack weapon = attacker.getHeldItemMainhand();
		int level = EnchantmentHelper.getEnchantmentLevel(reg.Frost, weapon);
		if (level<=0)return;
		if(count < 2){
			/*if(pos==null
					||pos1==null
					||pos2==null
					||pos3==null
					||pos4==null
					||pos5==null
					||pos6==null||pos7==null
					||pos8==null||pos9==null
					||pos10==null||pos12==null
					||pos11==null||pos13==null
					||pos14==null||pos15==null
					||pos16==null||pos17==null
					||pos18==null)return;*/
		//Item item = attacker.getHeldItemMainhand().getItem();
			if(patch==1&&pos!=null)attacker.getEntityWorld().destroyBlock(pos,false);
		if(patch1==1&&pos1!=null)attacker.getEntityWorld().destroyBlock(pos1,false);
		if(patch2==1&&pos2!=null)attacker.getEntityWorld().destroyBlock(pos2,false);
		if(patch3==1&&pos3!=null)attacker.getEntityWorld().destroyBlock(pos3,false);
		if(patch4==1&&pos4!=null)attacker.getEntityWorld().destroyBlock(pos4,false);
		if(patch5==1&&pos5!=null)attacker.getEntityWorld().destroyBlock(pos5,false);
		if(patch6==1&&pos6!=null)attacker.getEntityWorld().destroyBlock(pos6,false);
		if(patch7==1&&pos7!=null)attacker.getEntityWorld().destroyBlock(pos7,false);
		if(patch8==1&&pos8!=null)attacker.getEntityWorld().destroyBlock(pos8,false);
		if(patch9==1&&pos9!=null)attacker.getEntityWorld().destroyBlock(pos9,false);
		if(patch10==1&&pos10!=null)attacker.getEntityWorld().destroyBlock(pos10,false);
		if(patch11==1&&pos11!=null)attacker.getEntityWorld().destroyBlock(pos11,false);
		if(patch12==1&&pos12!=null)attacker.getEntityWorld().destroyBlock(pos12,false);
		if(patch13==1&&pos13!=null)attacker.getEntityWorld().destroyBlock(pos13,false);
		if(patch14==1&&pos14!=null)attacker.getEntityWorld().destroyBlock(pos14,false);
		if(patch15==1&&pos15!=null)attacker.getEntityWorld().destroyBlock(pos15,false);
		if(patch16==1&&pos16!=null)attacker.getEntityWorld().destroyBlock(pos16,false);
		if(patch17==1&&pos17!=null)attacker.getEntityWorld().destroyBlock(pos17,false);
		if(patch18==1&&pos18!=null)attacker.getEntityWorld().destroyBlock(pos18,false);
			//attacker.getCooldownTracker().setCooldown(item,50);
		count++;
	}
	}

}