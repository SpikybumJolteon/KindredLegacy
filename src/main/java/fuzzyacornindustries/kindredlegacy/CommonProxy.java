package fuzzyacornindustries.kindredlegacy;

import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityXelNagaPylon;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy 
{
	public void preInit() {}

	public void registerRenderers() 
	{
		//GameRegistry.registerTileEntity(TileEntityXelNagaPylon.class, TileEntityXelNagaPylon.getKey(TileEntityXelNagaPylon.class));
		//GameRegistry.registerTileEntity(TileEntityVespeneCondenser.class, TileEntityVespeneCondenser.getKey(TileEntityVespeneCondenser.class));
	}

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

	public void displayPokemonExplorationKitGui(ItemStack itemStack){}

	public void registerItemRenderer(Item item, int meta, String id) {}
}