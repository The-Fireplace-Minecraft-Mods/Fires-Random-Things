package the_fireplace.frt.client.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.entity.projectile.EntityPigderPearl;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class PigderPearlRenderFactory implements IRenderFactory<EntityPigderPearl> {
	@Override
	@SuppressWarnings("unchecked")
	public Render<? super EntityPigderPearl> createRenderFor(RenderManager manager) {
		return new RenderPigderPearl(manager);
	}
}
