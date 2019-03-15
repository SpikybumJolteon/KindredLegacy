package fuzzyacornindustries.kindredlegacy.item.tools;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe implements IHasModel
{
	public ToolPickaxe(String name, ToolMaterial material) 
	{
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(KindredLegacyCreativeTabs.tabMain);

		KindredLegacyItems.ITEMS.add(this);
	}

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}