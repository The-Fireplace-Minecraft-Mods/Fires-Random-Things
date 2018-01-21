package the_fireplace.frt.worldgen.structure;

import mcp.MethodsReturnNonnullByDefault;
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
public class PortalCave implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "cave1");
	private static final ResourceLocation STRUCTURE_LOOT = new ResourceLocation(FRT.MODID, "portal_cave");

	public static final ItemStack BOOK = new ItemStack(Items.WRITTEN_BOOK);
	public static final ItemStack BOOK2 = new ItemStack(Items.WRITTEN_BOOK);

	static {
		BOOK.setTagCompound(new NBTTagCompound());
		BOOK.getTagCompound().setString("author", "The_Fireplace");
		BOOK.getTagCompound().setString("title", "Expansion");
		BOOK.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.portalcavebook.1"));
		BOOK.getTagCompound().setTag("pages", pages);

		BOOK2.setTagCompound(new NBTTagCompound());
		BOOK2.getTagCompound().setString("author", "Wolver512");
		BOOK2.getTagCompound().setString("title", "no more netherrack");
		BOOK2.getTagCompound().setInteger("generation", 0);
		pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.portalcavebook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.portalcavebook.3"));
		BOOK2.getTagCompound().setTag("pages", pages);
	}

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		BlockPos maxPos = basePos.add(8, 12, 8);//Not perfect, but it'll work
		return random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 3000 : 1500) == 0 && world.getBlockState(basePos).getBlock() == Blocks.STONE && world.getBlockState(maxPos).getBlock() == Blocks.STONE && world.provider.getDimensionType().equals(DimensionType.OVERWORLD);
	}

	@Override
	public BlockPos getBase(Random random, int chunkX, int chunkZ, World world) {
		return new BlockPos(chunkX * 16, 20 + random.nextInt(20), chunkZ * 16);
	}

	@Override
	public ResourceLocation getStructure() {
		return STRUCTURE_LOC;
	}

	@Override
	public float getIntegrity() {
		return 1f;
	}

	@Override
	public ResourceLocation getLootTable() {
		return STRUCTURE_LOOT;
	}

	@Override
	public void addBooks(TileEntityChest chest, Random random) {
		chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), BOOK);
		chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), BOOK2);
	}

	@Override
	public void doCustomDestruction(World world, IBlockState state, BlockPos pos, Random random) {

	}
}
