package the_fireplace.frt.container;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.entity.tile.TileEntityPopFurnace;
import the_fireplace.frt.recipes.PopFurnaceRecipes;

/**
 * @author The_Fireplace
 */
public class SlotGunpowder extends Slot {
    TileEntityPopFurnace inv;

    public SlotGunpowder(TileEntityPopFurnace inventoryIn, int index, int xPosition,
                         int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        inv = inventoryIn;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return PopFurnaceRecipes.instance().isGunpowder(stack);
    }

    @Override
    public void putStack(ItemStack stack) {
        if (stack != null) {
            this.inventory.setInventorySlotContents(this.getSlotIndex(), stack);
            if (stack.getItem().isDamageable())
                inv.addToGunpowder(stack.getMaxDamage() - stack.getItemDamage());
            else
                inv.addToGunpowder(stack.stackSize);
            this.inventory.setInventorySlotContents(getSlotIndex(), null);
        }
    }
}
