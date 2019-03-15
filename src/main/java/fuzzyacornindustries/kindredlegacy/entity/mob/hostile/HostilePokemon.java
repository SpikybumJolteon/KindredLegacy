package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import fuzzyacornindustries.kindredlegacy.animation.IAdvAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.mob.IGravityTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.IMobMotionTracker;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HostilePokemon extends EntityMob implements IAnimatedEntity, IAdvAnimatedEntity, IMobMotionTracker, IGravityTracker
{
	protected static final DataParameter<Boolean> IS_AGGROED = EntityDataManager.<Boolean>createKey(TamablePokemon.class, DataSerializers.BOOLEAN);
	protected static final DataParameter<Boolean> IS_MOUNTED = EntityDataManager.<Boolean>createKey(TamablePokemon.class, DataSerializers.BOOLEAN);
	
	public float previousYaw[] = new float[6];
	public float changeInYaw;
	public float totalChangeInYaw;

	public float previousHeight[] = new float[6];
	public float changeInHeight;

	protected int animationID;
	protected int animationTick;

	public boolean isAggroed = false;
	public float targetingAggroValue = 0F;
	public boolean isMounted = false;
	public float mountedValue = 0F;
	
	public double worldGravity;

	public static final int actionIDNone = 0;

	public HostilePokemon(World world)
	{
		super(world);

		animationID = actionIDNone;
		animationTick = 0;

		if(this.world.isRemote)
		{
			for(int i = 0; i < previousYaw.length; i++)
			{
				previousYaw[i] = this.renderYawOffset;
			}

			changeInYaw = 0;
			totalChangeInYaw = 0;

			for(int i = 0; i < previousHeight.length; i++)
			{
				previousHeight[i] = (float)this.posY;
			}

			changeInHeight = 0;
		}
	}
	
	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.register(IS_AGGROED, Boolean.valueOf(false));
		this.dataManager.register(IS_MOUNTED, Boolean.valueOf(false));		
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(!this.world.isRemote)
		{
			checkAggro();
			checkMounted();
		}
		else
		{
			incrementPartClocks();

			calculateAggroValue();
			calculateMountedValue();
		}
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if(this.world.isRemote)
		{
			float currentYaw = this.renderYawOffset;

			if(currentYaw - previousYaw[0] > 180F)
			{
				for(int i = 0; i < previousYaw.length; i++)
				{
					previousYaw[i] += (currentYaw - previousYaw[i]);
				}
			}
			else if(currentYaw - previousYaw[0] < -180F)
			{
				for(int i = 0; i < previousYaw.length; i++)
				{
					previousYaw[i] += (currentYaw - previousYaw[i]);
				}
			}

			float sum = 0;
			for (float d : previousYaw) sum += d;
			float averagePreviousYaw = 1.0F * sum / previousYaw.length;

			changeInYaw = (currentYaw - averagePreviousYaw) / 50F;

			totalChangeInYaw =+ Math.abs(currentYaw) * 0.2F;

			for(int i = previousYaw.length - 1; i > 0; i--)
			{
				previousYaw[i] = previousYaw[i - 1];
			}

			previousYaw[0] = currentYaw;

			//System.out.println( "Test change in pitch" );
			//System.out.println( Float.toString( this.renderYawOffset ) );
			//System.out.println( Float.toString( averagePreviousYaw ) );
			//System.out.println( Float.toString( changeInYaw ) );

			float currentHeight = (float)this.posY;

			float sum2 = 0;
			for (float d : previousHeight) sum2 += d;
			float averagePreviousHeight = 1.0F * sum2 / previousHeight.length;

			changeInHeight = this.ticksExisted>6?((currentHeight - averagePreviousHeight) / 2F):0;

			for(int i = previousHeight.length - 1; i > 0; i--)
			{
				previousHeight[i] = previousHeight[i - 1];
			}

			previousHeight[0] = currentHeight;
		}
	}
	
	public void checkAggro()
	{
		if(this.getAttackTarget() != null)
		{
			if(!getIsAggroed())
			{
				setAggro(true);
			}
		}
		else
		{
			if(getIsAggroed())
			{
				setAggro(false);
			}
		}
	}
	
	public boolean getIsAggroed()
	{
		return this.dataManager.get(IS_AGGROED).booleanValue();
	}

	public void setAggro(boolean isAggroed)
	{
		this.dataManager.set(IS_AGGROED, isAggroed);
	}
	
	@Override
	public float getAggroValue()
	{
		return this.targetingAggroValue;
	}

	public void calculateAggroValue()
	{
		if(getIsAggroed())
		{
			this.targetingAggroValue += 0.1F;

			if(this.targetingAggroValue > 1F)
				this.targetingAggroValue = 1F;
		}
		else
		{
			this.targetingAggroValue -= 0.1F;

			if(this.targetingAggroValue < 0F)
				this.targetingAggroValue = 0F;
		}
	}
	
	public void checkMounted()
	{
		if(this.isBeingRidden())
		{
			if(!getIsMounted())
			{
				setMounted(true);
			}
		}
		else
		{
			if(getIsMounted())
			{
				setMounted(false);
			}
		}
	}

	public void setMounted(boolean isMounted)
	{
		this.dataManager.set(IS_MOUNTED, isMounted);
	}
	
	public boolean getIsMounted()
	{
		return this.dataManager.get(IS_MOUNTED).booleanValue();
	}
	
	@Override
	public float getMountedValue()
	{
		return this.mountedValue;
	}
	
	public void calculateMountedValue()
	{
		if(this.isMounted)
		{
			this.mountedValue =+ 0.1F;

			if(this.mountedValue > 1F)
				this.mountedValue = 1F;
		}
		else
		{
			this.mountedValue =- 0.1F;

			if(this.mountedValue < 0F)
				this.mountedValue = 0F;
		}
	}
	
	public void playIgniteSound(EntityLivingBase targetEntity)
	{
		this.world.playSound((EntityPlayer)null, targetEntity.posX, targetEntity.posY, targetEntity.posZ, KindredLegacySoundEvents.IGNITE, SoundCategory.HOSTILE, 1.2F, 1.0F / (this.world.rand.nextFloat() * 0.4F + 0.8F));
	}

	public void playBioSound(EntityLivingBase targetEntity)
	{
		this.world.playSound((EntityPlayer)null, targetEntity.posX, targetEntity.posY, targetEntity.posZ, KindredLegacySoundEvents.BIO, SoundCategory.HOSTILE, 1.0F, 1.2F / (this.world.rand.nextFloat() * 0.4F + 0.8F));
	}

	@Override
	public boolean getCanSpawnHere()
	{
		if(this.world.provider.getDimension() == 0)
		{
			return super.getCanSpawnHere();
		}
		else if(cancelNetherSpawns())
		{
			return false;
		}
		else if(cancelEndSpawns())
		{
			return false;
		}
		else if(cancelOtherDimensionSpawns())
		{
			return false;
		}
		else
		{
			return super.getCanSpawnHere();
		}
	}

	public boolean cancelNetherSpawns()
	{
		int dimensionNumber = this.world.provider.getDimension();

		if(dimensionNumber == -1)
		{
			return true;
		}

		return false;
	}

	public boolean cancelEndSpawns()
	{
		int dimensionNumber = this.world.provider.getDimension();

		if(dimensionNumber == 1)
		{
			return true;
		}

		return false;
	}

	public boolean cancelOtherDimensionSpawns()
	{
		if(KindredLegacyEntities.dimensionSpawnsDisablerList != null)
		{
			for (int dimensionNumber : KindredLegacyEntities.dimensionSpawnsDisablerList)
			{
				if(this.world.provider.getDimension() == dimensionNumber)
					return true;
			}
		}

		return false;
	}

	@Override
	public float getGravityFactor()
	{
		if(this.worldGravity != 0)
		{
			return (float) Math.sqrt(this.worldGravity / 0.08F);
		}
		else
		{
			return 1F;
		}
	}

	@Override
	public float getGravityVsOverworldRatio()
	{
		if(this.worldGravity != 0)
		{
			return (float) this.worldGravity / 0.08F;
		}
		else
		{
			return 1F;
		}
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks() {}

	@SideOnly(Side.CLIENT)
	public float getAngularVelocity()
	{	
		float angularVelocity = changeInYaw;

		if(angularVelocity != 0)
		{
			if(angularVelocity > 1F)
				angularVelocity = 1F;
			else if(angularVelocity < -1F)
				angularVelocity = -1F;
		}

		return angularVelocity;
	}

	@SideOnly(Side.CLIENT)
	public float getTotalAngularChange()
	{
		return totalChangeInYaw;
	}

	@SideOnly(Side.CLIENT)
	public float getHeightVelocity()
	{	
		float verticalVelocity = changeInHeight;

		if(verticalVelocity != 0)
		{
			if(verticalVelocity > 1F)
				verticalVelocity = 1F;
			else if(verticalVelocity < -1F)
				verticalVelocity = -1F;
		}
		return verticalVelocity;
	}

	@Override
	public void setAnimationID(int id) 
	{
		animationID = id;
	}

	@Override
	public void setAnimationTick(int tick) 
	{
		animationTick = tick;	
	}

	@Override
	public int getAnimationID() 
	{
		return animationID;
	}

	@Override
	public int getAnimationTick() 
	{
		return animationTick;
	}
}