package fuzzyacornindustries.kindredlegacy.item.tamable;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityImmortalArcanine;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemImmortalArcanineSummon extends ItemPoketamableSummon
{
	public ItemImmortalArcanineSummon()
	{
		super("immortal_arcanine_summon");
	}

	@Override
	public Entity spawnEntity(World parWorld, EntityPlayer parPlayer, ItemStack par1ItemStack, double parX, double parY, double parZ)
	{
		if (!parWorld.isRemote)
		{
			entityToSpawn = toPoketamableEntity(parWorld, par1ItemStack, parPlayer);
			//entityToSpawn.setLocationAndAngles(parX, parY + 1D, parZ, MathHelper.wrapAngleTo180_float(parWorld.rand.nextFloat() * 360.0F), 0.0F);

			entityToSpawn.setLocationAndAngles(parX, parY, parZ, MathHelper.wrapDegrees(parWorld.rand.nextFloat() * 360.0F), 0.0F);
			entityToSpawn.rotationYawHead = entityToSpawn.rotationYaw;
			entityToSpawn.renderYawOffset = entityToSpawn.rotationYaw;
            parWorld.spawnEntity(entityToSpawn);
            entityToSpawn.playLivingSound();
            
			parWorld.spawnEntity(entityToSpawn);
			//entityToSpawn.worldObj.playSoundAtEntity(entityToSpawn, ModInfo.MOD_ID + ":mob." + EntityOkamiSylveon.getMobName() + ".living", 3.0F, 1.0F);

			//System.out.println( "Test entity summon run, Sylveon Class." );
			//System.out.println( Float.toString( this.renderYawOffset ) );
		}

		return entityToSpawn;
	}

	public static ItemStack fromPoketamableEntity(EntityImmortalArcanine poketamableEntity)
	{
		if (poketamableEntity.world.isRemote)
		{
			return null;
		}

		ItemStack poketamableStack = new ItemStack(KindredLegacyItems.IMMORTAL_ARCANINE_SUMMON);

		retrieveEntityStats(poketamableEntity, poketamableStack);

		return poketamableStack;
	}

	public static EntityImmortalArcanine toPoketamableEntity(World world, ItemStack poketamableStack, EntityPlayer player)
	{
		EntityImmortalArcanine poketamableEntity = new EntityImmortalArcanine(world);

		applyEntityStats(poketamableEntity, poketamableStack, player);

		String name = poketamableStack.getTagCompound() != null ? poketamableStack.getTagCompound().getCompoundTag("display").getString("Name") : "Immortal Arcanine";
		if (name.equals("")) name = "Immortal Arcanine";
		poketamableEntity.setMobName(name);

		return poketamableEntity;
	}
}