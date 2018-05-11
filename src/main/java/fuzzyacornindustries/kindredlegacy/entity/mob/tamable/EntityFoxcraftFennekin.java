package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.item.BerryItem;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.tamable.IBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IPowerUp;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFoxcraftFennekinSummon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFoxcraftFennekin extends TamablePokemon
{
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[5];

	static String mobName = "foxcraft_fennekin";

	public static final float defaultBaseAttackPower = 2F;
	public static final float defaultBaseMaxHealth = 20F;
	public static final float defaultBaseSpeed = 0.40F;

	public static final int defaultRegenLevel = 1;

	public static final float defaultMaximumAttackBoost = 8F;
	public static final float defaultmaximumHealthBoost = 60F;
	public static final float defaultmaximumSpeedBoost = 0.43F;

	private int flashCooldown;
	private int flashCooldownDuration;

	public EntityFoxcraftFennekin(World par1World)
	{
		super(par1World);

		this.aiSit = new EntityAISit(this);

		this.setSize(0.3F, 0.9F);
		this.tasks.addTask(1, new EntityAISwimming(this));

		this.tasks.addTask(3, this.aiSit);
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIWander(this, 0.55D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, TamablePokemon.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		//this.tasks.addTask(8, new AIGeneralBerryBeg(this, 8.0F));

		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		flashCooldown = 0;
		flashCooldownDuration = 20 * 3;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.set(MAX_HEALTH, this.defaultBaseMaxHealth);
		this.dataManager.set(ATTACK_POWER, this.defaultBaseAttackPower);
		this.dataManager.set(SPEED, this.defaultBaseSpeed);

		this.dataManager.set(REGEN_LEVEL, (byte)this.defaultRegenLevel);

		this.setFireImmunityEssence(1);
		this.setFallImmunityEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return KindredLegacySoundEvents.FOXCRAFT_FENNEKIN_WHINE;
		}
		else if(this.getSoundType() == 1)
		{
			return null;
		}
		else
		{
			return KindredLegacySoundEvents.FOXCRAFT_FENNEKIN_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.FOXCRAFT_FENNEKIN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.FOXCRAFT_FENNEKIN_DEATH;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("FoxcraftFennekinTextureType", (byte)this.getMainTextureType());
		par1NBTTagCompound.setByte("FoxcraftFennekinSoundType", (byte)this.getSoundType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("FoxcraftFennekinTextureType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("FoxcraftFennekinTextureType");
			this.setMainTextureType(b0);
		}

		if (par1NBTTagCompound.hasKey("FoxcraftFennekinSoundType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("FoxcraftFennekinSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void returnToItem()
	{
		EntityPlayer owner = (EntityPlayer)this.getOwner();

		ItemStack poketamableStack = new ItemFoxcraftFennekinSummon().fromPoketamableEntity(this);

		if (poketamableStack != null)
		{
			if (owner.getHealth() > 0 && owner.inventory.addItemStackToInventory(poketamableStack))
			{
				//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
			}
			else
			{
				//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
				world.spawnEntity(new EntityItem(world, owner.posX, owner.posY, owner.posZ, poketamableStack));
			}
		}

		this.setDead();
	}

	@Override
	public void setDead()
	{
		if (this.getOwner() != null && this.getOwner() instanceof EntityPlayer && !world.isRemote && this.getHealth() <= 0F)
		{
			EntityPlayer owner = (EntityPlayer)this.getOwner();

			ItemStack poketamableStack = new ItemFoxcraftFennekinSummon().fromPoketamableEntity(this);

			if (poketamableStack != null)
			{
				if (owner.getHealth() > 0 && owner.inventory.addItemStackToInventory(poketamableStack))
				{
					//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
				}
				else
				{
					//world.playSoundAtEntity(owner, "mob.slime.big", 1F, 1F);
					world.spawnEntity(new EntityItem(world, owner.posX, owner.posY, owner.posZ, poketamableStack));
				}
			}
		}

		super.setDead();
	}

	@Override
	public void onLivingUpdate()
	{
		if(this.world.isRemote)
		{
			applyCurrentTexture();
		}

		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		//if(animationID != LibraryOkamiPokemonAttackID.NO_ACTION) animationTick++;

		if(flashCooldown > 0)
		{
			flashCooldown--;
			/*
			if(flashCooldown > flashCooldownDuration - 10)
			{
				this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, preFlashLocation.getX() + this.world.rand.nextFloat() * 2F,
						preFlashLocation.getY() + this.world.rand.nextFloat() * 2F,
						preFlashLocation.getZ() + this.world.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);
			}

			if(flashCooldown > flashCooldownDuration - 15 && flashCooldown < flashCooldownDuration - 5)
			{
				this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + this.world.rand.nextFloat() * 2F, 
						this.posY + this.world.rand.nextFloat() * 2F, 
						this.posZ + this.world.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);
			}*/
		}

		toggleHappiness();
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (this.isTamed())
		{
			if(player.isSneaking() && hand == EnumHand.MAIN_HAND && !this.world.isRemote)
			{
				this.toggleIdleSounds();
			}

			if (itemstack != null)
			{
				if(itemstack.getItem() == KindredLegacyItems.ESSENCE_RECALLER)
				{	
					this.returnToItem();

					return true;
				}
				else if (itemstack.getItem() instanceof BerryItem)
				{
					BerryItem berry = (BerryItem)itemstack.getItem();

					boolean decreaseBerryItemstack = false;

					if(this.getHealth() < ((float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()))
					{	
						decreaseBerryItemstack = berryHeal(player, ((float)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue()), berry);

						if(decreaseBerryItemstack == true)
						{
							decreasePlayerItemStack(player, itemstack);
						}

						return true;
					}
				}
				else if(itemstack.getItem() instanceof IBoostItem)
				{
					if(itemstack.getItem() == KindredLegacyItems.LIFE_BOOST && this.getMaximumHealth() < this.defaultmaximumHealthBoost)
					{
						applyLifeBoost(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.ATTACK_BOOST && this.getAttackPower() < this.defaultMaximumAttackBoost)
					{
						applyAttackBoost(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.SPEED_BOOST && this.getSpeed() < this.defaultmaximumSpeedBoost)
					{
						applySpeedBoost(player, itemstack);

						return true;
					}
				}
				else if(itemstack.getItem() instanceof IEssenceItem)
				{
					if(itemstack.getItem() == KindredLegacyItems.FIRAGA_ESSENCE && this.hasFireImmunityEssence() != 1)
					{
						applyFiragaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.WATERGA_ESSENCE && this.hasDrowningImmunityEssence() != 1)
					{
						applyWatergaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.GRAVIGA_ESSENCE && this.hasFallImmunityEssence() != 1)
					{
						applyGravigaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.QUAKAGA_ESSENCE && this.hasBlockSuffocationAvoidanceEssence() != 1)
					{
						applyQuakagaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.BIOGA_ESSENCE && this.hasToxinImmunityEssence() != 1)
					{
						applyBiogaEssence(player, itemstack);

						return true;
					}
					else if(itemstack.getItem() == KindredLegacyItems.CURAGA_ESSENCE && this.getRegenLevel() < 3)
					{
						applyCuragaEssence(player, itemstack);

						return true;
					}
				}
				else if(itemstack.getItem() instanceof IPowerUp)
				{
					if(itemstack.getItem() == KindredLegacyItems.RAW_SPICE_MELANGE)
					{
						this.setBoostPowerType(this.SPICE_MELANGE_POWER_ID);

						decreasePlayerItemStack(player, itemstack);
						
						this.boostPowerTimer = 5000;
						
						return true;
					}
				}
			}

			if (this.isOwner(player) && !this.world.isRemote && !player.isSneaking() && itemstack.getItem() != KindredLegacyItems.ATTACK_ORDERER)
			{
				applySit();
			}
		}

		return super.processInteract(player, hand);
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		if (targetEntity instanceof EntityLivingBase)
		{
			byte durationSeconds = 15;

			((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, durationSeconds * 20, 0));
			((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, durationSeconds * 20, 0));
			((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, durationSeconds * 20, 0));

			if(this.isTamed())
			{
				if(((EntityLivingBase)targetEntity).getHealth() <= this.getAttackDamage())
				{
					targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this.getOwner()), this.getAttackDamage());
				}
				else
				{
					targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackDamage());
				}
			}
			else
			{
				targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), this.getAttackDamage());
			}
		}

		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
	{
		if (this.isEntityInvulnerable(damageSource))
		{
			return false;
		}
		else
		{
			if (damageSource instanceof EntityDamageSource)
			{
				if(flashCooldown == 0)
				{
					for (int i = 0; i < 64; ++i)
					{
						if (this.teleportRandomly(32D))
						{
							if(damageSource.getTrueSource() instanceof EntityLiving)
							{
								((EntityLiving)damageSource.getTrueSource()).setAttackTarget(null);
							}

							flashCooldown = flashCooldownDuration;
							return true;
						}
					}
				}
				else if(damageSource.isExplosion())
				{
					for (int i = 0; i < 64; ++i)
					{
						if (this.teleportRandomly(32D))
						{
							flashCooldown = flashCooldownDuration;
							return true;
						}
					}
				}
			}

			return super.attackEntityFrom(damageSource, damageAmount);
		}
	}

	/**
	 * Teleport entity to a random nearby position
	 */
	protected boolean teleportRandomly(double maxDistance)
	{
		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * maxDistance;
		double d1 = this.posY + (double)(this.rand.nextInt(10) - 5);
		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * maxDistance;

		net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, d0, d1, d2, 0);
		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
		boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());

		if (flag)
		{
			this.world.playSound((EntityPlayer)null, this.prevPosX, this.prevPosY, this.prevPosZ, KindredLegacySoundEvents.FLASH, this.getSoundCategory(), 1.0F, 1.0F);
			this.playSound(KindredLegacySoundEvents.FLASH, 1.0F, 1.0F);
		}

		return flag;
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		bodyIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
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

	@SideOnly(Side.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 60);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 100);

			float pointClusterDensityX = 0.65F;
			float pointClusterDensityY = 0.75F;

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