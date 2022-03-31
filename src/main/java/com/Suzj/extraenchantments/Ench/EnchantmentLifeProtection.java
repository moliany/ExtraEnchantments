package com.Suzj.extraenchantments.Ench;


import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;


public class EnchantmentLifeProtection extends EnchantmentBase{

	public EnchantmentLifeProtection()
	{
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_CHEST, new EntityEquipmentSlot[]{EntityEquipmentSlot.CHEST});
		this.setName(extraenchantments.MODID + ".LifeProtection");
		this.setRegistryName("LifeProtection");
	}
	
	@Override
	public boolean isConfigEnabled()
	{
		return ModConfig.enabled.LifeProtectionEnable;
	}
	
	@Override
	public int getMaxLevel()
    {
        return ModConfig.level.LifeProtection;
    }
	
	@Override
    public int getMinEnchantability(int par1)
    {
		return 12 + (par1 - 1) * 14;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
    	return this.getMinEnchantability(par1) + 45;
    }


	@Override
	public void onUserHurt(EntityLivingBase user, Entity attacker, int level)
	{
		if(user == null)
			return;
		if (level<=0){
			return;
		}

		if(attacker == null || attacker.isDead)
			return;
		ItemStack itemstack = EnchantmentHelper.getEnchantedItem(reg.LifeProtection, user);
		if(user instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) user;
			if (player.getHealth() < player.getMaxHealth() *level *0.05){
				player.heal((float) (player.getMaxHealth()*level *0.05-player.getHealth()));
				damageArmor(itemstack, 100, user);

			}
		}

	}

	private void damageArmor(ItemStack stack, int amount, EntityLivingBase entity)
	{
		int slot = -1;
		int x = 0;
		for (ItemStack i : entity.getArmorInventoryList())
		{
			if (i == stack){
				slot = x;
				break;
			}
			x++;
		}
		if (slot == -1 || !(stack.getItem() instanceof net.minecraftforge.common.ISpecialArmor))
		{
			stack.damageItem(2, entity);
			return;
		}
		net.minecraftforge.common.ISpecialArmor armor = (net.minecraftforge.common.ISpecialArmor)stack.getItem();
		armor.damageArmor(entity, stack, DamageSource.causeThornsDamage(entity), amount, slot);
	}
}

