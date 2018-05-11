package fuzzyacornindustries.kindredlegacy.item.tamable;

import java.util.List;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAttackOrderer extends ItemBase
{
	public ItemAttackOrderer(String name)
	{
		super(name);

		this.maxStackSize = 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add("Right click a mob to order your Poketamables");
		tooltip.add("to attack mob. Sneak + right click cancels");
		tooltip.add("all of your Poketamables' attack orders.");
		tooltip.add("Poketamables will ignore orders to attack");
		tooltip.add("your own or other player's Poketamables");
		tooltip.add("or player entities.");
	}

	@Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand hand)
    {
        ItemStack itemstack = entityPlayer.getHeldItem(hand);
        
		if(!entityPlayer.isSneaking())
		{
			boolean returnBoolean = false;

			EntityLivingBase targetEntity = getTarget(entityPlayer, 30D);

			//System.out.println( "Test Entity Detected By Kill Order" );
			//System.out.println( Integer.toString( targetEntity.getEntityId() ) );

			if (entityPlayer.getHeldItem(hand) == null)
			{
	            return new ActionResult(EnumActionResult.FAIL, itemstack);
			}

			if(targetEntity != null)
			{
				if(targetEntity instanceof TamablePokemon && ((TamablePokemon) targetEntity).getOwner() instanceof EntityPlayer || targetEntity instanceof EntityPlayer)
				{
		            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
				}
				
				TamablePokemon poketamable;

				for (int i = 0; i < entityPlayer.world.loadedEntityList.size(); i++)
				{
					if (entityPlayer.world.loadedEntityList.get(i) instanceof TamablePokemon)
					{
						poketamable = (TamablePokemon)entityPlayer.world.loadedEntityList.get(i);

						if (!poketamable.world.isRemote && poketamable.getOwner() == entityPlayer && !poketamable.isSitting())
						{
							poketamable.setAttackTarget(targetEntity);
						}
					}
				}
			}

            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
		}
		else
		{
			TamablePokemon poketamable;

			for (int i = 0; i < world.loadedEntityList.size(); i++)
			{
				if (world.loadedEntityList.get(i) instanceof TamablePokemon)
				{
					poketamable = (TamablePokemon) world.loadedEntityList.get(i);

					if (!poketamable.world.isRemote && poketamable.getOwner() == entityPlayer && !poketamable.isSitting())
					{
						poketamable.setAttackTarget(null);
						poketamable.getNavigator().clearPath();
					}
				}
			}
		}

        return new ActionResult(EnumActionResult.SUCCESS, itemstack);
	}

	public EntityLivingBase getTarget(EntityPlayer entityPlayer, double range)
	{
		List list = entityPlayer.world.getEntitiesWithinAABBExcludingEntity(entityPlayer, entityPlayer.getEntityBoundingBox().grow(range, range, range));
		Entity entity;

		if (list != null)
		{
			for (int i = 0; i < list.size(); i++)
			{
				entity = (Entity)list.get(i);

				if (!entity.isDead)
				{
					if(entity instanceof EntityLivingBase)
					{
						Vec3d vec3d;
						Vec3d vec3d1;
						double d;
						double d1;
						double a;
						double b;
						double c;

						vec3d = entityPlayer.getLook(1.0F).normalize();
						vec3d1 = new Vec3d(entity.posX - entityPlayer.posX, (entity.getEntityBoundingBox().minY + (double)(entity.height / 2.0F)) - (entityPlayer.posY + (double)entityPlayer.getEyeHeight()), entity.posZ - entityPlayer.posZ);
						d = vec3d1.lengthVector();
						vec3d1 = vec3d1.normalize();
						d1 = vec3d.dotProduct(vec3d1);
						if (d1 > 1.0D - 0.025000000000000001D / d)
						{
							if(entity instanceof EntityLivingBase)
							{
								//System.out.println("Looking at " + entity);

								return (EntityLivingBase)entity;
							}
						}
					}

				}
			}
		}

		return (null);
	}
}