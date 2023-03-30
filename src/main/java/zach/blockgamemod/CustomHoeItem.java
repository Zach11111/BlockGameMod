package zach.blockgamemod;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

//Color for platinum is bab7b2
public class CustomHoeItem extends HoeItem {
    public CustomHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
