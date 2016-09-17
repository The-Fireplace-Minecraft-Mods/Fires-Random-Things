package the_fireplace.frt.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author The_Fireplace
 */
public class BlockEnderBookshelf extends FRTBlock {

    public BlockEnderBookshelf() {
        super(Material.WOOD);
        setUnlocalizedName("ender_bookshelf");
        setHardness(10F);
        setSoundType(SoundType.WOOD);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 15;
    }
}
