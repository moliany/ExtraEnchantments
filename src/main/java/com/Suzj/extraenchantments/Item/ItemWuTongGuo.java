package com.Suzj.extraenchantments.Item;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import com.Suzj.extraenchantments.creativetab.TabFMLTutor;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemWuTongGuo extends ItemFood {
    public ItemWuTongGuo() {
        super(10,false);
        this.setAlwaysEdible();
        this.setUnlocalizedName(extraenchantments.MODID+".wutongguo");
        this.setRegistryName("wutongguo");
        this.setMaxStackSize(64);
        this.setCreativeTab(TabFMLTutor.TAB_FMLTUTOR);
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(1),20*60,1));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(16),20*60,1));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(13),20*60,1));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(3),20*60,2));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(5),20*60,2));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(10),20*30,2));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(11),20*300,2));
        player.addPotionEffect(new PotionEffect(Potion.getPotionById(12),20*300,1));
        super.onFoodEaten(stack, worldIn, player);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        String lore = I18n.format("wutongguo.desc.1");
        String lore2 = I18n.format("wutongguo.desc.2");
        String lore3 = I18n.format("wutongguo.desc.3");
        String lore4 = I18n.format("wutongguo.desc.4");
        tooltip.add(lore);
        tooltip.add(lore2);
        tooltip.add(lore3);
        tooltip.add(lore4);
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
