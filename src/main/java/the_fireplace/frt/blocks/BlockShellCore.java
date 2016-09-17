package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.tileentity.TileEntityShellCore;
import the_fireplace.frt.tools.MiscTools;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockShellCore extends BlockContainer {

    public BlockShellCore() {
        super(Material.ANVIL);
        setUnlocalizedName("shell_core");
        setLightLevel(10);
        setResistance(131072);
        setHardness(3.0F);
        setSoundType(SoundType.METAL);
        setCreativeTab(FRT.TabFRT);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return true;
    }

    @Override
    public boolean isVisuallyOpaque() {
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
        return new TileEntityShellCore();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack held, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;
        else if (!playerIn.isSneaking()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TileEntityShellCore) {
                FMLNetworkHandler.openGui(playerIn, FRT.MODID, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        } else
            return false;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(ArrayUtils.contains(ConfigValues.DISABLEDITEMS, FRT.shell.getUnlocalizedName().substring(5)))
            return;
        TileEntityShellCore tile = (TileEntityShellCore) worldIn.getTileEntity(pos);
        if(tile != null && !worldIn.isRemote) {
            if (ConfigValues.ENABLESHELL) {
                if (worldIn.isBlockPowered(pos)) {
                    if(tile.getStoredRedstone() > 0 && !tile.isActive) {
                        this.generateShell(pos, worldIn);
                        tile.isActive = true;
                    }else if(tile.isActive && tile.getStoredRedstone() <= 0){
                        this.deconstructShell(pos, worldIn);
                        tile.isActive = false;
                    }else if(tile.getStoredRedstone() > 0 && tile.isActive){
                        tile.tempItemCounter++;
                        if(tile.tempItemCounter > ConfigValues.TICKSPERREDSTONE){
                            tile.tempItemCounter -= ConfigValues.TICKSPERREDSTONE;
                            tile.removeFromRedstone(1);
                        }
                    }
                }
            }
            if (tile.isActive && !worldIn.isBlockPowered(pos)) {
                this.deconstructShell(pos, worldIn);
                tile.isActive = false;
            }
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block neighborBlock) {
        TileEntityShellCore tile = (TileEntityShellCore) worldIn.getTileEntity(pos);
        if(tile != null && !worldIn.isRemote) {
            if (ConfigValues.ENABLESHELL) {
                if (worldIn.isBlockPowered(pos)) {
                    if(tile.getStoredRedstone() > 0) {
                        this.generateShell(pos, worldIn);
                        tile.isActive = true;
                    }else{
                        this.deconstructShell(pos, worldIn);
                        tile.isActive = false;
                    }
                }
            }
            if (!worldIn.isBlockPowered(pos)) {
                this.deconstructShell(pos, worldIn);
                tile.isActive = false;
            }
        }
    }

    private void createBlock(Block block, BlockPos pos, World world) {
        if (world.getBlockState(pos).getBlock() == Blocks.AIR) {
            world.setBlockState(pos, block.getDefaultState());
        }
    }

    @Override
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        this.deconstructShell(pos, worldIn);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityShellCore tile = (TileEntityShellCore) worldIn.getTileEntity(pos);
        if(tile != null) {
            int r = tile.getStoredRedstone();
            int rstacks = 0;
            while (r > 64) {
                rstacks++;
                r -= 64;
            }
            while (rstacks > 0) {
                MiscTools.spawnItemAtPos(new ItemStack(Items.REDSTONE, 64), worldIn, pos);
                rstacks--;
            }
            if (r > 0)
                MiscTools.spawnItemAtPos(new ItemStack(Items.REDSTONE, r), worldIn, pos);
        }
        super.breakBlock(worldIn, pos, state);
    }

    private void breakBlock(Block block, BlockPos pos, World world) {
        if (world.getBlockState(pos).getBlock() == block) {
            world.setBlockToAir(pos);
        }
    }

    public void generateShell(BlockPos pos, World world) {//TODO: Convert to for loops
        int X = pos.getX();
        int Y = pos.getY();
        int Z = pos.getZ();
        //Center Ring
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y, Z - 3), world);
        //Upper Middle Ring
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z - 3), world);
        //Lower Middle Ring
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 1, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z - 3), world);
        //Top Ring
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z - 3), world);
        //Bottom Ring
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 2, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z - 3), world);
        //Top layer
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y + 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z - 3), world);
        //Bottom Layer
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z + 3), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z + 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z + 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z - 1), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z - 2), world);
        createBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X, Y - 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z - 3), world);
        createBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z - 3), world);
    }

    public void deconstructShell(BlockPos pos, World world) {//TODO: Convert to for loops
        int X = pos.getX();
        int Y = pos.getY();
        int Z = pos.getZ();
        //Center Ring
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y, Z - 3), world);
        //Upper Ring
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 1, Z - 3), world);
        //Lower Ring
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 1, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 1, Z - 3), world);
        //Top Ring
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 2, Z - 3), world);
        //Bottom Ring
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 2, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 2, Z - 3), world);
        //Top layer
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y + 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y + 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y + 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y + 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y + 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y + 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y + 3, Z - 3), world);
        //Bottom Layer
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z + 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z + 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z + 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z - 1), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z - 2), world);
        breakBlock(FRT.shell, new BlockPos(X + 3, Y - 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 2, Y - 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X + 1, Y - 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X, Y - 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 1, Y - 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 2, Y - 3, Z - 3), world);
        breakBlock(FRT.shell, new BlockPos(X - 3, Y - 3, Z - 3), world);
    }
}
