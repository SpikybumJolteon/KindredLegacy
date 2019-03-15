package fuzzyacornindustries.kindredlegacy.block;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBaseAdvanced extends BlockBase implements IHasModel
{
	public BlockBaseAdvanced(String name, Material material)
	{
		super(name, material);

		KindredLegacyItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public BlockBaseAdvanced(String name, Material material, SoundType sound)
	{
		super(name, material);

		KindredLegacyItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}