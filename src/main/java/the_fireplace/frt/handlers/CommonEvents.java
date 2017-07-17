package the_fireplace.frt.handlers;

import com.google.common.collect.Maps;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;
import the_fireplace.frt.blocks.internal.BlockStrawBed;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.handlers.FRTFuelHandler;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.network.UpdatePotionMessage;
import the_fireplace.frt.potion.HallucinationPotion;

import java.util.HashMap;
import java.util.Set;

/**
 * @author The_Fireplace
 */
@SuppressWarnings("unused")
@Mod.EventBusSubscriber
public final class CommonEvents {

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(FRT.MODID))
			FRT.instance.syncConfig();
	}

	private static HashMap<EntityPlayer, BlockPos> bedLocations = Maps.newHashMap();

	@SubscribeEvent
	public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
		if (!(event.getEntityLiving() instanceof EntityPlayer) || event.getEntityLiving().world.isRemote || !(event.getEntityLiving().isPotionActive(FRT.hallucination))) {
			if (event.getEntityLiving() instanceof EntityPlayer && !(event.getEntityLiving().isPotionActive(FRT.hallucination))) {
				if (FRT.instance.clientCooldownTicks <= 0)
					FRT.proxy.tryRemoveShader();
				else
					FRT.instance.clientCooldownTicks--;
			}
		} else {
			FRT.hallucination.performEffect(event.getEntityLiving(), 0);
		}
		if(!event.getEntityLiving().getEntityWorld().isRemote){
			if(!bedLocations.isEmpty()){
				for(EntityPlayer player:bedLocations.keySet()){
					if(!player.isPlayerSleeping()) {
						net.minecraftforge.fml.relauncher.ReflectionHelper.setPrivateValue(EntityPlayer.class, player, bedLocations.get(player), "spawnChunk", "field_71077_c");
						bedLocations.remove(player);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public static void itemUseFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		if (event.getEntityLiving() instanceof EntityPlayer)
			for (PotionEffect effect : PotionUtils.getEffectsFromStack(event.getItem())) {
				if (effect.getPotion() instanceof HallucinationPotion)
					PacketDispatcher.sendTo(new UpdatePotionMessage(effect.getDuration()), (EntityPlayerMP) event.getEntityLiving());
			}
	}

	@SubscribeEvent
	public static void onPlayerWake(PlayerWakeUpEvent event){
		if(event.getEntityPlayer().getEntityWorld().getBlockState(event.getEntityPlayer().bedLocation).getBlock() instanceof BlockStrawBed){
			BlockPos pos = event.getEntityPlayer().getBedLocation();
			bedLocations.putIfAbsent(event.getEntityPlayer(), pos);
		}
	}

	@SubscribeEvent
	public static void furnaceBurn(FurnaceFuelBurnTimeEvent event){
		if(FRTFuelHandler.getBurnTime(event.getItemStack()) != 0)
			event.setBurnTime(FRTFuelHandler.getBurnTime(event.getItemStack()));
	}
}
