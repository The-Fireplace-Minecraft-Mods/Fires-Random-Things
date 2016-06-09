package the_fireplace.frt.recipes;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import the_fireplace.frt.entity.projectile.EntityBazookaAmmo;
import the_fireplace.frt.tools.MiscTools;

import java.util.Iterator;
import java.util.Map;

/**
 * @author The_Fireplace
 */
public class AmmoTypes {
    public static final AmmoTypes ammoBase = new AmmoTypes();
    private final Map<ItemStack, Class<? extends EntityBazookaAmmo>> ammoList = Maps.newHashMap();

    public static AmmoTypes instance() {
        return ammoBase;
    }

    public void addAmmo(ItemStack item, Class<? extends EntityBazookaAmmo> ammoClass){
        ammoList.put(new ItemStack(item.getItem(), 1, item.getMetadata()), ammoClass);
    }

    public Class<? extends EntityBazookaAmmo> getAmmoEntity(ItemStack item) {
        Iterator iterator = ammoList.entrySet().iterator();
        Map.Entry entry;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            entry = (Map.Entry) iterator.next();
        }
        while (!MiscTools.areItemStacksEqual(new ItemStack(item.getItem(), 1, item.getMetadata()), (ItemStack) entry.getKey()));
        return (Class<? extends EntityBazookaAmmo>) entry.getValue();
    }

    public Map getAmmoMap() {
        return ammoList;
    }
}
