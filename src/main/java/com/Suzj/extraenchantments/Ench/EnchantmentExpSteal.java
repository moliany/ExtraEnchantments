package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentExpSteal extends EnchantmentBase{
	public EnchantmentExpSteal(){
		super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
		this.setName(extraenchantments.MODID + ".ExpSteal");
		this.setRegistryName("ExpSteal");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.ExpStealEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.ExpSteal;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 15 + 15 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 30;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }
    @Override
    public void onEntityDamagedAlt(EntityLivingBase attacker, Entity victim, ItemStack stack, int level)
    {
        if(attacker==null||victim==null)return;
        if(level<=0)return;
    	if(!(victim instanceof EntityLivingBase))
    		return;
        if(victim instanceof EntityPlayer){
            EntityPlayer victimplayer = (EntityPlayer) victim;
            victimplayer.experienceLevel -= level;

        }
        if(attacker instanceof  EntityPlayer) {
            EntityPlayer player = (EntityPlayer) attacker;
            player.experienceLevel += level;
        }
    }
}
