package the_fireplace.frt.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.tileentity.TileEntityIRFurnace;

public class SlotIRFurnaceFuel extends Slot
{
    public SlotIRFurnaceFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return TileEntityIRFurnace.isItemFuel(stack);
    }

    @Override
    public int getItemStackLimit(ItemStack stack)
    {
        return 64;
    }
}