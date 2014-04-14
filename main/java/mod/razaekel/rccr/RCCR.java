package mod.razaekel.rccr;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = RCCR.MODID, version = RCCR.VERSION)
public class RCCR
{
    public static final String MODID = "rccr";
    public static final String VERSION = "a1";
    
    @Instance(value = "rccr")
    public static RCCR instance;
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
