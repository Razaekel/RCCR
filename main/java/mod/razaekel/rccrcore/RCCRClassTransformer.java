package mod.razaekel.rccrcore;

import java.util.ArrayList;
import java.util.Hashtable;

import mod.razaekel.rccrcore.transformers.*;
import net.minecraft.launchwrapper.IClassTransformer;

public class RCCRClassTransformer implements IClassTransformer
{
	private Hashtable<String, RzClassTransformer> transformers;
	private ArrayList<RzClassTransformer> generalTransformers;
	
	public RCCRClassTransformer()
	{
		RzDevRemapper.setUp();
		transformers = new Hashtable<String, RzClassTransformer>();
		generalTransformers = new ArrayList<RzClassTransformer>();
		
		registerTransformer("net.minecraft.world.gen.ChunkProviderGenerate", new ChunkProviderGenerateTransformer());
	}
	
	private void registerTransformer(String clazz, RzClassTransformer transformer)
	{
		transformers.put(clazz, transformer);
	}
	
	private void registerTransformer(RzClassTransformer transformer)
	{
		generalTransformers.add(transformer);
	}
	
	@Override
	public byte[] transform(String arg0, String arg1, byte[]arg2)
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
			
			for (RzClassTransformer generalTransformer : generalTransformers)
			{
				byte[] data = generalTransformer.transform(arg0, arg1, result, arg0.equals(arg1));
				
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
