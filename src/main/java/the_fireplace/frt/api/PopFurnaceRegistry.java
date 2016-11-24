package the_fireplace.frt.api;

import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.recipes.ShattererRecipes;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Shatterer Registry. Still called Pop Furnace Registry because that was the original name.
 * @author The_Fireplace
 */
@ParametersAreNonnullByDefault
public class PopFurnaceRegistry {
    /**
     * Adds a recipe to the Shatterer
     *
     * @param isIn        The itemstack of the input, containing the item and metadata
     * @param isOut       The itemstack of the output, containing the item and metadata
     * @param resultCount The number of items to output per input
     */
    public static void registerPopFurnaceRecipe(ItemStack isIn, ItemStack isOut, int resultCount) {
        if(isIn.isEmpty() || isOut.isEmpty() || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, isIn.getItem().getUnlocalizedName().substring(5)) || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, isOut.getItem().getUnlocalizedName().substring(5)))
            return;
        ShattererRecipes.instance().addPopFurnaceRecipe(isIn, isOut, resultCount);
    }

    /**
     * Adds a recipe to the Shatterer
     *
     * @param isIn  The itemstack of the input, containing the item and metadata
     * @param isOut The itemstack of the output, containing the item, metadata, and amount to output
     */
    public static void registerPopFurnaceRecipe(ItemStack isIn, ItemStack isOut) {
        registerPopFurnaceRecipe(isIn, isOut, isOut.getCount());
    }

    /**
     * Registers items as "gunpowder" for the Shatterer
     *
     * @param items ItemStack(s) containing the item(s) to be registered
     */
    public static void registerGunpowder(ItemStack... items) {
        for(int i=0;i<items.length;i++){
            if(items[i].isEmpty() || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, items[i].getItem().getUnlocalizedName().substring(5)))
                return;
        }
        ShattererRecipes.instance().addGunpowders(items);
    }

    /**
     * Registers items as firestarters for the Shatterer
     *
     * @param items ItemStack(s) containing the item(s) to be registered
     */
    public static void registerFirestarter(ItemStack... items) {
        for(int i=0;i<items.length;i++){
            if(items[i].isEmpty() || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, items[i].getItem().getUnlocalizedName().substring(5)))
                return;
        }
        ShattererRecipes.instance().addFirestarters(items);
    }
}
