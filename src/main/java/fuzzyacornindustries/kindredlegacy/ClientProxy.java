package fuzzyacornindustries.kindredlegacy;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiPokemonExplorationKit;
import fuzzyacornindustries.kindredlegacy.client.gui.GuiPoketamableRename;
import fuzzyacornindustries.kindredlegacy.handler.RenderHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFirework;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Timer;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ClientProxy extends CommonProxy
{
	private Timer mcTimer;

	@Override
	public void preInit() 
	{
		super.preInit();
		
		RenderHandler.registerEntityRenders();
		RenderHandler.registerTileEntityRenders();
	}

	@Override
	public void registerRenderers() {}

	public void initTimer() 
	{
		mcTimer = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), KindredLegacyMain.fTimer);
	}

	public float getPartialTick() 
	{
		return mcTimer.renderPartialTicks;
	}

	@Override
	public World getWorldClient() 
	{
		return FMLClientHandler.instance().getWorldClient();
	}

	@Override
	public void displayPoketamableRenameGui(ItemStack itemStack)
	{
		FMLClientHandler.instance().getClient().displayGuiScreen(new GuiPoketamableRename(itemStack));
	}

	@Override
	public void displayPokemonExplorationKitGui(ItemStack itemStack)
	{
		FMLClientHandler.instance().getClient().displayGuiScreen(new GuiPokemonExplorationKit(itemStack));
	}
	
	public void registerItemRenderer(Item item, int meta, String id) 
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
}