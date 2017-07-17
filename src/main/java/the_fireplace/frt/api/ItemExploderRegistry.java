package the_fireplace.frt.api;

import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.recipes.ItemExploderRecipeManager;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author The_Fireplace
 */
@ParametersAreNonnullByDefault
public class ItemExploderRegistry {
	/**
	 * Adds a recipe to the Item Exploder
	 *
	 * @param isIn
	 * 		The itemstack of the input, containing the item and metadata
	 * @param isOut
	 * 		The itemstack of the output, containing the item and metadata
	 * @param resultCount
	 * 		The number of items to output per input
	 */
	public static void registerExploderRecipe(ItemStack isIn, ItemStack isOut, int resultCount) {
		if (isIn.isEmpty() || isOut.isEmpty() || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, isIn.getItem().getUnlocalizedName().substring(5)) || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, isOut.getItem().getUnlocalizedName().substring(5)))
			return;
		ItemExploderRecipeManager.instance().addPopFurnaceRecipe(isIn, isOut, resultCount);
	}

	/**
	 * Adds a recipe to the Item Exploder
	 *
	 * @param isIn
	 * 		The itemstack of the input, containing the item and metadata
	 * @param isOut
	 * 		The itemstack of the output, containing the item, metadata, and amount to output
	 */
	public static void registerExploderRecipe(ItemStack isIn, ItemStack isOut) {
		registerExploderRecipe(isIn, isOut, isOut.getCount());
	}

	/**
	 * Registers items as "explosive" for the Item Exploder.
	 * This is intended for small explosives, such as Gunpowder or maybe Dynamite, not larger explosives, such as TNT or Nukes.
	 *
	 * @param items
	 * 		ItemStack(s) containing the item(s) to be registered
	 */
	public static void registerExplosive(ItemStack... items) {
		for (ItemStack item : items) {
			if (item.isEmpty() || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, item.getItem().getUnlocalizedName().substring(5)))
				return;
		}
		ItemExploderRecipeManager.instance().addGunpowders(items);
	}

	/**
	 * Registers items as firestarters for the Item Exploder
	 *
	 * @param items
	 * 		ItemStack(s) containing the item(s) to be registered
	 */
	public static void registerFirestarter(ItemStack... items) {
		for (ItemStack item : items) {
			if (item.isEmpty() || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, item.getItem().getUnlocalizedName().substring(5)))
				return;
		}
		ItemExploderRecipeManager.instance().addFirestarters(items);
	}
}
