package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author The_Fireplace
 */
public class BlockFireplaceBottom extends FRTBlock {

    public BlockFireplaceBottom() {
        super(Material.ROCK);
        setUnlocalizedName("fireplace_bottom");
        setHardness(2.0F);
        setResistance(10.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
        return true;
    }
}
