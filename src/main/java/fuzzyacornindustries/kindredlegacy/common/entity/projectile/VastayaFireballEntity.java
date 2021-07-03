package fuzzyacornindustries.kindredlegacy.common.entity.projectile;

import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.VastayaNinetalesEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class VastayaFireballEntity extends AbstractModFireball
{
	protected static final DataParameter<Byte> TEXTURE = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	private int currentTextureNumber = 0;

	public float attackDamage;

	public VastayaNinetalesEntity vastayaNinetales;

	public VastayaFireballEntity(EntityType<VastayaFireballEntity> type, World worldPar)
	{
		super(type, worldPar);

		//this.setSize(0.3125F, 0.3125F);
	}

	public VastayaFireballEntity(World worldPar, double setLocationX, double setLocationY, double setLocationZ,
			double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier)
	{
		super(ModEntities.VASTAYA_FIREBALL.get(), worldPar, setLocationX, setLocationY, setLocationZ, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		//this.setSize(0.3125F, 0.3125F);
	}

	public VastayaFireballEntity(World worldPar, LivingEntity shootingEntity, double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier)
	{
		super(ModEntities.VASTAYA_FIREBALL.get(), worldPar, shootingEntity, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		//this.setSize(0.3125F, 0.3125F);
	}

	public VastayaFireballEntity(World worldPar, VastayaNinetalesEntity shootingEntity, double setLocationX, double setLocationY, double setLocationZ,
			double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier, float attackDamage)
	{
		super(ModEntities.VASTAYA_FIREBALL.get(), worldPar, shootingEntity, setLocationX, setLocationY, setLocationZ, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		this.attackDamage = attackDamage;

		this.vastayaNinetales = shootingEntity;
		//this.setSize(0.3125F, 0.3125F);
	}

	@Override
	protected void registerData()
	{
		this.dataManager.register(TEXTURE, Byte.valueOf(Byte.valueOf((byte)0)));
	}

	public int getCurrentTexture()
	{
		return (int)this.dataManager.get(TEXTURE).byteValue();
	}

	public void setCurrentTexture(int par1)
	{
		this.dataManager.set(TEXTURE, (byte)par1);
	}


	@Override
	public void readAdditional(CompoundNBT compound) 
	{
		super.readAdditional(compound);

		if (compound.contains("VastayaFlameTexture", 99))
		{
			byte b0 = compound.getByte("VastayaFlameTexture");
			this.setCurrentTexture(b0);
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) 
	{
		super.writeAdditional(compound);
		compound.putByte("VastayaFlameTexture", (byte)this.getCurrentTexture());
	}

	@Override
	public void tick()
	{
		super.tick();

		if(this.ticksExisted > 1000)
		{
			this.remove();
		}

		if(currentTextureNumber >= 2)
		{
			currentTextureNumber = 0;
		}
		else
		{
			currentTextureNumber++;
		}

		setCurrentTexture(currentTextureNumber);
	}

	/**
	 * Called when this EntityFireball hits a block or entity.
	 */
	@Override
	protected void onImpact(RayTraceResult result)
	{
		if (!this.world.isRemote)
		{
			RayTraceResult.Type raytraceresult$type = result.getType();
			if (raytraceresult$type == RayTraceResult.Type.ENTITY) 
			{
				Entity entityHit = ((EntityRayTraceResult)result).getEntity();

				if (entityHit != null && !(entityHit instanceof TamablePokemon) && entityHit != this.vastayaNinetales && !(entityHit instanceof PlayerEntity)) 
				{
					if(!(entityHit.getRidingEntity() instanceof PlayerEntity))
					{
						if(((LivingEntity)entityHit).getHealth() <= attackDamage)
						{
							entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.vastayaNinetales.getOwner()), attackDamage);
						}
						else if(entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), attackDamage))
						{
							entityHit.setFire(5);
						}
					}
					else if (entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), attackDamage))
					{	
						entityHit.setFire(5);
					}

					if (!this.world.isRemote)
					{
						this.world.setEntityState(this, (byte)17);
					}

					this.remove();
				}
			}
		}
	}

	@Override
	public IPacket<?> createSpawnPacket() 
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}