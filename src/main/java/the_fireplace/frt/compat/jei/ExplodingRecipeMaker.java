package the_fireplace.frt.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.recipes.ItemExploderRecipeManager;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author The_Fireplace
 */
public class ExplodingRecipeMaker {
	@Nonnull
	public static List<ItemExploderRecipe> getPoppingRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		ItemExploderRecipeManager furnaceRecipes = ItemExploderRecipeManager.instance();
		Map<ItemStack, ItemStack> poppingMap = furnaceRecipes.getPoppingList();

		List<ItemExploderRecipe> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> itemStackItemStackEntry : poppingMap.entrySet()) {
			ItemStack input = itemStackItemStackEntry.getKey();
			ItemStack output = itemStackItemStackEntry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			ItemExploderRecipe recipe = new ItemExploderRecipe(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}

	@Nonnull
	public static List<FirestarterRecipe> getFirestarters(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		ItemExploderRecipeManager furnaceRecipes = ItemExploderRecipeManager.instance();
		ArrayList<ItemStack> firestarters = furnaceRecipes.getFirestarters();

		List<FirestarterRecipe> recipes = new ArrayList<>();

		for (ItemStack stack : firestarters) {
			List<ItemStack> inputs = stackHelper.getSubtypes(stack);
			FirestarterRecipe recipe = new FirestarterRecipe(inputs);
			recipes.add(recipe);
		}

		return recipes;
	}

	@Nonnull
	public static List<GunpowderRecipe> getGunpowders(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		ItemExploderRecipeManager furnaceRecipes = ItemExploderRecipeManager.instance();
		ArrayList<ItemStack> gunpowders = furnaceRecipes.getGunpowders();

		List<GunpowderRecipe> recipes = new ArrayList<>();

		for (ItemStack stack : gunpowders) {
			List<ItemStack> inputs = stackHelper.getSubtypes(stack);
			GunpowderRecipe recipe = new GunpowderRecipe(inputs);
			recipes.add(recipe);
		}

		return recipes;
	}
}
