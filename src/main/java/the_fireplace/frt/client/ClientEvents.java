package the_fireplace.frt.client;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResource;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.io.IOUtils;
import org.lwjgl.input.Keyboard;
import the_fireplace.frt.FRT;
import the_fireplace.frt.items.internal.ItemPaxel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static the_fireplace.frt.FRT.proxy;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public final class ClientEvents {
	private static Random rand = new Random();
	private static final ResourceLocation SPLASH_TEXTS = new ResourceLocation("texts/splashes.txt");
	public static int splashOffsetCount = 0;
	public static final int finalSplashOffsetCount;
	private static final List<String> mySplashes = Lists.newArrayList(
			"Give me the " + proxy.translateToLocal("item.hoeGold.name") + "!",
			"Unconforming walls!",
			">Live in caves",
			">Build a city"
	);

	static {
		if (Calendar.getInstance().get(Calendar.MONTH) == Calendar.JULY && Calendar.getInstance().get(Calendar.DATE) == 1)
			mySplashes.add("Happy birthday, The_Fireplace!");
		splashOffsetCount += mySplashes.size();

		//Using this system allows other mods using the system to know how many mod-added splashes there are. Not perfect, but Forge doesn't have a system in place, so this will have to do.
		try{
			File file = new File(".splashes");
			if(file.exists()) {
				byte[] encoded = Files.readAllBytes(file.toPath());
				try {
					splashOffsetCount += Integer.parseInt(new String(encoded, "UTF-8"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				if(!file.delete())
					FRT.logWarn("Splashes file could not be deleted");
			}
			file.createNewFile();
			file.deleteOnExit();
			FileWriter fw = new FileWriter(file);
			fw.write(String.valueOf(splashOffsetCount));
			fw.close();
		}catch(IOException e){
			FRT.logWarn(e.getLocalizedMessage());
		}
		finalSplashOffsetCount = splashOffsetCount;
	}

	@SubscribeEvent
	public static void screenload(GuiScreenEvent.InitGuiEvent event) {
		if (event.getGui() instanceof GuiMainMenu) {
			IResource iresource = null;
			try {
				List<String> defaultSplashes = Lists.newArrayList();
				iresource = Minecraft.getMinecraft().getResourceManager().getResource(SPLASH_TEXTS);
				BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8));
				String s;

				while ((s = bufferedreader.readLine()) != null) {
					s = s.trim();

					if (!s.isEmpty()) {
						defaultSplashes.add(s);
					}
				}

				int splashNum = rand.nextInt(defaultSplashes.size() + finalSplashOffsetCount);

				if (splashNum >= defaultSplashes.size()+finalSplashOffsetCount-mySplashes.size())
					ReflectionHelper.setPrivateValue(GuiMainMenu.class, (GuiMainMenu) event.getGui(), mySplashes.get(splashNum - (defaultSplashes.size()+finalSplashOffsetCount-mySplashes.size())), "splashText", "field_73975_c");
			} catch (IOException e) {
				FRT.logWarn(e.getLocalizedMessage());
			} finally {
				IOUtils.closeQuietly(iresource);
			}
		}
	}

	@SubscribeEvent
	public static void modelRegister(ModelRegistryEvent event){
		FRT.registerItemRenders();
	}

	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent e) {
		try {
			if (!e.getItemStack().getItem().getCreativeTab().equals(FRT.TabFRT))
				if(e.getItemStack().getItem() == Items.SKULL && e.getItemStack().getTagCompound().getString("Series") != null) {
					e.getToolTip().add(proxy.translateToLocal("series", e.getItemStack().getTagCompound().getString("Series")));
					return;
				}else if(e.getItemStack().getItem() == FRT.gift_box){
					e.getToolTip().add(proxy.translateToLocal("series", e.getItemStack().getTagCompound().getString("GiftId")));
					return;
				}else
					return;
		} catch (NullPointerException ex) {
			return;
		}
		if (!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && !Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
			e.getToolTip().add(I18n.format("frt.shiftforinfo"));
		else if (!(e.getItemStack().getItem() instanceof ItemPaxel))
			e.getToolTip().add(I18n.format(e.getItemStack().getItem().getUnlocalizedName() + ".tooltip"));
		else
			e.getToolTip().add(I18n.format("item.paxel.tooltip"));
	}
}
