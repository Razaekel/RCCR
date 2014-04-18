package mod.razaekel.rccrcore;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class RCCRLoadingPlugin implements IFMLLoadingPlugin {
{
	@Override
	public String[] getASMTransformerClass()
	{
		if(RCCRCore.DEBUG){System.out.println("RCCRCore LOADED! Loading Class Transformer!");}
		
		return new String[]{RCCRClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return RCCRCoreContainer.class.getName();
	}

	@Override
	public String getSetupClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAccessTransformerClass() {
		// TODO Auto-generated method stub
		return null;
	}

}
