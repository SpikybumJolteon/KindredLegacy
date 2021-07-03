package fuzzyacornindustries.kindredlegacy.common.network;

import java.io.File;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class ServerProxy implements IProxy 
{
    @Override
	public void initTimer() {}

    @Override
	public float getPartialTick() 
	{
		return 1F;
	}
	
    @Override
    public void onClientInit() {}

	@Override
	public void displayRenameGui(ItemStack itemStack) {}

	@Override
	public void displayExplorationKitGui() {}

    @Override
    public void onModPreInit() {}

    @Override
    public File getMcFolder() 
    {
        return ServerLifecycleHooks.getCurrentServer().getFile("");
    }
}