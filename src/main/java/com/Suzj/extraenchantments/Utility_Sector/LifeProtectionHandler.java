package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LifeProtectionHandler {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void HandleEnchant(LivingHurtEvent fEvent) {

        if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();
        EntityLivingBase victim = fEvent.getEntityLiving();
        if (attacker == null || victim == null)
            return;

        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.LifeProtection, victim);
        if (level <= 0)
            return;
        ItemStack itemstack = EnchantmentHelper.getEnchantedItem(reg.LifeProtection, victim);
            EntityPlayer player = (EntityPlayer) victim;
            if (victim.getHealth() < player.getMaxHealth() *level *0.05){
                victim.heal((float) (player.getMaxHealth()*level *0.05-player.getHealth()));
                damageArmor(itemstack, 100, victim);

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