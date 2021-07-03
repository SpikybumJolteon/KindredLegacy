package fuzzyacornindustries.kindredlegacy.common.entity.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractModFireball extends Entity
{
    public LivingEntity shootingEntity;
    private int ticksAlive;
    private int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

    public float fireballSpeedModifier;

    public AbstractModFireball(EntityType<? extends AbstractModFireball> fireballType, World worldIn)
    {
        super(fireballType, worldIn);
    }

    public AbstractModFireball(EntityType<? extends AbstractModFireball> fireballType, World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ, float fireballSpeedModifier)
    {
        super(fireballType, worldIn);
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        double d0 = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
        this.fireballSpeedModifier = fireballSpeedModifier;
    }

    public AbstractModFireball(EntityType<? extends AbstractModFireball> fireballType, World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ, float fireballSpeedModifier)
    {
        super(fireballType, worldIn);
        this.shootingEntity = shooter;
        this.setLocationAndAngles(shooter.getPosX(), shooter.getPosY(), shooter.getPosZ(), shooter.rotationYaw, shooter.rotationPitch);
        this.setPosition(this.getPosX(), this.getPosY(), this.getPosZ());
        this.setMotion(Vec3d.ZERO);
        accelX = accelX + this.rand.nextGaussian() * 0.4D;
        accelY = accelY + this.rand.nextGaussian() * 0.4D;
        accelZ = accelZ + this.rand.nextGaussian() * 0.4D;
        double d0 = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
        this.fireballSpeedModifier = fireballSpeedModifier;
    }


    public AbstractModFireball(EntityType<? extends AbstractModFireball> fireballType, World worldIn, LivingEntity shooter, double x, double y, double z, double accelX, double accelY, double accelZ, float fireballSpeedModifier)
    {
        super(fireballType, worldIn);
        this.shootingEntity = shooter;
        this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
        this.setPosition(x, y, z);
        this.setMotion(Vec3d.ZERO);
        //accelX = accelX + this.rand.nextGaussian() * 0.1D;
        //accelY = accelY + this.rand.nextGaussian() * 0.1D;
        //accelZ = accelZ + this.rand.nextGaussian() * 0.1D;
        double d0 = (double)MathHelper.sqrt(accelX * accelX + accelY * accelY + accelZ * accelZ);
        this.accelerationX = accelX / d0 * 0.1D;
        this.accelerationY = accelY / d0 * 0.1D;
        this.accelerationZ = accelZ / d0 * 0.1D;
        this.fireballSpeedModifier = fireballSpeedModifier;
    }

    /**
     * Checks if the entity is in range to render.
     */
    @Override
	@OnlyIn(Dist.CLIENT)
    public boolean isInRangeToRenderDist(double distance)
    {
        double d0 = this.getBoundingBox().getAverageEdgeLength() * 4.0D;

        if (Double.isNaN(d0))
        {
            d0 = 4.0D;
        }

        d0 = d0 * 64.0D;
        return distance < d0 * d0;
    }

    /**
     * Called to update the entity's position/logic.
     */
    @SuppressWarnings("deprecation")
	@Override
    public void tick() 
    {
        if (this.world.isRemote || (this.shootingEntity == null || this.shootingEntity.isAlive()) && this.world.isBlockLoaded(new BlockPos(this)))
        {
            super.tick();

            ++this.ticksInAir;
            RayTraceResult raytraceresult = ProjectileHelper.rayTrace(this, true, this.ticksInAir >= 25, this.shootingEntity, RayTraceContext.BlockMode.COLLIDER);

            if (raytraceresult != null)
            {
                this.onImpact(raytraceresult);
            }

            Vec3d vec3d = this.getMotion();
            double d0 = this.getPosX() + vec3d.x;
            double d1 = this.getPosY() + vec3d.y;
            double d2 = this.getPosZ() + vec3d.z;
            ProjectileHelper.rotateTowardsMovement(this, 0.2F);
            float f = this.getMotionFactor() * fireballSpeedModifier;

            if (this.isInWater())
            {
                for (int i = 0; i < 4; ++i)
                {
                    this.world.addParticle(ParticleTypes.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
                }

                f = 0.8F;
            }

            this.setMotion(vec3d.add(this.accelerationX, this.accelerationY, this.accelerationZ).scale((double)f));
            this.world.addParticle(this.getParticle(), d0, d1 + 0.5D, d2, 0.0D, 0.0D, 0.0D);
            this.setPosition(d0, d1, d2);
         }
        else 
        {
            this.remove();
         }
    }

    protected IParticleData getParticle() 
    {
       return ParticleTypes.SMOKE;
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by the original motion.
     */
    protected float getMotionFactor()
    {
        return 0.95F;
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onImpact(RayTraceResult result){};

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeAdditional(CompoundNBT compound) 
    {
        Vec3d vec3d = this.getMotion();
        compound.put("direction", this.newDoubleNBTList(new double[]{vec3d.x, vec3d.y, vec3d.z}));
        compound.put("power", this.newDoubleNBTList(new double[]{this.accelerationX, this.accelerationY, this.accelerationZ}));
        compound.putInt("life", this.ticksAlive);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound) 
    {
        if (compound.contains("power", 9)) 
        {
            ListNBT listnbt = compound.getList("power", 6);
            if (listnbt.size() == 3) 
            {
               this.accelerationX = listnbt.getDouble(0);
               this.accelerationY = listnbt.getDouble(1);
               this.accelerationZ = listnbt.getDouble(2);
            }
         }

         this.ticksAlive = compound.getInt("life");
         if (compound.contains("direction", 9) && compound.getList("direction", 6).size() == 3) 
         {
            ListNBT listnbt1 = compound.getList("direction", 6);
            this.setMotion(listnbt1.getDouble(0), listnbt1.getDouble(1), listnbt1.getDouble(2));
         }
         else 
         {
            this.remove();
         }

      }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if ((this.isInvulnerableTo(source)))
        {
            return false;
        }
        else
        {
            this.markVelocityChanged();

            if (source.getTrueSource() != null)
            {
                Vec3d vec3d = source.getTrueSource().getLookVec();
                this.setMotion(vec3d);
                this.accelerationX = vec3d.x * 0.1D;
                this.accelerationY = vec3d.y * 0.1D;
                this.accelerationZ = vec3d.z * 0.1D;

                if (source.getTrueSource() instanceof LivingEntity) 
                {
                   this.shootingEntity = (LivingEntity)source.getTrueSource();
                }

                return true;
            }
            else
            {
                return false;
            }
        }
    }

    /**
     * Gets how bright this entity is.
     */
    @Override
    public float getBrightness()
    {
        return 1.0F;
    }
}