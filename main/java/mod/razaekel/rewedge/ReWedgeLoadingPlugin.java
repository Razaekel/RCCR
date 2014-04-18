package mod.razaekel.rewedge;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class ReWedgeLoadingPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getASMTransformerClass()
	{
		return null;
	}

	@Override
	public String getModContainerClass()
	{
		return ReWedgeContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
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
