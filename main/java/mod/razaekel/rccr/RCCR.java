package mod.razaekel.rccr;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class RCCR
{
	public static final String VERSION = "a1";
	
	public static final boolean DEBUG = true;
	public static final boolean DEBUG_SPAM = false;
	
	private static int worldHeightInCubes = 16;
	private static int worldHeight = worldHeightInCubes << 4;
	
	public static int getWorldHeight()
	{
		return worldHeight;
	}
	
	public static int getNoSkyWorldHeight() // returns WorldHeight/2
	{
		return worldHeight >> 1;
	}
	
	public static int getWorldHeightInCubes() // returns WorldHeight/2
	{
		return worldHeightInCubes;
	}
}
