package mod.razaekel.rccr.core.lib;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.core.RzClassTransformer;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

public class InsnTransformers
{
	public static boolean replaceSIPUSH256(MethodNode methodNode, int targetIndex)
	{
		AbstractInsnNode currentNode = null;
		AbstractInsnNode targetNode = null;

		@SuppressWarnings("unchecked")
		Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

		int index = -1;
		int node_index = -1;

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

		if(RCCR.DEBUG){System.out.println("********* node_index should be " + targetIndex + " -> " + node_index);}

		if(RCCR.DEBUG)
		{
			if (targetNode == null || node_index == -1)
			{
				System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				return false;
			}
		}

		AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

		InsnList toInject = new InsnList();
		toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
		methodNode.instructions.insert(targetNode, toInject);

		methodNode.instructions.remove(remNode1);
		
		return true;
	}
	
	public static boolean replaceSIPUSH255(MethodNode methodNode, int targetIndex)
	{
		AbstractInsnNode currentNode = null;
		AbstractInsnNode targetNode = null;

		@SuppressWarnings("unchecked")
		Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

		int index = -1;
		int node_index = -1;

		while (iter.hasNext())
		{
			index++;
			currentNode = iter.next();

			if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 255)
			{
				targetNode = currentNode;
				node_index = index;
			}
		}

		if(RCCR.DEBUG){System.out.println("********* node_index should be " + targetIndex + " -> " + node_index);}

		if(RCCR.DEBUG)
		{
			if (targetNode == null || node_index == -1)
			{
				System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				return false;
			}
		}

		AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

		InsnList toInject = new InsnList();
		toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
		toInject.add(new InsnNode(ICONST_1));
		toInject.add(new InsnNode(ISUB));
		methodNode.instructions.insert(targetNode, toInject);

		methodNode.instructions.remove(remNode1);
		
		return true;
	}
	
	public static boolean replaceSIPUSH128(MethodNode methodNode, int targetIndex)
	{
		AbstractInsnNode currentNode = null;
		AbstractInsnNode targetNode = null;

		@SuppressWarnings("unchecked")
		Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

		int index = -1;
		int node_index = -1;

		while (iter.hasNext())
		{
			index++;
			currentNode = iter.next();

			if (currentNode.getOpcode() == SIPUSH && ((IntInsnNode) currentNode).operand == 128)
			{
				targetNode = currentNode;
				node_index = index;
			}
		}

		if(RCCR.DEBUG){System.out.println("********* node_index should be " + targetIndex + " -> " + node_index);}

		if(RCCR.DEBUG)
		{
			if (targetNode == null || node_index == -1)
			{
				System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				return false;
			}
		}

		AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

		InsnList toInject = new InsnList();
		toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getNoSkyWorldHeight", Type.getMethodDescriptor(Type.INT_TYPE)));
		methodNode.instructions.insert(targetNode, toInject);

		methodNode.instructions.remove(remNode1);
		
		return true;
	}
	
	public static boolean replaceWithWorldHeightInCubes(MethodNode methodNode, int targetIndex)
	{
		AbstractInsnNode currentNode = null;
		AbstractInsnNode targetNode = null;

		@SuppressWarnings("unchecked")
		Iterator<AbstractInsnNode> iter = methodNode.instructions.iterator();

		int index = -1;
		int node_index = -1;

		while (iter.hasNext())
		{
			index++;
			currentNode = iter.next();

			if (currentNode.getOpcode() == BIPUSH && ((IntInsnNode) currentNode).operand == 16)
			{
				targetNode = currentNode;
				node_index = index;
				break;
			}
		}

		if(RCCR.DEBUG){System.out.println("********* node_index should be " + targetIndex + " -> " + node_index);}

		if(RCCR.DEBUG)
		{
			if (targetNode == null || node_index == -1)
			{
				System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
				return false;
			}
		}

		AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);

		InsnList toInject = new InsnList();
//		toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeightInCubes", RzClassTransformer.getMethodDescriptor(Type.VOID_TYPE)));
		toInject.add(new FieldInsnNode(GETFIELD, "mod/razaekel/rccr/RCCR", "worldHeightInCubes", "I"));
		methodNode.instructions.insert(targetNode, toInject);

		methodNode.instructions.remove(remNode1);
		
		return true;
	}
}
