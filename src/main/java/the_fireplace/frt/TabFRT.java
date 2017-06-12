package the_fireplace.frt;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class TabFRT extends CreativeTabs {
	public TabFRT() {
		super("frt");
	}

	@Override
	@Nonnull
	public ItemStack getTabIconItem() {
		return new ItemStack(FRT.quad_dispenser);
	}
}
