package the_fireplace.frt.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class ChangeShaderMessage implements IMessage {

    public int cooldown = 0;

    public ChangeShaderMessage() {
    }

    public ChangeShaderMessage(int cooldown){
        this.cooldown = cooldown;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        cooldown = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(cooldown);
    }

    public static class Handler extends AbstractClientMessageHandler<ChangeShaderMessage> {
        @Override
        public IMessage handleClientMessage(EntityPlayer player, ChangeShaderMessage message, MessageContext ctx) {
            IThreadListener listener = Minecraft.getMinecraft();
            listener.addScheduledTask(() -> FRT.proxy.activateNextShader());
            listener.addScheduledTask(() -> FRT.instance.clientCooldownTicks = message.cooldown*2);
            return null;
        }
    }
}
