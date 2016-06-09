package the_fireplace.frt.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import the_fireplace.frt.entity.projectile.EntityPigderPearl;

/**
 * @author The_Fireplace
 */
public class PigderPearlRenderFactory implements IRenderFactory<EntityPigderPearl> {
    @Override
    public Render<? super EntityPigderPearl> createRenderFor(RenderManager manager) {
        return new RenderPigderPearl(manager);
    }
}
