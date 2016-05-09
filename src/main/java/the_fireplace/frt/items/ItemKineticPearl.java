package the_fireplace.frt.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class ItemKineticPearl extends Item {
    public ItemKineticPearl() {
        setUnlocalizedName("kinetic_pearl");
        setCreativeTab(FRT.TabFRT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        double prevX = playerIn.motionX;
        double prevY = playerIn.motionY;
        double prevZ = playerIn.motionZ;
        AxisAlignedBB bb = playerIn.getEntityBoundingBox().expandXyz(8);
        worldIn.getEntitiesWithinAABB(Entity.class, bb).stream().filter(entity -> entity != playerIn).forEach(entity -> {
            playerIn.addVelocity(entity.motionX, entity.motionY, entity.motionZ);
            entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
        });
        if (Math.abs(playerIn.motionX - prevX) > 0.25 || Math.abs(playerIn.motionY - prevY) > 0.25 || Math.abs(playerIn.motionZ - prevZ) > 0.25) {
            if (!playerIn.capabilities.isCreativeMode)
                itemStackIn.stackSize--;
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_bat_takeoff, SoundCategory.NEUTRAL, 0.3F, 0.5F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}
