package the_fireplace.frt.entity.coal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import the_fireplace.frt.config.ConfigValues;

/**
 * @author The_Fireplace
 */
public class EntityChargedCoal extends EntityCoalCommon {

    public EntityChargedCoal(World worldIn) {
        super(worldIn);
    }

    public EntityChargedCoal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityChargedCoal(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void executeImpact(RayTraceResult mop) {
        if (mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getPlayerThrower()), 2.0F);
            worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.0F, ConfigValues.ENABLEDAMAGE);
        } else {
            worldObj.createExplosion(this, mop.getBlockPos().getX(), mop.getBlockPos().getY(), mop.getBlockPos().getZ(), 1.0F, ConfigValues.ENABLEDAMAGE);
        }
    }
}
