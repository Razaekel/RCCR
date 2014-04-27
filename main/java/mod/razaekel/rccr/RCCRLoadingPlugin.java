package mod.razaekel.rccr;

import java.io.File;
import java.util.Map;

import mod.razaekel.rccr.core.RCCRClassTransformer;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class RCCRLoadingPlugin implements IFMLLoadingPlugin
{
	public static File location;
	
	@Override
	public String[] getASMTransformerClass()
	{
		if(RCCR.DEBUG){System.out.println("RCCRCore LOADED! Loading Class Transformer!");}
		
		return new String[]{RCCRClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass()
	{
		return RCCRContainer.class.getName();
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
