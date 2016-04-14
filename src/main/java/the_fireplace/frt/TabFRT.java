package the_fireplace.frt;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
/**
 *
 * @author The_Fireplace
 *
 */
public class TabFRT extends CreativeTabs {
	public TabFRT() {
		super("frt");
	}

	@Override
	public Item getTabIconItem() {
		return Item.getItemFromBlock(FRT.quad_dispenser);
	}
}
