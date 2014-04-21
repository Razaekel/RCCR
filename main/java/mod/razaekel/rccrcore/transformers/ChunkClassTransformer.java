package mod.razaekel.rccrcore.transformers;

import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import mod.razaekel.rccrcore.RCCRCoreLoadingPlugin;
import net.minecraft.launchwrapper.IClassTransformer;

public class ChunkClassTransformer implements IClassTransformer
{
	@Override
	public byte[] transform(String name, String transformedName, byte[] data)
	{
		if (name.equals("net.minecraft.world.chunk.Chunk"))
		{
			System.out.println(" INSIDE CHUNK TRANSFORMER ABOUT TO PATCH ");
			data = patchClassInJar(name, data, name, RCCRCoreLoadingPlugin.location);
		}
		
		return data;
	}

	public byte[] patchClassInJar(String name, byte[] bytes, String ObfName, File location)
	{
		try
		{
			//open the jar as zip
			ZipFile zip = new ZipFile(location);
			
			//find the file inside the zip that is called net.minecraft.world.chunk.Chunk.class
			//replacing the . to / so it would look for net/minecraft/world/chunk/Chunk.class
			ZipEntry entry = zip.getEntry(name.replace('.', '/') + ".class");
			
			if (entry == null)
			{
				System.out.println(name + " not found in " + location.getName());
			} 
			else
			{
				//serialize the class file into the bytes array
				InputStream zin = zip.getInputStream(entry);
				bytes = new byte[(int) entry.getSize()];
				zin.read(bytes);
				zin.close();
				
				System.out.println("[" + "RCCRCore" + "]: " + "Class " + name + " patched!");
			}
			
			zip.close();
		} 
		catch (Exception e)
		{
			throw new RuntimeException("Error overriding " + name + " from " + location.getName(), e);
		}
		
		//return the new bytes
		return bytes;
	}
}
