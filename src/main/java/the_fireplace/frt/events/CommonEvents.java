package the_fireplace.frt.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
@SuppressWarnings("unused")
public class CommonEvents {
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if(eventArgs.getModID().equals(FRT.MODID))
			FRT.instance.syncConfig();
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
		if(!(event.getEntityLiving() instanceof EntityPlayer) || event.getEntityLiving().worldObj.isRemote || !(event.getEntityLiving().isPotionActive(FRT.hallucination))) {
			if(event.getEntityLiving() instanceof EntityPlayer && !(event.getEntityLiving().isPotionActive(FRT.hallucination))){
				FRT.proxy.tryRemoveShader();
			}
		}
		else
			FRT.hallucination.performEffect(event.getEntityLiving(), 0);
	}
}
