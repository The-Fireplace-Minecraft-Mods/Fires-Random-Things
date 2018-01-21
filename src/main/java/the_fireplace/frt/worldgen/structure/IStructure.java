package the_fireplace.frt.worldgen.structure;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public interface IStructure {
	/**
	 * Can the structure spawn at the sprcified location?
	 *
	 * @param basePos
	 * @param random
	 * @param chunkX
	 * @param chunkZ
	 * @param world
	 * @param chunkProvider
	 * @return true if the structure can spawn, false if it can't
	 */
	boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider);

	/**
	 * Gets the base position to attempt to spawn the structure at. This is the position passed to canSpawn()
	 *
	 * @param random
	 * @param chunkX
	 * @param chunkZ
	 * @param world
	 * @return The base position.
	 */
	BlockPos getBase(Random random, int chunkX, int chunkZ, World world);

	/**
	 * The structure's integrity.
	 *
	 * @return A float in the range (0.0f,1.0f]
	 */
	float getIntegrity();

	/**
	 * The resource location of the structure file
	 */
	ResourceLocation getStructure();

	/**
	 * The loot table to use for chests in this structure
	 */
	@Nullable
	ResourceLocation getLootTable();

	/**
	 * Add books to a chest
	 *
	 * @param chest
	 * 		The chest to add books to.
	 */
	void addBooks(TileEntityChest chest, Random random);

	/**
	 * Use this to damage blocks within your structure how you want to. This is called for each block in your structure.
	 *
	 * @param world
	 * 		The world the structure is in
	 * @param state
	 * 		The state this is being called for
	 * @param pos
	 * 		The position this is being called for
	 */
	void doCustomDestruction(World world, IBlockState state, BlockPos pos, Random random);
}
