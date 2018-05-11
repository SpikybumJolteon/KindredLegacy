package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVoorstMightyena extends HostilePokemon implements IAnimatedEntity, IMiniBoss
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock jawMainIdleAnimationClock;
	private IdleAnimationClock jawLowerIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[6];

	static String mobName = "voorst_mightyena";

	public EntityVoorstMightyena(World par1World)
	{
		super(par1World);

		this.setSize(0.8F, 1F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(2, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
		{
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
			this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySkeleton.class, true));
		}

		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		this.isImmuneToFire = true;
		this.experienceValue = 30;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		this.setPathPriority(PathNodeType.WATER, -1.0F);
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		return currentAirSupply;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public boolean attackEntityAsMob(Entity par1Entity)
	{
		boolean flag = super.attackEntityAsMob(par1Entity);

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return KindredLegacySoundEvents.VOORST_MIGHTYENA_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.VOORST_MIGHTYENA_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.VOORST_MIGHTYENA_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn)
	{
		return (potioneffectIn.getPotion() == MobEffects.POISON || potioneffectIn.getPotion() == MobEffects.SLOWNESS) ? false : super.isPotionApplicable(potioneffectIn);
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.VOORST_MIGHTYENA_LOOT_TABLE;
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockJawMain()
	{
		return jawMainIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public void setIdleAnimationClockJawMain(IdleAnimationClock idleAnimationClock)
	{
		jawMainIdleAnimationClock = idleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockJawLower()
	{
		return jawLowerIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();
		jawMainIdleAnimationClock.incrementClocks();
		jawLowerIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setJawMainClockDefaults();
		setJawLowerClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setNeckBobbleClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		neckBobbleIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 75);

		int startingClockX = randomInt;

		while(startingClockX > neckBobbleIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= neckBobbleIdleAnimationClock.getTotalDurationLengthX();
		}

		neckBobbleIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setJawMainClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		jawMainIdleAnimationClock = new IdleAnimationClock(3, 0, 0);

		jawMainIdleAnimationClock.setPhaseDurationX(0, 30);
		jawMainIdleAnimationClock.setPhaseDurationX(1, 15);
		jawMainIdleAnimationClock.setPhaseDurationX(2, 30);

		int startingClockX = randomInt;

		while(startingClockX > jawMainIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= jawMainIdleAnimationClock.getTotalDurationLengthX();
		}

		jawMainIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setJawLowerClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		jawLowerIdleAnimationClock = new IdleAnimationClock(2, 0, 0);

		jawLowerIdleAnimationClock.setPhaseDurationX(0, 20);
		jawLowerIdleAnimationClock.setPhaseDurationX(1, 20);

		int startingClockX = randomInt;

		while(startingClockX > jawLowerIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= jawLowerIdleAnimationClock.getTotalDurationLengthX();
		}

		jawLowerIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 75);

			tailIdleAnimationClock[i].setPhaseDurationY(0, 50);

			float frequencyX = 0.65F;
			float frequencyY = 0.55F;

			int startingClockX = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockY = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > tailIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= tailIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > tailIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= tailIdleAnimationClock[i].getTotalDurationLengthY();
			}

			tailIdleAnimationClock[i].setClockX(startingClockX);
			tailIdleAnimationClock[i].setClockY(startingClockY);
		}
	}
}