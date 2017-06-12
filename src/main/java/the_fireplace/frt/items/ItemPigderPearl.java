package the_fireplace.frt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;
import the_fireplace.frt.entity.projectile.EntityPigderPearl;

/**
 * @author The_Fireplace
 */
public class ItemPigderPearl extends Item {
	public ItemPigderPearl() {
		maxStackSize = 16;
		setCreativeTab(FRT.TabFRT);
		setUnlocalizedName("pigder_pearl");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (!playerIn.capabilities.isCreativeMode) {
			itemStackIn.shrink(1);
		}

		worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERPEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldownTracker().setCooldown(this, 20);

		if (!worldIn.isRemote) {
			EntityPigderPearl pigderPearl = new EntityPigderPearl(worldIn, playerIn);
			pigderPearl.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
			worldIn.spawnEntity(pigderPearl);
		}

		return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	}
}
