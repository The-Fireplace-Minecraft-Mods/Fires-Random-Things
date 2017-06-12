package the_fireplace.frt.compat.basemetals;

import cyano.basemetals.init.Materials;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.items.internal.ItemPaxel;

public class RegisterBaseMetals implements IBaseMetalsRegister {
	public static Item copper_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.copper)).setUnlocalizedName("copper_paxel").setCreativeTab(FRT.TabFRT);
	public static Item silver_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.silver)).setUnlocalizedName("silver_paxel").setCreativeTab(FRT.TabFRT);
	public static Item tin_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.tin)).setUnlocalizedName("tin_paxel").setCreativeTab(FRT.TabFRT);
	public static Item lead_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.lead)).setUnlocalizedName("lead_paxel").setCreativeTab(FRT.TabFRT);
	public static Item nickel_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.nickel)).setUnlocalizedName("nickel_paxel").setCreativeTab(FRT.TabFRT);
	public static Item bronze_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.bronze)).setUnlocalizedName("bronze_paxel").setCreativeTab(FRT.TabFRT);
	public static Item brass_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.brass)).setUnlocalizedName("brass_paxel").setCreativeTab(FRT.TabFRT);
	public static Item steel_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.steel)).setUnlocalizedName("steel_paxel").setCreativeTab(FRT.TabFRT);
	public static Item invar_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.invar)).setUnlocalizedName("invar_paxel").setCreativeTab(FRT.TabFRT);
	public static Item electrum_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.electrum)).setUnlocalizedName("electrum_paxel").setCreativeTab(FRT.TabFRT);
	public static Item cold_iron_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.coldiron)).setUnlocalizedName("cold_iron_paxel").setCreativeTab(FRT.TabFRT);
	public static Item mithril_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.mithril)).setUnlocalizedName("mithril_paxel").setCreativeTab(FRT.TabFRT);
	public static Item adamantine_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.adamantine)).setUnlocalizedName("adamantine_paxel").setCreativeTab(FRT.TabFRT);
	public static Item star_steel_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.starsteel)).setUnlocalizedName("star_steel_paxel").setCreativeTab(FRT.TabFRT);
	public static Item aquarium_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.aquarium)).setUnlocalizedName("aquarium_paxel").setCreativeTab(FRT.TabFRT);
	public static Item cupronickel_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.cupronickel)).setUnlocalizedName("cupronickel_paxel").setCreativeTab(FRT.TabFRT);
	public static Item platinum_paxel = new ItemPaxel(Materials.getToolMaterialFor(Materials.platinum)).setUnlocalizedName("platinum_paxel").setCreativeTab(FRT.TabFRT);

	@Override
	public void registerItems() {
		FRT.instance.registerItem(adamantine_paxel);
		FRT.instance.registerItem(aquarium_paxel);
		FRT.instance.registerItem(copper_paxel);
		FRT.instance.registerItem(silver_paxel);
		FRT.instance.registerItem(tin_paxel);
		FRT.instance.registerItem(lead_paxel);
		FRT.instance.registerItem(nickel_paxel);
		FRT.instance.registerItem(bronze_paxel);
		FRT.instance.registerItem(brass_paxel);
		FRT.instance.registerItem(steel_paxel);
		FRT.instance.registerItem(invar_paxel);
		FRT.instance.registerItem(electrum_paxel);
		FRT.instance.registerItem(cold_iron_paxel);
		FRT.instance.registerItem(mithril_paxel);
		FRT.instance.registerItem(star_steel_paxel);
		FRT.instance.registerItem(cupronickel_paxel);
		FRT.instance.registerItem(platinum_paxel);
	}

	@Override
	public void registerItemRenders() {
		addRender(copper_paxel);
		addRender(silver_paxel);
		addRender(tin_paxel);
		addRender(lead_paxel);
		addRender(nickel_paxel);
		addRender(bronze_paxel);
		addRender(brass_paxel);
		addRender(steel_paxel);
		addRender(invar_paxel);
		addRender(electrum_paxel);
		addRender(cold_iron_paxel);
		addRender(mithril_paxel);
		addRender(star_steel_paxel);
		addRender(adamantine_paxel);
		addRender(aquarium_paxel);
		addRender(cupronickel_paxel);
		addRender(platinum_paxel);
	}

	private void addRender(Item i) {
		if (!ArrayUtils.contains(ConfigValues.DISABLEDITEMS, i.getUnlocalizedName().substring(5)))
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(FRT.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
	}
}
