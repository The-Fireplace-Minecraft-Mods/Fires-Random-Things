package the_fireplace.frt.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import the_fireplace.frt.config.ConfigValues;

/**
 * @author The_Fireplace
 */
public class EntityRestabilizedCoal extends EntityBazookaAmmo {
    public EntityRestabilizedCoal(World worldIn) {
        super(worldIn);
    }

    public EntityRestabilizedCoal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityRestabilizedCoal(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void executeImpact(RayTraceResult mop) {
        if (mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getPlayerThrower()), 4.0F);
            worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 0.5F, ConfigValues.ENABLEDAMAGE);
        } else {
            worldObj.createExplosion(this, mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ(), 0.5F, ConfigValues.ENABLEDAMAGE);
        }
    }
}
