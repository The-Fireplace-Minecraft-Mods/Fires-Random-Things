package the_fireplace.frt.compat.chisel;

import net.minecraft.block.state.IBlockState;
import team.chisel.api.carving.CarvingUtils;

/**
 * @author The_Fireplace
 */
public class ChiselCompat implements IChiselCompat {
	@Override
	public boolean isObsidian(IBlockState state) {
		return CarvingUtils.chisel.getOreName(state) != null && CarvingUtils.chisel.getOreName(state).equals("obsidian");
	}
}
