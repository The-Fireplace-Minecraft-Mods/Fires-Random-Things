package the_fireplace.frt.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import the_fireplace.frt.FRT;
import the_fireplace.frt.container.ContainerItemExploder;
import the_fireplace.frt.tileentity.TileEntityItemExploder;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class GuiItemExploder extends GuiContainer {
	//TODO: Use a dynamic transparent item overlay
	public static final ResourceLocation texture = new ResourceLocation(FRT.MODID, "textures/gui/pop_furnace.png");
	private TileEntityItemExploder te;

	public GuiItemExploder(InventoryPlayer invPlayer, TileEntityItemExploder entity) {
		super(new ContainerItemExploder(invPlayer, entity));
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
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(String.valueOf(te.getStoredGunpowder()), 8 + 18, 26 - 5, 16777215);
		this.fontRenderer.drawString(String.valueOf(te.getStoredFirestarter()), 8 + 18, 48 - 5, 16777215);
		this.fontRenderer.drawString(I18n.format("pop_furnace.redstone_tooltip"), 8, 136 - 5, 16409700);
	}
}
