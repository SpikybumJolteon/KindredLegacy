package fuzzyacornindustries.kindredlegacy.entity.projectile;

import fuzzyacornindustries.kindredlegacy.block.BlockGuardianField;
import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntityBloodmoonFoxfire;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBloodmoonNinetales;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockVine;
import net.minecraft.block.BlockWeb;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBloodmoonFireball extends KindredLegacyFireball
{
	protected static final DataParameter<Byte> TEXTURE = EntityDataManager.<Byte>createKey(HostilePokemon.class, DataSerializers.BYTE);

	private int currentTextureNumber = 0;

	public float attackDamage;

	public EntityBloodmoonFireball(World worldPar)
	{
		super(worldPar);

		//this.setSize(0.3125F, 0.3125F);
	}

	public EntityBloodmoonFireball(World worldPar, double setLocationX, double setLocationY, double setLocationZ,
			double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier)
	{
		super(worldPar, setLocationX, setLocationY, setLocationZ, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		//this.setSize(0.3125F, 0.3125F);
	}

	public EntityBloodmoonFireball(World worldPar, EntityLivingBase shootingEntity, double accelerationXPar, double accelerationYPar, double accelerationZPar, float fireballSpeedModifier)
	{
		super(worldPar, shootingEntity, accelerationXPar, accelerationYPar, accelerationZPar, fireballSpeedModifier);

		//this.setSize(0.3125F, 0.3125F);
	}

	public EntityBloodmoonFireball(World worldPar, EntityLivingBase shootingEntity, double setLocationX, double setLocationY, double setLocationZ,
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

		if (par1NBTTagCompound.hasKey("BloodmoonFlameTexture", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("BloodmoonFlameTexture");
			this.setCurrentTexture(b0);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("BloodmoonFlameTexture", (byte)this.getCurrentTexture());
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

					BlockPos blockpos = (new BlockPos(j2, k, l));
					IBlockState iblockstate = this.world.getBlockState(blockpos);
					Block block = iblockstate.getBlock();

					if (block instanceof BlockVine || block instanceof BlockWeb || block instanceof BlockCactus)
					{
			            block.dropBlockAsItem(this.world, blockpos, iblockstate, 0);
			            this.world.setBlockToAir(blockpos);
			            //block.breakBlock(this.world, blockpos, iblockstate);
					}
				}
			}
		}
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
				if(result.entityHit instanceof EntityBloodmoonFoxfire || 
						result.entityHit instanceof EntityBloodmoonFireball || 
						result.entityHit instanceof EntityBloodmoonNinetales) {}
				else if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), attackDamage))
				{
					result.entityHit.setFire(5);
				}
				else
				{
					this.setDead();
				}
			}
			else
			{
				this.setDead();
			}
		}
	}
}