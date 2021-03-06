package the_fireplace.frt.compat.jei;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author The_Fireplace
 */
public class GunpowderRecipe implements IRecipeWrapper {
	@Nonnull
	private final List<List<ItemStack>> inputs;

	public GunpowderRecipe(@Nonnull Collection<ItemStack> input) {
		List<ItemStack> inputList = new ArrayList<>(input);
		this.inputs = Collections.singletonList(inputList);
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, inputs);
	}
}
