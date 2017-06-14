package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockMeat extends BlockFalling {
	public static final PropertyInteger SQUISH = PropertyInteger.create("squish", 0, 6);
	protected static final AxisAlignedBB[] MEAT_AABB = new AxisAlignedBB[]{Block.FULL_BLOCK_AABB, new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 14D / 16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 12.5D / 16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 11D / 16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 9.5D / 16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 8D / 16D, 1.0D), new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 7D / 16D, 1.0D)};

	public BlockMeat() {
		super(new Material(MapColor.ADOBE));
		setCreativeTab(FRT.TabFRT);
		setDefaultState(this.blockState.getBaseState().withProperty(SQUISH, 0));
		setTickRandomly(true);
		setUnlocalizedName("meat_block");
		setHardness(0.5F);
		setSoundType(SoundType.SLIME);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return MEAT_AABB[state.getValue(SQUISH)];
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return getMetaFromState(state) == 0;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return getMetaFromState(state) == 0;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	@Nonnull
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(SQUISH, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@Nonnull
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}//Cutout

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(SQUISH);
	}

	@Override
	@Nonnull
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, SQUISH);
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
		return (7 - worldIn.getBlockState(pos).getValue(SQUISH)) * 2;
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public void onLanded(World worldIn, Entity entity) {
		worldIn.scheduleUpdate(entity.getPosition().down(), this, this.tickRate(worldIn));
		worldIn.scheduleUpdate(entity.getPosition(), this, this.tickRate(worldIn));
		super.onLanded(worldIn, entity);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getDustColor(IBlockState state) {
		return -6553600;
	}

	boolean flag = false;

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if(!worldIn.isRemote)
			if (getMetaFromState(state) > 0 && getEntityCountAt(worldIn, pos) <= 0 && getEntityCountAt(worldIn, pos.up()) <= 0) {
				worldIn.setBlockState(pos, getStateFromMeta(getMetaFromState(state) - 1));
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				flag = true;
			} else if (getEntityCountAt(worldIn, pos) + getEntityCountAt(worldIn, pos.up()) > 0 && getMetaFromState(state) < 6) {
				worldIn.setBlockState(pos, getStateFromMeta(getMetaFromState(state) + 1));
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				flag = true;
			} else if (flag || getMetaFromState(state) == 6) {
				worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
				if (getMetaFromState(state) < 6)
					flag = false;
			}
		super.updateTick(worldIn, pos, state, rand);
	}

	private int getEntityCountAt(World world, BlockPos pos) {
		return world.getEntitiesWithinAABB(EntityLivingBase.class, MEAT_AABB[0].offset(pos)).size();
	}
}
