package com.Suzj.extraenchantments.Utility_Sector;

import net.minecraft.item.ItemStack;

public class TempValue {
    private static ItemStack itemStack = null;
    public static ItemStack getItemStack(){
        return itemStack;
    }
    public static void setItemStack(ItemStack itemStackIn){
        itemStack = itemStackIn;
    }
}
