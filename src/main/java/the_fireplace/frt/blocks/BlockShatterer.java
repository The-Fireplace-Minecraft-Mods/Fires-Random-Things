package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;
import the_fireplace.frt.tileentity.TileEntityShatterer;
import the_fireplace.frt.tools.MiscTools;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockShatterer extends BlockContainer {
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public BlockShatterer() {
        super(Material.IRON);
        setUnlocalizedName("pop_furnace");
        setCreativeTab(FRT.TabFRT);
        setHardness(5F);
        setResistance(15F);
        setHarvestLevel("pickaxe", 0);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        setSoundType(SoundType.METAL);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityShatterer();
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        setDefaultDirection(world, pos, state);
    }

    private void setDefaultDirection(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            IBlockState block = worldIn.getBlockState(pos.north());
            IBlockState block1 = worldIn.getBlockState(pos.south());
            IBlockState block2 = worldIn.getBlockState(pos.west());
            IBlockState block3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && block.isFullBlock() && !block1.isFullBlock())
                enumfacing = EnumFacing.SOUTH;
            else if (enumfacing == EnumFacing.SOUTH && block1.isFullBlock() && !block.isFullBlock())
                enumfacing = EnumFacing.NORTH;
            else if (enumfacing == EnumFacing.WEST && block2.isFullBlock() && !block3.isFullBlock())
                enumfacing = EnumFacing.EAST;
            else if (enumfacing == EnumFacing.EAST && block3.isFullBlock() && !block2.isFullBlock())
                enumfacing = EnumFacing.WEST;

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        else if (!playerIn.isSneaking()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityShatterer) {
                FMLNetworkHandler.openGui(playerIn, FRT.MODID, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        } else
            return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
            enumfacing = EnumFacing.NORTH;

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos neighborPos) {
        if (!worldIn.isRemote)
            if (worldIn.isBlockPowered(pos))
                popItems(worldIn, pos);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote)
            if (worldIn.isBlockPowered(pos))
                popItems(worldIn, pos);
    }

    public void popItems(World worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityShatterer)
            ((TileEntityShatterer) te).popItems();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityShatterer tile = (TileEntityShatterer) worldIn.getTileEntity(pos);
        int g = tile.getStoredGunpowder();
        int f = tile.getStoredFirestarter();
        int gstacks = 0;
        int fstacks = 0;
        while (g > 64) {
            gstacks++;
            g -= 64;
        }
        while (f > 64) {
            fstacks++;
            f -= 64;
        }
        while (gstacks > 0) {
            MiscTools.spawnItemAtPos(new ItemStack(FRT.gunpowder_substitute, 64), worldIn, pos);
            gstacks--;
        }
        while (fstacks > 0) {
            MiscTools.spawnItemAtPos(new ItemStack(FRT.firestarter_substitute, 64), worldIn, pos);
            fstacks--;
        }
        if (g > 0)
            MiscTools.spawnItemAtPos(new ItemStack(FRT.gunpowder_substitute, g), worldIn, pos);
        if (f > 0)
            MiscTools.spawnItemAtPos(new ItemStack(FRT.firestarter_substitute, f), worldIn, pos);
        super.breakBlock(worldIn, pos, state);
    }
}
