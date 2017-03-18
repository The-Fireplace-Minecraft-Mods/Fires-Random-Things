package the_fireplace.frt.recipes;

import net.minecraft.block.BlockPlanks;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.FRT;
import the_fireplace.frt.api.PopFurnaceRegistry;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.tools.MIDLib;

/**
 * @author The_Fireplace
 */
public class VanillaStacks {
    //Vanilla Blocks
    static ItemStack alliumStack = new ItemStack(Blocks.RED_FLOWER, 1, 2);
    static ItemStack azureBluetStack = new ItemStack(Blocks.RED_FLOWER, 1, 3);
    static ItemStack blueOrchidStack = new ItemStack(Blocks.RED_FLOWER, 1, 1);
    static ItemStack bookshelfStack = new ItemStack(Blocks.BOOKSHELF);
    static ItemStack cobbleStack = new ItemStack(Blocks.COBBLESTONE);
    static ItemStack dandelionStack = new ItemStack(Blocks.YELLOW_FLOWER, 1, 0);
    static ItemStack dirtStack = new ItemStack(Blocks.DIRT);
    static ItemStack dirtStack9 = new ItemStack(Blocks.DIRT, 9);
    static ItemStack dispenserStack = new ItemStack(Blocks.DISPENSER);
    static ItemStack glassStack = new ItemStack(Blocks.GLASS);
    static ItemStack glowstoneStack = new ItemStack(Blocks.GLOWSTONE);
    static ItemStack ironTrapdoorStack = new ItemStack(Blocks.IRON_TRAPDOOR);
    static ItemStack leverStack = new ItemStack(Blocks.LEVER);
    static ItemStack lilacStack = new ItemStack(Blocks.DOUBLE_PLANT, 1, 1);
    static ItemStack orangeTulipStack = new ItemStack(Blocks.RED_FLOWER, 1, 5);
    static ItemStack oxeyeDaisyStack = new ItemStack(Blocks.RED_FLOWER, 1, 8);
    static ItemStack peonyStack = new ItemStack(Blocks.DOUBLE_PLANT, 1, 5);
    static ItemStack pinkTulipStack = new ItemStack(Blocks.RED_FLOWER, 1, 7);
    static ItemStack poppyStack = new ItemStack(Blocks.RED_FLOWER, 1, 0);
    static ItemStack redTulipStack = new ItemStack(Blocks.RED_FLOWER, 1, 4);
    static ItemStack roseBushStack = new ItemStack(Blocks.DOUBLE_PLANT, 1, 4);
    static ItemStack sandStack = new ItemStack(Blocks.SAND);
    static ItemStack stainedGlassStack = new ItemStack(Blocks.STAINED_GLASS, 1, OreDictionary.WILDCARD_VALUE);
    static ItemStack stoneStack = new ItemStack(Blocks.STONE);
    static ItemStack stoneSlabStack = new ItemStack(Blocks.STONE_SLAB);
    static ItemStack stoneSlabStack2 = new ItemStack(Blocks.STONE_SLAB, 2);
    static ItemStack sunflowerStack = new ItemStack(Blocks.DOUBLE_PLANT, 1, 0);
    static ItemStack whiteTulipStack = new ItemStack(Blocks.RED_FLOWER, 1, 6);
    static ItemStack woolStack = new ItemStack(Blocks.WOOL, 1, OreDictionary.WILDCARD_VALUE);
    //Vanilla Items
    static ItemStack blazePowderStack = new ItemStack(Items.BLAZE_POWDER);
    static ItemStack blazeRodStack = new ItemStack(Items.BLAZE_ROD);
    static ItemStack boneStack = new ItemStack(Items.BONE);
    static ItemStack boneMealStack = new ItemStack(Items.DYE, 1, 15);
    static ItemStack bottleStack = new ItemStack(Items.GLASS_BOTTLE);
    static ItemStack brickStack = new ItemStack(Items.BRICK);
    static ItemStack cakeStack = new ItemStack(Items.CAKE);
    static ItemStack coalStack = new ItemStack(Items.COAL);
    static ItemStack dragonBreathStack = new ItemStack(Items.DRAGON_BREATH);
    static ItemStack enderPearlStack = new ItemStack(Items.ENDER_PEARL);
    static ItemStack fireChargeStack = new ItemStack(Items.FIRE_CHARGE);
    static ItemStack flintAndSteelStack = new ItemStack(Items.FLINT_AND_STEEL);
    static ItemStack glisteringMelonStack = new ItemStack(Items.SPECKLED_MELON);
    static ItemStack glowstoneDustStack = new ItemStack(Items.GLOWSTONE_DUST);
    static ItemStack goldIngotStack = new ItemStack(Items.GOLD_INGOT);
    static ItemStack goldNuggetStack = new ItemStack(Items.GOLD_NUGGET);
    static ItemStack goldenAppleStack = new ItemStack(Items.GOLDEN_APPLE);
    static ItemStack goldenCarrotStack = new ItemStack(Items.GOLDEN_CARROT);
    static ItemStack gunpowderStack = new ItemStack(Items.GUNPOWDER);
    static ItemStack ironStack = new ItemStack(Items.IRON_INGOT);
    static ItemStack magentaDyeStack = new ItemStack(Items.DYE, 1, 13);
    static ItemStack magmaCreamStack = new ItemStack(Items.MAGMA_CREAM);
    static ItemStack mushroomStewStack = new ItemStack(Items.MUSHROOM_STEW);
    static ItemStack orangeDyeStack = new ItemStack(Items.DYE, 1, 14);
    static ItemStack pinkDyeStack = new ItemStack(Items.DYE, 1, 9);
    static ItemStack porkchopStack = new ItemStack(Items.PORKCHOP);
    static ItemStack redDyeStack = new ItemStack(Items.DYE, 1, 1);
    static ItemStack redMushroomStack = new ItemStack(Blocks.RED_MUSHROOM);
    static ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    static ItemStack saddleStack = new ItemStack(Items.SADDLE);
    static ItemStack shearsStack = new ItemStack(Items.SHEARS);
    static ItemStack shulkerShellStack = new ItemStack(Items.SHULKER_SHELL);
    static ItemStack silverDyeStack = new ItemStack(Items.DYE, 1, 7);
    static ItemStack skyDyeStack = new ItemStack(Items.DYE, 1, 12);
    static ItemStack sugarStack = new ItemStack(Items.SUGAR);
    static ItemStack sugarCaneStack = new ItemStack(Items.REEDS);
    static ItemStack yellowDyeStack = new ItemStack(Items.DYE, 1, 11);
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
    static ItemStack enderBookshelfStack = new ItemStack(FRT.ender_bookshelf);
    static ItemStack fireplaceBottomStack2 = new ItemStack(FRT.fireplace_bottom, 2);
    static ItemStack greenScreenStack = new ItemStack(FRT.green_screen);
    static ItemStack greyScreenStack = new ItemStack(FRT.grey_screen);
    static ItemStack insaneDispenserStack = new ItemStack(FRT.insane_dispenser);
    static ItemStack lightOrangeScreenStack = new ItemStack(FRT.light_orange_screen);
    static ItemStack lightTanScreenStack = new ItemStack(FRT.light_tan_screen);
    static ItemStack limeScreenStack = new ItemStack(FRT.lime_screen);
    static ItemStack magentaScreenStack = new ItemStack(FRT.magenta_screen);
    static ItemStack meatBlockStack = new ItemStack(FRT.meat_block);
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
    static ItemStack waxBlockStack = new ItemStack(FRT.wax_deposit);
    static ItemStack whiteScreenStack = new ItemStack(FRT.white_screen);
    static ItemStack yellowScreenStack = new ItemStack(FRT.yellow_screen);
    //Custom Items
    static ItemStack chargedCoalStack = new ItemStack(FRT.charged_coal);
    static ItemStack chargedCoalStack8 = new ItemStack(FRT.charged_coal, 8);
    static ItemStack chargedCoalStack9 = new ItemStack(FRT.charged_coal, 9);
    static ItemStack destabilizedCoalStack = new ItemStack(FRT.destabilized_coal);
    static ItemStack destabilizedCoalStack6 = new ItemStack(FRT.destabilized_coal, 6);
    static ItemStack destabilizedCoalStack9 = new ItemStack(FRT.destabilized_coal, 9);
    static ItemStack diamondPaxelStack = new ItemStack(FRT.diamond_paxel);
    static ItemStack firestarterSubstituteStack = new ItemStack(FRT.firestarter_substitute);
    static ItemStack goldPaxelStack = new ItemStack(FRT.gold_paxel);
    static ItemStack gunpowderSubstituteStack = new ItemStack(FRT.gunpowder_substitute);
    static ItemStack hallucinationGogglesStack = new ItemStack(FRT.hallucination_goggles);
    static ItemStack handheldDispenserStack = new ItemStack(FRT.handheld_dispenser);
    static ItemStack handheldQuadDispenserStack = new ItemStack(FRT.handheld_quad_dispenser);
    static ItemStack handheldInsaneDispenserStack = new ItemStack(FRT.handheld_insane_dispenser);
    static ItemStack ironPaxelStack = new ItemStack(FRT.iron_paxel);
    static ItemStack kineticPearlStack = new ItemStack(FRT.kinetic_pearl);
    static ItemStack leafcutterStack = new ItemStack(FRT.leafcutter);
    static ItemStack mysteryMeatStack = new ItemStack(FRT.mystery_meat);
    static ItemStack mysteryMeatStack8 = new ItemStack(FRT.mystery_meat, 8);
    static ItemStack obsidianToolStack = new ItemStack(FRT.obsidian_tool);
    static ItemStack pigderPearlStack = new ItemStack(FRT.pigder_pearl);
    static ItemStack pigderPearlStack4 = new ItemStack(FRT.pigder_pearl, 4);
    static ItemStack rawMysteryMeatStack = new ItemStack(FRT.raw_mystery_meat);
    static ItemStack rawMysteryMeatStack8 = new ItemStack(FRT.raw_mystery_meat, 8);
    static ItemStack refinedCoalStack = new ItemStack(FRT.refined_coal);
    static ItemStack refinedCoalStack9 = new ItemStack(FRT.refined_coal, 9);
    static ItemStack restabilizedCoalStack = new ItemStack(FRT.restabilized_coal);
    static ItemStack restabilizedCoalStack8 = new ItemStack(FRT.restabilized_coal, 8);
    static ItemStack restabilizedCoalStack9 = new ItemStack(FRT.restabilized_coal, 9);
    static ItemStack shimmeringStewStack = new ItemStack(FRT.shimmering_stew);
    static ItemStack stonePaxelStack = new ItemStack(FRT.stone_paxel);
    static ItemStack waxStack = new ItemStack(FRT.wax);
    static ItemStack waxStack4 = new ItemStack(FRT.wax, 4);
    static ItemStack woodPaxelStack = new ItemStack(FRT.wood_paxel);

