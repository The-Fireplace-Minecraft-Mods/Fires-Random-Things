package the_fireplace.frt.recipes;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.tools.MiscTools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author The_Fireplace
 */
public class PopFurnaceRecipes {
    public static final PopFurnaceRecipes pfBase = new PopFurnaceRecipes();
    private final Map poppingList = Maps.newHashMap();
    private ArrayList firestarters = new ArrayList();
    private ArrayList gunpowders = new ArrayList();

    public static PopFurnaceRecipes instance() {
        return pfBase;
    }

    /**
     * Adds a recipe to the Pop Furnace
     *
     * @param isIn        The itemstack of the input, containing the item and metadata
     * @param isOut       The itemstack of the output, containing the item and metadata
     * @param resultCount The number of items to output per input
     */
    public void addPopFurnaceRecipe(ItemStack isIn, ItemStack isOut, int resultCount) {
        poppingList.put(new ItemStack(isIn.getItem(), 1, isIn.getMetadata()), new ItemStack(isOut.getItem(), resultCount, isOut.getMetadata()));
    }

    /**
     * Gets the result of popping an item
     *
     * @param is The input
     * @return The result of popping the item
     */
    public ItemStack getPoppingResult(ItemStack is) {
        Iterator iterator = poppingList.entrySet().iterator();
        Entry entry;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            entry = (Entry) iterator.next();
        }
        while (!MiscTools.areItemStacksEqual(new ItemStack(is.getItem(), 1, is.getMetadata()), (ItemStack) entry.getKey()));
        return (ItemStack) entry.getValue();
    }

    /**
     * Gets how many items will be output
     *
     * @param is The <b>input</b> ItemStack in the pop furnace recipe
     * @return The number of items output for that input
     */
    public int getResultCount(ItemStack is) {
        Iterator iterator = poppingList.entrySet().iterator();
        Entry entry;
        do {
            if (!iterator.hasNext()) {
                return 0;
            }
            entry = (Entry) iterator.next();
        }
        while (!MiscTools.areItemStacksEqual(new ItemStack(is.getItem(), 1, is.getMetadata()), (ItemStack) entry.getKey()));
        return ((ItemStack) entry.getValue()).stackSize;
    }

    public Map getPoppingList() {
        return poppingList;
    }

    public ArrayList getFirestarters() {
        return firestarters;
    }

    public ArrayList getGunpowders() {
        return gunpowders;
    }

    public void addFirestarters(ItemStack... items) {
        int i = 0;
        while (i < items.length) {
            if (items[i].getItem().isDamageable())
                firestarters.add(new ItemStack(items[i].getItem()));
            else
                firestarters.add(new ItemStack(items[i].getItem(), 1, items[i].getMetadata()));
            i++;
        }
    }

    public void addGunpowders(ItemStack... items) {
        int i = 0;
        while (i < items.length) {
            if (items[i].getItem().isDamageable())
                gunpowders.add(new ItemStack(items[i].getItem()));
            else
                gunpowders.add(new ItemStack(items[i].getItem(), 1, items[i].getMetadata()));
            i++;
        }
    }

    public boolean isFirestarter(ItemStack item) {
        if (firestarters == null) {
            return false;
        }
        for (Object firestarter : firestarters) {
            if (item.getItem().isDamageable()) {
                if (MiscTools.areItemStacksEqual((ItemStack) firestarter, new ItemStack(item.getItem()))) {
                    return true;
                }
            } else if (MiscTools.areItemStacksEqual((ItemStack) firestarter, new ItemStack(item.getItem(), 1, item.getMetadata()))) {
                return true;
            }
        }
        return false;
    }

    public boolean isGunpowder(ItemStack item) {
        if (gunpowders == null) {
            return false;
        }
        for (Object gunpowder : gunpowders) {
            if (item.getItem().isDamageable()) {
                if (MiscTools.areItemStacksEqual((ItemStack) gunpowder, new ItemStack(item.getItem()))) {
                    return true;
                }
            } else if (MiscTools.areItemStacksEqual((ItemStack) gunpowder, new ItemStack(item.getItem(), 1, item.getMetadata()))) {
                return true;
            }
        }
        return false;
    }
}
