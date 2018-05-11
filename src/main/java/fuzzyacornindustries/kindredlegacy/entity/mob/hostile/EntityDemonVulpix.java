package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.EntityAINearestAttackableZombieExcludingPigman;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityDemonVulpix extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[][] = new IdleAnimationClock[6][3];

	static String mobName = "demon_vulpix";

	public static final int actionIDNone = 0;

	public EntityDemonVulpix(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.9F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityWolf.class, 15.0F, 1.0D, 1.2D));
		this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.0D, 20, 60, 15.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
			this.targetTasks.addTask(2, new EntityAINearestAttackableZombieExcludingPigman(this, true));
		
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		this.isImmuneToFire = true;
		this.experienceValue = 20;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return KindredLegacySoundEvents.DEMON_VULPIX_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.DEMON_VULPIX_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.DEMON_VULPIX_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		double d0 = targetEntity.posX - this.posX;
		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - (this.posY + (double)(this.height / 2.0F));
		double d2 = targetEntity.posZ - this.posZ;

		float f1 = MathHelper.sqrt(par2) * 0.5F;
		this.world.playEvent((EntityPlayer)null, 1018, new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ), 0);

		EntitySmallFireball entitysmallfireball = new EntitySmallFireball(this.world, this, d0 + this.getRNG().nextGaussian() * (double)f1, d1, d2 + this.getRNG().nextGaussian() * (double)f1);
		entitysmallfireball.posY = this.posY + (double)(this.height / 2.0F);
		this.world.spawnEntity(entitysmallfireball);	
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.DEMON_VULPIX_LOOT_TABLE;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			if(this.world.provider.getDimension() == -1)
			{
				if(this.rand.nextInt(3) == 0)
				{
					return super.getCanSpawnHere();
				}
				else
				{
					return false;
				}
			}

			if(MathHelper.floor(this.posY) >= 16)
			{
				return false;
			}

			return super.getCanSpawnHere();
		}

		return false;
	}

	@Override
	public boolean cancelNetherSpawns()
	{
		return false;
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
	public IdleAnimationClock getIdleAnimationClockTail(int tailNumber, int partNumber)
	{
		return tailIdleAnimationClock[tailNumber][partNumber];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			for(int j = 0; j < tailIdleAnimationClock[0].length; j++)
			{
				tailIdleAnimationClock[i][j].incrementClocks();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setNeckBobbleClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		neckBobbleIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 60);

		int startingClockX = randomInt;

		while(startingClockX > neckBobbleIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= neckBobbleIdleAnimationClock.getTotalDurationLengthX();
		}

		neckBobbleIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			for(int j = 0; j < tailIdleAnimationClock[0].length; j++)
			{
				tailIdleAnimationClock[i][j] = new IdleAnimationClock(1, 1, 0);

				tailIdleAnimationClock[i][j].setPhaseDurationX(0, 50);
				tailIdleAnimationClock[i][j].setPhaseDurationY(0, 100);

				float fractionStartX = (float)i / (float)tailIdleAnimationClock.length * (float)tailIdleAnimationClock[i][j].getTotalDurationLengthX();
				//float fractionStartY = (float)i / (float)tailIdleAnimationClock.length * (float)tailIdleAnimationClock[i][j].getTotalDurationLengthY();

				int startingClockX = (int)(fractionStartX + ((float)(tailIdleAnimationClock[i].length - j) / (float)tailIdleAnimationClock[i].length) * (float)tailIdleAnimationClock[i][j].getTotalDurationLengthX() * 0.4F) + randomInt;
				int startingClockY = (int)(((float)(tailIdleAnimationClock[i].length - j) / (float)tailIdleAnimationClock[i].length) * (float)tailIdleAnimationClock[i][j].getTotalDurationLengthX() * 1F) + randomInt;

				while(startingClockX > tailIdleAnimationClock[i][j].getTotalDurationLengthX())
				{
					startingClockX -= tailIdleAnimationClock[i][j].getTotalDurationLengthX();
				}

				while(startingClockY > tailIdleAnimationClock[i][j].getTotalDurationLengthY())
				{
					startingClockY -= tailIdleAnimationClock[i][j].getTotalDurationLengthY();
				}

				tailIdleAnimationClock[i][j].setClockX(startingClockX);
				tailIdleAnimationClock[i][j].setClockY(startingClockY);
			}
		}
	}
}