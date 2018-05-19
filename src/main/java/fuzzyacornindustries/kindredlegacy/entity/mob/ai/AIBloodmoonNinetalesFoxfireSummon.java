package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntityBloodmoonFoxfire;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBloodmoonNinetales;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryAhriNinetalesAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class AIBloodmoonNinetalesFoxfireSummon extends AIAnimation 
{
	private EntityBloodmoonNinetales entityBloodmoonNinetales;
	private EntityLivingBase attackTarget;

	public int attackDuration = 28;

	public AIBloodmoonNinetalesFoxfireSummon(EntityBloodmoonNinetales entityBloodmoonNinetales) 
	{
		super(entityBloodmoonNinetales, 3);
		this.entityBloodmoonNinetales = entityBloodmoonNinetales;
		attackTarget = null;
	}

	public int getAnimationID() 
	{
		return LibraryAhriNinetalesAttackID.FOXFIRE_SUMMON;
	}

	public boolean isAutomatic() 
	{
		return true;
	}

	public int getDuration() 
	{
		return attackDuration;
	}

	public void startExecuting() 
	{
		super.startExecuting();
		attackTarget = entityBloodmoonNinetales.getAttackTarget();
	}

	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityBloodmoonNinetales.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if(entityBloodmoonNinetales.getAnimationTick() == 1)
			{
				entityBloodmoonNinetales.playLaughSound();
			}
			else if(entityBloodmoonNinetales.getAnimationTick() == 5 ||
					entityBloodmoonNinetales.getAnimationTick() == 12 ||
					entityBloodmoonNinetales.getAnimationTick() == 19)
			{
				if (!entityBloodmoonNinetales.world.isRemote)
				{
					boolean foxfireSummoned = false;
					int numberOfSummonAttempts = 0;

					while (!foxfireSummoned && numberOfSummonAttempts < 30 ) 
					{
						int i = MathHelper.floor(entityBloodmoonNinetales.posX);
						int j = MathHelper.floor(entityBloodmoonNinetales.posY);
						int k = MathHelper.floor(entityBloodmoonNinetales.posZ);

						int i1 = i + MathHelper.getInt(entityBloodmoonNinetales.world.rand, 1, 2) * MathHelper.getInt(entityBloodmoonNinetales.world.rand, -1, 1);
						int j1 = j + MathHelper.getInt(entityBloodmoonNinetales.world.rand, 1, 2) * MathHelper.getInt(entityBloodmoonNinetales.world.rand, -1, 1);
						int k1 = k + MathHelper.getInt(entityBloodmoonNinetales.world.rand, 1, 2) * MathHelper.getInt(entityBloodmoonNinetales.world.rand, -1, 1);

						if(entityBloodmoonNinetales.world.isSideSolid(new BlockPos(i1, j1 - 1, k1), EnumFacing.UP) && entityBloodmoonNinetales.world.getLight(new BlockPos(i1, j1, k1)) > 1)
						{
							EntityBloodmoonFoxfire entityMinion = new EntityBloodmoonFoxfire(entityBloodmoonNinetales.world);

							entityMinion.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, entityBloodmoonNinetales.rotationYaw, entityBloodmoonNinetales.rotationPitch);

							entityMinion.setAttackTarget(attackTarget);

							entityBloodmoonNinetales.world.spawnEntity(entityMinion);

							entityMinion.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (entityBloodmoonNinetales.world.rand.nextFloat() * 0.4F + 0.8F));

							foxfireSummoned = true;
						}

						numberOfSummonAttempts++;
					}
				}
			}
		}
	}
}