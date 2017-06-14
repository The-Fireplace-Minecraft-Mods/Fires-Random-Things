package the_fireplace.frt.events;

import com.google.common.collect.Sets;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import the_fireplace.frt.FRT;
import the_fireplace.frt.network.PacketDispatcher;
import the_fireplace.frt.network.UpdatePotionMessage;
import the_fireplace.frt.potion.HallucinationPotion;
import the_fireplace.frt.worldgen.WorldGeneratorNoobHouse;

import java.util.HashSet;

/**
 * @author The_Fireplace
 */
@SuppressWarnings("unused")
public class CommonEvents {
	public static HashSet<ChunkPos> noobHousesToGen = Sets.newHashSet();
	public static HashSet<ChunkPos> portalCavesToGen = Sets.newHashSet();
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
		if(event.getWorld().provider.getDimensionType().equals(DimensionType.OVERWORLD)){
			ChunkPos pos = getSurroundingChunkOnList(event.getChunkX(), event.getChunkZ(), event.getWorld().getChunkProvider(), noobHousesToGen);
			if(pos != null)
				FRT.instance.worldGeneratorNoobHouse.generate(event.getWorld().rand, pos.x, pos.z, event.getWorld(), event.getGenerator(), event.getWorld().getChunkProvider());
			pos = getSurroundingChunkOnList(event.getChunkX(), event.getChunkZ(), event.getWorld().getChunkProvider(), portalCavesToGen);
			if(pos != null)
				FRT.instance.worldGeneratorPortalCave.generate(event.getWorld().rand, pos.x, pos.z, event.getWorld(), event.getGenerator(), event.getWorld().getChunkProvider());
		}
	}

	private ChunkPos getSurroundingChunkOnList(int chunkX, int chunkZ, IChunkProvider chunkprovider, HashSet list) {
		for(int x = chunkX - 1; x <= chunkX + 1; x++) {
			for(int z = chunkZ - 1; z <= chunkZ + 1; z++) {
				ChunkPos pos = new ChunkPos(x, z);
				if(list.contains(pos)) {
					list.remove(pos);
					return pos;
				}
			}
		}
		return null;
	}
}
