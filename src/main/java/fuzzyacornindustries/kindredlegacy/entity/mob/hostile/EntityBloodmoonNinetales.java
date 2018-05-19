package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.animation.ModMathFunctions;
import fuzzyacornindustries.kindredlegacy.animation.Vector3f;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.IAnimateAhriNinetales;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIBloodmoonNinetalesFireball;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIBloodmoonNinetalesFireblast;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIBloodmoonNinetalesFoxfireStorm;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIBloodmoonNinetalesFoxfireSummon;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIBloodmoonNinetalesJumpFireball;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralIgnite;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AIGeneralRangedAttack;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.EntityAINearestAttackableZombieExcludingPigman;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityBloodmoonFireball;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryUniversalAttackID;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBloodmoonNinetales extends HostilePokemon implements IRangedAttackMob, IAnimatedEntity, IAnimateAhriNinetales, IMinorBoss
{
	private IdleAnimationClock bodyIdleAnimationClock;
	private IdleAnimationClock armRtIdleAnimationClock;
	private IdleAnimationClock tailsIdleAnimationClock[] = new IdleAnimationClock[8];
	private IdleAnimationClock orbIdleAnimationClock;

	static String mobName = "bloodmoon_ninetales";

	private int igniteCooldown;
	private int flashCooldown;
	private int flashCooldownDuration;

	private float proxyVengenceDamage = 15F;
	private double proxyVengenceDistance = 32D;

	public float maxFireballRange = 10.0F;
	public float attackRange = 20F;

	public EntityBloodmoonNinetales(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 1.9F);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new AIBloodmoonNinetalesFoxfireSummon(this));
		this.tasks.addTask(2, new AIBloodmoonNinetalesJumpFireball(this));
		this.tasks.addTask(2, new AIBloodmoonNinetalesFireball(this));
		this.tasks.addTask(2, new AIBloodmoonNinetalesFoxfireStorm(this));
		this.tasks.addTask(2, new AIBloodmoonNinetalesFireblast(this));
		this.tasks.addTask(2, new AIGeneralIgnite(this));

		this.tasks.addTask(3, new AIGeneralRangedAttack(this, 1.0D, 20, attackRange));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));

		if(KindredLegacyEntities.mobsHostileToVanillaMobs)
			this.targetTasks.addTask(2, new EntityAINearestAttackableZombieExcludingPigman(this, true));

		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		this.isImmuneToFire = true;
		this.experienceValue = 20;

		igniteCooldown = 0;
		flashCooldown = 0;
		flashCooldownDuration = 20 * 3;

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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(160.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(45.0D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 6;
	}

	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		return currentAirSupply;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	/**
	 * adds a PotionEffect to the entity
	 */
	@Override
	public void addPotionEffect(PotionEffect potioneffectIn) {}

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		if(this.rand.nextInt(5) == 0)
		{
			return KindredLegacySoundEvents.BLOODMOON_NINETALES_LAUGH;
		}
		else
		{
			return null;
		}
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.BLOODMOON_NINETALES_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.BLOODMOON_NINETALES_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	@Override
	public void setInWeb() {}

	@Override
	public void onLivingUpdate()
	{
		if (this.isEntityAlive() && this.isEntityInsideOpaqueBlock())
		{
			if (this.teleportRandomly(32D))
			{
				flashCooldown = flashCooldownDuration;
			}
		}

		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(animationID != LibraryAhriNinetalesAttackID.NO_ACTION) animationTick++;

		if(igniteCooldown > 0) igniteCooldown--;

		if(flashCooldown > 0)
		{
			flashCooldown--;
			/*
			if(flashCooldown > flashCooldownDuration - 10)
			{
				this.worldObj.spawnParticle("largeexplode", preFlashLocation.getX() + this.worldObj.rand.nextFloat() * 2F,
						preFlashLocation.getY() + this.worldObj.rand.nextFloat() * 2F,
						preFlashLocation.getZ() + this.worldObj.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);
			}

			if(flashCooldown > flashCooldownDuration - 15 && flashCooldown < flashCooldownDuration - 5)
			{
				this.worldObj.spawnParticle("largeexplode", this.posX + this.worldObj.rand.nextFloat() * 2F, 
						this.posY + this.worldObj.rand.nextFloat() * 2F, 
						this.posZ + this.worldObj.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);

			}*/
		}

		if ((double)this.getHealth() <= this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2D && ticksExisted % 20 == 0 && (int)this.getHealth() > 0)
		{
			int counter = 0;

			counter = ((int)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2) / (int)this.getHealth();

			if(counter > 10)
			{
				counter = 10;
			}
			/*
			while(counter > 0)
			{
				PokemonMDMain.proxy.generateParticleBloodmoonFireBlast(this);

				counter--;
			}*/

			if ((double)this.getHealth() <= this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 4D)
			{
				counter = ((int)this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 4) / (int)this.getHealth() + 1;

				if(counter > 10)
				{
					counter = 10;
				}

				while(counter > 0)
				{
					double motionX = rand.nextGaussian() * 0.02D;
					double motionY = rand.nextGaussian() * 0.02D;
					double motionZ = rand.nextGaussian() * 0.02D;
					this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX + rand.nextFloat() * width * 2.0F - width, 
							posY + 0.5D + rand.nextFloat() * height - 1F, posZ + rand.nextFloat() * width * 2.0F - width, motionX, motionY, motionZ, new int[0]);

					counter--;
				}
			}
		}
		/*
		if(!this.world.isRemote)
		{
			int currentPosY = MathHelper.floor(this.posY);
			int currentPoxX = MathHelper.floor(this.posX);
			int currentPoxZ = MathHelper.floor(this.posZ);
			boolean flag = false;

			for (int l1 = -1; l1 <= 1; ++l1)
			{
				for (int i2 = -1; i2 <= 1; ++i2)
				{
					for (int j = 0; j <= 3; ++j)
					{
						int j2 = currentPoxX + l1;
						int k = currentPosY + j;
						int l = currentPoxZ + i2;
						Block block = this.world.getBlock(j2, k, l);

						if (!block.isAir(world, j2, k, l) && block == Blocks.tallgrass || block instanceof BlockDoublePlant || block instanceof BlockFlower || block instanceof BlockVine || block instanceof BlockWeb || block instanceof BlockCactus)
						{
							flag = this.worldObj.func_147480_a(j2, k, l, true) || flag;
						}
					}
				}
			}
		}*/
	}

	@Override
	public boolean getCanSpawnHere()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ));

		if (this.world.canBlockSeeSky(blockpos) && this.world.getCurrentMoonPhaseFactor() == 1.0F)
		{
			if(this.rand.nextInt(3) == 0)
			{
				return super.getCanSpawnHere();
			}
		}

		return false;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targetEntity, float par2)
	{
		if(animationID == LibraryUniversalAttackID.NO_ACTION)
		{
			if(targetEntity.isDead)
			{
				resetTargetToAttack();
			}
			else
			{
				int attackWeight = this.rand.nextInt(16);

				if(targetEntity.getHealth() <= 4 && igniteCooldown <= 0)
				{
					KindredLegacyMain.sendAnimationPacket(this, LibraryUniversalAttackID.IGNITE);

					igniteCooldown = 30 * 20;
				}
				else if(attackWeight < 2 && !this.isWet() && !targetEntity.isWet())
				{
					if(isWithinProxyDeathfireRange(targetEntity))
					{
						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
					}

					if(!this.isInWater())
					{
						KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.FOXFIRE_SUMMON);
					}
					else
					{
						KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.FIREBALL);
					}
				}
				else if(attackWeight < 6 && (double)this.getHealth() <= this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2D)
				{
					if(isWithinProxyDeathfireRange(targetEntity))
					{
						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
					}

					KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.FOXFIRE_STORM);
				}
				else if(attackWeight < 13 && !this.isInWater())
				{
					if(isWithinProxyDeathfireRange(targetEntity))
					{
						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
					}

					if((double)this.getHealth() <= this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue() / 2D)
					{
						KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.FIREBLAST);
					}
					else
					{
						KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.JUMP_FIREBALL);
					}
				}
				else
				{
					if(isWithinProxyDeathfireRange(targetEntity))
					{
						targetEntity.attackEntityFrom(DamageSource.causeMobDamage(this), proxyVengenceDamage);
					}

					KindredLegacyMain.sendAnimationPacket(this, LibraryAhriNinetalesAttackID.FIREBALL);
				}
			}
		}
	}

	public boolean isWithinProxyDeathfireRange(EntityLivingBase targetEntity)
	{
		return targetEntity.getDistanceSq(this.posX, this.getEntityBoundingBox().minY, this.posZ) < proxyVengenceDistance;
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}
	
	public void attackWithFireball(EntityBloodmoonNinetales attackingMob, EntityLivingBase targetEntity, float par2)
	{
		Vector3f spawnFireballPoint = new Vector3f(ModMathFunctions.findShooterOffsetPoint(attackingMob.posX, attackingMob.posZ, 
				targetEntity.posX, targetEntity.posZ, 1F));

		double d0 = targetEntity.posX - spawnFireballPoint.getX();//this.posX;
		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - (attackingMob.posY + (double)(attackingMob.height / 2.0F));
		double d2 = targetEntity.posZ - spawnFireballPoint.getZ();//this.posZ;

		//float f1 = MathHelper.sqrt(par2) * 0.1F;
		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

		EntityBloodmoonFireball entityFireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
				spawnFireballPoint.getX(), attackingMob.posY, spawnFireballPoint.getZ(),
				d0 + targetEntity.motionX * 0.5F, d1 + targetEntity.motionY * 0.5F, d2 + targetEntity.motionZ * 0.5F, 
				1.5F, 8F);
		entityFireball.posY = attackingMob.posY + (double)(attackingMob.height / 2.0F) + 0.25F;

		this.world.spawnEntity(entityFireball);
	}

	public void attackWithOffsetFireball(EntityBloodmoonNinetales attackingMob, EntityLivingBase targetEntity, float par2)
	{
		float fireballOffsetDistance = 4F;

		float offsetX = (float) (attackingMob.posX + attackingMob.rand.nextFloat() * fireballOffsetDistance * 2F - fireballOffsetDistance);
		float offsetY = (float) (attackingMob.posY + attackingMob.rand.nextFloat() * fireballOffsetDistance);
		float offsetZ = (float) (attackingMob.posZ + attackingMob.rand.nextFloat() * fireballOffsetDistance * 2F - fireballOffsetDistance);

		Vector3f spawnFireballPoint = new Vector3f(offsetX, offsetY, offsetZ);

		double d0 = targetEntity.posX - spawnFireballPoint.getX();//this.posX;
		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - spawnFireballPoint.getY();
		double d2 = targetEntity.posZ - spawnFireballPoint.getZ();//this.posZ;

		float f1 = MathHelper.sqrt(par2) * 2.8F;
		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

		EntityBloodmoonFireball entityFireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
				spawnFireballPoint.getX(), spawnFireballPoint.getY(), spawnFireballPoint.getZ(),
				d0 + targetEntity.motionX * 0.5F, d1 + targetEntity.motionY * 0.5F, d2 + targetEntity.motionZ * 0.5F, 
				1.5F, 8F);

		this.world.spawnEntity(entityFireball);

		this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, entityFireball.posX + this.world.rand.nextFloat() * 2F, 
				entityFireball.posY + this.world.rand.nextFloat() * 2F, 
				entityFireball.posZ + this.world.rand.nextFloat() * 2F, 1.0D, 0.0D, 0.0D);

		//System.out.println( "Test Animation Tick Code in Entity class" );
		//System.out.println( "X = " + Float.toString( spawnFireballPoint.getX() ) + ", Y = " + Float.toString( spawnFireballPoint.getY() ) + ", Z = " + Float.toString( spawnFireballPoint.getZ() ) + ".");
	}

	public void attackWithFireblast(EntityBloodmoonNinetales attackingMob, EntityLivingBase targetEntity, float par2)
	{
		Vector3f spawnFireballPoint = new Vector3f(ModMathFunctions.findShooterOffsetPoint(attackingMob.posX, attackingMob.posZ, 
				targetEntity.posX, targetEntity.posZ, 1F));

		float fireBlastAttackPower = 16F;

		double d0 = targetEntity.posX - spawnFireballPoint.getX();//this.posX;
		double d1 = targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 2.0F) - (attackingMob.posY + (double)(attackingMob.height / 2.0F));
		double d2 = targetEntity.posZ - spawnFireballPoint.getZ();//this.posZ;

		float f1 = MathHelper.sqrt(par2) * 0.2F;
		this.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (this.rand.nextFloat() * 0.4F + 0.8F));

		EntityBloodmoonFireball mainEntitysmallfireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
				spawnFireballPoint.getX(), attackingMob.posY, spawnFireballPoint.getZ(),
				d0, d1, d2, 1.0F, fireBlastAttackPower);
		mainEntitysmallfireball.posY = attackingMob.posY + (double)(attackingMob.height / 2.0F);

		float offsetDistance = 0.9f;

		double originX = targetEntity.posX - this.posX;
		double originY = targetEntity.posY - this.posY;
		double originZ = targetEntity.posZ - this.posZ;

		double xzDistance = Math.sqrt(Math.pow(originX,  2D) + Math.pow(originZ,  2D));
		float offsetAngleX = (float)Math.atan2(originY, xzDistance);

		Vector3f topFireballPoint = new Vector3f(0, offsetDistance, 0);
		Vector3f sideFireballPoint = new Vector3f(offsetDistance, 0, 0);
		Vector3f diagonalFireballPoint = new Vector3f(MathHelper.cos((float)Math.toRadians(60)) * offsetDistance,
				-MathHelper.sin((float)Math.toRadians(60)) * offsetDistance, 0F);

		double offsetAngleY = Math.atan2(spawnFireballPoint.getZ() - this.posZ, spawnFireballPoint.getX() - this.posX) - Math.toRadians(90);
		float offsetSin = -(float)Math.sin(offsetAngleY);
		float offsetCos = (float)Math.cos(offsetAngleY);

		/* Top Fireball Sets */
		float newZY[] = new float[2];
		newZY = ModMathFunctions.rotatePointGeneric2D(0, topFireballPoint.getY(), offsetAngleX);

		topFireballPoint.setY(newZY[1]);
		float topFireballHorzOffset = newZY[0];

		for(int i = 1; i <= 2; i++)
		{
			EntityBloodmoonFireball entitysmallfireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
					mainEntitysmallfireball.posX + (double)i * topFireballHorzOffset * offsetSin, 
					mainEntitysmallfireball.posY + (double)i * topFireballPoint.getY(), 
					mainEntitysmallfireball.posZ + (double)i * topFireballHorzOffset * offsetCos,
					d0, d1, d2, 1.0F, fireBlastAttackPower);

			this.world.spawnEntity(entitysmallfireball);
		}

		/* Side Fireball Sets */
		for(int i = 0; i < 5; i++)
		{
			EntityBloodmoonFireball entitysmallfireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
					mainEntitysmallfireball.posX + offsetCos * (offsetDistance * (i - 2)), mainEntitysmallfireball.posY, mainEntitysmallfireball.posZ - offsetSin * (offsetDistance * (i - 2)),
					d0, d1, d2, 1.0F, fireBlastAttackPower);

			this.world.spawnEntity(entitysmallfireball);
		}

		/* Diagonal Fireball Sets */
		newZY = new float[2];
		newZY = ModMathFunctions.rotatePointGeneric2D(diagonalFireballPoint.getZ(), diagonalFireballPoint.getY(), offsetAngleX);

		float diagonalFireballHorzOffset = newZY[0];

		for(int i = 1; i <= 2; i++)
		{
			EntityBloodmoonFireball entitysmallfireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
					mainEntitysmallfireball.posX + (double)i * (diagonalFireballHorzOffset * offsetSin + diagonalFireballPoint.getX() * offsetCos), 
					mainEntitysmallfireball.posY + (double)i * newZY[1], 
					mainEntitysmallfireball.posZ + (double)i * (diagonalFireballHorzOffset * offsetCos - diagonalFireballPoint.getX() * offsetSin),
					d0, d1, d2, 1.0F, fireBlastAttackPower);

			this.world.spawnEntity(entitysmallfireball);
		}

		newZY = ModMathFunctions.rotatePointGeneric2D(-diagonalFireballPoint.getZ(), diagonalFireballPoint.getY(), offsetAngleX);

		diagonalFireballHorzOffset = newZY[0];		

		for(int i = 1; i <= 2; i++)
		{
			EntityBloodmoonFireball entitysmallfireball = new EntityBloodmoonFireball(attackingMob.world, attackingMob, 
					mainEntitysmallfireball.posX + (double)i * (diagonalFireballHorzOffset * offsetSin - diagonalFireballPoint.getX() * offsetCos), 
					mainEntitysmallfireball.posY + (double)i * newZY[1], 
					mainEntitysmallfireball.posZ + (double)i * (diagonalFireballHorzOffset * offsetCos + diagonalFireballPoint.getX() * offsetSin),
					d0, d1, d2, 1.0F, fireBlastAttackPower);

			this.world.spawnEntity(entitysmallfireball);
		}

		/*
		System.out.println( "Test Fireball Coordinates for Fire Blast" );
		//System.out.println( "MainFireball: " + Double.toString( mainEntitysmallfireball.posX ) + ", " + Double.toString( mainEntitysmallfireball.posY ) + ", " + Double.toString( mainEntitysmallfireball.posZ ));
		System.out.println( "newZY[0]: " + Double.toString( newZY[0] ) );
		System.out.println( "newZY[1]: " + Double.toString( newZY[1] ) );
		System.out.println( "offsetAngleX: " + Double.toString( offsetAngleX ) );
		System.out.println( "offsetCos: " + Double.toString( offsetCos ) );
		System.out.println( "offsetSin: " + Double.toString( offsetSin ) );
		//System.out.println( "TopFireball Vector: " + Float.toString( topFireballPoint.getX()) + ", " + Float.toString( topFireballPoint.getY() ) + ", " + Float.toString( topFireballPoint.getZ()));
		System.out.println( "diagonalFireballHorzOffset * offsetCos: " + Double.toString( diagonalFireballHorzOffset * offsetCos ) );
		System.out.println( "diagonalFireballHorzOffset * offsetSin: " + Double.toString( diagonalFireballHorzOffset * offsetSin ) );
		System.out.println( " " );/**/
	}
