package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LifeStealHandler {
    @SubscribeEvent(priority = EventPriority.LOW)
    public void HandlingFirst(LivingHurtEvent fEvent)
    {
        if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
            return;

        if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
        ItemStack weapon = attacker.getHeldItemMainhand();
        EntityLivingBase victim = fEvent.getEntityLiving();

        if(weapon == null)

            return;
        int levellifesteal = EnchantmentHelper.getEnchantmentLevel(reg.LifeSteal, weapon);

        if(EnchantmentHelper.getEnchantmentLevel(reg.LifeSteal, weapon) <= 0)
            return;

        if(reg.LifeSteal.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
            return;

        attacker.heal(fEvent.getAmount() * (levellifesteal * 0.025f + 0.025f));
        //UtilityAccessor.damageTarget(fEvent.getEntityLiving(), extraenchantments.PhysicalDamage, fEvent.getAmount() * (0.05F + ((levellifesteal * 0.05F))));

    }
}
