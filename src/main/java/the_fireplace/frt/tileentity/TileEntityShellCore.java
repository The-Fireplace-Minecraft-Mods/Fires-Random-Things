package the_fireplace.frt.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.frt.FRT;
import the_fireplace.frt.tools.MiscTools;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * @author The_Fireplace
 */
public class TileEntityShellCore extends TileEntity implements ITickable, ISidedInventory {
	private ItemStack[] inventory;
	public static final String PROP_NAME = "TileEntityShellCore";
	int storedRedstone = 0;

	public boolean isActive = false;
	public int tempItemCounter = 0;

	public TileEntityShellCore() {
		inventory = new ItemStack[1];
	}

	@Override
	public void update() {
		world.getBlockState(this.pos).getBlock().updateTick(world, pos, world.getBlockState(this.pos), new Random());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		return new SPacketUpdateTileEntity(this.pos, getBlockMetadata(), getUpdateTag());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public String getName() {
		return FRT.proxy.translateToLocal("tile.shell_core.name");
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	@Nonnull
	public ITextComponent getDisplayName() {
		return new TextComponentTranslation("tile.shell_core.name");
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemStack : inventory)
			if (!itemStack.isEmpty())
				return false;
		return true;
	}

	@Override
	@Nonnull
	public ItemStack getStackInSlot(int index) {
		if (inventory[index] != null)
			return inventory[index];
		else
			return ItemStack.EMPTY;
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		ItemStack is = getStackInSlot(index);
		if (!is.isEmpty()) {
			if (is.getCount() <= count) {
				setInventorySlotContents(index, ItemStack.EMPTY);
			} else {
				is = is.splitStack(count);
				markDirty();
			}
		}
		return is;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack is = getStackInSlot(index);
		setInventorySlotContents(index, ItemStack.EMPTY);
		return is;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		inventory[index] = stack;

		if (!stack.isEmpty() && stack.getCount() > getInventoryStackLimit()) {
			stack.setCount(getInventoryStackLimit());
		}
		markDirty();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		for (int i = 0; i < inventory.length; ++i) {
			inventory[i] = ItemStack.EMPTY;
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setInteger("StoredRedstone", storedRedstone);
		compound.setBoolean("IsShellCoreActive", isActive);
		compound.setInteger("CountUntilRedstone", tempItemCounter);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.storedRedstone = compound.getInteger("StoredRedstone");
		this.isActive = compound.getBoolean("IsShellCoreActive");
		this.tempItemCounter = compound.getInteger("CountUntilRedstone");
		super.readFromNBT(compound);
	}

	public void addToRedstone(int amount) {
		storedRedstone += amount;
		if (this.world.getMinecraftServer() != null)
			for (WorldServer server : this.world.getMinecraftServer().worlds) {
				server.getPlayerChunkMap().markBlockForUpdate(getPos());
			}
	}

	public void removeFromRedstone(int amount) {
		storedRedstone -= amount;
		if (storedRedstone < 0) {
			storedRedstone = 0;
		}
		if (this.world.getMinecraftServer() != null)
			for (WorldServer server : this.world.getMinecraftServer().worlds) {
				server.getPlayerChunkMap().markBlockForUpdate(getPos());
			}
	}

	public int getStoredRedstone() {
		return storedRedstone;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return new int[]{0};
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, EnumFacing direction) {
		if (!stack.isEmpty()) {
			if (index == 0) {
				for (ItemStack oreDict : OreDictionary.getOres("dustRedstone")) {
					if (MiscTools.areItemStacksEqual(oreDict, stack))
						return true;
				}
				for (ItemStack oreDict : OreDictionary.getOres("blockRedstone")) {
					if (MiscTools.areItemStacksEqual(oreDict, stack))
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return false;
	}
}
