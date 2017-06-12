package the_fireplace.frt.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class FRTFuelHandler implements IFuelHandler {
	private static final int charged_coal_rate = 2400;
	private static final int destabilized_coal_rate = 3200;
	private static final int restabilized_coal_rate = 4800;
	private static final int refined_coal_rate = 6400;
	private static final int screen_rate = 240;
	private static final int wood_tool_rate = 120;

	private int blockMultiplierOf(int i) {
		return 9 * i + i / 10;
	}

	@Override
	public int getBurnTime(ItemStack fuel) {
		Item ifuel = fuel.getItem();
		if (ifuel == FRT.charged_coal) {
			return charged_coal_rate;
		} else if (ifuel == FRT.destabilized_coal) {
			return destabilized_coal_rate;
		} else if (ifuel == FRT.restabilized_coal) {
			return restabilized_coal_rate;
		} else if (ifuel == FRT.refined_coal) {
			return refined_coal_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.charged_coal_block)) {
			return blockMultiplierOf(charged_coal_rate);
		} else if (ifuel == Item.getItemFromBlock(FRT.destabilized_coal_block)) {
			return blockMultiplierOf(destabilized_coal_rate);
		} else if (ifuel == Item.getItemFromBlock(FRT.restabilized_coal_block)) {
			return blockMultiplierOf(restabilized_coal_rate);
		} else if (ifuel == Item.getItemFromBlock(FRT.refined_coal_block)) {
			return blockMultiplierOf(refined_coal_rate);
		} else if (ifuel == Item.getItemFromBlock(FRT.black_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.blue_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.brown_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.cyan_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.dark_tan_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.green_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.grey_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.light_orange_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.light_tan_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.lime_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.magenta_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.orange_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.pink_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.purple_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.red_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.silver_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.sky_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.white_screen)) {
			return screen_rate;
		} else if (ifuel == Item.getItemFromBlock(FRT.yellow_screen)) {
			return screen_rate;
		} else if (ifuel == FRT.wood_paxel) {
			return wood_tool_rate;
		} else {
			return 0;
		}
	}
}
