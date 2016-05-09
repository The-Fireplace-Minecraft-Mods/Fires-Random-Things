package the_fireplace.frt.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import the_fireplace.frt.FRT;

public class ULBlock extends Block {

	public ULBlock(Material materialIn) {
		super(materialIn);
		setCreativeTab(FRT.TabFRT);
	}

	public ULBlock setSoundType(SoundType sound){
		return (ULBlock)super.setStepSound(sound);
	}

	public ULBlock setHarvestTool(String tool, int type){
		this.setHarvestLevel(tool, type);
		return this;
	}
}
