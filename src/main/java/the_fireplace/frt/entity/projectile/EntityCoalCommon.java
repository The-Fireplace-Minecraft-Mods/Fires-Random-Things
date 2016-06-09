package the_fireplace.frt.entity.projectile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class EntityCoalCommon extends EntityThrowable implements IProjectile {
    protected int xTile = -1;
    protected int yTile = -1;
    protected int zTile = -1;
    protected Block inTile;
    /**
     * The entity that threw this throwable item.
     */
    protected EntityLivingBase thrower;
    protected String throwerName;
    protected int ticksInGround;
    protected int ticksInAir;

    public EntityCoalCommon(World worldIn) {
        super(worldIn);
        this.setSize(0.25F, 0.25F);
    }

    public EntityCoalCommon(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn);
        this.thrower = throwerIn;
        this.setSize(0.25F, 0.25F);
        this.setLocationAndAngles(throwerIn.posX, throwerIn.posY + throwerIn.getEyeHeight(), throwerIn.posZ, throwerIn.rotationYaw, throwerIn.rotationPitch);
        this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.posY -= 0.10000000149011612D;
        this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(this.posX, this.posY, this.posZ);
        float f = 0.4F;
        this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f;
        this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float) Math.PI) * f;
        this.motionY = -MathHelper.sin((this.rotationPitch + this.getInaccuracy()) / 180.0F * (float) Math.PI) * f;
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.getVelocity(), 1.0F);
    }

    public EntityCoalCommon(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
        this.ticksInGround = 0;
        this.setSize(0.25F, 0.25F);
        this.setPosition(x, y, z);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isInRangeToRenderDist(double distance) {
        double d1 = this.getEntityBoundingBox().getAverageEdgeLength() * 4.0D;
        d1 *= 64.0D;
        return distance < d1 * d1;
    }

    //@Override
    protected float getVelocity() {
        return 0.5F;
    }

    //@Override
    protected float getInaccuracy() {
        return 0.0F;
    }

    @Override
    protected void onImpact(RayTraceResult mop) {
        if (!this.worldObj.isRemote) {
            this.executeImpact(mop);
            this.setDead();
        }
    }

    protected void executeImpact(RayTraceResult mop) {

    }

    @Override
    public void setThrowableHeading(double x, double y, double z,
                                    float velocity, float inaccuracy) {
        float f2 = MathHelper.sqrt_double(x * x + y * y + z * z);
        x /= f2;
        y /= f2;
        z /= f2;
        x += this.rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        y += this.rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        z += this.rand.nextGaussian() * 0.007499999832361937D * inaccuracy;
        x *= velocity;
        y *= velocity;
        z *= velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt_double(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f3) * 180.0D / Math.PI);
        this.ticksInGround = 0;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z) {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float f = MathHelper.sqrt_double(x * x + z * z);
            this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * 180.0D / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f) * 180.0D / Math.PI);
        }
    }

    @Override
    public void onUpdate() {
        this.lastTickPosX = this.posX;
        this.lastTickPosY = this.posY;
        this.lastTickPosZ = this.posZ;
        super.onUpdate();

        if (this.throwableShake > 0) {
            --this.throwableShake;
        }

        if (this.inGround) {
            if (this.worldObj.getBlockState(new BlockPos(this.xTile, this.yTile, this.zTile)).getBlock() == this.inTile) {
                ++this.ticksInGround;

                if (this.ticksInGround == 1200) {
                    this.setDead();
                }

                return;
            }

            this.inGround = false;
            this.motionX *= this.rand.nextFloat() * 0.2F;
            this.motionY *= this.rand.nextFloat() * 0.2F;
            this.motionZ *= this.rand.nextFloat() * 0.2F;
            this.ticksInGround = 0;
            this.ticksInAir = 0;
        } else {
            ++this.ticksInAir;
        }

        Vec3d vec3 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult movingobjectposition = this.worldObj.rayTraceBlocks(vec3, vec31);
        vec3 = new Vec3d(this.posX, this.posY, this.posZ);
        vec31 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (movingobjectposition != null) {
            vec31 = new Vec3d(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        if (!this.worldObj.isRemote) {
            Entity entity = null;
            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double d0 = 0.0D;
            EntityLivingBase entitylivingbase = this.getPlayerThrower();

            for (Object aList : list) {
                Entity entity1 = (Entity) aList;

                if (entity1.canBeCollidedWith() && (entity1 != entitylivingbase || this.ticksInAir >= 5)) {
                    float f = 0.3F;
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f, f, f);
                    RayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);

                    if (movingobjectposition1 != null) {
                        double d1 = vec3.distanceTo(movingobjectposition1.hitVec);

                        if (d1 < d0 || d0 == 0.0D) {
                            entity = entity1;
                            d0 = d1;
                        }
                    }
                }
            }

            if (entity != null) {
                movingobjectposition = new RayTraceResult(entity);
            }
        }

        if (movingobjectposition != null) {
            if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK && this.worldObj.getBlockState(movingobjectposition.getBlockPos()).getBlock() == Blocks.PORTAL) {
                this.inPortal = true;
            } else {
                this.onImpact(movingobjectposition);
            }
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;
        float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

        while (this.rotationPitch - this.prevRotationPitch >= 180.0F) {
            this.prevRotationPitch += 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw < -180.0F) {
            this.prevRotationYaw -= 360.0F;
        }

        while (this.rotationYaw - this.prevRotationYaw >= 180.0F) {
            this.prevRotationYaw += 360.0F;
        }

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
        float f2 = 0.99F;
        float f3 = this.getGravityVelocity();

        if (this.isInWater()) {
            for (int i = 0; i < 4; ++i) {
                float f4 = 0.25F;
                this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
            }

            f2 = 0.8F;
        } else {
            for (int i = 0; i < 4; ++i) {
                float f4 = 0.25F;
                this.worldObj.spawnParticle(EnumParticleTypes.FLAME, this.posX - this.motionX * f4, this.posY - this.motionY * f4, this.posZ - this.motionZ * f4, this.motionX, this.motionY, this.motionZ);
            }

            f2 = 0.99F;
        }

        this.motionX *= f2;
        this.motionY *= f2;
        this.motionZ *= f2;
        this.motionY -= f3;
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    @Override
    protected float getGravityVelocity() {
        return 0.001F;//0.03F
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        tagCompound.setShort("xTile", (short) this.xTile);
        tagCompound.setShort("yTile", (short) this.yTile);
        tagCompound.setShort("zTile", (short) this.zTile);
        ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(this.inTile);
        tagCompound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        tagCompound.setByte("shake", (byte) this.throwableShake);
        tagCompound.setByte("inGround", (byte) (this.inGround ? 1 : 0));

        if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower instanceof EntityPlayer) {
            this.throwerName = this.thrower.getName();
        }

        tagCompound.setString("ownerName", this.throwerName == null ? "" : this.throwerName);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        this.xTile = tagCompund.getShort("xTile");
        this.yTile = tagCompund.getShort("yTile");
        this.zTile = tagCompund.getShort("zTile");

        if (tagCompund.hasKey("inTile", 8)) {
            this.inTile = Block.getBlockFromName(tagCompund.getString("inTile"));
        } else {
            this.inTile = Block.getBlockById(tagCompund.getByte("inTile") & 255);
        }

        this.throwableShake = tagCompund.getByte("shake") & 255;
        this.inGround = tagCompund.getByte("inGround") == 1;
        this.throwerName = tagCompund.getString("ownerName");

        if (this.throwerName != null && this.throwerName.length() == 0) {
            this.throwerName = null;
        }
    }

    public EntityLivingBase getPlayerThrower() {
        if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0) {
            this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
        }

        return this.thrower;
    }
}
