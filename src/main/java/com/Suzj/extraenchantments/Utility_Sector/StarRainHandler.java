package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collection;

public class StarRainHandler {
    @SubscribeEvent
    public void onHurt(LivingHurtEvent fEvent) {
        if (!fEvent.getSource().damageType.equals("player") && !fEvent.getSource().damageType.equals("mob"))
            return;

        if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();

        if (attacker == null)
            return;

        ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
        if (dmgSource == null)
            return;

        if (EnchantmentHelper.getEnchantmentLevel(reg.StarRain, dmgSource) <= 0)
            return;

        if (reg.StarRain.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
            return;

        Collection<PotionEffect> collectPotion = attacker.getActivePotionEffects();
        int size = collectPotion.size();
        attacker.clearActivePotions();
        float seeDamage = fEvent.getAmount();
        float extraDamage = seeDamage * size;
        fEvent.setAmount(seeDamage + extraDamage);


    }
}
