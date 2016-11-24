package the_fireplace.frt.handlers;

import net.minecraft.block.BlockDispenser;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author The_Fireplace
 */
public class BlockSourceHand implements IBlockSource {

    BlockPos blockPos;
    World worldIn;
    EnumFacing enumFacing;

    public BlockSourceHand(BlockPos pos, World world, EnumFacing facing){
        blockPos = pos;
        worldIn = world;
        enumFacing = facing;
    }

    @Override
    public double getX() {
        return blockPos.getX();
    }

    @Override
    public double getY() {
        return blockPos.getY();
    }

    @Override
    public double getZ() {
        return blockPos.getZ();
    }

    @Override
    public BlockPos getBlockPos() {
        return blockPos;
    }

    @Override
    public IBlockState getBlockState() {
        return Blocks.DISPENSER.getDefaultState().withProperty(BlockDispenser.FACING, enumFacing);
    }

    @Override
    public <T extends TileEntity> T getBlockTileEntity() {
        return null;
    }

    @Override
    public World getWorld() {
        return worldIn;
    }
}
