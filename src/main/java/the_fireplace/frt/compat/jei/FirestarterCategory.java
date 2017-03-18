package the_fireplace.frt.compat.jei;

import com.google.common.collect.Lists;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {

    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        int lighterSlot = 11;

        guiItemStacks.init(lighterSlot, true, 4, 29);
        guiItemStacks.set(ingredients);
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return Lists.newArrayList();
    }
}
