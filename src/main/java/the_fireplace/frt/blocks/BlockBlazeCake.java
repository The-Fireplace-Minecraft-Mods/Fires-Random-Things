package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
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
public class BlockBlazeCake extends FRTBlock {
    public static final PropertyInteger BITES = PropertyInteger.create("bites", 0, 6);
    protected static final AxisAlignedBB[] CAKE_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.1875D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.3125D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.4375D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.5625D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.6875D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D), new AxisAlignedBB(0.8125D, 0.0D, 0.0625D, 0.9375D, 0.5D, 0.9375D)};

    public BlockBlazeCake() {
        super(Material.CAKE);
        setDefaultState(this.blockState.getBaseState().withProperty(BITES, 0));
        setTickRandomly(true);
        setUnlocalizedName("blaze_cake");
        setHardness(0.5F);
        disableStats();
        setSoundType(SoundType.SNOW);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return CAKE_AABB[state.getValue(BITES)];
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        this.eatCake(worldIn, pos, state, playerIn);
        return true;
    }

    @Override
    public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
        this.eatCake(worldIn, pos, worldIn.getBlockState(pos), playerIn);
    }

    private void eatCake(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        if (player.canEat(false)) {
            player.getFoodStats().addStats(2, 0.1F);
            int i = state.getValue(BITES);

            if (i < 6) {
                worldIn.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
            } else {
                worldIn.setBlockToAir(pos);
            }
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 1800));
        } else if (player.canEat(true)) {
            player.getFoodStats().addStats(0, -0.1F);
            int i = state.getValue(BITES);

            if (i < 6) {
                worldIn.setBlockState(pos, state.withProperty(BITES, i + 1), 3);
            } else {
                worldIn.setBlockToAir(pos);
            }
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 4800));
            player.setFire(240);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock, BlockPos neighborPos) {
        if (!this.canBlockStay(worldIn, pos)) {
            worldIn.setBlockToAir(pos);
        }
    }

    private boolean canBlockStay(World worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
    }

    @Override
    public int quantityDropped(Random random) {
        return 1;
    }

    @Override
    @Nonnull
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (state == this.getDefaultState()) {
            return Item.getItemFromBlock(FRT.blaze_cake);
        } else if (state == this.blockState.getBaseState().withProperty(BITES, 1) || state == this.blockState.getBaseState().withProperty(BITES, 2)) {
            return Items.MAGMA_CREAM;
        } else if (state == this.blockState.getBaseState().withProperty(BITES, 3) || state == this.blockState.getBaseState().withProperty(BITES, 4)) {
            return Items.SLIME_BALL;
        } else {
            return Items.SUGAR;
        }
    }

    @Override
    @Nonnull
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(BITES, meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(BITES);
    }

    @Override
    @Nonnull
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, BITES);
    }

    @Override
    public int getComparatorInputOverride(IBlockState state, World worldIn, BlockPos pos) {
        return (7 - worldIn.getBlockState(pos).getValue(BITES)) * 2;
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }
}
