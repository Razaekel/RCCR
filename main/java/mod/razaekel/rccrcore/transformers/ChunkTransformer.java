package mod.razaekel.rccrcore.transformers;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import mod.razaekel.rccrcore.RCCRCore;
import mod.razaekel.rccrcore.RzClassTransformerClass;
import mod.razaekel.rccrcore.lib.InsnTransformers;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class ChunkTransformer extends RzClassTransformerClass
{
	public ChunkTransformer()
	{
		registerExpectedMethod("<init>", "<init>", getMethodDescriptor(Type.VOID_TYPE, "net/minecraft/world/World", Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("relightBlock", "relightBlock", getMethodDescriptor(Type.VOID_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getAreLevelsEmpty", "getAreLevelsEmpty", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCRCore.DEBUG_SPAM){System.out.println("CHUNK TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		if (methodID == null)
		{
			return false;
		}
		
		switch (methodID)
		{
			case "<init>":
				if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
				if (!InsnTransformers.replaceWithWorldHeightInCubes(methodNode, 7))
				{
					return false;
				}
				
				if (!InsnTransformers.replaceWithWorldHeightInCubes(methodNode, 43))
				{
					return false;
				}
				
				break;
			
			case "relightBlock":
				if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
				if (!InsnTransformers.replaceSIPUSH255(methodNode, 10))
				{
					return false;
				}
				
				break;
			
			case "getAreLevelsEmpty":
				if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
				if (!InsnTransformers.replaceSIPUSH256(methodNode, 12))
				{
					return false;
				}
				
				if (!InsnTransformers.replaceSIPUSH255(methodNode, 16))
				{
					return false;
				}
				
				break;
		}
		
		if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
		
		return true;
	}
}
