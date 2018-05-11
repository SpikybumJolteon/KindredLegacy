package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralRangedAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AISwordieMienshaoSlash;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntitySwordieMienshao extends HostilePokemon implements IAnimatedEntity, IRangedAttackMob
{
	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock bodyIdleAnimationClock[] = new IdleAnimationClock[2];
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[12];
	private IdleAnimationClock armIdleAnimationClock;

	public static final int actionIDNone = 0;
	public static final int actionIDSlash = 1;

	static String mobName = "swordie_mienshao";

	public EntitySwordieMienshao(World par1World)
	{
		super(par1World);
		this.setSize(0.4F, 1.4F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new AISwordieMienshaoSlash(this));
		this.tasks.addTask(2, new AIGeneralRangedAttack(this, 1.0D, 1, 2.5F));
		this.tasks.addTask(3, new EntityAIWander(this, 0.75D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		
		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityZombie.class, true));
		
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
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
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
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

		if(animationID != 0) animationTick++;
	}

	@Override
    protected void collideWithEntity(Entity targetEntity)
    {
        if (targetEntity instanceof IMob && this.getRNG().nextInt(10) == 0  && !(targetEntity instanceof EntityCreeper) && !(targetEntity instanceof EntityAnimal))
        {
            this.setAttackTarget((EntityLivingBase)targetEntity);
        }

        super.collideWithEntity(targetEntity);
    }

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		if(animationID == 0)
		{
			KindredLegacyMain.sendAnimationPacket(this, actionIDSlash);
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	@Override
    protected SoundEvent getAmbientSound()
    {
        return KindredLegacySoundEvents.SWORDIE_MIENSHAO_AMBIENT;
    }

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return KindredLegacySoundEvents.SWORDIE_MIENSHAO_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return KindredLegacySoundEvents.SWORDIE_MIENSHAO_DEATH;
    }

	@Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }

    @Nullable
    protected ResourceLocation getLootTable()
    {
        return KindredLegacyLootTables.SWORDIE_MIENSHAO_LOOT_TABLE;
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
	public IdleAnimationClock getIdleAnimationClockBody(int partNumber)
	{
		return bodyIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArm()
	{
		return armIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();

		for(int i = 0; i < bodyIdleAnimationClock.length; i++)
		{
			bodyIdleAnimationClock[i].incrementClocks();
		}
		
		armIdleAnimationClock.incrementClocks();
		
		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setBodyClockDefaults();
		setArmClockDefaults();
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
	public void setBodyClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < bodyIdleAnimationClock.length; i++)
		{
			bodyIdleAnimationClock[i] = new IdleAnimationClock(0, 0, 1);

			bodyIdleAnimationClock[i].setPhaseDurationZ(0, 60);

			float frequencyZ = 0.35F;

			int startingClockZ = (int)(((float)(bodyIdleAnimationClock.length - i) / (float)bodyIdleAnimationClock.length) * (float)bodyIdleAnimationClock[i].getTotalDurationLengthZ() * frequencyZ) + randomInt;

			while(startingClockZ > bodyIdleAnimationClock[i].getTotalDurationLengthZ())
			{
				startingClockZ -= bodyIdleAnimationClock[i].getTotalDurationLengthZ();
			}

			bodyIdleAnimationClock[i].setClockZ(startingClockZ);
		}
	}

	@SideOnly(Side.CLIENT)
	public void setArmClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		armIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		armIdleAnimationClock.setPhaseDurationX(0, 80);

		int startingClockX = randomInt;

		while(startingClockX > armIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= armIdleAnimationClock.getTotalDurationLengthX();
		}

		armIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 80);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 100);

			float frequencyX = 0.65F;
			float frequencyY = 0.60F;

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