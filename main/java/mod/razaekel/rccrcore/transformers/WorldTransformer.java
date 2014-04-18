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

public class WorldTransformer extends RzClassTransformerClass
{
	public WorldTransformer()
	{
		registerExpectedMethod("getBlock", "func_147439_b", getMethodDescriptor("net/minecraft/block/Block", Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCRCore.DEBUG){System.out.println("WORLD TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		int node_index = -1;
		
		if(methodID.equals("generateTerrain"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method" + methodID + "!");}
			
			//System.out.println("m.instructions.size = " + m.instructions.size());

			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			//Loop over the instruction set and find the instruction
			
			/*
            The line in World.java that we want to modify is:

            if (p_147439_1_ >= -30000000 && p_147439_3_ >= -30000000 && p_147439_1_ < 30000000 && p_147439_3_ < 30000000 && p_147439_2_ >= 0 && p_147439_2_ < 256)

            The code we are looking for is the following in bytecode:

                	 	L3
					    LINENUMBER 273 L3
					    ILOAD 1
					    LDC -30000000
					    IF_ICMPLT L4
					    ILOAD 3
					    LDC -30000000
					    IF_ICMPLT L4
					    ILOAD 1
					    LDC 30000000
					    IF_ICMPGE L4
					    ILOAD 3
					    LDC 30000000
					    IF_ICMPGE L4
					    ILOAD 2
					    IFLT L4
					    ILOAD 2
					    SIPUSH 256
					    IF_ICMPGE L4
			*/
			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();
				//	                    System.out.println("********* index : " + index + " currentNode.getOpcode() = " + currentNode.getOpcode());

				//Found it! save the index location of instruction BIPUSH and the node for this instruction
				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
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

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 9 -> " + node_index);}

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

			// save the nodes that load the variable
			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);   // mv.visitIntInsn(BIPUSH, 63);

			/*
			 * want to add bytecode to get the following:
				L3
			    LINENUMBER 276 L3
			    ILOAD 1
			    LDC -30000000
			    IF_ICMPLT L4
			    ILOAD 3
			    LDC -30000000
			    IF_ICMPLT L4
			    ILOAD 1
			    LDC 30000000
			    IF_ICMPGE L4
			    ILOAD 3
			    LDC 30000000
			    IF_ICMPGE L4
			    ILOAD 2
			    IFLT L4
			    ILOAD 2
			    INVOKESTATIC mod/razaekel/rccr/RCCR.getWorldHeight ()I
			    IF_ICMPGE L4
			 */

			// make new instruction list
			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
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

