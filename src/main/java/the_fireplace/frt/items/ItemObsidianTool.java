package the_fireplace.frt.items;

import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import the_fireplace.frt.FRT;
import the_fireplace.frt.compat.chisel.ChiselCompat;
import the_fireplace.frt.compat.chisel.ChiselCompatDummy;
import the_fireplace.frt.compat.chisel.IChiselCompat;

/**
 * @author The_Fireplace
 */
public class ItemObsidianTool extends Item {
	public ItemObsidianTool() {
		setMaxStackSize(1);
		setUnlocalizedName("obsidian_tool");
		setCreativeTab(FRT.TabFRT);
		setMaxDamage(1562);//same as diamond pickaxe
		setFull3D();
	}

	@Override
	public boolean hitEntity(ItemStack is, EntityLivingBase entityHit, EntityLivingBase attacker) {
		is.damageItem(2, attacker);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack is, World world, IBlockState block, BlockPos pos, EntityLivingBase player) {
		if (block.getBlockHardness(world, pos) != 0.0D) {
			is.damageItem(1, player);
		}
		if (block.getBlockHardness(world, pos) == 50.0F) {
			block.getBlock().dropBlockAsItem(world, pos, block, 0);
		}
		return true;
	}

	@Override
	public int getItemEnchantability() {
		return 10;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		IChiselCompat compat;
		if (Loader.isModLoaded("chisel"))
			compat = new ChiselCompat();
		else
			compat = new ChiselCompatDummy();
		if (state.getBlock() instanceof BlockObsidian || compat.isObsidian(state)) {
			return 5000;
		}
		return super.getStrVsBlock(stack, state);
	}
}
