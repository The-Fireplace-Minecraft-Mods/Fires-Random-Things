package the_fireplace.frt.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.frt.entity.tile.TileEntityCoalGun;
import the_fireplace.frt.entity.tile.TileEntityPopFurnace;
/**
 * 
 * @author The_Fireplace
 *
 */
public class CommonProxy {
	public void registerRenderers(){}//Leave empty on server side
	public void registerEntityRenderers(){}//Fired preinit instead of init
	public void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityCoalGun.class, TileEntityCoalGun.publicName);
		GameRegistry.registerTileEntity(TileEntityPopFurnace.class, "pop_furnace");
	}

	public EntityPlayer getPlayerEntity(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity;
	}

	public void registerClient(){}

	public void tryRemoveShader(){}

	public void activateNextShader(){}
}
