package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.potion.PotionRegistryHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FoggyMoonHandler {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public void HandleEnchant(LivingHurtEvent fEvent)
    {
        if(fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
            return;

        if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase attacker = (EntityLivingBase)fEvent.getSource().getTrueSource();
        EntityLivingBase victim = fEvent.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.FoggyMoon, attacker);
        if(level<=0)return;
        victim.addPotionEffect(new PotionEffect(PotionRegistryHandler.POTION_YISHANG,150,1));
        if(victim.isPotionActive(MobEffects.SLOWNESS)&&victim.isPotionActive(PotionRegistryHandler.POTION_YISHANG)&&victim.isPotionActive(PotionRegistryHandler.POTION_JINGLINGSHUQING)&&attacker.getEntityWorld().isRaining()&&!(attacker.getEntityWorld().isDaytime())){
            fEvent.setAmount(999);
        }
    }
}
