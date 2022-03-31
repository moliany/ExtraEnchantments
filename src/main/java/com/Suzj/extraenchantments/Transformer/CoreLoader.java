package com.Suzj.extraenchantments.Transformer;

import java.util.Map;

import com.Suzj.extraenchantments.Transformer.helper.ObfHelper;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("ExtraEnchantments ASM")
@IFMLLoadingPlugin.SortingIndex(1002)
@IFMLLoadingPlugin.TransformerExclusions({ "com.Suzj.extraenchantments.Transformer", "com.Suzj.extraenchantments.Transformer." })

public class CoreLoader implements IFMLLoadingPlugin
{
	//
	// IFMLLoadingPlugin
	// 

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { "SMEASM" };
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		ObfHelper.setObfuscated((Boolean) data.get("runtimeDeobfuscationEnabled"));
		ObfHelper.setRunsAfterDeobfRemapper(true);
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}

