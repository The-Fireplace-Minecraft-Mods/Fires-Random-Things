package the_fireplace.frt.items;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class ItemLeafcutter extends Item {
    public ItemLeafcutter() {
        super();
        setUnlocalizedName("leafcutter");
        setCreativeTab(FRT.TabFRT);
        setMaxDamage(256);
        setMaxStackSize(1);
        setFull3D();
    }

    @Override
    public boolean hitEntity(ItemStack is, EntityLivingBase entityHit, EntityLivingBase attacker) {
        is.damageItem(2, attacker);
        entityHit.attackEntityFrom(DamageSource.generic, 2);
        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack is, World world, IBlockState blockIn, BlockPos pos, EntityLivingBase entity) {
        if ((double) blockIn.getBlockHardness(world, pos) != 0) {
            is.damageItem(1, entity);
        }
        return blockIn.getMaterial() == Material.leaves || super.onBlockDestroyed(is, world, blockIn, pos, entity);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    public int getItemEnchantability() {
        return 5;
    }

    @Override
    public float getStrVsBlock(ItemStack stack, IBlockState state) {
        if (state.getBlock() instanceof BlockLeaves) {
            return 5000;
        }
        return super.getStrVsBlock(stack, state);
    }
}
