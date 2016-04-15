package the_fireplace.frt.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class EntityHallucinationPotion extends EntityThrowable {
	private ItemStack potionDamage;

	public EntityHallucinationPotion(World w){super(w);}
	public EntityHallucinationPotion(World worldIn, EntityLivingBase throwerIn)
	{
		super(worldIn, throwerIn);
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
	//@Override
	protected float getVelocity()
	{
		return 0.5F;
	}
	//@Override
	protected float getInaccuracy()
	{
		return -20.0F;
	}
	@Override
	protected void onImpact(RayTraceResult mop) {
		//if (!this.worldObj.isRemote) {
			AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().expand(4.0D, 2.0D, 4.0D);
			List<EntityLivingBase> list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

			if (!list1.isEmpty()) {
				for (EntityLivingBase entitylivingbase : list1) {
					double d0 = this.getDistanceSqToEntity(entitylivingbase);

					if (d0 < 16.0D) {
						if(potionDamage.getMetadata() == 2)
							entitylivingbase.addPotionEffect(new PotionEffect(FRT.hallucination, 2700));
						else
							entitylivingbase.addPotionEffect(new PotionEffect(FRT.hallucination, 7200));
					}
				}
			}
		//}
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
