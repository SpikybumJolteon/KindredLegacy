package fuzzyacornindustries.kindredlegacy.common.item;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class AttackOrdererItem extends Item
{
	public AttackOrdererItem()
	{
		super(ModItems.defaultUnstackableProps());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(new StringTextComponent("Right click in direction of a mob to order"));
		tooltip.add(new StringTextComponent("your Poketamables to attack mob."));
		tooltip.add(new StringTextComponent("Crouch + right click cancels all of your"));
		tooltip.add(new StringTextComponent("Poketamables' attack orders."));
		tooltip.add(new StringTextComponent("Poketamables ignore orders to attack your own"));
		tooltip.add(new StringTextComponent("or other player's Poketamables or player."));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		ItemStack itemStack = playerIn.getHeldItem(hand);

		if(!playerIn.isCrouching())
		{
			LivingEntity targetEntity = getTarget(playerIn, 30D);

			if (playerIn.getHeldItem(hand) == null)
			{
				return new ActionResult<ItemStack>(ActionResultType.FAIL, itemStack);
			}

			if(targetEntity != null)
			{
				if((targetEntity instanceof TamablePokemon && ((TamablePokemon) targetEntity).getOwner() instanceof PlayerEntity || targetEntity instanceof PlayerEntity)
						|| targetEntity instanceof VillagerEntity)
				{
					return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStack);
				}

				List<TamablePokemon> poketamableList = getPoketambleAreaList(playerIn);

				if(!poketamableList.isEmpty())
				{
					for (int i = 0; i < poketamableList.size(); i++)
					{
						poketamableList.get(i).setAttackTarget(targetEntity);
					}
				}
			}

			return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStack);
		}
		else
		{
			List<TamablePokemon> poketamableList = getPoketambleAreaList(playerIn);

			if(!poketamableList.isEmpty())
			{
				for (int i = 0; i < poketamableList.size(); i++)
				{
					poketamableList.get(i).setAttackTarget(null);
					poketamableList.get(i).getNavigator().clearPath();
				}
			}
		}

		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemStack);
	}

	public List<TamablePokemon> getPoketambleAreaList(PlayerEntity playerIn)
	{
		List<Entity> list = Lists.newArrayList();

		double range = 60D;
		int entityListSize = playerIn.world.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getBoundingBox().grow(range, range, range)).size();
		list = playerIn.world.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getBoundingBox().grow(range, range, range));

		List<TamablePokemon> poketamableList = Lists.newArrayList();

		for(int i = 0; i < entityListSize; i++)
		{
			if(list.get(i) instanceof TamablePokemon)
			{
				if(!list.get(i).world.isRemote && ((TamablePokemon)list.get(i)).getOwner() == playerIn && !((TamablePokemon)list.get(i)).isSitting())
				{
					poketamableList.add((TamablePokemon) list.get(i));
				}
			}
		}

		return poketamableList;
	}

	public LivingEntity getTarget(PlayerEntity playerIn, double range)
	{
		List<Entity> list = playerIn.world.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getBoundingBox().grow(range, range, range));
		Entity entity;

		if (list != null)
		{
			for (int i = 0; i < list.size(); i++)
			{
				entity = (Entity)list.get(i);

				if (entity.isAlive())
				{
					if(entity instanceof LivingEntity)
					{
						Vec3d vec3d;
						Vec3d vec3d1;
						double d;
						double d1;

						vec3d = playerIn.getLook(1.0F).normalize();
						vec3d1 = new Vec3d(entity.getPosX() - playerIn.getPosX(), (entity.getPosY() + (double)(entity.getHeight() / 2.0F)) - (playerIn.getPosY() + (double)playerIn.getEyeHeight()), entity.getPosZ() - playerIn.getPosZ());
						d = vec3d1.length();
						vec3d1 = vec3d1.normalize();
						d1 = vec3d.dotProduct(vec3d1);
						if (d1 > 1.0D - 0.025000000000000001D / d)
						{
							if(entity instanceof LivingEntity)
							{
								return (LivingEntity)entity;
							}
						}
					}
				}
			}
		}

		return (null);
	}
}