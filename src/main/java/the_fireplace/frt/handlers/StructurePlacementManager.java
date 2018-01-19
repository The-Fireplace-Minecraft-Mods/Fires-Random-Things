package the_fireplace.frt.handlers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.worldgen.StructureGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber
public final class StructurePlacementManager extends WorldSavedData {
	private static HashMap<World, StructurePlacementManager> instances = Maps.newHashMap();

	private Multimap<ChunkPos, String> worldgenQueue = HashMultimap.create();

	private static final String sep = ",";

	public StructurePlacementManager(String name) {
		super(name);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		for(NBTBase tag:nbt.getTagList("worldgenQueue", 8)) {
			String[] tagData = ((NBTTagString)tag).getString().split(sep);
			worldgenQueue.put(new ChunkPos(Integer.parseInt(tagData[0]), Integer.parseInt(tagData[1])), tagData[2]);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setTag("worldgenQueue", new NBTTagList());
		for(Map.Entry<ChunkPos, String> entry : worldgenQueue.entries())
			compound.getTagList("worldGenQueue", 8).appendTag(new NBTTagString(entry.getKey().x+sep+entry.getKey().z+sep+entry.getValue()));
		return compound;
	}

	/**
	 * Checks if a structure can be generated at the given chunk coordinates.
	 * @param chunkX
	 *  The chunk's x-coord
	 * @param chunkZ
	 *  The chunk's z-coord
	 * @param chunkprovider
	 *  The chunk provider
	 * @return
	 *  true if the structure can generate, false if it cannot.
	 */
	public static boolean canGenerateStructure(int chunkX, int chunkZ, IChunkProvider chunkprovider) {
		for (int x = chunkX - 1; x <= chunkX + 1; x++) {
			for (int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				if (chunkprovider.getLoadedChunk(x, z) == null) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Queues a structure for generation. When it can safely load without causing cascading worldgen lag, it will.
	 * @param world
	 *  The world the chunk is in
	 * @param chunk
	 *  The chunk the structure will generate in
	 * @param strid
	 *  The structure id for the structure
	 */
	public static void queueGeneration(World world, ChunkPos chunk, String strid){
		if(!world.isRemote) {
			if(instances.get(world) == null)
				loadWorldData(world);
			instances.get(world).worldgenQueue.put(chunk, strid);
			instances.get(world).markDirty();
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void chunkGen(PopulateChunkEvent.Post event) {
		if(instances.get(event.getWorld()) != this)
			return;
		if (ConfigValues.GENSTRUCTURES) {
			if (worldgenQueue.isEmpty())
				return;
			ChunkPos pos = getSurroundingChunkFromList(event.getChunkX(), event.getChunkZ(), worldgenQueue.keySet());
			if (pos != null && event.getWorld().getChunkProvider().getLoadedChunk(pos.x, pos.z) != null) {
				for (String s : Lists.newArrayList(worldgenQueue.get(pos))) {
					worldgenQueue.remove(pos, s);
					markDirty();
					StructureGenerator.generate(s, event.getWorld().rand, pos.x, pos.z, event.getWorld(), event.getWorld().getChunkProvider());
				}
			}
		}
	}

	@SubscribeEvent
	public static void worldLoad(WorldEvent.Load event){
		if(!event.getWorld().isRemote)
			loadWorldData(event.getWorld());
	}
	
	private static void loadWorldData(World world){
		if(world.isRemote)
			return;
		StructurePlacementManager manager = (StructurePlacementManager) world.loadData(StructurePlacementManager.class, "frt:structure_management");
		if(manager == null){
			FRT.logDebug("Creating a new Structure Placement Manager for "+world.provider.getDimensionType().getName());
			manager = new StructurePlacementManager("frt:structure_management");
		}
		instances.put(world, manager);
		world.setData("frt:structure_management", instances.get(world));
		FRT.logTrace("Structure Placement Manager for "+world.provider.getDimensionType().getName()+" is set to "+manager.toString());
	}

	private static ChunkPos getSurroundingChunkFromList(int chunkX, int chunkZ, Set list) {
		for (int x = chunkX - 1; x <= chunkX + 1; x++) {
			for (int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				ChunkPos pos = new ChunkPos(x, z);
				if (list.contains(pos)) {
					return pos;
				}
			}
		}
		return null;
	}
}
