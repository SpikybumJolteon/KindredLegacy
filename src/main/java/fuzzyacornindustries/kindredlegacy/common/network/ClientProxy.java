package fuzzyacornindustries.kindredlegacy.common.network;

import java.io.File;

import fuzzyacornindustries.kindredlegacy.client.gui.PoketamableRenameGui;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Timer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ClientProxy implements IProxy 
{
	private Timer mcTimer;

	@Override
	public void initTimer()
	{
		mcTimer = ObfuscationReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getInstance(), "field_71428_T");
	}

	@Override
	public float getPartialTick() 
	{
		return mcTimer.renderPartialTicks;
	}
	
    @Override
    public void onModPreInit() {}

    @Override
    public void onClientInit() {}

    @Override
    public void displayRenameGui(ItemStack itemStack) 
    {
        Minecraft.getInstance().displayGuiScreen(new PoketamableRenameGui(itemStack));
    }

//    @Override
//    public void displayExplorationKitGui()
//    {
//    	Minecraft.getInstance().displayGuiScreen(new GuiPokemonExplorationKit());
//    }
    
    @Override
    public File getMcFolder() 
    {
        return Minecraft.getInstance().gameDir;
    }
}