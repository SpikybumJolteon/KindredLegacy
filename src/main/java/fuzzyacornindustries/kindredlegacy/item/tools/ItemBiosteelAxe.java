package fuzzyacornindustries.kindredlegacy.item.tools;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBiosteelAxe extends ToolAxe
{
	public ItemBiosteelAxe(String name, ToolMaterial material, float attackDamage, float attackSpeed) 
	{
		super(name, material, attackDamage, attackSpeed);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(worldIn.getTotalWorldTime() % 2000 == 0)
		{
			if(stack.getItemDamage() > 0 && stack.getItemDamage() < stack.getMaxDamage())
				stack.setItemDamage(stack.getItemDamage() - 1);
		}
    }
}