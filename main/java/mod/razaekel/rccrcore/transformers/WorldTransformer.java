package mod.razaekel.rccrcore.transformers;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import mod.razaekel.rccrcore.RCCRCore;
import mod.razaekel.rccrcore.RzClassTransformerClass;
import mod.razaekel.rccrcore.lib.InsnTransformers;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.Type;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class WorldTransformer extends RzClassTransformerClass
{
	public WorldTransformer()
	{
		registerExpectedMethod("getBlock", "getBlock", getMethodDescriptor("net/minecraft/block/Block", Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("blockExists", "blockExists", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("checkChunksExist", "checkChunksExist", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("setBlock", "setBlock", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, "net/minecraft/block/Block", Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getBlockMetadata", "getBlockMetadata", getMethodDescriptor(Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("setBlockMetadataWithNotify", "setBlockMetadataWithNotify", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getFullBlockLightValue", "getFullBlockLightValue", getMethodDescriptor(Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getBlockLightValue_do", "getBlockLightValue_do", getMethodDescriptor(Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.BOOLEAN_TYPE));
		registerExpectedMethod("getSkyBlockTypeBrightness", "getSkyBlockTypeBrightness", getMethodDescriptor(Type.INT_TYPE, "net/minecraft/world/EnumSkyBlock", Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getSavedLightValue", "getSavedLightValue", getMethodDescriptor(Type.INT_TYPE, "net/minecraft/world/EnumSkyBlock", Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCRCore.DEBUG_SPAM){System.out.println("WORLD TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		int node_index = -1;
		
		if (methodID.equals("getBlock"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			if(!InsnTransformers.replaceSIPUSH256(methodNode, 17))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("blockExists"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			if(!InsnTransformers.replaceSIPUSH256(methodNode, 5))
			{
				return true;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("checkChunksExist"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			if(!InsnTransformers.replaceSIPUSH256(methodNode, 5))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("setBlock"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 26))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getBlockMetadata"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 26))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("setBlockMetadataWithNotify"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 26))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getFullBlockLightValue"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 12))
			{
				return false;
			}
			
			if(!InsnTransformers.replaceSIPUSH255(methodNode, 16))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getBlockLightValue_do"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 137))
			{
				return false;
			}
			
			if(!InsnTransformers.replaceSIPUSH255(methodNode, 141))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getSkyBlockTypeBrightness"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			if(!InsnTransformers.replaceSIPUSH256(methodNode, 26))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getSavedLightValue"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if(!InsnTransformers.replaceSIPUSH256(methodNode, 12))
			{
				return false;
			}
			
			if(!InsnTransformers.replaceSIPUSH255(methodNode, 16))
			{
				return false;
			}

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}	
		
		return false;
	}
}

