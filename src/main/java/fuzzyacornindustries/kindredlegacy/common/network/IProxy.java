package fuzzyacornindustries.kindredlegacy.common.network;

import java.io.File;

import net.minecraft.item.ItemStack;

public interface IProxy 
{
	void initTimer();

	float getPartialTick();
	
    void onModPreInit();

    void onClientInit();

    void displayRenameGui(ItemStack itemStack);

    void displayExplorationKitGui();

    File getMcFolder();
}