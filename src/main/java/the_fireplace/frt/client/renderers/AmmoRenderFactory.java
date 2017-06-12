package the_fireplace.frt.client.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.ArrayUtils;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.entity.projectile.AbstractEntityCoal;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class AmmoRenderFactory implements IRenderFactory<AbstractEntityCoal> {

	private Item ammo;
	private ResourceLocation location;

	public AmmoRenderFactory(Item item) {
		if (ArrayUtils.contains(ConfigValues.DISABLEDITEMS, item.getUnlocalizedName().substring(5)))
			ammo = Items.COAL;
		else
			ammo = item;
	}

	public AmmoRenderFactory(Item item, ResourceLocation loc) {
		ammo = item;
		location = loc;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Render<? super AbstractEntityCoal> createRenderFor(RenderManager manager) {
		if (location != null)
			return new RenderAmmo(manager, ammo, location);
		else
			return new RenderAmmo(manager, ammo);
	}
}
