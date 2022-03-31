package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExpStealHandler {
    @SubscribeEvent
    public void HandleEnchant(LivingHurtEvent fEvent) {
        if (fEvent.getSource().damageType != "player" && fEvent.getSource().damageType != "mob")
            return;

        if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();
        EntityLivingBase victim = fEvent.getEntityLiving();
        if (attacker == null||victim==null)
            return;

        ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
        if (dmgSource == null)
            return;
        int level = EnchantmentHelper.getEnchantmentLevel(reg.ExpSteal, dmgSource);
        if (level <= 0)
            return;
        if(victim instanceof EntityPlayer){
            EntityPlayer victimplayer = (EntityPlayer) victim;
            victimplayer.experienceLevel -= level;

        }
        if(attacker instanceof  EntityPlayer) {
            EntityPlayer player = (EntityPlayer) attacker;
            player.experienceLevel += level;
            player.sendMessage(new TextComponentString("do effect"));
        }
    }
}
