package the_fireplace.frt.blocks.internal;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.Random;

/**
 * @author The_Fireplace
 */
public class BlockShell extends Block {

    public BlockShell() {
        super(Material.ANVIL);
        setUnlocalizedName("shell");
        setBlockUnbreakable();
        setResistance(131072);
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }
}
