package fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.LeapAttackGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BandersnatchFennekinEntity extends HostilePokemon implements IAnimatedEntity
{
	protected static final DataParameter<Byte> TEXTURE = EntityDataManager.<Byte>createKey(BandersnatchFennekinEntity.class, DataSerializers.BYTE);

	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[4];

	public static final int actionIDNone = 0;

	static String mobName = "bandersnatch_fennekin";

	public static final int snowCoatID = 0;
	public static final int temperateCoatID = 1;

	public BandersnatchFennekinEntity(EntityType<? extends BandersnatchFennekinEntity> type, World world)
	{
		super(type, world);
		
		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;
	}

	public BandersnatchFennekinEntity(World world) 
	{
		this(ModEntities.BANDERSNATCH_FENNEKIN.get(), world);
	}

	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new LeapAttackGoal(this, 0.4F, 2F, 5D, 16D, 10, null));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void registerData()
	{
		super.registerData();

		this.dataManager.register(TEXTURE, Byte.valueOf(Byte.valueOf((byte)0)));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.2D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.42D);
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSounds.BANDERSNATCH_FENNEKIN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.BANDERSNATCH_FENNEKIN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.BANDERSNATCH_FENNEKIN_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	public int getBandersnatchFennekinType()
	{
		return (int)this.dataManager.get(TEXTURE).byteValue();
	}

	public void setBandersnatchFennekinType(int par1)
	{
		this.dataManager.set(TEXTURE, (byte)par1);
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);

		if (nbt.contains("BandersnatchFennekinType", 99))
		{
			byte b0 = nbt.getByte("BandersnatchFennekinType");
			this.setBandersnatchFennekinType(b0);
		}
	}

	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);
		nbt.putByte("BandersnatchFennekinType", (byte)this.getBandersnatchFennekinType());
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		if (super.attackEntityAsMob(targetEntity))
		{
			if (targetEntity instanceof LivingEntity)
			{
				byte durationSeconds = 5;

				if (this.world.getDifficulty() == Difficulty.NORMAL)
				{
					durationSeconds = 10;
				}
				else if (this.world.getDifficulty() == Difficulty.HARD)
				{
					durationSeconds = 18;
				}

				if (this.rand.nextInt(2) == 0)
				{
					((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(8) == 0)
				{
					((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(10) == 0)
				{
					((LivingEntity)targetEntity).setFire(durationSeconds);

					playIgniteSound((LivingEntity)targetEntity);
				}

				((LivingEntity)targetEntity).addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, durationSeconds * 20, 0));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isPotionApplicable(EffectInstance potioneffectIn) 
	{
		if (potioneffectIn.getPotion() == Effects.SLOWNESS) 
		{
			net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
		}

		return super.isPotionApplicable(potioneffectIn);
	}

    @Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        setBandersnatchFennekinType(getFennekinType());

        return spawnDataIn;
    }

	public int getFennekinType()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.getPosX()), 0, MathHelper.floor(this.getPosZ()));
		
		Biome biomegenbase = this.world.getBiome(blockpos);

		Set<Type> biomeTypes = BiomeDictionary.getTypes(biomegenbase);

		Iterator<Type> biomeTypeIterator = biomeTypes.iterator();

		while(biomeTypeIterator.hasNext())
		{
			if(biomeTypeIterator.next().equals(Type.SNOWY))
			{
				return snowCoatID;
			}
		}

		if (biomegenbase == Biomes.MOUNTAINS || biomegenbase == Biomes.MOUNTAIN_EDGE)
		{
			if (MathHelper.floor(this.getPosY()) >= 92)
			{
				return snowCoatID;
			}
		}

		return temperateCoatID;
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setTailClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setNeckBobbleClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		neckBobbleIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 70);

		int startingClockX = randomInt;

		while(startingClockX > neckBobbleIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= neckBobbleIdleAnimationClock.getTotalDurationLengthX();
		}

		neckBobbleIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 30);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 50);

			float pointClusterDensityX = 0.45F;
			float pointClusterDensityY = 0.70F;

			int startingClockX = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockY = (int)(((float)(tailIdleAnimationClock.length - i) / (float)tailIdleAnimationClock.length) * (float)tailIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;

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