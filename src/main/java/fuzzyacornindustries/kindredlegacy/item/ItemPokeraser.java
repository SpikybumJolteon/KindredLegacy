package fuzzyacornindustries.kindredlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPokeraser extends ItemBase
{
	public ItemPokeraser(String name)
	{
		super(name);
		
		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("A creative mode item for removing");
		tooltip.add("Pokemobs from this mod; won't remove");
		tooltip.add("other player's Poketamables.");
	}

	@Override
	public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
	{
		if(par2EntityLivingBase instanceof HostilePokemon) // || par2EntityLivingBase instanceof EntitySubstituteDoll
		{
			((EntityLivingBase)par2EntityLivingBase).setDead();
		}
		else if(par2EntityLivingBase instanceof TamablePokemon && par3EntityLivingBase instanceof EntityPlayer)
		{
			if(((TamablePokemon)par2EntityLivingBase).isTamed() && !((TamablePokemon)par2EntityLivingBase).isOwner((EntityPlayer)par3EntityLivingBase))
			{
				return true;
			}

			((EntityLivingBase)par2EntityLivingBase).setDead();
		}

		return true;
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
	{
		if (target.world.isRemote)
		{
			return false;
		}
		else if(target instanceof HostilePokemon)// || target instanceof EntitySubstituteDoll)
		{
			((EntityLivingBase)target).setDead();
		}
		else if(target instanceof TamablePokemon)
		{
			if(((TamablePokemon)target).isTamed() && !((TamablePokemon)target).isOwner(player))
			{
				return true;
			}

			((EntityLivingBase)target).setDead();
		}

		return false;
	}
}