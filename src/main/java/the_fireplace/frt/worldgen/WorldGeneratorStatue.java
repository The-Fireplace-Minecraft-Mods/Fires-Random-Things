package the_fireplace.frt.worldgen;

import com.google.common.collect.Maps;
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

public class WorldGeneratorStatue implements IWorldGenerator {
	public static final ResourceLocation STATUE = new ResourceLocation(FRT.MODID, "statue");
	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(FRT.MODID, "statue");

	public static final ItemStack continentBook = new ItemStack(Items.WRITTEN_BOOK);

	public WorldGeneratorStatue() {
		continentBook.setTagCompound(new NBTTagCompound());
		continentBook.getTagCompound().setString("author", "The_Fireplace");
		continentBook.getTagCompound().setString("title", "Continent");
		continentBook.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.3"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.4"));
		continentBook.getTagCompound().setTag("pages", pages);
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

		BlockPos basePos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16)).down(random.nextInt(34));

		if (random.nextInt(32000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			Template templateStatue = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), STATUE);
			PlacementSettings settings = new PlacementSettings().setRotation(rotation);

			templateStatue.addBlocksToWorld(world, basePos, settings);

			boolean addedBook = false;

			BlockPos size = templateStatue.getSize();
			for (int x = 0; x < size.getX(); x++)
				for (int y = 0; y < size.getY(); y++)
					for (int z = 0; z < size.getZ(); z++) {
						BlockPos checkPos = basePos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
						IBlockState checkState = world.getBlockState(checkPos);
						if (checkState.getBlock() == Blocks.CHEST) {
							TileEntityChest chestTE = (TileEntityChest) world.getTileEntity(checkPos);
							chestTE.setLootTable(LOOT_TABLE, random.nextLong());
							if (!addedBook && ConfigValues.GENSTORIES) {
								chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), continentBook);
								addedBook = true;
							}
						}else if(checkState.getBlock() == Blocks.CONCRETE && random.nextInt(20) == 2){
							world.setBlockState(checkPos, Blocks.CONCRETE_POWDER.getDefaultState().withProperty(BlockConcretePowder.COLOR, checkState.getValue(BlockColored.COLOR)));
						}else if(checkState.getBlock() == Blocks.STAINED_GLASS && random.nextInt(10) == 2){
							world.setBlockState(checkPos, Blocks.SAND.getDefaultState());
						}else if(checkState.getMaterial() == Material.WOOD && random.nextInt(15) == 2){
							world.setBlockToAir(checkPos);
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
