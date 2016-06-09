package the_fireplace.frt.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import org.lwjgl.opengl.GL11;
import the_fireplace.frt.FRT;
import the_fireplace.frt.container.ContainerShatterer;
import the_fireplace.frt.entity.tile.TileEntityShatterer;

/**
 * @author The_Fireplace
 */
public class GuiShatterer extends GuiContainer {
    public static final ResourceLocation texture = new ResourceLocation(FRT.MODID, "textures/gui/pop_furnace.png");
    private TileEntityShatterer te;

    public GuiShatterer(InventoryPlayer invPlayer, TileEntityShatterer entity) {
        super(new ContainerShatterer(invPlayer, entity));
        xSize = 176;
        ySize = 143;
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        width = res.getScaledWidth();
        height = res.getScaledHeight();
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;
        te = entity;
    }

    @Override
    public void initGui() {
        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
        width = res.getScaledWidth();
        height = res.getScaledHeight();
        guiLeft = (width - xSize) / 2;
        guiTop = (height - ySize) / 2;
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks,
                                                   int mouseX, int mouseY) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString(String.valueOf(te.getStoredGunpowder()), 8 + 18, 26 - 5, 16777215);
        this.fontRendererObj.drawString(String.valueOf(te.getStoredFirestarter()), 8 + 18, 48 - 5, 16777215);
        this.fontRendererObj.drawString(I18n.translateToLocal("pop_furnace.redstone_tooltip"), 8, 136 - 5, 16409700);
    }
}
