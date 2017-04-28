package the_fireplace.frt.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class UpdatePotionMessage implements IMessage {
    private int duration;

    public UpdatePotionMessage() {
    }

    public UpdatePotionMessage(int duration) {
        this.duration = duration;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        duration = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(duration);
    }

    public static class Handler extends AbstractClientMessageHandler<UpdatePotionMessage> {
        @Override
        public IMessage handleClientMessage(EntityPlayer player, UpdatePotionMessage message, MessageContext ctx) {
            IThreadListener listener = Minecraft.getMinecraft();
            listener.addScheduledTask(() -> player.addPotionEffect(new PotionEffect(FRT.hallucination, message.duration)));
            return null;
        }
    }
}
