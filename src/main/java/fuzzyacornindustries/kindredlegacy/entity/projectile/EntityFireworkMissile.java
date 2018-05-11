package fuzzyacornindustries.kindredlegacy.entity.projectile;

import java.util.List;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.reference.LibraryDyeColors;
import fuzzyacornindustries.kindredlegacy.reference.LibraryFireworks;
import fuzzyacornindustries.kindredlegacy.utility.UtilityTargeting;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFireworkMissile extends Entity implements IProjectile
{
	private static final DataParameter<Byte> CRITICAL = EntityDataManager.<Byte>createKey(EntityFireworkMissile.class, DataSerializers.BYTE);

	protected int inData, xTile = -1, yTile = -1, zTile = -1;

	private Block inTile;

	private boolean inGround;

	/** The owner of this arrow. */
	public Entity shootingEntity;

	/** Shooter's name, if shooter is a player - based on EntityThrowable's code */
	private String shooterName = null;

	private int ticksInAir;

	private double attackDamage = 2.0D;

	public EntityFireworkMissile(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	public EntityFireworkMissile(World world, double xPar, double yPar, double zPar)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
		this.setPosition(xPar, yPar, zPar);
	}

	public EntityFireworkMissile(World world, EntityLivingBase shooterEntity, EntityLivingBase targetEntity, float baseSpeed, float accuracy, float attackDamage)
	{
		super(world);

		this.shootingEntity = shooterEntity;

		this.posY = shooterEntity.posY + (double)shooterEntity.getEyeHeight() * 0.95D;
		double d0 = targetEntity.posX - shooterEntity.posX;
		double d1 = (targetEntity.getEntityBoundingBox().minY + (double)(targetEntity.height / 3.0F) - this.posY) * 1.1D;
		double d2 = targetEntity.posZ - shooterEntity.posZ;
		double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);

		if (d3 >= 1.0E-7D)
		{
			float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
			float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));

			//double d4 = d0 / d3;
			//double d5 = d2 / d3;

			this.setPosition(shooterEntity.posX, this.posY, shooterEntity.posZ);
			this.setLocationAndAngles(shooterEntity.posX, this.posY, shooterEntity.posZ, f2, f3);
			float f4 = (float)d3 * 0.15F;

			this.shoot(d0, d1 + (double)f4, d2, baseSpeed, accuracy);
		}

		this.attackDamage = attackDamage;

		//setTarget(targetEntity);
	}

	public EntityFireworkMissile(World world, EntityLivingBase shooterEntity, float baseSpeed)
	{
		super(world);

		this.shootingEntity = shooterEntity;

		this.setSize(0.5F, 0.5F);
		this.setPosition(shooterEntity.posX, shooterEntity.posY + (double)shooterEntity.getEyeHeight() * 0.95D, shooterEntity.posZ);

		this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
		this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));

		this.shoot(this.motionX, this.motionY, this.motionZ, baseSpeed * 1.5F, 1.0F);
	}

	@Override
	protected void entityInit()
	{
		this.dataManager.register(CRITICAL, Byte.valueOf((byte)0));
	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double distance)
	{
		double d0 = this.getEntityBoundingBox().getAverageEdgeLength() * 10.0D;

		if (Double.isNaN(d0))
		{
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D * getRenderDistanceWeight();
		return distance < d0 * d0;
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	@Override
	public void shoot(double xDirection, double yDirection, double zDirection, float baseSpeed, float accuracy)
	{
		float vectorMagnitude = MathHelper.sqrt(xDirection * xDirection + yDirection * yDirection + zDirection * zDirection);

		xDirection /= (double)vectorMagnitude;
		yDirection /= (double)vectorMagnitude;
		zDirection /= (double)vectorMagnitude;

		xDirection += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)accuracy;
		yDirection += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)accuracy;
		zDirection += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)accuracy;

		xDirection *= (double)baseSpeed;
		yDirection *= (double)baseSpeed;
		zDirection *= (double)baseSpeed;

		this.motionX = xDirection;
		this.motionY = yDirection;
		this.motionZ = zDirection;

		float f3 = MathHelper.sqrt(xDirection * xDirection + zDirection * zDirection);
		this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(xDirection, zDirection) * 180.0D / Math.PI);
		this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(yDirection, (double)f3) * 180.0D / Math.PI);
	}

	/**
	 * Set the position and rotation values directly without any clamping.
	 */
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
	{
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void setVelocity(double xVelocity, double yVelocity, double zVelocity)
	{
		this.motionX = xVelocity;
		this.motionY = yVelocity;
		this.motionZ = zVelocity;

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float horizontalMagnitude = MathHelper.sqrt(xVelocity * xVelocity + zVelocity * zVelocity);
			this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(xVelocity, zVelocity) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(yVelocity, (double)horizontalMagnitude) * 180.0D / Math.PI);
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;

			this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
		}
	}

	/**
	 * Returns the shootingEntity, or if null, tries to get the shooting player from the world based on shooterName, if available
	 */
	public Entity getShooter() 
	{
		if (shootingEntity == null && shooterName != null) 
		{
			shootingEntity = world.getPlayerEntityByName(shooterName);
		}

		return shootingEntity;
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();

		updateAngles();
		checkInGround();

		if (inGround) 
		{
			//this.world.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, false);

			this.setDead();
			
			if (!this.world.isRemote)
			{
				this.world.setEntityState(this, (byte)17);
			}
		} 
		else 
		{
			updateInAir();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void handleStatusUpdate(byte bytePar)
	{
		if (bytePar == 17 && this.world.isRemote)
		{
			ItemStack fireworkItemStack = generateFirework();
			NBTTagCompound nbttagcompound5 = null;

			if (fireworkItemStack != null && fireworkItemStack.hasTagCompound())
			{
				nbttagcompound5 = fireworkItemStack.getTagCompound().getCompoundTag("Fireworks");

				//System.out.println( "Test Firework Missile Run" );
				//System.out.println( Integer.toString( targetEntity.getEntityId() ) );
			}
			
			this.world.makeFireworks(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, nbttagcompound5);
		}

		super.handleStatusUpdate(bytePar);
	}

	/**
	 * Checks if entity is colliding with a block and if so, sets inGround to true
	 */
	protected void checkInGround() 
	{
		BlockPos blockpos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iblockstate = this.world.getBlockState(blockpos);
		Block block = iblockstate.getBlock();

		if (iblockstate.getMaterial() != Material.AIR)
		{
			AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockpos);

			if (axisalignedbb != Block.NULL_AABB && axisalignedbb.offset(blockpos).contains(new Vec3d(this.posX, this.posY, this.posZ)))
			{
				this.inGround = true;
			}
		}
	}

	/**
	 * Adjusts rocket's motion: multiplies each by factor, subtracts adjustY from motionY
	 */
	protected void updateMotion(float factor, float adjustY) 
	{
		motionX *= (double) factor;
		motionY *= (double) factor;
		motionZ *= (double) factor;
		motionY -= (double) adjustY;
	}

	/**
	 * Updates yaw and pitch based on current motion
	 */
	protected void updateAngles() 
	{
		if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) 
		{
			float f = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);
			prevRotationPitch = rotationPitch = (float)(Math.atan2(motionY, (double) f) * 180.0D / Math.PI);
		}
	}

	/**
	 * Updates the rocket's position and angles
	 */
	protected void updatePosition() 
	{
		posX += motionX;
		posY += motionY;
		posZ += motionZ;

		float f = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / Math.PI);

		for (rotationPitch = (float)(Math.atan2(motionY, (double) f) * 180.0D / Math.PI); rotationPitch - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F)
		{ ; }

		while (rotationPitch - prevRotationPitch >= 180.0F)
		{ prevRotationPitch += 360.0F; }

		while (rotationYaw - prevRotationYaw < -180.0F)
		{ prevRotationYaw -= 360.0F; }

		while (rotationYaw - prevRotationYaw >= 180.0F)
		{ prevRotationYaw += 360.0F; }

		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;

		float motionFactor = 0.99F;

		if (isInWater()) 
		{
			for (int i = 0; i < 4; ++i) 
			{
				float f3 = 0.25F;
				this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ, new int[0]);
			}

			motionFactor = 0.8F;
		}

		updateMotion(motionFactor, getGravityVelocity());
		setPosition(posX, posY, posZ);
	}

	/**
	 * Checks for impacts, spawns trailing particles and updates entity position
	 */
	protected void updateInAir() 
	{
		++ticksInAir;
		RayTraceResult raytraceresult = UtilityTargeting.checkForImpact(world, this, getShooter(), 0.3D, ticksInAir >= 5);

		if (raytraceresult != null) 
		{
			onImpact(raytraceresult);
		}

		spawnTrailingParticles();
		updatePosition();
		this.doBlockCollisions();
	}

	/**
	 * Called when custom rocket impacts an entity or block
	 */
	protected void onImpact(RayTraceResult mop) 
	{
		if (mop.entityHit != null) 
		{
			onImpactEntity(mop);
		} 
		else 
		{
			onImpactBlock(mop);
		}
	}

	/**
	 * Called when custom rocket impacts a block
	 */
	protected void onImpactBlock(RayTraceResult raytraceresult) 
	{
		BlockPos blockpos = raytraceresult.getBlockPos();
		this.xTile = blockpos.getX();
		this.yTile = blockpos.getY();
		this.zTile = blockpos.getZ();
		IBlockState iblockstate = this.world.getBlockState(blockpos);
		this.inTile = iblockstate.getBlock();
		this.inData = this.inTile.getMetaFromState(iblockstate);
		this.motionX = (double)((float)(raytraceresult.hitVec.x - this.posX));
		this.motionY = (double)((float)(raytraceresult.hitVec.y - this.posY));
		this.motionZ = (double)((float)(raytraceresult.hitVec.z - this.posZ));
		float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
		this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
		this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
		this.inGround = true;

		if (iblockstate.getMaterial() != Material.AIR)
		{
			this.inTile.onEntityCollidedWithBlock(this.world, blockpos, iblockstate, this);
		}
	}

	/**
	 * Called when custom rocket impacts another entity
	 */
	protected void onImpactEntity(RayTraceResult raytraceresult) 
	{
		if (raytraceresult.entityHit != null && !(raytraceresult.entityHit instanceof TamablePokemon) && raytraceresult.entityHit != this.shootingEntity && !(raytraceresult.entityHit instanceof EntityPlayer)) 
		{
			shootingEntity = getShooter();

			if (raytraceresult.entityHit.attackEntityFrom(getDamageSource(raytraceresult.entityHit), (float) this.attackDamage)) 
			{
				if (canTargetEntity(raytraceresult.entityHit)) 
				{
					setDead();
				}
			} 
			else 
			{
				setDead();
			}

			this.world.newExplosion(this, this.posX, this.posY, this.posZ, 1.0F, false, false);

			if (!this.world.isRemote)
			{
				this.world.setEntityState(this, (byte)17);
			}
		}
	}

	/** Returns whether this rocket can target the entity; normally used for Endermen */
	protected boolean canTargetEntity(Entity entity) 
	{
		return true; //(!(entity instanceof EntityEnderman));
	}

	/** Returns the damage source this rocket will use against the entity struck */ 
	protected DamageSource getDamageSource(Entity entity) 
	{
		return new EntityDamageSourceIndirect("fireworks", this, shootingEntity).setProjectile();
	}

	/** Returns the rockets's velocity factor */
	protected float getVelocityFactor() 
	{
		return 1.5F;
	}

	/** Default gravity adjustment for arrows seems to be 0.05F */
	protected float getGravityVelocity() 
	{
		return 0.05F;
	}

	/**
	 * Spawns trailing particles, if any
	 */
	protected void spawnTrailingParticles() 
	{
		for (int i = 0; i < 2; ++i) 
		{
			this.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, this.posX, this.posY - 0.3D, this.posZ, this.rand.nextGaussian() * 0.05D, -this.motionY * 0.5D, this.rand.nextGaussian() * 0.05D);
		}
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setShort("xTile", (short)this.xTile);
		nbttagcompound.setShort("yTile", (short)this.yTile);
		nbttagcompound.setShort("zTile", (short)this.zTile);
		nbttagcompound.setByte("inTile", (byte)Block.getIdFromBlock(this.inTile));
		nbttagcompound.setByte("inData", (byte)this.inData);
		nbttagcompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
		nbttagcompound.setDouble("damage", this.attackDamage);

		nbttagcompound.setString("shooter", shooterName == null ? "" : shooterName);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inTile = Block.getBlockById(nbttagcompound.getByte("inTile") & 255);
		this.inData = nbttagcompound.getByte("inData") & 255;
		this.inGround = nbttagcompound.getByte("inGround") == 1;

		if (nbttagcompound.hasKey("damage", 99))
		{
			this.attackDamage = nbttagcompound.getDouble("damage");
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	public void setDamage(double damagePower)
	{
		this.attackDamage = damagePower;
	}
	
	public double getDamage()
	{
		return this.attackDamage;
	}

	public ItemStack generateFirework()
	{
		NBTTagCompound nbttagcompound;
		NBTTagCompound nbttagcompound1;

		ItemStack fireworkChargeItemStack = new ItemStack(Items.FIREWORK_CHARGE);

		nbttagcompound = new NBTTagCompound();

		nbttagcompound1 = new NBTTagCompound();
		NBTTagList nbttaglist = new NBTTagList();

        List<Integer> list1 = Lists.<Integer>newArrayList();

        list1 = getFireworkColorList();

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

	public List<Integer> getFireworkColorList()
	{
		List<Integer> arraylist = Lists.<Integer>newArrayList();

		ItemStack tempItemStack;

		switch (this.rand.nextInt(7))
		{
		case 0:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.ROSE_RED);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.ORANGE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.DANDELION_YELLOW);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 1:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.DANDELION_YELLOW);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.PURPLE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 2:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.ROSE_RED);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.CACTUS_GREEN);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 3:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.LAPIS_LAZULI);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.PURPLE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.LIGHT_BLUE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 4:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.LAPIS_LAZULI);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.PURPLE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.CACTUS_GREEN);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 5:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.BONE_MEAL);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.PINK_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.ROSE_RED);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 6:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.ROSE_RED);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.DANDELION_YELLOW);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.LAPIS_LAZULI);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
			break;
		case 7:
			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.CACTUS_GREEN);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.PURPLE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));

			tempItemStack = new ItemStack(Items.DYE, 1, LibraryDyeColors.ORANGE_DYE);
			arraylist.add(Integer.valueOf(ItemDye.DYE_COLORS[tempItemStack.getMetadata() & 15]));
		}

		return arraylist;
	}

	public byte getExplosionStyle()
	{
		int k = this.rand.nextInt(10);

		if(k >= 6)
		{
			return LibraryFireworks.SHAPE_NORMAL;
		}
		else if(k == 1 || k == 2)
		{
			return LibraryFireworks.SHAPE_STAR;
		}
		else if(k == 0)
		{
			return LibraryFireworks.SHAPE_LARGE;
		}
		else
		{
			return LibraryFireworks.SHAPE_BURST;
		}
	}

	public NBTTagCompound applySpecialEffect(NBTTagCompound nbttagcompound)
	{
		int k = this.rand.nextInt(3);

		if(k == 1)
		{
			nbttagcompound.setBoolean("Flicker", true);
		}

		k = this.rand.nextInt(6);

		if(k == 1)
		{
			nbttagcompound.setBoolean("Trail", true);
		}  

		return nbttagcompound;
	}
}