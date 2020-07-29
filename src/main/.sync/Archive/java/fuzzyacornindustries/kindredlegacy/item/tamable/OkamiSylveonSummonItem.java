package fuzzyacornindustries.kindredlegacy.item.tamable;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class OkamiSylveonSummonItem extends PoketamableSummonItem
{
	public OkamiSylveonSummonItem(Properties properties)
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
            
			//entityToSpawn.worldObj.playSoundAtEntity(entityToSpawn, ModInfo.MOD_ID + ":mob." + EntityOkamiSylveon.getMobName() + ".living", 3.0F, 1.0F);

			//System.out.println( "Test entity summon run, Sylveon Class." );
			//System.out.println( Float.toString( this.renderYawOffset ) );
		}

		return entityToSpawn;
	}

	public static ItemStack fromPoketamableEntity(OkamiSylveonEntity poketamableEntity)
	{
		if (poketamableEntity.world.isRemote)
		{
			return null;
		}

		ItemStack poketamableStack = new ItemStack(KindredLegacyItems.OKAMI_SYLVEON_SUMMON);

		retrieveEntityStats(poketamableEntity, poketamableStack);

		return poketamableStack;
	}

	public static OkamiSylveonEntity toPoketamableEntity(World world, ItemStack poketamableStack, PlayerEntity player)
	{
		OkamiSylveonEntity poketamableEntity = KindredLegacyEntities.OKAMI_SYLVEON.create(world);

		applyEntityStats(poketamableEntity, poketamableStack, player);

		String name = poketamableStack.getTag() != null ? poketamableStack.getTag().getCompound("display").getString("Name") : "Okami Sylveon";
		if (name.equals("")) name = "Okami Sylveon";
		poketamableEntity.setMobName(name);

		return poketamableEntity;
	}
}