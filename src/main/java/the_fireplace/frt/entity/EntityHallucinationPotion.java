package the_fireplace.frt.entity;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.network.UpdatePotionMessage;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class EntityHallucinationPotion extends EntityThrowable {
	private ItemStack potionDamage;

	public EntityHallucinationPotion(World w){
		super(w);
		this.potionDamage = new ItemStack(FRT.hallucination_potion, 1, 0);//TODO: This should not be necessary, this constructor should not be used
	}
	public EntityHallucinationPotion(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
		this.potionDamage = new ItemStack(FRT.hallucination_potion, 1, 0);//TODO: This should not be necessary, this constructor should not be used
	}
	public EntityHallucinationPotion(World worldIn, double x, double y, double z, int d)
	{
		super(worldIn);
		this.setPosition(x, y, z);
		this.potionDamage = new ItemStack(FRT.hallucination_potion, 1, d);
	}
	public EntityHallucinationPotion(World worldIn, EntityLivingBase throwerIn, ItemStack potionDamageIn){
		super(worldIn, throwerIn);
		this.potionDamage = potionDamageIn;
	}
	@Override
	protected float getGravityVelocity()
	{
		return 0.05F;
	}
	@Override
	protected void onImpact(RayTraceResult mop) {
		if (!this.worldObj.isRemote) {
			AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
			List<EntityLivingBase> list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

			if (!list1.isEmpty()) {
				for (EntityLivingBase entitylivingbase : list1) {
					double d0 = this.getDistanceSqToEntity(entitylivingbase);

					if (d0 < 16.0D) {
						if (potionDamage.getMetadata() == 2) {
							entitylivingbase.addPotionEffect(new PotionEffect(FRT.hallucination, 2700));
							PacketDispatcher.sendTo(new UpdatePotionMessage(2700), (EntityPlayerMP)entitylivingbase);
						}else if (potionDamage.getMetadata() == 3) {
							entitylivingbase.addPotionEffect(new PotionEffect(FRT.hallucination, 7200));
							PacketDispatcher.sendTo(new UpdatePotionMessage(7200), (EntityPlayerMP)entitylivingbase);
						}
					}
				}
			}
			if (potionDamage.getMetadata() == 4) {
				ItemStack itemstack = potionDamage;
				PotionType potiontype = PotionUtils.getPotionFromItem(itemstack);

				EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.worldObj, this.posX, this.posY, this.posZ);
				entityareaeffectcloud.setOwner(this.getThrower());
				entityareaeffectcloud.setRadius(3.0F);
				entityareaeffectcloud.setRadiusOnUse(-0.5F);
				entityareaeffectcloud.setWaitTime(10);
				entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float) entityareaeffectcloud.getDuration());
				entityareaeffectcloud.setPotion(potiontype);

				for (PotionEffect potioneffect : PotionUtils.getFullEffectsFromItem(itemstack)) {
					entityareaeffectcloud.addEffect(new PotionEffect(potioneffect.getPotion(), potioneffect.getDuration(), potioneffect.getAmplifier()));
				}

				this.worldObj.spawnEntityInWorld(entityareaeffectcloud);
			}
		}
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompund)
	{
		super.readEntityFromNBT(tagCompund);

		if (tagCompund.hasKey("Potion", 10))
		{
			this.potionDamage = ItemStack.loadItemStackFromNBT(tagCompund.getCompoundTag("Potion"));
		}

		if (this.potionDamage == null)
		{
			this.setDead();
		}
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		super.writeEntityToNBT(tagCompound);

		if (this.potionDamage != null)
		{
			tagCompound.setTag("Potion", this.potionDamage.writeToNBT(new NBTTagCompound()));
		}
	}
}
