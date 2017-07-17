package the_fireplace.frt.worldgen;

import com.google.common.collect.Maps;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.handlers.CommonEvents;
import the_fireplace.frt.handlers.StructurePlacementManager;
import the_fireplace.frt.tools.MiscTools;
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
