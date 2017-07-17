package the_fireplace.frt.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.tileentity.TileEntityForceFieldCore;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author The_Fireplace
 */
@ParametersAreNonnullByDefault
public class ContainerForceFieldCore extends Container {
	private TileEntityForceFieldCore te;

	public ContainerForceFieldCore(InventoryPlayer invPlayer, TileEntityForceFieldCore entity) {
		this.te = entity;

		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(invPlayer, x, 8 + x * 18, 142 - 14 - 4 - 6 - 5));//player inventory IDs 0 to 8
		}

		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(invPlayer, 9 + x + y * 9, 8 + x * 18, 84 + y * 18 - 14 - 4 - 6 - 5));//player inventory IDs 9 to 35
			}
		}

		this.addSlotToContainer(new SlotRedstone(entity, 0, 80, 26 - 4 - 6 - 5 + 10));
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return te.isUsableByPlayer(playerIn);
	}

	@Override
	@Nonnull
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = getSlot(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack is = slot.getStack();
			ItemStack result = is.copy();

			if (i >= 36) {
				if (!mergeItemStack(is, 0, 36, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!mergeItemStack(is, 36, 36 + te.getSizeInventory(), false)) {
				return ItemStack.EMPTY;
			}
			if (is.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			slot.onTake(player, is);
			return result;
		}
		return ItemStack.EMPTY;
	}
}
