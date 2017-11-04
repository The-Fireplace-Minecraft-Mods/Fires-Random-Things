package the_fireplace.frt;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import the_fireplace.frt.armor.FRTArmor;
import the_fireplace.frt.blocks.*;
import the_fireplace.frt.blocks.internal.BlockShell;
import the_fireplace.frt.blocks.internal.BlockStrawBed;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.entity.projectile.*;
import the_fireplace.frt.handlers.*;
import the_fireplace.frt.items.*;
import the_fireplace.frt.items.internal.ItemPaxel;
import the_fireplace.frt.items.internal.ItemStrawBed;
import the_fireplace.frt.network.FRTGuiHandler;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.potion.HallucinationPotion;
import the_fireplace.frt.recipes.RecipeHandler;
import the_fireplace.frt.worldgen.*;
import the_fireplace.frt.worldgen.structure.*;

/**
 * @author The_Fireplace
 */
@Mod(modid = FRT.MODID, name = FRT.MODNAME, guiFactory = "the_fireplace.frt.client.gui.FRTGuiFactory", updateJSON = "https://bitbucket.org/The_Fireplace/minecraft-mod-updates/raw/master/frt.json", acceptedMinecraftVersions = "[1.12,)", version = "${version}")
@Mod.EventBusSubscriber
public final class FRT {
	@Instance(FRT.MODID)
	public static FRT instance;

	public static final String MODID = "frt";
	public static final String MODNAME = "Fire's Random Things";

	private static Logger LOGGER;

	public static Configuration config;
	public static Property ENABLESHELL_PROPERTY;
	public static Property ENABLEDAMAGE_PROPERTY;
	public static Property ITEMSPERGUNPOWDER_PROPERTY;
	public static Property POTIONSWITCH_PROPERTY;
	public static Property TICKSPERREDSTONE_PROPERTY;
	public static Property DISABLEDITEMS_PROPERTY;
	public static Property GENSTRUCTURES_PROPERTY;
	public static Property GENSTORIES_PROPERTY;

	@SidedProxy(clientSide = "the_fireplace.frt.client.ClientProxy", serverSide = "the_fireplace.frt.CommonProxy")
	public static CommonProxy proxy;

	public static final CreativeTabs TabFRT = new TabFRT();

	public static Potion hallucination;

	private boolean structureGeneratorsRegistered = false;

	public int clientCooldownTicks;

	public static final Block ender_bookshelf = new BlockEnderBookshelf();
	public static final Block compact_bookshelf = new BlockCompactBookshelf();
	public static final Block compact_dirt = new FRTBlock(Material.GROUND).setSoundType(SoundType.GROUND).setHarvestTool("shovel", 1).setHardness(2.3F).setUnlocalizedName("compact_dirt");
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
	public static final Block polished_stone = new FRTBlock(Material.ROCK).setHarvestTool("pickaxe", 0).setHardness(1.5F).setResistance(10.0F).setUnlocalizedName("polished_stone");
	public static final Block shell_core = new BlockShellCore();
	public static final Block shell = new BlockShell();
	public static final Block item_exploder = new BlockItemExploder();
	public static final Block quad_dispenser = new BlockQuadDispenser();
	public static final Block insane_dispenser = new BlockInsaneDispenser();
	public static final Block candle = new BlockCandle().setUnlocalizedName("candle");
	public static final Block candle_with_base = new BlockCandle().setUnlocalizedName("candle_with_plate");
	public static final Block wax_deposit = new FRTBlock(Material.GROUND).setHarvestTool("pickaxe", 0).setSoundType(SoundType.GROUND).setUnlocalizedName("wax_deposit").setHardness(2.0F);
	public static final Block waxed_planks = new BlockWaxedPlanks();
	public static final Block meat_block = new BlockMeat();
	public static final Block straw_bed_block = new BlockStrawBed().setUnlocalizedName("straw_bed_block").setCreativeTab(TabFRT);
	public static final Block ir_furnace = new BlockIRFurnace(false).setUnlocalizedName("ir_furnace").setCreativeTab(TabFRT);
	public static final Block lit_ir_furnace = new BlockIRFurnace(true).setUnlocalizedName("lit_ir_furnace").setCreativeTab(TabFRT);

