package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AILeapAttack;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import fuzzyacornindustries.kindredlegacy.reference.LibraryBiomeID;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityBandersnatchFennekin extends HostilePokemon implements IAnimatedEntity
{
	protected static final DataParameter<Byte> TEXTURE = EntityDataManager.<Byte>createKey(EntityBandersnatchFennekin.class, DataSerializers.BYTE);

	private IdleAnimationClock neckBobbleIdleAnimationClock;
	private IdleAnimationClock tailIdleAnimationClock[] = new IdleAnimationClock[4];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	static String mobName = "bandersnatch_fennekin";

	public static final int snowCoatID = 0;
	public static final int temperateCoatID = 1;

	public EntityBandersnatchFennekin(World par1World)
	{
		super(par1World);
		this.setSize(0.3F, 0.9F);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new AILeapAttack(this, 0.4F, 2F, 5D, 16D, 5, null));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntitySubstituteDoll.class, true));

		this.isImmuneToFire = true;

		if(this.world.isRemote)
		{
			setClockDefaults();
		}

		animationID = actionIDNone;
		animationTick = 0;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();

		this.dataManager.register(TEXTURE, Byte.valueOf(Byte.valueOf((byte)0)));
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.2D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.42D);
	}

	@Override
    public void fall(float distance, float damageMultiplier) {}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return KindredLegacySoundEvents.BANDERSNATCH_FENNEKIN_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return KindredLegacySoundEvents.BANDERSNATCH_FENNEKIN_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return KindredLegacySoundEvents.BANDERSNATCH_FENNEKIN_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}

	public int getBandersnatchFennekinType()
	{
		return (int)this.dataManager.get(TEXTURE).byteValue();
	}

	public void setBandersnatchFennekinType(int par1)
	{
		this.dataManager.set(TEXTURE, (byte)par1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);

		if (par1NBTTagCompound.hasKey("BandersnatchFennekinType", 99))
		{
			byte b0 = par1NBTTagCompound.getByte("BandersnatchFennekinType");
			this.setBandersnatchFennekinType(b0);
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("BandersnatchFennekinType", (byte)this.getBandersnatchFennekinType());
	}

	@Override
	public boolean attackEntityAsMob(Entity targetEntity)
	{
		if (super.attackEntityAsMob(targetEntity))
		{
			if (targetEntity instanceof EntityLivingBase)
			{
				byte durationSeconds = 5;

				if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
				{
					durationSeconds = 10;
				}
				else if (this.world.getDifficulty() == EnumDifficulty.HARD)
				{
					durationSeconds = 18;
				}

				if (this.rand.nextInt(2) == 0)
				{
		            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(8) == 0)
				{
		            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, durationSeconds * 20, 0));
				}

				if (this.rand.nextInt(10) == 0)
				{
					((EntityLivingBase)targetEntity).setFire(durationSeconds);

					playIgniteSound((EntityLivingBase)targetEntity);
				}

	            ((EntityLivingBase)targetEntity).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, durationSeconds * 20, 0));
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isPotionApplicable(PotionEffect potioneffectIn)
	{
		return potioneffectIn.getPotion() == MobEffects.SLOWNESS ? false : super.isPotionApplicable(potioneffectIn);
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.BANDERSNATCH_FENNEKIN_LOOT_TABLE;
	}

    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);

        setBandersnatchFennekinType(getFennekinType());

        return livingdata;
    }

	public int getFennekinType()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));
		Chunk chunk = this.world.getChunkFromBlockCoords(blockpos);

		Biome biomegenbase = this.world.getBiome(blockpos);

		Set<Type> biomeTypes = BiomeDictionary.getTypes(biomegenbase);

		Iterator<Type> biomeTypeIterator = biomeTypes.iterator();

		while(biomeTypeIterator.hasNext())
		{
			if(biomeTypeIterator.next().equals(Type.SNOWY))
			{
				return snowCoatID;
			}
		}

		if (biomegenbase == Biome.getBiome(LibraryBiomeID.EXTREME_HILLS) || biomegenbase == Biome.getBiome(LibraryBiomeID.EXTREME_HILLS_EDGE) || biomegenbase == Biome.getBiome(LibraryBiomeID.EXTREME_HILLS_PLUS))
		{
			if (MathHelper.floor(this.posY) >= 92)
			{
				return snowCoatID;
			}
		}

		return temperateCoatID;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));
		Chunk chunk = this.world.getChunkFromBlockCoords(blockpos);

		if (this.world.getDifficulty() != EnumDifficulty.PEACEFUL)
		{
			Biome biomegenbase = this.world.getBiome(blockpos);

			Set<Type> biomeTypes = BiomeDictionary.getTypes(biomegenbase);

			Iterator<Type> biomeTypeIterator = biomeTypes.iterator();

			while(biomeTypeIterator.hasNext())
			{
				if(biomeTypeIterator.next().equals(Type.END))
				{
					return false;
				}
			}

			return super.getCanSpawnHere();
		}

		return false;
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockNeckBobble()
	{
		return neckBobbleIdleAnimationClock;
	}

	@SideOnly(Side.CLIENT)
	public IdleAnimationClock getIdleAnimationClockTail(int partNumber)
	{
		return tailIdleAnimationClock[partNumber];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void incrementPartClocks()
	{
		neckBobbleIdleAnimationClock.incrementClocks();

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i].incrementClocks();
		}
	}

	@SideOnly(Side.CLIENT)
	public void setClockDefaults()
	{
		setNeckBobbleClockDefaults();
		setTailClockDefaults();
	}

	@SideOnly(Side.CLIENT)
	public void setNeckBobbleClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		neckBobbleIdleAnimationClock = new IdleAnimationClock(1, 0, 0);

		neckBobbleIdleAnimationClock.setPhaseDurationX(0, 70);

		int startingClockX = randomInt;

		while(startingClockX > neckBobbleIdleAnimationClock.getTotalDurationLengthX())
		{
			startingClockX -= neckBobbleIdleAnimationClock.getTotalDurationLengthX();
		}

		neckBobbleIdleAnimationClock.setClockX(startingClockX);
	}

	@SideOnly(Side.CLIENT)
	public void setTailClockDefaults()
	{
		int randomInt = this.rand.nextInt(100);

		for(int i = 0; i < tailIdleAnimationClock.length; i++)
		{
			tailIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			tailIdleAnimationClock[i].setPhaseDurationX(0, 30);
			tailIdleAnimationClock[i].setPhaseDurationY(0, 50);

			float pointClusterDensityX = 0.45F;
			float pointClusterDensityY = 0.70F;

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