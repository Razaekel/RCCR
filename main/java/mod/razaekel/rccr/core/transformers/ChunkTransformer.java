package mod.razaekel.rccr.core.transformers;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.core.RzClassTransformerClass;
import mod.razaekel.rccr.core.lib.InsnTransformers;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class ChunkTransformer extends RzClassTransformerClass
{
	public ChunkTransformer()
	{
//		registerExpectedMethod("<init>", "<init>", getMethodDescriptor(Type.VOID_TYPE, "net/minecraft/world/World", Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("relightBlock", "relightBlock", getMethodDescriptor(Type.VOID_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getAreLevelsEmpty", "getAreLevelsEmpty", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCR.DEBUG_SPAM){System.out.println("CHUNK TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		if (methodID == null)
		{
			return false;
		}
		
		switch (methodID)
		{
			case "<init>":
				if(RCCR.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
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
				if(RCCR.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
				if (!InsnTransformers.replaceSIPUSH255(methodNode, 10))
				{
					return false;
				}
				
				break;
			
			case "getAreLevelsEmpty":
				if(RCCR.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
				
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
		
		if(RCCR.DEBUG){System.out.println("Patching Complete!");}
		
		return true;
	}
}
