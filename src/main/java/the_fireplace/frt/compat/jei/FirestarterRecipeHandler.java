package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class FirestarterRecipeHandler implements IRecipeWrapperFactory<FirestarterRecipe> {

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull FirestarterRecipe recipe) {
		return recipe;
	}
}
