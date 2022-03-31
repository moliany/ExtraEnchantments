package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.Utility_Sector.EUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentTimeDetention extends EnchantmentBase {
    public static int dalay;
    public static float seeDamage;
    public static int start = 0;
    public static EntityLivingBase victim;
    public static boolean ishurt;
    public EnchantmentTimeDetention(){
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
        this.setName(extraenchantments.MODID + ".TimeDetention");
        this.setRegistryName("TimeDetention");
    }
    @Override
    public boolean isConfigEnabled()
    {
        return ModConfig.enabled.TimeDetentionEnable;
    }

    @Override
    public int getMaxLevel()
    {
        return ModConfig.level.TimeDetention;
    }

    @Override
    public int getMinEnchantability(int par1)
    {
        return  30;
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return  super.getMinEnchantability(par1) + 30;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onAttract(LivingAttackEvent fEvent){


        if (!(fEvent.getSource().getTrueSource() instanceof EntityLivingBase))
            return;



        ItemStack dmgSource = ((EntityLivingBase) fEvent.getSource().getTrueSource()).getHeldItemMainhand();
        if (dmgSource == null)
            return;
        EntityLivingBase e = fEvent.getEntityLiving();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(this,e);
        if(level<=0)return;
        victim = e;
        dalay = level*1000;
        seeDamage = fEvent.getAmount();
        victim.hurtResistantTime = 10;
        fEvent.setCanceled(true);
        ishurt = true;
        if(seeDamage > e.getHealth()){

        }
    }

    @SubscribeEvent
    public void doDetention(LivingEvent.LivingUpdateEvent fEvent){
        if(victim == null)return;
        if(!ishurt)return;
        start++;
        if(victim instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) victim;
            String s =String.valueOf(start);
            player.sendMessage(new TextComponentString(s));
        }
        if(start>=dalay){
            if(victim instanceof EntityPlayer){
                EntityPlayer player = (EntityPlayer) victim;
            }
            start=0;
            ishurt = false;
            EUtil.damageTarget(victim,extraenchantments.PhysicalDamage,seeDamage);
        }
    }
}
