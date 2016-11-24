package the_fireplace.frt.compat.jei;

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

/**
 * @author The_Fireplace
 */
public class GunpowderCategory implements IRecipeCategory {
    @Nonnull
    private final IDrawable background;

    public GunpowderCategory(IGuiHelper guiHelper) {
        super();
        ResourceLocation location = new ResourceLocation("frt", "textures/gui/nei_pop_furnace.png");
        background = guiHelper.createDrawable(location, 3, 15, 169, 53);
    }

    @Nonnull
    @Override
    public String getUid() {
        return "frt.pop_furnace.gunpowder";
    }

    @Nonnull
    @Override
    public String getTitle() {
        return I18n.translateToLocal("jei.gunpowder");
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

        int powderSlot = 10;

        guiItemStacks.init(powderSlot, true, 4, 7);
        guiItemStacks.set(ingredients);
    }
}
