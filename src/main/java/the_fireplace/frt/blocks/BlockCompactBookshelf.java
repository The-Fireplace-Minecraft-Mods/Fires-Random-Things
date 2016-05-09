package the_fireplace.frt.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author The_Fireplace
 */
public class BlockCompactBookshelf extends FRTBlock {

    public BlockCompactBookshelf() {
        super(Material.wood);
        setUnlocalizedName("compact_bookshelf");
        setHardness(4.5F);
        setStepSound(SoundType.WOOD);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 3;
    }
}
