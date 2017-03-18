package the_fireplace.frt.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import the_fireplace.frt.FRT;

/**
 * @author The_Fireplace
 */
public class FRTArmor extends ItemArmor {
    public FRTArmor(ArmorMaterial material, EntityEquipmentSlot slot) {
        super(material, -1, slot);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (stack.getItem() == FRT.hallucination_goggles)
            return "frt:textures/armor/hallucination_goggles.png";
        return super.getArmorTexture(stack, entity, slot, type);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack is) {
        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == FRT.hallucination_goggles)
            player.addPotionEffect(new PotionEffect(FRT.hallucination, 20, 0, true, true));
        super.onArmorTick(world, player, is);
    }
}


