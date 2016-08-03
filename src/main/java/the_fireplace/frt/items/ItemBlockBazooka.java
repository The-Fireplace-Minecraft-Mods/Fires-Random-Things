package the_fireplace.frt.items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import the_fireplace.frt.entity.projectile.EntityBazookaAmmo;
import the_fireplace.frt.recipes.AmmoTypes;

import java.lang.reflect.Constructor;

/**
 * @author The_Fireplace
 */
public class ItemBlockBazooka extends ItemBlock {

    public ItemBlockBazooka(Block b) {
        super(b);
        setMaxStackSize(1);
        setUnlocalizedName("coal_gun");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (!worldIn.isRemote && playerIn.inventory.offHandInventory[0] != null) {
            ItemStack ammo = playerIn.inventory.offHandInventory[0];
            Class<? extends EntityBazookaAmmo> ammoClass = AmmoTypes.instance().getAmmoEntity(ammo);
            if(ammoClass != null){
                EntityBazookaAmmo entityAmmo;
                try {
                    Class<?>[] argClasses = new Class<?>[]{World.class, EntityLivingBase.class};
                    Constructor<? extends EntityBazookaAmmo> ctor = ammoClass.getConstructor(argClasses);
                    entityAmmo = ctor.newInstance(worldIn, playerIn);
                }catch(Exception e){
                    e.printStackTrace();
                    return new ActionResult(EnumActionResult.FAIL, itemStackIn);
                }

                worldIn.spawnEntityInWorld(entityAmmo);

                playerIn.inventory.offHandInventory[0].stackSize--;
                playerIn.getCooldownTracker().setCooldown(this, 40);
                new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
            }
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}
