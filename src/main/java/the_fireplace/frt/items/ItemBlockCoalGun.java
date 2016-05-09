package the_fireplace.frt.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import the_fireplace.frt.enums.EnumAmmo;

/**
 * @author The_Fireplace
 */
public class ItemBlockCoalGun extends ItemBlock {

    public ItemBlockCoalGun(Block b) {
        super(b);
        setMaxStackSize(1);
        setUnlocalizedName("coal_gun");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        EnumAmmo ammo = EnumAmmo.fromPlayer(playerIn);

        if (playerIn.inventory.offHandInventory[0] != null && ammo != null)
            if (playerIn.inventory.offHandInventory[0].getItem().equals(ammo.toItem())) {
                if (!worldIn.isRemote) {
                    worldIn.spawnEntityInWorld(ammo.makeEntity(worldIn, playerIn));
                }
                playerIn.inventory.offHandInventory[0].stackSize--;
            }

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}
