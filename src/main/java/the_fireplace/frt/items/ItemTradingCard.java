package the_fireplace.frt.items;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import the_fireplace.frt.FRT;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ItemTradingCard extends Item {

	public ItemTradingCard(){
		setHasSubtypes(true);
		setCreativeTab(FRT.TabFRT);
		setUnlocalizedName("trading_card");
		setRegistryName("trading_card");
	}

	@Override
	public EnumRarity getRarity(ItemStack stack){
		switch(stack.getMetadata()){
			case 1:
				return EnumRarity.UNCOMMON;
			case 2:
				return EnumRarity.RARE;
			case 3:
				return EnumRarity.EPIC;
			default:
				return EnumRarity.COMMON;
		}
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (this.isInCreativeTab(tab))
		{
			items.add(new ItemStack(this));
			items.add(new ItemStack(this, 1, 1));
			items.add(new ItemStack(this, 1, 2));
			items.add(new ItemStack(this, 1, 3));
		}
	}
}
