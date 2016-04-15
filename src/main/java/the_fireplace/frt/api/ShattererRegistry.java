package the_fireplace.frt.api;

import net.minecraft.item.ItemStack;
import the_fireplace.frt.recipes.PopFurnaceRecipes;
/**
 * 
 * @author The_Fireplace
 *
 */
public class ShattererRegistry {
	/**
	 * Adds a recipe to the Shatterer
	 * @param isIn
	 * 		The itemstack of the input, containing the item and metadata
	 * @param isOut
	 * 		The itemstack of the output, containing the item and metadata
	 * @param resultCount
	 * 		The number of items to output per input
	 */
	public static void registerPopFurnaceRecipe(ItemStack isIn, ItemStack isOut, int resultCount){
		PopFurnaceRecipes.instance().addPopFurnaceRecipe(isIn, isOut, resultCount);
	}
	/**
	 * Adds a recipe to the Shatterer
	 * @param isIn
	 * 		The itemstack of the input, containing the item and metadata
	 * @param isOut
	 * 		The itemstack of the output, containing the item, metadata, and amount to output
	 */
	public static void registerPopFurnaceRecipe(ItemStack isIn, ItemStack isOut){
		registerPopFurnaceRecipe(isIn, isOut, isOut.stackSize);
	}
	/**
	 * Registers items as "gunpowder" for the Shatterer
	 * @param items
	 * 		ItemStack(s) containing the item(s) to be registered
	 */
	public static void registerGunpowder(ItemStack... items){
		PopFurnaceRecipes.instance().addGunpowders(items);
	}
	/**
	 * Registers items as firestarters for the Shatterer
	 * @param items
	 * 		ItemStack(s) containing the item(s) to be registered
	 */
	public static void registerFirestarter(ItemStack... items){
		PopFurnaceRecipes.instance().addFirestarters(items);
	}
}
