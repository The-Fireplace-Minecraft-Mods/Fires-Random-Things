package the_fireplace.frt.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import the_fireplace.frt.entity.EntityHallucinationPotion;
import the_fireplace.frt.entity.coal.*;
import the_fireplace.frt.enums.EnumAmmo;
import the_fireplace.frt.renderers.RenderCoal;
import the_fireplace.frt.renderers.RenderHallucinationPotion;

/**
 * @author The_Fireplace
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void registerEntityRenderers() {

    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), EnumAmmo.COAL));
        RenderingRegistry.registerEntityRenderingHandler(EntityChargedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), EnumAmmo.CHARGED_COAL));
        RenderingRegistry.registerEntityRenderingHandler(EntityDestabilizedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), EnumAmmo.DESTABILIZED_COAL));
        RenderingRegistry.registerEntityRenderingHandler(EntityRestabilizedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), EnumAmmo.RESTABILIZED_COAL));
        RenderingRegistry.registerEntityRenderingHandler(EntityRefinedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), EnumAmmo.REFINED_COAL));
        RenderingRegistry.registerEntityRenderingHandler(EntityHallucinationPotion.class, new RenderHallucinationPotion(Minecraft.getMinecraft().getRenderManager()));
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }

    @Override
    public void registerClient() {

    }

    @Override
    public void tryRemoveShader() {
        while (Minecraft.getMinecraft().entityRenderer.isShaderActive())
            activateNextShader();
    }

    @Override
    public void activateNextShader() {//TODO: Finish
        /*if (OpenGlHelper.shadersSupported) {
            EntityRenderer renderer = Minecraft.getMinecraft().entityRenderer;

            if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
                if (renderer.getShaderGroup() != null) {
                    renderer.getShaderGroup().deleteShaderGroup();
                }

                renderer.shaderIndex = (renderer.shaderIndex + 1) % (renderer.shaderResourceLocations.length + 1);

                if (renderer.shaderIndex != shaderCount) {
                    renderer.loadShader(renderer.shaderResourceLocations[renderer.shaderIndex]);
                } else {
                    renderer.theShaderGroup = null;
                }
            }
        }*/
    }
}
