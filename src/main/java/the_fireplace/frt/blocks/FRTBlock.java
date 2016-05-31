package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import the_fireplace.frt.FRT;

public class FRTBlock extends Block {

    public FRTBlock(Material materialIn) {
        super(materialIn);
        setCreativeTab(FRT.TabFRT);
    }

    public FRTBlock setSoundType(SoundType sound) {
        return (FRTBlock) super.setSoundType(sound);
    }

    public FRTBlock setHarvestTool(String tool, int type) {
        this.setHarvestLevel(tool, type);
        return this;
    }
}
