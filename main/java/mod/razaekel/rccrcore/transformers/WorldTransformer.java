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
//		registerExpectedMethod("getBlockMetadata", "getBlockMetadata", getMethodDescriptor(Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
//		registerExpectedMethod("setBlockMetadataWithNotify", "setBlockMetadataWithNotify", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getFullBlockLightValue", "getFullBlockLightValue", getMethodDescriptor(Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
	}
	
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{		
		if(RCCRCore.DEBUG_SPAM){System.out.println("WORLD TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		int node_index = -1;
		
		if (methodID.equals("getBlock"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();

				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 17 -> " + node_index);}

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

			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(targetNode, toInject);

			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("blockExists"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();

				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 5 -> " + node_index);}

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

			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));

			methodNode.instructions.insert(targetNode, toInject);

			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("checkChunksExist"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}

			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;
			
			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();

				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 5 -> " + node_index);}

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


			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);
			
			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(targetNode, toInject);

			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("setBlock"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();
				
				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 26 -> " + node_index);}

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

			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(targetNode, toInject);

			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getBlockMetadata"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();
				
				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 26 -> " + node_index);}

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

			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(targetNode, toInject);

			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("setBlockMetadataWithNotify"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			AbstractInsnNode currentNode = null;
			AbstractInsnNode targetNode = null;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;

			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();
				
				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					targetNode = currentNode;
					node_index = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 26 -> " + node_index);}

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

			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(targetNode, toInject);

			methodNode.instructions.remove(remNode1);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		else if(methodID.equals("getFullBlockLightValue"))
		{
			if(RCCRCore.DEBUG){System.out.println("********* Inside target method " + methodID + "!");}
			
			AbstractInsnNode currentNode = null;
			AbstractInsnNode target1Node = null;
			AbstractInsnNode target2Node = null;
			
			int node_index2 = -1;

			@SuppressWarnings("unchecked")
			Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

			int index = -1;
			/* additional work required here
			 * 
			 * Have
			 * L4
			    LINENUMBER 670 L4
			    SIPUSH 255
			    ISTORE 2
			 * Need:
			 * L4
			    LINENUMBER 670 L4
			    INVOKESTATIC mod/razaekel/rccr/RCCR.getWorldHeight ()I
			    ICONST_1
			    ISUB
			    ISTORE 2
			 */
			while (iter.hasNext())
			{
				index++;
				currentNode = iter.next();
				
				if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 256)
				{
					target1Node = currentNode;
					node_index = index;
				}
				else if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 255)
				{
					target2Node = currentNode;
					node_index2 = index;
				}
			}

			if(RCCRCore.DEBUG){System.out.println("********* node_index should be 12 -> " + node_index);}
			if(RCCRCore.DEBUG){System.out.println("********* node_index2 should be 16 -> " + node_index2);}

			if(RCCRCore.DEBUG)
			{
				if (target1Node == null)
				{
					System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				}
	
				if (node_index == -1)
				{
					System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				}
			}

			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);
			AbstractInsnNode remNode2 = methodNode.instructions.get(node_index2);

			InsnList toInject = new InsnList();
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(target1Node, toInject);
			
			InsnList toInject2 = new InsnList();
			toInject2.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
			toInject2.add(new InsnNode(ICONST_1));
			toInject2.add(new InsnNode(ISUB));
			methodNode.instructions.insert(target2Node, toInject2);

			methodNode.instructions.remove(remNode1);
			methodNode.instructions.remove(remNode2);

			if(RCCRCore.DEBUG){System.out.println("Patching Complete!");}
			
			return true;
		}
		
		return false;
	}
}

