package the_fireplace.frt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class ItemShimmeringStew extends ItemFood {
	public ItemShimmeringStew() {
		super(10, 12, false);
		setUnlocalizedName("shimmering_stew");
		setCreativeTab(FRT.TabFRT);
		setAlwaysEdible();
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			if (!player.inventory.addItemStackToInventory(new ItemStack(Items.BOWL)))
				player.dropItem(new ItemStack(Items.BOWL), false);
			player.addPotionEffect(new PotionEffect(FRT.hallucination, 500));
			player.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 500, 2));
			player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 500, 4));
			player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 600, 9001));
			player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 500));
			player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 500));
			player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 500, 10));
			player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 500));
			player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 500, 10));
			player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, 500, 10));
		}
		super.onFoodEaten(stack, worldIn, player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
