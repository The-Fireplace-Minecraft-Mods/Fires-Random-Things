package the_fireplace.frt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
	public ItemChargedCoal(){
		setUnlocalizedName("charged_coal");
		setCreativeTab(FRT.TabFRT);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(world.getBlockState(pos).getBlock() == Blocks.redstone_ore || world.getBlockState(pos).getBlock() == Blocks.lit_redstone_ore){
			world.setBlockToAir(pos);
			world.setBlockState(pos, Blocks.coal_ore.getDefaultState());
			--stack.stackSize;
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
	}
}