	public static final Item charged_coal = new ItemChargedCoal();
	public static final Item handheld_dispenser = new ItemHandheldDispenser("handheld_dispenser");
	public static final Item handheld_quad_dispenser = new ItemHandheldDispenser("handheld_quad_dispenser");
	public static final Item handheld_insane_dispenser = new ItemHandheldDispenser("handheld_insane_dispenser");
	public static final Item destabilized_coal = new Item().setUnlocalizedName("destabilized_coal").setCreativeTab(TabFRT);
	public static final Item diamond_paxel = new ItemPaxel(ToolMaterial.DIAMOND).setUnlocalizedName("diamond_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item iron_paxel = new ItemPaxel(ToolMaterial.IRON).setUnlocalizedName("iron_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item gold_paxel = new ItemPaxel(ToolMaterial.GOLD).setUnlocalizedName("gold_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item stone_paxel = new ItemPaxel(ToolMaterial.STONE).setUnlocalizedName("stone_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item wood_paxel = new ItemPaxel(ToolMaterial.WOOD).setUnlocalizedName("wood_paxel").setCreativeTab(FRT.TabFRT);
	public static final Item restabilized_coal = new Item().setUnlocalizedName("restabilized_coal").setCreativeTab(TabFRT);
	public static final Item refined_coal = new Item().setUnlocalizedName("refined_coal").setCreativeTab(TabFRT);
	public static final Item obsidian_tool = new ItemObsidianBreaker();
	public static final Item gunpowder_substitute = new Item().setUnlocalizedName("gunpowder_substitute").setCreativeTab(TabFRT);
	public static final Item firestarter_substitute = new Item().setUnlocalizedName("firestarter_substitute").setCreativeTab(TabFRT);
	public static final Item leafcutter = new ItemLeafcutter();
	public static final Item wax = new Item().setUnlocalizedName("wax").setCreativeTab(TabFRT);
	public static final Item kinetic_pearl = new ItemKineticPearl();
	public static final Item pigder_pearl = new ItemPigderPearl();
	public static final Item mystery_meat = new ItemMysteryMeat(4, 2, false).setUnlocalizedName("mystery_meat").setCreativeTab(TabFRT);
	public static final Item raw_mystery_meat = new ItemMysteryMeat(2, 0, true).setUnlocalizedName("raw_mystery_meat").setCreativeTab(TabFRT);
	public static final Item raw_meat_pie = new ItemMeatPie(4, 4, true).setUnlocalizedName("raw_meat_pie").setCreativeTab(TabFRT);
	public static final Item meat_pie = new ItemMeatPie(10, 10, false).setAlwaysEdible().setUnlocalizedName("meat_pie").setCreativeTab(TabFRT);
	public static final Item shimmering_stew = new ItemShimmeringStew();
	public static final Item straw_bed = new ItemStrawBed().setUnlocalizedName("straw_bed").setCreativeTab(TabFRT);
	//public static final Item gift_box = new ItemGiftBox();
	//public static final Item trading_card = new ItemTradingCard();

	public static final Item hallucination_goggles = new FRTArmor(ArmorMaterial.LEATHER, EntityEquipmentSlot.HEAD).setUnlocalizedName("hallucination_goggles").setCreativeTab(TabFRT);

	public void syncConfig() {
		ConfigValues.ENABLESHELL = ENABLESHELL_PROPERTY.getBoolean();
		ConfigValues.ENABLEDAMAGE = ENABLEDAMAGE_PROPERTY.getBoolean();
		ConfigValues.ITEMSPERGUNPOWDER = ITEMSPERGUNPOWDER_PROPERTY.getInt();
		ConfigValues.POTIONSWITCH = POTIONSWITCH_PROPERTY.getInt();
		ConfigValues.TICKSPERREDSTONE = TICKSPERREDSTONE_PROPERTY.getInt();
		ConfigValues.DISABLEDITEMS = DISABLEDITEMS_PROPERTY.getStringList();
		ConfigValues.GENSTRUCTURES = GENSTRUCTURES_PROPERTY.getBoolean();
		ConfigValues.GENSTORIES = GENSTORIES_PROPERTY.getBoolean();
		if (!structureGeneratorsRegistered)
			addStructures();
		if (config.hasChanged())
			config.save();
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		LOGGER = event.getModLog();
		PacketDispatcher.registerPackets();
		proxy.registerClient();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new FRTGuiHandler());
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ENABLESHELL_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ENABLESHELL_NAME, ConfigValues.ENABLESHELL_DEFAULT, proxy.translateToLocal(ConfigValues.ENABLESHELL_NAME + ".tooltip"));
		ENABLEDAMAGE_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ENABLEDAMAGE_NAME, ConfigValues.ENABLEDAMAGE_DEFAULT, proxy.translateToLocal(ConfigValues.ENABLEDAMAGE_NAME + ".tooltip"));
		ITEMSPERGUNPOWDER_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.ITEMSPERGUNPOWDER_NAME, ConfigValues.ITEMSPERGUNPOWDER_DEFAULT, proxy.translateToLocal(ConfigValues.ITEMSPERGUNPOWDER_NAME + ".tooltip"));
		POTIONSWITCH_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.POTIONSWITCH_NAME, ConfigValues.POTIONSWITCH_DEFAULT, proxy.translateToLocal(ConfigValues.POTIONSWITCH_NAME + ".tooltip"));
		TICKSPERREDSTONE_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.TICKSPERREDSTONE_NAME, ConfigValues.TICKSPERREDSTONE_DEFAULT, proxy.translateToLocal(ConfigValues.TICKSPERREDSTONE_NAME + ".tooltip"));
		DISABLEDITEMS_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.DISABLEDITEMS_NAME, ConfigValues.DISABLEDITEMS_DEFAULT, proxy.translateToLocal(ConfigValues.DISABLEDITEMS_NAME + ".tooltip"));
		GENSTRUCTURES_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.GENSTRUCTURES_NAME, ConfigValues.GENSTRUCTURES_DEFAULT, proxy.translateToLocal(ConfigValues.GENSTRUCTURES_NAME + ".tooltip"));
		GENSTORIES_PROPERTY = config.get(Configuration.CATEGORY_GENERAL, ConfigValues.GENSTORIES_NAME, ConfigValues.GENSTORIES_DEFAULT, proxy.translateToLocal(ConfigValues.GENSTORIES_NAME + ".tooltip"));
		DISABLEDITEMS_PROPERTY.setRequiresMcRestart(true);
		POTIONSWITCH_PROPERTY.setMinValue(1);
		POTIONSWITCH_PROPERTY.setMaxValue(10);
		if (event.getSide().isClient())
			POTIONSWITCH_PROPERTY.setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
		syncConfig();

		int eid = 0;
		EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":ammo_coal"), EntityCoal.class, "ammo_coal", eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":ammo_charged_coal"), EntityChargedCoal.class, "ammo_charged_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":ammo_destabilized_coal"), EntityDestabilizedCoal.class, "ammo_destabilized_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":ammo_restabilized_coal"), EntityRestabilizedCoal.class, "ammo_restabilized_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":ammo_refined_coal"), EntityRefinedCoal.class, "ammo_refined_coal", ++eid, instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(MODID + ":pigder_pearl"), EntityPigderPearl.class, "pigder_pearl", ++eid, instance, 64, 10, true);
		proxy.registerEntityRenderers();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerTileEntities();
		if (!isItemDisabled(wax_deposit))
			GameRegistry.registerWorldGenerator(new WorldGeneratorWax(), 6);
		addStructures();
		OreDictionary.registerOre("book", Items.BOOK);
		OreDictionary.registerOre("book", Items.WRITTEN_BOOK);
		OreDictionary.registerOre("book", Items.WRITABLE_BOOK);
		OreDictionary.registerOre("book", Items.ENCHANTED_BOOK);
		OreDictionary.registerOre("bookshelfWood", Blocks.BOOKSHELF);
		OreDictionary.registerOre("bookshelf", Blocks.BOOKSHELF);
		OreDictionary.registerOre("listAllMeatRaw", Items.PORKCHOP);
		OreDictionary.registerOre("listAllMeatRaw", Items.BEEF);
		OreDictionary.registerOre("listAllMeatRaw", Items.CHICKEN);
		OreDictionary.registerOre("listAllMeatRaw", Items.MUTTON);
		OreDictionary.registerOre("listAllMeatRaw", Items.FISH);
		OreDictionary.registerOre("listAllMeatRaw", new ItemStack(Items.FISH, 1, 1));
		OreDictionary.registerOre("listAllMeatRaw", new ItemStack(Items.FISH, 1, 2));
		registerOre("screen", white_screen);
		registerOre("screen", black_screen);
		registerOre("screen", red_screen);
		registerOre("screen", blue_screen);
		registerOre("screen", green_screen);
		registerOre("screen", orange_screen);
		registerOre("screen", brown_screen);
		registerOre("screen", sky_screen);
		registerOre("screen", magenta_screen);
		registerOre("screen", pink_screen);
		registerOre("screen", cyan_screen);
		registerOre("screen", lime_screen);
		registerOre("screen", grey_screen);
		registerOre("screen", silver_screen);
		registerOre("screen", purple_screen);
		registerOre("screen", yellow_screen);
		registerOre("screen", light_tan_screen);
		registerOre("screen", dark_tan_screen);
		registerOre("enderpearl", pigder_pearl);
		registerOre("plankWood", new ItemStack(waxed_planks, 1, OreDictionary.WILDCARD_VALUE));
		registerOre("listAllMeatRaw", raw_mystery_meat);
		RecipeHandler.registerConstantRecipes();
		BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(Items.COAL, new DispenseBehaviorCoal());
		if (!isItemDisabled(charged_coal))
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(charged_coal, new DispenseBehaviorChargedCoal());
		if (!isItemDisabled(destabilized_coal))
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(destabilized_coal, new DispenseBehaviorDestabilizedCoal());
		if (!isItemDisabled(restabilized_coal))
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(restabilized_coal, new DispenseBehaviorRestabilizedCoal());
		if (!isItemDisabled(refined_coal))
			BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY.putObject(refined_coal, new DispenseBehaviorRefinedCoal());
	}

	private void addStructures() {
		if (ConfigValues.GENSTRUCTURES) {
			GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("statue").register(new Statue()), 18);
			GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("nether_base").register(new NetherBase()), 18);
			GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("noobhouse").register(new NoobHouse()), 19);
			GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("portalcave").register(new PortalCave()), 19);
			GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("prewar_outpost").register(new PrewarOutpost()), 19);
			GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("bossign").register(new BosSign()), 20);
			if(ConfigValues.GENSTORIES)
				StructureGenerator.registerStructure("outpost_warzone", new WarzoneOutpost());
			else
				GameRegistry.registerWorldGenerator(new WorldGeneratorStructure("outpost_warzone").register(new WarzoneOutpost()), 18);
			structureGeneratorsRegistered = true;
		}
	}

	private static IForgeRegistry<Block> blockRegistry = null;

	public static void registerBlock(Block block) {
		if (isItemDisabled(block))
			return;
		if(blockRegistry == null){
			logError("Block registry was null, could not register: "+block.getUnlocalizedName());
			return;
		}
		if(block.getRegistryName() == null)
			block.setRegistryName(block.getUnlocalizedName().substring(5));
		blockRegistry.register(block);
	}

	private static IForgeRegistry<Item> itemRegistry = null;

	public static void registerItem(Item item) {
		if (isItemDisabled(item))
			return;
		if(itemRegistry == null){
			logError("Item registry was null, could not register: "+item.getUnlocalizedName());
			return;
		}
		if(item.getRegistryName() == null)
			item.setRegistryName(item.getUnlocalizedName().substring(5));
		itemRegistry.register(item);
	}

	public static void registerItemForBlock(Block block) {
		if (isItemDisabled(block))
			return;
		if(itemRegistry == null){
			logError("Item registry was null, could not register: "+block.getUnlocalizedName());
			return;
		}
		itemRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	public static void registerItemBlock(ItemBlock itemBlock) {
		if (isItemDisabled(itemBlock))
			return;
		if(itemRegistry == null){
			logError("Item registry was null, could not register: "+itemBlock.getUnlocalizedName());
			return;
		}
		if(itemBlock.getRegistryName() == null)
			itemBlock.setRegistryName(itemBlock.getUnlocalizedName().substring(5));
		itemRegistry.register(itemBlock);
	}

	@SubscribeEvent
	public static void itemRegistry(RegistryEvent.Register<Item> event){
		itemRegistry = event.getRegistry();
		registerItem(straw_bed);
		registerItem(charged_coal);
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
		registerItem(wax);
		registerItem(kinetic_pearl);
		registerItem(pigder_pearl);
		registerItem(mystery_meat);
		registerItem(raw_mystery_meat);
		registerItem(shimmering_stew);
		registerItem(handheld_dispenser);
		registerItem(handheld_quad_dispenser);
		registerItem(handheld_insane_dispenser);
		registerItem(raw_meat_pie);
		registerItem(meat_pie);
		//registerItem(gift_box);
		//registerItem(trading_card);
		registerItemBlock(new ItemWaxedPlanks(waxed_planks));
		//Blocks
		registerItemForBlock(ender_bookshelf);
		registerItemForBlock(polished_stone);
		registerItemForBlock(compact_dirt);
		registerItemForBlock(fireplace_bottom);
		registerItemForBlock(charged_coal_block);
		registerItemForBlock(destabilized_coal_block);
		registerItemForBlock(restabilized_coal_block);
		registerItemForBlock(refined_coal_block);
		registerItemForBlock(blaze_cake);
		registerItemForBlock(dark_tan_screen);
		registerItemForBlock(light_tan_screen);
		registerItemForBlock(white_screen);
		registerItemForBlock(red_screen);
		registerItemForBlock(black_screen);
		registerItemForBlock(blue_screen);
		registerItemForBlock(cyan_screen);
		registerItemForBlock(sky_screen);
		registerItemForBlock(green_screen);
		registerItemForBlock(orange_screen);
		registerItemForBlock(light_orange_screen);
		registerItemForBlock(yellow_screen);
		registerItemForBlock(pink_screen);
		registerItemForBlock(grey_screen);
		registerItemForBlock(silver_screen);
		registerItemForBlock(magenta_screen);
		registerItemForBlock(purple_screen);
		registerItemForBlock(lime_screen);
		registerItemForBlock(brown_screen);
		registerItemForBlock(compact_bookshelf);
		registerItemForBlock(shell_core);
		registerItemForBlock(item_exploder);
		registerItemForBlock(quad_dispenser);
		registerItemForBlock(insane_dispenser);
		registerItemForBlock(candle);
		registerItemForBlock(candle_with_base);
		registerItemForBlock(wax_deposit);
		registerItemForBlock(meat_block);
		registerItemForBlock(ir_furnace);
	}

	@SubscribeEvent
	public static void blockRegistry(RegistryEvent.Register<Block> event){
		blockRegistry = event.getRegistry();
		registerBlock(ender_bookshelf);
		registerBlock(polished_stone);
		registerBlock(compact_dirt);
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
		registerBlock(item_exploder);
		registerBlock(quad_dispenser);
		registerBlock(insane_dispenser);
		registerBlock(candle);
		registerBlock(candle_with_base);
		registerBlock(wax_deposit);
		registerBlock(meat_block);
		registerBlock(waxed_planks);
		registerBlock(shell);
		registerBlock(straw_bed_block);
		registerBlock(ir_furnace);
		registerBlock(lit_ir_furnace);
	}

	@SubscribeEvent
	public static void potionRegister(RegistryEvent.Register<Potion> event){
		if(hallucination == null)
			hallucination = new HallucinationPotion().setPotionName("potion.hallucination").setRegistryName(new ResourceLocation(FRT.MODID, "hallucination"));
		event.getRegistry().register(hallucination);
	}

	@SubscribeEvent
	public static void potionTypeRegister(RegistryEvent.Register<PotionType> event){
		event.getRegistry().register(new PotionType(new PotionEffect(hallucination, 3600)).setRegistryName(new ResourceLocation(FRT.MODID, "hallucination")));
		event.getRegistry().register(new PotionType(new PotionEffect(hallucination, 9600)).setRegistryName(new ResourceLocation(FRT.MODID, "long_hallucination")));
	}

	/**
	 * Registers the item renders
	 */
	@SideOnly(Side.CLIENT)
	public static void registerItemRenders() {
		rmm(shell_core);
		rmm(polished_stone);
		rmm(ender_bookshelf);
		rmm(compact_bookshelf);
		rmm(compact_dirt);
		rmm(blaze_cake);
		rmm(fireplace_bottom);
		rmm(charged_coal_block);
		rmm(destabilized_coal_block);
		rmm(restabilized_coal_block);
		rmm(refined_coal_block);
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
		rmm(item_exploder);
		rmm(quad_dispenser);
		rmm(insane_dispenser);
		rmm(candle);
		rmm(candle_with_base);
		rmm(wax_deposit);
		rmm(meat_block);
		rmm(ir_furnace);
		rmm(mystery_meat);
		rmm(raw_mystery_meat);
		rmm(meat_pie);
		rmm(raw_meat_pie);
		rmm(shimmering_stew);
		rmm(straw_bed);
		//rmm(gift_box);
		if (!isItemDisabled(waxed_planks)) {
			ModelBakery.registerItemVariants(Item.getItemFromBlock(waxed_planks),
					new ModelResourceLocation(MODID + ":oak_waxed_planks", "inventory"),
					new ModelResourceLocation(MODID + ":spruce_waxed_planks", "inventory"),
					new ModelResourceLocation(MODID + ":birch_waxed_planks", "inventory"),
					new ModelResourceLocation(MODID + ":jungle_waxed_planks", "inventory"),
					new ModelResourceLocation(MODID + ":acacia_waxed_planks", "inventory"),
					new ModelResourceLocation(MODID + ":dark_oak_waxed_planks", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(waxed_planks), 0, new ModelResourceLocation(FRT.MODID + ":oak_waxed_planks", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(waxed_planks), 1, new ModelResourceLocation(FRT.MODID + ":spruce_waxed_planks", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(waxed_planks), 2, new ModelResourceLocation(FRT.MODID + ":birch_waxed_planks", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(waxed_planks), 3, new ModelResourceLocation(FRT.MODID + ":jungle_waxed_planks", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(waxed_planks), 4, new ModelResourceLocation(FRT.MODID + ":acacia_waxed_planks", "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(waxed_planks), 5, new ModelResourceLocation(FRT.MODID + ":dark_oak_waxed_planks", "inventory"));
		}
		rmm(handheld_dispenser);
		rmm(handheld_quad_dispenser);
		rmm(handheld_insane_dispenser);

		rmm(charged_coal);
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
		rmm(kinetic_pearl);
		rmm(pigder_pearl);
	}

	@SideOnly(Side.CLIENT)
	private static void rmm(Block b) {
		if (!isItemDisabled(b))
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(b), 0, new ModelResourceLocation(FRT.MODID + ":" + b.getUnlocalizedName().substring(5), "inventory"));
	}

	@SideOnly(Side.CLIENT)
	private static void rmm(Item i) {
		if (!isItemDisabled(i))
			ModelLoader.setCustomModelResourceLocation(i, 0, new ModelResourceLocation(FRT.MODID + ":" + i.getUnlocalizedName().substring(5), "inventory"));
	}

	private static void registerOre(String s, Block b) {
		if (!isItemDisabled(b))
			OreDictionary.registerOre(s, b);
	}

	private static void registerOre(String s, Item i) {
		if (!isItemDisabled(i))
			OreDictionary.registerOre(s, i);
	}

	private static void registerOre(String s, ItemStack is) {
		if (!is.isEmpty())
			if (!isItemDisabled(is))
				OreDictionary.registerOre(s, is);
	}

	public static void logInfo(String log, Object... params) {
		LOGGER.log(Level.INFO, log, params);
	}

	public static void logDebug(String log, Object... params) {
		LOGGER.log(Level.DEBUG, log, params);
	}

	public static void logError(String log, Object... params) {
		LOGGER.log(Level.ERROR, log, params);
	}

	public static void logTrace(String log, Object... params) {
		LOGGER.log(Level.TRACE, log, params);
	}

	public static void logWarn(String log, Object... params) {
		LOGGER.log(Level.WARN, log, params);
	}

	/**
	 * Check if an item is disabled
	 * @param item
	 *  The item. Can be an Item, Block, or ItemStack
	 * @return
	 *  true if disabled, false if enabled
	 */
	public static boolean isItemDisabled(Object item){
		if(item instanceof Item)
			return ArrayUtils.contains(ConfigValues.DISABLEDITEMS, ((Item)item).getUnlocalizedName().substring(5));
		else if(item instanceof Block)
			return ArrayUtils.contains(ConfigValues.DISABLEDITEMS, ((Block)item).getUnlocalizedName().substring(5));
		else if(item instanceof ItemStack)
			return ArrayUtils.contains(ConfigValues.DISABLEDITEMS, ((ItemStack)item).getItem().getUnlocalizedName().substring(5));
		else
			throw new IllegalArgumentException("Item being checked must be an Item, Block, or ItemStack!");
	}
}
