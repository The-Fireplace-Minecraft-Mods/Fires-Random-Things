package the_fireplace.frt.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;
import the_fireplace.frt.entity.EntityHallucinationPotion;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class ItemHallucinationPotion extends Item {
	public ItemHallucinationPotion(){
		super();
		setCreativeTab(FRT.TabFRT);
		setHasSubtypes(true);
		setMaxStackSize(1);
		setUnlocalizedName("hallucination_potion");
	}
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
		if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
		{
			--stack.stackSize;
		}
		if (!worldIn.isRemote){
			switch(stack.getItemDamage()){
				case 0:
					entityLiving.addPotionEffect(new PotionEffect(FRT.hallucination, 3600));
				case 1:
					entityLiving.addPotionEffect(new PotionEffect(FRT.hallucination, 9600));
				default:
					break;
			}
		}

		if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
		{
			if (stack.stackSize <= 0)
			{
				return new ItemStack(Items.glass_bottle);
			}

			if (entityplayer != null)
			{
				entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
			}
		}

		return stack;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 32;
	}
	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.DRINK;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
		if (itemStackIn.getMetadata() > 1 && itemStackIn.getItemDamage() < 4)
		{
			if (!playerIn.capabilities.isCreativeMode)
			{
				--itemStackIn.stackSize;
			}

			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_splash_potion_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!worldIn.isRemote)
			{
				EntityHallucinationPotion entitypotion = new EntityHallucinationPotion(worldIn, playerIn, itemStackIn);
				entitypotion.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
				worldIn.spawnEntityInWorld(entitypotion);
			}

			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}else if(itemStackIn.getMetadata() >= 4){
			if (!playerIn.capabilities.isCreativeMode)
			{
				--itemStackIn.stackSize;
			}

			worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.entity_lingeringpotion_throw, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

			if (!worldIn.isRemote)
			{
				EntityHallucinationPotion entitypotion = new EntityHallucinationPotion(worldIn, playerIn, itemStackIn);
				entitypotion.func_184538_a(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
				worldIn.spawnEntityInWorld(entitypotion);
			}

			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		} else {
			playerIn.setActiveHand(hand);//TODO: ?
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
	}
	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		String s = "";

		if (stack.getItemDamage() > 1 && stack.getItemDamage() < 4)
		{
			s = I18n.translateToLocal("potion.prefix.grenade").trim() + " ";
		}else if (stack.getItemDamage() >= 4){
			s = I18n.translateToLocal("potion.prefix.lingering").trim() + " ";
		}

		return s+I18n.translateToLocal("item.hallucination_potion.name");
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
	{
		String s1=I18n.translateToLocal("potion.hallucination");
		if(stack.getItemDamage() == 0)
			s1 += " (3:00)";
		else if(stack.getItemDamage() == 1)
			s1 += " (8:00)";
		else if(stack.getItemDamage() == 2)
			s1 += " (2:15)";
		else if(stack.getItemDamage() == 3)
			s1 += " (6:00)";
		else if(stack.getItemDamage() == 4)
			s1 += " (0:25)";
		tooltip.add(ChatFormatting.RED + s1);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
	{
		subItems.add(new ItemStack(itemIn, 1, 0));
		subItems.add(new ItemStack(itemIn, 1, 1));
		subItems.add(new ItemStack(itemIn, 1, 2));
		subItems.add(new ItemStack(itemIn, 1, 3));
		subItems.add(new ItemStack(itemIn, 1, 4));
	}
}
