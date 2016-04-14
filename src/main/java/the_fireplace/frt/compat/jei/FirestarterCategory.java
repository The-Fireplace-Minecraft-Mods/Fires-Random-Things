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
public class FirestarterCategory implements IRecipeCategory {
	@Nonnull
	private final IDrawable background;

	public FirestarterCategory(IGuiHelper guiHelper) {
		super();
		ResourceLocation location = new ResourceLocation("frt", "textures/gui/nei_pop_furnace.png");
		background = guiHelper.createDrawable(location, 3, 15, 169, 53);
	}

	@Nonnull
	@Override
	public String getUid() {
		return "frt.pop_furnace.firestarter";
	}

	@Nonnull
	@Override
	public String getTitle() {
		return I18n.translateToLocal("jei.firestarter");
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

		int lighterSlot = 11;

		guiItemStacks.init(lighterSlot, true, 4, 29);
		guiItemStacks.setFromRecipe(lighterSlot, recipeWrapper.getInputs());
	}
}
