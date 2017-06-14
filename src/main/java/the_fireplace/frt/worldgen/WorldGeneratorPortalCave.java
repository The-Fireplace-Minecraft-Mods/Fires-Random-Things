package the_fireplace.frt.worldgen;

import com.google.common.collect.Maps;
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
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.events.CommonEvents;
import the_fireplace.frt.tools.MiscTools;

import java.util.Random;

public class WorldGeneratorPortalCave implements IWorldGenerator {
	public static final ResourceLocation PORTAL_CAVE = new ResourceLocation(FRT.MODID, "cave1");
	private static final ResourceLocation LOOT_TABLE = new ResourceLocation(FRT.MODID,"portal_cave");

	public static final ItemStack portalCaveBook = new ItemStack(Items.WRITTEN_BOOK);
	public static final ItemStack portalCaveBook2 = new ItemStack(Items.WRITTEN_BOOK);

	public WorldGeneratorPortalCave(){
		portalCaveBook.setTagCompound(new NBTTagCompound());
		portalCaveBook.getTagCompound().setString("author", "The_Fireplace");
		portalCaveBook.getTagCompound().setString("title", "Expansion");
		portalCaveBook.getTagCompound().setInteger("generation", 0);
		NBTTagList pages = new NBTTagList();
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
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(!ConfigValues.GENSTRUCTURES)
			return;
		if(!areSurroundingChunksLoaded(chunkX, chunkZ, chunkProvider)) {
			CommonEvents.worldgenQueue.computeIfAbsent(world, k -> Maps.newHashMap());
			CommonEvents.worldgenQueue.get(world).put(new ChunkPos(chunkX, chunkZ), this);
			return;
		}

		BlockPos basePos = new BlockPos(chunkX*16, 20+random.nextInt(20), chunkZ*16);

		if(random.nextInt(1000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && world.getBlockState(basePos).getBlock() == Blocks.STONE && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)){
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			Template templateNoobHouse = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), PORTAL_CAVE);
			PlacementSettings settings = new PlacementSettings().setRotation(rotation);

			templateNoobHouse.addBlocksToWorld(world, basePos, settings);

			if(!ConfigValues.GENSTORIES)
				return;

			BlockPos size = templateNoobHouse.getSize();
			for(int x = 0; x < size.getX(); x++)
				for(int y = 0; y < size.getY(); y++)
					for(int z = 0; z < size.getZ(); z++) {
						BlockPos checkPos = basePos.add(Template.transformedBlockPos(settings, new BlockPos(x, y, z)));
						IBlockState checkState = world.getBlockState(checkPos);
						if(checkState.getBlock() == Blocks.CHEST) {
							TileEntityChest chestTE = (TileEntityChest) world.getTileEntity(checkPos);
							chestTE.setLootTable(LOOT_TABLE, random.nextLong());
							chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), portalCaveBook);
							chestTE.setInventorySlotContents(random.nextInt(chestTE.getSizeInventory()), portalCaveBook2);
						}
					}
		}
	}

	private boolean areSurroundingChunksLoaded(int chunkX, int chunkZ, IChunkProvider chunkprovider) {
		for(int x = chunkX - 1; x <= chunkX + 1; x++) {
			for(int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				if(chunkprovider.getLoadedChunk(x, z) == null) {
					return false;
				}
			}
		}
		return true;
	}
}
