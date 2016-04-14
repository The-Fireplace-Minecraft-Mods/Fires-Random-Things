package the_fireplace.frt.handlers;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
/**
 * 
 * @author The_Fireplace
 *
 */
public class UnLogicIIKeyHandler {
	public static final int TOGGLEAMMO = 0;
	private static final String[] desc = {"key.toggleammo.desc"};
	private static final int[] keyValues = {Keyboard.KEY_R};
	private final KeyBinding[] keys;
	public UnLogicIIKeyHandler(){
		keys = new KeyBinding[desc.length];
		for(int i = 0; i < desc.length; ++i){
			keys[i] = new KeyBinding(desc[i], keyValues[i], "key.unlogicii.category");
			ClientRegistry.registerKeyBinding(keys[i]);
		}
	}
}
