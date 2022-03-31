package com.Suzj.extraenchantments.Main_Sector;

import com.Suzj.extraenchantments.Enchantments_Sector.OrderedRegistry;
import com.Suzj.extraenchantments.Enchantments_Sector.reg;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;


@Mod(modid = extraenchantments.MODID, name = extraenchantments.NAME, version = extraenchantments.VERSION, acceptedMinecraftVersions = "[1.12.0, 1.12.2]")
public class extraenchantments {
	// Mod Info
	public static final String MODID = "extraenchantments";
	public static final String NAME = "Extra Enchantments";
	public static final String VERSION = "1.1";


	public static final ArrayList<Item> itemList = new ArrayList<>();
	public static int size = 0;

	@Instance("ExtraEnchantments")

	public static extraenchantments instance;



	public static DamageSource PhysicalDamage = new DamageSource("PhysicalDamage");
	

	
    @EventHandler
    public void preInit(FMLPreInitializationEvent fEvent)
    {
    	
    	reg.init();
		setup();

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent fEvent)
    {
	    OrderedRegistry.orderedRegister();
	}
	/*@SubscribeEvent
	public void dosome(PlayerSleepInBedEvent fEvent){
		EntityPlayer player = (EntityPlayer) fEvent.getEntity();


	}*/
	public static void setup() {
		for (Item item : ForgeRegistries.ITEMS) {
			itemList.add(item);
			size++;
		}
	}
}
