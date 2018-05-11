package fuzzyacornindustries.kindredlegacy.block;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockCropsBase extends BlockCrops implements IHasModel
{
	public BlockCropsBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);

		KindredLegacyBlocks.BLOCKS.add(this);
	}

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}