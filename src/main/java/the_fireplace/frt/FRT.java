package the_fireplace.frt;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.frt.armor.FRTArmor;
import the_fireplace.frt.blocks.*;
import the_fireplace.frt.blocks.internal.BlockShell;
import the_fireplace.frt.blocks.internal.DummyBlockCoalGun;
import the_fireplace.frt.compat.basemetals.IBaseMetalsRegister;
import the_fireplace.frt.compat.basemetals.RegisterBaseMetals;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.entity.EntityHallucinationPotion;
import the_fireplace.frt.entity.coal.*;
import the_fireplace.frt.events.CommonEvents;
import the_fireplace.frt.handlers.*;
import the_fireplace.frt.items.*;
import the_fireplace.frt.items.internal.ItemPaxel;
import the_fireplace.frt.tools.MIDLib;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.potion.HallucinationPotion;
import the_fireplace.frt.proxy.CommonProxy;
import the_fireplace.frt.recipes.BaseMetalsRecipes;
import the_fireplace.frt.recipes.DefaultRecipes;
import the_fireplace.frt.recipes.IRecipeRegister;
import the_fireplace.frt.recipes.VanillaStacks;
import the_fireplace.frt.worldgen.WorldGeneratorFossil;

/**
 * @author The_Fireplace
 */
@Mod(modid= FRT.MODID, name= FRT.MODNAME, guiFactory = "the_fireplace.frt.config.FRTGuiFactory")
public class FRT {
	@Instance(FRT.MODID)
	public static FRT instance;

	public static final String MODID = "frt";
	public static final String MODNAME = "Fire's Random Things";
	public static String VERSION;
	public static final String curseCode = "";//TODO

	public static Configuration config;
	public static Property ENABLESHELL_PROPERTY;
	public static Property ENABLEDAMAGE_PROPERTY;
	public static Property ITEMSPERGUNPOWDER_PROPERTY;
	public static Property ENABLEFOSSILGEN_PROPERTY;
	public static Property POTIONSWITCH_PROPERTY;

