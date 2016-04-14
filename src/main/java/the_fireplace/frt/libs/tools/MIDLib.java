package the_fireplace.frt.libs.tools;

import net.minecraftforge.fml.common.Loader;

/**
 * 
 * @author The_Fireplace
 *
 */
public class MIDLib {
	public static boolean hasBaseMetals(){
		return Loader.isModLoaded("basemetals");
	}
	public static boolean hasRealStoneTools(){
		return Loader.isModLoaded("realstonetools");
	}
}
