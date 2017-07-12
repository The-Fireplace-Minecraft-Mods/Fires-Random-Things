package the_fireplace.frt.events;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;
import the_fireplace.frt.potion.HallucinationPotion;

@Mod.EventBusSubscriber
public class RegistryEvents {
	@SubscribeEvent
	public static void potionRegister(RegistryEvent.Register<Potion> event){
		if(FRT.hallucination == null)
			FRT.hallucination = new HallucinationPotion().setPotionName("potion.hallucination").setRegistryName(new ResourceLocation(FRT.MODID, "hallucination"));
		event.getRegistry().register(FRT.hallucination);
	}

	@SubscribeEvent
	public static void potionTypeRegister(RegistryEvent.Register<PotionType> event){
		event.getRegistry().register(new PotionType(new PotionEffect(FRT.hallucination, 3600)).setRegistryName(new ResourceLocation(FRT.MODID, "hallucination")));
		event.getRegistry().register(new PotionType(new PotionEffect(FRT.hallucination, 9600)).setRegistryName(new ResourceLocation(FRT.MODID, "long_hallucination")));
	}
}
