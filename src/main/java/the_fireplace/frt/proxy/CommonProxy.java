package the_fireplace.frt.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import the_fireplace.frt.entity.tile.TileEntityBazooka;
import the_fireplace.frt.entity.tile.TileEntityShatterer;

/**
 * @author The_Fireplace
 */
public class CommonProxy {
    public void registerRenderers() {
    }//Leave empty on server side

    public void registerEntityRenderers() {
    }//Fired preinit instead of init

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityBazooka.class, TileEntityBazooka.publicName);
        GameRegistry.registerTileEntity(TileEntityShatterer.class, "pop_furnace");
    }

    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }

    public void registerClient() {
    }

    public void tryRemoveShader() {
    }

    public void activateNextShader() {
    }

    public String translateToLocal(String s){
        return s;
    }
}
