package mod.razaekel.rccr.core;

import java.util.ArrayList;
import java.util.Hashtable;

import cpw.mods.fml.common.Loader;
import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.core.transformers.*;
import net.minecraft.launchwrapper.IClassTransformer;

public class RCCRClassTransformer implements IClassTransformer
{
	private Hashtable<String, RzClassTransformer> transformers;
	
	public RCCRClassTransformer()
	{
		if(RCCR.DEBUG){System.out.println("RCCRCore Class Transformer LOADED!");}
		
		RzDevRemapper.setUp();
		transformers = new Hashtable<String, RzClassTransformer>();
		
		
		registerTransformer("net.minecraft.world.World", new WorldTransformer());
		registerTransformer("net.minecraft.world.WorldProvider", new WorldProviderTransformer());
		registerTransformer("net.minecraft.world.chunk.Chunk", new ChunkTransformer());
		registerTransformer("net.minecraft.client.render.RenderGlobal", new RenderGlobalTransformer());
		
//		if (Loader.isModLoaded("rewedge"))
//		{
			registerTransformer("net.minecraft.world.gen.ChunkProviderGenerate", new ChunkProviderGenerateTransformer());
//		}
	}
	
	private void registerTransformer(String clazz, RzClassTransformer transformer)
	{
		transformers.put(clazz, transformer);
	}
	
	@Override
	public byte[] transform(String arg0, String arg1, byte[] arg2)
	{
		if (arg2 != null)
		{
			byte[] result = arg2;
			
			RzClassTransformer transformer = transformers.get(arg1);
			if (transformer != null)
			{
				byte[] data = transformer.transform(arg0, arg1, result, arg0.equals(arg1));
				
				if (data != null)
				{
					result = data;
				}
			}
			
			return result;
		}
		
		return arg2;
	}
}
