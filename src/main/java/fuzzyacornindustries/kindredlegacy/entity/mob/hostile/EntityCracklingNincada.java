package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AILeapAttack;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class EntityCracklingNincada extends EntityZerglingNincada
{
	static String mobName = "crackling_nincada";

	public EntityCracklingNincada(World par1World)
	{
		super(par1World);

		this.setSize(0.4F, 0.9F);
		this.tasks.addTask(3, new AILeapAttack(this, 0.45F, 2.3F, 5D, 16D, 5, KindredLegacySoundEvents.ZERGLING_NINCADA_SOAR));

		this.experienceValue = 25;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
	}

	@Override
	public int getTotalArmorValue()
	{
		return 3;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.CRACKLING_NINCADA_LOOT_TABLE;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.posY), MathHelper.floor(this.posZ));

		if (this.world.canBlockSeeSky(blockpos) && this.world.getCurrentMoonPhaseFactor() == 1.0F)
		{
			return super.getCanSpawnHere();
		}
		else if(KindredLegacyEntities.isGalacticraftDimension(this.world.provider.getDimension()))
		{
			return super.getCanSpawnHere();
		}

		return false;
	}
}