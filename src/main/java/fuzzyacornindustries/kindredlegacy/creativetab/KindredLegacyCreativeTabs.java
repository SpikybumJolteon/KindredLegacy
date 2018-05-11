package fuzzyacornindustries.kindredlegacy.creativetab;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class KindredLegacyCreativeTabs 
{
	public abstract static class KindredLegacyCreativeTab extends CreativeTabs 
	{
		public KindredLegacyCreativeTab(String label) 
		{
			super(label);
		}
	}

	public static CreativeTabs tabMain = new KindredLegacyCreativeTab("kindredlegacy.main") 
	{
		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack getTabIconItem() 
		{
			return new ItemStack(KindredLegacyItems.OKAMI_SYLVEON_SUMMON);
		}
	};
}