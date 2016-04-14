package the_fireplace.frt.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class PopFurnaceCategory implements IRecipeCategory {
	@Nonnull
	private final IDrawable background;

	public PopFurnaceCategory(IGuiHelper guiHelper) {
		super();
		ResourceLocation location = new ResourceLocation("frt", "textures/gui/nei_pop_furnace.png");
		background = guiHelper.createDrawable(location, 3, 15, 169, 53);
	}

	@Nonnull
	@Override
	public String getUid() {
		return "frt.pop_furnace";
	}

	@Nonnull
	@Override
	public String getTitle() {
		return I18n.translateToLocal("jei.pop_furnace");
	}

	@Nonnull
	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) {

	}

	@Override
	public void drawAnimations(Minecraft minecraft) {

	}

	@Override
	public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull IRecipeWrapper recipeWrapper) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		int inputSlot = 0;
		int outputSlot = 5;

		guiItemStacks.init(inputSlot, true, 76, 7);
		guiItemStacks.init(outputSlot, false, 76, 29);

		guiItemStacks.setFromRecipe(inputSlot, recipeWrapper.getInputs());
		guiItemStacks.setFromRecipe(outputSlot, recipeWrapper.getOutputs());
	}
}
