package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author The_Fireplace
 */
public class BlockRefinedCoal extends FRTBlock {
    public BlockRefinedCoal() {
        super(Material.rock);
        setUnlocalizedName("refined_coal_block");
        setHardness(7.0F);
        setResistance(14.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 5;
    }
}
