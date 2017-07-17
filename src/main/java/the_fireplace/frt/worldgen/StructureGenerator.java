package the_fireplace.frt.worldgen;

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
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.handlers.StructurePlacementManager;
import the_fireplace.frt.tools.MiscTools;

import java.util.Random;

public abstract class StructureGenerator {

	public static final ResourceLocation NOOB_HOUSE = new ResourceLocation(FRT.MODID, "noobhouse");
	public static final ResourceLocation BOS_SIGN = new ResourceLocation(FRT.MODID, "bossign");
	public static final ResourceLocation PORTAL_CAVE = new ResourceLocation(FRT.MODID, "cave1");
	public static final ResourceLocation STATUE = new ResourceLocation(FRT.MODID, "statue");

	private static final ResourceLocation NOOB_HOUSE_LOOT = new ResourceLocation(FRT.MODID, "noob_house");
	private static final ResourceLocation PORTAL_CAVE_LOOT = new ResourceLocation(FRT.MODID, "portal_cave");
	private static final ResourceLocation STATUE_LOOT = new ResourceLocation(FRT.MODID, "statue");

	public static final ItemStack noobHouseBook = new ItemStack(Items.WRITTEN_BOOK);
	public static final ItemStack portalCaveBook = new ItemStack(Items.WRITTEN_BOOK);
	public static final ItemStack portalCaveBook2 = new ItemStack(Items.WRITTEN_BOOK);
	public static final ItemStack continentBook = new ItemStack(Items.WRITTEN_BOOK);

	static {
		noobHouseBook.setTagCompound(new NBTTagCompound());
		noobHouseBook.getTagCompound().setString("author", "The_Fireplace");
		noobHouseBook.getTagCompound().setString("title", "About this house");
		noobHouseBook.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.noobhousebook.2"));
		noobHouseBook.getTagCompound().setTag("pages", pages);

		portalCaveBook.setTagCompound(new NBTTagCompound());
		portalCaveBook.getTagCompound().setString("author", "The_Fireplace");
		portalCaveBook.getTagCompound().setString("title", "Expansion");
		portalCaveBook.getTagCompound().setInteger("generation", 0);
		pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.portalcavebook.1"));
		portalCaveBook.getTagCompound().setTag("pages", pages);

		portalCaveBook2.setTagCompound(new NBTTagCompound());
		portalCaveBook2.getTagCompound().setString("author", "Wolver512");
		portalCaveBook2.getTagCompound().setString("title", "no more netherrack");
		portalCaveBook2.getTagCompound().setInteger("generation", 0);
		pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.portalcavebook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.portalcavebook.3"));
		portalCaveBook2.getTagCompound().setTag("pages", pages);

		continentBook.setTagCompound(new NBTTagCompound());
		continentBook.getTagCompound().setString("author", "The_Fireplace");
		continentBook.getTagCompound().setString("title", "Continent");
		continentBook.getTagCompound().setInteger("generation", 0);
		pages = new NBTTagList();
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.1"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.2"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.3"));
		pages.appendTag(MiscTools.getLocalBookPage("frt.continentbook.4"));
		continentBook.getTagCompound().setTag("pages", pages);
	}

	/**
	 * Generate a structure
	 *
	 * @param random the chunk specific {@link Random}.
	 * @param chunkX the chunk X coordinate of this chunk.
	 * @param chunkZ the chunk Z coordinate of this chunk.
	 * @param world : additionalData[0] The minecraft {@link World} we're generating for.
	 * @param chunkProvider : additionalData[2] {@link IChunkProvider} that is requesting the world generation.
	 *
	 */
	public static void generate(String id, Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkProvider){
		if (!ConfigValues.GENSTRUCTURES)
			return;
		if (!StructurePlacementManager.canGenerateStructure(chunkX, chunkZ, chunkProvider)) {
			StructurePlacementManager.queueGeneration(world, new ChunkPos(chunkX, chunkZ), id);
			return;
		}
		switch(id){
			case "noobhouse": {
				BlockPos basePos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16)).down();

				if (random.nextInt(30000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && (world.getBlockState(basePos).getMaterial() == Material.GROUND || world.getBlockState(basePos).getMaterial() == Material.GRASS) && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
					Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
					Template templateNoobHouse = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), NOOB_HOUSE);
					PlacementSettings settings = new PlacementSettings().setIntegrity(0.999F).setRotation(rotation);

					templateNoobHouse.addBlocksToWorld(world, basePos, settings);

					boolean addedBook = false;

					BlockPos size = templateNoobHouse.getSize();
					for (int x = 0; x < size.getX(); x++)
						for (int y = 0; y < size.getY(); y++)
							for (int z = 0; z < size.getZ(); z++) {
								BlockPos checkPos = basePos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
								IBlockState checkState = world.getBlockState(checkPos);
								if (checkState.getBlock() == Blocks.CHEST) {
									TileEntityChest chestTE = (TileEntityChest) world.getTileEntity(checkPos);
									chestTE.setLootTable(NOOB_HOUSE_LOOT, random.nextLong());
									if (!addedBook && ConfigValues.GENSTORIES) {
										chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), noobHouseBook);
										addedBook = true;
									}
								}
							}
				}
				break;
			}
			case "bossign": {
				BlockPos basePos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16));
				basePos = basePos.down(random.nextInt(20) - 10);
				if (basePos.getY() <= 0)
					basePos = basePos.up(10 + Math.abs(basePos.getY()));

				if (random.nextInt(10000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
					Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
					Template templateBosSign = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), BOS_SIGN);
					PlacementSettings settings = new PlacementSettings().setIntegrity(0.5F).setRotation(rotation);

					templateBosSign.addBlocksToWorld(world, basePos, settings);
				}
				break;
			}
			case "portalcave": {
				BlockPos basePos = new BlockPos(chunkX * 16, 20 + random.nextInt(20), chunkZ * 16);

				if (random.nextInt(1000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && world.getBlockState(basePos).getBlock() == Blocks.STONE && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
					Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
					Template templateNoobHouse = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), PORTAL_CAVE);
					PlacementSettings settings = new PlacementSettings().setRotation(rotation);

					templateNoobHouse.addBlocksToWorld(world, basePos, settings);

					BlockPos size = templateNoobHouse.getSize();
					for (int x = 0; x < size.getX(); x++)
						for (int y = 0; y < size.getY(); y++)
							for (int z = 0; z < size.getZ(); z++) {
								BlockPos checkPos = basePos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
								IBlockState checkState = world.getBlockState(checkPos);
								if (checkState.getBlock() == Blocks.CHEST) {
									TileEntityChest chestTE = (TileEntityChest) world.getTileEntity(checkPos);
									chestTE.setLootTable(PORTAL_CAVE_LOOT, random.nextLong());
									if(ConfigValues.GENSTORIES) {
										chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), portalCaveBook);
										chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), portalCaveBook2);
									}
								}
							}
				}
				break;
			}
			case "statue": {
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
									chestTE.setLootTable(STATUE_LOOT, random.nextLong());
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
		}
	}
}
