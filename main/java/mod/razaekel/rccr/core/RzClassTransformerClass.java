package mod.razaekel.rccr.core;

import java.util.ArrayList;

import mod.razaekel.rccr.RCCR;
import mod.razaekel.rccr.RCCRContainer;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class RzClassTransformerClass extends RzClassTransformer
{
	public ArrayList<String[]> registeredMethods;
	
	public RzClassTransformerClass()
	{
		registeredMethods = new ArrayList<String[]>();
	}
	
	public void registerExpectedMethod(String methodID, String obfName, String signature)
	{
		registeredMethods.add(new String[]{obfName, signature, methodID});
	}
	
	@Override
	public boolean transform(String className, ClassNode classNode, boolean obf)
	{
		boolean sigs[] = new boolean[registeredMethods.size()];
		
		for (MethodNode m : classNode.methods)
		{			
			for (int methodIndex = 0; methodIndex < registeredMethods.size(); methodIndex++)
			{
				String[] methodInfo = registeredMethods.get(methodIndex);
						
				String srgName = getSrgName(className, m);
				String srgSignature = getSrgDescriptor(m.desc);
				
				if (RCCR.DEBUG){System.out.println("Checking " + srgName + " against " + methodInfo[0] + " and " + srgSignature + " against " + methodInfo[1] +"!");}
				
				if (srgName.equals(methodInfo[0]) && srgSignature.equals(methodInfo[1]))
				{
					if (transformMethod(className, methodInfo[2], m, obf))
					{
						sigs[methodIndex] = true;
					}
				}
			}
		}
		
		boolean didChange = false;
		
		for (int methodIndex = 0; methodIndex < registeredMethods.size(); methodIndex++)
		{
			if (!sigs[methodIndex])
			{
				String[] methodInfo = registeredMethods.get(methodIndex);
				RCCRContainer.logger.error("Could not transform expected method in class \"" + className + "\" (Obf: " + obf + "): " + methodInfo[0] + " - " + methodInfo[1] + " - " + methodInfo[2]);
			}
			else
			{
				didChange = true;
			}
		}
		
		return didChange;
	}
	
	public abstract boolean transformMethod(String className, String methodID, MethodNode methodNode, boolean obf);
}
