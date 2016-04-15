package the_fireplace.frt.tools;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MiscTools {
    public static boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
        return is2.getItem() == is1.getItem() && (is2.getMetadata() == 32767 || is2.getMetadata() == is1.getMetadata());
    }

    public static void spawnItemAtPos(ItemStack stack, World world, BlockPos pos) {
        EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
        if (!world.isRemote)
            world.spawnEntityInWorld(entityitem);
    }
}
