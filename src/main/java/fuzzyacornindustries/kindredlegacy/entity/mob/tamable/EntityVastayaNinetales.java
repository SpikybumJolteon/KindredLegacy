package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.ModMathFunctions;
import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.block.BlockGuardianField;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.IAnimateAhriNinetales;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralRangedAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIVastayaNinetalesFireball;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIVastayaNinetalesJumpFireball;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityVastayaFireball;
import fuzzyacornindustries.kindredlegacy.item.BerryItem;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.tamable.IBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IPowerUp;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemVastayaNinetalesSummon;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityVastayaNinetales extends TamablePokemon implements IRangedAttackMob, IAnimateAhriNinetales
{
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock armRtIdleAnimationClock;
	private IdleAnimationClock tailsIdleAnimationClock[] = new IdleAnimationClock[8];
	private IdleAnimationClock orbIdleAnimationClock;

	static String mobName = "vastaya_ninetales";

	public static final float defaultBaseAttackPower = 4F;
	public static final float defaultBaseMaxHealth = 30F;
	public static final float defaultBaseSpeed = 0.35F;

	public static final int defaultRegenLevel = 1;

	public static final float defaultMaximumAttackBoost = 10F;
	public static final float defaultmaximumHealthBoost = 80F;
	public static final float defaultmaximumSpeedBoost = 0.40F;

	public static final int actionIDNone = 0;

	public float attackRange = 14.0F;

	public EntityVastayaNinetales(World par1World)
	{
		super(par1World);

		this.aiSit = new EntityAISit(this);

		this.setSize(0.5F, 1.9F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new AIVastayaNinetalesJumpFireball(this));
		this.tasks.addTask(2, new AIVastayaNinetalesFireball(this));

		this.tasks.addTask(3, this.aiSit);
		this.tasks.addTask(4, new AIGeneralRangedAttack(this, 1.0D, 20, attackRange));
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
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 2;
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
		this.setBlockSuffocationAvoidanceEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return KindredLegacySoundEvents.VASTAYA_NINETALES_WHINE;
		}
		else if(this.getSoundType() == 1)
		{
			return null;
		}
		else
		{
			return KindredLegacySoundEvents.VASTAYA_NINETALES_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.VASTAYA_NINETALES_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.VASTAYA_NINETALES_DEATH;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("VastayaNinetalesTextureType", (byte)this.getMainTextureType());
		par1NBTTagCompound.setByte("VastayaNinetalesSoundType", (byte)this.getSoundType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("VastayaNinetalesTextureType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("VastayaNinetalesTextureType");
			this.setMainTextureType(b0);
		}

		if (par1NBTTagCompound.hasKey("VastayaNinetalesSoundType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("VastayaNinetalesSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void returnToItem()
	{
		EntityPlayer owner = (EntityPlayer)this.getOwner();

		ItemStack poketamableStack = new ItemVastayaNinetalesSummon().fromPoketamableEntity(this);

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

			ItemStack poketamableStack = new ItemVastayaNinetalesSummon().fromPoketamableEntity(this);

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

		if(animationID != LibraryUniversalAttackID.NO_ACTION) animationTick++;

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
		if(animationID == LibraryUniversalAttackID.NO_ACTION)
		{
			BlockPos blockAtCurrentPosition = new BlockPos(this);
			IBlockState iblockstate = this.world.getBlockState(blockAtCurrentPosition);
			Block block = iblockstate.getBlock();

			if(!(block instanceof BlockGuardianField))
			{
				int attackWeight = this.rand.nextInt(5);

				if(attackWeight < 2)
				{
					KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.JUMP_FIREBALL);
				}
				else
				{
					KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.FIREBALL);
				}
			}
		}
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	public void attackWithFireball(EntityVastayaNinetales attackingMob, EntityLivingBase targetEntity, float par2)
	{
		Vector3f spawnFireballPoint = new Vector3f(ModMathFunctions.findShooterOffsetPoint(attackingMob.posX, attackingMob.posZ, 
				targetEntity.posX, targetEntity.posZ, 1F));

		double d0 = targetEntity.posX - spawnFireballPoint.getX();//this.posX;
		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - (attackingMob.posY + (double)(attackingMob.height / 2.0F));
		double d2 = targetEntity.posZ - spawnFireballPoint.getZ();//this.posZ;

		//float f1 = MathHelper.sqrt(par2) * 0.1F;
		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

		EntityVastayaFireball entitysmallfireball = new EntityVastayaFireball(attackingMob.world, attackingMob, 
				spawnFireballPoint.getX(), attackingMob.posY, spawnFireballPoint.getZ(),
				d0 + targetEntity.motionX * 0.5F, d1 + targetEntity.motionY * 0.5F, d2 + targetEntity.motionZ * 0.5F, 
				1.5F, this.getAttackPower());
		entitysmallfireball.posY = attackingMob.posY + (double)(attackingMob.height / 2.0F) + 0.25F;

		this.world.spawnEntity(entitysmallfireball);
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@Override
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockBody()
	{
		return bodyIdleAnimationClock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockArmRt()
	{
		return armRtIdleAnimationClock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTails(int partNumber)
	{
		return tailsIdleAnimationClock[partNumber];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockOrb()
	{
		return orbIdleAnimationClock;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		bodyIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailsIdleAnimationClock.length; i++)
		{
			tailsIdleAnimationClock[i].incrementClocks();
		}

		armRtIdleAnimationClock.incrementClocks();
		orbIdleAnimationClock.incrementClocks();
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setBodyClockDefaults();
		setArmRtClockDefaults();
		setTailsClockDefaults();
		setOrbClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setBodyClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		bodyIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		bodyIdleAnimationClock.setPhaseDurationX(0, 50);

		int startingClockX = randomInt;

		while(startingClockX > bodyIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= bodyIdleAnimationClock.getTotalDurationLengthX();
		}

		bodyIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setArmRtClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		armRtIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		armRtIdleAnimationClock.setPhaseDurationX(0, 60);

		int startingClockX = randomInt;

		while(startingClockX > armRtIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= armRtIdleAnimationClock.getTotalDurationLengthX();
		}

		armRtIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setTailsClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailsIdleAnimationClock.length; i++)
		{
			tailsIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailsIdleAnimationClock[i].setPhaseDurationX(0, 45);
			tailsIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float pointClusterDensityX = 0.95F;
			float pointClusterDensityY = 0.85F;

			int startingClockX = (int)(((float)(tailsIdleAnimationClock.length - i) / (float)tailsIdleAnimationClock.length) * (float)tailsIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
			int startingClockY = (int)(((float)(tailsIdleAnimationClock.length - i) / (float)tailsIdleAnimationClock.length) * (float)tailsIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;

			while(startingClockX > tailsIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= tailsIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > tailsIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= tailsIdleAnimationClock[i].getTotalDurationLengthY();
			}

			tailsIdleAnimationClock[i].setClockX(startingClockX);
			tailsIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	@SideOnly(Side.CLIENT)
	public void setOrbClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		orbIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		orbIdleAnimationClock.setPhaseDurationX(0, 40);

		int startingClockX = randomInt;

		while(startingClockX > orbIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= orbIdleAnimationClock.getTotalDurationLengthX();
		}

		orbIdleAnimationClock.setClockX(startingClockX);
	}
}