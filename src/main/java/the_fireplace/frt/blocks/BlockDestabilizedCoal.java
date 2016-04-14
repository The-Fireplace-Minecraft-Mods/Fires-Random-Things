package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * @author The_Fireplace
 */
public class BlockDestabilizedCoal extends ULBlock {
	public BlockDestabilizedCoal() {
		super(Material.rock);
		setUnlocalizedName("destabilized_coal_block");
		setHardness(5.0F);
		setResistance(6.0F);
		setHarvestLevel("pickaxe", 0);
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face)
	{
		return 50;
	}
}
