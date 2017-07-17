package the_fireplace.frt.handlers;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import the_fireplace.frt.entity.projectile.EntityChargedCoal;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * @author The_Fireplace
 */
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class DispenseBehaviorChargedCoal extends BehaviorProjectileDispense {
	@Override
	protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stack) {
		return new EntityChargedCoal(worldIn, position.getX(), position.getY(), position.getZ());
	}
}
