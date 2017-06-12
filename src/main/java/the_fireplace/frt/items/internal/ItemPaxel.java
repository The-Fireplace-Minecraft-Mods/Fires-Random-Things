package the_fireplace.frt.items.internal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @author The_Fireplace
 */
public class ItemPaxel extends ItemTool {
	private static float damageVsEntity;
	private static float attackSpeed;

	public ItemPaxel(ToolMaterial p_i45333_2_) {
		super(damageVsEntity, attackSpeed, p_i45333_2_, new Set<Block>() {
			@Override
			public int size() {
				return 0;
			}

			@Override
			public boolean isEmpty() {
				return false;
			}

			@Override
			public boolean contains(Object o) {
				return false;
			}

			@Override
			public Iterator<Block> iterator() {
				return null;
			}

			@Override
			public Object[] toArray() {
				return new Object[0];
			}

			@Override
			public <T> T[] toArray(T[] a) {
				return null;
			}

			@Override
			public boolean add(Block block) {
				return false;
			}

			@Override
			public boolean remove(Object o) {
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean addAll(Collection<? extends Block> c) {
				return false;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				return false;
			}

			@Override
			public void clear() {

			}
		});
		setFull3D();
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn, ItemStack stack) {
		Block block = blockIn.getBlock();
		if (block == Blocks.OBSIDIAN) {
			return toolMaterial.getHarvestLevel() >= 3;
		}

		if (block == Blocks.DIAMOND_BLOCK || block == Blocks.DIAMOND_ORE) {
			return toolMaterial.getHarvestLevel() >= 2;
		}

		if (block == Blocks.GOLD_BLOCK || block == Blocks.GOLD_ORE) {
			return toolMaterial.getHarvestLevel() >= 2;
		}

		if (block == Blocks.IRON_BLOCK || block == Blocks.IRON_ORE) {
			return toolMaterial.getHarvestLevel() >= 1;
		}

		if (block == Blocks.LAPIS_BLOCK || block == Blocks.LAPIS_ORE) {
			return toolMaterial.getHarvestLevel() >= 1;
		}

		if (block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE) {
			return toolMaterial.getHarvestLevel() >= 2;
		}

		if (block == Blocks.ANVIL) {
			return toolMaterial.getHarvestLevel() >= 0;
		}

		return block == Blocks.SNOW || block == Blocks.SNOW_LAYER || blockIn.getMaterial() == Material.ROCK || blockIn.getMaterial() == Material.IRON;

	}

	@Override
	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
		par1ItemStack.damageItem(2, par3EntityLivingBase);
		return true;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		return state != Blocks.BEDROCK.getDefaultState() ? efficiencyOnProperMaterial : 1.0F;
	}
}
