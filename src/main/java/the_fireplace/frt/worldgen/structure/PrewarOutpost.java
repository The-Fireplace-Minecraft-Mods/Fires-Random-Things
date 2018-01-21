package the_fireplace.frt.worldgen.structure;

import com.google.common.collect.Lists;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import the_fireplace.frt.FRT;
import the_fireplace.frt.tools.MiscTools;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class PrewarOutpost implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "outpost_prewar");
	public static final ResourceLocation STRUCTURE_LOOT = new ResourceLocation(FRT.MODID, "outpost_prewar");

	public static final List<Biome> forbiddenBiomes = Lists.newArrayList(Biomes.OCEAN, Biomes.DEEP_OCEAN, Biomes.FROZEN_OCEAN, Biomes.RIVER, Biomes.FROZEN_RIVER, Biomes.BEACH, Biomes.SWAMPLAND, Biomes.MUTATED_SWAMPLAND, Biomes.MUSHROOM_ISLAND, Biomes.MUSHROOM_ISLAND_SHORE, Biomes.COLD_BEACH, Biomes.STONE_BEACH);

	public static final ItemStack BOOK = new ItemStack(Items.WRITTEN_BOOK);

	static {
		BOOK.setTagCompound(new NBTTagCompound());
		BOOK.getTagCompound().setString("author", "The_Fireplace");
		BOOK.getTagCompound().setString("title", "Outpost");
		BOOK.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.outpostbook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.outpostbook.2"));
		BOOK.getTagCompound().setTag("pages", pages);
	}

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		BlockPos maxPos = basePos.add(18, 0, 22);//not perfect, but it'll do.
		return (world.getBlockState(basePos).getBlock() instanceof BlockGrass || world.getBlockState(basePos).getBlock() instanceof BlockDirt) && (world.getBlockState(maxPos).getBlock() instanceof BlockGrass || world.getBlockState(maxPos).getBlock() instanceof BlockDirt) && random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 6000 : 600) == 0 && world.provider.getDimensionType().equals(DimensionType.OVERWORLD) && !forbiddenBiomes.contains(world.getBiome(basePos));

	}

	@Override
	public BlockPos getBase(Random random, int chunkX, int chunkZ, World world) {
		return world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16)).down();
	}

	@Override
	public ResourceLocation getStructure() {
		return STRUCTURE_LOC;
	}

	@Override
	public float getIntegrity() {
		return 0.998f;
	}

	@Override
	public ResourceLocation getLootTable() {
		return STRUCTURE_LOOT;
	}

	@Override
	public void addBooks(TileEntityChest chest, Random random) {
		chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), BOOK);
	}

	@Override
	public void doCustomDestruction(World world, IBlockState state, BlockPos pos, Random random) {

	}
}
