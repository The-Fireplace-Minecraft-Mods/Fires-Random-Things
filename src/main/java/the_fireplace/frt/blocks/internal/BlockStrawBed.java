package the_fireplace.frt.blocks.internal;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockBed;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

import javax.annotation.Nullable;
import java.util.Random;

@MethodsReturnNonnullByDefault
public class BlockStrawBed extends BlockBed {

	public BlockStrawBed() {
		setSoundType(SoundType.PLANT);
		setHardness(0.5F);
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(FRT.straw_bed);
	}

	@Override
	public boolean isBed(IBlockState state, IBlockAccess world, BlockPos pos, @Nullable Entity player) {
		return true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(PART) == BlockBed.EnumPartType.HEAD ? Items.AIR : FRT.straw_bed;
	}
}
