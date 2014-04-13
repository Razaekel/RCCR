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
		
		ModMetadata myMeta = super.getMetadata();
		myMeta.authorList = Arrays.asList("Razaekel");
		myMeta.description = "Core Mod for RCCR. Required for RCCR.";
		myMeta.modId = "rccr";
		myMeta.version = "a1";
		myMeta.name = "RCCR Core";
		myMeta.url = "";
	}
	
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
}
