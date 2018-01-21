package the_fireplace.frt.items;

import com.google.common.collect.Maps;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import the_fireplace.frt.tools.MiscTools;

import java.util.HashMap;

/**
 * @author The_Fireplace
 */
public class ItemGiftBox extends Item {
	public static final HashMap<String, NonNullList<ItemStack>> gifts = Maps.newHashMap();

	public ItemGiftBox() {
		setUnlocalizedName("gift_box");
		setMaxStackSize(1);

		NonNullList<ItemStack> list = NonNullList.create();
		list.add(MiscTools.createHead("D3nya").setStackDisplayName("§6Wolver512's Head"));
		list.add(MiscTools.createHead("The_Fireplace"));
		list.add(MiscTools.createHead("TheMoonPanda_"));
		list.add(MiscTools.createHead("Wee_dood"));
		list.add(MiscTools.createHead("nathankemp"));
		list.add(MiscTools.createHead("harrispop"));
		gifts.put("TheTerrans", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("harrispop"));
		list.add(MiscTools.createHead("TheMoonPanda_"));
		list.add(MiscTools.createHead("Wee_Dood"));
		list.add(MiscTools.createHead("nathankemp").setStackDisplayName("§6nathankemp's Head"));
		gifts.put("TheTerransV2", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("The_Fireplace").setStackDisplayName("§6The_Fireplace's Head"));
		list.add(MiscTools.createHead("tcb1005"));
		list.add(MiscTools.createHead("7thfleet"));
		list.add(MiscTools.createHead("MrPonyboy").setStackDisplayName("§rCrAzYpOnYbOy's Head"));
		list.add(MiscTools.createHead("ix3Cookie"));
		list.add(MiscTools.createHead("LucemDivinus").setStackDisplayName("§3zexalpowa1999's Head"));
		list.add(MiscTools.createHead("tnert").setStackDisplayName("§3tman11233's Head"));
		gifts.put("TheCorruptV3", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("The_Fireplace").setStackDisplayName("§6The_Fireplace's Head"));
		list.add(MiscTools.createHead("tcb1005"));
		list.add(MiscTools.createHead("ElevatorLordII").setStackDisplayName("§rSgtVolt's Head"));
		list.add(MiscTools.createHead("skyress").setStackDisplayName("§rmikoili9's Head"));
		list.add(MiscTools.createHead("hotdog1997"));
		list.add(MiscTools.createHead("mustang_swe"));
		list.add(MiscTools.createHead("Honorable"));
		list.add(MiscTools.createHead("Djustin321"));
		list.add(MiscTools.createHead("ChingLongQua").setStackDisplayName("§rDollaBillz7's Head"));
		list.add(MiscTools.createHead("Wee_Dood"));
		list.add(MiscTools.createHead("gtdog770").setStackDisplayName("§3gtdog770's Head"));
		gifts.put("TheCorruptV2", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("The_Fireplace").setStackDisplayName("§6The_Fireplace's Head"));
		list.add(MiscTools.createHead("DestroyandPWN").setStackDisplayName("§3DestroyandPWN's Head"));
		list.add(MiscTools.createHead("Hychet"));
		list.add(MiscTools.createHead("horselovergirlly"));
		list.add(MiscTools.createHead("TheHayleyMills"));
		list.add(MiscTools.createHead("knovik1").setStackDisplayName("§3knovik1's Head"));
		list.add(MiscTools.createHead("D3nya").setStackDisplayName("§rWolver512's Head"));
		gifts.put("TheCorruptV1", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("DJACOB").setStackDisplayName("§6DJACOB's Head"));
		list.add(MiscTools.createHead("clarkbros_com").setStackDisplayName("§3clarkbros_com's Head"));
		list.add(MiscTools.createHead("clarkbros2_com"));
		list.add(MiscTools.createHead("WorkerSushi"));
		list.add(MiscTools.createHead("P0et").setStackDisplayName("§r3went's Head"));
		list.add(MiscTools.createHead("tcb1005"));
		gifts.put("EchondriaV1", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("Hate_Comments").setStackDisplayName("§6zakattakman's Head"));
		list.add(MiscTools.createHead("DestroyandPWN").setStackDisplayName("§3DestroyandPWN's Head"));
		list.add(MiscTools.createHead("OneCaptainCubby").setStackDisplayName("§3OneCaptainCubby's Head"));
		gifts.put("EchondriaV2", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("Hate_Comments").setStackDisplayName("§6zakattakman's Head"));
		list.add(MiscTools.createHead("Djustin321"));
		gifts.put("Echelon", list);
		list = NonNullList.create();
		list.add(MiscTools.createHead("gtdog770").setStackDisplayName("§6gtdog770's Head"));
		list.add(MiscTools.createHead("2k17").setStackDisplayName("§3XmaxX1212's Head"));
		gifts.put("TheMarines", list);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		if (worldIn.isRemote)
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		String giftId = itemStackIn.getTagCompound().getString("GiftId");

		if (itemStackIn.hasTagCompound() && !giftId.isEmpty() && gifts.containsKey(giftId)) {
			if (!playerIn.capabilities.isCreativeMode)
				itemStackIn.shrink(1);
			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_FIREWORK_BLAST, SoundCategory.PLAYERS, 0.3F, 0.5F / (itemRand.nextFloat() * 0.4F + 0.8F));
			for (ItemStack stack : gifts.get(giftId)) {
				stack.getTagCompound().setString("Series", giftId);
				if (!playerIn.addItemStackToInventory(stack))
					playerIn.dropItem(stack, false);
			}
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		} else
			return new ActionResult(EnumActionResult.FAIL, itemStackIn);
	}

}
