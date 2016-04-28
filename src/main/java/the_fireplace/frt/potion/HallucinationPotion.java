package the_fireplace.frt.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.network.ChangeShaderMessage;
import the_fireplace.frt.network.PacketDispatcher;

/**
 * @author The_Fireplace
 */
public class HallucinationPotion extends Potion {
    private int timer = 0;

    public HallucinationPotion() {
        super(true, 13133055);
        setIconIndex(0, 0);
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBaseIn, int par2) {
        if (!(entityLivingBaseIn instanceof EntityPlayer) || entityLivingBaseIn.worldObj.isRemote)
            return;
        if (timer < ConfigValues.POTIONSWITCH * 20) {
            timer++;
        } else {
            PacketDispatcher.sendTo(new ChangeShaderMessage(), (EntityPlayerMP) entityLivingBaseIn);
            timer = 0;
        }
    }

    @Override
    public int getStatusIconIndex()//TODO: Solve #20
    {
        ResourceLocation r = new ResourceLocation(FRT.MODID, "textures/gui/inventory.png");

        ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(r);
        Minecraft.getMinecraft().renderEngine.bindTexture(r);

        return super.getStatusIconIndex();
    }
}
