package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockInsaneDispenser extends BlockDispenser {
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	public static final PropertyBool TRIGGERED = PropertyBool.create("triggered");
	protected Random rand = new Random();

	public BlockInsaneDispenser() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, false));
		this.setCreativeTab(FRT.TabFRT);
		this.setUnlocalizedName("insane_dispenser");
	}

	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World worldIn) {
		return 4;
	}

	@Override
	public void onBlockAdded(World worldIn, @Nullable BlockPos pos, @Nullable IBlockState state) {
		super.onBlockAdded(worldIn, pos, state);
		this.setDefaultDirection(worldIn, pos, state);
	}

	private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			EnumFacing enumfacing = state.getValue(FACING);
			boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
			boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();

			if (enumfacing == EnumFacing.NORTH && flag && !flag1) {
				enumfacing = EnumFacing.SOUTH;
			} else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag) {
				enumfacing = EnumFacing.NORTH;
			} else {
				boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
				boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();

				if (enumfacing == EnumFacing.WEST && flag2 && !flag3) {
					enumfacing = EnumFacing.EAST;
				} else if (enumfacing == EnumFacing.EAST && flag3 && !flag2) {
					enumfacing = EnumFacing.WEST;
				}
			}

			worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(TRIGGERED, false), 2);
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, @Nullable BlockPos pos, IBlockState state, @Nullable EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote) {
			return true;
		} else if (pos != null && playerIn != null) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityDispenser) {
				playerIn.displayGUIChest((TileEntityDispenser) tileentity);
			}

			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void dispense(@Nonnull World worldIn, @Nonnull BlockPos pos) {
		BlockSourceImpl blocksourceimpl = new BlockSourceImpl(worldIn, pos);
		TileEntityDispenser tileentitydispenser = blocksourceimpl.getBlockTileEntity();

		if (tileentitydispenser != null) {
			int i = tileentitydispenser.getDispenseSlot();

			if (i < 0) {
				worldIn.playBroadcastSound(1001, pos, 0);
			} else {
				ItemStack itemstack = tileentitydispenser.getStackInSlot(i);
				IBehaviorDispenseItem ibehaviordispenseitem = this.getBehavior(itemstack);

				if (ibehaviordispenseitem != IBehaviorDispenseItem.DEFAULT_BEHAVIOR) {
					ItemStack itemstack1 = ibehaviordispenseitem.dispense(blocksourceimpl, itemstack);
					tileentitydispenser.setInventorySlotContents(i, itemstack1.getCount() <= 0 ? ItemStack.EMPTY : itemstack1);
				}
			}
		}
	}

	@Override
	@Nonnull
	protected IBehaviorDispenseItem getBehavior(ItemStack stack) {
		return DISPENSE_BEHAVIOR_REGISTRY.getObject(stack.getItem());
	}

	/**
	 * Called when a neighboring block changes.
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, @Nullable BlockPos pos, Block neighborBlock, BlockPos neighborPos) {
		boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
		boolean flag1 = state.getValue(TRIGGERED);

		if (flag && !flag1) {
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
			worldIn.setBlockState(pos, state.withProperty(TRIGGERED, true), 4);
		} else if (!flag && flag1) {
			worldIn.setBlockState(pos, state.withProperty(TRIGGERED, false), 4);
		}
	}

	@Override
	public void updateTick(World worldIn, @Nullable BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote) {
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
			this.dispense(worldIn, pos);
		}
	}

	/**
	 * Returns a new instance of a block's tile entity class. Called on placing the block.
	 */
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDispenser();
	}

	/**
	 * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
	 * IBlockstate
	 */
	@Override
	@Nonnull
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(TRIGGERED, false);
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow post-place logic
	 */
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);

		if (stack.hasDisplayName()) {
			TileEntity tileentity = worldIn.getTileEntity(pos);

			if (tileentity instanceof TileEntityDispenser) {
				((TileEntityDispenser) tileentity).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public void breakBlock(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileEntityDispenser) {
			InventoryHelper.dropInventoryItems(worldIn, pos, (TileEntityDispenser) tileentity);
			worldIn.updateComparatorOutputLevel(pos, this);
		}

		super.breakBlock(worldIn, pos, state);
	}

	/**
	 * Get the facing of a dispenser with the given metadata
	 */
	public static EnumFacing getFacing(int meta) {
		return EnumFacing.getFront(meta & 7);
	}

	@Override
	public boolean hasComparatorInputOverride(IBlockState state) {
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World worldIn, @Nullable BlockPos pos) {
		return Container.calcRedstone(worldIn.getTileEntity(pos));
	}

	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	@Override
	@Nonnull
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(TRIGGERED, (meta & 8) > 0);
	}

	/**
	 * Convert the BlockState into the correct metadata value
	 */
	@Override
	public int getMetaFromState(IBlockState state) {
		int i = 0;
		i = i | state.getValue(FACING).getIndex();

		if (state.getValue(TRIGGERED)) {
			i |= 8;
		}

		return i;
	}

	@Override
	@Nonnull
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, FACING, TRIGGERED);
	}
}
