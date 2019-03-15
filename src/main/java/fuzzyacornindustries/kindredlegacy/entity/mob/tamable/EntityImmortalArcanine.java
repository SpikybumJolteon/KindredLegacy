package fuzzyacornindustries.kindredlegacy.entity.mob.tamable;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.block.BlockGuardianField;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralRangedAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIImmortalArcanineShoot;
import fuzzyacornindustries.kindredlegacy.item.BerryItem;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.tamable.IBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.IPowerUp;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemImmortalArcanineSummon;
import fuzzyacornindustries.kindredlegacy.reference.LibraryDyeColors;
import fuzzyacornindustries.kindredlegacy.reference.LibraryFireworks;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryImmortalArcanineAttackID;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IJumpingMount;
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
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityImmortalArcanine extends TamablePokemon implements IRangedAttackMob, IJumpingMount
{
	protected static final DataParameter<Float> ATTACK_MOTION_X = EntityDataManager.<Float>createKey(EntityImmortalArcanine.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> ATTACK_MOTION_Y = EntityDataManager.<Float>createKey(EntityImmortalArcanine.class, DataSerializers.FLOAT);
	protected static final DataParameter<Float> ATTACK_MOTION_Z = EntityDataManager.<Float>createKey(EntityImmortalArcanine.class, DataSerializers.FLOAT);

	protected static final DataParameter<String> TARGET_LOCATION_X = EntityDataManager.<String>createKey(EntityImmortalArcanine.class, DataSerializers.STRING);
	protected static final DataParameter<String> TARGET_LOCATION_Y = EntityDataManager.<String>createKey(EntityImmortalArcanine.class, DataSerializers.STRING);
	protected static final DataParameter<String> TARGET_LOCATION_Z = EntityDataManager.<String>createKey(EntityImmortalArcanine.class, DataSerializers.STRING);

	protected static final IAttribute JUMP_STRENGTH = (new RangedAttribute((IAttribute)null, "horse.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);

	public float attackRange = 15.0F;

	private final EntityAISwimming swimmingAI = new EntityAISwimming(this);
	private final AIImmortalArcanineShoot shootCannons = new AIImmortalArcanineShoot(this);
	private final AIGeneralRangedAttack rangedAttack = new AIGeneralRangedAttack(this, 1.0D, 20, attackRange);

	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[11];

	static String mobName = "immortal_arcanine";

	public static final float defaultBaseAttackPower = 4F;
	public static final float defaultBaseMaxHealth = 30F;
	public static final float defaultBaseSpeed = 0.35F;

	public static final int defaultRegenLevel = 1;

	public static final float defaultMaximumAttackBoost = 12F;
	public static final float defaultmaximumHealthBoost = 80F;
	public static final float defaultmaximumSpeedBoost = 0.41F;

	public static final int defaultArmor = 2;
	
	protected int gallopTime;
	protected float jumpPower;
	protected boolean horseJumping;
	public int sprintCounter;

	public EntityImmortalArcanine(World par1World)
	{
		super(par1World);

		this.stepHeight = 1.0F;

		this.aiSit = new EntityAISit(this);

		this.setSize(1.3964844F, 1.6F);
		this.tasks.addTask(1, swimmingAI);
		this.tasks.addTask(2, shootCannons);

		this.tasks.addTask(3, this.aiSit);
		this.tasks.addTask(4, rangedAttack);
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

		this.getAttributeMap().registerAttribute(JUMP_STRENGTH);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(0.9F);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.set(MAX_HEALTH, this.defaultBaseMaxHealth);
		this.dataManager.set(ATTACK_POWER, this.defaultBaseAttackPower);
		this.dataManager.set(SPEED, this.defaultBaseSpeed);

		this.dataManager.set(REGEN_LEVEL, (byte)this.defaultRegenLevel);

		this.dataManager.register(ATTACK_MOTION_X, 0F);
		this.dataManager.register(ATTACK_MOTION_Y, 0F);
		this.dataManager.register(ATTACK_MOTION_Z, 0F);

		this.dataManager.register(TARGET_LOCATION_X, Double.toString(0));
		this.dataManager.register(TARGET_LOCATION_Y, Double.toString(0));
		this.dataManager.register(TARGET_LOCATION_Z, Double.toString(0));

		this.setFireImmunityEssence(1);
		this.setFallImmunityEssence(1);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.isTamed() && this.getHealth() < (this.dataManager.get(MAX_HEALTH)).floatValue() / 4F)
		{
			return KindredLegacySoundEvents.IMMORTAL_ARCANINE_WHINE;
		}
		else if(this.getSoundType() == 1)
		{
			return null;
		}
		else
		{
			return KindredLegacySoundEvents.IMMORTAL_ARCANINE_AMBIENT;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.IMMORTAL_ARCANINE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.IMMORTAL_ARCANINE_DEATH;
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setByte("ImmortalArcanineTextureType", (byte)this.getMainTextureType());
		par1NBTTagCompound.setByte("ImmortalArcanineSoundType", (byte)this.getSoundType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("ImmortalArcanineTextureType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("ImmortalArcanineTextureType");
			this.setMainTextureType(b0);
		}

		if (par1NBTTagCompound.hasKey("ImmortalArcanineSoundType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("ImmortalArcanineSoundType");
			this.setSoundType(b0);
		}
	}

	@Override
	public void returnToItem()
	{
		EntityPlayer owner = (EntityPlayer)this.getOwner();

		ItemStack poketamableStack = new ItemImmortalArcanineSummon().fromPoketamableEntity(this);

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

			ItemStack poketamableStack = new ItemImmortalArcanineSummon().fromPoketamableEntity(this);

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

		if (this.sprintCounter > 0)
		{
			++this.sprintCounter;

			if (this.sprintCounter > 300)
			{
				this.sprintCounter = 0;
			}
		}

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
				applySit();
				//this.toggleIdleSounds();
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
				else if(itemstack.getItem() == KindredLegacyItems.REGEN_CREAM)
				{
					activateHealthRegen(player, itemstack);

					return true;
				}
			}

			if (this.isOwner(player) && !this.world.isRemote && !player.isSneaking() && itemstack.getItem() != KindredLegacyItems.ATTACK_ORDERER)
			{
				if (this.isBeingRidden())
				{
					return super.processInteract(player, hand);
				}

				this.aiSit.setSitting(false);
				this.mountTo(player);

				this.setAttackTarget(null);

				this.setAggro(false);

				removeAttackAITasks();

				return true;
			}
		}

		return super.processInteract(player, hand);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void handleStatusUpdate(byte bytePar)
	{
		if (bytePar == 17 && this.world.isRemote)
		{
			ItemStack fireworkItemStack = generateFirework(0);

			NBTTagCompound nbttagcompound5 = null;

			if (fireworkItemStack != null && fireworkItemStack.hasTagCompound())
			{
				nbttagcompound5 = fireworkItemStack.getTagCompound().getCompoundTag("Fireworks");
			}

			this.world.makeFireworks(Double.valueOf(this.dataManager.get(TARGET_LOCATION_X)), Double.valueOf(this.dataManager.get(TARGET_LOCATION_Y)), Double.valueOf(this.dataManager.get(TARGET_LOCATION_Z)), 
					this.dataManager.get(ATTACK_MOTION_X), this.dataManager.get(ATTACK_MOTION_Y), this.dataManager.get(ATTACK_MOTION_Z), nbttagcompound5);
			
			fireworkItemStack = generateFirework(1);

			if (fireworkItemStack != null && fireworkItemStack.hasTagCompound())
			{
				nbttagcompound5 = fireworkItemStack.getTagCompound().getCompoundTag("Fireworks");
			}

			this.world.makeFireworks(this.posX, this.posY + this.height / 2F, this.posZ, 
					this.dataManager.get(ATTACK_MOTION_X), this.dataManager.get(ATTACK_MOTION_Y), this.dataManager.get(ATTACK_MOTION_Z), nbttagcompound5);
			
			//this.world.makeFireworks(this.posX, this.posY + this.height /2F, this.posZ, this.attackMotionX, this.attackMotionY, this.attackMotionZ, nbttagcompound5);
		}

		super.handleStatusUpdate(bytePar);
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
				KindredLegacyMain.sendAnimationPacket(this, LibraryImmortalArcanineAttackID.SHOOT);
			}
		}
	}

	public void attackWithCannons(EntityImmortalArcanine attackingMob, EntityLivingBase targetEntity, float par2)
	{
		if(attackingMob.isTamed())
		{
			if(targetEntity.getHealth() <= (attackingMob.getAttackPower() + (float)targetEntity.getTotalArmorValue() * 0.5F))
			{
				targetEntity.attackEntityFrom(new EntityDamageSource("mob", attackingMob.getOwner()).setDamageBypassesArmor(), attackingMob.getAttackDamage() + (float)targetEntity.getTotalArmorValue() * 0.5F);
			}
			else
			{
				targetEntity.attackEntityFrom(new EntityDamageSource("mob", attackingMob).setDamageBypassesArmor(), attackingMob.getAttackDamage() + (float)targetEntity.getTotalArmorValue() * 0.5F);
			}
		}
		else
		{
			targetEntity.attackEntityFrom(new EntityDamageSource("mob", attackingMob).setDamageBypassesArmor(), attackingMob.getAttackDamage() + (float)targetEntity.getTotalArmorValue() * 0.5F);
		}

		this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, KindredLegacySoundEvents.IMMORTAL_ARCANINE_CANNONS_SHOOT, SoundCategory.HOSTILE, 1.2F, 1.0F / (this.world.rand.nextFloat() * 0.4F + 0.8F));

		//this.setPosition(attackingMob.posX, shooterEntity.posY + (double)shooterEntity.getEyeHeight() * 0.95D, shooterEntity.posZ);
		double shellPosX = attackingMob.posY + (double)attackingMob.getEyeHeight() * 0.95D;
		double d0 = targetEntity.posX - attackingMob.posX;
		double d1 = (targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 3.0F) - this.posY) * 1.1D;
		double d2 = targetEntity.posZ - attackingMob.posZ;
		double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));

			double d4 = d0 / d3;
			double d5 = d2 / d3;

			//this.setPosition(shooterEntity.posX, this.posY, shooterEntity.posZ);
			//this.setLocationAndAngles(shooterEntity.posX, this.posY, shooterEntity.posZ, f2, f3);
			float f4 = (float)d3 * 0.15F;

			//this.shoot(d0, d1 + (double)f4, d2, baseSpeed, accuracy);
			double attackMotionX = d0;
			double attackMotionY = d1 + f4;
			double attackMotionZ = d2;

			double vectorMagnitude = MathHelper.sqrt(attackMotionX * attackMotionX + attackMotionY * attackMotionY + attackMotionZ * attackMotionZ);

			attackMotionX /= vectorMagnitude;
			attackMotionY /= vectorMagnitude;
			attackMotionZ /= vectorMagnitude;

			double baseSpeed = 2.0F;

			attackMotionX *= baseSpeed;
			attackMotionY *= baseSpeed;
			attackMotionZ *= baseSpeed;

			this.dataManager.set(ATTACK_MOTION_X, (float)attackMotionX);
			this.dataManager.set(ATTACK_MOTION_Y, (float)attackMotionY);
			this.dataManager.set(ATTACK_MOTION_Z, (float)attackMotionZ);
			
			this.dataManager.set(TARGET_LOCATION_X, Double.toString(targetEntity.posX));
			this.dataManager.set(TARGET_LOCATION_Y, Double.toString(targetEntity.posY + targetEntity.height / 2F));
			this.dataManager.set(TARGET_LOCATION_Z, Double.toString(targetEntity.posZ));
			
			if (!this.world.isRemote)
			{
				//this.world.setEntityState(this, (byte)17);
			}
		}
	}

	public ItemStack generateFirework(int explosionID)
	{
		NBTTagCompound nbttagcompound;
		NBTTagCompound nbttagcompound1;

		ItemStack fireworkChargeItemStack = new ItemStack(Items.FIREWORK_CHARGE);

		nbttagcompound = new NBTTagCompound();

		nbttagcompound1 = new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();

		List<Integer> list1 = Lists.<Integer>newArrayList();

		list1 = getFireworkColorList(explosionID);

		byte explosionStyle = getExplosionStyle();

		int[] aint1 = new int[list1.size()];

		for (int l2 = 0; l2 < aint1.length; ++l2)
		{
			aint1[l2] = ((Integer)list1.get(l2)).intValue();
		}

		nbttagcompound1 = applySpecialEffect(nbttagcompound1);

		nbttagcompound1.setIntArray("Colors", aint1);
		nbttagcompound1.setByte("Type", explosionStyle);
		nbttagcompound.setTag("Explosion", nbttagcompound1);

		fireworkChargeItemStack.setTagCompound(nbttagcompound);

		NBTTagCompound nbttagcompound2 = new NBTTagCompound();
		NBTTagCompound nbttagcompound3 = new NBTTagCompound();

		ItemStack fireworkItemStack = new ItemStack(Items.FIREWORKS);

		NBTTagList nbttaglist2 = new NBTTagList();

		nbttaglist2.appendTag(fireworkChargeItemStack.getTagCompound().getCompoundTag("Explosion"));

		nbttagcompound3.setTag("Explosions", nbttaglist2);
		nbttagcompound3.setByte("Flight", (byte)1);
		nbttagcompound2.setTag("Fireworks", nbttagcompound3);

		fireworkItemStack.setTagCompound(nbttagcompound2);

		return fireworkItemStack;
	}

	public List<Integer> getFireworkColorList(int explosionID)
	{
		List<Integer> arraylist = Lists.<Integer>newArrayList();

		ItemStack tempItemStack;

		switch (explosionID)
		{
		case 0:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.LAPIS_LAZULI);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.PURPLE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.LIGHT_BLUE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 1:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.BONE_MEAL);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
		}

		return arraylist;
	}

	public byte getExplosionStyle()
	{
		return LibraryFireworks.SHAPE_BURST;
	}

	public NBTTagCompound applySpecialEffect(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setBoolean("Trail", true);
		return nbttagcompound;
	}

	protected void mountTo(EntityPlayer player)
	{
		player.rotationYaw = this.rotationYaw;
		player.rotationPitch = this.rotationPitch;

		if (!this.world.isRemote)
		{
			player.startRiding(this);
		}
	}

	@Override
	public void addAttackAITasks()
	{
		this.tasks.addTask(1, swimmingAI);
		this.tasks.addTask(2, shootCannons);
		this.tasks.addTask(4, rangedAttack);
	}

	@Override
	public void removeAttackAITasks()
	{
		this.tasks.removeTask(swimmingAI);
		this.tasks.removeTask(shootCannons);
		this.tasks.removeTask(rangedAttack);
	}

	protected boolean isMovementBlocked()
	{
		return super.isMovementBlocked() && this.isBeingRidden();
	}

	public boolean isHorseJumping()
	{
		return this.horseJumping;
	}

	public void setHorseJumping(boolean jumping)
	{
		this.horseJumping = jumping;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return !this.isBeingRidden();
	}

	@Override
	public void travel(float strafe, float vertical, float forward)
	{
		if (this.isBeingRidden() && this.canBeSteered())
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
			this.rotationYaw = entitylivingbase.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.renderYawOffset;
			strafe = entitylivingbase.moveStrafing * 0.5F;
			forward = entitylivingbase.moveForward;

			if (forward <= 0.0F)
			{
				forward *= 0.25F;
				this.gallopTime = 0;
			}

			if (this.jumpPower > 0.0F && !this.isHorseJumping() && this.onGround)
			{
				this.motionY = this.getHorseJumpStrength() * (double)this.jumpPower;

				if (this.isPotionActive(MobEffects.JUMP_BOOST))
				{
					this.motionY += (double)((float)(this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
				}

				this.setHorseJumping(true);
				this.isAirBorne = true;

				if (forward > 0.0F)
				{
					float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
					float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
					this.motionX += (double)(-0.4F * f * this.jumpPower);
					this.motionZ += (double)(0.4F * f1 * this.jumpPower);
					this.playSound(SoundEvents.ENTITY_HORSE_JUMP, 0.4F, 1.0F);
				}

				this.jumpPower = 0.0F;
			}

			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer())
			{
				this.setAIMoveSpeed(this.getSpeed());
				super.travel(strafe, vertical, forward);
			}

			if (this.onGround)
			{
				this.jumpPower = 0.0F;
				this.setHorseJumping(false);
			}
		}
		else
		{
			this.jumpMovementFactor = 0.02F;
			super.travel(strafe, vertical, forward);
		}
	}

	public double getHorseJumpStrength()
	{
		return this.getEntityAttribute(JUMP_STRENGTH).getAttributeValue();
	}

	public boolean canJump()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setJumpPower(int jumpPowerIn)
	{
		if (jumpPowerIn < 0)
		{
			jumpPowerIn = 0;
		}

		if (jumpPowerIn >= 90)
		{
			this.jumpPower = 1.0F;
		}
		else
		{
			this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
		}
	}

	@Override
	public void handleStartJump(int p_184775_1_)
	{
	}

	@Override
	public void handleStopJump()
	{
	}

	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);

		if (passenger instanceof EntityLiving)
		{
			EntityLiving entityliving = (EntityLiving)passenger;
			this.renderYawOffset = entityliving.renderYawOffset;
		}
	}
	
	public double getMountedYOffset()
    {
        return (double)this.height * 0.70D;
    }

	public boolean isOnLadder()
	{
		return false;
	}

	/**
	 * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
	 * Pigs, Horses, and Boats are generally "steered" by the controlling passenger.
	 */
	@Override
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	@Override
	public boolean canBeSteered()
	{
		return this.getControllingPassenger() instanceof EntityLivingBase;
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

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

			tailIdleAnimationClock[i].setPhaseDurationX(0, 50);
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