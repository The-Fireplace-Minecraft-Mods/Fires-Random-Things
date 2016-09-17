package the_fireplace.frt.api;

import net.minecraft.item.ItemStack;
import the_fireplace.frt.entity.projectile.EntityBazookaAmmo;

/**
 * @author The_Fireplace
 */
@Deprecated
public class AmmoRegistry {
    /**
     * Adds an ammo type to the Coal Gun
     * @param item
     * An itemstack containing the item and metadata to fire.
     * @param ammoClass
     * Your ammo entity's class.
     */
    @Deprecated
    public static void addAmmo(ItemStack item, Class<? extends EntityBazookaAmmo> ammoClass){
        System.out.println("Mod adding "+ammoClass.toString()+" as an ammo needs to update their FRT Compat code");
    }
}
