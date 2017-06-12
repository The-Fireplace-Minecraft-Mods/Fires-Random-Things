package the_fireplace.frt.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class ItemMysteryMeat extends ItemFood {
	public final boolean isMeatRaw;

	public ItemMysteryMeat(int amount, float saturation, boolean isRaw) {
		super(amount, saturation, true);
		isMeatRaw = isRaw;
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			Random rand = new Random();
			switch (rand.nextInt(10)) {
				case 0:
				default:
					break;
				case 1:
					player.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 100));
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
					break;
				case 2:
					player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 50));
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
					break;
				case 3:
					player.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, 1));
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100));
					break;

				case 4:
					if (!isMeatRaw) {
						player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 50));
						player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 50, 2));
					}
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 50));
					break;
				case 5:
					if (!isMeatRaw) {
						player.addPotionEffect(new PotionEffect(MobEffects.LUCK, 200));
						player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 50));
						player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 50));
					}
					player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 50));
					break;
			}
		}
		super.onFoodEaten(stack, worldIn, player);
	}
}
