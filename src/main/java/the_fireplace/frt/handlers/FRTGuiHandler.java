package the_fireplace.frt.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import the_fireplace.frt.client.gui.GuiShatterer;
import the_fireplace.frt.client.gui.GuiShellCore;
import the_fireplace.frt.container.ContainerShatterer;
import the_fireplace.frt.container.ContainerShellCore;
import the_fireplace.frt.tileentity.TileEntityShatterer;
import the_fireplace.frt.tileentity.TileEntityShellCore;

/**
 * @author The_Fireplace
 */
public class FRTGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0:
                if (entity != null && entity instanceof TileEntityShatterer) {
                    return new ContainerShatterer(player.inventory, (TileEntityShatterer) entity);
                } else {
                    return null;
                }
            case 1:
                if (entity != null && entity instanceof TileEntityShellCore) {
                    return new ContainerShellCore(player.inventory, (TileEntityShellCore) entity);
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
        switch (ID) {
            case 0:
                if (entity != null && entity instanceof TileEntityShatterer) {
                    return new GuiShatterer(player.inventory, (TileEntityShatterer) entity);
                } else {
                    return null;
                }
            case 1:
                if (entity != null && entity instanceof TileEntityShellCore) {
                    return new GuiShellCore(player.inventory, (TileEntityShellCore) entity);
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

}
