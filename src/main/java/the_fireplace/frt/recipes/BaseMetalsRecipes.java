package the_fireplace.frt.recipes;

import cyano.basemetals.init.Items;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.api.PopFurnaceRegistry;
import the_fireplace.frt.compat.basemetals.RegisterBaseMetals;
/**
 * 
 * @author The_Fireplace
 *
 */
public class BaseMetalsRecipes extends VanillaStacks implements IRecipeRegister {
	ItemStack goldDustStack = new ItemStack(Items.gold_powder);
	ItemStack ironDustStack = new ItemStack(Items.iron_powder);
	ItemStack copperStack = new ItemStack(Items.copper_ingot);
	ItemStack silverStack = new ItemStack(Items.silver_ingot);
	ItemStack tinStack = new ItemStack(Items.tin_ingot);
	ItemStack leadStack = new ItemStack(Items.lead_ingot);
	ItemStack nickelStack = new ItemStack(Items.nickel_ingot);
	ItemStack bronzeStack = new ItemStack(Items.bronze_ingot);
	ItemStack brassStack = new ItemStack(Items.brass_ingot);
	ItemStack steelStack = new ItemStack(Items.steel_ingot);
	ItemStack invarStack = new ItemStack(Items.invar_ingot);
	ItemStack electrumStack = new ItemStack(Items.electrum_ingot);
	ItemStack coldironStack = new ItemStack(Items.coldiron_ingot);
	ItemStack mithrilStack = new ItemStack(Items.mithril_ingot);
	ItemStack adamantineStack = new ItemStack(Items.adamantine_ingot);
	ItemStack starsteelStack = new ItemStack(Items.starsteel_ingot);
	ItemStack aquariumStack = new ItemStack(Items.aquarium_ingot);

	ItemStack copperPaxelStack = new ItemStack(RegisterBaseMetals.copper_paxel);
	ItemStack silverPaxelStack = new ItemStack(RegisterBaseMetals.silver_paxel);
	ItemStack tinPaxelStack = new ItemStack(RegisterBaseMetals.tin_paxel);
	ItemStack leadPaxelStack = new ItemStack(RegisterBaseMetals.lead_paxel);
	ItemStack nickelPaxelStack = new ItemStack(RegisterBaseMetals.nickel_paxel);
	ItemStack bronzePaxelStack = new ItemStack(RegisterBaseMetals.bronze_paxel);
	ItemStack brassPaxelStack = new ItemStack(RegisterBaseMetals.brass_paxel);
	ItemStack steelPaxelStack = new ItemStack(RegisterBaseMetals.steel_paxel);
	ItemStack invarPaxelStack = new ItemStack(RegisterBaseMetals.invar_paxel);
	ItemStack electrumPaxelStack = new ItemStack(RegisterBaseMetals.electrum_paxel);
	ItemStack coldironPaxelStack = new ItemStack(RegisterBaseMetals.cold_iron_paxel);
	ItemStack mithrilPaxelStack = new ItemStack(RegisterBaseMetals.mithril_paxel);
	ItemStack adamantinePaxelStack = new ItemStack(RegisterBaseMetals.adamantine_paxel);
	ItemStack starsteelPaxelStack = new ItemStack(RegisterBaseMetals.star_steel_paxel);
	ItemStack aquariumPaxelStack = new ItemStack(RegisterBaseMetals.aquarium_paxel);

	@Override
	public void registerRecipes() {
		PopFurnaceRegistry.registerPopFurnaceRecipe(goldenAppleStack, goldDustStack, 8);
		PopFurnaceRegistry.registerPopFurnaceRecipe(glisteringMelonStack, goldDustStack, 1);
		PopFurnaceRegistry.registerPopFurnaceRecipe(goldenCarrotStack, goldDustStack, 1);
		//Mod-only recipes
		PopFurnaceRegistry.registerPopFurnaceRecipe(goldIngotStack, goldDustStack, 1);
		PopFurnaceRegistry.registerPopFurnaceRecipe(ironStack, ironDustStack, 1);

		shaped(adamantinePaxelStack, "iii", " i ", " s ", 'i', adamantineStack, 's', "stickWood");
		shaped(copperPaxelStack, "iii", " i ", " s ", 'i', copperStack, 's', "stickWood");
		shaped(silverPaxelStack, "iii", " i ", " s ", 'i', silverStack, 's', "stickWood");
		shaped(tinPaxelStack, "iii", " i ", " s ", 'i', tinStack, 's', "stickWood");
		shaped(leadPaxelStack, "iii", " i ", " s ", 'i', leadStack, 's', "stickWood");
		shaped(nickelPaxelStack, "iii", " i ", " s ", 'i', nickelStack, 's', "stickWood");
		shaped(bronzePaxelStack, "iii", " i ", " s ", 'i', bronzeStack, 's', "stickWood");
		shaped(brassPaxelStack, "iii", " i ", " s ", 'i', brassStack, 's', "stickWood");
		shaped(steelPaxelStack, "iii", " i ", " s ", 'i', steelStack, 's', "stickWood");
		shaped(invarPaxelStack, "iii", " i ", " s ", 'i', invarStack, 's', "stickWood");
		shaped(electrumPaxelStack, "iii", " i ", " s ", 'i', electrumStack, 's', "stickWood");
		shaped(coldironPaxelStack, "iii", " i ", " s ", 'i', coldironStack, 's', "stickWood");
		shaped(mithrilPaxelStack, "iii", " i ", " s ", 'i', mithrilStack, 's', "stickWood");
		shaped(starsteelPaxelStack, "iii", " i ", " s ", 'i', starsteelStack, 's', "stickWood");
		shaped(aquariumPaxelStack, "iii", " i ", " s ", 'i', aquariumStack, 's', "stickWood");
	}

}
