package mod.razaekel.rccr;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class RCCRContainer extends DummyModContainer
{
//	public static Logger logger = LogManager.getLogger("rccr");

	public RCCRContainer()
	{
		super(new ModMetadata());

		ModMetadata meta = super.getMetadata();
		meta.modId = "rccr";
		meta.name = "RCCR";
		meta.description = "Modifies World Height. Based on the Cubic Chunks mod by Robinton.";
		
		meta.url = "";
		meta.updateUrl = "";
		
		meta.logoFile = "";
		meta.version = RCCR.VERSION;
		meta.authorList = Arrays.asList("Razaekel");
		meta.credits = "Robinton";
		meta.parent = "";
		meta.screenshots = new String[0];
		
	    meta.useDependencyInformation = false;
//	    meta.requiredMods = Arrays.asList("");
//	    meta.dependencies = Arrays.asList("");
//	    meta.dependants;
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}

	@Subscribe
	public void modConstruction(FMLConstructionEvent evt){

	}

	@Subscribe
	public void init(FMLInitializationEvent evt) {

	}

	@Subscribe
	public void preInit(FMLPreInitializationEvent evt) {

	}

	@Subscribe
	public void postInit(FMLPostInitializationEvent evt) {

	}
}
