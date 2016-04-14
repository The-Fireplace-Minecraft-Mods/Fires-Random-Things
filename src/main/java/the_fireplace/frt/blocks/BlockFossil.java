package the_fireplace.frt.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
/**
 *
 * @author The_Fireplace
 *
 */
public class BlockFossil extends ULBlock {

	public BlockFossil() {
		super(Material.rock);
		setHardness(1.5F);
		setResistance(5.0F);
		setUnlocalizedName("fossil");
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.bone;
	}
}
