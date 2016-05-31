package the_fireplace.frt.worldgen;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import the_fireplace.frt.FRT;

import java.util.Random;

public class WorldGeneratorWax implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension()) {
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateSurface(World world, Random random, int BlockX, int BlockZ) {
        int maxY = 80;
        int minY = 60;
        for (int i = minY; i < maxY; i++) {
            int Xcoord = BlockX + random.nextInt(16);
            int Zcoord = BlockZ + random.nextInt(16);
            int Ycoord = random.nextInt(maxY - minY) + minY;
            (new WorldGenMinable(FRT.wax_deposit.getDefaultState(), 4, BlockMatcher.forBlock(Blocks.DIRT))).generate(world, random, new BlockPos(Xcoord, Ycoord, Zcoord));
        }
    }
}
