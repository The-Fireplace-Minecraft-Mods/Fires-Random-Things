package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class FirestarterRecipeHandler implements IRecipeHandler<FirestarterRecipe> {
    @Nonnull
    @Override
    public Class<FirestarterRecipe> getRecipeClass() {
        return FirestarterRecipe.class;
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid() {
        return "frt.pop_furnace.firestarter";
    }

    @Nonnull
    @Override
    public String getRecipeCategoryUid(FirestarterRecipe recipe) {
        return "frt.pop_furnace.firestarter";
    }

    @Nonnull
    @Override
    public IRecipeWrapper getRecipeWrapper(@Nonnull FirestarterRecipe recipe) {
        return recipe;
    }

    @Override
    public boolean isRecipeValid(@Nonnull FirestarterRecipe recipe) {
        return recipe.getInputs().size() > 0 && recipe.getOutputs().size() == 0;
    }
}
