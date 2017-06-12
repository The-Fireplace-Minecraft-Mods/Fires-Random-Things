package the_fireplace.frt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class ItemChargedCoal extends Item {
	public ItemChargedCoal() {
		setUnlocalizedName("charged_coal");
		setCreativeTab(FRT.TabFRT);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (world.getBlockState(pos).getBlock() == Blocks.REDSTONE_ORE || world.getBlockState(pos).getBlock() == Blocks.LIT_REDSTONE_ORE) {
			world.setBlockToAir(pos);
			world.setBlockState(pos, Blocks.COAL_ORE.getDefaultState());
			if (!playerIn.capabilities.isCreativeMode)
				playerIn.getHeldItem(hand).shrink(1);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
