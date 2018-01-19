package the_fireplace.frt.worldgen.structure;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import the_fireplace.frt.FRT;
import the_fireplace.frt.tools.MiscTools;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BosSign implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "bossign");

	private static boolean toggle = false;

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		return random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 4000 : 2000) == 0 && world.provider.getDimensionType().equals(DimensionType.OVERWORLD);
	}

	@Override
	public BlockPos getBase(Random random, int chunkX, int chunkZ, World world) {
		BlockPos basePos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16)).down(random.nextInt(20) - 10);
		if (basePos.getY() <= 0)
			basePos = basePos.up(10 + Math.abs(basePos.getY()));
		return basePos;
	}

	@Override
	public ResourceLocation getStructure() {
		return STRUCTURE_LOC;
	}

	@Override
	public float getIntegrity() {
		toggle = !toggle;
		return toggle ? 0 : 0.75f;
	}

	@Override
	@Nullable
	public ResourceLocation getLootTable() {
		return null;
	}

	@Override
	public void addBooks(TileEntityChest chest, Random random) {

	}

	@Override
	public void doCustomDestruction(World world, IBlockState state, BlockPos pos, Random random) {

	}
}
