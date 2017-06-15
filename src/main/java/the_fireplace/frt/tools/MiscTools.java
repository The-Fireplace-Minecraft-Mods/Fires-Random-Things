package the_fireplace.frt.tools;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;

public class MiscTools {
	public static boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
		return is2.getItem() == is1.getItem() && (is2.getMetadata() == 32767 || is2.getMetadata() == is1.getMetadata());
	}

	public static void spawnItemAtPos(ItemStack stack, World world, BlockPos pos) {
		if (ArrayUtils.contains(ConfigValues.DISABLEDITEMS, stack.getItem().getRegistryName()))
			return;
		EntityItem entityitem = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
		if (!world.isRemote)
			world.spawnEntity(entityitem);
	}

	public static NBTTagString getLocalBookPage(String key) {
		return new NBTTagString("\"" + FRT.proxy.translateToLocal(key) + "\"");
	}
}
