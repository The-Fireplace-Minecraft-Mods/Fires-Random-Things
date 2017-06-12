package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class PopFurnaceRecipeHandler implements IRecipeWrapperFactory<PopFurnaceRecipe> {

    @Override
    @Nonnull
    public IRecipeWrapper getRecipeWrapper(@Nonnull PopFurnaceRecipe recipe) {
        return recipe;
    }
}
