package fuzzyacornindustries.kindredlegacy.item.tamable;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class OkamiEspeonSummonItem extends PoketamableSummonItem
{
	public OkamiEspeonSummonItem(Properties properties)
	{
		super(properties);
	}

	@Override
	public Entity spawnEntity(World parWorld, PlayerEntity parPlayer, ItemStack par1ItemStack, double parX, double parY, double parZ)
	{
		if (!parWorld.isRemote)
		{
			entityToSpawn = toPoketamableEntity(parWorld, par1ItemStack, parPlayer);
			//entityToSpawn.setLocationAndAngles(parX, parY + 1D, parZ, MathHelper.wrapAngleTo180_float(parWorld.rand.nextFloat() * 360.0F), 0.0F);

			entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
			entityToSpawn.rotationYawHead = entityToSpawn.rotationYaw;
			entityToSpawn.renderYawOffset = entityToSpawn.rotationYaw;
            parWorld.addEntity(entityToSpawn);
            entityToSpawn.playAmbientSound();
            
			//entityToSpawn.worldObj.playSoundAtEntity(entityToSpawn, ModInfo.MOD_ID + ":mob." + EntityOkamiEspeon.getMobName() + ".living", 3.0F, 1.0F);

			//System.out.println( "Test entity summon run, Espeon Class." );
			//System.out.println( Float.toString( this.renderYawOffset ) );
		}

		return entityToSpawn;
	}

	public static ItemStack fromPoketamableEntity(OkamiEspeonEntity poketamableEntity)
	{
		if (poketamableEntity.world.isRemote)
		{
			return null;
		}

		ItemStack poketamableStack = new ItemStack(KindredLegacyItems.OKAMI_ESPEON_SUMMON);

		retrieveEntityStats(poketamableEntity, poketamableStack);

		return poketamableStack;
	}

	public static OkamiEspeonEntity toPoketamableEntity(World world, ItemStack poketamableStack, PlayerEntity player)
	{
		OkamiEspeonEntity poketamableEntity = KindredLegacyEntities.OKAMI_ESPEON.create(world);

		applyEntityStats(poketamableEntity, poketamableStack, player);

		String name = poketamableStack.getDisplayName().getUnformattedComponentText();

		if (name.equals("") || name.equals("Okami Espeon Summon"))
		{
			name = "Okami Espeon";
		}
		
		if(!name.equals("Okami Espeon"))
		{
			poketamableEntity.setCustomNameVisible(true);
		}

		poketamableEntity.setMobName(name);
		poketamableEntity.setCustomName(new StringTextComponent(name));

		return poketamableEntity;
	}
}