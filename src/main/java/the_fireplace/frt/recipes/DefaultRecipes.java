package the_fireplace.frt.recipes;

import the_fireplace.frt.api.ShattererRegistry;
import the_fireplace.frt.tools.MIDLib;
/**
 * 
 * @author The_Fireplace
 *
 */
public class DefaultRecipes extends VanillaStacks implements IRecipeRegister {
	@Override
	public void registerRecipes() {
		if(!MIDLib.hasBaseMetals()){//in any mod adding gold dust, replace with the appropriate amount of gold dust instead of nuggets
			ShattererRegistry.registerPopFurnaceRecipe(goldenAppleStack, goldIngotStack, 8);
			ShattererRegistry.registerPopFurnaceRecipe(glisteringMelonStack, goldNuggetStack, 8);
			ShattererRegistry.registerPopFurnaceRecipe(goldenCarrotStack, goldNuggetStack, 8);
		}
	}
}
