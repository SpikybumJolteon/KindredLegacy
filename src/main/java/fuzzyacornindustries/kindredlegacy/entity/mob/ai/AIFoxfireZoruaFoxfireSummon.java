package fuzzyacornindustries.kindredlegacy.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.animation.AIAnimation;
import fuzzyacornindustries.kindredlegacy.client.KindredLegacySoundEvents;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntityFoxfire;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFoxfireZorua;
import fuzzyacornindustries.kindredlegacy.reference.action.LibraryFoxfireZoruaAttackID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class AIFoxfireZoruaFoxfireSummon extends AIAnimation 
{
	private EntityFoxfireZorua entityFoxfireZorua;
	private EntityLivingBase attackTarget;

	public int attackDuration = 26;

	public AIFoxfireZoruaFoxfireSummon(EntityFoxfireZorua entityFoxfireZorua) 
	{
		super(entityFoxfireZorua, 3);
		this.entityFoxfireZorua = entityFoxfireZorua;
		attackTarget = null;
	}

	public int getAnimationID() 
	{
		return LibraryFoxfireZoruaAttackID.SUMMON_FOXFIRE;
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
		attackTarget = entityFoxfireZorua.getAttackTarget();
	}

	public void updateTask() 
	{	
		if(attackTarget != null)
		{
			entityFoxfireZorua.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);

			if (entityFoxfireZorua.getAnimationTick() == 4 && entityFoxfireZorua.onGround)
            {
				entityFoxfireZorua.motionY = 0.35D;
            }
			
			if(entityFoxfireZorua.getAnimationTick() == 12)
			{
				if (!entityFoxfireZorua.world.isRemote)
				{
					boolean foxfireSummoned = false;
					int numberOfSummonAttempts = 0;

					while (!foxfireSummoned && numberOfSummonAttempts < 30 ) 
					{
						int i = MathHelper.floor(entityFoxfireZorua.posX);
						int j = MathHelper.floor(entityFoxfireZorua.posY);
						int k = MathHelper.floor(entityFoxfireZorua.posZ);

						int i1 = i + MathHelper.getInt(entityFoxfireZorua.world.rand, 1, 2) * MathHelper.getInt(entityFoxfireZorua.world.rand, -1, 1);
						int j1 = j + MathHelper.getInt(entityFoxfireZorua.world.rand, 1, 2) * MathHelper.getInt(entityFoxfireZorua.world.rand, -1, 1);
						int k1 = k + MathHelper.getInt(entityFoxfireZorua.world.rand, 1, 2) * MathHelper.getInt(entityFoxfireZorua.world.rand, -1, 1);

						if(entityFoxfireZorua.world.isSideSolid(new BlockPos(i1, j1 - 1, k1), EnumFacing.UP) && entityFoxfireZorua.world.getLight(new BlockPos(i1, j1, k1)) > 1)
						{
							EntityFoxfire entityMinion = new EntityFoxfire(entityFoxfireZorua.world);

							entityMinion.attackingPoketamable = entityFoxfireZorua;
							entityMinion.attackPower = entityFoxfireZorua.getAttackDamage() / 2F;
							entityMinion.setLocationAndAngles(i1 + 0.5D, j1, k1 + 0.5D, entityFoxfireZorua.rotationYaw, entityFoxfireZorua.rotationPitch);

							entityMinion.setAttackTarget(attackTarget);

							entityFoxfireZorua.world.spawnEntity(entityMinion);

							entityMinion.playSound(KindredLegacySoundEvents.FIREBALL_SWOOSH, 0.5F, 0.4F / (entityFoxfireZorua.world.rand.nextFloat() * 0.4F + 0.8F));

							foxfireSummoned = true;
						}

						numberOfSummonAttempts++;
					}
				}
			}
		}
	}
}