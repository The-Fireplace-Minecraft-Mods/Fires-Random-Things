package the_fireplace.frt.recipes;

import cyano.basemetals.init.Items;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.api.ShattererRegistry;
import the_fireplace.frt.compat.basemetals.RegisterBaseMetals;

/**
 * @author The_Fireplace
 */
public class BaseMetalsRecipes extends VanillaStacks implements IRecipeRegister {
    ItemStack goldDustStack = new ItemStack(Items.gold_powder);
    ItemStack ironDustStack = new ItemStack(Items.iron_powder);

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
        ShattererRegistry.registerPopFurnaceRecipe(goldenAppleStack, goldDustStack, 8);
        ShattererRegistry.registerPopFurnaceRecipe(glisteringMelonStack, goldDustStack, 1);
        ShattererRegistry.registerPopFurnaceRecipe(goldenCarrotStack, goldDustStack, 1);
        //Mod-only recipes
        ShattererRegistry.registerPopFurnaceRecipe(goldIngotStack, goldDustStack, 1);
        ShattererRegistry.registerPopFurnaceRecipe(ironStack, ironDustStack, 1);

        shaped(adamantinePaxelStack, "iii", " i ", " s ", 'i', "ingotAdamantine", 's', "stickWood");
        shaped(copperPaxelStack, "iii", " i ", " s ", 'i', "ingotCopper", 's', "stickWood");
        shaped(silverPaxelStack, "iii", " i ", " s ", 'i', "ingotSilver", 's', "stickWood");
        shaped(tinPaxelStack, "iii", " i ", " s ", 'i', "ingotTin", 's', "stickWood");
        shaped(leadPaxelStack, "iii", " i ", " s ", 'i', "ingotLead", 's', "stickWood");
        shaped(nickelPaxelStack, "iii", " i ", " s ", 'i', "ingotNickel", 's', "stickWood");
        shaped(bronzePaxelStack, "iii", " i ", " s ", 'i', "ingotBronze", 's', "stickWood");
        shaped(brassPaxelStack, "iii", " i ", " s ", 'i', "ingotBrass", 's', "stickWood");
        shaped(steelPaxelStack, "iii", " i ", " s ", 'i', "ingotSteel", 's', "stickWood");
        shaped(invarPaxelStack, "iii", " i ", " s ", 'i', "ingotInvar", 's', "stickWood");
        shaped(electrumPaxelStack, "iii", " i ", " s ", 'i', "ingotElectrum", 's', "stickWood");
        shaped(coldironPaxelStack, "iii", " i ", " s ", 'i', "ingotColdiron", 's', "stickWood");
        shaped(mithrilPaxelStack, "iii", " i ", " s ", 'i', "ingotMithril", 's', "stickWood");
        shaped(starsteelPaxelStack, "iii", " i ", " s ", 'i', "ingotStarsteel", 's', "stickWood");
        shaped(aquariumPaxelStack, "iii", " i ", " s ", 'i', "ingotAquarium", 's', "stickWood");
    }

}
