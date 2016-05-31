package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockFossil extends FRTBlock {

    public BlockFossil() {
        super(Material.ROCK);
        setHardness(1.5F);
        setResistance(5.0F);
        setUnlocalizedName("fossil");
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.BONE;
    }
}
