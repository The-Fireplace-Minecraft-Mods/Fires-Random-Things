package the_fireplace.frt.container;

import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import the_fireplace.frt.tileentity.TileEntityShellCore;
import the_fireplace.frt.tools.MiscTools;

/**
 * @author The_Fireplace
 */
public class SlotRedstone extends Slot {
    TileEntityShellCore inv;

    public SlotRedstone(TileEntityShellCore inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
        inv = inventoryIn;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        for(ItemStack oreDict: OreDictionary.getOres("dustRedstone")){
            if(MiscTools.areItemStacksEqual(oreDict, stack))
                return true;
        }
        for(ItemStack oreDict:OreDictionary.getOres("blockRedstone")){
            if(MiscTools.areItemStacksEqual(oreDict, stack))
                return true;
        }
        return false;
    }

    @Override
    public void putStack(ItemStack stack) {
        if (stack != null) {
            this.inventory.setInventorySlotContents(this.getSlotIndex(), stack);
            for(ItemStack oreDict:OreDictionary.getOres("dustRedstone")){
                if(MiscTools.areItemStacksEqual(oreDict, stack)) {
                    inv.addToRedstone(stack.stackSize);
                    this.inventory.setInventorySlotContents(getSlotIndex(), null);
                    return;
                }
            }
            for(ItemStack oreDict:OreDictionary.getOres("blockRedstone")){
                if(MiscTools.areItemStacksEqual(oreDict, stack)){
                    inv.addToRedstone(stack.stackSize*9);
                    this.inventory.setInventorySlotContents(getSlotIndex(), null);
                    return;
                }
            }
        }
    }
}
