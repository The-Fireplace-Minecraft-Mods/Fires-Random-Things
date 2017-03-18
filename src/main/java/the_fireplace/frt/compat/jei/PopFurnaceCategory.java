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
import the_fireplace.frt.FRT;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

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
        return FRT.proxy.translateToLocal("jei.pop_furnace");
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

        int inputSlot = 0;
        int outputSlot = 5;

        guiItemStacks.init(inputSlot, true, 76, 7);
        guiItemStacks.init(outputSlot, false, 76, 29);

        guiItemStacks.set(ingredients);
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return Lists.newArrayList();
    }
}
