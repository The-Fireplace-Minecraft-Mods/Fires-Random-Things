package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;

import java.util.Random;
/**
 *
 * @author The_Fireplace
 *
 */
public class BlockShellCore extends ULBlock {

	public BlockShellCore() {
		super(Material.anvil);
		setUnlocalizedName("shell_core");
		setTickRandomly(true);
		setLightLevel(10);
		setResistance(131072);
		setHardness(3.0F);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if(ConfigValues.ENABLESHELL){
			if(!worldIn.isRemote && worldIn.isBlockPowered(pos)){
				this.generateShell(pos, worldIn);
			}
		}
		if(!worldIn.isRemote && !worldIn.isBlockPowered(pos)){
			this.deconstructShell(pos, worldIn);
		}
	}

	@Override
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
		if(ConfigValues.ENABLESHELL){
			if(!worldIn.isRemote && worldIn.isBlockPowered(pos)){
				this.generateShell(pos, worldIn);
			}
		}
		if(!worldIn.isRemote && !worldIn.isBlockPowered(pos)){
			this.deconstructShell(pos, worldIn);
		}
	}

	private void createBlock(Block block, BlockPos pos, World world){
		if(world.getBlockState(pos).getBlock() != Blocks.air){}else{
			world.setBlockState(pos, block.getDefaultState());
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
		this.deconstructShell(pos, worldIn);
	}

	private void breakBlock(Block block, BlockPos pos, World world){
		if(world.getBlockState(pos).getBlock() == block){
			world.setBlockToAir(pos);
		}
	}

	public void generateShell(BlockPos pos, World world){
		int X = pos.getX();
		int Y = pos.getY();
		int Z = pos.getZ();
		//Center Ring//TODO: Convert to a for loop
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y, Z-3), world);
		//Upper Middle Ring
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y+1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+1, Z-3), world);
		//Lower Middle Ring
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y-1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-1, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-1, Z-3), world);
		//Top Ring
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y+2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+2, Z-3), world);
		//Bottom Ring
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y-2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-2, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-2, Z-3), world);
		//Top layer
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y+3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y+3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y+3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y+3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y+3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y+3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y+3, Z-3), world);
		//Bottom Layer
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z+3), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z+2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z+1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z-1), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z-2), world);
		createBlock(FRT.shell, new BlockPos(X+3, Y-3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+2, Y-3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X+1, Y-3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X, Y-3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-1, Y-3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-2, Y-3, Z-3), world);
		createBlock(FRT.shell, new BlockPos(X-3, Y-3, Z-3), world);
	}

	public void deconstructShell(BlockPos pos, World world){
		int X = pos.getX();
		int Y = pos.getY();
		int Z = pos.getZ();
		//Center Ring
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y, Z-3), world);
		//Upper Ring
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+1, Z-3), world);
		//Lower Ring
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-1, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-1, Z-3), world);
		//Top Ring
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+2, Z-3), world);
		//Bottom Ring
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-2, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-2, Z-3), world);
		//Top layer
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y+3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y+3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y+3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y+3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y+3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y+3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y+3, Z-3), world);
		//Bottom Layer
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z+3), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z+2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z+1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z-1), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z-2), world);
		breakBlock(FRT.shell, new BlockPos(X+3, Y-3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+2, Y-3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X+1, Y-3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X, Y-3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-1, Y-3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-2, Y-3, Z-3), world);
		breakBlock(FRT.shell, new BlockPos(X-3, Y-3, Z-3), world);
	}
}
