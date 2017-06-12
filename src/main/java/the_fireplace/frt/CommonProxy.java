package the_fireplace.frt;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.frt.tileentity.TileEntityShatterer;
import the_fireplace.frt.tileentity.TileEntityShellCore;

/**
 * @author The_Fireplace
 */
public class CommonProxy {

	public void registerEntityRenderers() {
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityShatterer.class, "pop_furnace");
		GameRegistry.registerTileEntity(TileEntityShellCore.class, "shell_core");
	}

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	public void registerClient() {
	}

	public void tryRemoveShader() {
	}

	public void activateNextShader() {
	}

	public String translateToLocal(String s, Object... args) {
		return I18n.translateToLocalFormatted(s, args);
	}
}
