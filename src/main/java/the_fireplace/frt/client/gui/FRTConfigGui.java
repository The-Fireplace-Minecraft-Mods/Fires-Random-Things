package the_fireplace.frt.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
@SideOnly(Side.CLIENT)
public class FRTConfigGui extends GuiConfig {

    public FRTConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(FRT.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), FRT.MODID, false,
                false, GuiConfig.getAbridgedConfigPath(FRT.config.toString()));
    }

}
