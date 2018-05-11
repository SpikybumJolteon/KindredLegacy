package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Poison/Wither Damage immunity is handled in the Entity Event Handler
 */
public class ItemSilkscreenRibbon extends ItemArmorBase
{
	public ItemSilkscreenRibbon(String name)
	{
		super(name, KindredLegacyItems.SILKSCREEN_RIBBON_MATERIAL, 1, EntityEquipmentSlot.HEAD);

		setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Infinity durablity head");
		tooltip.add("piece that prevents damage");
		tooltip.add("from Poison and Wither.");
	}
}