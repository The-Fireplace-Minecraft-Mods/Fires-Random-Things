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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NoobHouse implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "noobhouse");
	public static final ResourceLocation STRUCTURE_LOOT = new ResourceLocation(FRT.MODID, "noob_house");

	public static final ItemStack BOOK = new ItemStack(Items.WRITTEN_BOOK);

	static {
		BOOK.setTagCompound(new NBTTagCompound());
		BOOK.getTagCompound().setString("author", "The_Fireplace");
		BOOK.getTagCompound().setString("title", "About this house");
		BOOK.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.3"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.4"));
		BOOK.getTagCompound().setTag("pages", pages);
	}

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		return random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 4000 : 2000) == 0 && (world.getBlockState(basePos).getMaterial() == Material.GROUND || world.getBlockState(basePos).getMaterial() == Material.GRASS) && world.provider.getDimensionType().equals(DimensionType.OVERWORLD);
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
		return 0.999f;
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
