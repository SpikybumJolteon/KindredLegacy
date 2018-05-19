package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCometEssence extends Item implements IHasModel, IEssenceItem
{
	public ItemCometEssence(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);

		if(KindredLegacyMain.isGalacticraftEnabled)
		{
			setCreativeTab(KindredLegacyCreativeTabs.tabMain);
		}

		KindredLegacyItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(this, 0, "inventory");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Right click a Poketamable");
		tooltip.add("to permanently add the");
		tooltip.add("ability for them to breath");
		tooltip.add("in Galacticraft Space.");
	}
}