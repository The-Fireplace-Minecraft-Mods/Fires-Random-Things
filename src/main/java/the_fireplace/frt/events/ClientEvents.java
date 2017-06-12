package the_fireplace.frt.events;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import the_fireplace.frt.FRT;
import the_fireplace.frt.items.internal.ItemPaxel;

/**
 * @author The_Fireplace
 */
public class ClientEvents {
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent e) {
		try {
			if (!e.getItemStack().getItem().getCreativeTab().equals(FRT.TabFRT))
				return;
		} catch (NullPointerException ex) {
			return;
		}
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
			e.getToolTip().add(I18n.format("frt.shiftforinfo"));
		else if (!(e.getItemStack().getItem() instanceof ItemPaxel))
			e.getToolTip().add(I18n.format(e.getItemStack().getItem().getUnlocalizedName() + ".tooltip"));
		else
			e.getToolTip().add(I18n.format("item.paxel.tooltip"));
	}
}
