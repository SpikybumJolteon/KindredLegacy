package fuzzyacornindustries.kindredlegacy.block;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String name, Material material)
	{
		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(KindredLegacyCreativeTabs.tabMain);

		KindredLegacyBlocks.BLOCKS.add(this);
		KindredLegacyItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	public BlockBase(String name, Material material, SoundType sound)
	{
		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(KindredLegacyCreativeTabs.tabMain);
		
		this.setSoundType(sound);

		KindredLegacyBlocks.BLOCKS.add(this);
		KindredLegacyItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}