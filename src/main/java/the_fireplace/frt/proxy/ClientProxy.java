package the_fireplace.frt.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import the_fireplace.frt.FRT;
import the_fireplace.frt.entity.projectile.*;
import the_fireplace.frt.renderers.RenderCoal;
import the_fireplace.frt.renderers.RenderPigderPearl;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void registerEntityRenderers() {

    }

    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), Items.COAL));
        RenderingRegistry.registerEntityRenderingHandler(EntityChargedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), FRT.charged_coal));
        RenderingRegistry.registerEntityRenderingHandler(EntityDestabilizedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), FRT.destabilized_coal));
        RenderingRegistry.registerEntityRenderingHandler(EntityRestabilizedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), FRT.restabilized_coal));
        RenderingRegistry.registerEntityRenderingHandler(EntityRefinedCoal.class, new RenderCoal(Minecraft.getMinecraft().getRenderManager(), FRT.refined_coal));
        RenderingRegistry.registerEntityRenderingHandler(EntityPigderPearl.class, new RenderPigderPearl(Minecraft.getMinecraft().getRenderManager()));
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
        try {
            Minecraft.getMinecraft().entityRenderer.stopUseShader();
        } catch (RuntimeException e) {

        }
    }

    private static final ResourceLocation[] shaderResourceLocations = new ResourceLocation[]{new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json"), new ResourceLocation("shaders/post/creeper.json"), new ResourceLocation("shaders/post/spider.json")};

    @Override
    public void activateNextShader() {
        if (OpenGlHelper.shadersSupported) {
            EntityRenderer renderer = Minecraft.getMinecraft().entityRenderer;

            if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
                if (renderer.getShaderGroup() != null) {
                    renderer.getShaderGroup().deleteShaderGroup();
                }

                Random rand = new Random();
                int index = rand.nextInt(shaderResourceLocations.length);
                if (index != shaderResourceLocations.length) {
                    renderer.loadShader(shaderResourceLocations[index]);
                } else {
                    renderer.stopUseShader();
                }
            }
        }
    }
}
