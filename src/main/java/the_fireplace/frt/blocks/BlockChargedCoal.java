package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockChargedCoal extends FRTBlock {

    public BlockChargedCoal() {
        super(Material.ROCK);
        setUnlocalizedName("charged_coal_block");
        setTickRandomly(true);
        setHardness(5.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 10;
    }

    @Override
    public int tickRate(World worldIn) {
        return 30;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        this.spawnParticles(worldIn, pos);
    }

    private void spawnParticles(World worldIn, BlockPos pos) {
        Random random = worldIn.rand;
        double d0 = 0.0625D;
        short k = 0;

        for (int i = 0; i < 6; ++i) {
            double d1 = pos.getX() + random.nextFloat();
            double d2 = pos.getY() + random.nextFloat();
            double d3 = pos.getZ() + random.nextFloat();

            if (i == 0 && !worldIn.getBlockState(pos.up()).isOpaqueCube()) {
                d2 = pos.getY() + d0 + 1.0D;
            }

            if (i == 1 && !worldIn.getBlockState(pos.down()).isOpaqueCube()) {
                d2 = pos.getY() - d0;
            }

            if (i == 2 && !worldIn.getBlockState(pos.south()).isOpaqueCube()) {
                d3 = pos.getZ() + d0 + 1.0D;
            }

            if (i == 3 && !worldIn.getBlockState(pos.north()).isOpaqueCube()) {
                d3 = pos.getZ() - d0;
            }

            if (i == 4 && !worldIn.getBlockState(pos.east()).isOpaqueCube()) {
                d1 = pos.getX() + d0 + 1.0D;
            }

            if (i == 5 && !worldIn.getBlockState(pos.west()).isOpaqueCube()) {
                d1 = pos.getX() - d0;
            }

            if (d1 < pos.getX() || d1 > pos.getX() + 1 || d2 < 0.0D || d2 > pos.getY() + 1 || d3 < pos.getZ() || d3 > pos.getZ() + 1) {
                if (k == 0) {
                    worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
                }
                if (k < 10) {
                    k++;
                } else {
                    k = 0;
                }
            }
        }
    }
}
