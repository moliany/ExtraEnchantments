package com.Suzj.extraenchantments.Utility_Sector;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.EnumSet;

public class EUtil {
    private static Method applyPotionDamageCalculations;
    private static Method applyArmorCalculations;
    public static void damageTarget(EntityLivingBase target, DamageSource damageSrc, float damageAmount)
    {
        if (!target.isEntityInvulnerable(damageSrc))
        {
            if (damageAmount <= 0) return;
            damageAmount = applyArmorCalculations(target, damageSrc, damageAmount);
            damageAmount = applyPotionDamageCalculations(target, damageSrc, damageAmount);
            float f = damageAmount;
            damageAmount = Math.max(damageAmount - target.getAbsorptionAmount(), 0.0F);
            target.setAbsorptionAmount(target.getAbsorptionAmount() - (f - damageAmount));

            if (damageAmount > 0.0F)
            {
                float f1 = target.getHealth();
                target.getCombatTracker().trackDamage(damageSrc, f1, damageAmount);
                // System.out.println(target.getHealth());
                target.setHealth(f1 - damageAmount); // Forge: moved to fix MC-121048
                // System.out.println(target.getHealth() + "POST");
                target.setAbsorptionAmount(target.getAbsorptionAmount() - damageAmount);
            }
        }
    }

    public static float applyPotionDamageCalculations(EntityLivingBase target, DamageSource source, float amount) {
        if (applyPotionDamageCalculations == null) {
            //applyPotionDamageCalculations = ObfuscationReflectionHelper.findMethod(EntityLivingBase.class, "func_70672_c", float.class, DamageSource.class, float.class);
        }
        try {
            applyPotionDamageCalculations.invoke(target, source, amount);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }

    public static float applyArmorCalculations(EntityLivingBase target, DamageSource source, float amount) {
        if (applyArmorCalculations == null) {
            //public static Method findMethod(Class<?> clazz, String srgName, Class<?> returnType, Class<?>... parameterTypes)
           // applyArmorCalculations = ObfuscationReflectionHelper.findMethod(EntityLivingBase.class, "func_70655_b", float.class, DamageSource.class, float.class);
        }
        try {
            assert applyArmorCalculations != null;
            applyArmorCalculations.invoke(target, source, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return amount;
    }
    public static void spawnParticle(World world, EnumParticleTypes type, double x, double y, double z, double motion) {
        if (world instanceof WorldServer) {
            WorldServer worldServer = (WorldServer) world;
            worldServer.spawnParticle(type, false, x, y, z, 1, 0, 0, 0, motion);
        }
    }
}
