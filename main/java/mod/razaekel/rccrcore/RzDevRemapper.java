package mod.razaekel.rccrcore;

import java.util.Hashtable;

public class RzDevRemapper
{
	private static Hashtable<String, String> fakeMappings = new Hashtable<String, String>();
	
	public static void setUp()
	{
		fakeMappings.put("generateTerrain", "func_147424_a");
	}
	
	public static String getSrgName(String name)
	{
		String mapping = fakeMappings.get(name);
		
		return mapping != null ? mapping : name;
	}
}
