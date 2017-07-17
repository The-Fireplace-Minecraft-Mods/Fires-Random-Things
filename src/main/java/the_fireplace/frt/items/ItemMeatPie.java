package the_fireplace.frt.items;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

import java.util.List;

/**
 * @author The_Fireplace
 */
public class ItemMeatPie extends ItemFood {
	public final boolean isMeatRaw;

	public ItemMeatPie(int amount, float saturation, boolean isRaw) {
		super(amount, saturation, true);
		isMeatRaw = isRaw;
		setMaxStackSize(1);
	}

	@Override
	protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote && stack.hasTagCompound() && !isMeatRaw) {
			NBTTagList effects = stack.getTagCompound().getTagList("Effects", 10);
			System.out.println(effects);
			List<PotionEffect> potionEffects = Lists.newArrayList();
			for(NBTBase element : effects){
				if(element instanceof NBTTagCompound) {
					ItemStack potion = new ItemStack((NBTTagCompound)element);
					potionEffects.addAll(PotionUtils.getEffectsFromStack(potion));
				}
			}
			for(PotionEffect e:potionEffects){
				player.addPotionEffect(new PotionEffect(e.getPotion(), e.getDuration()/4, e.getAmplifier(), e.getIsAmbient(), e.doesShowParticles()));
			}
		}
		super.onFoodEaten(stack, worldIn, player);
	}
}
