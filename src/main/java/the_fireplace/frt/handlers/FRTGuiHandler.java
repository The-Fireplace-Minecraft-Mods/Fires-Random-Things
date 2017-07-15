package the_fireplace.frt.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import the_fireplace.frt.client.gui.GuiItemExploder;
import the_fireplace.frt.client.gui.GuiForceFieldCore;
import the_fireplace.frt.container.ContainerItemExploder;
import the_fireplace.frt.container.ContainerForceFieldCore;
import the_fireplace.frt.tileentity.TileEntityForceFieldCore;
import the_fireplace.frt.tileentity.TileEntityItemExploder;

/**
 * @author The_Fireplace
 */
public class FRTGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		switch (ID) {
			case 0:
				if (entity != null && entity instanceof TileEntityItemExploder) {
					return new ContainerItemExploder(player.inventory, (TileEntityItemExploder) entity);
				} else {
					return null;
				}
			case 1:
				if (entity != null && entity instanceof TileEntityForceFieldCore) {
					return new ContainerForceFieldCore(player.inventory, (TileEntityForceFieldCore) entity);
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
				if (entity != null && entity instanceof TileEntityItemExploder) {
					return new GuiItemExploder(player.inventory, (TileEntityItemExploder) entity);
				} else {
					return null;
				}
			case 1:
				if (entity != null && entity instanceof TileEntityForceFieldCore) {
					return new GuiForceFieldCore(player.inventory, (TileEntityForceFieldCore) entity);
				} else {
					return null;
				}
			default:
				return null;
		}
	}

}
