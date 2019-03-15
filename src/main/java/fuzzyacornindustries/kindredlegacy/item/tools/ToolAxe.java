package fuzzyacornindustries.kindredlegacy.item.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;

public class ToolAxe extends ItemAxe implements IHasModel
{
	public ToolAxe(String name, ToolMaterial material, float attackDamage, float attackSpeed) 
	{
		super(material, attackDamage, attackSpeed);
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