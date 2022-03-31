package com.Suzj.extraenchantments.creativetab;

import com.Suzj.extraenchantments.Item.ItemRegistryHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;


public class TabFMLTutor extends CreativeTabs
{
    public static final TabFMLTutor TAB_FMLTUTOR = new TabFMLTutor();

    public TabFMLTutor()
    {
        super("extraenchantments");
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return new ItemStack(ItemRegistryHandler.WU_TONG_GUO);
    }

    @Override
    public boolean hasSearchBar()
    {
        return true;
    }

    @Override
    public int getSearchbarWidth()
    {
        return 45;
    }

    //@Override
    //public String getBackgroundImageName()
    //{
    //    return "fmltutor.png";
    //}
}
