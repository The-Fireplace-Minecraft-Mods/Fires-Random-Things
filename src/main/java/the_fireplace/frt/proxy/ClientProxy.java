package the_fireplace.frt.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import the_fireplace.frt.entity.EntityHallucinationPotion;
import the_fireplace.frt.entity.coal.*;
import the_fireplace.frt.enums.EnumAmmo;
import the_fireplace.frt.renderers.RenderCoal;
import the_fireplace.frt.renderers.RenderHallucinationPotion;

import java.util.Random;

import static net.minecraft.client.renderer.EntityRenderer.shaderCount;

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
        Minecraft.getMinecraft().entityRenderer.stopUseShader();
    }

    private static final ResourceLocation[] shaderResourceLocations = new ResourceLocation[] {new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json"), new ResourceLocation("shaders/post/creeper.json"), new ResourceLocation("shaders/post/spider.json")};

    @Override
    public void activateNextShader() {//TODO: Finish
        if (OpenGlHelper.shadersSupported) {
            EntityRenderer renderer = Minecraft.getMinecraft().entityRenderer;

            if (Minecraft.getMinecraft().getRenderViewEntity() instanceof EntityPlayer) {
                if (renderer.getShaderGroup() != null) {
                    renderer.getShaderGroup().deleteShaderGroup();
                }

                /*renderer.shaderIndex = (renderer.shaderIndex + 1) % (shaderResourceLocations.length + 1);

                if (renderer.shaderIndex != shaderCount) {
                    renderer.loadShader(shaderResourceLocations[renderer.shaderIndex]);
                } else {
                    renderer.theShaderGroup = null;
                }*/
                Random rand = new Random();
                int index = rand.nextInt(shaderResourceLocations.length);
                if (index != shaderCount) {
                    renderer.loadShader(shaderResourceLocations[index]);
                } else {
                    renderer.stopUseShader();
                }
            }
        }
    }
}
