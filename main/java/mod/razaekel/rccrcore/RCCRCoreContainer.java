package mod.razaekel.rccrcore;

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

public class RCCRCoreContainer extends DummyModContainer
{
	public static Logger logger = LogManager.getLogger("rccr");
	
	public RCCRCoreContainer()
	{
		super(new ModMetadata());
		
		ModMetadata meta = super.getMetadata();
		meta.modId = "rccrcore";
		meta.name = "RCCR Core";
		meta.version = "@VERSION@";
		meta.credits = "Robinton, Ivorious";
		meta.authorList = Arrays.asList("Razaekel");
		meta.description = "Core Mod for RCCR. Required for RCCR.";
		meta.url = "";
		meta.updateUrl = "";
		meta.screenshots = new String[0];
		meta.logoFile = "";
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
