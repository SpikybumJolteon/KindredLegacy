//package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;
//
//import javax.annotation.Nullable;
//
//import fuzzyacornindustries.kindredlegacy.KindredLegacy;
//import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
//import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
//import fuzzyacornindustries.kindredlegacy.animation.ModMathFunctions;
//import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
//import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
//import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
//import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyEntities;
//import fuzzyacornindustries.kindredlegacy.network.ActionAnimationMessage;
//import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
//import fuzzyacornindustries.kindredlegacy.reference.action.LibraryOkamiPokemonAttackID;
//import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Blocks;
//import net.minecraft.block.CactusBlock;
//import net.minecraft.block.VineBlock;
//import net.minecraft.block.WebBlock;
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.IRangedAttackMob;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.SharedMonsterAttributes;
//import net.minecraft.entity.SpawnReason;
//import net.minecraft.entity.ai.goal.HurtByTargetGoal;
//import net.minecraft.entity.ai.goal.LookAtGoal;
//import net.minecraft.entity.ai.goal.LookRandomlyGoal;
//import net.minecraft.entity.ai.goal.MeleeAttackGoal;
//import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
//import net.minecraft.entity.ai.goal.RangedAttackGoal;
//import net.minecraft.entity.ai.goal.SwimGoal;
//import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
//import net.minecraft.entity.monster.ZombieEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.particles.ParticleTypes;
//import net.minecraft.potion.EffectInstance;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.EntityDamageSource;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.SoundEvent;
//import net.minecraft.util.SoundEvents;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.util.math.Vec3d;
//import net.minecraft.world.IWorld;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.network.PacketDistributor;
//
//public class BloodmoonNinetalesEntity extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity, IAnimateAhriNinetales, IMinorBoss
//{
//	private IdleAnimationClock bodyIdleAnimationClock;
//	private IdleAnimationClock armRtIdleAnimationClock;
//	private IdleAnimationClock tailsIdleAnimationClock[] = new IdleAnimationClock[8];
//	private IdleAnimationClock orbIdleAnimationClock;
//
//	static String mobName = "bloodmoon_ninetales";
//
//	private int igniteCooldown;
//	private int flashCooldown;
//	private int flashCooldownDuration;
//
//	private float proxyVengenceDamage = 15F;
//	private double proxyVengenceDistance = 32D;
//
//	public float maxFireballRange = 10.0F;
//	public float attackRange = 20F;
//
//	public BloodmoonNinetalesEntity(EntityType<? extends BloodmoonNinetalesEntity> type, World world)
//	{
//		super(type, world);
//
//		this.experienceValue = 30;
//
//		igniteCooldown = 0;
//		flashCooldown = 0;
//		flashCooldownDuration = 20 * 3;
//
//		if(this.world.isRemote)
//		{
//			setClockDefaults();
//		}
//	}
//
//	@Override
//	protected void registerGoals() 
//	{
//		this.goalSelector.addGoal(1, new SwimGoal(this));
//
//		this.goalSelector.addGoal(4, new GeneralRangedAttackGoal(this, 1.0D, 20, 20, attackRange));
//		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
//		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
//		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
//
//		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
//		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
//		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, true));
//
//
//		//		this.tasks.addTask(2, new AIBloodmoonNinetalesFoxfireSummon(this));
//		//		this.tasks.addTask(2, new AIBloodmoonNinetalesJumpFireball(this));
//		//		this.tasks.addTask(2, new AIBloodmoonNinetalesFireball(this));
//		//		this.tasks.addTask(2, new AIBloodmoonNinetalesFoxfireStorm(this));
//		//		this.tasks.addTask(2, new AIBloodmoonNinetalesFireblast(this));
//		//		this.tasks.addTask(2, new AIGeneralIgnite(this));
//
//	}
//
//	public static String getMobName()
//	{
//		return mobName;
//	}
//
//	@Override
//	protected void registerAttributes()
//	{
//		super.registerAttributes();
//
//		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(160.0D);
//		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
//		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(45.0D);
//	}
//
//	@Override
//	public int getTotalArmorValue()
//	{
//		return 6;
//	}
//
//	@Override
//	protected int decreaseAirSupply(int currentAirSupply)
//	{
//		return currentAirSupply;
//	}
//
//	@Override
//	public boolean onLivingFall(float distance, float damageMultiplier)
//	{
//		return false;
//	}
//
//	/**
//	 * adds a PotionEffect to the entity
//	 */
//	@Override
//	public boolean addPotionEffect(EffectInstance effectInstanceIn) 
//	{
//		return false;
//	}
//
//
//	@Override
//	protected SoundEvent getAmbientSound()
//	{
//		if(this.rand.nextInt(5) == 0)
//		{
//			return KindredLegacySoundEvents.BLOODMOON_NINETALES_LAUGH;
//		}
//		else
//		{
//			return null;
//		}
//	}
//
//	@Override
//	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//	{
//		return KindredLegacySoundEvents.BLOODMOON_NINETALES_HURT;
//	}
//
//	@Override
//	protected SoundEvent getDeathSound()
//	{
//		return KindredLegacySoundEvents.BLOODMOON_NINETALES_DEATH;
//	}
//
//	@Override
//	protected void playStepSound(BlockPos pos, BlockState blockIn) 
//	{
//		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
//	}
//
//
//	@Override
//	public void setMotionMultiplier(BlockState p_213295_1_, Vec3d p_213295_2_) 
//	{
//		if (p_213295_1_.getBlock() != Blocks.COBWEB) {
//			super.setMotionMultiplier(p_213295_1_, p_213295_2_);
//		}
//
//	}
//
//	@Override
//	public void livingTick()
//	{
//		if (this.isAlive() && this.isEntityInsideOpaqueBlock())
//		{
//			if (this.teleportRandomly(32D))
//			{
//				flashCooldown = flashCooldownDuration;
//			}
//		}
//
//		super.livingTick();
//	}
//
//	@Override
//	public void tick()
//	{
//		super.tick();
//
//		if(animationID != LibraryAhriNinetalesAttackID.NO_ACTION) animationTick++;
//
//		if(igniteCooldown > 0) igniteCooldown--;
//
//		if(flashCooldown > 0)
//		{
//			flashCooldown--;
//			/*
//			if(flashCooldown > flashCooldownDuration - 10)
//			{
//				this.worldObj.spawnParticle("largeexplode", preFlashLocation.getX() + this.worldObj.rand.nextFloat() * 2F,
//						preFlashLocation.getY() + this.worldObj.rand.nextFloat() * 2F,
//						preFlashLocation.getZ() + this.worldObj.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);
//			}
//
//			if(flashCooldown > flashCooldownDuration - 15 && flashCooldown < flashCooldownDuration - 5)
//			{
//				this.worldObj.spawnParticle("largeexplode", this.posX + this.worldObj.rand.nextFloat() * 2F, 
//						this.posY + this.worldObj.rand.nextFloat() * 2F, 
//						this.posZ + this.worldObj.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);
//
//			}*/
//		}
//
//		if ((double)this.getHealth() <= this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2D && ticksExisted % 20 == 0 && (int)this.getHealth() > 0)
//		{
//			int counter = 0;
//
//			counter = ((int)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2) / (int)this.getHealth();
//
//			if(counter > 10)
//			{
//				counter = 10;
//			}
//			/*
//			while(counter > 0)
//			{
//				PokemonMDMain.proxy.generateParticleBloodmoonFireBlast(this);
//
//				counter--;
//			}*/
//
//			if ((double)this.getHealth() <= this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 4D)
//			{
//				counter = ((int)this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 4) / (int)this.getHealth() + 1;
//
//				if(counter > 10)
//				{
//					counter = 10;
//				}
//
//				while(counter > 0)
//				{
//					double motionX = rand.nextGaussian() * 0.02D;
//					double motionY = rand.nextGaussian() * 0.02D;
//					double motionZ = rand.nextGaussian() * 0.02D;
//					this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosX() + rand.nextFloat() * this.getWidth() * 2.0F - this.getWidth(), 
//							this.getPosY() + 0.5D + rand.nextFloat() * this.getHeight() - 1F, this.getPosZ() + rand.nextFloat() * this.getWidth() * 2.0F - this.getWidth(), motionX, motionY, motionZ);
//
//					counter--;
//				}
//			}
//		}
//
//		int currentPosY = MathHelper.floor(this.getPosY());
//		int currentPoxX = MathHelper.floor(this.getPosX());
//		int currentPoxZ = MathHelper.floor(this.getPosZ());
//		boolean flag = false;
//
//		for (int l1 = -1; l1 <= 1; ++l1)
//		{
//			for (int i2 = -1; i2 <= 1; ++i2)
//			{
//				for (int j = 0; j <= 3; ++j)
//				{
//					int j2 = currentPoxX + l1;
//					int k = currentPosY + j;
//					int l = currentPoxZ + i2;
//
//					BlockPos blockpos = (new BlockPos(j2, k, l));
//                    BlockState blockstate = this.world.getBlockState(blockpos);
//					Block block = blockstate.getBlock();
//
//					if (block instanceof VineBlock || block instanceof WebBlock || block instanceof CactusBlock)
//					{
//						this.world.func_225521_a_(blockpos, true, this);
//					}
//				}
//			}
//		}
//	}
//
//	@Override
//	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) 
//	{ 
//		BlockPos blockpos = new BlockPos(MathHelper.floor(this.getPosX()), MathHelper.floor(this.getPosY()), MathHelper.floor(this.getPosZ()));
//
//		if (this.world.canBlockSeeSky(blockpos) && this.world.getCurrentMoonPhaseFactor() == 1.0F)
//		{
//			return super.canSpawn(worldIn, spawnReasonIn);
//		}
//
//		return false;
//	}
//
//	@Override
//	public void attackEntityWithRangedAttack(LivingEntity targetEntity, float par2)
//	{
//		if(animationID == LibraryAhriNinetalesAttackID.NO_ACTION)
//		{
//			if(!targetEntity.isAlive())
//			{
//				resetTargetToAttack();
//			}
//			else
//			{
//				int attackWeight = this.rand.nextInt(16);
//
//				if(targetEntity.getHealth() <= 4 && igniteCooldown <= 0)
//				{
//					KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryUniversalAttackID.IGNITE, this.getEntityId()));
//					this.setAnimationID(LibraryUniversalAttackID.IGNITE);
//					igniteCooldown = 30 * 20;
//				}
//				else if(attackWeight < 2 && !this.isWet() && !targetEntity.isWet())
//				{
//					if(isWithinProxyDeathfireRange(targetEntity))
//					{
//						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
//					}
//
//					if(!this.isInWater())
//					{
//						KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.FOXFIRE_SUMMON, this.getEntityId()));
//						this.setAnimationID(LibraryAhriNinetalesAttackID.FOXFIRE_SUMMON);
//					}
//					else
//					{
//						KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.FIREBALL, this.getEntityId()));
//						this.setAnimationID(LibraryAhriNinetalesAttackID.FIREBALL);
//					}
//				}
//				else if(attackWeight < 6 && (double)this.getHealth() <= this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2D)
//				{
//					if(isWithinProxyDeathfireRange(targetEntity))
//					{
//						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
//					}
//
//					KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.FOXFIRE_STORM, this.getEntityId()));
//					this.setAnimationID(LibraryAhriNinetalesAttackID.FOXFIRE_STORM);
//				}
//				else if(attackWeight < 13 && !this.isInWater())
//				{
//					if(isWithinProxyDeathfireRange(targetEntity))
//					{
//						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
//					}
//
//					if((double)this.getHealth() <= this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2D)
//					{
//						KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.FIREBLAST, this.getEntityId()));
//						this.setAnimationID(LibraryAhriNinetalesAttackID.FIREBLAST);
//					}
//					else
//					{
//						KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.JUMP_FIREBALL, this.getEntityId()));
//						this.setAnimationID(LibraryAhriNinetalesAttackID.JUMP_FIREBALL);
//					}
//				}
//				else
//				{
//					if(isWithinProxyDeathfireRange(targetEntity))
//					{
//						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
//					}
//
//					KindredLegacy.network.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new ActionAnimationMessage((byte)LibraryAhriNinetalesAttackID.FIREBALL, this.getEntityId()));
//					this.setAnimationID(LibraryAhriNinetalesAttackID.FIREBALL);
//				}
//			}
//		}
//	}
//
//	public boolean isWithinProxyDeathfireRange(LivingEntity targetEntity)
//	{
//		return targetEntity.getDistanceSq(this.getPosX(), this.getBoundingBox().minY, this.getPosZ()) < proxyVengenceDistance;
//	}
//
//	public void attackWithFireball(BloodmoonNinetalesEntity attackingMob, LivingEntity targetEntity, float par2)
//	{
//		Vector3f spawnFireballPoint = new Vector3f(ModMathFunctions.findShooterOffsetPoint(attackingMob.getPosX(), attackingMob.getPosZ(), 
//				targetEntity.getPosX(), targetEntity.getPosZ(), 1F));
//
//		double d0 = targetEntity.getPosX() - spawnFireballPoint.getX();//this.posX;
//		double d1 = targetEntity.getBoundingBox().minY + (double)(targetEntity.getHeight() / 2.0F) - (attackingMob.getPosY() + (double)(attackingMob.getHeight() / 2.0F));
//		double d2 = targetEntity.getPosZ() - spawnFireballPoint.getZ();//this.posZ;
//
//		//float f1 = MathHelper.sqrt(par2) * 0.1F;
//		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
//
//		BloodmoonFireballEntity entityFireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//				spawnFireballPoint.getX(), attackingMob.getPosY(), spawnFireballPoint.getZ(),
//				d0 + targetEntity.getMotion().getX() * 0.5F, d1 + targetEntity.getMotion().getY() * 0.5F, d2 + targetEntity.getMotion().getZ() * 0.5F, 
//				1.0F, 8F);
//		entityFireball.posY = attackingMob.getPosY() + (double)(attackingMob.getHeight() / 2.0F) + 0.25F;
//
//		this.world.spawnEntity(entityFireball);
//	}
//
//	public void attackWithOffsetFireball(BloodmoonNinetalesEntity attackingMob, LivingEntity targetEntity, float par2)
//	{
//		float fireballOffsetDistance = 4F;
//
//		float offsetX = (float) (attackingMob.posX + attackingMob.rand.nextFloat() * fireballOffsetDistance * 2F - fireballOffsetDistance);
//		float offsetY = (float) (attackingMob.posY + attackingMob.rand.nextFloat() * fireballOffsetDistance);
//		float offsetZ = (float) (attackingMob.posZ + attackingMob.rand.nextFloat() * fireballOffsetDistance * 2F - fireballOffsetDistance);
//
//		Vector3f spawnFireballPoint = new Vector3f(offsetX, offsetY, offsetZ);
//
//		double d0 = targetEntity.posX - spawnFireballPoint.getX();//this.posX;
//		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - spawnFireballPoint.getY();
//		double d2 = targetEntity.posZ - spawnFireballPoint.getZ();//this.posZ;
//
//		float f1 = MathHelper.sqrt(par2) * 2.8F;
//		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
//
//		BloodmoonFireballEntity entityFireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//				spawnFireballPoint.getX(), spawnFireballPoint.getY(), spawnFireballPoint.getZ(),
//				d0 + targetEntity.motionX * 0.5F, d1 + targetEntity.motionY * 0.5F, d2 + targetEntity.motionZ * 0.5F, 
//				1.0F, 8F);
//
//		this.world.spawnEntity(entityFireball);
//
//		this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entityFireball.posX + this.world.rand.nextFloat() * 2F, 
//				entityFireball.posY + this.world.rand.nextFloat() * 2F, 
//				entityFireball.posZ + this.world.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);
//
//		//System.out.println( "Test Animation Tick Code in Entity class" );
//		//System.out.println( "X = " + Float.toString( spawnFireballPoint.getX() ) + ", Y = " + Float.toString( spawnFireballPoint.getY() ) + ", Z = " + Float.toString( spawnFireballPoint.getZ() ) + ".");
//	}
//
//	public void attackWithFireblast(BloodmoonNinetalesEntity attackingMob, EntityLivingBase targetEntity, float par2)
//	{
//		Vector3f spawnFireballPoint = new Vector3f(ModMathFunctions.findShooterOffsetPoint(attackingMob.posX, attackingMob.posZ, 
//				targetEntity.posX, targetEntity.posZ, 1F));
//
//		float fireBlastAttackPower = 16F;
//
//		double d0 = targetEntity.posX - spawnFireballPoint.getX();//this.posX;
//		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - (attackingMob.posY + (double)(attackingMob.height / 2.0F));
//		double d2 = targetEntity.posZ - spawnFireballPoint.getZ();//this.posZ;
//
//		float f1 = MathHelper.sqrt(par2) * 0.2F;
//		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));
//
//		BloodmoonFireballEntity mainEntitysmallfireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//				spawnFireballPoint.getX(), attackingMob.posY, spawnFireballPoint.getZ(),
//				d0, d1, d2, 1.0F, fireBlastAttackPower);
//		mainEntitysmallfireball.posY = attackingMob.posY + (double)(attackingMob.height / 2.0F);
//
//		float offsetDistance = 0.9f;
//
//		double originX = targetEntity.posX - this.posX;
//		double originY = targetEntity.posY - this.posY;
//		double originZ = targetEntity.posZ - this.posZ;
//
//		double xzDistance = Math.sqrt(Math.pow(originX,  2D) + Math.pow(originZ,  2D));
//		float offsetAngleX = (float)Math.atan2(originY, xzDistance);
//
//		Vector3f topFireballPoint = new Vector3f(0, offsetDistance, 0);
//		Vector3f sideFireballPoint = new Vector3f(offsetDistance, 0, 0);
//		Vector3f diagonalFireballPoint = new Vector3f(MathHelper.cos((float)Math.toRadians(60)) * offsetDistance,
//				-MathHelper.sin((float)Math.toRadians(60)) * offsetDistance, 0F);
//
//		double offsetAngleY = Math.atan2(spawnFireballPoint.getZ() - this.posZ, spawnFireballPoint.getX() - this.posX) - Math.toRadians(90);
//		float offsetSin = -(float)Math.sin(offsetAngleY);
//		float offsetCos = (float)Math.cos(offsetAngleY);
//
//		/* Top Fireball Sets */
//		float newZY[] = new float[2];
//		newZY = ModMathFunctions.rotatePointGeneric2D(0, topFireballPoint.getY(), offsetAngleX);
//
//		topFireballPoint.setY(newZY[1]);
//		float topFireballHorzOffset = newZY[0];
//
//		for(int i = 1; i <= 2; i++)
//		{
//			BloodmoonFireballEntity entitysmallfireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//					mainEntitysmallfireball.posX + (double)i * topFireballHorzOffset * offsetSin, 
//					mainEntitysmallfireball.posY + (double)i * topFireballPoint.getY(), 
//					mainEntitysmallfireball.posZ + (double)i * topFireballHorzOffset * offsetCos,
//					d0, d1, d2, 1.0F, fireBlastAttackPower);
//
//			this.world.spawnEntity(entitysmallfireball);
//		}
//
//		/* Side Fireball Sets */
//		for(int i = 0; i < 5; i++)
//		{
//			BloodmoonFireballEntity entitysmallfireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//					mainEntitysmallfireball.posX + offsetCos * (offsetDistance * (i - 2)), mainEntitysmallfireball.posY, mainEntitysmallfireball.posZ - offsetSin * (offsetDistance * (i - 2)),
//					d0, d1, d2, 1.0F, fireBlastAttackPower);
//
//			this.world.spawnEntity(entitysmallfireball);
//		}
//
//		/* Diagonal Fireball Sets */
//		newZY = new float[2];
//		newZY = ModMathFunctions.rotatePointGeneric2D(diagonalFireballPoint.getZ(), diagonalFireballPoint.getY(), offsetAngleX);
//
//		float diagonalFireballHorzOffset = newZY[0];
//
//		for(int i = 1; i <= 2; i++)
//		{
//			BloodmoonFireballEntity entitysmallfireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//					mainEntitysmallfireball.posX + (double)i * (diagonalFireballHorzOffset * offsetSin + diagonalFireballPoint.getX() * offsetCos), 
//					mainEntitysmallfireball.posY + (double)i * newZY[1], 
//					mainEntitysmallfireball.posZ + (double)i * (diagonalFireballHorzOffset * offsetCos - diagonalFireballPoint.getX() * offsetSin),
//					d0, d1, d2, 1.0F, fireBlastAttackPower);
//
//			this.world.spawnEntity(entitysmallfireball);
//		}
//
//		newZY = ModMathFunctions.rotatePointGeneric2D(-diagonalFireballPoint.getZ(), diagonalFireballPoint.getY(), offsetAngleX);
//
//		diagonalFireballHorzOffset = newZY[0];		
//
//		for(int i = 1; i <= 2; i++)
//		{
//			BloodmoonFireballEntity entitysmallfireball = new BloodmoonFireballEntity(attackingMob.world, attackingMob, 
//					mainEntitysmallfireball.posX + (double)i * (diagonalFireballHorzOffset * offsetSin - diagonalFireballPoint.getX() * offsetCos), 
//					mainEntitysmallfireball.posY + (double)i * newZY[1], 
//					mainEntitysmallfireball.posZ + (double)i * (diagonalFireballHorzOffset * offsetCos + diagonalFireballPoint.getX() * offsetSin),
//					d0, d1, d2, 1.0F, fireBlastAttackPower);
//
//			this.world.spawnEntity(entitysmallfireball);
//		}
//
//		/*
//		System.out.println( "Test Fireball Coordinates for Fire Blast" );
//		//System.out.println( "MainFireball: " + Double.toString( mainEntitysmallfireball.posX ) + ", " + Double.toString( mainEntitysmallfireball.posY ) + ", " + Double.toString( mainEntitysmallfireball.posZ ));
//		System.out.println( "newZY[0]: " + Double.toString( newZY[0] ) );
//		System.out.println( "newZY[1]: " + Double.toString( newZY[1] ) );
//		System.out.println( "offsetAngleX: " + Double.toString( offsetAngleX ) );
//		System.out.println( "offsetCos: " + Double.toString( offsetCos ) );
//		System.out.println( "offsetSin: " + Double.toString( offsetSin ) );
//		//System.out.println( "TopFireball Vector: " + Float.toString( topFireballPoint.getX()) + ", " + Float.toString( topFireballPoint.getY() ) + ", " + Float.toString( topFireballPoint.getZ()));
//		System.out.println( "diagonalFireballHorzOffset * offsetCos: " + Double.toString( diagonalFireballHorzOffset * offsetCos ) );
//		System.out.println( "diagonalFireballHorzOffset * offsetSin: " + Double.toString( diagonalFireballHorzOffset * offsetSin ) );
//		System.out.println( " " );/**/
//	}
//	/*
//	public boolean isInLiquid()
//	{
//		int currentPosY = MathHelper.floor(this.posY);
//		int currentPoxX = MathHelper.floor(this.posX);
//		int currentPoxZ = MathHelper.floor(this.posZ);
//
//		for(int i = 0; i < 2; i++)
//		{
//			this.isInWater()
//
//			int j2 = currentPoxX;
//			int k = currentPosY - i;
//			int l = currentPoxZ;
//			Block block = this.world.getBlock(j2, k, l);
//
//			if (block instanceof BlockLiquid)
//			{
//				return true;
//			}
//		}
//
//		return false;
//	}*/
//
//	/**
//	 * Called when the entity is attacked.
//	 */
//	@Override
//	public boolean attackEntityFrom(DamageSource damageSource, float damageAmount)
//	{
//		if (this.isEntityInvulnerable(damageSource))
//		{
//			return false;
//		}
//		else
//		{
//			if (damageSource instanceof EntityDamageSource)
//			{
//				if(damageSource.getTrueSource() instanceof EntityLiving)
//				{	
//					if((damageSource.getTrueSource().getDistanceSq(this.posX, this.getBoundingBox().minY, this.posZ) < 18D || 
//							damageSource.getTrueSource().getDistanceSq(this.posX, this.getBoundingBox().minY, this.posZ) > Math.pow(attackRange + 3F, 2D)) 
//							&& flashCooldown == 0)
//					{
//						for (int i = 0; i < 64; ++i)
//						{
//							if (this.teleportRandomly(32D))
//							{
//								flashCooldown = flashCooldownDuration;
//
//								switchTargetToAttack(damageSource);
//
//								return true;
//							}
//						}
//					}
//
//					if(damageSource.getTrueSource() instanceof TamablePokemon)
//					{
//						super.attackEntityFrom(damageSource, damageAmount * 0.5F);
//					}
//				}
//				else if(damageSource.isExplosion())
//				{
//					for (int i = 0; i < 64; ++i)
//					{
//						if (this.teleportRandomly(32D))
//						{
//							flashCooldown = flashCooldownDuration;
//							return true;
//						}
//					}
//				}
//				/*
//				else if(damageSource.getTrueSource() instanceof EntityLivingBase)
//				{
//					switchTargetToAttack(damageSource);
//				}*/
//			}
//
//			return super.attackEntityFrom(damageSource, damageAmount);
//		}
//	}
//
//	/**
//	 * sets this entity's combat AI.
//	 */
//	public void switchTargetToAttack(DamageSource damageSource)
//	{
//		//this.setTarget((Entity)null);
//		if(!(damageSource.getTrueSource() instanceof EntityPlayerMP) || !((EntityPlayerMP)damageSource.getTrueSource()).isCreative())
//		{
//			this.setAttackTarget((EntityLivingBase)damageSource.getTrueSource());
//		}
//	}
//
//	/**
//	 * sets this entity's combat AI.
//	 */
//	public void resetTargetToAttack()
//	{
//		this.setAttackTarget((EntityLivingBase)null);
//	}
//
//	/**
//	 * Teleport entity to a random nearby position
//	 */
//	protected boolean teleportRandomly(double maxDistance)
//	{
//		double d0 = this.posX + (this.rand.nextDouble() - 0.5D) * maxDistance;
//		double d1 = this.posY + (double)(this.rand.nextInt(10) - 5);
//		double d2 = this.posZ + (this.rand.nextDouble() - 0.5D) * maxDistance;
//
//		net.minecraftforge.event.entity.living.EnderTeleportEvent event = new net.minecraftforge.event.entity.living.EnderTeleportEvent(this, d0, d1, d2, 0);
//		if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) return false;
//		boolean flag = this.attemptTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ());
//
//		if (flag)
//		{
//			this.world.playSound((EntityPlayer)null, this.prevPosX, this.prevPosY, this.prevPosZ, KindredLegacySoundEvents.FLASH, this.getSoundCategory(), 1.0F, 1.0F);
//			this.playSound(KindredLegacySoundEvents.FLASH, 1.0F, 1.0F);
//		}
//
//		return flag;
//	}
//
//	public void playAttackSound()
//	{
//		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_ATTACK, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
//	}
//
//	public void playExertSelfSound()
//	{
//		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_EXERT_SELF, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
//	}
//
//	public void playGaspSound()
//	{
//		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_GASP, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
//	}
//
//	public void playLaughSound()
//	{
//		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_LAUGH, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
//	}
//	/*
//	@Override
//    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
//    {
//        EntityItem entityitem = this.dropItem(KindredLegacyItems.BLESSING_OF_ARCEUS, 1);
//    }*/
//
//	@Nullable
//	protected ResourceLocation getLootTable()
//	{
//		return KindredLegacyLootTables.BLOODMOON_NINETALES_LOOT_TABLE;
//	}
//
//	/************************************
//	 * Animation dependent code follows.*
//	 ************************************/
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IdleAnimationClock getIdleAnimationClockBody()
//	{
//		return bodyIdleAnimationClock;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IdleAnimationClock getIdleAnimationClockArmRt()
//	{
//		return armRtIdleAnimationClock;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IdleAnimationClock getIdleAnimationClockTails(int partNumber)
//	{
//		return tailsIdleAnimationClock[partNumber];
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IdleAnimationClock getIdleAnimationClockOrb()
//	{
//		return orbIdleAnimationClock;
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void incrementPartClocks()
//	{
//		bodyIdleAnimationClock.incrementClocks();
//
//		for(int i = 0; i < tailsIdleAnimationClock.length; i++)
//		{
//			tailsIdleAnimationClock[i].incrementClocks();
//		}
//
//		armRtIdleAnimationClock.incrementClocks();
//		orbIdleAnimationClock.incrementClocks();
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void setClockDefaults()
//	{
//		setBodyClockDefaults();
//		setArmRtClockDefaults();
//		setTailsClockDefaults();
//		setOrbClockDefaults();
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void setBodyClockDefaults()
//	{
//		int randomInt = this.rand.nextInt(100);
//
//		bodyIdleAnimationClock = new IdleAnimationClock(1, 0, 0);
//
//		bodyIdleAnimationClock.setPhaseDurationX(0, 50);
//
//		int startingClockX = randomInt;
//
//		while(startingClockX > bodyIdleAnimationClock.getTotalDurationLengthX())
//		{
//			startingClockX -= bodyIdleAnimationClock.getTotalDurationLengthX();
//		}
//
//		bodyIdleAnimationClock.setClockX(startingClockX);
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void setArmRtClockDefaults()
//	{
//		int randomInt = this.rand.nextInt(100);
//
//		armRtIdleAnimationClock = new IdleAnimationClock(1, 0, 0);
//
//		armRtIdleAnimationClock.setPhaseDurationX(0, 60);
//
//		int startingClockX = randomInt;
//
//		while(startingClockX > armRtIdleAnimationClock.getTotalDurationLengthX())
//		{
//			startingClockX -= armRtIdleAnimationClock.getTotalDurationLengthX();
//		}
//
//		armRtIdleAnimationClock.setClockX(startingClockX);
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void setTailsClockDefaults()
//	{
//		int randomInt = this.rand.nextInt(100);
//
//		for(int i = 0; i < tailsIdleAnimationClock.length; i++)
//		{
//			tailsIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);
//
//			tailsIdleAnimationClock[i].setPhaseDurationX(0, 45);
//			tailsIdleAnimationClock[i].setPhaseDurationY(0, 80);
//
//			float pointClusterDensityX = 0.95F;
//			float pointClusterDensityY = 0.85F;
//
//			int startingClockX = (int)(((float)(tailsIdleAnimationClock.length - i) / (float)tailsIdleAnimationClock.length) * (float)tailsIdleAnimationClock[i].getTotalDurationLengthX() * pointClusterDensityX) + randomInt;
//			int startingClockY = (int)(((float)(tailsIdleAnimationClock.length - i) / (float)tailsIdleAnimationClock.length) * (float)tailsIdleAnimationClock[i].getTotalDurationLengthY() * pointClusterDensityY) + randomInt;
//
//			while(startingClockX > tailsIdleAnimationClock[i].getTotalDurationLengthX())
//			{
//				startingClockX -= tailsIdleAnimationClock[i].getTotalDurationLengthX();
//			}
//
//			while(startingClockY > tailsIdleAnimationClock[i].getTotalDurationLengthY())
//			{
//				startingClockY -= tailsIdleAnimationClock[i].getTotalDurationLengthY();
//			}
//
//			tailsIdleAnimationClock[i].setClockX(startingClockX);
//			tailsIdleAnimationClock[i].setClockY(startingClockY);
//		}
//	}
//
//	@SideOnly(Side.CLIENT)
//	public void setOrbClockDefaults()
//	{
//		int randomInt = this.rand.nextInt(100);
//
//		orbIdleAnimationClock = new IdleAnimationClock(1, 0, 0);
//
//		orbIdleAnimationClock.setPhaseDurationX(0, 40);
//
//		int startingClockX = randomInt;
//
//		while(startingClockX > orbIdleAnimationClock.getTotalDurationLengthX())
//		{
//			startingClockX -= orbIdleAnimationClock.getTotalDurationLengthX();
//		}
//
//		orbIdleAnimationClock.setClockX(startingClockX);
//	}
//}