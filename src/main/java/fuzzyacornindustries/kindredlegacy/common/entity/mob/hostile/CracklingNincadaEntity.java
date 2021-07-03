package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.LeapAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class CracklingNincadaEntity extends ZerglingNincadaEntity
{
	static String mobName = "crackling_nincada";

	public CracklingNincadaEntity(EntityType<? extends CracklingNincadaEntity> type, World world)
	{
		super(type, world);

		this.experienceValue = 25;
	}

	public CracklingNincadaEntity(World world) 
	{
		this(ModEntities.CRACKLING_NINCADA.get(), world);
	}

	protected void registerGoals() 
	{
		super.registerGoals();

		this.goalSelector.addGoal(3, new LeapAttackGoal(this, 0.45F, 2.3F, 5D, 16D, 10, ModSounds.ZERGLING_NINCADA_SOAR.get()));
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
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 3;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		return false;
	}

	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) 
	{ 
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.getPosX()), MathHelper.floor(this.getPosY()), MathHelper.floor(this.getPosZ()));

		if (this.world.canBlockSeeSky(blockpos) && this.world.getCurrentMoonPhaseFactor() == 1.0F)
		{
			return super.canSpawn(worldIn, spawnReasonIn);
		}

		return false;
	}
}