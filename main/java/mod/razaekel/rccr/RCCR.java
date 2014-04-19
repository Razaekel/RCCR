package mod.razaekel.rccr;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class RCCR
{
	public static final String VERSION = "a1";
	
	private static int worldHeight = 75;
	
	public static int getWorldHeight()
	{
		return worldHeight;
	}
}
