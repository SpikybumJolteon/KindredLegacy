package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBiogaEssence extends ItemBase implements IEssenceItem
{
	public ItemBiogaEssence(String name)
	{
		super(name);
		
		this.maxStackSize = 64;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List parList, boolean parBoolean) 
	{
		parList.add("Right click a Poketamable");
		parList.add("to permanently add poison");
		parList.add("and wither immunity.");
	}
}