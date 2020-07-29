package fuzzyacornindustries.kindredlegacy.block;

import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyBlocks;
import net.minecraft.block.Block;

public class BlockBase extends Block
{
	public BlockBase(Block.Properties propertiesBlock)
	{
		super(propertiesBlock);

		KindredLegacyBlocks.BLOCKS.add(this);
	}
}