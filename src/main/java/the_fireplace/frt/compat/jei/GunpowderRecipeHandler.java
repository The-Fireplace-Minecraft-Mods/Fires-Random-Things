package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class GunpowderRecipeHandler implements IRecipeWrapperFactory<GunpowderRecipe> {

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull GunpowderRecipe recipe) {
		return recipe;
	}
}
