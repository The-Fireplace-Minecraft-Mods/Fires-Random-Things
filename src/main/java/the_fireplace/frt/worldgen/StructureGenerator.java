package the_fireplace.frt.worldgen;

import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.handlers.StructurePlacementManager;
import the_fireplace.frt.worldgen.structure.IStructure;

import java.util.Map;
import java.util.Random;

public abstract class StructureGenerator {

	private static final Map<String, IStructure> templates = Maps.newHashMap();

	public static void registerStructure(String id, IStructure structure){
		templates.put(id, structure);
	}

	public static boolean isWorldAcceptable(World world){
		return world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT;
	}

	/**
	 * Generate a structure
	 *
	 * @param random the chunk specific {@link Random}.
	 * @param chunkX the chunk X coordinate of this chunk.
	 * @param chunkZ the chunk Z coordinate of this chunk.
	 * @param world : additionalData[0] The minecraft {@link World} we're generating for.
	 * @param chunkProvider : additionalData[2] {@link IChunkProvider} that is requesting the world generation.
	 *
	 */
	public static void generate(String id, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		if (!ConfigValues.GENSTRUCTURES)
			return;
		if (!StructurePlacementManager.canGenerateStructure(chunkX, chunkZ, chunkProvider)) {
			StructurePlacementManager.queueGeneration(world, new ChunkPos(chunkX, chunkZ), id);
			return;
		}
		if (!templates.containsKey(id)) {
			FRT.logError("Template is not registered for " + id);
			return;
		}

		BlockPos basePos = templates.get(id).getBase(random, chunkX, chunkZ, world);

		if (isWorldAcceptable(world) && templates.get(id).canSpawn(basePos, random, chunkX, chunkZ, world, chunkProvider)) {
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), templates.get(id).getStructure());
			PlacementSettings settings = new PlacementSettings().setIntegrity(templates.get(id).getIntegrity()).setRotation(rotation);

			template.addBlocksToWorld(world, basePos, settings);

			boolean addedBook = false;

			BlockPos size = template.getSize();
			for (int x = 0; x < size.getX(); x++)
				for (int y = 0; y < size.getY(); y++)
					for (int z = 0; z < size.getZ(); z++) {
						BlockPos checkPos = basePos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
						IBlockState checkState = world.getBlockState(checkPos);
						if (checkState.getBlock() == Blocks.CHEST) {
							TileEntityChest chestTE = (TileEntityChest) world.getTileEntity(checkPos);
							if (chestTE != null) {
								ResourceLocation loot = templates.get(id).getLootTable();
								if (loot != null)
									chestTE.setLootTable(loot, random.nextLong());
								if (!addedBook && ConfigValues.GENSTORIES) {
									templates.get(id).addBooks(chestTE, random);
									addedBook = true;
								}
							}
						}
						templates.get(id).doCustomDestruction(world, checkState, checkPos, random);
					}
		}
	}
}
