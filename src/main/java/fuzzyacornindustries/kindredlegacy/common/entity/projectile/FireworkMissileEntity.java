package fuzzyacornindustries.kindredlegacy.common.entity.projectile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.lib.FireworksLib;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class FireworkMissileEntity extends ThrowableEntity 
{
	private static final DataParameter<Byte> CRITICAL = EntityDataManager.createKey(FireworkMissileEntity.class, DataSerializers.BYTE);
	protected static final DataParameter<Optional<UUID>> field_212362_a = EntityDataManager.createKey(FireworkMissileEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	//private static final DataParameter<Byte> PIERCE_LEVEL = EntityDataManager.createKey(FireworkMissileEntity.class, DataSerializers.BYTE);
	//@Nullable
	//private BlockState inBlockState;
	protected boolean inGround;
	//protected int timeInGround;
	//public FireworkMissileEntity.PickupStatus pickupStatus = FireworkMissileEntity.PickupStatus.DISALLOWED;
	//public int arrowShake;
	public UUID shootingEntity;
	//private int ticksInGround;
	private int ticksInAir;
	private double damage = 2.0D;
	//private int knockbackStrength;
	//private SoundEvent hitSound = this.getHitEntitySound();
	//private IntOpenHashSet piercedEntities;
	//private List<Entity> hitEntities;

	public FirecrackerLittenEntity firecrackerLitten;

	public FireworkMissileEntity(EntityType<FireworkMissileEntity> type, World worldIn) 
	{
		super(type, worldIn);
	}

	public FireworkMissileEntity(double x, double y, double z, World worldIn) 
	{
		this(ModEntities.FIREWORK_MISSILE.get(), worldIn);
		this.setPosition(x, y, z);
	}

	public FireworkMissileEntity(FirecrackerLittenEntity shooter, World worldIn) 
	{
		this(shooter.getPosX(), shooter.getPosYEye() - (double)0.1F, shooter.getPosZ(), worldIn);

		this.firecrackerLitten = shooter;
		this.setShooter(shooter);
		//		if (shooter instanceof PlayerEntity) {
		//			this.pickupStatus = FireworkMissileEntity.PickupStatus.ALLOWED;
		//		}


	}

	//	public void setHitSound(SoundEvent soundIn) 
	//	{
	//		this.hitSound = soundIn;
	//	}

	/**
	 * Checks if the entity is in range to render.
	 */
	@OnlyIn(Dist.CLIENT)
	public boolean isInRangeToRenderDist(double distance) 
	{
		double d0 = this.getBoundingBox().getAverageEdgeLength() * 10.0D;
		if (Double.isNaN(d0)) 
		{
			d0 = 1.0D;
		}

		d0 = d0 * 64.0D * getRenderDistanceWeight();
		return distance < d0 * d0;
	}

	@Override
	protected void registerData() 
	{
		this.dataManager.register(CRITICAL, (byte)0);
		this.dataManager.register(field_212362_a, Optional.empty());
		//this.dataManager.register(PIERCE_LEVEL, (byte)0);
	}

	@Override
	public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy) 
	{
		float f = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
		float f1 = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
		float f2 = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
		this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
		this.setMotion(this.getMotion().add(shooter.getMotion().x, shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) 
	{
		Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy, this.rand.nextGaussian() * (double)0.0075F * (double)inaccuracy).scale((double)velocity);
		this.setMotion(vec3d);
		float f = MathHelper.sqrt(horizontalMag(vec3d));
		this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
		this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI));
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
		//this.ticksInGround = 0;
	}

	/**
	 * Sets a target for the client to interpolate towards over the next few ticks
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport) 
	{
		this.setPosition(x, y, z);
		this.setRotation(yaw, pitch);
	}

	/**
	 * Updates the entity motion clientside, called by packets from the server
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public void setVelocity(double x, double y, double z) 
	{
		this.setMotion(x, y, z);
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) 
		{
			float f = MathHelper.sqrt(x * x + z * z);
			this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * (double)(180F / (float)Math.PI));
			this.rotationYaw = (float)(MathHelper.atan2(x, z) * (double)(180F / (float)Math.PI));
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			this.setLocationAndAngles(this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch);
			//this.ticksInGround = 0;
		}

	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void tick() 
	{
		super.tick();
		boolean flag = this.getNoClip();
		Vec3d vec3d = this.getMotion();
		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) 
		{
			float f = MathHelper.sqrt(horizontalMag(vec3d));
			this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * (double)(180F / (float)Math.PI));
			this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * (double)(180F / (float)Math.PI));
			this.prevRotationYaw = this.rotationYaw;
			this.prevRotationPitch = this.rotationPitch;
		}

		BlockPos blockpos = new BlockPos(this);
		BlockState blockstate = this.world.getBlockState(blockpos);
		if (!blockstate.isAir(this.world, blockpos) && !flag) 
		{
			VoxelShape voxelshape = blockstate.getCollisionShape(this.world, blockpos);
			if (!voxelshape.isEmpty()) 
			{
				Vec3d vec3d1 = this.getPositionVec();

				for(AxisAlignedBB axisalignedbb : voxelshape.toBoundingBoxList()) 
				{
					if (axisalignedbb.offset(blockpos).contains(vec3d1)) 
					{
						this.inGround = true;
						break;
					}
				}
			}
		}

		//		 if (this.arrowShake > 0) {
		//			 --this.arrowShake;
		//		 }

		//		 if (this.isWet()) 
		//		 {
		//			 this.extinguish();
		//		 }

		//		if (this.inGround && !flag) 
		//		{
		//			if (this.inBlockState != blockstate && this.world.func_226664_a_(this.getBoundingBox().grow(0.06D))) 
		//			{
		//				this.inGround = false;
		//				this.setMotion(vec3d.mul((double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F), (double)(this.rand.nextFloat() * 0.2F)));
		//				//this.ticksInGround = 0;
		//				this.ticksInAir = 0;
		//			} 
		//			else if (!this.world.isRemote) 
		//			{
		//				this.func_225516_i_();
		//			}
		//
		//			//++this.timeInGround;
		//
		//			if (!this.world.isRemote)
		//			{
		//				this.world.setEntityState(this, (byte)17);
		//			}
		//		} 
		//		else 
		//		{
		//this.timeInGround = 0;
		++this.ticksInAir;
		Vec3d vec3d2 = this.getPositionVec();
		Vec3d vec3d3 = vec3d2.add(vec3d);
		RayTraceResult raytraceresult = this.world.rayTraceBlocks(new RayTraceContext(vec3d2, vec3d3, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this));
		if (raytraceresult.getType() != RayTraceResult.Type.MISS) 
		{
			vec3d3 = raytraceresult.getHitVec();
		}

		//			while(!this.removed) 
		//			{
		EntityRayTraceResult entityraytraceresult = this.rayTraceEntities(vec3d2, vec3d3);
		if (entityraytraceresult != null) 
		{
			raytraceresult = entityraytraceresult;
		}

		if (raytraceresult != null && raytraceresult.getType() == RayTraceResult.Type.ENTITY) 
		{
			Entity entity = ((EntityRayTraceResult)raytraceresult).getEntity();
			Entity entity1 = this.getShooter();
			if (entity instanceof PlayerEntity && entity1 instanceof PlayerEntity && !((PlayerEntity)entity1).canAttackPlayer((PlayerEntity)entity)) 
			{
				raytraceresult = null;
				entityraytraceresult = null;
			}
		}

		if (raytraceresult != null && raytraceresult.getType() != RayTraceResult.Type.MISS && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult) && !flag)
		{
			this.onImpact(raytraceresult);
			this.isAirBorne = true;
		}

		//				if (entityraytraceresult == null)// || this.getPierceLevel() <= 0) 
		//				{
		//					break;
		//				}

		//raytraceresult = null;
		//}

		vec3d = this.getMotion();
		double d3 = vec3d.x;
		double d4 = vec3d.y;
		double d0 = vec3d.z;
		//			if (this.getIsCritical()) 
		//			{
		//				for(int i = 0; i < 4; ++i) 
		//				{
		//					this.world.addParticle(ParticleTypes.CRIT, this.getPosX() + d3 * (double)i / 4.0D, this.getPosY() + d4 * (double)i / 4.0D, this.getPosZ() + d0 * (double)i / 4.0D, -d3, -d4 + 0.2D, -d0);
		//				}
		//			}

		for(int i = 0; i < 4; ++i) 
		{
			this.world.addParticle(ParticleTypes.FIREWORK, this.getPosX() + d3 * (double)i / 4.0D, this.getPosY() + d4 * (double)i / 4.0D, this.getPosZ() + d0 * (double)i / 4.0D, -d3 * 0.25F, -d4 + 0.2D, -d0 * 0.25F);
		}

		double d5 = this.getPosX() + d3;
		double d1 = this.getPosY() + d4;
		double d2 = this.getPosZ() + d0;
		float f1 = MathHelper.sqrt(horizontalMag(vec3d));
		//			if (flag) 
		//			{
		//				this.rotationYaw = (float)(MathHelper.atan2(-d3, -d0) * (double)(180F / (float)Math.PI));
		//			} 
		//			else 
		//			{
		this.rotationYaw = (float)(MathHelper.atan2(d3, d0) * (double)(180F / (float)Math.PI));
		//}

		for(this.rotationPitch = (float)(MathHelper.atan2(d4, (double)f1) * (double)(180F / (float)Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) 
		{
			;
		}

		while(this.rotationPitch - this.prevRotationPitch >= 180.0F) 
		{
			this.prevRotationPitch += 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw < -180.0F) 
		{
			this.prevRotationYaw -= 360.0F;
		}

		while(this.rotationYaw - this.prevRotationYaw >= 180.0F) 
		{
			this.prevRotationYaw += 360.0F;
		}

		this.rotationPitch = MathHelper.lerp(0.2F, this.prevRotationPitch, this.rotationPitch);
		this.rotationYaw = MathHelper.lerp(0.2F, this.prevRotationYaw, this.rotationYaw);
		float f2 = 0.99F;
		//float f3 = 0.05F;
		if (this.isInWater()) 
		{
			for(int j = 0; j < 4; ++j) 
			{
				//float f4 = 0.25F;
				this.world.addParticle(ParticleTypes.BUBBLE, d5 - d3 * 0.25D, d1 - d4 * 0.25D, d2 - d0 * 0.25D, d3, d4, d0);
			}

			f2 = this.getWaterDrag();
		}

		this.setMotion(vec3d.scale((double)f2));
		if (!this.hasNoGravity() && !flag) 
		{
			Vec3d vec3d4 = this.getMotion();
			this.setMotion(vec3d4.x, vec3d4.y - (double)0.05F, vec3d4.z);
		}

		this.setPosition(d5, d1, d2);


		this.func_225516_i_();
		//}
	}

	protected void func_225516_i_() 
	{
		//		 ++this.ticksInGround;
		//		 if (this.ticksInGround >= 1200) {
		//			 this.remove();
		//		 }

		if(this.inGround)
		{
			if (!this.world.isRemote)
			{
				this.world.setEntityState(this, (byte)17);
			}

			this.remove();
		}
		else if(this.ticksExisted > 1000)
			this.remove();
	}

	/**
	 * Called when the arrow hits a block or an entity
	 */
	@Override
	public void onImpact(RayTraceResult raytraceResultIn) 
	{
		RayTraceResult.Type raytraceresult$type = raytraceResultIn.getType();
		if (raytraceresult$type == RayTraceResult.Type.ENTITY) 
		{
			this.onEntityHit((EntityRayTraceResult)raytraceResultIn);
		} 
		//		else if (raytraceresult$type == RayTraceResult.Type.BLOCK) 
		//		{
		//			BlockRayTraceResult blockraytraceresult = (BlockRayTraceResult)raytraceResultIn;
		//			BlockState blockstate = this.world.getBlockState(blockraytraceresult.getPos());
		//			this.inBlockState = blockstate;
		//			Vec3d vec3d = blockraytraceresult.getHitVec().subtract(this.getPosX(), this.getPosY(), this.getPosZ());
		//			this.setMotion(vec3d);
		//			Vec3d vec3d1 = vec3d.normalize().scale((double)0.05F);
		//			this.setRawPosition(this.getPosX() - vec3d1.x, this.getPosY() - vec3d1.y, this.getPosZ() - vec3d1.z);
		//			//this.playSound(this.getHitGroundSound(), 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
		//			this.inGround = true;
		//			//this.arrowShake = 7;
		//			//this.setIsCritical(false);
		//			//this.setPierceLevel((byte)0);
		//			//this.setHitSound(SoundEvents.ENTITY_ARROW_HIT);
		//			//this.setShotFromCrossbow(false);
		//			this.func_213870_w();
		//			blockstate.onProjectileCollision(this.world, blockstate, blockraytraceresult, this);
		//
		//			if (!this.world.isRemote)
		//			{
		//				this.world.setEntityState(this, (byte)17);
		//			}
		//		}


	}

	/**
	 * Called when the arrow hits an entity
	 */
	protected void onEntityHit(EntityRayTraceResult p_213868_1_) 
	{
		Entity hitEntity = p_213868_1_.getEntity();
		//Entity shooterEntity = this.getShooter();
		//DamageSource damagesource;
		
		//float f = (float)this.getMotion().length();
		//int i = MathHelper.ceil(Math.max((double)f * this.damage, 0.0D));

		if (hitEntity != null && !(hitEntity instanceof TamablePokemon) && hitEntity != this.firecrackerLitten && !(hitEntity instanceof PlayerEntity)) 
		{
			if(hitEntity instanceof LivingEntity && this.firecrackerLitten != null && this.firecrackerLitten.getOwner() != null && ((LivingEntity)hitEntity).getHealth() <= this.getDamage())
			{
				hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this.firecrackerLitten.getOwner()), (float)this.getDamage());

			}
			else if(hitEntity.attackEntityFrom(DamageSource.causeMobDamage(this.firecrackerLitten), (float)this.getDamage()))
			{
			}

			this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), 1.0F, false, Explosion.Mode.NONE);

			if (!this.world.isRemote)
			{
				this.world.setEntityState(this, (byte)17);
			}

			this.remove();
		}
