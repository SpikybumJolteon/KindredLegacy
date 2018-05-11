package fuzzyacornindustries.kindredlegacy;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CommonProxy 
{
	public void preInit() {}

	public void registerRenderers() {}

	public void initTimer() {}

	public float getPartialTick() 
	{
		return 1F;
	}

	public World getWorldClient() 
	{
		return null;
	}

	public void displayPoketamableRenameGui(ItemStack itemStack){}

	public void registerItemRenderer(Item item, int meta, String id) {}
}