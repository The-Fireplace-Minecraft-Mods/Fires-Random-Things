package the_fireplace.frt.worldgen.structure;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockConcretePowder;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class Statue implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "statue");
	private static final ResourceLocation STRUCTURE_LOOT = new ResourceLocation(FRT.MODID, "statue");

	public static final ItemStack BOOK = new ItemStack(Items.WRITTEN_BOOK);

	static {
		BOOK.setTagCompound(new NBTTagCompound());
		BOOK.getTagCompound().setString("author", "The_Fireplace");
		BOOK.getTagCompound().setString("title", "Continent");
		BOOK.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.3"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.4"));
		BOOK.getTagCompound().setTag("pages", pages);
	}

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		return random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 48000 : 12000) == 0 && world.provider.getDimensionType().equals(DimensionType.OVERWORLD);
	}

	@Override
	public BlockPos getBase(Random random, int chunkX, int chunkZ, World world) {
		return world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16)).down(random.nextInt(36));
	}

	@Override
	public ResourceLocation getStructure() {
		return STRUCTURE_LOC;
	}

	@Override
	public float getIntegrity() {
		return 1.0f;
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
		if (state.getBlock() == Blocks.CONCRETE && random.nextInt(20) == 2) {
			world.setBlockState(pos, Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, state.getValue(BlockColored.COLOR)));
		} else if (state.getBlock() == Blocks.STAINED_GLASS && random.nextInt(10) == 2) {
			world.setBlockState(pos, Blocks.SAND.getDefaultState());
		} else if (state.getMaterial() == Material.WOOD && random.nextInt(15) == 2) {
			world.setBlockToAir(pos);
		}
	}
}
