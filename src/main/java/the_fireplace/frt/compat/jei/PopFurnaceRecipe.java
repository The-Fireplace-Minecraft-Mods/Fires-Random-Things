package the_fireplace.frt.compat.jei;

import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * @author The_Fireplace
 */
public class PopFurnaceRecipe extends BlankRecipeWrapper {
	@Nonnull
	private final List<List<ItemStack>> input;
	@Nonnull
	private final List<ItemStack> outputs;

	public PopFurnaceRecipe(@Nonnull List<ItemStack> input, @Nonnull ItemStack output) {
		this.input = Collections.singletonList(input);
		this.outputs = Collections.singletonList(output);
	}

	@Nonnull
	public List<List<ItemStack>> getInputs() {
		return input;
	}

	@Nonnull
	public List<ItemStack> getOutputs() {
		return outputs;
	}

	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {

	}
}
