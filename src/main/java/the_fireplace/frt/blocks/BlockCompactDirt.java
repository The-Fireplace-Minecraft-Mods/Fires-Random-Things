package the_fireplace.frt.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
/**
 *
 * @author The_Fireplace
 *
 */
public class BlockCompactDirt extends ULBlock {

	public BlockCompactDirt() {
		super(Material.ground);
		setHardness(2.3F);
		setUnlocalizedName("compact_dirt");
		setHarvestLevel("shovel", 1);
		setStepSound(SoundType.GROUND);
	}

}
