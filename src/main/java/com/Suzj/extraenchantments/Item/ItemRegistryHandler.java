package com.Suzj.extraenchantments.Item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;


import javax.annotation.Nonnull;
import java.util.Objects;

@Nonnull
@Mod.EventBusSubscriber
public class ItemRegistryHandler {
    public static final ItemWuTongGuo WU_TONG_GUO = new ItemWuTongGuo();
    public static final ItemTimeWatch TIME_WATCH = new ItemTimeWatch();

    @Nonnull
    @SubscribeEvent
    public static void onRegistry( RegistryEvent.Register<Item> event){
        IForgeRegistry<Item> registry = event.getRegistry();
        registry.register(WU_TONG_GUO);
        registry.register(TIME_WATCH);

    }
    @Nonnull
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onModelRegistry( ModelRegistryEvent event){
        ModelLoader.setCustomModelResourceLocation(WU_TONG_GUO, 0,
                new ModelResourceLocation(Objects.requireNonNull(WU_TONG_GUO.getRegistryName()),"inventory"));
        ModelLoader.setCustomModelResourceLocation(TIME_WATCH,0,
                new ModelResourceLocation(Objects.requireNonNull(TIME_WATCH.getRegistryName()),"inventory"));


    }
    private static ItemBlock withRegistryName(ItemBlock item)
    {
        item.setRegistryName(item.getBlock().getRegistryName());
        return item;

    }
    @SideOnly(Side.CLIENT)
    private static void registerModel(Item item)
    {
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
    }

}
