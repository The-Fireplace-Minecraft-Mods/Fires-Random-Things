package the_fireplace.frt.items;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import the_fireplace.frt.FRT;
import the_fireplace.frt.handlers.BlockSourceHand;

import java.lang.reflect.Method;

import static net.minecraft.block.BlockDispenser.DISPENSE_BEHAVIOR_REGISTRY;

/**
 * @author The_Fireplace
 */
public class ItemHandheldDispenser extends Item {

    private byte multiplier = 1;

    public ItemHandheldDispenser(String name) {
        super();
        setMaxStackSize(1);
        setUnlocalizedName(name);
        if(name.equals("handheld_quad_dispenser"))
            multiplier=4;
        else if(name.equals("handheld_insane_dispenser"))
            multiplier=16;
        setCreativeTab(FRT.TabFRT);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if (!worldIn.isRemote && !playerIn.getItemStackFromSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND).isEmpty()) {
            ItemStack ammo = playerIn.getItemStackFromSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND);
            IBehaviorDispenseItem ibehaviordispenseitem = DISPENSE_BEHAVIOR_REGISTRY.getObject(ammo.getItem());

            if(ibehaviordispenseitem instanceof BehaviorProjectileDispense){
                if (ibehaviordispenseitem != IBehaviorDispenseItem.DEFAULT_BEHAVIOR)
                {
                    int dispenseCount = multiplier > ammo.getCount() ? ammo.getCount() : multiplier;
                    for(int i=0;i<dispenseCount;i++)
                        if(!playerIn.getItemStackFromSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND).isEmpty())
                            dispenseStack(new BlockSourceHand(new BlockPos(playerIn.posX, playerIn.posY+playerIn.eyeHeight, playerIn.posZ), worldIn, playerIn.getHorizontalFacing()), playerIn.getItemStackFromSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND), (BehaviorProjectileDispense)ibehaviordispenseitem, playerIn);
                    playerIn.getCooldownTracker().setCooldown(this, 40*multiplier);
                    return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
                }else{
                    return new ActionResult(EnumActionResult.FAIL, itemStackIn);
                }
            }else{
                if (ibehaviordispenseitem != IBehaviorDispenseItem.DEFAULT_BEHAVIOR)
                {
                    int dispenseCount = multiplier > ammo.getCount() ? ammo.getCount() : multiplier;
                    for(int i=0;i<dispenseCount;i++)
                        if(!playerIn.getItemStackFromSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND).isEmpty())
                            ibehaviordispenseitem.dispense(new BlockSourceHand(new BlockPos(playerIn.posX, playerIn.posY+playerIn.eyeHeight, playerIn.posZ), worldIn, playerIn.getHorizontalFacing()), playerIn.getItemStackFromSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.OFFHAND : EntityEquipmentSlot.MAINHAND));
                    playerIn.getCooldownTracker().setCooldown(this, 40*multiplier);
                    return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
                }else{
                    return new ActionResult(EnumActionResult.FAIL, itemStackIn);
                }
            }
        }
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    private static final Method projectilemethod = ReflectionHelper.findMethod(BehaviorProjectileDispense.class, "getProjectileEntity","func_82499_a", World.class, IPosition.class, ItemStack.class);
    private static final Method velocitymethod = ReflectionHelper.findMethod(BehaviorProjectileDispense.class, "getProjectileVelocity","func_82500_b");
    private static final Method inaccuracymethod = ReflectionHelper.findMethod(BehaviorProjectileDispense.class, "getProjectileInaccuracy","func_82498_a");

    public void dispenseStack(IBlockSource source, ItemStack stack, BehaviorProjectileDispense behavior, EntityPlayer player)
    {
        World world = source.getWorld();
        IPosition iposition = getDispensePosition(source);
        IProjectile iprojectile;
        try {
            float velocity;
            float inaccuracy;
            iprojectile = (IProjectile)projectilemethod.invoke(behavior, world, iposition, stack);
            velocity = (float)velocitymethod.invoke(behavior);
            inaccuracy = (float)inaccuracymethod.invoke(behavior);
            iprojectile.setThrowableHeading(player.getLookVec().x, player.getLookVec().y + 0.1F, player.getLookVec().z, velocity*1.1F, inaccuracy*(float)Math.sqrt(multiplier));
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        world.spawnEntity((Entity)iprojectile);
        stack.splitStack(1);
    }

    public static IPosition getDispensePosition(IBlockSource coords)
    {
        EnumFacing enumfacing = coords.getBlockState().getValue(BlockDispenser.FACING);
        double d0 = coords.getX() + /*0.7D * */(double)enumfacing.getFrontOffsetX();
        double d1 = coords.getY() + /*0.7D * */(double)enumfacing.getFrontOffsetY();
        double d2 = coords.getZ() + /*0.7D * */(double)enumfacing.getFrontOffsetZ();
        return new PositionImpl(d0, d1, d2);
    }
}
