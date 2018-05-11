package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEssenceRecaller extends ItemBase
{
	public ItemEssenceRecaller(String name)
	{
		super(name);
		
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("An item to return your own Poketamable");
		tooltip.add("to their summon item form; will not work");
		tooltip.add("on other players' or ones spawned with eggs.");
	}
/*
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
	{
		System.out.println( "Testing code at Essence Recaller" );
		
		if (target.world.isRemote)
		{
			return false;
		}
		else if(target instanceof TamablePokemon && ((TamablePokemon)target).isOwner(player))
		{
			System.out.println( "Testing code at Essence Recaller" );
			
			((TamablePokemon)target).returnToItem();

			player.inventoryContainer.detectAndSendChanges();
		}

		return false;
	}*/
}