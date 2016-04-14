package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class PopFurnaceRecipeHandler implements IRecipeHandler<PopFurnaceRecipe> {
	@Override
	@Nonnull
	public Class<PopFurnaceRecipe> getRecipeClass() {
		return PopFurnaceRecipe.class;
	}

	@Nonnull
	@Override
	public String getRecipeCategoryUid() {
		return "frt.pop_furnace";
	}

	@Override
	@Nonnull
	public IRecipeWrapper getRecipeWrapper(@Nonnull PopFurnaceRecipe recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(@Nonnull PopFurnaceRecipe recipe) {
		return recipe.getInputs().size() != 0 && recipe.getOutputs().size() > 0;
	}
}
