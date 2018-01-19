package the_fireplace.frt.worldgen;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import the_fireplace.frt.worldgen.structure.IStructure;

import java.util.Random;

public class WorldGeneratorStructure implements IWorldGenerator {
	private final String id;

	public WorldGeneratorStructure(String id){
		this.id = id;
	}

	public WorldGeneratorStructure register(IStructure structure){
		StructureGenerator.registerStructure(id, structure);
		return this;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		StructureGenerator.generate(id, random, chunkX, chunkZ, world, chunkProvider);
	}
}
