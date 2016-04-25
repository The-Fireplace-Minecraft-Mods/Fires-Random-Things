package the_fireplace.frt.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import the_fireplace.frt.container.ContainerPopFurnace;
import the_fireplace.frt.entity.tile.TileEntityPopFurnace;
import the_fireplace.frt.gui.GuiPopFurnace;
/**
 * 
 * @author The_Fireplace
 *
 */
public class FRTGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		switch(ID){
		case 0:
			if(entity != null && entity instanceof TileEntityPopFurnace){
				return new ContainerPopFurnace(player.inventory, (TileEntityPopFurnace)entity);
			}else{
				return null;
			}
		default: return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
		switch(ID){
		case 0:
			if(entity != null && entity instanceof TileEntityPopFurnace){
				return new GuiPopFurnace(player.inventory, (TileEntityPopFurnace)entity);
			}else{
				return null;
			}
		default: return null;
		}
	}

}
