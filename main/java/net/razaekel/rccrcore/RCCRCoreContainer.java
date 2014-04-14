package net.razaekel.rccrcore;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.eventhandler.EventBus;


public class RCCRCoreContainer extends DummyModContainer
{
	public static Logger logger = LogManager.getLogger("rccr");
	
	public RCCRCoreContainer()
	{
		super(new ModMetadata());
		
		ModMetadata meta = super.getMetadata();
		meta.modId = "rccr";
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
	
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
}
