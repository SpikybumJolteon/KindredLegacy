package fuzzyacornindustries.kindredlegacy.entity.mob.hostile;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.mob.ai.AILeapAttack;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyLootTables;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityRaptorZerglingNincada extends EntityZerglingNincada
{
	static String mobName = "raptor_zergling_nincada";

	public EntityRaptorZerglingNincada(World par1World)
	{
		super(par1World);

		this.setSize(0.4F, 0.9F);
		this.tasks.addTask(3, new AILeapAttack(this, 0.6F, 2.0F, 6D, 20D, 5, KindredLegacySoundEvents.ZERGLING_NINCADA_SOAR));

		this.experienceValue = 10;
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
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
	}

	@Override
    public void fall(float distance, float damageMultiplier) {}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return KindredLegacyLootTables.RAPTOR_ZERGLING_NINCADA_LOOT_TABLE;
	}
}