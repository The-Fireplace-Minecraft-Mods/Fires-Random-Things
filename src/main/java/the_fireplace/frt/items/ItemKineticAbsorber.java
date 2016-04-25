package the_fireplace.frt.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class ItemKineticAbsorber extends Item {
    public ItemKineticAbsorber(){
        setUnlocalizedName("kinetic_absorber");
        setCreativeTab(FRT.TabFRT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        AxisAlignedBB bb = playerIn.getEntityBoundingBox().expandXyz(4);
        for(Entity entity:worldIn.getEntitiesWithinAABB(Entity.class, bb)) {
            playerIn.addVelocity(entity.motionX, entity.motionY, entity.motionZ);
            entity.addVelocity(-entity.motionX, -entity.motionY, -entity.motionZ);
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}
