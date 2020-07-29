package fuzzyacornindustries.kindredlegacy.lists;

import java.util.ArrayList;
import java.util.List;

import fuzzyacornindustries.kindredlegacy.block.BlockBase;
import fuzzyacornindustries.kindredlegacy.block.OranianBerryBlock;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class KindredLegacyBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();

	// MATERIALS TAB
	public static final Block PACKED_AURUM_DUST = new BlockBase(Block.Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(0.5F)).setRegistryName(UtilityFunctions.location("packed_aurum_dust"));

	// FARM BLOCKS
	public static final Block ORANIAN_BERRIES = new OranianBerryBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)).setRegistryName(UtilityFunctions.location("oranian_berry_plant"));


}