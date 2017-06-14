package the_fireplace.frt.config;

/**
 * @author The_Fireplace
 */
public class ConfigValues {
	public static final boolean ENABLESHELL_DEFAULT = true;
	public static boolean ENABLESHELL;
	public static final String ENABLESHELL_NAME = "cfg.enable_shell";

	public static final boolean ENABLEDAMAGE_DEFAULT = true;
	public static boolean ENABLEDAMAGE;
	public static final String ENABLEDAMAGE_NAME = "cfg.enable_block_damage";

	public static final int ITEMSPERGUNPOWDER_DEFAULT = 5;
	public static int ITEMSPERGUNPOWDER;
	public static final String ITEMSPERGUNPOWDER_NAME = "cfg.items_per_gunpowder";

	public static final int TICKSPERREDSTONE_DEFAULT = 600;
	public static int TICKSPERREDSTONE;
	public static final String TICKSPERREDSTONE_NAME = "cfg.ticks_per_redstone";

	public static final int POTIONSWITCH_DEFAULT = 1;
	public static int POTIONSWITCH;
	public static final String POTIONSWITCH_NAME = "cfg.potion_switch";

	public static final String[] DISABLEDITEMS_DEFAULT = new String[]{};
	public static String[] DISABLEDITEMS;
	public static final String DISABLEDITEMS_NAME = "cfg.disabled_items";

	public static final boolean GENSTRUCTURES_DEFAULT = true;
	public static boolean GENSTRUCTURES;
	public static final String GENSTRUCTURES_NAME = "cfg.gen_structures";

	public static final boolean GENSTORIES_DEFAULT = true;
	public static boolean GENSTORIES;
	public static final String GENSTORIES_NAME = "cfg.gen_stories";
}
