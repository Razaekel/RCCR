package mod.razaekel.rccr.core.transformers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import scala.tools.asm.Type;
import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.core.RzClassTransformerClass;
import mod.razaekel.rccr.core.lib.InsnTransformers;

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
		if(RCCR.DEBUG_SPAM){System.out.println("ITEMBLOCK TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		if(methodID.equals("onItemUse"))
		{
			if(RCCR.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			if (!InsnTransformers.replaceSIPUSH255(methodNode, 7))
			{
				return false;
			}
			
			if(RCCR.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
			
		}
		
		return false;
	}
}
