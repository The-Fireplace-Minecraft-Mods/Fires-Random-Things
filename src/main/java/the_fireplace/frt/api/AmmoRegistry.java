package the_fireplace.frt.api;

import net.minecraft.item.ItemStack;
import the_fireplace.frt.entity.projectile.EntityBazookaAmmo;
import the_fireplace.frt.recipes.AmmoTypes;

/**
 * @author The_Fireplace
 */
public class AmmoRegistry {
    /**
     * Adds an ammo type to the Coal Gun
     * @param item
     * An itemstack containing the item and metadata to fire.
     * @param ammoClass
     * Your ammo entity's class.
     */
    public static void addAmmo(ItemStack item, Class<? extends EntityBazookaAmmo> ammoClass){
        AmmoTypes.instance().addAmmo(item, ammoClass);
    }
}
