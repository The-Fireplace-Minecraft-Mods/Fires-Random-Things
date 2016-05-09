package the_fireplace.frt.compat.jei;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.recipes.PopFurnaceRecipes;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author The_Fireplace
 */
public class PoppingRecipeMaker {
    @Nonnull
    public static List<PopFurnaceRecipe> getPoppingRecipes(IJeiHelpers helpers) {
        IStackHelper stackHelper = helpers.getStackHelper();
        PopFurnaceRecipes furnaceRecipes = PopFurnaceRecipes.instance();
        Map<ItemStack, ItemStack> poppingMap = furnaceRecipes.getPoppingList();

        List<PopFurnaceRecipe> recipes = new ArrayList<>();

        for (Map.Entry<ItemStack, ItemStack> itemStackItemStackEntry : poppingMap.entrySet()) {
            ItemStack input = itemStackItemStackEntry.getKey();
            ItemStack output = itemStackItemStackEntry.getValue();

            List<ItemStack> inputs = stackHelper.getSubtypes(input);
            PopFurnaceRecipe recipe = new PopFurnaceRecipe(inputs, output);
            recipes.add(recipe);
        }

        return recipes;
    }

    @Nonnull
    public static List<FirestarterRecipe> getFirestarters(IJeiHelpers helpers) {
        IStackHelper stackHelper = helpers.getStackHelper();
        PopFurnaceRecipes furnaceRecipes = PopFurnaceRecipes.instance();
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
        PopFurnaceRecipes furnaceRecipes = PopFurnaceRecipes.instance();
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
