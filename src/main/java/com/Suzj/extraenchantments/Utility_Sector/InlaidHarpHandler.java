package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class InlaidHarpHandler {
    @SubscribeEvent(priority = EventPriority.LOW, receiveCanceled = false)
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
}
