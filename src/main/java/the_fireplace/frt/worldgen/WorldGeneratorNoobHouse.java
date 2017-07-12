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
import the_fireplace.frt.events.CommonEvents;
import the_fireplace.frt.tools.MiscTools;

import java.util.Random;

public class WorldGeneratorNoobHouse implements IWorldGenerator {
	public static final ResourceLocation NOOB_HOUSE = new ResourceLocation(FRT.MODID, "noobhouse");
	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(FRT.MODID, "noob_house");

	public static final ItemStack noobHouseBook = new ItemStack(Items.WRITTEN_BOOK);

	public WorldGeneratorNoobHouse() {
		noobHouseBook.setTagCompound(new NBTTagCompound());
		noobHouseBook.getTagCompound().setString("author", "The_Fireplace");
		noobHouseBook.getTagCompound().setString("title", "About this house");
		noobHouseBook.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.2"));
		noobHouseBook.getTagCompound().setTag("pages", pages);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!ConfigValues.GENSTRUCTURES)
			return;
		if (!areSurroundingChunksLoaded(chunkX, chunkZ, chunkProvider)) {
			CommonEvents.worldgenQueue.computeIfAbsent(world, k -> Maps.newHashMap());
			CommonEvents.worldgenQueue.get(world).put(new ChunkPos(chunkX, chunkZ), this);
			return;
		}

		BlockPos basePos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16)).down();

		if (random.nextInt(30000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && (world.getBlockState(basePos).getMaterial() == Material.GROUND || world.getBlockState(basePos).getMaterial() == Material.GRASS) && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			Template templateNoobHouse = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), NOOB_HOUSE);
			PlacementSettings settings = new PlacementSettings().setIntegrity(0.999F).setRotation(rotation);

			templateNoobHouse.addBlocksToWorld(world, basePos, settings);

			if (!ConfigValues.GENSTORIES)
				return;

			boolean addedBook = false;

			BlockPos size = templateNoobHouse.getSize();
			for (int x = 0; x < size.getX(); x++)
				for (int y = 0; y < size.getY(); y++)
					for (int z = 0; z < size.getZ(); z++) {
						BlockPos checkPos = basePos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
						IBlockState checkState = world.getBlockState(checkPos);
						if (checkState.getBlock() == Blocks.CHEST) {
							TileEntityChest chestTE = (TileEntityChest) world.getTileEntity(checkPos);
							chestTE.setLootTable(LOOT_TABLE, random.nextLong());
							if (!addedBook) {
								chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), noobHouseBook);
								addedBook = true;
							}
						}
					}
		}
	}

	private boolean areSurroundingChunksLoaded(int chunkX, int chunkZ, IChunkProvider chunkprovider) {
		for (int x = chunkX - 1; x <= chunkX + 1; x++) {
			for (int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				if (chunkprovider.getLoadedChunk(x, z) == null) {
					return false;
				}
			}
		}
		return true;
	}
}
