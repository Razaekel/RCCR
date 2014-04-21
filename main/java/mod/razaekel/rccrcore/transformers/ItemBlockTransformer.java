package mod.razaekel.rccrcore.transformers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import scala.tools.asm.Type;
import mod.razaekel.rccrcore.RCCRCore;
import mod.razaekel.rccrcore.RzClassTransformerClass;
import mod.razaekel.rccrcore.lib.InsnTransformers;

public class ItemBlockTransformer extends RzClassTransformerClass
{
	public ItemBlockTransformer()
	{
		registerExpectedMethod("onItemUse", "onItemUse", getMethodDescriptor(
				Type.BOOLEAN_TYPE, 
				"net/minecraft/item/ItemStack", 
				"net/minecraft/entity/player/EntityPlayer", 
				"net/minecraft/world/World",
				Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE,
				Type.FLOAT_TYPE, Type.FLOAT_TYPE, Type.FLOAT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{
		if(RCCRCore.DEBUG_SPAM){System.out.println("ITEMBLOCK TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		if(methodID.equals("onItemUse"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if (!InsnTransformers.replaceSIPUSH255(methodNode, 7))
			{
				return false;
			}
			
			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
			
		}
		
		return false;
	}
}
