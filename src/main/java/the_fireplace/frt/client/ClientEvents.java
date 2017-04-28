package the_fireplace.frt.client;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import the_fireplace.frt.FRT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import static the_fireplace.frt.FRT.proxy;

/**
 * @author The_Fireplace
 */
public final class ClientEvents {
    private Random rand = new Random();
    private static final ResourceLocation SPLASH_TEXTS = new ResourceLocation("texts/splashes.txt");
    private final List<String> mySplashes = Lists.newArrayList(
            "Give me the "+proxy.translateToLocal("item.hoeGold.name")+"!",
            "Unconforming walls!",
            ">Live in caves",
            ">Build a city"
    );

    public ClientEvents(){
        if(Calendar.getInstance().get(Calendar.MONTH) == Calendar.JULY && Calendar.getInstance().get(Calendar.DATE) == 1)
            mySplashes.add("Happy birthday, The_Fireplace!");
    }

    @SubscribeEvent
    public void screenload(GuiScreenEvent.InitGuiEvent event){
        if(event.getGui() instanceof GuiMainMenu){
            IResource iresource = null;
            try
            {
                List<String> defaultSplashes = Lists.newArrayList();
                iresource = Minecraft.getMinecraft().getResourceManager().getResource(SPLASH_TEXTS);
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(iresource.getInputStream(), Charsets.UTF_8));
                String s;

                while ((s = bufferedreader.readLine()) != null)
                {
                    s = s.trim();

                    if (!s.isEmpty())
                    {
                        defaultSplashes.add(s);
                    }
                }

                int splashNum = rand.nextInt(defaultSplashes.size()+mySplashes.size());

                if (splashNum >= defaultSplashes.size())
                    ReflectionHelper.setPrivateValue(GuiMainMenu.class, (GuiMainMenu)event.getGui(), mySplashes.get(splashNum-defaultSplashes.size()), "splashText", "field_73975_c");
            }
            catch (IOException e)
            {
                FRT.logWarn(e.getLocalizedMessage());
            }
            finally
            {
                IOUtils.closeQuietly(iresource);
            }
        }
    }
}
