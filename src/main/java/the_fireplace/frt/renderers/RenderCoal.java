package the_fireplace.frt.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import the_fireplace.frt.enums.EnumAmmo;

/**
 * @author The_Fireplace
 */
public class RenderCoal extends Render {

    private EnumAmmo ammo;
    protected final Item itr;
    private final RenderItem ri;

    public RenderCoal(RenderManager p_i46185_1_, EnumAmmo ammo) {
        super(p_i46185_1_);
        this.ammo = ammo;
        this.itr = EnumAmmo.getItem(ammo);
        this.ri = Minecraft.getMinecraft().getRenderItem();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        if (this.ammo == EnumAmmo.COAL)
            return new ResourceLocation("minecraft:items/coal");
        else if (this.ammo == EnumAmmo.CHARGED_COAL)
            return new ResourceLocation("unlogicii:items/charged_coal");
        else if (this.ammo == EnumAmmo.DESTABILIZED_COAL)
            return new ResourceLocation("unlogicii:items/destabilized_coal");
        else if (this.ammo == EnumAmmo.RESTABILIZED_COAL)
            return new ResourceLocation("unlogicii:items/semirefined_coal");
        else if (this.ammo == EnumAmmo.REFINED_COAL)
            return new ResourceLocation("unlogicii:items/refined_coal");
        else
            return new ResourceLocation("minecraft:items/coal");
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float p_76986_8_, float partialTicks) {
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
        super.doRender(entity, x, y, z, p_76986_8_, partialTicks);
    }
}
