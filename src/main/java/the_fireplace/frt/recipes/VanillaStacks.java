package the_fireplace.frt.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import the_fireplace.frt.FRT;
import the_fireplace.frt.api.ShattererRegistry;
import the_fireplace.frt.tools.MIDLib;

/**
 * @author The_Fireplace
 */
public class VanillaStacks {
    //Vanilla Blocks
    static ItemStack alliumStack = new ItemStack(Blocks.red_flower, 1, 2);
    static ItemStack azureBluetStack = new ItemStack(Blocks.red_flower, 1, 3);
    static ItemStack blueOrchidStack = new ItemStack(Blocks.red_flower, 1, 1);
    static ItemStack bookshelfStack = new ItemStack(Blocks.bookshelf);
    static ItemStack cobbleStack = new ItemStack(Blocks.cobblestone);
    static ItemStack dandelionStack = new ItemStack(Blocks.yellow_flower, 1, 0);
    static ItemStack dirtStack = new ItemStack(Blocks.dirt);
    static ItemStack dirtStack9 = new ItemStack(Blocks.dirt, 9);
    static ItemStack dispenserStack = new ItemStack(Blocks.dispenser);
    static ItemStack glassStack = new ItemStack(Blocks.glass);
    static ItemStack glowstoneStack = new ItemStack(Blocks.glowstone);
    static ItemStack ironTrapdoorStack = new ItemStack(Blocks.iron_trapdoor);
    static ItemStack lilacStack = new ItemStack(Blocks.double_plant, 1, 1);
    static ItemStack netherrackStack = new ItemStack(Blocks.netherrack);
    static ItemStack orangeTulipStack = new ItemStack(Blocks.red_flower, 1, 5);
    static ItemStack oxeyeDaisyStack = new ItemStack(Blocks.red_flower, 1, 8);
    static ItemStack peonyStack = new ItemStack(Blocks.double_plant, 1, 5);
    static ItemStack pinkTulipStack = new ItemStack(Blocks.red_flower, 1, 7);
    static ItemStack poppyStack = new ItemStack(Blocks.red_flower, 1, 0);
    static ItemStack redTulipStack = new ItemStack(Blocks.red_flower, 1, 4);
    static ItemStack roseBushStack = new ItemStack(Blocks.double_plant, 1, 4);
    static ItemStack sandStack = new ItemStack(Blocks.sand);
    static ItemStack stainedGlassStack = new ItemStack(Blocks.stained_glass, 1, OreDictionary.WILDCARD_VALUE);
    static ItemStack stoneStack = new ItemStack(Blocks.stone);
    static ItemStack stoneSlabStack = new ItemStack(Blocks.stone_slab);
    static ItemStack stoneSlabStack2 = new ItemStack(Blocks.stone_slab, 2);
    static ItemStack sunflowerStack = new ItemStack(Blocks.double_plant, 1, 0);
    static ItemStack whiteTulipStack = new ItemStack(Blocks.red_flower, 1, 6);
    static ItemStack woolStack = new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE);
    //Vanilla Items
    static ItemStack awkwardPotionStack = new ItemStack(Items.potionitem, 1, 16);
    static ItemStack blazePowderStack = new ItemStack(Items.blaze_powder);
    static ItemStack blazeRodStack = new ItemStack(Items.blaze_rod);
    static ItemStack boneStack = new ItemStack(Items.bone);
    static ItemStack boneMealStack = new ItemStack(Items.dye, 1, 15);
    static ItemStack bottleStack = new ItemStack(Items.glass_bottle);
    static ItemStack brickStack = new ItemStack(Items.brick);
    static ItemStack cakeStack = new ItemStack(Items.cake);
    static ItemStack coalStack = new ItemStack(Items.coal);
    static ItemStack diamondStack = new ItemStack(Items.diamond);
    static ItemStack fireChargeStack = new ItemStack(Items.fire_charge);
    static ItemStack flintStack = new ItemStack(Items.flint);
    static ItemStack flintAndSteelStack = new ItemStack(Items.flint_and_steel);
    static ItemStack glisteringMelonStack = new ItemStack(Items.speckled_melon);
    static ItemStack glowstoneDustStack = new ItemStack(Items.glowstone_dust);
    static ItemStack goldIngotStack = new ItemStack(Items.gold_ingot);
    static ItemStack goldNuggetStack = new ItemStack(Items.gold_nugget);
    static ItemStack goldenAppleStack = new ItemStack(Items.golden_apple);
    static ItemStack goldenCarrotStack = new ItemStack(Items.golden_carrot);
    static ItemStack gunpowderStack = new ItemStack(Items.gunpowder);
    static ItemStack ironStack = new ItemStack(Items.iron_ingot);
    static ItemStack leatherStack = new ItemStack(Items.leather);
    static ItemStack magentaDyeStack = new ItemStack(Items.dye, 1, 13);
    static ItemStack magmaCreamStack = new ItemStack(Items.magma_cream);
    static ItemStack orangeDyeStack = new ItemStack(Items.dye, 1, 14);
    static ItemStack paperStack = new ItemStack(Items.paper);
    static ItemStack pinkDyeStack = new ItemStack(Items.dye, 1, 9);
    static ItemStack redDyeStack = new ItemStack(Items.dye, 1, 1);
    static ItemStack redMushroomStack = new ItemStack(Blocks.red_mushroom);
    static ItemStack redstoneStack = new ItemStack(Items.redstone);
    static ItemStack shearsStack = new ItemStack(Items.shears);
    static ItemStack silverDyeStack = new ItemStack(Items.dye, 1, 7);
    static ItemStack skyDyeStack = new ItemStack(Items.dye, 1, 12);
    static ItemStack slimeballStack = new ItemStack(Items.slime_ball);
    static ItemStack stringStack = new ItemStack(Items.string);
    static ItemStack sugarStack = new ItemStack(Items.sugar);
    static ItemStack sugarCaneStack = new ItemStack(Items.reeds);
    static ItemStack yellowDyeStack = new ItemStack(Items.dye, 1, 11);
    //Custom Blocks
    static ItemStack blackScreenStack = new ItemStack(FRT.black_screen);
    static ItemStack blazeCakeStack = new ItemStack(FRT.blaze_cake);
    static ItemStack blueScreenStack = new ItemStack(FRT.blue_screen);
    static ItemStack brownScreenStack = new ItemStack(FRT.brown_screen);
    static ItemStack candleStack = new ItemStack(FRT.candle);
    static ItemStack candlePlateStack = new ItemStack(FRT.candle_with_base);
    static ItemStack chargedCoalBlockStack = new ItemStack(FRT.charged_coal_block);
    static ItemStack compactBookshelfStack = new ItemStack(FRT.compact_bookshelf);
    static ItemStack compactDirtStack = new ItemStack(FRT.compact_dirt);
    static ItemStack cyanScreenStack = new ItemStack(FRT.cyan_screen);
    static ItemStack darkTanScreenStack = new ItemStack(FRT.dark_tan_screen);
    static ItemStack destabilizedCoalBlockStack = new ItemStack(FRT.destabilized_coal_block);
    static ItemStack fireplaceBottomStack2 = new ItemStack(FRT.fireplace_bottom, 2);
    static ItemStack fossilStack = new ItemStack(FRT.fossil);
    static ItemStack greenScreenStack = new ItemStack(FRT.green_screen);
    static ItemStack greyScreenStack = new ItemStack(FRT.grey_screen);
    static ItemStack insaneDispenserStack = new ItemStack(FRT.insane_dispenser);
    static ItemStack lightOrangeScreenStack = new ItemStack(FRT.light_orange_screen);
    static ItemStack lightTanScreenStack = new ItemStack(FRT.light_tan_screen);
    static ItemStack limeScreenStack = new ItemStack(FRT.lime_screen);
    static ItemStack magentaScreenStack = new ItemStack(FRT.magenta_screen);
    static ItemStack orangeScreenStack = new ItemStack(FRT.orange_screen);
    static ItemStack pinkScreenStack = new ItemStack(FRT.pink_screen);
    static ItemStack polishedStoneStack = new ItemStack(FRT.polished_stone);
    static ItemStack polishedStoneStack2 = new ItemStack(FRT.polished_stone, 2);
    static ItemStack popFurnaceStack = new ItemStack(FRT.pop_furnace);
    static ItemStack purpleScreenStack = new ItemStack(FRT.purple_screen);
    static ItemStack quadDispenserStack = new ItemStack(FRT.quad_dispenser);
    static ItemStack redScreenStack = new ItemStack(FRT.red_screen);
    static ItemStack refinedCoalBlockStack = new ItemStack(FRT.refined_coal_block);
    static ItemStack restabilizedCoalBlockStack = new ItemStack(FRT.restabilized_coal_block);
    static ItemStack shellCoreStack = new ItemStack(FRT.shell_core);
    static ItemStack silverScreenStack = new ItemStack(FRT.silver_screen);
    static ItemStack skyScreenStack = new ItemStack(FRT.sky_screen);
    static ItemStack whiteScreenStack = new ItemStack(FRT.white_screen);
    static ItemStack yellowScreenStack = new ItemStack(FRT.yellow_screen);
    //Custom Items
    static ItemStack chargedCoalStack = new ItemStack(FRT.charged_coal);
    static ItemStack chargedCoalStack8 = new ItemStack(FRT.charged_coal, 8);
    static ItemStack chargedCoalStack9 = new ItemStack(FRT.charged_coal, 9);
    static ItemStack coalGunStack = new ItemStack(FRT.coal_gun);
    static ItemStack coalGunBarrelStack = new ItemStack(FRT.coal_gun_barrel);
    static ItemStack coalGunStockStack = new ItemStack(FRT.coal_gun_stock);
    static ItemStack destabilizedCoalStack = new ItemStack(FRT.destabilized_coal);
    static ItemStack destabilizedCoalStack6 = new ItemStack(FRT.destabilized_coal, 6);
    static ItemStack destabilizedCoalStack9 = new ItemStack(FRT.destabilized_coal, 9);
    static ItemStack diamondPaxelStack = new ItemStack(FRT.diamond_paxel);
    static ItemStack firestarterSubstituteStack = new ItemStack(FRT.firestarter_substitute);
    static ItemStack goldPaxelStack = new ItemStack(FRT.gold_paxel);
    static ItemStack gunpowderSubstituteStack = new ItemStack(FRT.gunpowder_substitute);
    static ItemStack hallucinationGogglesStack = new ItemStack(FRT.hallucination_goggles);
    static ItemStack ironPaxelStack = new ItemStack(FRT.iron_paxel);
    static ItemStack leafcutterStack = new ItemStack(FRT.leafcutter);
    static ItemStack obsidianToolStack = new ItemStack(FRT.obsidian_tool);
    static ItemStack refinedCoalStack = new ItemStack(FRT.refined_coal);
    static ItemStack refinedCoalStack9 = new ItemStack(FRT.refined_coal, 9);
    static ItemStack restabilizedCoalStack = new ItemStack(FRT.restabilized_coal);
    static ItemStack restabilizedCoalStack8 = new ItemStack(FRT.restabilized_coal, 8);
    static ItemStack restabilizedCoalStack9 = new ItemStack(FRT.restabilized_coal, 9);
    static ItemStack stonePaxelStack = new ItemStack(FRT.stone_paxel);
    static ItemStack waxStack = new ItemStack(FRT.wax);
    static ItemStack woodPaxelStack = new ItemStack(FRT.wood_paxel);

    /**
     * This is where recipes that, even if additional recipes are added, will not be removed. Call in all {@link IRecipeRegister#registerRecipes()}.
     */
    public static void registerConstantRecipes() {
        shaped(coalGunStack, "xxy", 'x', coalGunBarrelStack, 'y', coalGunStockStack);
        shaped(polishedStoneStack2, " s ", "s s", " s ", 's', stoneSlabStack);
        shaped(blazeCakeStack, "m", "c", 'm', magmaCreamStack, 'c', cakeStack);
        shaped(blazeCakeStack, "p", "s", "c", 'p', blazePowderStack, 's', slimeballStack, 'c', cakeStack);
        shaped(chargedCoalBlockStack, "ccc", "ccc", "ccc", 'c', chargedCoalStack);
        shaped(destabilizedCoalBlockStack, "ccc", "ccc", "ccc", 'c', destabilizedCoalStack);
        shaped(restabilizedCoalBlockStack, "ccc", "ccc", "ccc", 'c', restabilizedCoalStack);
        shaped(refinedCoalBlockStack, "ccc", "ccc", "ccc", 'c', refinedCoalStack);
        shaped(chargedCoalStack8, "ccc", "crc", "ccc", 'c', coalStack, 'r', redstoneStack);
        shaped(fireplaceBottomStack2, "bbb", "bnb", "bbb", 'b', brickStack, 'n', netherrackStack);
        shaped(whiteScreenStack, " p ", "pwp", " p ", 'p', paperStack, 'w', woolStack);
        shaped(destabilizedCoalStack6, "ccc", "ggg", "ccc", 'c', chargedCoalStack, 'g', gunpowderStack);
        shaped(restabilizedCoalStack8, "ccc", "csc", "ccc", 'c', destabilizedCoalStack, 's', slimeballStack);
        shaped(quadDispenserStack, "dd", "dd", 'd', dispenserStack);
        shaped(insaneDispenserStack, "dd", "dd", 'd', quadDispenserStack);
        shaped(bookshelfStack, "www", "bbb", "www", 'w', "plankWood", 'b', "book");
        shaped(candleStack, "s", "w", "w", 's', stringStack, 'w', waxStack);
        shaped(candlePlateStack, " c ", "nnn", 'c', candleStack, 'n', "nuggetGold");
        shaped(popFurnaceStack, "bib", "btb", "bib", 'b', "blockIron", 't', ironTrapdoorStack, 'i', "ingotIron");
        shaped(obsidianToolStack, "dpd", "dsd", " s ", 'd', "gemDiamond", 'p', "gemPrismarine", 's', "stickWood");
        shapeless(dirtStack9, compactDirtStack);
        shapeless(chargedCoalStack9, chargedCoalBlockStack);
        shapeless(destabilizedCoalStack9, destabilizedCoalBlockStack);
        shapeless(restabilizedCoalStack9, restabilizedCoalBlockStack);
        shapeless(refinedCoalStack9, refinedCoalBlockStack);
        shapeless(stoneSlabStack2, polishedStoneStack);
        GameRegistry.addSmelting(restabilizedCoalStack, refinedCoalStack, 0.05F);
        /*BrewingRecipeRegistry.addRecipe(awkwardPotionStack, redMushroomStack, hallucinationPotionStackS);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackS, redstoneStack, hallucinationPotionStackL);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackS, gunpowderStack, hallucinationPotionStackSS);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackL, gunpowderStack, hallucinationPotionStackSL);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackSS, redstoneStack, hallucinationPotionStackSL);
        shaped(hallucinationGogglesStack, "l l", "gpg", 'l', leatherStack, 'g', "paneGlass", 'p', hallucinationPotionStackL);*/
        shaped(woodPaxelStack, "www", " w ", " s ", 'w', "plankWood", 's', "stickWood");
        shaped(ironPaxelStack, "www", " w ", " s ", 'w', ironStack, 's', "stickWood");
        shaped(goldPaxelStack, "www", " w ", " s ", 'w', goldIngotStack, 's', "stickWood");
        shaped(diamondPaxelStack, "www", " w ", " s ", 'w', diamondStack, 's', "stickWood");
        shaped(leafcutterStack, " s ", "t t", "t t", 's', shearsStack, 't', "stickWood");
        shapeless(whiteScreenStack, "screen", "dyeWhite", "dyeWhite");
        shapeless(redScreenStack, "screen", "dyeRed", "dyeRed");
        shapeless(blueScreenStack, "screen", "dyeBlue", "dyeBlue");
        shapeless(orangeScreenStack, "screen", "dyeOrange", "dyeOrange");
        shapeless(orangeScreenStack, "screen", "dyeYellow", "dyeRed");
        shapeless(yellowScreenStack, "screen", "dyeYellow", "dyeYellow");
        shapeless(greenScreenStack, "screen", "dyeGreen", "dyeGreen");
        shapeless(lightTanScreenStack, "screen", "dyeBrown", "dyeWhite", "dyeWhite");
        shapeless(lightOrangeScreenStack, "screen", "dyeOrange", "dyeWhite");
        shapeless(brownScreenStack, "screen", "dyeBrown", "dyeBrown");
        shapeless(blackScreenStack, "screen", "dyeBlack", "dyeBlack");
        shapeless(purpleScreenStack, "screen", "dyePurple", "dyePurple");
        shapeless(purpleScreenStack, "screen", "dyeRed", "dyeBlue");
        shapeless(limeScreenStack, "screen", "dyeLime", "dyeLime");
        shapeless(limeScreenStack, "screen", "dyeWhite", "dyeGreen");
        shapeless(cyanScreenStack, "screen", "dyeCyan", "dyeCyan");
        shapeless(cyanScreenStack, "screen", "dyeGreen", "dyeBlue");
        shapeless(silverScreenStack, "screen", "dyeLightGray", "dyeLightGray");
        shapeless(greyScreenStack, "screen", "dyeGray", "dyeGray");
        shapeless(greyScreenStack, "screen", "dyeWhite", "dyeBlack");
        shapeless(pinkScreenStack, "screen", "dyePink", "dyePink");
        shapeless(pinkScreenStack, "screen", "dyeWhite", "dyeRed");
        shapeless(skyScreenStack, "screen", "dyeLightBlue", "dyeLightBlue");
        shapeless(skyScreenStack, "screen", "dyeWhite", "dyeBlue");
        shapeless(magentaScreenStack, "screen", "dyeMagenta", "dyeMagenta");
        shapeless(darkTanScreenStack, "screen", "dyeBrown", "dyeWhite");
        shapeless(blackScreenStack, whiteScreenStack, "dyeBlack");
        shapeless(redScreenStack, whiteScreenStack, "dyeRed");
        shapeless(orangeScreenStack, whiteScreenStack, "dyeOrange");
        shapeless(yellowScreenStack, whiteScreenStack, "dyeYellow");
        shapeless(greenScreenStack, whiteScreenStack, "dyeGreen");
        shapeless(limeScreenStack, whiteScreenStack, "dyeLime");
        shapeless(blueScreenStack, whiteScreenStack, "dyeBlue");
        shapeless(cyanScreenStack, whiteScreenStack, "dyeCyan");
        shapeless(purpleScreenStack, whiteScreenStack, "dyePurple");
        shapeless(pinkScreenStack, whiteScreenStack, "dyePink");
        shapeless(brownScreenStack, whiteScreenStack, "dyeBrown");
        shapeless(greyScreenStack, whiteScreenStack, "dyeGray");
        shapeless(silverScreenStack, whiteScreenStack, "dyeLightGray");
        shapeless(skyScreenStack, whiteScreenStack, "dyeLightBlue");
        shapeless(magentaScreenStack, whiteScreenStack, "dyeMagenta");
        ShattererRegistry.registerGunpowder(gunpowderStack, gunpowderSubstituteStack);
        ShattererRegistry.registerFirestarter(flintAndSteelStack, fireChargeStack, firestarterSubstituteStack);
        ShattererRegistry.registerPopFurnaceRecipe(glassStack, sandStack, 1);
        ShattererRegistry.registerPopFurnaceRecipe(stainedGlassStack, sandStack, 1);
        ShattererRegistry.registerPopFurnaceRecipe(bottleStack, sandStack, 1);
        ShattererRegistry.registerPopFurnaceRecipe(sugarCaneStack, sugarStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(glowstoneStack, glowstoneDustStack, 4);
        ShattererRegistry.registerPopFurnaceRecipe(boneStack, boneMealStack, 4);
        ShattererRegistry.registerPopFurnaceRecipe(blazeRodStack, blazePowderStack, 4);
        ShattererRegistry.registerPopFurnaceRecipe(dandelionStack, yellowDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(poppyStack, redDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(blueOrchidStack, skyDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(alliumStack, magentaDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(azureBluetStack, silverDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(redTulipStack, redDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(orangeTulipStack, orangeDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(whiteTulipStack, silverDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(pinkTulipStack, pinkDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(oxeyeDaisyStack, silverDyeStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(sunflowerStack, yellowDyeStack, 3);
        ShattererRegistry.registerPopFurnaceRecipe(lilacStack, magentaDyeStack, 3);
        ShattererRegistry.registerPopFurnaceRecipe(roseBushStack, redDyeStack, 3);
        ShattererRegistry.registerPopFurnaceRecipe(peonyStack, pinkDyeStack, 3);
        ShattererRegistry.registerPopFurnaceRecipe(candleStack, waxStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(candlePlateStack, waxStack, 2);
        ShattererRegistry.registerPopFurnaceRecipe(fossilStack, boneMealStack, 6);
        shaped(coalGunBarrelStack, "fff", "   ", "fff", 'f', flintStack);
        shaped(coalGunStockStack, "fff", "s f", "fff", 'f', flintStack, 's', flintAndSteelStack);
        shaped(compactDirtStack, "ddd", "ddd", "ddd", 'd', dirtStack);
        shaped(compactBookshelfStack, "bbb", 'b', bookshelfStack);
        //Recipes that change with certain mods installed, which don't require different classes
        if (MIDLib.hasRealStoneTools()) {
            shaped(stonePaxelStack, "www", " w ", " s ", 'w', stoneStack, 's', "stickWood");
        } else {
            shaped(stonePaxelStack, "www", " w ", " s ", 'w', cobbleStack, 's', "stickWood");
        }
    }

    public static void shapeless(ItemStack is, Object... args) {
        GameRegistry.addRecipe(new ShapelessOreRecipe(is, args));
    }

    public static void shaped(ItemStack is, Object... args) {
        GameRegistry.addRecipe(new ShapedOreRecipe(is, args));
    }
}
