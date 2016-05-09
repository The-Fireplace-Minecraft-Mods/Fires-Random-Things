package the_fireplace.frt.handlers;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import the_fireplace.frt.entity.coal.EntityRefinedCoal;

/**
 * @author The_Fireplace
 */
public class DispenseBehaviorRefinedCoal extends BehaviorProjectileDispense {
    @Override
    protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stack) {
        return new EntityRefinedCoal(worldIn, position.getX(), position.getY(), position.getZ());
    }
}
