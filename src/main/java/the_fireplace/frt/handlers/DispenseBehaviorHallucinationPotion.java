package the_fireplace.frt.handlers;

import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import the_fireplace.frt.entity.EntityHallucinationPotion;

/**
 * @author The_Fireplace
 */
public class DispenseBehaviorHallucinationPotion extends BehaviorProjectileDispense {
	private int damage=3;
	public DispenseBehaviorHallucinationPotion(){}
	public DispenseBehaviorHallucinationPotion(int d){
		this.damage=d;
	}
	@Override
	protected IProjectile getProjectileEntity(World worldIn, IPosition position, ItemStack stack){
		return new EntityHallucinationPotion(worldIn, position.getX(), position.getY(), position.getZ(), damage);
	}
}
