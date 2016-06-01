package the_fireplace.frt.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.FRT;
import the_fireplace.frt.container.ContainerPopFurnace;
import the_fireplace.frt.gui.GuiPopFurnace;

/**
 * @author The_Fireplace
 */
@JEIPlugin
public class FRTJEIPlugin implements IModPlugin {

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
    }

    @Override
    public void register(IModRegistry registry) {
        IJeiHelpers helpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = helpers.getGuiHelper();
        registry.addRecipeCategories(new PopFurnaceCategory(guiHelper), new GunpowderCategory(guiHelper), new FirestarterCategory(guiHelper));
        registry.addRecipeHandlers(new PopFurnaceRecipeHandler(), new FirestarterRecipeHandler(), new GunpowderRecipeHandler());

        registry.addRecipeClickArea(GuiPopFurnace.class, 79, 28, 90, 4, "frt.pop_furnace");
        registry.addRecipeClickArea(GuiPopFurnace.class, 25, 11, 18, 18, "frt.pop_furnace.gunpowder");
        registry.addRecipeClickArea(GuiPopFurnace.class, 25, 33, 18, 18, "frt.pop_furnace.firestarter");

        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        recipeTransferRegistry.addRecipeTransferHandler(ContainerPopFurnace.class, "frt.pop_furnace", 0, 5, 0, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerPopFurnace.class, "frt.pop_furnace.gunpowder", 10, 1, 0, 36);
        recipeTransferRegistry.addRecipeTransferHandler(ContainerPopFurnace.class, "frt.pop_furnace.firestarter", 11, 1, 0, 36);

        registry.addRecipeCategoryCraftingItem(new ItemStack(FRT.pop_furnace), "frt.pop_furnace", "frt.pop_furnace.gunpowder", "frt.pop_furnace.firestarter");

        registry.addRecipes(PoppingRecipeMaker.getPoppingRecipes(helpers));
        registry.addRecipes(PoppingRecipeMaker.getGunpowders(helpers));
        registry.addRecipes(PoppingRecipeMaker.getFirestarters(helpers));
    }
}
