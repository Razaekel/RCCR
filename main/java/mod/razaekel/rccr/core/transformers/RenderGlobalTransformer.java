package mod.razaekel.rccr.core.transformers;

import static org.objectweb.asm.Opcodes.*;

import java.util.Iterator;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.core.RzClassTransformerClass;

public class RenderGlobalTransformer extends RzClassTransformerClass
{
	public RenderGlobalTransformer()
	{
		registerExpectedMethod("loadRenderers", "loadRenderers", getMethodDescriptor(Type.VOID_TYPE));
		registerExpectedMethod("markRenderersForNewPosition", "markRenderersForNewPosition", getMethodDescriptor(Type.VOID_TYPE, Type.INT_TYPE, Type.INT_TYPE, Type.INT_TYPE));
		registerExpectedMethod("getAreLevelsEmpty", "getAreLevelsEmpty", getMethodDescriptor(Type.BOOLEAN_TYPE, Type.INT_TYPE, Type.INT_TYPE));
	}
	@Override
	public boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf)
	{
		if(RCCR.DEBUG_SPAM){System.out.println("CHUNK TRANSFORMER LOADED! BEGINNING TRANSFORM!");}
		
		if(methodID.equals("loadRenderers"))
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
	
			if(RCCR.DEBUG){System.out.println("********* node_index should be 40 -> " + node_index);}
	
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
			toInject.add(new MethodInsnNode(INVOKESTATIC, "mod/razaekel/rccr/RCCR", "getWorldHeightInCubes", Type.getMethodDescriptor(Type.INT_TYPE)));
			methodNode.instructions.insert(targetNode, toInject);
	
			methodNode.instructions.remove(remNode1);
			
			return true;
		}
		else if(methodID.equals("markRenderersForNewPosition"))
		{
			
			/* this is the big one
			 * 
			 * L40
			    LINENUMBER 591 L40
			   FRAME APPEND [int]
			    ILOAD 12: l2
			    BIPUSH 16
			    IMUL
			    ISTORE 13
			   L41
			    LINENUMBER 593 L41
			    ILOAD 13: i3
			    ALOAD 0: this
			    GETFIELD RenderGlobal.minBlockY : int
			    IF_ICMPGE L42
			   
			   becomes
			   
			   L40
			    LINENUMBER 591 L40
			   FRAME APPEND [int]
			    ILOAD 12: l2
			    BIPUSH 16
			    IMUL
			    ISTORE 13
			   L41
			    LINENUMBER 592 L41
			    ILOAD 13: i3
			    ILOAD 5: i1
			    IADD
			    ILOAD 3: par3
			    ISUB
			    ISTORE 14
			   L42
			    LINENUMBER 594 L42
			    ILOAD 14: j3
			    IFGE L43
			   L44
			    LINENUMBER 596 L44
			    ILOAD 14: j3
			    ILOAD 4: l
			    ICONST_1
			    ISUB
			    ISUB
			    ISTORE 14: j3
			   L43
			    LINENUMBER 599 L43
			   FRAME APPEND [int int]
			    ILOAD 14: j3
			    ILOAD 4: l
			    IDIV
			    ISTORE 14: j3
			   L45
			    LINENUMBER 600 L45
			    ILOAD 13: i3
			    ILOAD 14: j3
			    ILOAD 4: l
			    IAND
			    ISUB
			    ISTORE 13: i3
			   L46
			    LINENUMBER 602 L46
			    ILOAD 13: i3
			    ALOAD 0: this
			    GETFIELD RenderGlobal.minBlockY : int
			    IF_ICMPGE L47
			   
			   also as
			   
			    mv.visitLineNumber(591, l40);
				mv.visitFrame(Opcodes.F_APPEND,1, new Object[] {Opcodes.INTEGER}, 0, null);
				mv.visitVarInsn(ILOAD, 12);
				mv.visitIntInsn(BIPUSH, 16);
				mv.visitInsn(IMUL);
				mv.visitVarInsn(ISTORE, 13);
				Label l41 = new Label();
				mv.visitLabel(l41);
				mv.visitLineNumber(592, l41);
				mv.visitVarInsn(ILOAD, 13);
				mv.visitVarInsn(ILOAD, 5);
				mv.visitInsn(IADD);
				mv.visitVarInsn(ILOAD, 3);
				mv.visitInsn(ISUB);
				mv.visitVarInsn(ISTORE, 14);
				Label l42 = new Label();
				mv.visitLabel(l42);
				mv.visitLineNumber(594, l42);
				mv.visitVarInsn(ILOAD, 14);
				Label l43 = new Label();
				mv.visitJumpInsn(IFGE, l43);
				Label l44 = new Label();
				mv.visitLabel(l44);
				mv.visitLineNumber(596, l44);
				mv.visitVarInsn(ILOAD, 14);
				mv.visitVarInsn(ILOAD, 4);
				mv.visitInsn(ICONST_1);
				mv.visitInsn(ISUB);
				mv.visitInsn(ISUB);
				mv.visitVarInsn(ISTORE, 14);
				mv.visitLabel(l43);
				mv.visitLineNumber(599, l43);
				mv.visitFrame(Opcodes.F_APPEND,2, new Object[] {Opcodes.INTEGER, Opcodes.INTEGER}, 0, null);
				mv.visitVarInsn(ILOAD, 14);
				mv.visitVarInsn(ILOAD, 4);
				mv.visitInsn(IDIV);
				mv.visitVarInsn(ISTORE, 14);
				Label l45 = new Label();
				mv.visitLabel(l45);
				mv.visitLineNumber(600, l45);
				mv.visitVarInsn(ILOAD, 13);
				mv.visitVarInsn(ILOAD, 14);
				mv.visitVarInsn(ILOAD, 4);
				mv.visitInsn(IAND);
				mv.visitInsn(ISUB);
				mv.visitVarInsn(ISTORE, 13);
				Label l46 = new Label();
				mv.visitLabel(l46);
				mv.visitLineNumber(602, l46);
				mv.visitVarInsn(ILOAD, 13);
				mv.visitVarInsn(ALOAD, 0);
				mv.visitFieldInsn(GETFIELD, "net/minecraft/client/renderer/RenderGlobal", "minBlockY", "I");
			 */
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
	
				if (currentNode.getOpcode() == ISTORE && ((IntInsnNode) currentNode).operand == 13
						&& currentNode.getPrevious().getOpcode() == IMUL)
				{
					targetNode = currentNode;
					node_index = index;
					break;
				}
			}
	
			if(RCCR.DEBUG){System.out.println("********* node_index should be 40 -> " + node_index);}
	
			if(RCCR.DEBUG)
			{
				if (targetNode == null || node_index == -1)
				{
					System.out.println("Did not find all necessary target nodes! ABANDON CLASS!");
					return false;
				}
			}
	
			AbstractInsnNode remNode1 = methodNode.instructions.get(node_index);
	/*
	 * 			ILOAD 13: i3
			    ILOAD 5: i1
			    IADD
			    ILOAD 3: par3
			    ISUB
			    ISTORE 14
			   L42
			    LINENUMBER 594 L42
			    ILOAD 14: j3
			    IFGE L43
			   L44
			    LINENUMBER 596 L44
			    ILOAD 14: j3
			    ILOAD 4: l
			    ICONST_1
			    ISUB
			    ISUB
			    ISTORE 14: j3
			   L43
			    LINENUMBER 599 L43
			   FRAME APPEND [int int]
			    ILOAD 14: j3
			    ILOAD 4: l
			    IDIV
			    ISTORE 14: j3
			   L45
			    LINENUMBER 600 L45
			    ILOAD 13: i3
			    ILOAD 14: j3
			    ILOAD 4: l
			    IAND
			    ISUB
			    ISTORE 13: i3
	 */
			
			LabelNode l43 = new LabelNode();
			
			InsnList toInject = new InsnList();
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 13));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 5));
			toInject.add(new InsnNode(Opcodes.IADD));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 3));
			toInject.add(new InsnNode(Opcodes.ISUB));
			toInject.add(new VarInsnNode(Opcodes.ISTORE, 14));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 14));
			toInject.add(new JumpInsnNode(Opcodes.IFGE, l43));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 14));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 4));
			toInject.add(new InsnNode(Opcodes.ICONST_1));
			toInject.add(new InsnNode(Opcodes.ISUB));
			toInject.add(new InsnNode(Opcodes.ISUB));
			toInject.add(new VarInsnNode(Opcodes.ISTORE, 14));
			toInject.add(l43);
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 14));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 4));
			toInject.add(new InsnNode(Opcodes.IDIV));
			toInject.add(new VarInsnNode(Opcodes.ISTORE, 14));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 13));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 14));
			toInject.add(new VarInsnNode(Opcodes.ILOAD, 4));
			toInject.add(new InsnNode(Opcodes.IAND));
			toInject.add(new InsnNode(Opcodes.ISUB));
			toInject.add(new VarInsnNode(Opcodes.ISTORE, 13));
			methodNode.instructions.insert(targetNode, toInject);
			
			return true;
		}
		
		return false;
	}
}
