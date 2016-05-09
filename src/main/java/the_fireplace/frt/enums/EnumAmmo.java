package the_fireplace.frt.enums;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;
import the_fireplace.frt.entity.coal.*;

/**
 * @author The_Fireplace
 */
public enum EnumAmmo {
    COAL,
    CHARGED_COAL,
    DESTABILIZED_COAL,
    RESTABILIZED_COAL,
    REFINED_COAL;

    public static EnumAmmo getNext(EnumAmmo ammo) {
        if (ammo == COAL) {
            return CHARGED_COAL;
        } else if (ammo == CHARGED_COAL) {
            return DESTABILIZED_COAL;
        } else if (ammo == DESTABILIZED_COAL) {
            return RESTABILIZED_COAL;
        } else if (ammo == RESTABILIZED_COAL) {
            return REFINED_COAL;
        } else {
            return COAL;
        }
    }

    public EnumAmmo next() {
        if (this == COAL) {
            return CHARGED_COAL;
        } else if (this == CHARGED_COAL) {
            return DESTABILIZED_COAL;
        } else if (this == DESTABILIZED_COAL) {
            return RESTABILIZED_COAL;
        } else if (this == RESTABILIZED_COAL) {
            return REFINED_COAL;
        } else {
            return COAL;
        }
    }

    public static String getStringFromAmmo(EnumAmmo ammo) {
        if (ammo == COAL) {
            return "COAL";
        } else if (ammo == CHARGED_COAL) {
            return "CHARGED_COAL";
        } else if (ammo == DESTABILIZED_COAL) {
            return "DESTABILIZED_COAL";
        } else if (ammo == RESTABILIZED_COAL) {
            return "RESTABILIZED_COAL";
        } else if (ammo == REFINED_COAL) {
            return "REFINED_COAL";
        } else {
            return "COAL";
        }
    }

    @Override
    public String toString() {
        if (this == COAL) {
            return "COAL";
        } else if (this == CHARGED_COAL) {
            return "CHARGED_COAL";
        } else if (this == DESTABILIZED_COAL) {
            return "DESTABILIZED_COAL";
        } else if (this == RESTABILIZED_COAL) {
            return "RESTABILIZED_COAL";
        } else if (this == REFINED_COAL) {
            return "REFINED_COAL";
        } else {
            return "COAL";
        }
    }

    public static EnumAmmo getAmmoFromString(String string) {
        switch (string) {
            case "COAL":
                return COAL;
            case "CHARGED_COAL":
                return CHARGED_COAL;
            case "DESTABILIZED_COAL":
                return DESTABILIZED_COAL;
            case "RESTABILIZED_COAL":
                return RESTABILIZED_COAL;
            case "REFINED_COAL":
                return REFINED_COAL;
            default:
                return COAL;
        }
    }

    public static Item getItem(EnumAmmo ammo) {
        if (ammo == COAL) {
            return Items.coal;
        } else if (ammo == CHARGED_COAL) {
            return FRT.charged_coal;
        } else if (ammo == DESTABILIZED_COAL) {
            return FRT.destabilized_coal;
        } else if (ammo == RESTABILIZED_COAL) {
            return FRT.restabilized_coal;
        } else if (ammo == REFINED_COAL) {
            return FRT.refined_coal;
        } else {
            return Items.coal;
        }
    }

    public Item toItem() {
        if (this == COAL) {
            return Items.coal;
        } else if (this == CHARGED_COAL) {
            return FRT.charged_coal;
        } else if (this == DESTABILIZED_COAL) {
            return FRT.destabilized_coal;
        } else if (this == RESTABILIZED_COAL) {
            return FRT.restabilized_coal;
        } else if (this == REFINED_COAL) {
            return FRT.refined_coal;
        } else {
            return Items.coal;
        }
    }

    public Entity makeEntity(World world, EntityPlayer player) {
        if (this == COAL) {
            return new EntityCoal(world, player);
        } else if (this == CHARGED_COAL) {
            return new EntityChargedCoal(world, player);
        } else if (this == DESTABILIZED_COAL) {
            return new EntityDestabilizedCoal(world, player);
        } else if (this == RESTABILIZED_COAL) {
            return new EntityRestabilizedCoal(world, player);
        } else if (this == REFINED_COAL) {
            return new EntityRefinedCoal(world, player);
        } else {
            System.out.println("Severe error: These are not the droids you are looking for.");
            return null;
        }
    }

    public static EnumAmmo fromPlayer(EntityPlayer player) {
        if (player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND) != null)
            if (getAmmoFromItem(player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem()) != null)
                return getAmmoFromItem(player.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem());
        return null;
    }

    public static EnumAmmo getAmmoFromItem(Item item) {
        if (item.equals(Items.coal))
            return COAL;
        if (item.equals(FRT.charged_coal))
            return CHARGED_COAL;
        if (item.equals(FRT.destabilized_coal))
            return DESTABILIZED_COAL;
        if (item.equals(FRT.restabilized_coal))
            return RESTABILIZED_COAL;
        if (item.equals(FRT.refined_coal))
            return REFINED_COAL;
        return null;
    }
}
