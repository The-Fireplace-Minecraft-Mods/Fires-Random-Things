package the_fireplace.frt.worldgen;

import com.google.common.collect.Maps;
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

import java.util.Random;

public class WorldGeneratorBosSign implements IWorldGenerator {
	public static final ResourceLocation BOS_SIGN = new ResourceLocation(FRT.MODID, "bossign");

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if (!ConfigValues.GENSTRUCTURES)
			return;
		if (!areSurroundingChunksLoaded(chunkX, chunkZ, chunkProvider)) {
			CommonEvents.worldgenQueue.computeIfAbsent(world, k -> Maps.newHashMap());
			CommonEvents.worldgenQueue.get(world).put(new ChunkPos(chunkX, chunkZ), this);
			return;
		}

		BlockPos basePos = world.getTopSolidOrLiquidBlock(new BlockPos(chunkX * 16, 0, chunkZ * 16));
		basePos = basePos.down(random.nextInt(20) - 10);
		if (basePos.getY() <= 0)
			basePos = basePos.up(10 + Math.abs(basePos.getY()));

		if (random.nextInt(10000) == 0 && world.getWorldType() != WorldType.DEBUG_ALL_BLOCK_STATES && world.getWorldType() != WorldType.FLAT && world.provider.getDimensionType().equals(DimensionType.OVERWORLD)) {
			Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
			Template templateNoobHouse = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), BOS_SIGN);
			PlacementSettings settings = new PlacementSettings().setIntegrity(0.5F).setRotation(rotation);

			templateNoobHouse.addBlocksToWorld(world, basePos, settings);
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
