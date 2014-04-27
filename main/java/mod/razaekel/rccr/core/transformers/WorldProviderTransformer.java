package mod.razaekel.rccr.core.transformers;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.core.RzClassTransformerClass;
import mod.razaekel.rccr.core.lib.InsnTransformers;

public class WorldProviderTransformer extends RzClassTransformerClass
{
	public WorldProviderTransformer()
	{
		registerExpectedMethod("getHeight", "getHeight", getMethodDescriptor(Type.INT_TYPE));
		registerExpectedMethod("getActualHeight", "getActualHeight", getMethodDescriptor(Type.INT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCR.DEBUG_SPAM){System.out.println("WORLD PROVIDER TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		switch (methodID)
		{
			default:
				return false;
				
			case "getHeight":
				if(RCCR.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
				if(!InsnTransformers.replaceSIPUSH256(methodNode, 2))
				{
					return false;
				}
				
				break;

			case "getActualHeight":
				if(RCCR.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
				if(!InsnTransformers.replaceSIPUSH128(methodNode, 5))
				{
					return false;
				}
				
				if(!InsnTransformers.replaceSIPUSH256(methodNode, 9))
				{
					return false;
				}
				
				break;
		}
	
		if(RCCR.DEBUG){System.out.println("Patching Complete!");}
		
		return true;
	}
}
