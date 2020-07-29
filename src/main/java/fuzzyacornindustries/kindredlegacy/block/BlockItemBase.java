package fuzzyacornindustries.kindredlegacy.block;

import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;

public class BlockItemBase extends BlockItem
{
	public BlockItemBase(Block block, Properties properties) 
	{
		super(block, properties);

		KindredLegacyItems.ITEMS.add(this);
	}
}