//
//		if (shooterEntity == null) 
//		{
//			damagesource = DamageSource.causeMobDamage((LivingEntity) this.getShooter());
//		} 
//		else 
//		{
//			damagesource = DamageSource.causeMobDamage((LivingEntity) this.getShooter());
//			if (shooterEntity instanceof LivingEntity) 
//			{
//				((LivingEntity)shooterEntity).setLastAttackedEntity(hitEntity);
//			}
//		}

//		if (hitEntity.attackEntityFrom(damagesource, (float)i)) 
//		{
//			if (hitEntity instanceof LivingEntity) 
//			{
//				LivingEntity livingentity = (LivingEntity)hitEntity;
//				
//				if (shooterEntity != null && livingentity != shooterEntity && livingentity instanceof PlayerEntity && shooterEntity instanceof ServerPlayerEntity) 
//				{
//					((ServerPlayerEntity)shooterEntity).connection.sendPacket(new SChangeGameStatePacket(6, 0.0F));
//				}
//
//				if (!hitEntity.isAlive() && this.hitEntities != null) 
//				{
//					this.hitEntities.add(livingentity);
//				}
//			}
//
//			this.remove();
//		} 
//		else 
//		{
//			//entity.setFireTimer(j);
//			this.setMotion(this.getMotion().scale(-0.1D));
//			this.rotationYaw += 180.0F;
//			this.prevRotationYaw += 180.0F;
//			this.ticksInAir = 0;
//			if (!this.world.isRemote && this.getMotion().lengthSquared() < 1.0E-7D) 
//			{
//				//				 if (this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED) {
//				//					 this.entityDropItem(this.getArrowStack(), 0.1F);
//				//				 }
//
//				this.remove();
//			}
//		}
	}

	/**
	 * Gets the EntityRayTraceResult representing the entity hit
	 */
	@Nullable
	protected EntityRayTraceResult rayTraceEntities(Vec3d startVec, Vec3d endVec) 
	{
		return ProjectileHelper.rayTraceEntities(this.world, this, startVec, endVec, this.getBoundingBox().expand(this.getMotion()).grow(1.0D), (p_213871_1_) -> {
			return !p_213871_1_.isSpectator() && p_213871_1_.isAlive() && p_213871_1_.canBeCollidedWith() && (p_213871_1_ != this.getShooter() || this.ticksInAir >= 5);
		});
		// && (this.piercedEntities == null || !this.piercedEntities.contains(p_213871_1_.getEntityId()))
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		//compound.putShort("life", (short)this.ticksInGround);
		//		if (this.inBlockState != null) 
		//		{
		//			compound.put("inBlockState", NBTUtil.writeBlockState(this.inBlockState));
		//		}

		//compound.putByte("shake", (byte)this.arrowShake);
		compound.putBoolean("inGround", this.inGround);
		//compound.putByte("pickup", (byte)this.pickupStatus.ordinal());
		compound.putDouble("damage", this.damage);
		//compound.putBoolean("crit", this.getIsCritical());
		//compound.putByte("PierceLevel", this.getPierceLevel());
		if (this.shootingEntity != null) 
		{
			compound.putUniqueId("OwnerUUID", this.shootingEntity);
		}

		//compound.putString("SoundEvent", Registry.SOUND_EVENT.getKey(this.hitSound).toString());
		//compound.putBoolean("ShotFromCrossbow", this.getShotFromCrossbow());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		//this.ticksInGround = compound.getShort("life");
		//		if (compound.contains("inBlockState", 10)) {
		//			this.inBlockState = NBTUtil.readBlockState(compound.getCompound("inBlockState"));
		//		}

		//this.arrowShake = compound.getByte("shake") & 255;
		this.inGround = compound.getBoolean("inGround");
		if (compound.contains("damage", 99)) {
			this.damage = compound.getDouble("damage");
		}

		//		 if (compound.contains("pickup", 99)) {
		//			 this.pickupStatus = AbstractArrowEntity.PickupStatus.getByOrdinal(compound.getByte("pickup"));
		//		 } else if (compound.contains("player", 99)) {
		//			 this.pickupStatus = compound.getBoolean("player") ? AbstractArrowEntity.PickupStatus.ALLOWED : AbstractArrowEntity.PickupStatus.DISALLOWED;
		//		 }

		//this.setIsCritical(compound.getBoolean("crit"));
		//this.setPierceLevel(compound.getByte("PierceLevel"));
		if (compound.hasUniqueId("OwnerUUID")) 
		{
			this.shootingEntity = compound.getUniqueId("OwnerUUID");
		}

		//		 if (compound.contains("SoundEvent", 8)) 
		//		 {
		//			 this.hitSound = Registry.SOUND_EVENT.getValue(new ResourceLocation(compound.getString("SoundEvent"))).orElse(this.getHitEntitySound());
		//		 }

		//		 this.setShotFromCrossbow(compound.getBoolean("ShotFromCrossbow"));
	}

	public void setShooter(@Nullable Entity entityIn) 
	{
		this.shootingEntity = entityIn == null ? null : entityIn.getUniqueID();
		if (entityIn instanceof PlayerEntity) 
		{
			//this.pickupStatus = ((PlayerEntity)entityIn).abilities.isCreativeMode ? AbstractArrowEntity.PickupStatus.CREATIVE_ONLY : AbstractArrowEntity.PickupStatus.ALLOWED;
		}

	}

	@Nullable
	public Entity getShooter() 
	{
		return this.shootingEntity != null && this.world instanceof ServerWorld ? ((ServerWorld)this.world).getEntityByUuid(this.shootingEntity) : null;
	}

	//	 /**
	//	  * Called by a player entity when they collide with an entity
	//	  */
	//	 public void onCollideWithPlayer(PlayerEntity entityIn) 
	//	 {
	//		 if (!this.world.isRemote && (this.inGround || this.getNoClip()) && this.arrowShake <= 0) {
	////			 boolean flag = this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED || this.pickupStatus == AbstractArrowEntity.PickupStatus.CREATIVE_ONLY && entityIn.abilities.isCreativeMode || this.getNoClip() && this.getShooter().getUniqueID() == entityIn.getUniqueID();
	////			 if (this.pickupStatus == AbstractArrowEntity.PickupStatus.ALLOWED && !entityIn.inventory.addItemStackToInventory(this.getArrowStack())) {
	////				 flag = false;
	////			 }
	//
	////			 if (flag) {
	////				 entityIn.onItemPickup(this, 1);
	////				 this.remove();
	////			 }
	//
	//		 }
	//	 }

	//protected abstract ItemStack getArrowStack();

	@Override
	protected boolean canTriggerWalking() 
	{
		return false;
	}

	public void setDamage(double damageIn) 
	{
		this.damage = damageIn;
	}

	public double getDamage() 
	{
		return this.damage;
	}

	//	 /**
	//	  * Sets the amount of knockback the arrow applies when it hits a mob.
	//	  */
	//	 public void setKnockbackStrength(int knockbackStrengthIn) {
	//		 this.knockbackStrength = knockbackStrengthIn;
	//	 }

	/**
	 * Returns true if it's possible to attack this entity with an item.
	 */
	@Override
	public boolean canBeAttackedWithItem() 
	{
		return false;
	}

	@Override
	protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) 
	{
		return 0.0F;
	}

	//	 /**
	//	  * Whether the arrow has a stream of critical hit particles flying behind it.
	//	  */
	//	 public void setIsCritical(boolean critical) {
	//		 this.setArrowFlag(1, critical);
	//	 }

	//	 public void setPierceLevel(byte level) {
	//		 this.dataManager.set(PIERCE_LEVEL, level);
	//	 }

	private void setArrowFlag(int p_203049_1_, boolean p_203049_2_) 
	{
		byte b0 = this.dataManager.get(CRITICAL);
		if (p_203049_2_) {
			this.dataManager.set(CRITICAL, (byte)(b0 | p_203049_1_));
		} else {
			this.dataManager.set(CRITICAL, (byte)(b0 & ~p_203049_1_));
		}

	}

	//	/**
	//	 * Whether the arrow has a stream of critical hit particles flying behind it.
	//	 */
	//	public boolean getIsCritical() 
	//	{
	//		byte b0 = this.dataManager.get(CRITICAL);
	//		return (b0 & 1) != 0;
	//	}

	//	 /**
	//	  * Whether the arrow was shot from a crossbow.
	//	  */
	//	 public boolean getShotFromCrossbow() {
	//		 byte b0 = this.dataManager.get(CRITICAL);
	//		 return (b0 & 4) != 0;
	//	 }

	//	 public byte getPierceLevel() {
	//		 return this.dataManager.get(PIERCE_LEVEL);
	//	 }

	//	 public void setEnchantmentEffectsFromEntity(LivingEntity p_190547_1_, float p_190547_2_) 
	//	 {
	//		 int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.POWER, p_190547_1_);
	//		 int j = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.PUNCH, p_190547_1_);
	//		 this.setDamage((double)(p_190547_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.world.getDifficulty().getId() * 0.11F));
	//		 if (i > 0) {
	//			 this.setDamage(this.getDamage() + (double)i * 0.5D + 0.5D);
	//		 }
	//
	//		 if (j > 0) {
	//			 this.setKnockbackStrength(j);
	//		 }
	//
	//		 if (EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FLAME, p_190547_1_) > 0) {
	//			 this.setFire(100);
	//		 }
	//
	//	 }

	protected float getWaterDrag() 
	{
		return 0.6F;
	}

	/**
	 * Sets if this arrow can noClip
	 */
	public void setNoClip(boolean noClipIn) 
	{
		this.noClip = noClipIn;
		this.setArrowFlag(2, noClipIn);
	}

	/**
	 * Whether the arrow can noClip
	 */
	public boolean getNoClip() 
	{
		if (!this.world.isRemote) 
		{
			return this.noClip;
		} 
		else 
		{
			return (this.dataManager.get(CRITICAL) & 2) != 0;
		}
	}

	//	 /**
	//	  * Sets data about if this arrow entity was shot from a crossbow
	//	  */
	//	 public void setShotFromCrossbow(boolean fromCrossbow) {
	//		 this.setArrowFlag(4, fromCrossbow);
	//	 }
	//
	//	 public IPacket<?> createSpawnPacket() {
	//		 Entity entity = this.getShooter();
	//		 return new SSpawnObjectPacket(this, entity == null ? 0 : entity.getEntityId());
	//	 }

	//	 public static enum PickupStatus {
	//		 DISALLOWED,
	//		 ALLOWED,
	//		 CREATIVE_ONLY;
	//
	////		 public static AbstractArrowEntity.PickupStatus getByOrdinal(int ordinal) {
	////			 if (ordinal < 0 || ordinal > values().length) {
	////				 ordinal = 0;
	////			 }
	////
	////			 return values()[ordinal];
	////		 }
	//	 }

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleStatusUpdate(byte bytePar)
	{
		if (bytePar == 17 && this.world.isRemote)
		{
			ItemStack fireworkItemStack = generateFirework();
			CompoundNBT compoundnbt = null;

			if (fireworkItemStack != null && fireworkItemStack.hasTag())
			{
				compoundnbt = fireworkItemStack.getChildTag("Fireworks");

				//System.out.println( "Test Firework Missile Run" );
				//System.out.println( Integer.toString( targetEntity.getEntityId() ) );
			}

			//this.world.makeFireworks(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, nbttagcompound5);
			Vec3d vec3d = this.getMotion();
			this.world.makeFireworks(this.getPosX(), this.getPosY(), this.getPosZ(), vec3d.x, vec3d.y, vec3d.z, compoundnbt);
		}

		super.handleStatusUpdate(bytePar);
	}

	public ItemStack generateFirework()
	{
		CompoundNBT nbttagcompound;
		CompoundNBT nbttagcompound1;

		ItemStack fireworkChargeItemStack = new ItemStack(Items.FIREWORK_STAR);

		nbttagcompound = new CompoundNBT();

		nbttagcompound1 = new CompoundNBT();
		//ListNBT nbttaglist = new ListNBT();

		List<Integer> list1 = Lists.<Integer>newArrayList();

		list1 = getFireworkColorList();

		byte explosionStyle = getExplosionStyle();

		int[] aint1 = new int[list1.size()];

		for (int l2 = 0; l2 < aint1.length; ++l2)
		{
			aint1[l2] = ((Integer)list1.get(l2)).intValue();
		}

		nbttagcompound1 = applySpecialEffect(nbttagcompound1);

		nbttagcompound1.putIntArray("Colors", aint1);
		nbttagcompound1.putByte("Type", explosionStyle);
		nbttagcompound.put("Explosion", nbttagcompound1);

		fireworkChargeItemStack.setTag(nbttagcompound);

		CompoundNBT nbttagcompound2 = new CompoundNBT();
		CompoundNBT nbttagcompound3 = new CompoundNBT();

		ItemStack fireworkItemStack = new ItemStack(Items.FIREWORK_ROCKET);

		ListNBT nbttaglist2 = new ListNBT();

		nbttaglist2.add(fireworkChargeItemStack.getChildTag("Explosion"));

		nbttagcompound3.put("Explosions", nbttaglist2);
		nbttagcompound3.putByte("Flight", (byte)1);
		nbttagcompound2.put("Fireworks", nbttagcompound3);

		fireworkItemStack.setTag(nbttagcompound2);

		return fireworkItemStack;
	}

	public List<Integer> getFireworkColorList()
	{
		List<Integer> arraylist = Lists.<Integer>newArrayList();

		ItemStack tempItemStack;

		switch (this.rand.nextInt(7))
		{
		case 0:
			tempItemStack = new ItemStack(Items.RED_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.ORANGE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.YELLOW_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 1:
			tempItemStack = new ItemStack(Items.YELLOW_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.PURPLE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 2:
			tempItemStack = new ItemStack(Items.RED_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.GREEN_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 3:
			tempItemStack = new ItemStack(Items.BLUE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.PURPLE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.LIGHT_BLUE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 4:
			tempItemStack = new ItemStack(Items.BLUE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.PURPLE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.GREEN_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 5:
			tempItemStack = new ItemStack(Items.WHITE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.PINK_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.RED_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 6:
			tempItemStack = new ItemStack(Items.RED_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.YELLOW_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.BLUE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
			break;
		case 7:
			tempItemStack = new ItemStack(Items.GREEN_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.PURPLE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());

			tempItemStack = new ItemStack(Items.ORANGE_DYE);
			arraylist.add(((DyeItem)tempItemStack.getItem()).getDyeColor().getFireworkColor());
		}

		return arraylist;
	}

	public byte getExplosionStyle()
	{
		int k = this.rand.nextInt(10);

		if(k >= 6)
		{
			return FireworksLib.SHAPE_NORMAL;
		}
		else if(k == 1 || k == 2)
		{
			return FireworksLib.SHAPE_STAR;
		}
		else if(k == 0)
		{
			return FireworksLib.SHAPE_LARGE;
		}
		else
		{
			return FireworksLib.SHAPE_BURST;
		}
	}

	public CompoundNBT applySpecialEffect(CompoundNBT nbttagcompound)
	{
		int k = this.rand.nextInt(3);

		if(k == 1)
		{
			nbttagcompound.putBoolean("Flicker", true);
		}

		k = this.rand.nextInt(6);

		if(k == 1)
		{
			nbttagcompound.putBoolean("Trail", true);
		}  

		return nbttagcompound;
	}
}