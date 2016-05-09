package the_fireplace.frt.entity.coal;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

/**
 * @author The_Fireplace
 */
public class EntityCoal extends EntityCoalCommon {

    public EntityCoal(World worldIn) {
        super(worldIn);
    }

    public EntityCoal(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
    }

    public EntityCoal(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    protected void executeImpact(RayTraceResult mop) {
        if (mop.entityHit != null) {
            mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getPlayerThrower()), 1.0F);
        }
    }
}
