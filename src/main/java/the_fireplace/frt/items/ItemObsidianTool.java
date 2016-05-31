package the_fireplace.frt.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

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
        if (block.getBlock().equals(Blocks.OBSIDIAN)) {
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
        if (state.getBlock() == Blocks.OBSIDIAN) {
            return 5000;
        }
        return super.getStrVsBlock(stack, state);
    }
}
