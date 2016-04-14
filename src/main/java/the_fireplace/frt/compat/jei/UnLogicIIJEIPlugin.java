package the_fireplace.frt.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import the_fireplace.frt.container.ContainerPopFurnace;

/**
 * @author The_Fireplace
 */
@JEIPlugin
public class UnLogicIIJEIPlugin implements IModPlugin {

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {}

	@Override
	public void register(IModRegistry registry) {
		IJeiHelpers helpers = registry.getJeiHelpers();
		IGuiHelper guiHelper = helpers.getGuiHelper();
		registry.addRecipeCategories(new PopFurnaceCategory(guiHelper), new GunpowderCategory(guiHelper), new FirestarterCategory(guiHelper));
		registry.addRecipeHandlers(new PopFurnaceRecipeHandler(), new FirestarterRecipeHandler(), new GunpowderRecipeHandler());

		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		recipeTransferRegistry.addRecipeTransferHandler(ContainerPopFurnace.class, "frt.pop_furnace", 0, 5, 0, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerPopFurnace.class, "frt.pop_furnace.gunpowder", 10, 1, 0, 36);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerPopFurnace.class, "frt.pop_furnace.firestarter", 11, 1, 0, 36);

		registry.addRecipes(PoppingRecipeMaker.getPoppingRecipes(helpers));
		registry.addRecipes(PoppingRecipeMaker.getGunpowders(helpers));
		registry.addRecipes(PoppingRecipeMaker.getFirestarters(helpers));
	}
}
