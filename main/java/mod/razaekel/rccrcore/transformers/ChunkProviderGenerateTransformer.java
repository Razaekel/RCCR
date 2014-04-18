package mod.razaekel.rccrcore.transformers;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import mod.razaekel.rccrcore.RCCRCore;
import mod.razaekel.rccrcore.RzClassTransformerClass;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.Type;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ChunkProviderGenerateTransformer extends RzClassTransformerClass
{
	public ChunkProviderGenerateTransformer()
	{
		registerExpectedMethod("generateTerrain", "func_147424_a", getMethodDescriptor(Type.VOID_TYPE, Type.INT_TYPE, Type.INT_TYPE, "[Lnet/minecraft/block/Block;"));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCRCore.DEBUG){System.out.println("CPG TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		int node_index = -1;
		
		if(methodID.equals("generateTerrain"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method!");}
			
			//System.out.println("m.instructions.size = " + m.instructions.size());

			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			//Loop over the instruction set and find the instruction
			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();
				//	                    System.out.println("********* index : " + index + " currentNode.getOpcode() = " + currentNode.getOpcode());

				//Found it! save the index location of instruction BIPUSH and the node for this instruction
				if (currentNode.getOpcode() == BIPUSH && ((IntInsnNode) currentNode).operand == 63)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			/*
            	2013-07-05 18:32:29 [INFO] [STDOUT] ********* index : 334 currentNode.getOpcode() = 25
				2013-07-05 18:32:29 [INFO] [STDOUT] ********* index : 335 currentNode.getOpcode() = 180
				2013-07-05 18:32:29 [INFO] [STDOUT] ********* index : 336 currentNode.getOpcode() = 110
			 */

			if(RCCRCore.DEBUG){System.out.println("********* bipush_index should be 2 -> " + node_index);}

			if(RCCRCore.DEBUG)
			{
				if (targetNode == null)
				{
					System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				}
	
				if (node_index == -1)
				{
					System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				}
			}

			/*
                 //now we want the save nods that load the variable

                 The line in ChunkProviderGenerate.java that we want to modify is:

                 byte b0 = 63;

                 The code we are looking for is the following in bytecode:

                 Label l0 = new Label();
					mv.visitLabel(l0);
					mv.visitLineNumber(118, l0);
					mv.visitIntInsn(BIPUSH, 63);
					mv.visitVarInsn(ISTORE, 4);
			 */
			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);   // mv.visitIntInsn(BIPUSH, 63);

			/*
			 * want to add bytecode to get the following:
			 * GETSTATIC mod/razaekel/rccr/RCCR.SeaLevel : I
			 * 
			 * should be
			 * 
			 * MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getSeaLevel", ()I);
			 */

			// make new instruction list
			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rewedge/ReWedge", "getSeaLevel", Type.getMethodDescriptor(Type.INT_TYPE)));
			// inject new instruction list into method instruction list
			methodNode.instructions.insert(targetNode, toInject);

			//just remove these nodes from the instruction set, this will prevent the instruction
			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		
		return false;
	}
}

