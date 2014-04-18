package mod.razaekel.rewedge;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.gson.annotations.SerializedName;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.versioning.ArtifactVersion;

public class ReWedgeContainer extends DummyModContainer
{
	public ReWedgeContainer()
	{
		super(new ModMetadata());

		ModMetadata meta = super.getMetadata();
		meta.modId = "rewedge";
		meta.name = "ReWedge";
		meta.description = "ReWedge";
		
		meta.url = "";
		meta.updateUrl = "";
		
		meta.logoFile = "";
		meta.version = "a1";
		meta.authorList = Arrays.asList("Razaekel");
		meta.credits = "";
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
