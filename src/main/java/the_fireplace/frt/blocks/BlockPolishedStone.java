package the_fireplace.frt.blocks;

import net.minecraft.block.material.Material;
/**
 *
 * @author The_Fireplace
 *
 */
public class BlockPolishedStone extends ULBlock {

	public BlockPolishedStone() {
		super(Material.rock);
		setUnlocalizedName("polished_stone");
		setHarvestLevel("pickaxe", 0);
		setHardness(1.5F);
		setResistance(10.0F);
	}
}
