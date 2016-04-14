package the_fireplace.frt.recipes;

import the_fireplace.frt.api.PopFurnaceRegistry;
import the_fireplace.frt.libs.tools.MIDLib;
/**
 * 
 * @author The_Fireplace
 *
 */
public class DefaultRecipes extends VanillaStacks implements IRecipeRegister {
	@Override
	public void registerRecipes() {
		if(!MIDLib.hasBaseMetals()){//in any mod adding gold dust, replace with the appropriate amount of gold dust instead of nuggets
			PopFurnaceRegistry.registerPopFurnaceRecipe(goldenAppleStack, goldIngotStack, 8);
			PopFurnaceRegistry.registerPopFurnaceRecipe(glisteringMelonStack, goldNuggetStack, 8);
			PopFurnaceRegistry.registerPopFurnaceRecipe(goldenCarrotStack, goldNuggetStack, 8);
		}
	}
}
