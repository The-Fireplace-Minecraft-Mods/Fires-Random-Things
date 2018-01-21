package the_fireplace.frt.worldgen.structure;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import the_fireplace.frt.FRT;
import the_fireplace.frt.handlers.StructurePlacementManager;
import the_fireplace.frt.tools.MiscTools;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NetherBase implements IStructure {
	public static final ResourceLocation STRUCTURE_LOC = new ResourceLocation(FRT.MODID, "nether_base");
	private static final ResourceLocation STRUCTURE_LOOT = new ResourceLocation(FRT.MODID, "nether_base");

	public static final ItemStack BOOK = new ItemStack(Items.WRITTEN_BOOK);

	static {
		BOOK.setTagCompound(new NBTTagCompound());
		BOOK.getTagCompound().setString("author", "The_Fireplace");
		BOOK.getTagCompound().setString("title", "The Nether Base");
		BOOK.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.netherbook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.netherbook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.netherbook.3"));
		BOOK.getTagCompound().setTag("pages", pages);
	}

	@Override
	public boolean canSpawn(BlockPos basePos, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider) {
		return random.nextInt((world.getMinecraftServer() != null && world.getMinecraftServer().isDedicatedServer()) ? 2000 : 200) == 0 && (world.getBlockState(basePos).isFullBlock()) && world.provider.getDimensionType().equals(DimensionType.NETHER);
	}

	@Override
	public BlockPos getBase(Random random, int chunkX, int chunkZ, World world) {
		return new BlockPos(chunkX * 16, random.nextInt(110) + 2, chunkZ * 16);
	}

	@Override
	public ResourceLocation getStructure() {
		return STRUCTURE_LOC;
	}

	@Override
	public float getIntegrity() {
		return 0.9f;
	}

	@Override
	public ResourceLocation getLootTable() {
		return STRUCTURE_LOOT;
	}

	@Override
	public void addBooks(TileEntityChest chest, Random random) {
		chest.setInventorySlotContents(random.nextInt(chest.getSizeInventory()), BOOK);
		//Hacky, but it works. It will only be done when the story is enabled, which is what we want. It will also only be done once per structure. Also what we want.
		if (chest.getWorld().getMinecraftServer() != null) {
			ChunkPos owChunk = new ChunkPos(new BlockPos(chest.getPos().getX() * 8, chest.getPos().getY(), chest.getPos().getZ() * 8));
			FRT.logDebug("Queueing generation of Warzone Outpost at chunk " + owChunk.toString() + " in world " + chest.getWorld().getMinecraftServer().getWorld(0));
			StructurePlacementManager.queueGeneration(chest.getWorld().getMinecraftServer().getWorld(0), owChunk, "outpost_warzone");
		}
	}

	@Override
	public void doCustomDestruction(World world, IBlockState state, BlockPos pos, Random random) {

	}
}
