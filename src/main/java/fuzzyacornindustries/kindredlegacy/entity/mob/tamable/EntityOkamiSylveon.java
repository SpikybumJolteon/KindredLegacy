package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralRangedAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIOkamiGlaiveSlash;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIOkamiGlaiveSlashReverse;
import fuzzyacornindustries.kindredlegacy.item.BerryItem;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.tamable.IBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IPowerUp;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemEssenceRecaller;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiSylveonSummon;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryOkamiPokemonAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityOkamiSylveon extends OkamiPokemon implements IRangedAttackMob
{
	private IdleAnimationClock ribbonsHeadRtIdleAnimationClock[] = new IdleAnimationClock[19];
	private IdleAnimationClock ribbonsIdleAnimationClock[] = new IdleAnimationClock[19];
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[7];

	static String mobName = "okami_sylveon";

	public float meleeRange = 3.0F;

	public static final float defaultBaseAttackPower = 3F;
	public static final float defaultBaseMaxHealth = 30F;
	public static final float defaultBaseSpeed = 0.33F;

	public static final int defaultRegenLevel = 2;

	public static final float defaultMaximumAttackBoost = 10F;
	public static final float defaultmaximumHealthBoost = 80F;
	public static final float defaultmaximumSpeedBoost = 0.40F;

	public EntityOkamiSylveon(World par1World)
	{
		super(par1World);

		this.aiSit = new EntityAISit(this);

		this.setSize(0.5F, 0.95F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new AIOkamiGlaiveSlash(this, meleeRange));
		this.tasks.addTask(2, new AIOkamiGlaiveSlashReverse(this, meleeRange));

		this.tasks.addTask(3, this.aiSit);
		this.tasks.addTask(4, new AIGeneralRangedAttack(this, 1.0D, 1, meleeRange));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIWander(this, 0.75D));
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
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.33D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 3;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.set(MAX_HEALTH, this.defaultBaseMaxHealth);
		this.dataManager.set(ATTACK_POWER, this.defaultBaseAttackPower);
		this.dataManager.set(SPEED, this.defaultBaseSpeed);

		this.dataManager.set(REGEN_LEVEL, (byte)this.defaultRegenLevel);

		this.setBlockSuffocationAvoidanceEssence(1);
		this.setFallImmunityEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return KindredLegacySoundEvents.OKAMI_SYLVEON_WHINE;
		}
		else if(this.dataManager.get(SOUND).byteValue() == 1)
		{
			return null;
		}
		else
		{
			return KindredLegacySoundEvents.OKAMI_SYLVEON_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.OKAMI_SYLVEON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.OKAMI_SYLVEON_DEATH;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("OkamiSylveonTextureType", (byte)this.getMainTextureType());
		par1NBTTagCompound.setByte("OkamiSylveonSoundType", (byte)this.getSoundType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("OkamiSylveonTextureType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("OkamiSylveonTextureType");
			this.setMainTextureType(b0);
		}

		if (par1NBTTagCompound.hasKey("OkamiSylveonSoundType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("OkamiSylveonSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void returnToItem()
	{
		EntityPlayer owner = (EntityPlayer)this.getOwner();

		ItemStack poketamableStack = new ItemOkamiSylveonSummon().fromPoketamableEntity(this);

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

			ItemStack poketamableStack = new ItemOkamiSylveonSummon().fromPoketamableEntity(this);

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

		if(animationID != LibraryOkamiPokemonAttackID.NO_ACTION) animationTick++;

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
				if (itemstack.getItem() instanceof BerryItem)
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
					else if(KindredLegacyMain.isGalacticraftEnabled)
					{
						if(itemstack.getItem() == KindredLegacyItems.COMET_ESSENCE && this.hasSpaceSurvivabilityEssence() != 1)
						{		
							applyCometEssence(player, itemstack);

							return true;
						}
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
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		if(animationID == LibraryOkamiPokemonAttackID.NO_ACTION)
		{
			if(this.rand.nextInt(2) == 0)
			{
				KindredLegacyMain.sendAnimationPacket(this, LibraryOkamiPokemonAttackID.GLAIVE_SLASH);
			}
			else
			{
				KindredLegacyMain.sendAnimationPacket(this, LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE);
			}
		}
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockRibbonsHeadRt(int partNumber)
	{
		return ribbonsHeadRtIdleAnimationClock[partNumber];
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockRibbons(int partNumber)
	{
		return ribbonsIdleAnimationClock[partNumber];
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

		for(int i = 0; i < ribbonsIdleAnimationClock.length; i++)
		{
			ribbonsIdleAnimationClock[i].incrementClocks();
		}

		for(int i = 0; i < ribbonsHeadRtIdleAnimationClock.length; i++)
		{
			ribbonsHeadRtIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setRibbonsHeadRtClockDefaults();
		setRibbonsClockDefaults();
		setTailsClockDefaults();
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
	public void setRibbonsHeadRtClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < ribbonsHeadRtIdleAnimationClock.length; i++)
		{
			ribbonsHeadRtIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			ribbonsHeadRtIdleAnimationClock[i].setPhaseDurationX(0, 75);
			ribbonsHeadRtIdleAnimationClock[i].setPhaseDurationY(0, 85);

			float pointClusterDensityX = 0.5F;
			float pointClusterDensityY = 0.5F;

			int startingClockX = (int)(((float)(ribbonsHeadRtIdleAnimationClock.length - i) / (float)ribbonsHeadRtIdleAnimationClock.length) * (float)ribbonsHeadRtIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockY = (int)(((float)(ribbonsHeadRtIdleAnimationClock.length - i) / (float)ribbonsHeadRtIdleAnimationClock.length) * (float)ribbonsHeadRtIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;

			while(startingClockX > ribbonsHeadRtIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= ribbonsHeadRtIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > ribbonsHeadRtIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= ribbonsHeadRtIdleAnimationClock[i].getTotalDurationLengthY();
			}

			ribbonsHeadRtIdleAnimationClock[i].setClockX(startingClockX);
			ribbonsHeadRtIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void setRibbonsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < ribbonsIdleAnimationClock.length; i++)
		{
			ribbonsIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			ribbonsIdleAnimationClock[i].setPhaseDurationX(0, 75);
			ribbonsIdleAnimationClock[i].setPhaseDurationY(0, 85);

			float pointClusterDensityX = 0.95F;
			float pointClusterDensityY = 0.95F;

			int startingClockX = (int)(((float)(ribbonsIdleAnimationClock.length - i) / (float)ribbonsIdleAnimationClock.length) * (float)ribbonsIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockY = (int)(((float)(ribbonsIdleAnimationClock.length - i) / (float)ribbonsIdleAnimationClock.length) * (float)ribbonsIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;

			while(startingClockX > ribbonsIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= ribbonsIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > ribbonsIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= ribbonsIdleAnimationClock[i].getTotalDurationLengthY();
			}

			ribbonsIdleAnimationClock[i].setClockX(startingClockX);
			ribbonsIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void setTailsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 45);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float pointClusterDensityX = 0.55F;
			float pointClusterDensityY = 0.45F;

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