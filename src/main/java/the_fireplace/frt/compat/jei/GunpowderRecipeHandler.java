package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class GunpowderRecipeHandler implements IRecipeHandler<GunpowderRecipe> {
	@Nonnull
	@Override
	public Class<GunpowderRecipe> getRecipeClass() {
		return GunpowderRecipe.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return "frt.pop_furnace.gunpowder";
	}

	@Nonnull
	@Override
	public IRecipeWrapper getRecipeWrapper(@Nonnull GunpowderRecipe recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull GunpowderRecipe recipe) {
		return recipe.getInputs().size() > 0 && recipe.getOutputs().size() == 0;
	}
}
