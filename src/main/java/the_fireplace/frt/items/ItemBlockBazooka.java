package the_fireplace.frt.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.entity.projectile.EntityBazookaAmmo;
import the_fireplace.frt.recipes.AmmoTypes;

import java.lang.reflect.Constructor;
import java.util.List;

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
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        super.addInformation(stack, playerIn, tooltip, advanced);
        tooltip.add(I18n.format("bazooka.tooltip"));
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
