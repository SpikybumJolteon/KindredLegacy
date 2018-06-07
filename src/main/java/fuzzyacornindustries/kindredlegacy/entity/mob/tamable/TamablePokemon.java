package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.IGravityTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.item.BerryItem;
import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.api.entity.IEntityBreathable;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Optional.Interface(iface="micdoodle8.mods.galacticraft.api.entity.IEntityBreathable", modid="galacticraftcore", striprefs=true)
public class TamablePokemon extends EntityTameable implements IEntityAdditionalSpawnData, IAnimatedEntity, IMobMotionTracker, IGravityTracker, IEntityBreathable
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
	
	protected static final int NO_BOOST_POWER_ID = 0;
	protected static final int SPICE_MELANGE_POWER_ID = 1;

	private EntityAINearestAttackableTarget<EntityMob> beserkAIMobs;

	private String poketamableName;

	public double worldGravity;

	@SideOnly(Side.CLIENT)
	public float previousYaw[] = new float[6];
	@SideOnly(Side.CLIENT)
	public float changeInYaw;

	@SideOnly(Side.CLIENT)
	public float previousHeight[] = new float[6];
	@SideOnly(Side.CLIENT)
	public float changeInHeight;

	public static final int actionIDNone = 0;

	protected int animationID;
	protected int animationTick;

	public int regenTimer = 0;

	public int boostPowerTimer = 0;

	boolean isHappy = false;
	int happyTimer = 0;
	int happyDuration = 20;

	public boolean isBegging = false;

	/** Float used to smooth the rotation of the mob head */
	private float headRotationCourse;
	private float headRotationCourseOld;

	public TamablePokemon(World par1World)
	{
		super(par1World);

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

			for(int i = 0; i < previousHeight.length; i++)
			{
				previousHeight[i] = (float)this.posY;
			}

			changeInHeight = 0;
		}
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.register(CURRENT_HEALTH, Float.valueOf(this.getHealth()));

		this.dataManager.register(TEXTURE, Byte.valueOf(Byte.valueOf((byte)0)));
		this.dataManager.register(SOUND, Byte.valueOf(Byte.valueOf((byte)0)));

		this.dataManager.register(MAX_HEALTH, Float.valueOf((float)0));
		this.dataManager.register(ATTACK_POWER, Float.valueOf((float)0));
		this.dataManager.register(SPEED, Float.valueOf((float)0));

		this.dataManager.register(FIRE_IMMUNITY, Byte.valueOf(Byte.valueOf((byte)0)));
		this.dataManager.register(DROWNING_IMMUNITY, Byte.valueOf(Byte.valueOf((byte)0)));
		this.dataManager.register(FALL_DAMAGE_IMMUNITY, Byte.valueOf(Byte.valueOf((byte)0)));
		this.dataManager.register(BLOCK_SUFFOCATION_AVOIDANCE, Byte.valueOf(Byte.valueOf((byte)0)));
		this.dataManager.register(TOXIN_IMMUNITY, Byte.valueOf(Byte.valueOf((byte)0)));

		this.dataManager.register(REGEN_LEVEL, Byte.valueOf(Byte.valueOf((byte)0)));

		this.dataManager.register(BOOST_POWER, Byte.valueOf(Byte.valueOf((byte)0)));

		this.dataManager.register(SPACE_SURVIVALBILITY, Byte.valueOf(Byte.valueOf((byte)0)));
	}

	public float getMaximumHealth()
	{
		return ((Float)this.dataManager.get(MAX_HEALTH)).floatValue();
	}

	public void setMaximumHealth(float newMaxHealth)
	{
		this.dataManager.set(MAX_HEALTH, Float.valueOf(newMaxHealth));

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)newMaxHealth);
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

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double)newMaxSpeed);
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

			this.isImmuneToFire = true;
		}
		else
		{
			this.dataManager.set(FIRE_IMMUNITY, (byte)0);

			this.isImmuneToFire = false;
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
	public void writeSpawnData(ByteBuf data)
	{
		ByteBufUtils.writeUTF8String(data, poketamableName);
	}

	@Override
	public void readSpawnData(ByteBuf data)
	{
		poketamableName = ByteBufUtils.readUTF8String(data);
	}

	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentTranslation(poketamableName);
	}

	/**
	 * Used by Renderer to display Poketamable Name
	 */
	@Override
	public String getName()
	{
		return poketamableName;
	}

	public void setMobName(String name)
	{
		this.poketamableName = name;
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
	public void fall(float distance, float damageMultiplier)
	{
		if(this.hasFallImmunityEssence() != 1)
		{
			super.fall(distance, damageMultiplier);
		}
	}

	@Method(modid="galacticraftcore")
	@Override
	public boolean canBreath()
	{
		if(this.hasSpaceSurvivabilityEssence() == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		this.headRotationCourseOld = this.headRotationCourse;

		if(this.world.isRemote)
		{
			incrementPartClocks();
		}
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

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

			for(int i = previousYaw.length - 1; i > 0; i--)
			{
				previousYaw[i] = previousYaw[i - 1];
			}

			previousYaw[0] = currentYaw;

			//System.out.println( "Test change in pitch" );
			//System.out.println( Float.toString( this.renderYawOffset ) );
			//System.out.println( Float.toString( averagePreviousYaw ) );
			//System.out.println( Float.toString( changeInYaw ) );

			float currentHeight = (float)this.posY;

			float sum2 = 0;
			for (float d : previousHeight) sum2 += d;
			float averagePreviousHeight = 1.0F * sum2 / previousHeight.length;

			changeInHeight = this.ticksExisted>6?((currentHeight - averagePreviousHeight) / 2F):0;

			for(int i = previousHeight.length - 1; i > 0; i--)
			{
				previousHeight[i] = previousHeight[i - 1];
			}

			previousHeight[0] = currentHeight;
		}

		if (!this.world.isRemote)
		{
			if(this.isEntityAlive() && this.isEntityInsideOpaqueBlock() && this.hasBlockSuffocationAvoidanceEssence() == 1)
			{
				EntityLivingBase theOwner = this.getOwner();

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

		if(this.getHealth() < (float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue())
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

				spawnHealParticles(healAmount);
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
				if(this.rand.nextInt(15) == 1)
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

	public void teleportToOwner(EntityLivingBase theOwner)
	{
		double i = MathHelper.floor(theOwner.posX);
		double j = MathHelper.floor(theOwner.posZ);
		double k = MathHelper.floor(theOwner.getEntityBoundingBox().minY);

		boolean flag = this.attemptTeleport(i, k, j);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);

		this.poketamableName = nbt.getString("petName");

		if (nbt.hasKey("MaximumHealth", 99))
		{
			float b0 = nbt.getFloat("MaximumHealth");
			this.setMaximumHealth(b0);
		}

		if (nbt.hasKey("AttackPower", 99))
		{
			float b0 = nbt.getFloat("AttackPower");
			this.setAttackPower(b0);
		}

		if (nbt.hasKey("Speed", 99))
		{
			float b0 = nbt.getFloat("Speed");
			this.setSpeed(b0);
		}

		if (nbt.hasKey("FireImmunity", 99))
		{
			int b0 = nbt.getInteger("FireImmunity");
			this.setFireImmunityEssence(b0);
		}

		if (nbt.hasKey("DrowningImmunity", 99))
		{
			int b0 = nbt.getInteger("DrowningImmunity");
			this.setDrowningImmunityEssence(b0);
		}

		if (nbt.hasKey("FallImmunity", 99))
		{
			int b0 = nbt.getInteger("FallImmunity");
			this.setFallImmunityEssence(b0);
		}

		if (nbt.hasKey("BlockSuffacationAvoidance", 99))
		{
			int b0 = nbt.getInteger("BlockSuffacationAvoidance");
			this.setBlockSuffocationAvoidanceEssence(b0);
		}

		if (nbt.hasKey("ToxinImmunity", 99))
		{
			int b0 = nbt.getInteger("ToxinImmunity");
			this.setToxinImmunityEssence(b0);
		}

		if (nbt.hasKey("SpaceSurvivability", 99))
		{
			int b0 = nbt.getInteger("SpaceSurvivability");
			this.setSpaceSurvivabilityEssence(b0);
		}
		
		if (nbt.hasKey("RegenLevel", 99))
		{
			int b0 = nbt.getInteger("RegenLevel");
			this.setRegenLevel(b0);
		}

		if (nbt.hasKey("BoostPower", 99))
		{
			int b0 = nbt.getInteger("BoostPower");
			this.setBoostPowerType(b0);
		}

		if (nbt.hasKey("BoostPowerTime", 99))
		{
			int b0 = nbt.getInteger("BoostPowerTime");
			this.boostPowerTimer = b0;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);

		nbt.setString("petName", this.poketamableName);

		nbt.setFloat("MaximumHealth", (float)this.getMaximumHealth());
		nbt.setFloat("AttackPower", (float)this.getAttackPower());
		nbt.setFloat("Speed", (float)this.getSpeed());
		nbt.setInteger("FireImmunity", (int)this.hasFireImmunityEssence());
		nbt.setInteger("DrowningImmunity", (int)this.hasDrowningImmunityEssence());
		nbt.setInteger("FallImmunity", (int)this.hasFallImmunityEssence());
		nbt.setInteger("BlockSuffacationAvoidance", (int)this.hasBlockSuffocationAvoidanceEssence());
		nbt.setInteger("ToxinImmunity", (int)this.hasToxinImmunityEssence());

		nbt.setInteger("SpaceSurvivability", (int)this.hasSpaceSurvivabilityEssence());
		
		nbt.setInteger("RegenLevel", (int)this.getRegenLevel());

		nbt.setInteger("BoostPower", (int)this.getBoostPowerType());
		nbt.setInteger("BoostPowerTime", this.boostPowerTimer);
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
		else if(this.getHealth() < ((float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 4))
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
	protected void playStepSound(BlockPos pos, Block blockIn)
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

	public boolean berryHeal(EntityPlayer par1EntityPlayer, float healthMaximum, BerryItem berry)
	{
		boolean decreaseBerryItemstack = false;

		float oldHealthAmount = this.getHealth();

		if (!par1EntityPlayer.capabilities.isCreativeMode)
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

	public void decreasePlayerItemStack(EntityPlayer player, ItemStack itemstack)
	{
		if (!player.capabilities.isCreativeMode)
		{
			itemstack.shrink(1); // Decrease itemStack by 1
		}
	}

	public void applyLifeBoost(EntityPlayer player, ItemStack itemstack)
	{
		if(!this.world.isRemote)
		{
			this.setMaximumHealth(this.getMaximumHealth() + 1F);

			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.getMaximumHealth());
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

	public void applyFiragaEssence(EntityPlayer player, ItemStack itemstack)
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

	public void applyWatergaEssence(EntityPlayer player, ItemStack itemstack)
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

	public void applyGravigaEssence(EntityPlayer player, ItemStack itemstack)
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

	public void applyQuakagaEssence(EntityPlayer player, ItemStack itemstack)
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

	public void applyBiogaEssence(EntityPlayer player, ItemStack itemstack)
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
	
	public void applyCometEssence(EntityPlayer player, ItemStack itemstack)
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

	public void applyCuragaEssence(EntityPlayer player, ItemStack itemstack)
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

	public void applyAttackBoost(EntityPlayer player, ItemStack itemstack)
	{
		if(!this.world.isRemote)
		{
			this.setAttackPower(this.getAttackPower() + 1.0F);
		}

		if(this.world.isRemote)
		{
			spawnAttackChargeParticles(4);

			spawnHeartParticles(1);
		}

		decreasePlayerItemStack(player, itemstack);

		isHappy = true;
	}

	public void applySpeedBoost(EntityPlayer player, ItemStack itemstack)
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

	@SideOnly(Side.CLIENT)
	private void spawnParticles(EnumParticleTypes particleType)
	{
		double d0 = this.rand.nextGaussian() * 0.02D;
		double d1 = this.rand.nextGaussian() * 0.02D;
		double d2 = this.rand.nextGaussian() * 0.02D;

		this.world.spawnParticle(particleType, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2, new int[0]);
	}

	@SideOnly(Side.CLIENT)
	public void spawnHealParticles(float healAmount)
	{
		for (int i = 0; i < (int)healAmount; ++i)
		{
			this.spawnParticles(EnumParticleTypes.VILLAGER_HAPPY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnAttackChargeParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.REDSTONE);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnHeartParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.HEART);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnFlameParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.FLAME);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnBubbleParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.WATER_SPLASH);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnPortalParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.PORTAL);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnMagicCritParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.CRIT_MAGIC);
		}
	}

	@SideOnly(Side.CLIENT)
	public void spawnAngerParticles(int numOfParticles)
	{
		for (int i = 0; i < numOfParticles; ++i)
		{
			this.spawnParticles(EnumParticleTypes.VILLAGER_ANGRY);
		}
	}

	public void applySit()
	{
		this.aiSit.setSitting(!this.isSitting());
		this.isJumping = false;
		this.navigator.clearPath();
		this.setAttackTarget((EntityLivingBase)null);
	}

	/**
	 * Returns true if this entity can attack entities of the specified class.
	 */
	@Override
	public boolean canAttackClass(Class par1Class)
	{
		return EntityCreeper.class != par1Class && EntityGhast.class != par1Class && EntityVillager.class != par1Class && EntityPlayer.class != par1Class && TamablePokemon.class != par1Class;
	}

	@Override
	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
	public float getHealthPercent()
	{
		float healthAmount = this.getHealth();

		if(!this.isTamed())
		{
			return 0;
		}

		return healthAmount / (float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue();
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
	protected boolean canDespawn()
	{
		return !this.isTamed() && this.ticksExisted > 2400;
	}

	public void returnToItem() {};

	@Override
	public boolean shouldAttackEntity(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase)
	{
		if (!(par1EntityLivingBase instanceof EntityGhast))
		{
			if (par1EntityLivingBase instanceof TamablePokemon)
			{
				TamablePokemon entityPokemon = (TamablePokemon)par1EntityLivingBase;

				if (entityPokemon.isTamed() && entityPokemon.getOwner() == par2EntityLivingBase)
				{
					return false;
				}
			}

			return par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase) ? false : !(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame();
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
	public EntityAgeable createChild(EntityAgeable var1)
	{
		return null;
	}

	public void setDeadWithoutRecall()
	{
		super.setDead();
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else
		{
			if (this.aiSit != null)
			{
				this.aiSit.setSitting(false);
			}

			if (source.getTrueSource() != null)
			{
				if(this.isTamed() && this.getOwner() instanceof EntityPlayer)
				{
					if(source.getTrueSource() instanceof EntityPlayer)
					{
						EntityPlayer entityPlayer = (EntityPlayer)this.getOwner();

						if(!entityPlayer.capabilities.isCreativeMode)
						{
							return false;
						}
					}
					else if(source.getTrueSource() instanceof TamablePokemon)
					{
						if(((TamablePokemon)source.getTrueSource()).getOwner() instanceof EntityPlayer)
						{
							return false;
						}
					}
				}
			}

			return super.attackEntityFrom(source, amount);
		}
	}

	@SideOnly(Side.CLIENT)
	public float getInterestedAngle(float p_70917_1_)
	{
		return (this.headRotationCourseOld + (this.headRotationCourse - this.headRotationCourseOld) * p_70917_1_) * 0.15F * (float)Math.PI;
	}
	/*
	@Override
	public String getCommandSenderName()
	{
		return poketamableName;
	}*/

	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack)
	{
		return false;
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn)
	{
		if(potioneffectIn.getPotion() == MobEffects.POISON || potioneffectIn.getPotion() == MobEffects.WITHER)
		{
			if(this.hasToxinImmunityEssence() == 1)
				return false;
		}

		super.isPotionApplicable(potioneffectIn);

		return true;
	}

	protected void handleBeserkAI()
	{
		if (this.beserkAIMobs == null)
		{
			this.beserkAIMobs = new EntityAINearestAttackableTarget(this, EntityMob.class, false);
		}

		if (this.getBoostPowerType() != this.SPICE_MELANGE_POWER_ID)
		{
			this.targetTasks.removeTask(this.beserkAIMobs);
		}
		else
		{
			this.targetTasks.addTask(4, this.beserkAIMobs);
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
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks() {}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
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