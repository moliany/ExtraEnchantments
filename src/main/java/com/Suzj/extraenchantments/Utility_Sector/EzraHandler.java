package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.potion.PotionRegistryHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EzraHandler {
    Potion potion = PotionRegistryHandler.POTION_JINGLINGSHUQING;
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

        if(EnchantmentHelper.getEnchantmentLevel(reg.Ezra, dmgSource) <= 0)
            return;

        if(reg.Ezra.isOffensivePetDisallowed(fEvent.getSource().getImmediateSource(), fEvent.getSource().getTrueSource()))
            return;

        EntityLivingBase victim = fEvent.getEntityLiving();
        victim.addPotionEffect(new PotionEffect(potion,100,1));

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = false)
    public void HandleEnchant2(LivingAttackEvent fEvent)
    {

        if(!(fEvent.getEntity() instanceof EntityLivingBase))
            return;

        if(!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;

        EntityLivingBase victim = fEvent.getEntityLiving();

        if(victim == null)
            return;

        EntityLivingBase attacker = (EntityLivingBase) fEvent.getSource().getTrueSource();

        if(attacker == null)
            return;



        double randX = 0.65 + victim.getRNG().nextDouble() * 0.25f;
        randX = victim.getRNG().nextBoolean() ? randX * -1 : randX;

        double randZ = 0.65 + victim.getRNG().nextDouble() * 0.25f;
        randZ = victim.getRNG().nextBoolean() ? randZ * -1 : randZ;


        if (attacker.isPotionActive(potion)) {
            if (!attacker.world.isRemote)
                victim.getEntityWorld().playSound(null, victim.posX, victim.posY, victim.posZ, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS, 0.3f, victim.getRNG().nextFloat() * 2.25f + 0.75f);
            fEvent.setCanceled(true);//取消苦力怕爆炸伤害
            if(victim instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) victim;
                player.sendMessage(new TextComponentString("Ezra took you under her wing"));
            }

            victim.hurtResistantTime = 30;//无敌
        }
    }
}
