package com.Suzj.extraenchantments.Main_Sector;

import net.minecraftforge.common.config.Config;

public class LevelConfig
{
	//Level caps
	private static final int LMin = 1;
	private static final int LMax = 10;
	
	//Enchantment Levels


	@Config.Name("ModKiller")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int ModKiller = 5;
	
	@Config.Name("SCN")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int SCN = 3;

	@Config.Name("Frost")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int Frost = 3;

	@Config.Name("Exp Steal")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int ExpSteal = 2;
	
	@Config.Name("LifeSteal")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int LifeSteal = 4;
	
	@Config.Name("Inlaid Harp")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int InlaidHarp = 3;

	@Config.Name("Life Protection")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int LifeProtection = 5;
	
	@Config.Name("Foggy Moon")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int FoggyMoon = 1;

	@Config.Name("Star Rain")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int StarRain = 1;
	
	@Config.Name("Ezra's Blessing")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int Ezra = 1;
	
	@Config.Name("Curse of Potential")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int CursePotential = 2;

	@Config.Name("SNN")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int SNN = 3;

    @Config.Name("Sycamore")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int Sycamore = 3;

	@Config.Name("Light Armour Killer")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int LightArmourKiller = 5;

    @Config.Name("Reining")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int Reining = 2;

    @Config.Name("Broken Heart")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int BrokenHeart = 1;

    @Config.Name("Medea")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int Medea = 1;

    @Config.Name("Grieve")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int Grieve = 1;

    @Config.Name("Time Detention")
    @Config.RequiresMcRestart
    @Config.RangeInt(min=LMin, max=LMax)
    public int TimeDetention = 2;
}
