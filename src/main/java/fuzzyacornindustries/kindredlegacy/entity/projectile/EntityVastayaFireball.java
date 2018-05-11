package fuzzyacornindustries.kindredlegacy.entity.projectile;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityVastayaFireball extends KindredLegacyFireball
{
	protected static final DataParameter<Byte> TEXTURE = EntityDataManager.<Byte>createKey(TamablePokemon.class, DataSerializers.BYTE);

	private int currentTextureNumber = 0;

	public float attackDamage;

	public EntityVastayaFireball(World worldPar)
	{
		super(worldPar);

		//this.setSize(0.3125F, 0.3125F);
	}

	public EntityVastayaFireball(World worldPar, double setLocationX, double setLocationY, double setLocationZ,
			double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier)
	{
		super(worldPar, setLocationX, setLocationY, setLocationZ, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		//this.setSize(0.3125F, 0.3125F);
	}

	public EntityVastayaFireball(World worldPar, EntityLivingBase shootingEntity, double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier)
	{
		super(worldPar, shootingEntity, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		//this.setSize(0.3125F, 0.3125F);
	}

	public EntityVastayaFireball(World worldPar, EntityLivingBase shootingEntity, double setLocationX, double setLocationY, double setLocationZ,
			double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier, float attackDamage)
	{
		super(worldPar, shootingEntity, setLocationX, setLocationY, setLocationZ, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		this.attackDamage = attackDamage;
		//this.setSize(0.3125F, 0.3125F);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

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
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("VastayaFlameTexture", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("VastayaFlameTexture");
			this.setCurrentTexture(b0);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("VastayaFlameTexture", (byte)this.getCurrentTexture());
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.ticksExisted > 200)
		{
			this.setDead();

			return;
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
			if (result.entityHit != null)
			{
				if (!(result.entityHit instanceof EntityTameable) 
						&& !(result.entityHit instanceof EntityPlayer && !(result.entityHit instanceof EntityVastayaFireball)))
				{
					if(!(result.entityHit instanceof EntityAnimal) && !(result.entityHit.getRidingEntity() instanceof EntityPlayer))
					{
						if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), attackDamage))
						{
							result.entityHit.setFire(5);
						}
					}
					else if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), attackDamage))
					{
						result.entityHit.setFire(5);
					}
				}
			}

			this.setDead();
		}
	}
}