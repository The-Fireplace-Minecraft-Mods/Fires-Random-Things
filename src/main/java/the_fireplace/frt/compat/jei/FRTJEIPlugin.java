package the_fireplace.frt.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.FRT;
import the_fireplace.frt.client.gui.GuiItemExploder;
import the_fireplace.frt.container.ContainerItemExploder;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author The_Fireplace
 */
@JEIPlugin
@ParametersAreNonnullByDefault
public class FRTJEIPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
	}

	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {

	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {

	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new ItemExploderCategory(guiHelper), new GunpowderCategory(guiHelper), new FirestarterCategory(guiHelper));
	}

	@Override
	public void register(IModRegistry registry) {
		IJeiHelpers helpers = registry.getJeiHelpers();
		registry.handleRecipes(FirestarterRecipe.class, new FirestarterRecipeHandler(), "frt.pop_furnace.firestarter");
		registry.handleRecipes(ItemExploderRecipe.class, new ItemExploderRecipeHandler(), "frt.pop_furnace");
		registry.handleRecipes(GunpowderRecipe.class, new GunpowderRecipeHandler(), "frt.pop_furnace.gunpowder");

		registry.addRecipeClickArea(GuiItemExploder.class, 79, 28, 90, 4, "frt.pop_furnace");
		registry.addRecipeClickArea(GuiItemExploder.class, 25, 11, 18, 18, "frt.pop_furnace.gunpowder");
		registry.addRecipeClickArea(GuiItemExploder.class, 25, 33, 18, 18, "frt.pop_furnace.firestarter");

		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		recipeTransferRegistry.addRecipeTransferHandler(ContainerItemExploder.class, "frt.pop_furnace", 0, 5, 0, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerItemExploder.class, "frt.pop_furnace.gunpowder", 10, 1, 0, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerItemExploder.class, "frt.pop_furnace.firestarter", 11, 1, 0, 36);

		registry.addRecipeCatalyst(new ItemStack(FRT.item_exploder), "frt.pop_furnace", "frt.pop_furnace.gunpowder", "frt.pop_furnace.firestarter");

		registry.addRecipes(ExplodingRecipeMaker.getPoppingRecipes(helpers), "frt.pop_furnace");
		registry.addRecipes(ExplodingRecipeMaker.getGunpowders(helpers), "frt.pop_furnace.gunpowder");
		registry.addRecipes(ExplodingRecipeMaker.getFirestarters(helpers), "frt.pop_furnace.firestarter");
	}
}
