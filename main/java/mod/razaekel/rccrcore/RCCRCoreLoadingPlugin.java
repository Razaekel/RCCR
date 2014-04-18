package mod.razaekel.rccrcore;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class RCCRCoreLoadingPlugin implements IFMLLoadingPlugin
{
	@Override
	public String[] getASMTransformerClass()
	{
		if(RCCRCore.DEBUG){System.out.println("RCCRCore LOADED! Loading Class Transformer!");}
		
		return new String[]{RCCRClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass()
	{
		return RCCRCoreContainer.class.getName();
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}
