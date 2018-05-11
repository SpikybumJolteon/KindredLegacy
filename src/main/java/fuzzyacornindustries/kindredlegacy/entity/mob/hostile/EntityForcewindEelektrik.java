package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityForcewindEelektrik extends HostilePokemon implements IAnimatedEntity
{
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntitySpider.class, DataSerializers.BYTE);
    
	private IdleAnimationClock fangsIdleAnimationClock;
	private IdleAnimationClock bodyIdleAnimationClock[] = new IdleAnimationClock[13];

	public static final int actionIDNone = 0;

	public float destPos;

	static String mobName = "forcewind_eelektrik";

	private int speedChangeTimer = 0;
	private boolean isOnLand = true;

	public EntityForcewindEelektrik(World par1World)
	{
		super(par1World);
		this.setSize(0.7F, 0.9F);

		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIWander(this, 0.8D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

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
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(30.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
	}

	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		return currentAirSupply;
	}

	@Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(CLIMBING, Byte.valueOf((byte)0));
    }

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (!this.onGround && !this.isInWater() && this.motionY < 0.0D)
		{
			this.motionY *= 0.6D;
		}

		if(this.speedChangeTimer <= 0)
		{
			if(!this.isInWater() && this.isOnLand == false)
			{
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
				
				this.isOnLand = true;

				this.speedChangeTimer = 100;
			}
			else if(this.isInWater() && this.isOnLand == true)
			{
				this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);

				this.isOnLand = false;

				this.speedChangeTimer = 5000;
			}
			else
			{
				this.speedChangeTimer = 100;
			}
		}

		this.speedChangeTimer--;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (!this.world.isRemote)
		{
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onStruckByLightning(EntityLightningBolt par1EntityLightningBolt) {}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		if (super.attackEntityAsMob(targetEntity))
		{
			if (targetEntity instanceof EntityLivingBase)
			{
				byte durationSeconds = 6;

				if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
				{
					durationSeconds = 12;
				}
				else if (this.world.getDifficulty() == EnumDifficulty.HARD)
				{
					durationSeconds = 20;
				}

	            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, durationSeconds * 20, 1));
	            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, durationSeconds * 20, 0));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return KindredLegacySoundEvents.FORCEWIND_EELEKTRIK_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.FORCEWIND_EELEKTRIK_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.FORCEWIND_EELEKTRIK_DEATH;
    }
	
	/**
	 * Returns new PathNavigateGround instance
	 */
	@Override
    protected PathNavigate createNavigator(World worldIn)
    {
        return new PathNavigateClimber(this, worldIn);
    }

	@Override
	public boolean isOnLadder()
	{
		return this.isBesideClimbableBlock();
	}

	public boolean isBesideClimbableBlock()
	{
        return (((Byte)this.dataManager.get(CLIMBING)).byteValue() & 1) != 0;
	}

	/**
	 * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
	 * false.
	 */
	public void setBesideClimbableBlock(boolean par1)
	{
        byte b0 = ((Byte)this.dataManager.get(CLIMBING)).byteValue();

		if (par1)
		{
			b0 = (byte)(b0 | 1);
		}
		else
		{
			b0 = (byte)(b0 & -2);
		}

        this.dataManager.set(CLIMBING, Byte.valueOf(b0));
	}

	@Override
    public boolean isPotionApplicable(PotionEffect potioneffectIn)
    {
        return potioneffectIn.getPotion() == MobEffects.POISON ? false : super.isPotionApplicable(potioneffectIn);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.FORCEWIND_EELEKTRIK_LOOT_TABLE;
    }

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockFangs() 
	{
		return fangsIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody(int partNumber)
	{
		return bodyIdleAnimationClock[partNumber];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		fangsIdleAnimationClock.incrementClocks();

		for(int i = 0; i < bodyIdleAnimationClock.length; i++)
		{
			bodyIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setFangsClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setFangsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		fangsIdleAnimationClock = new IdleAnimationClock(2, 0, 0);

		fangsIdleAnimationClock.setPhaseDurationX(0, 15);
		fangsIdleAnimationClock.setPhaseDurationX(1, 15);

		int startingClockX = randomInt;

		while(startingClockX > fangsIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= fangsIdleAnimationClock.getTotalDurationLengthX();
		}

		fangsIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setBodyClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < bodyIdleAnimationClock.length; i++)
		{
			bodyIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			bodyIdleAnimationClock[i].setPhaseDurationX(0, 35);
			bodyIdleAnimationClock[i].setPhaseDurationY(0, 70);

			float frequencyX = 0.70F;
			float frequencyY = 0.75F;

			int startingClockX = (int)(((float)(bodyIdleAnimationClock.length - i) / (float)bodyIdleAnimationClock.length) * (float)bodyIdleAnimationClock[i].getTotalDurationLengthX() * frequencyX) + randomInt;
			int startingClockY = (int)(((float)(bodyIdleAnimationClock.length - i) / (float)bodyIdleAnimationClock.length) * (float)bodyIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > bodyIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= bodyIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > bodyIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= bodyIdleAnimationClock[i].getTotalDurationLengthY();
			}

			bodyIdleAnimationClock[i].setClockX(startingClockX);
			bodyIdleAnimationClock[i].setClockY(startingClockY);
		}
	}
}