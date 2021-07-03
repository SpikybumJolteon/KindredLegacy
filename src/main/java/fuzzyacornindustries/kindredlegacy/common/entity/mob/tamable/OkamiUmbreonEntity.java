package fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.GeneralRangedAttackGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.OkamiGlaiveSlashGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.OkamiGlaiveSlashReverseGoal;
import fuzzyacornindustries.kindredlegacy.common.network.ActionAnimationMessage;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class OkamiUmbreonEntity extends OkamiPokemon implements IRangedAttackMob
{
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[7];

	static String mobName = "okami_umbreon";

	public final float meleeRange = 2.8F;

	public static float defaultBaseAttackPower = 2F;
	public static float defaultBaseMaxHealth = 30F;
	public static float defaultBaseSpeed = 0.30F;

	public static int defaultRegenLevel = 3;

	public static float defaultMaximumAttackBoost = 8F;
	public static float defaultMaximumHealthBoost = 100F;
	public static float defaultMaximumSpeedBoost = 0.36F;

	public static int defaultArmor = 10;
	
	public OkamiUmbreonEntity(EntityType<? extends OkamiUmbreonEntity> type, World world)
	{
		super(type, world);

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
		
		default_name = "Okami Umbreon";
		default_summon_item_name = "Okami Umbreon Summon";
		summonItem = new ItemStack(ModItems.OKAMI_UMBREON_SUMMON.get());
	}

	public OkamiUmbreonEntity(World world) 
	{
		this(ModEntities.OKAMI_UMBREON.get(), world);
	}

	public static String getMobName()
	{
		return mobName;
	}

	protected void registerGoals() 
	{
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new OkamiGlaiveSlashGoal(this, meleeRange));
		this.goalSelector.addGoal(2, new OkamiGlaiveSlashReverseGoal(this, meleeRange));

		this.goalSelector.addGoal(3, this.sitGoal);
		this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 1, meleeRange));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.85D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookAtGoal(this, TamablePokemon.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		//this.tasks.addTask(8, new AIGeneralBerryBeg(this, 8.0F));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		//this.targetSelector.addGoal(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));
	}

	@Override
	public void setDefaultStats()
	{
		setDefaultBaseAttackPower(defaultBaseAttackPower);
		setDefaultBaseMaxHealth(defaultBaseMaxHealth);
		setDefaultBaseSpeed(defaultBaseSpeed);

		setDefaultRegenLevel(defaultRegenLevel);

		setDefaultMaximumAttackBoost(defaultMaximumAttackBoost);
		setDefaultMaximumHealthBoost(defaultMaximumHealthBoost);
		setDefaultMaximumSpeedBoost(defaultMaximumSpeedBoost);

		setDefaultArmor(defaultArmor);
	}
	
	@Override
	protected void registerData() 
	{
		super.registerData();

		this.dataManager.set(MAX_HEALTH, getDefaultBaseMaxHealth());
		this.dataManager.set(ATTACK_POWER, getDefaultBaseAttackPower());
		this.dataManager.set(SPEED, getDefaultBaseSpeed());

		this.dataManager.set(REGEN_LEVEL, (byte)getDefaultRegenLevel());

		this.setFireImmunityEssence(1);
		this.setToxinImmunityEssence(1);
		this.setSpaceSurvivabilityEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return ModSounds.OKAMI_UMBREON_WHINE.get();
		}
		else if(this.dataManager.get(SOUND).byteValue() == 1)
		{
			return null;
		}
		else
		{
			return ModSounds.OKAMI_UMBREON_AMBIENT.get();
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.OKAMI_UMBREON_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.OKAMI_UMBREON_DEATH.get();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);

		nbt.putByte("OkamiUmbreonTextureType", (byte)this.getMainTextureType());
		nbt.putByte("OkamiUmbreonSoundType", (byte)this.getSoundType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);

		if (nbt.contains("OkamiUmbreonTextureType", 99))
		{
			byte b0 = nbt.getByte("OkamiUmbreonTextureType");
			this.setMainTextureType(b0);
		}

		if (nbt.contains("OkamiUmbreonSoundType", 99))
		{
			byte b0 = nbt.getByte("OkamiUmbreonSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
	{
		if(this.getDistanceSq(targetEntity.getPosX(), targetEntity.getPosYHeight(0.5), targetEntity.getPosZ()) <= this.meleeRange * this.meleeRange)
		{
			if(this.getAnimationID() == LibraryOkamiPokemonAttackID.NO_ACTION)
			{
				if(this.rand.nextInt(2) == 0)
				{
					NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)LibraryOkamiPokemonAttackID.GLAIVE_SLASH, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
					this.setAnimationID(LibraryOkamiPokemonAttackID.GLAIVE_SLASH);
				}
				else
				{
					NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
					this.setAnimationID(LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE);
				}
			}
		}
	}

	public void triggerTaunt()
	{
		if(this.getAttackTarget() != null && this.getAttackTarget() instanceof MobEntity)
		{
			if(!(this.getAttackTarget() instanceof CreeperEntity) && this.getAttackTarget().isNonBoss())
			{
				((MobEntity) this.getAttackTarget()).setAttackTarget(this);
			}
		}
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks()
	{
		bodyIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setTailsClockDefaults();
	}

	@OnlyIn(Dist.CLIENT)
	public void setBodyClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		bodyIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		bodyIdleAnimationClock.setPhaseDurationX(0, 60);

		int startingClockX = randomInt;

		while(startingClockX > bodyIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= bodyIdleAnimationClock.getTotalDurationLengthX();
		}

		bodyIdleAnimationClock.setClockX(startingClockX);
	}

	@OnlyIn(Dist.CLIENT)
	public void setTailsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 40);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 70);

			float pointClusterDensityX = 0.75F;
			float pointClusterDensityY = 0.85F;

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