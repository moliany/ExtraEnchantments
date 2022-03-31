package com.Suzj.extraenchantments.Main_Sector;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config(modid= extraenchantments.MODID)
public class ModConfig
{
	@Config.Comment("Enabled Enchantments")
	@Config.Name("Enabled Enchantments")
	public static EnabledConfig enabled = new EnabledConfig();
	
	@Config.Comment("Maximum levels of each enchantment")
	@Config.Name("Enchantment Levels")
	public static LevelConfig level = new LevelConfig();
	
	@Config.Comment("Miscellaneous")
	@Config.Name("Miscellaneous")
	public static MiscellaneousConfig miscellaneous = new MiscellaneousConfig();
	
	public static class MiscellaneousConfig
	{
		@Config.Comment("Unregister disabled enchantments")
		@Config.Name("Unregister Disabled Enchants")
		@Config.RequiresMcRestart
		public boolean unregisterDisabled = true;

		@Config.Comment("Whether enchantments should work with pet attacks")
		@Config.Name("Enable Pet Attacks")
		public boolean enablePetAttacks = false;
	}
	
	@Mod.EventBusSubscriber(modid = extraenchantments.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID().equals(extraenchantments.MODID))
			{
				ConfigManager.sync(extraenchantments.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
