package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.KindredLegacy;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.FeywoodAbsolMegahornGoal;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.GeneralRangedAttackGoal;
import fuzzyacornindustries.kindredlegacy.item.tamable.FeywoodAbsolSummonItem;
import fuzzyacornindustries.kindredlegacy.network.ActionAnimationMessage;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryFeywoodAbsolAttackID;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryOkamiPokemonAttackID;
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

	public final float meleeRange = 2.15F;

	public FeywoodAbsolEntity(EntityType<? extends FeywoodAbsolEntity> type, World world)
	{
		super(type, world);

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
			return KindredLegacySoundEvents.FEYWOOD_ABSOL_WHINE;
		}
		else if(this.getSoundType() == 1)
		{
			return null;
		}
		else
		{
			return KindredLegacySoundEvents.FEYWOOD_ABSOL_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.FEYWOOD_ABSOL_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.FEYWOOD_ABSOL_DEATH;
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

		ItemStack poketamableStack = FeywoodAbsolSummonItem.fromPoketamableEntity(this);

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
		ItemStack poketamableStack = FeywoodAbsolSummonItem.fromPoketamableEntity(this);

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

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	//	@Override
	//	public boolean processInteract(EntityPlayer player, EnumHand hand)
	//	{
	//		ItemStack itemstack = player.inventory.getCurrentItem();
	//
	//		if (this.isTamed())
	//		{
	//			if(player.isSneaking() && hand == EnumHand.MAIN_HAND && !this.world.isRemote)
	//			{
	//				this.toggleIdleSounds();
	//			}
	//
	//			if (itemstack != null)
	//			{
	//				if (itemstack.getItem() instanceof BerryItem)
	//				{
	//					BerryItem berry = (BerryItem)itemstack.getItem();
	//
	//					boolean decreaseBerryItemstack = false;
	//
	//					if(this.getHealth() < ((float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()))
	//					{	
	//						decreaseBerryItemstack = berryHeal(player, ((float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()), berry);
	//
	//						if(decreaseBerryItemstack == true)
	//						{
	//							decreasePlayerItemStack(player, itemstack);
	//						}
	//
	//						return true;
	//					}
	//				}
	//				else if(itemstack.getItem() instanceof IBoostItem)
	//				{
	//					if(itemstack.getItem() == KindredLegacyItems.LIFE_BOOST && this.getMaximumHealth() < this.defaultmaximumHealthBoost)
	//					{
	//						applyLifeBoost(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.ATTACK_BOOST && this.getAttackPower() < this.defaultMaximumAttackBoost)
	//					{
	//						applyAttackBoost(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.SPEED_BOOST && this.getSpeed() < this.defaultmaximumSpeedBoost)
	//					{
	//						applySpeedBoost(player, itemstack);
	//
	//						return true;
	//					}
	//				}
	//				else if(itemstack.getItem() instanceof IEssenceItem)
	//				{
	//					if(itemstack.getItem() == KindredLegacyItems.FIRAGA_ESSENCE && this.hasFireImmunityEssence() != 1)
	//					{
	//						applyFiragaEssence(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.WATERGA_ESSENCE && this.hasDrowningImmunityEssence() != 1)
	//					{
	//						applyWatergaEssence(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.GRAVIGA_ESSENCE && this.hasFallImmunityEssence() != 1)
	//					{
	//						applyGravigaEssence(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.QUAKAGA_ESSENCE && this.hasBlockSuffocationAvoidanceEssence() != 1)
	//					{
	//						applyQuakagaEssence(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.BIOGA_ESSENCE && this.hasToxinImmunityEssence() != 1)
	//					{
	//						applyBiogaEssence(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(itemstack.getItem() == KindredLegacyItems.CURAGA_ESSENCE && this.getRegenLevel() < 3)
	//					{
	//						applyCuragaEssence(player, itemstack);
	//
	//						return true;
	//					}
	//					else if(KindredLegacyMain.isGalacticraftEnabled)
	//					{
	//						if(itemstack.getItem() == KindredLegacyItems.COMET_ESSENCE && this.hasSpaceSurvivabilityEssence() != 1)
	//						{		
	//							applyCometEssence(player, itemstack);
	//
	//							return true;
	//						}
	//					}
	//				}
	//				else if(itemstack.getItem() instanceof IPowerUp)
	//				{
	//					if(itemstack.getItem() == KindredLegacyItems.RAW_SPICE_MELANGE)
	//					{
	//						this.setBoostPowerType(this.SPICE_MELANGE_POWER_ID);
	//
	//						decreasePlayerItemStack(player, itemstack);
	//
	//						this.boostPowerTimer = 5000;
	//
	//						return true;
	//					}
	//				}
	//				else if(itemstack.getItem() == KindredLegacyItems.REGEN_CREAM)
	//				{
	//					activateHealthRegen(player, itemstack);
	//					
	//					return true;
	//				}
	//			}
	//
	//			if (this.isOwner(player) && !this.world.isRemote && !player.isSneaking() && itemstack.getItem() != KindredLegacyItems.ATTACK_ORDERER)
	//			{
	//				applySit();
	//			}
	//		}
	//
	//		return super.processInteract(player, hand);
	//	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
	{
		if(this.getDistanceSq(targetEntity.getPosX(), targetEntity.getPosYHeight(0.5), targetEntity.getPosZ()) <= this.meleeRange * this.meleeRange)
		{
			if(this.getAnimationID() == LibraryOkamiPokemonAttackID.NO_ACTION)
			{
				//KindredLegacyMain.sendAnimationPacket(this, this, LibraryOkamiPokemonAttackID.GLAIVE_SLASH);
				KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryFeywoodAbsolAttackID.MEGAHORN, this.getEntityId()));
				this.setAnimationID(LibraryFeywoodAbsolAttackID.MEGAHORN);
			}

			//System.out.println( "Entity ID: " + Integer.toString( this.getEntityId() ) );
		}
	}

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