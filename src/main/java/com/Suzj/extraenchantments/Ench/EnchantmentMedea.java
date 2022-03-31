package com.Suzj.extraenchantments.Ench;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.Utility_Sector.EUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentMedea extends EnchantmentBase {
    public EnchantmentMedea()
    {
        super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR, new EntityEquipmentSlot[]{EntityEquipmentSlot.HEAD, EntityEquipmentSlot.CHEST, EntityEquipmentSlot.LEGS, EntityEquipmentSlot.FEET});
        this.setName(extraenchantments.MODID + ".Medea");
        this.setRegistryName("Medea");
    }

    @Override
    public boolean isConfigEnabled()
    {
        return ModConfig.enabled.MedeaEnable;
    }

    @Override
    public int getMaxLevel()
    {
        return ModConfig.level.Medea;
    }

    @Override
    public int getMinEnchantability(int par1)
    {
        return 30 + 25 * (par1 - 1);
    }

    @Override
    public int getMaxEnchantability(int par1)
    {
        return super.getMinEnchantability(par1) + 50;
    }

    @Override
    public boolean isTreasureEnchantment()
    {
        return true;
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event){
        if (!event.getSource().damageType.equals("player") && !event.getSource().damageType.equals("mob"))
            return;
        EntityLivingBase entity = (EntityLivingBase)event.getEntity();
        int level = EnchantmentHelper.getMaxEnchantmentLevel(reg.BrokenHeart, entity);

        if(level<=0) return;

        if(event.getSource().isMagicDamage()){
            float seeDamage = event.getAmount();


            if(entity == null)
                return;
            event.setAmount(0);
            int a = entity.getTotalArmorValue();
            float result = (float) (seeDamage*(1-a*0.04));


            EUtil.damageTarget(event.getEntityLiving(), extraenchantments.PhysicalDamage, result);
        }
    }

    @Override
    public String getPrefix() {
        return TextFormatting.BLUE.toString();
    }

}
