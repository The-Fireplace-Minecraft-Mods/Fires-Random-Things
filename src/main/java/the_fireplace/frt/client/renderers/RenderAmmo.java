package the_fireplace.frt.client.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class RenderAmmo extends Render {

	protected final Item itr;
	private final RenderItem ri;
	private ResourceLocation itemTexture;

	public RenderAmmo(RenderManager rm, Item ammo) {
		super(rm);
		this.itr = ammo;
		this.ri = Minecraft.getMinecraft().getRenderItem();
	}

	public RenderAmmo(RenderManager rm, Item ammo, ResourceLocation texture) {
		super(rm);
		this.itr = ammo;
		this.ri = Minecraft.getMinecraft().getRenderItem();
		this.itemTexture = texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (this.itr == Items.COAL)
			return new ResourceLocation("minecraft:items/coal");
		else if (this.itr == FRT.charged_coal)
			return new ResourceLocation("frt:items/charged_coal");
		else if (this.itr == FRT.destabilized_coal)
			return new ResourceLocation("frt:items/destabilized_coal");
		else if (this.itr == FRT.restabilized_coal)
			return new ResourceLocation("frt:items/semirefined_coal");
		else if (this.itr == FRT.refined_coal)
			return new ResourceLocation("frt:items/refined_coal");
		else if (this.itemTexture != null)
			return itemTexture;
		else
			return new ResourceLocation("minecraft:items/coal");
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float f, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		this.ri.renderItem(new ItemStack(this.itr), ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, f, partialTicks);
	}
}
