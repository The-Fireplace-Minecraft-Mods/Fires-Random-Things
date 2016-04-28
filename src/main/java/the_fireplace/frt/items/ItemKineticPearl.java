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
    public ItemKineticPearl(){
        setUnlocalizedName("kinetic_pearl");
        setCreativeTab(FRT.TabFRT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        double prevX = playerIn.motionX;
        double prevY = playerIn.motionY;
        double prevZ = playerIn.motionZ;
        AxisAlignedBB bb = playerIn.getEntityBoundingBox().expandXyz(8);
        for(Entity entity:worldIn.getEntitiesWithinAABB(Entity.class, bb)) {
            if (entity != playerIn) {
                playerIn.addVelocity(entity.motionX, entity.motionY, entity.motionZ);
                entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
            }
        }
        //TODO: Ensure that the item is only consumed if the player actually moves. 0.09 is too low.
        if(Math.abs(playerIn.motionX - prevX) > 0.09 || Math.abs(playerIn.motionY - prevY) > 0.09 || Math.abs(playerIn.motionZ - prevZ) > 0.09) {
            if (!playerIn.capabilities.isCreativeMode)
                itemStackIn.stackSize--;
            worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_bat_takeoff, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}
