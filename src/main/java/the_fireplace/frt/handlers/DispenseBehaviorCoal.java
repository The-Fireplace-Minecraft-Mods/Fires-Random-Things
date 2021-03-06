package the_fireplace.frt.handlers;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import the_fireplace.frt.entity.projectile.EntityCoal;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author The_Fireplace
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DispenseBehaviorCoal extends BehaviorProjectileDispense {
	@Override
	protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stack) {
		return new EntityCoal(worldIn, position.getX(), position.getY(), position.getZ());
	}
}
