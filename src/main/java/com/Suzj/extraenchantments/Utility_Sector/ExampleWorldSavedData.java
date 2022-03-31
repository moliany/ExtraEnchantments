package com.Suzj.extraenchantments.Utility_Sector;

import com.Suzj.extraenchantments.Main_Sector.extraenchantments;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;

public class ExampleWorldSavedData extends WorldSavedData {
    private static final String DATA_NAME = extraenchantments.MODID + "_ExampleData";

    // 必须的构造器
    public ExampleWorldSavedData() {
        super(DATA_NAME);
    }
    public ExampleWorldSavedData(String s) {
        super(s);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        return null;
    }

    // WorldSavedData方法
}
