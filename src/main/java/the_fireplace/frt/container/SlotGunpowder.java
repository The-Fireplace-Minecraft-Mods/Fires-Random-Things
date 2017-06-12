package the_fireplace.frt.container;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.recipes.ShattererRecipes;
import the_fireplace.frt.tileentity.TileEntityShatterer;

import javax.annotation.Nonnull;

/**
 * @author The_Fireplace
 */
public class SlotGunpowder extends Slot {
	TileEntityShatterer inv;

	public SlotGunpowder(TileEntityShatterer inventoryIn, int index, int xPosition,
	                     int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		inv = inventoryIn;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return ShattererRecipes.instance().isGunpowder(stack);
	}

	@Override
	public void putStack(@Nonnull ItemStack stack) {
		if (!stack.isEmpty()) {
			this.inventory.setInventorySlotContents(this.getSlotIndex(), stack);
			if (stack.getItem().isDamageable())
				inv.addToGunpowder(stack.getMaxDamage() - stack.getItemDamage());
			else
				inv.addToGunpowder(stack.getCount());
			this.inventory.setInventorySlotContents(getSlotIndex(), ItemStack.EMPTY);
		}
	}
}
