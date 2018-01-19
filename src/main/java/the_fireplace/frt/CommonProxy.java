package the_fireplace.frt;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.frt.tileentity.TileEntityForceFieldCore;
import the_fireplace.frt.tileentity.TileEntityIRFurnace;
import the_fireplace.frt.tileentity.TileEntityItemExploder;

/**
 * @author The_Fireplace
 */
public class CommonProxy {

	public void registerEntityRenderers() {
	}

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityItemExploder.class, "pop_furnace");
		GameRegistry.registerTileEntity(TileEntityForceFieldCore.class, "shell_core");
		GameRegistry.registerTileEntity(TileEntityIRFurnace.class, "ir_furnace");
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
