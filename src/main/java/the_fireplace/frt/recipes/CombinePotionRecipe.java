package the_fireplace.frt.recipes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import the_fireplace.frt.items.ItemMeatPie;

@MethodsReturnNonnullByDefault
public class CombinePotionRecipe extends ShapelessRecipes {
	public CombinePotionRecipe(String group, ItemStack output, NonNullList<Ingredient> ingredients) {
		super(group, output, ingredients);
	}

	@Override
	public String getGroup() {
		return "all";
	}

	@Override
	public boolean isHidden() {
		return false;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		ItemStack out = this.getRecipeOutput().copy();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).hasTagCompound() && inv.getStackInSlot(i).getItem() instanceof ItemMeatPie) {
				out.setTagCompound(inv.getStackInSlot(i).getTagCompound());
				break;
			}
		}
		if (!out.hasTagCompound())
			out.setTagCompound(new NBTTagCompound());
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).hasTagCompound() && inv.getStackInSlot(i).getTagCompound().hasKey("Potion")) {
				if (!out.getTagCompound().hasKey("Effects"))
					out.getTagCompound().setTag("Effects", new NBTTagList());
				out.getTagCompound().getTagList("Effects", 10).appendTag(inv.getStackInSlot(i).writeToNBT(new NBTTagCompound()));
			}
		}
		return out;
	}

	public static class Factory implements IRecipeFactory {

		@Override
		public IRecipe parse(final JsonContext context, final JsonObject json) {
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = parseShapeless(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new CombinePotionRecipe(group.isEmpty() ? null : group, result, ingredients);
		}

		public static NonNullList<Ingredient> parseShapeless(final JsonContext context, final JsonObject json) {
			final NonNullList<Ingredient> ingredients = NonNullList.create();
			for (final JsonElement element : JsonUtils.getJsonArray(json, "ingredients"))
				ingredients.add(CraftingHelper.getIngredient(element, context));

			if (ingredients.isEmpty())
				throw new JsonParseException("No ingredients for shapeless recipe");

			return ingredients;
		}
	}
}
