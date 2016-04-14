package the_fireplace.frt.compat.basemetals;

import cyano.basemetals.init.Materials;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import the_fireplace.frt.FRT;
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
	}
	@Override
	public void registerItemRenders() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(copper_paxel, 0, new ModelResourceLocation(FRT.MODID + ":copper_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(silver_paxel, 0, new ModelResourceLocation(FRT.MODID + ":silver_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(tin_paxel, 0, new ModelResourceLocation(FRT.MODID + ":tin_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(lead_paxel, 0, new ModelResourceLocation(FRT.MODID + ":lead_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(nickel_paxel, 0, new ModelResourceLocation(FRT.MODID + ":nickel_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bronze_paxel, 0, new ModelResourceLocation(FRT.MODID + ":bronze_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(brass_paxel, 0, new ModelResourceLocation(FRT.MODID + ":brass_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(steel_paxel, 0, new ModelResourceLocation(FRT.MODID + ":steel_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(invar_paxel, 0, new ModelResourceLocation(FRT.MODID + ":invar_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(electrum_paxel, 0, new ModelResourceLocation(FRT.MODID + ":electrum_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(cold_iron_paxel, 0, new ModelResourceLocation(FRT.MODID + ":cold_iron_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(mithril_paxel, 0, new ModelResourceLocation(FRT.MODID + ":mithril_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(star_steel_paxel, 0, new ModelResourceLocation(FRT.MODID + ":star_steel_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(adamantine_paxel, 0, new ModelResourceLocation(FRT.MODID + ":adamantine_paxel", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(aquarium_paxel, 0, new ModelResourceLocation(FRT.MODID + ":aquarium_paxel", "inventory"));
	}
}
