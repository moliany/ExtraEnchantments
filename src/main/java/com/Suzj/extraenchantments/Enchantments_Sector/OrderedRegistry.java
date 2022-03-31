package com.Suzj.extraenchantments.Enchantments_Sector;

import java.util.LinkedHashSet;
import java.util.Set;

import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;
import com.Suzj.extraenchantments.Item.ItemRegistryHandler;
import com.Suzj.extraenchantments.Item.ItemTimeWatch;
import com.Suzj.extraenchantments.Main_Sector.ModConfig;
import com.Suzj.extraenchantments.Main_Sector.extraenchantments;

import com.Suzj.extraenchantments.Utility_Sector.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class OrderedRegistry
{
	public static Set<EnchantmentBase> orderedEnchants = new LinkedHashSet<>();
	
	public static void orderedRegister()
	{
		subscribe(reg.ModKiller);
		subscribe(reg.SCN);
		subscribe(reg.Ezra);

		subscribe(reg.CursePotential);


		subscribe(reg.FoggyMoon);
		subscribe(reg.LifeSteal);

		subscribe(reg.SNN);
		subscribe(reg.LightArmourKiller);
		subscribe(reg.ExpSteal);
		subscribe(reg.LifeProtection);



		subscribe(reg.Frost);

		subscribe(reg.InlaidHarp);
		subscribe(reg.Sycamore);
		subscribe(reg.Reining);
		subscribe(reg.BrokenHeart);
		subscribe(reg.Medea);

		subscribe(reg.Grieve);
		subscribe(reg.Risks);

		subscribe(reg.TimeDetention);
		
		//ORDERED REGISTRY	



		subscribe(reg.StarRain);
		//subscribe(ItemRegistryHandler.TIME_WATCH);

		subscribe(reg.Ezra);
		MinecraftForge.EVENT_BUS.register(new SNNHandler());
		MinecraftForge.EVENT_BUS.register(new LightArmourKillerHandler());
		MinecraftForge.EVENT_BUS.register(new SCNHandler());
		MinecraftForge.EVENT_BUS.register(new StarRainHandler());
		MinecraftForge.EVENT_BUS.register(new EzraHandler());
		MinecraftForge.EVENT_BUS.register(new FoggyMoonHandler());
		MinecraftForge.EVENT_BUS.register(new LifeStealHandler());
		MinecraftForge.EVENT_BUS.register(new ExpStealHandler());
		MinecraftForge.EVENT_BUS.register(new LifeProtectionHandler());
		MinecraftForge.EVENT_BUS.register(new InlaidHarpHandler());
		MinecraftForge.EVENT_BUS.register(ItemRegistryHandler.TIME_WATCH);
		//MinecraftForge.EVENT_BUS.register(new SycamoreHandler());
		
		//HURT PATCH

	    
	    //OTHER HANDLER

	}
	
	public static EnchantmentBase registerEnchant(EnchantmentBase enchant)
	{
		if(enchant==null)
			throw new RuntimeException("Passed a null enchant during registerEnchant!");
		
		
		if(!ModConfig.miscellaneous.unregisterDisabled || enchant.isConfigEnabled())
		{
			orderedEnchants.add(enchant);
		}
		
		return enchant;
	}
	
	@Mod.EventBusSubscriber(modid = extraenchantments.MODID)
    public static class EventSubscriber
    {
        @SubscribeEvent
        public static void registerEnchantment(RegistryEvent.Register<net.minecraft.enchantment.Enchantment> event)
        {
        	IForgeRegistry<Enchantment> registry = event.getRegistry();
        	for(EnchantmentBase enchant : orderedEnchants)
        	{
        		if(!enchant.isRegistered())
        		{
	        		registry.register(enchant);
	    			enchant.setRegistered();
        		}
        	}
        }
    }
	
	public static void subscribe(EnchantmentBase o)
	{
		//TODO take steps towards this being fully configurable
		//This should in the future just check for isRegistered, and all related enchantments check for the isEnabled themselves

		if(o==null)return;
		if(o.isEnabled())
			MinecraftForge.EVENT_BUS.register(o);
		
	}
	
	public static EnchantmentBase registerAs(Enchantment enchant)
	{
		if(enchant instanceof EnchantmentBase)
		{
			return OrderedRegistry.registerEnchant((EnchantmentBase) enchant);
		}
		else
		{
			throw new RuntimeException("Tried to registerAs an enchantment that was not an EnchantmentBase! "+enchant);
		}
	}
}
