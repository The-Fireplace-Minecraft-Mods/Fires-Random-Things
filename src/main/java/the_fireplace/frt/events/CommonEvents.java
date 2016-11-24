package the_fireplace.frt.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.network.UpdatePotionMessage;
import the_fireplace.frt.potion.HallucinationPotion;

/**
 * @author The_Fireplace
 */
@SuppressWarnings("unused")
public class CommonEvents {
    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.getModID().equals(FRT.MODID))
            FRT.instance.syncConfig();
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer) || event.getEntityLiving().world.isRemote || !(event.getEntityLiving().isPotionActive(FRT.hallucination))) {
            if (event.getEntityLiving() instanceof EntityPlayer && !(event.getEntityLiving().isPotionActive(FRT.hallucination))) {
                if(FRT.instance.clientCooldownTicks <= 0)
                    FRT.proxy.tryRemoveShader();
                else
                    FRT.instance.clientCooldownTicks--;
            }
        } else {
            FRT.hallucination.performEffect(event.getEntityLiving(), 0);
        }
    }

    @SubscribeEvent
    public void itemUseFinish(LivingEntityUseItemEvent.Finish event){
        if(event.getEntityLiving().world.isRemote)
            return;
        if(event.getEntityLiving() instanceof EntityPlayer)
            for(PotionEffect effect:PotionUtils.getEffectsFromStack(event.getItem())){
                if(effect.getPotion() instanceof HallucinationPotion)
                    PacketDispatcher.sendTo(new UpdatePotionMessage(effect.getDuration()), (EntityPlayerMP)event.getEntityLiving());
            }
    }
}
