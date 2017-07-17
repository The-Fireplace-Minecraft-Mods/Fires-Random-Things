package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class ItemExploderRecipeHandler implements IRecipeWrapperFactory<ItemExploderRecipe> {

	@Override
	@Nonnull
	public IRecipeWrapper getRecipeWrapper(@Nonnull ItemExploderRecipe recipe) {
		return recipe;
	}
}