/*
	public boolean isInLiquid()
	{
		int currentPosY = MathHelper.floor(this.posY);
		int currentPoxX = MathHelper.floor(this.posX);
		int currentPoxZ = MathHelper.floor(this.posZ);

		for(int i = 0; i < 2; i++)
		{
			this.isInWater()
			
			int j2 = currentPoxX;
			int k = currentPosY - i;
			int l = currentPoxZ;
			Block block = this.world.getBlock(j2, k, l);

			if (block instanceof BlockLiquid)
			{
				return true;
			}
		}

		return false;
	}*/

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
				if(damageSource.getTrueSource() instanceof EntityLiving)
				{
				if((damageSource.getTrueSource().getDistanceSq(this.posX, this.getEntityBoundingBox().minY, this.posZ) < 18D || 
						damageSource.getTrueSource().getDistanceSq(this.posX, this.getEntityBoundingBox().minY, this.posZ) > Math.pow(attackRange + 3F, 2D)) 
						&& flashCooldown == 0)
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
				else if(damageSource.getTrueSource() instanceof EntityLivingBase)
				{
					switchTargetToAttack(damageSource);
				}
			}

			return super.attackEntityFrom(damageSource, damageAmount);
		}
	}

	/**
	 * sets this entity's combat AI.
	 */
	public void switchTargetToAttack(DamageSource damageSource)
	{
		//this.setTarget((Entity)null);
		if(!(damageSource.getTrueSource() instanceof EntityPlayerMP) || !((EntityPlayerMP)damageSource.getTrueSource()).isCreative())
		{
			this.setAttackTarget((EntityLivingBase)damageSource.getTrueSource());
		}
	}

	/**
	 * sets this entity's combat AI.
	 */
	public void resetTargetToAttack()
	{
		this.setAttackTarget((EntityLivingBase)null);
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

	public void playAttackSound()
	{
		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_ATTACK, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
	}

	public void playExertSelfSound()
	{
		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_EXERT_SELF, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
	}

	public void playGaspSound()
	{
		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_GASP, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
	}

	public void playLaughSound()
	{
		this.playSound(KindredLegacySoundEvents.BLOODMOON_NINETALES_LAUGH, 1.0F, 1.0F + this.world.rand.nextFloat() * 0.2F);
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