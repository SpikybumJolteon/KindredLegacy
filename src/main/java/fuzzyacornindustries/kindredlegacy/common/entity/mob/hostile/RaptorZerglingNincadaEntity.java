package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.LeapAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class RaptorZerglingNincadaEntity extends ZerglingNincadaEntity
{
	public static String mobName = "raptor_zergling_nincada";

	public RaptorZerglingNincadaEntity(EntityType<? extends RaptorZerglingNincadaEntity> type, World world)
	{
		super(type, world);

		this.experienceValue = 10;
	}

	public RaptorZerglingNincadaEntity(World world) 
	{
		this(ModEntities.RAPTOR_ZERGLING_NINCADA.get(), world);
	}

	@Override
	protected void registerGoals() 
	{
		super.registerGoals();

		this.goalSelector.addGoal(3, new LeapAttackGoal(this, 0.6F, 2.0F, 6D, 20D, 10, ModSounds.ZERGLING_NINCADA_SOAR.get()));
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();

		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		return false;
	}
}