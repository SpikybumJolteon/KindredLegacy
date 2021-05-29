package fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.core.ModSounds;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.FeywoodAbsolMegahornGoal;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.ai.GeneralRangedAttackGoal;
import fuzzyacornindustries.kindredlegacy.common.item.PoketamableSummonItem;
import fuzzyacornindustries.kindredlegacy.common.network.ActionAnimationMessage;
import fuzzyacornindustries.kindredlegacy.common.network.NetworkHandler;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryFeywoodAbsolAttackID;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.PacketDistributor;

public class FeywoodAbsolEntity extends TamablePokemon implements IRangedAttackMob
{
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock;

	static String mobName = "feywood_absol";

	private ItemStack summonItem = new ItemStack(ModItems.FEYWOOD_ABSOL_SUMMON.get());

	public final float meleeRange = 2.15F;

	public FeywoodAbsolEntity(EntityType<? extends FeywoodAbsolEntity> type, World world)
	{
		super(type, world);

		if(this.world.isRemote)
		{
			setClockDefaults();
		}
		
		default_name = "Feywood Absol";
		default_summon_item_name = "Feywood Absol Summon";
	}

	public FeywoodAbsolEntity(World world) 
	{
		this(ModEntities.FEYWOOD_ABSOL.get(), world);
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	public void setDefaultStats()
	{
		setDefaultBaseAttackPower(10F);
		setDefaultBaseMaxHealth(30F);
		setDefaultBaseSpeed(0.40F);

		setDefaultRegenLevel(2);

		setDefaultMaximumAttackBoost(20F);
		setDefaultMaximumHealthBoost(80F);
		setDefaultMaximumSpeedBoost(0.43F);

		setDefaultArmor(0);
	}

	protected void registerGoals() 
	{
		this.sitGoal = new SitGoal(this);
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new FeywoodAbsolMegahornGoal(this, meleeRange));

		this.goalSelector.addGoal(3, this.sitGoal);
		this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 1, meleeRange));
		//this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 1, meleeRange));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.55D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookAtGoal(this, TamablePokemon.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		//this.tasks.addTask(8, new AIGeneralBerryBeg(this, 8.0F));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
		//this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, ZerglingNincadaEntity.class, false));
		//this.targetSelector.addGoal(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));
	}

	@Override
	protected void registerData() 
	{
		super.registerData();

		this.dataManager.set(MAX_HEALTH, getDefaultBaseMaxHealth());
		this.dataManager.set(ATTACK_POWER, getDefaultBaseAttackPower());
		this.dataManager.set(SPEED, getDefaultBaseSpeed());

		this.dataManager.set(REGEN_LEVEL, (byte)getDefaultRegenLevel());

		this.setToxinImmunityEssence(1);
		this.setFallImmunityEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return ModSounds.FEYWOOD_ABSOL_WHINE.get();
		}
		else if(this.getSoundType() == 1)
		{
			return null;
		}
		else
		{
			return ModSounds.FEYWOOD_ABSOL_AMBIENT.get();
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSounds.FEYWOOD_ABSOL_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSounds.FEYWOOD_ABSOL_DEATH.get();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);

		nbt.putByte("FeywoodAbsolTextureType", (byte)this.getMainTextureType());
		nbt.putByte("FeywoodAbsolSoundType", (byte)this.getSoundType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void read(CompoundNBT nbt)
	{
		super.read(nbt);

		if (nbt.contains("FeywoodAbsolTextureType", 99))
		{
			byte b0 = nbt.getByte("FeywoodAbsolTextureType");
			this.setMainTextureType(b0);
		}

		if (nbt.contains("FeywoodAbsolSoundType", 99))
		{
			byte b0 = nbt.getByte("FeywoodAbsolSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void returnToItem()
	{
		PlayerEntity owner = (PlayerEntity)this.getOwner();

		ItemStack poketamableStack = PoketamableSummonItem.fromPoketamableEntity(this, summonItem);

		if (poketamableStack != null)
		{
			if (owner.getHealth() > 0 && owner.inventory.addItemStackToInventory(poketamableStack))
			{
				//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
			}
			else
			{
				//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
				ItemEntity itementity = owner.entityDropItem(poketamableStack);
				if (itementity != null) 
				{
					itementity.setNoDespawn();
				}
			}
		}

		this.remove();
	}

	@Override
	public void returnToItemOnDeath()
	{
		ItemStack poketamableStack = PoketamableSummonItem.fromPoketamableEntity(this, summonItem);

		if (this.getOwner() != null && this.getOwner() instanceof PlayerEntity && !world.isRemote && this.getHealth() <= 0F)
		{
			PlayerEntity owner = (PlayerEntity)this.getOwner();

			if (poketamableStack != null)
			{
				if (owner.getHealth() > 0 && owner.inventory.addItemStackToInventory(poketamableStack))
				{
					//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
				}
				else
				{
					//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
					//world.addEntity(new ItemEntity(world, owner.getPosX(), owner.getPosY(), owner.getPosZ(), poketamableStack));
					ItemEntity itementity = owner.entityDropItem(poketamableStack);
					if (itementity != null) 
					{
						itementity.setNoDespawn();
					}
				}
			}
		}
		else
		{
			ItemEntity itementity = this.entityDropItem(poketamableStack);
			if (itementity != null) 
			{
				itementity.setNoDespawn();
			}
			//world.addEntity(new ItemEntity(world, this.getPosX(), this.getPosY(), this.getPosZ(), poketamableStack));	
		}
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
	{
		if(this.getDistanceSq(targetEntity.getPosX(), targetEntity.getPosYHeight(0.5), targetEntity.getPosZ()) <= this.meleeRange * this.meleeRange)
		{
			if(this.getAnimationID() == LibraryFeywoodAbsolAttackID.NO_ACTION)
			{
				NetworkHandler.sendToTarget(new ActionAnimationMessage((byte)LibraryFeywoodAbsolAttackID.MEGAHORN, this.getEntityId()), PacketDistributor.TRACKING_ENTITY.with(() -> this));
				this.setAnimationID(LibraryFeywoodAbsolAttackID.MEGAHORN);
			}
		}
	}

//	@Override
//	public static String defaultNameCheck(String name)
//	{
//		if (name.equals("") || name.equals(DEFAULT_SUMMON_ITEM_NAME))
//		{
//			name = DEFAULT_NAME;
//		}
//		
//		return name;
//	}
//
//	@Override
//	public static boolean shouldDisplayName(String name)
//	{
//		return !name.equals(DEFAULT_NAME);
//	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail()
	{
		return tailIdleAnimationClock;
	}

	@OnlyIn(Dist.CLIENT)
	public void incrementPartClocks()
	{
		bodyIdleAnimationClock.incrementClocks();
		tailIdleAnimationClock.incrementClocks();
	}

	@OnlyIn(Dist.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setTailClockDefaults();
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
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		tailIdleAnimationClock = new IdleAnimationClock(1, 1, 0);

		tailIdleAnimationClock.setPhaseDurationX(0, 45);
		tailIdleAnimationClock.setPhaseDurationY(0, 80);

		int startingClockX = randomInt;
		int startingClockY = randomInt;

		while(startingClockX > tailIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= tailIdleAnimationClock.getTotalDurationLengthX();
		}

		while(startingClockY > tailIdleAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= tailIdleAnimationClock.getTotalDurationLengthY();
		}

		tailIdleAnimationClock.setClockX(startingClockX);
		tailIdleAnimationClock.setClockY(startingClockY);
	}
}