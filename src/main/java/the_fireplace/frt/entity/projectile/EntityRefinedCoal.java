package the_fireplace.frt.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * @author The_Fireplace
 */
public class EntityRefinedCoal extends EntityBazookaAmmo {
    public EntityRefinedCoal(World worldIn) {
        super(worldIn);
    }

    public EntityRefinedCoal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityRefinedCoal(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void executeImpact(RayTraceResult mop) {
        if (mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getPlayerThrower()), 5.5F);
        }
    }
}