    /**
     * This is where recipes that, even if additional recipes are added, will not be removed. Call in all {@link IRecipeRegister#registerRecipes()}.
     */
    public static void registerConstantRecipes() {
        shaped(handheldDispenserStack, " d ", "ili", 'd', dispenserStack, 'i', "ingotIron", 'l', leverStack);
        shaped(handheldQuadDispenserStack, " d ", "ili", 'd', quadDispenserStack, 'i', "ingotGold", 'l', leverStack);
        shaped(handheldInsaneDispenserStack, " d ", "ili", 'd', insaneDispenserStack, 'i', "gemDiamond", 'l', leverStack);
        shaped(waxBlockStack, "ww", "ww", 'w', waxStack);
        shaped(polishedStoneStack2, " s ", "s s", " s ", 's', stoneSlabStack);
        shaped(blazeCakeStack, "m", "c", 'm', magmaCreamStack, 'c', cakeStack);
        shaped(blazeCakeStack, "p", "s", "c", 'p', blazePowderStack, 's', "slimeball", 'c', cakeStack);
        shaped(chargedCoalBlockStack, "ccc", "ccc", "ccc", 'c', chargedCoalStack);
        shaped(destabilizedCoalBlockStack, "ccc", "ccc", "ccc", 'c', destabilizedCoalStack);
        shaped(restabilizedCoalBlockStack, "ccc", "ccc", "ccc", 'c', restabilizedCoalStack);
        shaped(refinedCoalBlockStack, "ccc", "ccc", "ccc", 'c', refinedCoalStack);
        shaped(chargedCoalStack8, "ccc", "crc", "ccc", 'c', coalStack, 'r', "dustRedstone");
        shaped(fireplaceBottomStack2, "bbb", "bnb", "bbb", 'b', brickStack, 'n', "netherrack");
        shaped(whiteScreenStack, " p ", "pwp", " p ", 'p', "paper", 'w', woolStack);
        shaped(destabilizedCoalStack6, "ccc", "ggg", "ccc", 'c', chargedCoalStack, 'g', "gunpowder");
        shaped(restabilizedCoalStack8, "ccc", "csc", "ccc", 'c', destabilizedCoalStack, 's', waxStack);
        shaped(quadDispenserStack, "dd", "dd", 'd', dispenserStack);
        shaped(insaneDispenserStack, "dd", "dd", 'd', quadDispenserStack);
        shaped(bookshelfStack, "www", "bbb", "www", 'w', "plankWood", 'b', "book");
        shaped(candleStack, "s", "w", "w", 's', "string", 'w', waxStack);
        shaped(candlePlateStack, " c ", "nnn", 'c', candleStack, 'n', "nuggetGold");
        shaped(popFurnaceStack, "bib", "btb", "bib", 'b', "blockIron", 't', ironTrapdoorStack, 'i', "ingotIron");
        shaped(obsidianToolStack, "dpd", "dsd", " s ", 'd', "gemDiamond", 'p', "gemPrismarine", 's', "stickWood");
        shaped(shellCoreStack, "grg", "rer", "grg", 'g', "blockGold", 'r', "blockRedstone", 'e', "enderpearl");
        shaped(pigderPearlStack, " s ", "pep", 's', saddleStack, 'p', porkchopStack, 'e', enderPearlStack);
        shaped(pigderPearlStack4, " s ", "pep", "eee", 's', saddleStack, 'p', porkchopStack, 'e', enderPearlStack);
        shaped(enderBookshelfStack, "ebe", "bbb", "ebe", 'e', "enderpearl", 'b', compactBookshelfStack);
        shaped(meatBlockStack, "mmm", "m m", "mmm", 'm', "listAllMeatRaw");
        shapeless(waxStack4, waxBlockStack);
        shapeless(dirtStack9, compactDirtStack);
        shapeless(chargedCoalStack9, chargedCoalBlockStack);
        shapeless(destabilizedCoalStack9, destabilizedCoalBlockStack);
        shapeless(restabilizedCoalStack9, restabilizedCoalBlockStack);
        shapeless(refinedCoalStack9, refinedCoalBlockStack);
        shapeless(stoneSlabStack2, polishedStoneStack);
        shapeless(rawMysteryMeatStack8, meatBlockStack);
        addSmelting(restabilizedCoalStack, refinedCoalStack, 0.05F);
        addSmelting(meatBlockStack, mysteryMeatStack8, 0);
        addSmelting(rawMysteryMeatStack, mysteryMeatStack, 0);
        for(BlockPlanks.EnumType meta: BlockPlanks.EnumType.values()) {
            shapeless(new ItemStack(FRT.waxed_planks, 1, meta.ordinal()), new ItemStack(Blocks.PLANKS, 1, meta.ordinal()), waxStack);
            shapeless(new ItemStack(Blocks.PLANKS, 1, meta.ordinal()), new ItemStack(FRT.waxed_planks, 1, meta.ordinal()));
        }
        NBTTagCompound potionS = new NBTTagCompound();
        NBTTagCompound potionL = new NBTTagCompound();
        NBTTagCompound potionA = new NBTTagCompound();
        potionS.setString("Potion", "frt:hallucination");
        potionL.setString("Potion", "frt:long_hallucination");
        potionA.setString("Potion", "minecraft:awkward");
        ItemStack hallucinationPotionStackS = new ItemStack(Items.POTIONITEM);
        hallucinationPotionStackS.setTagCompound(potionS);
        ItemStack hallucinationPotionStackL = new ItemStack(Items.POTIONITEM);
        hallucinationPotionStackL.setTagCompound(potionL);
        ItemStack hallucinationPotionStackSS = new ItemStack(Items.SPLASH_POTION);
        hallucinationPotionStackSS.setTagCompound(potionS);
        ItemStack hallucinationPotionStackSL = new ItemStack(Items.SPLASH_POTION);
        hallucinationPotionStackSL.setTagCompound(potionL);
        ItemStack hallucinationPotionStackLS = new ItemStack(Items.LINGERING_POTION);
        hallucinationPotionStackLS.setTagCompound(potionS);
        ItemStack hallucinationPotionStackLL = new ItemStack(Items.LINGERING_POTION);
        hallucinationPotionStackLL.setTagCompound(potionL);
        ItemStack awkwardPotionStack = new ItemStack(Items.POTIONITEM);
        awkwardPotionStack.setTagCompound(potionA);
        ItemStack awkwardSplashPotionStack = new ItemStack(Items.SPLASH_POTION);
        awkwardSplashPotionStack.setTagCompound(potionA);
        ItemStack awkwardLingeringPotionStack = new ItemStack(Items.LINGERING_POTION);
        awkwardLingeringPotionStack.setTagCompound(potionA);
        BrewingRecipeRegistry.addRecipe(awkwardPotionStack, redMushroomStack, hallucinationPotionStackS);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackS, redstoneStack, hallucinationPotionStackL);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackS, gunpowderStack, hallucinationPotionStackSS);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackL, gunpowderStack, hallucinationPotionStackSL);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackSS, redstoneStack, hallucinationPotionStackSL);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackSS, dragonBreathStack, hallucinationPotionStackLS);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackSL, dragonBreathStack, hallucinationPotionStackLL);
        BrewingRecipeRegistry.addRecipe(hallucinationPotionStackLS, redstoneStack, hallucinationPotionStackLL);
        BrewingRecipeRegistry.addRecipe(awkwardLingeringPotionStack, redMushroomStack, hallucinationPotionStackLS);
        BrewingRecipeRegistry.addRecipe(awkwardSplashPotionStack, redMushroomStack, hallucinationPotionStackSS);
        shaped(hallucinationGogglesStack, "l l", "gpg", 'l', "leather", 'g', "paneGlass", 'p', hallucinationPotionStackS);
        shaped(woodPaxelStack, "www", " w ", " s ", 'w', "plankWood", 's', "stickWood");
        shaped(ironPaxelStack, "www", " w ", " s ", 'w', "ingotIron", 's', "stickWood");
        shaped(goldPaxelStack, "www", " w ", " s ", 'w', "ingotGold", 's', "stickWood");
        shaped(diamondPaxelStack, "www", " w ", " s ", 'w', "gemDiamond", 's', "stickWood");
        shaped(leafcutterStack, " s ", "t t", "t t", 's', shearsStack, 't', "stickWood");
        shaped(kineticPearlStack, " g ", "geg", " g ", 'g', "nuggetGold", 'e', "enderpearl");
        shapeless(dispenserStack, handheldDispenserStack);
        shapeless(quadDispenserStack, handheldQuadDispenserStack);
        shapeless(insaneDispenserStack, handheldInsaneDispenserStack);
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
        shapeless(shimmeringStewStack, mushroomStewStack, hallucinationPotionStackL, shulkerShellStack);
        shapeless(shimmeringStewStack, mushroomStewStack, hallucinationPotionStackLL, shulkerShellStack);
        shapeless(shimmeringStewStack, mushroomStewStack, hallucinationPotionStackLS, shulkerShellStack);
        shapeless(shimmeringStewStack, mushroomStewStack, hallucinationPotionStackS, shulkerShellStack);
        shapeless(shimmeringStewStack, mushroomStewStack, hallucinationPotionStackSL, shulkerShellStack);
        shapeless(shimmeringStewStack, mushroomStewStack, hallucinationPotionStackSS, shulkerShellStack);
        PopFurnaceRegistry.registerGunpowder(gunpowderStack, gunpowderSubstituteStack);
        PopFurnaceRegistry.registerFirestarter(flintAndSteelStack, fireChargeStack, firestarterSubstituteStack);
        PopFurnaceRegistry.registerPopFurnaceRecipe(glassStack, sandStack, 1);
        PopFurnaceRegistry.registerPopFurnaceRecipe(stainedGlassStack, sandStack, 1);
        PopFurnaceRegistry.registerPopFurnaceRecipe(bottleStack, sandStack, 1);
        PopFurnaceRegistry.registerPopFurnaceRecipe(sugarCaneStack, sugarStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(glowstoneStack, glowstoneDustStack, 4);
        PopFurnaceRegistry.registerPopFurnaceRecipe(boneStack, boneMealStack, 4);
        PopFurnaceRegistry.registerPopFurnaceRecipe(blazeRodStack, blazePowderStack, 4);
        PopFurnaceRegistry.registerPopFurnaceRecipe(dandelionStack, yellowDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(poppyStack, redDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(blueOrchidStack, skyDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(alliumStack, magentaDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(azureBluetStack, silverDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(redTulipStack, redDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(orangeTulipStack, orangeDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(whiteTulipStack, silverDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(pinkTulipStack, pinkDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(oxeyeDaisyStack, silverDyeStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(sunflowerStack, yellowDyeStack, 3);
        PopFurnaceRegistry.registerPopFurnaceRecipe(lilacStack, magentaDyeStack, 3);
        PopFurnaceRegistry.registerPopFurnaceRecipe(roseBushStack, redDyeStack, 3);
        PopFurnaceRegistry.registerPopFurnaceRecipe(peonyStack, pinkDyeStack, 3);
        PopFurnaceRegistry.registerPopFurnaceRecipe(candleStack, waxStack, 2);
        PopFurnaceRegistry.registerPopFurnaceRecipe(candlePlateStack, waxStack, 2);
        shaped(compactDirtStack, "ddd", "ddd", "ddd", 'd', dirtStack);
        shaped(compactBookshelfStack, "bbb", 'b', "bookshelfWood");
        shaped(compactBookshelfStack, "bbb", 'b', "bookshelf");
        //Recipes that change with certain mods installed, which don't require different classes
        if (MIDLib.hasRealStoneTools()) {
            shaped(stonePaxelStack, "www", " w ", " s ", 'w', stoneStack, 's', "stickWood");
            shaped(stonePaxelStack, "www", " w ", " s ", 'w', stoneStack, 's', "rodWood");
        } else {
            shaped(stonePaxelStack, "www", " w ", " s ", 'w', cobbleStack, 's', "stickWood");
            shaped(stonePaxelStack, "www", " w ", " s ", 'w', cobbleStack, 's', "rodWood");
        }
    }

    public static void shapeless(ItemStack output, Object... args) {
        if(output.getItem() != null) {
            if (ArrayUtils.contains(ConfigValues.DISABLEDITEMS, output.getItem().getUnlocalizedName().substring(5)))
                return;
        }else return;
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof ItemStack)
                if(((ItemStack)args[i]).getItem() != null) {
                    if(ArrayUtils.contains(ConfigValues.DISABLEDITEMS, ((ItemStack)args[i]).getItem().getUnlocalizedName().substring(5)))
                        return;
                }else return;
        }
        GameRegistry.addRecipe(new ShapelessOreRecipe(output, args));
    }

    public static void shaped(ItemStack output, Object... args) {
        if(output.getItem() != null) {
            if (ArrayUtils.contains(ConfigValues.DISABLEDITEMS, output.getItem().getUnlocalizedName().substring(5)))
                return;
        }else return;
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof ItemStack)
                if(((ItemStack)args[i]).getItem() != null) {
                    if(ArrayUtils.contains(ConfigValues.DISABLEDITEMS, ((ItemStack)args[i]).getItem().getUnlocalizedName().substring(5)))
                        return;
                }else return;
        }
        GameRegistry.addRecipe(new ShapedOreRecipe(output, args));
    }

    public static void addSmelting(ItemStack input, ItemStack output, float xp){
        if(input.getItem() == null || output.getItem() == null || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, input.getItem().getUnlocalizedName().substring(5)) || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, output.getItem().getUnlocalizedName().substring(5)))
            return;
        GameRegistry.addSmelting(input, output, xp);
    }
}
