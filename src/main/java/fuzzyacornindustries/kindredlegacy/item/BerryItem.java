package fuzzyacornindustries.kindredlegacy.item;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.utility.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BerryItem extends ItemFoodBase implements IHasModel
{
	float poketamableHealAmount;

	public BerryItem(String name, int healAmount, float saturation, float pokemonHealAmount) 
	{
		super(name, healAmount, saturation);

		poketamableHealAmount = pokemonHealAmount;
	}
	
	public float getPokemonHealAmount()
	{
		return poketamableHealAmount;
	}
	
	@Override
	public void registerModels() 
	{
		KindredLegacyMain.proxy.registerItemRenderer(this, 0, "inventory");
	}
}