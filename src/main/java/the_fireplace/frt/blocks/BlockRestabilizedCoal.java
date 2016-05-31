package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author The_Fireplace
 */
public class BlockRestabilizedCoal extends FRTBlock {
    public BlockRestabilizedCoal() {
        super(Material.ROCK);
        setUnlocalizedName("restabilized_coal_block");
        setHardness(5.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return 20;
    }
}