	@SidedProxy(clientSide="the_fireplace.frt.proxy.ClientProxy", serverSide="the_fireplace.frt.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs TabFRT = new TabFRT();

	public static Potion hallucination;

	public static final Block compact_bookshelf = new BlockCompactBookshelf();
	public static final Block compact_dirt = new BlockCompactDirt();
	public static final Block fossil = new BlockFossil();
	public static final Block fireplace_bottom = new BlockFireplaceBottom();
	public static final Block charged_coal_block = new BlockChargedCoal();
	public static final Block destabilized_coal_block = new BlockDestabilizedCoal();
	public static final Block restabilized_coal_block = new BlockRestabilizedCoal();
	public static final Block refined_coal_block = new BlockRefinedCoal();
	public static final Block blaze_cake = new BlockBlazeCake();
	public static final Block light_tan_screen = new BlockScreen("light_tan");
	public static final Block dark_tan_screen = new BlockScreen("dark_tan");
	public static final Block white_screen = new BlockScreen("white");
	public static final Block red_screen = new BlockScreen("red");
	public static final Block black_screen = new BlockScreen("black");
	public static final Block blue_screen = new BlockScreen("blue");
	public static final Block cyan_screen = new BlockScreen("cyan");
	public static final Block sky_screen = new BlockScreen("sky");
	public static final Block green_screen = new BlockScreen("green");
	public static final Block orange_screen = new BlockScreen("orange");
	public static final Block yellow_screen = new BlockScreen("yellow");
	public static final Block pink_screen = new BlockScreen("pink");
	public static final Block grey_screen = new BlockScreen("grey");
	public static final Block silver_screen = new BlockScreen("silver");
	public static final Block magenta_screen = new BlockScreen("magenta");
	public static final Block purple_screen = new BlockScreen("purple");
	public static final Block lime_screen = new BlockScreen("lime");
	public static final Block brown_screen = new BlockScreen("brown");
	public static final Block light_orange_screen = new BlockScreen("light_orange");
	public static final Block polished_stone = new BlockPolishedStone();
	public static final Block shell_core = new BlockShellCore();
	public static final Block shell = new BlockShell();
	public static final Block pop_furnace = new BlockPopFurnace();
	public static final Block quad_dispenser = new BlockQuadDispenser();
	public static final Block insane_dispenser = new BlockInsaneDispenser();
	public static final Block candle = new BlockCandle().setUnlocalizedName("candle");
	public static final Block candle_with_base = new BlockCandle().setUnlocalizedName("candle_with_plate");

	public static final Item charged_coal = new ItemChargedCoal();
	public static final Item coal_gun_barrel = new Item().setUnlocalizedName("coal_gun_barrel").setCreativeTab(TabFRT);
	public static final Item coal_gun_stock = new Item().setUnlocalizedName("coal_gun_stock").setCreativeTab(TabFRT);
	public static final DummyBlockCoalGun coal_gun = new DummyBlockCoalGun();
	public static final Item destabilized_coal = new Item().setUnlocalizedName("destabilized_coal").setCreativeTab(TabFRT);
	public static final Item diamond_paxel = new ItemPaxel(ToolMaterial.DIAMOND).setUnlocalizedName("diamond_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item iron_paxel = new ItemPaxel(ToolMaterial.IRON).setUnlocalizedName("iron_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item gold_paxel = new ItemPaxel(ToolMaterial.GOLD).setUnlocalizedName("gold_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item stone_paxel = new ItemPaxel(ToolMaterial.STONE).setUnlocalizedName("stone_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item wood_paxel = new ItemPaxel(ToolMaterial.WOOD).setUnlocalizedName("wood_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item restabilized_coal = new Item().setUnlocalizedName("restabilized_coal").setCreativeTab(TabFRT);
	public static final Item refined_coal = new Item().setUnlocalizedName("refined_coal").setCreativeTab(TabFRT);
	public static final Item obsidian_tool = new ItemObsidianTool();
	public static final Item gunpowder_substitute = new Item().setUnlocalizedName("gunpowder_substitute").setCreativeTab(TabFRT);
	public static final Item firestarter_substitute = new Item().setUnlocalizedName("firestarter_substitute").setCreativeTab(TabFRT);
	public static final Item leafcutter = new ItemLeafcutter();
	public static final Item hallucination_potion = new ItemHallucinationPotion();
	public static final Item wax = new Item().setUnlocalizedName("wax").setCreativeTab(TabFRT);

	public static final Item hallucination_goggles = new FRTArmor(ArmorMaterial.LEATHER, EntityEquipmentSlot.HEAD).setUnlocalizedName("hallucination_goggles").setCreativeTab(TabFRT);

	public void registerBlock(Block block){
		if(!(block.getUnlocalizedName() == null) && !block.getUnlocalizedName().equals("tile.") && !block.getUnlocalizedName().equals("tile.null.name") && !block.getUnlocalizedName().equals("null"))
			GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
	}

	public void registerItem(Item item){
		if(!(item.getUnlocalizedName() == null) && !item.getUnlocalizedName().equals("item.") && !item.getUnlocalizedName().equals("item.null.name") && !item.getUnlocalizedName().equals("null"))
			GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}

	public void syncConfig(){
		ConfigValues.ENABLESHELL = ENABLESHELL_PROPERTY.getBoolean();
		ConfigValues.ENABLEDAMAGE = ENABLEDAMAGE_PROPERTY.getBoolean();
		ConfigValues.ITEMSPERGUNPOWDER = ITEMSPERGUNPOWDER_PROPERTY.getInt();
		ConfigValues.ENABLEFOSSILGEN = ENABLEFOSSILGEN_PROPERTY.getBoolean();
		ConfigValues.POTIONSWITCH = POTIONSWITCH_PROPERTY.getInt();
		if(config.hasChanged())
			config.save();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		String[] version = event.getModMetadata().version.split("\\.");
		if(version[3].equals("BUILDNUMBER"))//Dev environment
			VERSION = event.getModMetadata().version.replace("BUILDNUMBER", "9001");
		else//Released build
			VERSION = event.getModMetadata().version;

		PacketDispatcher.registerPackets();
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
		proxy.registerClient();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new UnLogicIIGuiHandler());
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ENABLESHELL_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ENABLESHELL_NAME, ConfigValues.ENABLESHELL_DEFAULT, I18n.translateToLocal(ConfigValues.ENABLESHELL_NAME+".tooltip"));
		ENABLEDAMAGE_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ENABLEDAMAGE_NAME, ConfigValues.ENABLEDAMAGE_DEFAULT, I18n.translateToLocal(ConfigValues.ENABLEDAMAGE_NAME+".tooltip"));
		ITEMSPERGUNPOWDER_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ITEMSPERGUNPOWDER_NAME, ConfigValues.ITEMSPERGUNPOWDER_DEFAULT, I18n.translateToLocal(ConfigValues.ITEMSPERGUNPOWDER_NAME+".tooltip"));
		ENABLEFOSSILGEN_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ENABLEFOSSILGEN_NAME, ConfigValues.ENABLEFOSSILGEN_DEFAULT, I18n.translateToLocal(ConfigValues.ENABLEFOSSILGEN_NAME+".tooltip"));
		POTIONSWITCH_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.POTIONSWITCH_NAME, ConfigValues.POTIONSWITCH_DEFAULT, I18n.translateToLocal(ConfigValues.POTIONSWITCH_NAME+".tooltip"));
		POTIONSWITCH_PROPERTY.setMinValue(1);
		POTIONSWITCH_PROPERTY.setMaxValue(60);
		if(event.getSide().isClient())
			POTIONSWITCH_PROPERTY.setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
		syncConfig();
		registerBlock(polished_stone);
		registerBlock(compact_dirt);
		registerBlock(fossil);
		registerBlock(fireplace_bottom);
		registerBlock(charged_coal_block);
		registerBlock(destabilized_coal_block);
		registerBlock(restabilized_coal_block);
		registerBlock(refined_coal_block);
		registerBlock(blaze_cake);
		registerBlock(dark_tan_screen);
		registerBlock(light_tan_screen);
		registerBlock(white_screen);
		registerBlock(red_screen);
		registerBlock(black_screen);
		registerBlock(blue_screen);
		registerBlock(cyan_screen);
		registerBlock(sky_screen);
		registerBlock(green_screen);
		registerBlock(orange_screen);
		registerBlock(light_orange_screen);
		registerBlock(yellow_screen);
		registerBlock(pink_screen);
		registerBlock(grey_screen);
		registerBlock(silver_screen);
		registerBlock(magenta_screen);
		registerBlock(purple_screen);
		registerBlock(lime_screen);
		registerBlock(brown_screen);
		registerBlock(compact_bookshelf);
		registerBlock(shell_core);
		registerBlock(shell);
		registerBlock(pop_furnace);
		registerBlock(quad_dispenser);
		registerBlock(insane_dispenser);
		registerBlock(candle);
		registerBlock(candle_with_base);

		registerItem(charged_coal);
		registerItem(coal_gun_barrel);
		registerItem(coal_gun_stock);
		registerItem(destabilized_coal);
		registerItem(restabilized_coal);
		registerItem(refined_coal);
		registerItem(diamond_paxel);
		registerItem(gold_paxel);
		registerItem(iron_paxel);
		registerItem(stone_paxel);
		registerItem(wood_paxel);
		registerItem(obsidian_tool);
		registerItem(hallucination_goggles);
		registerItem(gunpowder_substitute);
		registerItem(firestarter_substitute);
		registerItem(leafcutter);
		registerItem(hallucination_potion);
		registerItem(wax);

		GameRegistry.registerBlock(coal_gun, ItemBlockCoalGun.class, "coal_gun");

		int eid = 0;
		EntityRegistry.registerModEntity(EntityCoal.class, "ammo_coal", eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityChargedCoal.class, "ammo_charged_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityDestabilizedCoal.class, "ammo_destabilized_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityRestabilizedCoal.class, "ammo_restabilized_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityRefinedCoal.class, "ammo_refined_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(EntityHallucinationPotion.class, "hallucination_potion", ++eid, instance, 64, 10, true);
		GameRegistry.registerFuelHandler(new UnLogicIIFuelHandler());
		proxy.registerEntityRenderers();

		hallucination = new HallucinationPotion().setPotionName("potion.hallucination");
	}

	@EventHandler
	public void init(FMLInitializationEvent event){
		IBaseMetalsRegister bm;
		if(MIDLib.hasBaseMetals()){
			bm = new RegisterBaseMetals();
			bm.registerItems();
		}

		if(event.getSide().isClient())
			registerItemRenders();
		proxy.registerRenderers();
		proxy.registerTileEntities();
		if(ConfigValues.ENABLEFOSSILGEN)
			GameRegistry.registerWorldGenerator(new WorldGeneratorFossil(), 5);
		OreDictionary.registerOre("book", Items.book);
		OreDictionary.registerOre("book", Items.written_book);
		OreDictionary.registerOre("book", Items.writable_book);
		OreDictionary.registerOre("screen", white_screen);
		OreDictionary.registerOre("screen", black_screen);
		OreDictionary.registerOre("screen", red_screen);
		OreDictionary.registerOre("screen", blue_screen);
		OreDictionary.registerOre("screen", green_screen);
		OreDictionary.registerOre("screen", orange_screen);
		OreDictionary.registerOre("screen", brown_screen);
		OreDictionary.registerOre("screen", sky_screen);
		OreDictionary.registerOre("screen", magenta_screen);
		OreDictionary.registerOre("screen", pink_screen);
		OreDictionary.registerOre("screen", cyan_screen);
		OreDictionary.registerOre("screen", lime_screen);
		OreDictionary.registerOre("screen", grey_screen);
		OreDictionary.registerOre("screen", silver_screen);
		OreDictionary.registerOre("screen", purple_screen);
		OreDictionary.registerOre("screen", yellow_screen);
		OreDictionary.registerOre("screen", light_tan_screen);
		OreDictionary.registerOre("screen", dark_tan_screen);
		IRecipeRegister recipes;
		if(MIDLib.hasBaseMetals()){
			recipes = new BaseMetalsRecipes();
			recipes.registerRecipes();
		}
		recipes = new DefaultRecipes();
		recipes.registerRecipes();
		VanillaStacks.registerConstantRecipes();
		BlockDispenser.dispenseBehaviorRegistry.putObject(Items.coal, new DispenseBehaviorCoal());
		BlockDispenser.dispenseBehaviorRegistry.putObject(charged_coal, new DispenseBehaviorChargedCoal());
		BlockDispenser.dispenseBehaviorRegistry.putObject(destabilized_coal, new DispenseBehaviorDestabilizedCoal());
		BlockDispenser.dispenseBehaviorRegistry.putObject(restabilized_coal, new DispenseBehaviorRestabilizedCoal());
		BlockDispenser.dispenseBehaviorRegistry.putObject(refined_coal, new DispenseBehaviorRefinedCoal());
		BlockDispenser.dispenseBehaviorRegistry.putObject(hallucination_potion, new DispenseBehaviorHallucinationPotion());
	}

	/**
	 * Registers the item renders
	 */
	@SideOnly(Side.CLIENT)
	private void registerItemRenders(){
		rmm(shell_core);
		rmm(polished_stone);
		rmm(compact_bookshelf);
		rmm(compact_dirt);
		rmm(blaze_cake);
		rmm(fireplace_bottom);
		rmm(charged_coal_block);
		rmm(destabilized_coal_block);
		rmm(restabilized_coal_block);
		rmm(refined_coal_block);
		rmm(fossil);
		rmm(light_tan_screen);
		rmm(dark_tan_screen);
		rmm(white_screen);
		rmm(black_screen);
		rmm(red_screen);
		rmm(green_screen);
		rmm(grey_screen);
		rmm(silver_screen);
		rmm(blue_screen);
		rmm(sky_screen);
		rmm(yellow_screen);
		rmm(lime_screen);
		rmm(pink_screen);
		rmm(brown_screen);
		rmm(orange_screen);
		rmm(light_orange_screen);
		rmm(cyan_screen);
		rmm(purple_screen);
		rmm(magenta_screen);
		rmm(pop_furnace);
		rmm(quad_dispenser);
		rmm(insane_dispenser);
		rmm(candle);
		rmm(candle_with_base);

		rmm(coal_gun);

		rmm(charged_coal);
		rmm(coal_gun_barrel);
		rmm(coal_gun_stock);
		rmm(destabilized_coal);
		rmm(restabilized_coal);
		rmm(refined_coal);
		rmm(diamond_paxel);
		rmm(gold_paxel);
		rmm(iron_paxel);
		rmm(stone_paxel);
		rmm(wood_paxel);
		rmm(obsidian_tool);
		rmm(firestarter_substitute);
		rmm(gunpowder_substitute);
		rmm(leafcutter);
		rmm(hallucination_goggles);
		rmm(wax);

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hallucination_potion, 0, new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5), "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hallucination_potion, 1, new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5), "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hallucination_potion, 2, new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5)+"_splash", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hallucination_potion, 3, new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5)+"_splash", "inventory"));
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(hallucination_potion, 4, new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5)+"_lingering", "inventory"));

		ModelBakery.registerItemVariants(hallucination_potion, new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5), "inventory"), new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5)+"_splash", "inventory"), new ModelResourceLocation(FRT.MODID+":"+hallucination_potion.getUnlocalizedName().substring(5)+"_lingering", "inventory"));

		IBaseMetalsRegister bm;
		if(MIDLib.hasBaseMetals()){
			bm = new RegisterBaseMetals();
			bm.registerItemRenders();
		}
	}
	@SideOnly(Side.CLIENT)
	private void rmm(Block b){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(b), 0, new ModelResourceLocation(FRT.MODID+":"+b.getUnlocalizedName().substring(5), "inventory"));
	}
	@SideOnly(Side.CLIENT)
	private void rmm(Item i){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(FRT.MODID+":"+i.getUnlocalizedName().substring(5), "inventory"));
	}
}
