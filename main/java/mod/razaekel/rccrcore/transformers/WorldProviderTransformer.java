package mod.razaekel.rccrcore.transformers;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import mod.razaekel.rccrcore.RCCRCore;
import mod.razaekel.rccrcore.RzClassTransformerClass;
import mod.razaekel.rccrcore.lib.InsnTransformers;

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
		if(RCCRCore.DEBUG_SPAM){System.out.println("WORLD PROVIDER TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		if(methodID.equals("getHeight"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 2))
			{
				return false;
			}
	
			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getActualHeight"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH128(methodNode, 5))
			{
				return false;
			}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 9))
			{
				return false;
			}
	
			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		
		return false;
	}
}
