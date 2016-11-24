package the_fireplace.frt.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.network.ChangeShaderMessage;
import the_fireplace.frt.network.PacketDispatcher;

/**
 * @author The_Fireplace
 */
public class HallucinationPotion extends Potion {
    private int timer = 0;
    ResourceLocation icon = new ResourceLocation(FRT.MODID, "textures/icons/hallucination.png");

    public HallucinationPotion() {
        super(true, 13133055);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int par2) {
        if (!(entityLivingBaseIn instanceof EntityPlayer) || entityLivingBaseIn.world.isRemote)
            return;
        if (timer < ConfigValues.POTIONSWITCH * 20) {
            timer++;
        } else {
            PacketDispatcher.sendTo(new ChangeShaderMessage(ConfigValues.POTIONSWITCH*20), (EntityPlayerMP) entityLivingBaseIn);
            timer = 0;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        super.renderInventoryEffect(x, y, effect, mc);

        mc.renderEngine.bindTexture(icon);

        Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        super.renderHUDEffect(x, y, effect, mc, alpha);

        mc.renderEngine.bindTexture(icon);

        Gui.drawModalRectWithCustomSizedTexture(x + 4, y + 4, 0, 0, 18, 18, 18, 18);
    }
}
