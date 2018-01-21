package the_fireplace.frt.worldgen.structure;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
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
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.tools.MiscTools;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class WarzoneOutpost implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "outpost_warzone");
	public static final ResourceLocation STRUCTURE_LOOT = new ResourceLocation(FRT.MODID, "outpost_warzone");

	public static final ItemStack BOOK = new ItemStack(Items.WRITTEN_BOOK);

	static{
		BOOK.setTagCompound(new NBTTagCompound());
		BOOK.getTagCompound().setString("author", "The_Fireplace");
		BOOK.getTagCompound().setString("title", "Battle for the City");
		BOOK.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.outpostbook.3"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.outpostbook.4"));
		BOOK.getTagCompound().setTag("pages", pages);
	}

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		if(!ConfigValues.GENSTORIES) {
			BlockPos maxPos = basePos.add(18, 0, 22);//not perfect, but it'll do.
			if(!(world.getBlockState(basePos).getBlock() instanceof BlockGrass || world.getBlockState(basePos).getBlock() instanceof BlockDirt) || !(world.getBlockState(maxPos).getBlock() instanceof BlockGrass || world.getBlockState(maxPos).getBlock() instanceof BlockDirt))
				return false;

			return random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 8000 : 800) == 0 && world.provider.getDimensionType().equals(DimensionType.OVERWORLD) && !PrewarOutpost.forbiddenBiomes.contains(world.getBiome(basePos));
		}else
			return world.provider.getDimensionType().equals(DimensionType.OVERWORLD);
	}

	@Override
	public BlockPos getBase(Random random, int chunkX, int chunkZ, World world) {
		return world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16));
	}

	@Override
	public ResourceLocation getStructure() {
		return STRUCTURE_LOC;
	}

	@Override
	public float getIntegrity() {
		return (float)Math.abs(Math.sin(new Random().nextInt(16)*0.863f));
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
		if(state.getMaterial() == Material.LAVA) {
			Random sameRand = new Random(world.getSeed());
			if (sameRand.nextDouble() < Math.abs(Math.sin(world.getWorldTime() / 20d)))
				world.setBlockToAir(pos);
		}
	}
}
