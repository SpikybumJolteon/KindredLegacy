package fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.client.animation.IAdvAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IGravityTracker;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.common.item.BerryItem;
import fuzzyacornindustries.kindredlegacy.common.item.IBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.IEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.IPowerUp;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class TamablePokemon extends TameableEntity implements IEntityAdditionalSpawnData, IAnimatedEntity, IAdvAnimatedEntity, IMobMotionTracker, IGravityTracker
{	
	protected static final DataParameter<Float> CURRENT_HEALTH = EntityDataManager.<Float>createKey(TamablePokemon.class, DataSerializers.FLOAT);

	protected static final DataParameter<Byte> TEXTURE = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> SOUND = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	protected static final DataParameter<Float> MAX_HEALTH = EntityDataManager.<Float>createKey(TamablePokemon.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> ATTACK_POWER = EntityDataManager.<Float>createKey(TamablePokemon.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> SPEED = EntityDataManager.<Float>createKey(TamablePokemon.class, DataSerializers.FLOAT);

	protected static final DataParameter<Byte> FIRE_IMMUNITY = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> DROWNING_IMMUNITY = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> FALL_DAMAGE_IMMUNITY = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> BLOCK_SUFFOCATION_AVOIDANCE = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);
	protected static final DataParameter<Byte> TOXIN_IMMUNITY = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	protected static final DataParameter<Byte> REGEN_LEVEL = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	protected static final DataParameter<Byte> BOOST_POWER = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	protected static final DataParameter<Byte> SPACE_SURVIVALBILITY = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	protected static final DataParameter<Boolean> IS_AGGROED = EntityDataManager.<Boolean>createKey(TamablePokemon.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> IS_MOUNTED = EntityDataManager.<Boolean>createKey(TamablePokemon.class, DataSerializers.BOOLEAN);

	protected final int NO_BOOST_POWER_ID = 0;
	protected final int SPICE_MELANGE_POWER_ID = 1;

	private NearestAttackableTargetGoal<MonsterEntity> beserkAIMobs;

	private String poketamableName;

	public String default_name;
	public String default_summon_item_name;
	
	public double worldGravity;

	public float previousYaw[] = new float[6];
	public float changeInYaw;
	public float totalChangeInYaw;

	public float previousHeight[] = new float[6];
	public float changeInHeight;

	public static final int actionIDNone = 0;

	protected int animationID;
	protected int animationTick;

	public boolean isAggroed = false;
	public float targetingAggroValue = 0F;
	public boolean isMounted = false;
	public float mountedValue = 0F;

	public int regenTimer = 0;

	public int boostPowerTimer = 0;

	boolean isHappy = false;
	int happyTimer = 0;
	int happyDuration = 20;

	public boolean isBegging = false;

	/** Float used to smooth the rotation of the mob head */
	private float headRotationCourse;
	private float headRotationCourseOld;

	public static float defaultBaseAttackPower;
	public static float defaultBaseMaxHealth;
	public static float defaultBaseSpeed;

	public static int defaultRegenLevel;

	public static float defaultMaximumAttackBoost;
	public static float defaultMaximumHealthBoost;
	public static float defaultMaximumSpeedBoost;

	public static int defaultArmor;
	
	public TamablePokemon(EntityType<? extends TameableEntity> type, World world)
	{
		super(type, world);

		poketamableName = "";

		this.experienceValue = 0;

		animationID = actionIDNone;
		animationTick = 0;

		if(this.world.isRemote)
		{
			for(int i = 0; i < previousYaw.length; i++)
			{
				previousYaw[i] = this.renderYawOffset;
			}

			changeInYaw = 0;
			totalChangeInYaw = 0;

			for(int i = 0; i < previousHeight.length; i++)
			{
				previousHeight[i] = (float)this.getPosY();
			}

			changeInHeight = 0;
		}
	}
	
	public void setDefaultStats() {}
	
	public String defaultNameCheck(String name)
	{
		if (name.equals("") || name.equals(default_summon_item_name))
		{
			name = default_name;
		}
		
		return name;
	}

	public boolean shouldDisplayName(String name)
	{
		return !name.equals(default_name);
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getDefaultBaseSpeed());
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();

		setDefaultStats();
		
		this.dataManager.register(CURRENT_HEALTH, Float.valueOf(this.getHealth()));

		this.dataManager.register(TEXTURE, Byte.valueOf((byte)0));
		this.dataManager.register(SOUND, Byte.valueOf((byte)0));

		this.dataManager.register(MAX_HEALTH, Float.valueOf((float)0));
		this.dataManager.register(ATTACK_POWER, Float.valueOf((float)0));
		this.dataManager.register(SPEED, Float.valueOf((float)0));

		this.dataManager.register(FIRE_IMMUNITY, Byte.valueOf((byte)0));
		this.dataManager.register(DROWNING_IMMUNITY, Byte.valueOf((byte)0));
		this.dataManager.register(FALL_DAMAGE_IMMUNITY, Byte.valueOf((byte)0));
		this.dataManager.register(BLOCK_SUFFOCATION_AVOIDANCE, Byte.valueOf((byte)0));
		this.dataManager.register(TOXIN_IMMUNITY, Byte.valueOf((byte)0));

		this.dataManager.register(REGEN_LEVEL, Byte.valueOf((byte)0));

		this.dataManager.register(BOOST_POWER, Byte.valueOf((byte)0));

		this.dataManager.register(SPACE_SURVIVALBILITY, Byte.valueOf((byte)0));

		this.dataManager.register(IS_AGGROED, Boolean.valueOf(false));
		this.dataManager.register(IS_MOUNTED, Boolean.valueOf(false));		
	}

	public float getMaximumHealth()
	{
		return ((Float)this.dataManager.get(MAX_HEALTH)).floatValue();
	}

	public void setMaximumHealth(float newMaxHealth)
	{
		this.dataManager.set(MAX_HEALTH, Float.valueOf(newMaxHealth));

		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)newMaxHealth);
	}

	public float getAttackPower()
	{
		return ((Float)this.dataManager.get(ATTACK_POWER)).floatValue();
	}

	public float getAttackDamage()
	{
		float totalAttackPower = ((Float)this.dataManager.get(ATTACK_POWER)).floatValue();

		if(this.getBoostPowerType() == this.SPICE_MELANGE_POWER_ID)
		{
			totalAttackPower += 1F;
		}

		return totalAttackPower;
	}

	public void setAttackPower(float newAttackPower)
	{
		this.dataManager.set(ATTACK_POWER, Float.valueOf(newAttackPower));
	}

	public float getSpeed()
	{
		return ((Float)this.dataManager.get(SPEED)).floatValue();
	}

	public void setSpeed(float newMaxSpeed)
	{
		this.dataManager.set(SPEED, Float.valueOf(newMaxSpeed));

		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)newMaxSpeed);
	}

	public int hasFireImmunityEssence()
	{
		return (int)this.dataManager.get(FIRE_IMMUNITY).byteValue();
	}

	// Set 1 for true; 0 for false
	public void setFireImmunityEssence(int activateFireImmunityEssence)
	{
		if(activateFireImmunityEssence == 1)
		{
			this.dataManager.set(FIRE_IMMUNITY, (byte)1);
		}
		else
		{
			this.dataManager.set(FIRE_IMMUNITY, (byte)0);
		}
	}

	public int hasDrowningImmunityEssence()
	{
		return (int)this.dataManager.get(DROWNING_IMMUNITY).byteValue();
	}

	// Set 1 for true; 0 for false
	public void setDrowningImmunityEssence(int activateDrowningImmunityEssence)
	{
		if(activateDrowningImmunityEssence == 1)
		{
			this.dataManager.set(DROWNING_IMMUNITY, (byte)1);
		}
		else
		{
			this.dataManager.set(DROWNING_IMMUNITY, (byte)0);
		}
	}

	public int hasFallImmunityEssence()
	{
		return (int)this.dataManager.get(FALL_DAMAGE_IMMUNITY).byteValue();
	}

	// Set 1 for true; 0 for false
	public void setFallImmunityEssence(int activateFallImmunityEssence)
	{
		if(activateFallImmunityEssence == 1)
		{
			this.dataManager.set(FALL_DAMAGE_IMMUNITY, (byte)1);
		}
		else
		{
			this.dataManager.set(FALL_DAMAGE_IMMUNITY, (byte)0);
		}
	}

	public int hasBlockSuffocationAvoidanceEssence()
	{
		return (int)this.dataManager.get(BLOCK_SUFFOCATION_AVOIDANCE).byteValue();
	}

	// Set 1 for true; 0 for false
	public void setBlockSuffocationAvoidanceEssence(int activateBlockSuffacationAvoidanceEssence)
	{
		if(activateBlockSuffacationAvoidanceEssence == 1)
		{
			this.dataManager.set(BLOCK_SUFFOCATION_AVOIDANCE, (byte)1);
		}
		else
		{
			this.dataManager.set(BLOCK_SUFFOCATION_AVOIDANCE, (byte)0);
		}
	}

	public int hasToxinImmunityEssence()
	{
		return (int)this.dataManager.get(TOXIN_IMMUNITY).byteValue();
	}

	// Set 1 for true; 0 for false
	public void setToxinImmunityEssence(int activateToxinImmunityEssence)
	{
		if(activateToxinImmunityEssence == 1)
		{
			this.dataManager.set(TOXIN_IMMUNITY, (byte)1);
		}
		else
		{
			this.dataManager.set(TOXIN_IMMUNITY, (byte)0);
		}
	}

	public int hasSpaceSurvivabilityEssence()
	{
		return (int)this.dataManager.get(SPACE_SURVIVALBILITY).byteValue();
	}

	// Set 1 for true; 0 for false
	public void setSpaceSurvivabilityEssence(int activateSpaceSurvivabilityEssence)
	{
		if(activateSpaceSurvivabilityEssence == 1)
		{
			this.dataManager.set(SPACE_SURVIVALBILITY, (byte)1);
		}
		else
		{
			this.dataManager.set(SPACE_SURVIVALBILITY, (byte)0);
		}
	}

	public int getRegenLevel()
	{
		return (int)this.dataManager.get(REGEN_LEVEL).byteValue();
	}

	public void setRegenLevel(int newRegenLevel)
	{
		this.dataManager.set(REGEN_LEVEL, (byte)newRegenLevel);
	}

	public int getBoostPowerType()
	{
		return (int)this.dataManager.get(BOOST_POWER).byteValue();
	}

	public void setBoostPowerType(int newBoostPowerType)
	{
		this.dataManager.set(BOOST_POWER, (byte)newBoostPowerType);
	}

	@Override
	public void writeSpawnData(PacketBuffer buffer)
	{
		buffer.writeString(poketamableName);
	}

	@Override
	public void readSpawnData(PacketBuffer buffer)
	{
		poketamableName = buffer.readString();
	}

	public void setMobName(String name)
	{
		this.poketamableName = name;
	}
	
	public String getTamableName()
	{
		return this.poketamableName;
	}

	public void setDefaultBaseAttackPower(float value)
	{
		defaultBaseAttackPower = value;
	}
	
	public static float getDefaultBaseAttackPower()
	{
		return defaultBaseAttackPower;
	}

	public void setDefaultBaseMaxHealth(float value)
	{
		defaultBaseMaxHealth = value;
	}
	
	public static float getDefaultBaseMaxHealth()
	{
		return defaultBaseMaxHealth;
	}

	public void setDefaultBaseSpeed(float value)
	{
		defaultBaseSpeed = value;
	}
	
	public static float getDefaultBaseSpeed()
	{
		return defaultBaseSpeed;
	}

	public void setDefaultRegenLevel(int value)
	{
		defaultRegenLevel = value;
	}
	
	public static int getDefaultRegenLevel()
	{
		return defaultRegenLevel;
	}

	public void setDefaultMaximumAttackBoost(float value)
	{
		defaultMaximumAttackBoost = value;
	}
	
	public float getDefaultMaximumAttackBoost()
	{
		return defaultMaximumAttackBoost;
	}

	public void setDefaultMaximumHealthBoost(float value)
	{
		defaultMaximumHealthBoost = value;
	}
	
	public float getDefaultMaximumHealthBoost()
	{
		return defaultMaximumHealthBoost;
	}

	public void setDefaultMaximumSpeedBoost(float value)
	{
		defaultMaximumSpeedBoost = value;
	}
	
	public float getDefaultMaximumSpeedBoost()
	{
		return defaultMaximumSpeedBoost;
	}

	public void setDefaultArmor(int value)
	{
		defaultArmor = value;
	}
	
	@Override
	public int getTotalArmorValue()
	{
		return defaultArmor;
	}
	
	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		if(this.hasDrowningImmunityEssence() == 1)
		{
			return currentAirSupply;
		}
		else
		{
			return super.decreaseAirSupply(currentAirSupply);
		}
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier)
	{
		if(this.hasFallImmunityEssence() != 1)
		{
			return super.onLivingFall(distance, damageMultiplier);
		}

		return false;
	}

	@Override
	public void tick()
	{
		super.tick();

		this.headRotationCourseOld = this.headRotationCourse;

		if(!this.world.isRemote)
		{
			checkAggro();
			checkMounted();
		}
		else
		{
			incrementPartClocks();

			calculateAggroValue();
			calculateMountedValue();
		}

		if(animationID != 0) animationTick++;

		toggleHappiness();
	}

	@Override
	public void livingTick()
	{
		super.livingTick();

		if(this.world.isRemote)
		{
			float currentYaw = this.renderYawOffset;

			if(currentYaw - previousYaw[0] > 180F)
			{
				for(int i = 0; i < previousYaw.length; i++)
				{
					previousYaw[i] += (currentYaw - previousYaw[i]);
				}
			}
			else if(currentYaw - previousYaw[0] < -180F)
			{
				for(int i = 0; i < previousYaw.length; i++)
				{
					previousYaw[i] += (currentYaw - previousYaw[i]);
				}
			}

			float sum = 0;
			for (float d : previousYaw) sum += d;
			float averagePreviousYaw = 1.0F * sum / previousYaw.length;

			changeInYaw = (currentYaw - averagePreviousYaw) / 50F;

			totalChangeInYaw =+ Math.abs(currentYaw) * 0.2F;

			for(int i = previousYaw.length - 1; i > 0; i--)
			{
				previousYaw[i] = previousYaw[i - 1];
			}

			previousYaw[0] = currentYaw;

			float currentHeight = (float)this.getPosY();

			float sum2 = 0;
			for (float d : previousHeight) sum2 += d;
			float averagePreviousHeight = 1.0F * sum2 / previousHeight.length;

			changeInHeight = this.ticksExisted>6?((currentHeight - averagePreviousHeight) / 2F):0;

			for(int i = previousHeight.length - 1; i > 0; i--)
			{
				previousHeight[i] = previousHeight[i - 1];
			}

			previousHeight[0] = currentHeight;

			applyCurrentTexture();
		}

		if (!this.world.isRemote)
		{
			if(this.isAlive() && this.isEntityInsideOpaqueBlock() && this.hasBlockSuffocationAvoidanceEssence() == 1)
			{
				LivingEntity theOwner = this.getOwner();

				if (theOwner != null)
				{
					if (!this.isSitting())
					{
						this.teleportToOwner(theOwner);
					}
				}
			}

			handleBeserkAI();
		}

		if(this.getHealth() < (float)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue())
		{
			if(this.isSitting() == true)
			{
				regenTimer += this.getRegenLevel();
			}
			else
			{
				regenTimer += (this.getRegenLevel() - 1);
			}

			if(regenTimer >= 700)
			{
				float healAmount = 1.0F;

				this.heal(healAmount);
				regenTimer = 0;

				if(this.world.isRemote)
				{
					spawnHealParticles(healAmount);
				}
			}
		}
		else
		{
			regenTimer = 0;
		}

		if(boostPowerTimer >= 0)
		{
			if(this.getBoostPowerType() == this.SPICE_MELANGE_POWER_ID)
			{
				if(this.rand.nextInt(15) == 1 && this.world.isRemote)
				{
					spawnAngerParticles(1);
				}
			}

			this.boostPowerTimer--;

			if(this.boostPowerTimer <= 0)
			{
				this.setBoostPowerType(this.NO_BOOST_POWER_ID);
			}
		}
	}

	public void checkAggro()
	{
		if(this.getAttackTarget() != null && this.getAttackTarget().isAlive())
		{
			if(!getIsAggroed())
			{
				setAggro(true);
			}
		}
		else
		{
			if(getIsAggroed())
			{
				setAggro(false);
			}
		}
	}

	public boolean getIsAggroed()
	{
		return this.dataManager.get(IS_AGGROED).booleanValue();
	}

	public void setAggro(boolean isAggroed)
	{
		this.dataManager.set(IS_AGGROED, isAggroed);
	}

	@Override
	public float getAggroValue()
	{
		return this.targetingAggroValue;
	}

	public void calculateAggroValue()
	{
		if(getIsAggroed())
		{
			this.targetingAggroValue += 0.1F;

			if(this.targetingAggroValue > 1F)
				this.targetingAggroValue = 1F;
		}
		else
		{
			this.targetingAggroValue -= 0.1F;

			if(this.targetingAggroValue < 0F)
				this.targetingAggroValue = 0F;
		}
	}

	public void checkMounted()
	{
		if(this.isBeingRidden())
		{
			if(!getIsMounted())
			{
				setMounted(true);

				removeAttackAITasks();
			}
		}
		else
		{
			if(getIsMounted())
			{
				setMounted(false);

				addAttackAITasks();

				this.setAttackTarget(null);
			}
		}
	}

	public void setMounted(boolean isMounted)
	{
		this.dataManager.set(IS_MOUNTED, isMounted);
	}

	public boolean getIsMounted()
	{
		return this.dataManager.get(IS_MOUNTED).booleanValue();
	}

	@Override
	public float getMountedValue()
	{
		return this.mountedValue;
	}

	public void calculateMountedValue()
	{
		if(getIsMounted())
		{
			this.mountedValue += 0.1F;

			if(this.mountedValue > 1F)
				this.mountedValue = 1F;
		}
		else
		{
			this.mountedValue -= 0.1F;

			if(this.mountedValue < 0F)
				this.mountedValue = 0F;
		}
	}

	public void addAttackAITasks() 	{}

	public void removeAttackAITasks() {}

	public void teleportToOwner(LivingEntity theOwner)
	{
		double i = MathHelper.floor(theOwner.getPosX());
		double j = MathHelper.floor(theOwner.getPosZ());
		double k = MathHelper.floor(theOwner.getBoundingBox().minY);

		this.attemptTeleport(i, k, j, true);
	}

	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);

		this.poketamableName = nbt.getString("petName");

		if (nbt.contains("MaximumHealth", 99))
		{
			float b0 = nbt.getFloat("MaximumHealth");
			this.setMaximumHealth(b0);
		}

		if (nbt.contains("AttackPower", 99))
		{
			float b0 = nbt.getFloat("AttackPower");
			this.setAttackPower(b0);
		}

		if (nbt.contains("Speed", 99))
		{
			float b0 = nbt.getFloat("Speed");
			this.setSpeed(b0);
		}

		if (nbt.contains("FireImmunity", 99))
		{
			int b0 = nbt.getInt("FireImmunity");
			this.setFireImmunityEssence(b0);
		}

		if (nbt.contains("DrowningImmunity", 99))
		{
			int b0 = nbt.getInt("DrowningImmunity");
			this.setDrowningImmunityEssence(b0);
		}

		if (nbt.contains("FallImmunity", 99))
		{
			int b0 = nbt.getInt("FallImmunity");
			this.setFallImmunityEssence(b0);
		}

		if (nbt.contains("BlockSuffacationAvoidance", 99))
		{
			int b0 = nbt.getInt("BlockSuffacationAvoidance");
			this.setBlockSuffocationAvoidanceEssence(b0);
		}

		if (nbt.contains("ToxinImmunity", 99))
		{
			int b0 = nbt.getInt("ToxinImmunity");
			this.setToxinImmunityEssence(b0);
		}

		if (nbt.contains("SpaceSurvivability", 99))
		{
			int b0 = nbt.getInt("SpaceSurvivability");
			this.setSpaceSurvivabilityEssence(b0);
		}

		if (nbt.contains("RegenLevel", 99))
		{
			int b0 = nbt.getInt("RegenLevel");
			this.setRegenLevel(b0);
		}

		if (nbt.contains("BoostPower", 99))
		{
			int b0 = nbt.getInt("BoostPower");
			this.setBoostPowerType(b0);
		}

		if (nbt.contains("BoostPowerTime", 99))
		{
			int b0 = nbt.getInt("BoostPowerTime");
			this.boostPowerTimer = b0;
		}
	}

	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);

		nbt.putString("petName", this.poketamableName);

		nbt.putFloat("MaximumHealth", (float)this.getMaximumHealth());
		nbt.putFloat("AttackPower", (float)this.getAttackPower());
		nbt.putFloat("Speed", (float)this.getSpeed());
		nbt.putInt("FireImmunity", (int)this.hasFireImmunityEssence());
		nbt.putInt("DrowningImmunity", (int)this.hasDrowningImmunityEssence());
		nbt.putInt("FallImmunity", (int)this.hasFallImmunityEssence());
		nbt.putInt("BlockSuffacationAvoidance", (int)this.hasBlockSuffocationAvoidanceEssence());
		nbt.putInt("ToxinImmunity", (int)this.hasToxinImmunityEssence());

		nbt.putInt("SpaceSurvivability", (int)this.hasSpaceSurvivabilityEssence());

		nbt.putInt("RegenLevel", (int)this.getRegenLevel());

		nbt.putInt("BoostPower", (int)this.getBoostPowerType());
		nbt.putInt("BoostPowerTime", this.boostPowerTimer);
	}

	public int getMainTextureType()
	{
		return (int)this.dataManager.get(TEXTURE).byteValue();
	}

	public void setMainTextureType(int par1)
	{
		this.dataManager.set(TEXTURE, (byte)par1);
	}

	public void applyCurrentTexture()
	{
		if(!this.isTamed())
		{
			setMainTextureType(0);
		}
		else if(this.getHealth() < ((float)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 4))
		{
			setMainTextureType(1);
		}
		else if(isHappy == true)
		{
			setMainTextureType(2);
		}
		else
		{
			setMainTextureType(0);
		}
	}

	public int getSoundType()
	{
		return (int)this.dataManager.get(SOUND).byteValue();
	}

	public void setSoundType(int par1)
	{
		this.dataManager.set(SOUND, (byte)par1);
	}

	public void toggleIdleSounds()
	{
		if(getSoundType() == 0)
		{
			setSoundType(1);
		}
		else
		{
			setSoundType(0);
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) 
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.7F;
	}

	public void toggleHappiness()
	{
		if(this.happyTimer != 0 || isHappy)
		{
			if(this.happyTimer >= this.happyDuration)
			{
				this.happyTimer = 0;

				isHappy = false;
			}
			else
			{
				this.happyTimer++;

				isHappy = true;
			}
		}
	}

	/**
	 * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
	 * use in wolves.
	 */
	public int getVerticalFaceSpeed()
	{
		return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
	}

	public boolean berryHeal(PlayerEntity par1EntityPlayer, float healthMaximum, BerryItem berry)
	{
		boolean decreaseBerryItemstack = false;

		float oldHealthAmount = this.getHealth();

		if (!par1EntityPlayer.abilities.isCreativeMode)
		{
			decreaseBerryItemstack = true;
		}

		this.heal(berry.getPokemonHealAmount());

		if(this.world.isRemote)
		{
			spawnHealParticles(berry.getPokemonHealAmount());

			if((oldHealthAmount + berry.getPokemonHealAmount()) >= healthMaximum)
			{	
				spawnHeartParticles(2);

				isHappy = true;	
			}
		}
		/*
		if(!(berry instanceof ItemBerryOranian))
		{
			if(berry instanceof ItemBerryColberta && this.getActivePotionEffect(Potion.blindness) != null)
			{
				this.removePotionEffect(Potion.blindness.id);

				isHappy = true;
			}
			else if(berry instanceof ItemBerryPasshaura)
			{
				this.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 1800 * 20, 1));

				isHappy = true;
			}
			else if(berry instanceof ItemBerryPechita && this.getActivePotionEffect(Potion.poison) != null)
			{
				this.removePotionEffect(Potion.poison.id);

				isHappy = true;
			}
			else if(berry instanceof ItemBerryRawstetta)
			{
				this.extinguish();
				this.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 1800 * 20, 1));

				isHappy = true;
			}
		}
		 */
		return decreaseBerryItemstack;
	}

	public void decreasePlayerItemStack(PlayerEntity player, ItemStack itemstack)
	{
		if (!player.abilities.isCreativeMode)
		{
			itemstack.shrink(1); // Decrease itemStack by 1
		}
	}

	public void activateHealthRegen(PlayerEntity player, ItemStack itemstack)
	{
		int durationSeconds = 300;

		this.addPotionEffect(new EffectInstance(Effects.REGENERATION, durationSeconds * 20, 0));

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnHealParticles(4);
			spawnHeartParticles(2);
		}
	}

	public void applyLifeBoost(PlayerEntity player, ItemStack itemstack)
	{
		if(!this.world.isRemote)
		{
			this.setMaximumHealth(this.getMaximumHealth() + 1F);

			this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.getMaximumHealth());
		}

		this.heal(1F);

		if(this.world.isRemote)
		{
			spawnHealParticles(1F);

			spawnHeartParticles(1);
		}

		decreasePlayerItemStack(player, itemstack);

		isHappy = true;
	}

	public void applyFiragaEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setFireImmunityEssence(1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnFlameParticles(8);

			spawnHeartParticles(1);
		}
	}

	public void applyWatergaEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setDrowningImmunityEssence(1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnBubbleParticles(14);

			spawnHeartParticles(1);
		}
	}

	public void applyGravigaEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setFallImmunityEssence(1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnPortalParticles(10);

			spawnHeartParticles(1);
		}
	}

	public void applyQuakagaEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setBlockSuffocationAvoidanceEssence(1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnMagicCritParticles(8);

			spawnHeartParticles(1);
		}
	}

	public void applyBiogaEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setToxinImmunityEssence(1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnHealParticles(8);

			spawnHeartParticles(1);
		}
	}

	public void applyCometEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setSpaceSurvivabilityEssence(1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnPortalParticles(8);

			spawnHeartParticles(1);
		}
	}

	public void applyCuragaEssence(PlayerEntity player, ItemStack itemstack)
	{
		this.setRegenLevel(this.getRegenLevel() + 1);

		isHappy = true;

		decreasePlayerItemStack(player, itemstack);

		if(this.world.isRemote)
		{
			spawnHealParticles(14);

			spawnHeartParticles(1);
		}
	}

	public void applyAttackBoost(PlayerEntity player, ItemStack itemstack)
	{
		if(!this.world.isRemote)
		{
			this.setAttackPower(this.getAttackPower() + 1.0F);
		}

		if(this.world.isRemote)
		{
			spawnFlameParticles(4);

			spawnHeartParticles(1);
		}

		decreasePlayerItemStack(player, itemstack);

		isHappy = true;
	}

	public void applySpeedBoost(PlayerEntity player, ItemStack itemstack)
	{
		if(!this.world.isRemote)
		{
			this.setSpeed(this.getSpeed() + 0.01F);
		}

		if(this.world.isRemote)
		{
			spawnMagicCritParticles(4);

			spawnHeartParticles(1);
		}

		decreasePlayerItemStack(player, itemstack);

		isHappy = true;
	}

	@OnlyIn(Dist.CLIENT)
	private void spawnParticles(BasicParticleType particle)
	{
		double d0 = this.rand.nextGaussian() * 0.02D;
		double d1 = this.rand.nextGaussian() * 0.02D;
		double d2 = this.rand.nextGaussian() * 0.02D;

		this.world.addParticle(particle, this.getPosX() + (double)(this.rand.nextFloat() * this.getWidth() * 2.0F) - (double)this.getWidth(), this.getPosY() + (double)(this.rand.nextFloat() * this.getHeight()), this.getPosZ() + (double)(this.rand.nextFloat() * this.getWidth() * 2.0F) - (double)this.getWidth(), d0, d1, d2);
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnHealParticles(float healAmount)
	{
		for (int i = 0; i < (int)healAmount; ++i)
		{
			this.spawnParticles(ParticleTypes.HAPPY_VILLAGER);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnHeartParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(ParticleTypes.HEART);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnFlameParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(ParticleTypes.FLAME);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnBubbleParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(ParticleTypes.SPLASH);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnPortalParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(ParticleTypes.PORTAL);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnMagicCritParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(ParticleTypes.CRIT);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void spawnAngerParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(ParticleTypes.ANGRY_VILLAGER);
		}
	}

	public void applySit()
	{
		this.sitGoal.setSitting(!this.isSitting());
		this.isJumping = false;
		this.navigator.clearPath();
		this.setAttackTarget((LivingEntity)null);
	}

	/**
	 * Returns true if this entity can attack entities of the specified class.
	 */
	@Override
	public boolean canAttack(EntityType<?> typeIn)
	{
		return EntityType.GHAST != typeIn && EntityType.VILLAGER != typeIn && EntityType.PLAYER != typeIn;// && typeIn.getClass().isInstance(TamablePokemon.class);//EntityType.CREEPER != typeIn && 
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 8)
		{
			//this.isShaking = true;
			//this.timeWolfIsShaking = 0.0F;
			//this.prevTimeWolfIsShaking = 0.0F;
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getHealthPercent()
	{
		float healthAmount = this.getHealth();

		if(!this.isTamed())
		{
			return 0;
		}

		return healthAmount / (float)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
	public boolean canDespawn(double distanceToClosestPlayer)
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	public void returnToItem() {};
	public void returnToItemOnDeath() {};

	@Override
	public void onDeath(DamageSource cause) 
	{
		returnToItemOnDeath();

		super.onDeath(cause);
	}
	
	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if(player.isCrouching() && hand == Hand.MAIN_HAND && !this.world.isRemote)
			{
				this.toggleIdleSounds();
			}

			if (itemstack != null)
			{
				if (itemstack.getItem() instanceof BerryItem)
				{
					BerryItem berry = (BerryItem)itemstack.getItem();

					boolean decreaseBerryItemstack = false;

					if(this.getHealth() < ((float)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()))
					{	
						decreaseBerryItemstack = berryHeal(player, ((float)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()), berry);

						if(decreaseBerryItemstack == true)
						{
							decreasePlayerItemStack(player, itemstack);
						}

						return true;
					}
				}
				else if(itemstack.getItem() instanceof IBoostItem)
				{
					if(itemstack.getItem() == ModItems.LIFE_BOOST.get() && this.getMaximumHealth() < this.getDefaultMaximumHealthBoost())
					{
						applyLifeBoost(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.ATTACK_BOOST.get() && this.getAttackPower() < this.getDefaultMaximumAttackBoost())
					{
						applyAttackBoost(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.SPEED_BOOST.get() && this.getSpeed() < this.getDefaultMaximumSpeedBoost())
					{
						applySpeedBoost(player, itemstack);

						return true;
					}
				}
				else if(itemstack.getItem() instanceof IEssenceItem)
				{
					if(itemstack.getItem() == ModItems.FIRAGA_ESSENCE.get() && this.hasFireImmunityEssence() != 1)
					{
						applyFiragaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.WATERGA_ESSENCE.get() && this.hasDrowningImmunityEssence() != 1)
					{
						applyWatergaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.GRAVIGA_ESSENCE.get() && this.hasFallImmunityEssence() != 1)
					{
						applyGravigaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.QUAKAGA_ESSENCE.get() && this.hasBlockSuffocationAvoidanceEssence() != 1)
					{
						applyQuakagaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.BIOGA_ESSENCE.get() && this.hasToxinImmunityEssence() != 1)
					{
						applyBiogaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == ModItems.CURAGA_ESSENCE.get() && this.getRegenLevel() < 3)
					{
						applyCuragaEssence(player, itemstack);

						return true;
					}
				}
				else if(itemstack.getItem() instanceof IPowerUp)
				{
					if(itemstack.getItem() == ModItems.RAW_SPICE_MELANGE.get())
					{
						this.setBoostPowerType(this.SPICE_MELANGE_POWER_ID);

						decreasePlayerItemStack(player, itemstack);

						this.boostPowerTimer = 5000;

						return true;
					}
				}
				else if(itemstack.getItem() == ModItems.REGEN_CREAM.get())
				{
					activateHealthRegen(player, itemstack);

					return true;
				}
			}

			if (this.isOwner(player) && !this.world.isRemote && !player.isCrouching())// && itemstack.getItem()) != KindredLegacyItems.ATTACK_ORDERER)
			{
				applySit();
			}
		}

		return super.processInteract(player, hand);
	}
	
	
	@Override
	public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner)
	{
		if (!(target instanceof GhastEntity))
		{
			if (target instanceof TamablePokemon)
			{
				TamablePokemon entityPokemon = (TamablePokemon)target;

				if (entityPokemon.isTamed() && entityPokemon.getOwner() == owner)
				{
					return false;
				}
			}

			return target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).canAttackPlayer((PlayerEntity)target) ? false : !(target instanceof HorseEntity) || !((HorseEntity)target).isTame();
		}
		else
		{
			return false;
		}
	}

	/*
	 * Required function to implement.
	 */
	@Override
	public AgeableEntity createChild(AgeableEntity var1)
	{
		return null;
	}

	public void setDeadWithoutRecall()
	{
		this.remove(false);
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if ((source == DamageSource.LAVA || source == DamageSource.IN_FIRE || source == DamageSource.ON_FIRE)
				&& this.hasFireImmunityEssence() == 1)
		{
			return false;
		}
		else
		{
			if (this.sitGoal != null)
			{
				this.sitGoal.setSitting(false);
			}

			if (source.getTrueSource() != null)
			{
				if(this.isTamed() && this.getOwner() instanceof PlayerEntity)
				{
					if(source.getTrueSource() instanceof PlayerEntity)
					{
						PlayerEntity entityPlayer = (PlayerEntity)this.getOwner();

						if(!entityPlayer.abilities.isCreativeMode)
						{
							return false;
						}
					}
					else if(source.getTrueSource() instanceof TamablePokemon)
					{
						if(((TamablePokemon)source.getTrueSource()).getOwner() instanceof PlayerEntity)
						{
							return false;
						}
					}
				}
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public float getInterestedAngle(float p_70917_1_)
	{
		return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float)Math.PI;
	}

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return false;
	}

	@Override
	public boolean isPotionApplicable(EffectInstance potioneffectIn)
	{
		if (potioneffectIn.getPotion() == Effects.POISON || potioneffectIn.getPotion() == Effects.WITHER) 
		{
			if(this.hasToxinImmunityEssence() == 1)
			{
				net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent event = new net.minecraftforge.event.entity.living.PotionEvent.PotionApplicableEvent(this, potioneffectIn);
				net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event);
				return event.getResult() == net.minecraftforge.eventbus.api.Event.Result.ALLOW;
			}
		}

		return super.isPotionApplicable(potioneffectIn);
	}

	protected void handleBeserkAI()
	{
		if (this.beserkAIMobs == null)
		{
			this.beserkAIMobs = new NearestAttackableTargetGoal<>(this, MonsterEntity.class, false);
		}

		if (this.getBoostPowerType() != this.SPICE_MELANGE_POWER_ID)
		{
			this.goalSelector.removeGoal(this.beserkAIMobs);
		}
		else
		{
			this.goalSelector.addGoal(4, this.beserkAIMobs);
		}
	}

	@Override
	public float getGravityFactor()
	{
		if(this.worldGravity != 0)
		{
			return (float) Math.sqrt(this.worldGravity / 0.08F);
		}
		else
		{
			return 1F;
		}
	}

	@Override
	public float getGravityVsOverworldRatio()
	{
		if(this.worldGravity != 0)
		{
			return (float) this.worldGravity / 0.08F;
		}
		else
		{
			return 1F;
		}
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks() {}

	@OnlyIn(Dist.CLIENT)
	public float getAngularVelocity()
	{	
		float angularVelocity = changeInYaw;

		if(angularVelocity != 0)
		{
			if(angularVelocity > 1F)
				angularVelocity = 1F;
			else if(angularVelocity < -1F)
				angularVelocity = -1F;
		}

		return angularVelocity;
	}

	@OnlyIn(Dist.CLIENT)
	public float getTotalAngularChange()
	{
		return totalChangeInYaw;
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeightVelocity()
	{	
		float verticalVelocity = changeInHeight;

		if(verticalVelocity != 0)
		{
			if(verticalVelocity > 1F)
				verticalVelocity = 1F;
			else if(verticalVelocity < -1F)
				verticalVelocity = -1F;
		}
		return verticalVelocity;
	}

	@Override
	public void setAnimationID(int id) 
	{
		animationID = id;
	}

	@Override
	public void setAnimationTick(int tick) 
	{
		animationTick = tick;	
	}

	@Override
	public int getAnimationID() 
	{
		return animationID;
	}

	@Override
	public int getAnimationTick() 
	{
		return animationTick;
	}
}