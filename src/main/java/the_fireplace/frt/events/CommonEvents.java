package the_fireplace.frt.events;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;
import the_fireplace.frt.config.ConfigValues;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.network.UpdatePotionMessage;
import the_fireplace.frt.potion.HallucinationPotion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author The_Fireplace
 */
@SuppressWarnings("unused")
public class CommonEvents {
	public static HashMap<World, HashMap<ChunkPos, IWorldGenerator>> worldgenQueue = Maps.newHashMap();
	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.getModID().equals(FRT.MODID))
			FRT.instance.syncConfig();
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
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
	}

	@SubscribeEvent
	public void itemUseFinish(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntityLiving().world.isRemote)
			return;
		if (event.getEntityLiving() instanceof EntityPlayer)
			for (PotionEffect effect : PotionUtils.getEffectsFromStack(event.getItem())) {
				if (effect.getPotion() instanceof HallucinationPotion)
					PacketDispatcher.sendTo(new UpdatePotionMessage(effect.getDuration()), (EntityPlayerMP) event.getEntityLiving());
			}
	}

	@SubscribeEvent
	public void chunkGen(PopulateChunkEvent.Post event){
		if(ConfigValues.GENSTRUCTURES)
		if(worldgenQueue.containsKey(event.getWorld())){
			if(worldgenQueue.get(event.getWorld()).isEmpty())
				return;
			ChunkPos pos = getSurroundingChunkOnList(event.getChunkX(), event.getChunkZ(), event.getWorld().getChunkProvider(), worldgenQueue.get(event.getWorld()).keySet());
			if(pos != null)
				worldgenQueue.get(event.getWorld()).get(pos).generate(event.getWorld().rand, pos.x, pos.z, event.getWorld(), event.getGenerator(), event.getWorld().getChunkProvider());
			worldgenQueue.get(event.getWorld()).remove(pos);
		}
	}

	private ChunkPos getSurroundingChunkOnList(int chunkX, int chunkZ, IChunkProvider chunkprovider, Set list) {
		for(int x = chunkX - 1; x <= chunkX + 1; x++) {
			for(int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				ChunkPos pos = new ChunkPos(x, z);
				if(list.contains(pos)) {
					return pos;
				}
			}
		}
		return null;
	}
}
