package fuzzyacornindustries.kindredlegacy.entity.ability;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AILeapAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.EntityAINearestAttackableZombieExcludingPigman;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.HostilePokemon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBloodmoonFoxfire extends HostilePokemon implements IAnimatedEntity, IAnimateFoxfire
{
	private IdleAnimationClock spinIdleAnimationClock;

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	static String mobName = "bloodmoon_foxfire";

	public EntityBloodmoonFoxfire(World par1World)
	{
		super(par1World);
		
		this.setSize(0.5F, 0.9F);

		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.8D, 1.0000001E-5F));
		this.tasks.addTask(3, new AILeapAttack(this, 0.6F, 2.0F, 6D, 20D, 5, KindredLegacySoundEvents.FIREBALL_SWOOSH));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));

		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		
		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
			this.targetTasks.addTask(3, new EntityAINearestAttackableZombieExcludingPigman(this, true));

		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
		
		animationID = actionIDNone;
		animationTick = 0;
		
		this.experienceValue = 0;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(0.0001D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
	}

	@Override
    public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.ticksExisted < 10)
		{
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 1.0D, 0.0D, 0.0D, new int[0]);
		}

		if(this.ticksExisted > 200)
		{
			this.setDead();
		}

        if (this.world.isRemote)
        {
            for (int i = 0; i < 2; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
        
		int currentPosY = MathHelper.floor(this.posY);
		int currentPoxX = MathHelper.floor(this.posX);
		int currentPoxZ = MathHelper.floor(this.posZ);
		boolean flag = false;

		for (int l1 = -1; l1 <= 1; ++l1)
		{
			for (int i2 = -1; i2 <= 1; ++i2)
			{
				for (int j = 0; j <= 3; ++j)
				{
					int j2 = currentPoxX + l1;
					int k = currentPosY + j;
					int l = currentPoxZ + i2;

					BlockPos blockpos = (new BlockPos(j2, k, l));
					IBlockState iblockstate = this.world.getBlockState(blockpos);
					Block block = iblockstate.getBlock();

					if (block instanceof BlockVine || block instanceof BlockWeb || block instanceof BlockCactus)
					{
			            block.dropBlockAsItem(this.world, blockpos, iblockstate, 0);
			            this.world.setBlockToAir(blockpos);
			            //block.breakBlock(this.world, blockpos, iblockstate);
					}
				}
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		boolean flag = super.attackEntityAsMob(targetEntity);

		this.setDead();

		return flag;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.BLOCK_FIRE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.BLOCK_FIRE_EXTINGUISH;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.BLOCK_FIRE_EXTINGUISH;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
	@Override
    public float getBrightness()
    {
        return 1.0F;
    }

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	@Override
	public IdleAnimationClock getIdleAnimationClockSpin() 
	{
		return spinIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void incrementPartClocks()
	{
		spinIdleAnimationClock.incrementClocks();
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setSpinClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setSpinClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		spinIdleAnimationClock = new IdleAnimationClock(0, 1, 0);

		spinIdleAnimationClock.setPhaseDurationY(0, 60);

		int startingClockY = randomInt;

		while(startingClockY > spinIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= spinIdleAnimationClock.getTotalDurationLengthY();
		}

		spinIdleAnimationClock.setClockY(startingClockY);
	}
}