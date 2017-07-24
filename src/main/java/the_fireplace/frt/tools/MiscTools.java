package the_fireplace.frt.tools;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;

public final class MiscTools {
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

	public static ItemStack createHead(String name){
		ItemStack stack = new ItemStack(Items.SKULL, 1, 3);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setTag("SkullOwner", new NBTTagString(name));
		stack.setTagCompound(compound);
		return stack;
	}

	public static ItemStack createGiftBox(String group){
		ItemStack stack = new ItemStack(FRT.gift_box);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setTag("GiftId", new NBTTagString(group));
		stack.setTagCompound(compound);
		return stack;
	}
}
