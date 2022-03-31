package com.Suzj.extraenchantments.Enchantments_Sector;


import com.Suzj.extraenchantments.Ench.*;
import com.Suzj.extraenchantments.Enchantment_Base_Sector.EnchantmentBase;

import net.minecraft.enchantment.Enchantment;

public class reg {
	
	public static EnchantmentBase SCN; //
	public static EnchantmentBase ModKiller; //
	public static EnchantmentBase Ezra; //
	public static EnchantmentBase StarRain; //
	public static EnchantmentBase CursePotential; //

	public static EnchantmentBase Frost;
	public static EnchantmentBase InlaidHarp;
	public static EnchantmentBase Sycamore;

	public static EnchantmentBase SNN;
	public static EnchantmentBase LightArmourKiller;
	public static EnchantmentBase ExpSteal;
	public static EnchantmentBase LifeProtection;

	public static EnchantmentBase FoggyMoon;
	public static EnchantmentBase LifeSteal;
	public static EnchantmentBase Reining;
	public static EnchantmentBase BrokenHeart;
	public static EnchantmentBase Medea;

	public static EnchantmentBase Grieve;
	public static EnchantmentBase Risks;
	public static EnchantmentBase TimeDetention;
	
	public static void init()
	{
		CursePotential = registerAs(new EnchantmentCursePotential());
		ModKiller = registerAs(new EnchantmentModKiller());
        SCN = registerAs(new EnchantmentSCN());
		StarRain = registerAs(new EnchantmentStarRain());
    	Ezra = registerAs(new EnchantmentEzra());
		FoggyMoon = registerAs(new EnchantmentFoggyMoon());
		LifeSteal = registerAs(new EnchantmentLifeSteal());
		SNN = registerAs(new EnchantmentSNN());
		LightArmourKiller = registerAs(new EnchantmentLightArmourKiller());
		ExpSteal = registerAs(new EnchantmentExpSteal());
		LifeProtection = registerAs(new EnchantmentLifeProtection());
		Frost                    = registerAs(new EnchantmentFrost());
		InlaidHarp					= registerAs(new EnchantmentInlaidHarp());
		Sycamore 			= registerAs(new EnchantmentSycamore());
		Reining = registerAs(new EnchantmentReining());
		BrokenHeart = registerAs(new EnchantmentBrokenHeart());
		Medea = registerAs(new EnchantmentMedea());
		Grieve = registerAs(new EnchantmentGrieve());
		Risks = registerAs(new EnchantmentTakingRisks());
		TimeDetention = registerAs(new EnchantmentTimeDetention());
	}
	
	private static EnchantmentBase registerAs(Enchantment enchant)
	{
		return OrderedRegistry.registerAs(enchant);
	}

}
