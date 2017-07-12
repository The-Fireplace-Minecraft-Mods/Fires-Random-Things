package the_fireplace.frt.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.FRT;
import the_fireplace.frt.api.PopFurnaceRegistry;
import the_fireplace.frt.config.ConfigValues;

/**
 * @author The_Fireplace
 */
public class RecipeHandler {
	//Vanilla Blocks
	static ItemStack alliumStack = new ItemStack(Blocks.RED_FLOWER, 1, 2);
	static ItemStack azureBluetStack = new ItemStack(Blocks.RED_FLOWER, 1, 3);
	static ItemStack blueOrchidStack = new ItemStack(Blocks.RED_FLOWER, 1, 1);
	static ItemStack dandelionStack = new ItemStack(Blocks.YELLOW_FLOWER, 1, 0);
	static ItemStack glassStack = new ItemStack(Blocks.GLASS);
	static ItemStack glowstoneStack = new ItemStack(Blocks.GLOWSTONE);
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
	static ItemStack sunflowerStack = new ItemStack(Blocks.DOUBLE_PLANT, 1, 0);
	static ItemStack whiteTulipStack = new ItemStack(Blocks.RED_FLOWER, 1, 6);
	//Vanilla Items
	static ItemStack blazePowderStack = new ItemStack(Items.BLAZE_POWDER);
	static ItemStack blazeRodStack = new ItemStack(Items.BLAZE_ROD);
	static ItemStack boneStack = new ItemStack(Items.BONE);
	static ItemStack boneMealStack = new ItemStack(Items.DYE, 1, 15);
	static ItemStack bottleStack = new ItemStack(Items.GLASS_BOTTLE);
	static ItemStack dragonBreathStack = new ItemStack(Items.DRAGON_BREATH);
	static ItemStack fireChargeStack = new ItemStack(Items.FIRE_CHARGE);
	static ItemStack flintAndSteelStack = new ItemStack(Items.FLINT_AND_STEEL);
	static ItemStack glisteringMelonStack = new ItemStack(Items.SPECKLED_MELON);
	static ItemStack glowstoneDustStack = new ItemStack(Items.GLOWSTONE_DUST);
	static ItemStack goldIngotStack = new ItemStack(Items.GOLD_INGOT);
	static ItemStack goldNuggetStack = new ItemStack(Items.GOLD_NUGGET);
	static ItemStack goldenAppleStack = new ItemStack(Items.GOLDEN_APPLE);
	static ItemStack goldenCarrotStack = new ItemStack(Items.GOLDEN_CARROT);
	static ItemStack gunpowderStack = new ItemStack(Items.GUNPOWDER);
	static ItemStack magentaDyeStack = new ItemStack(Items.DYE, 1, 13);
	static ItemStack orangeDyeStack = new ItemStack(Items.DYE, 1, 14);
	static ItemStack pinkDyeStack = new ItemStack(Items.DYE, 1, 9);
	static ItemStack redDyeStack = new ItemStack(Items.DYE, 1, 1);
	static ItemStack redMushroomStack = new ItemStack(Blocks.RED_MUSHROOM);
	static ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
	static ItemStack silverDyeStack = new ItemStack(Items.DYE, 1, 7);
	static ItemStack skyDyeStack = new ItemStack(Items.DYE, 1, 12);
	static ItemStack sugarStack = new ItemStack(Items.SUGAR);
	static ItemStack sugarCaneStack = new ItemStack(Items.REEDS);
	static ItemStack yellowDyeStack = new ItemStack(Items.DYE, 1, 11);
	//Custom Blocks
	static ItemStack candleStack = new ItemStack(FRT.candle);
	static ItemStack candlePlateStack = new ItemStack(FRT.candle_with_base);
	static ItemStack meatBlockStack = new ItemStack(FRT.meat_block);
	//Custom Items
	static ItemStack firestarterSubstituteStack = new ItemStack(FRT.firestarter_substitute);
	static ItemStack gunpowderSubstituteStack = new ItemStack(FRT.gunpowder_substitute);
	static ItemStack mysteryMeatStack = new ItemStack(FRT.mystery_meat);
	static ItemStack mysteryMeatStack8 = new ItemStack(FRT.mystery_meat, 8);
	static ItemStack rawMysteryMeatStack = new ItemStack(FRT.raw_mystery_meat);
	static ItemStack refinedCoalStack = new ItemStack(FRT.refined_coal);
	static ItemStack restabilizedCoalStack = new ItemStack(FRT.restabilized_coal);
	static ItemStack waxStack = new ItemStack(FRT.wax);

	public static void registerConstantRecipes() {
		addSmelting(restabilizedCoalStack, refinedCoalStack, 0.05F);
		addSmelting(meatBlockStack, mysteryMeatStack8, 0);
		addSmelting(rawMysteryMeatStack, mysteryMeatStack, 0);
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
		PopFurnaceRegistry.registerPopFurnaceRecipe(goldenAppleStack, goldIngotStack, 8);
		PopFurnaceRegistry.registerPopFurnaceRecipe(glisteringMelonStack, goldNuggetStack, 8);
		PopFurnaceRegistry.registerPopFurnaceRecipe(goldenCarrotStack, goldNuggetStack, 8);
	}

	public static void addSmelting(ItemStack input, ItemStack output, float xp) {
		if (input.getItem() == null || output.getItem() == null || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, input.getItem().getUnlocalizedName().substring(5)) || ArrayUtils.contains(ConfigValues.DISABLEDITEMS, output.getItem().getUnlocalizedName().substring(5)))
			return;
		GameRegistry.addSmelting(input, output, xp);
	}
}
