package fuzzyacornindustries.kindredlegacy.utility;

import java.util.ArrayList;
import java.util.List;

import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityHunterBolt;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * 
 * A collection of methods related to target acquisition
 *
 * Most are taken from Zelda Sword Skills by CoolAlias
 *
 */
public class UtilityTargeting 
{
	/** Maximum range within which to search for targets */
	private static final int MAX_DISTANCE = 256;
	/** Max distance squared, used for comparing target distances (avoids having to call sqrt) */
	private static final double MAX_DISTANCE_SQ = MAX_DISTANCE * MAX_DISTANCE;
	
	/**
	 * Returns RayTraceResult of Entity or Block impacted, or null if nothing was struck
	 * @param entity	The entity checking for impact, e.g. an arrow
	 * @param shooter	An entity not to be collided with, generally the shooter
	 * @param hitBox	The amount by which to expand the collided entities' bounding boxes when checking for impact (may be negative)
	 * @param flag		Optional flag to allow collision with shooter, e.g. (ticksInAir >= 5)
	 * 
	 */
	public static RayTraceResult checkForImpact(World world, Entity entity, Entity shooter, double hitBox, boolean flag) 
	{
		Vec3d vec3 = new Vec3d(entity.posX, entity.posY, entity.posZ);
		Vec3d vec31 = new Vec3d(entity.posX + entity.motionX, entity.posY + entity.motionY, entity.posZ + entity.motionZ);

		RayTraceResult raytraceresult = world.rayTraceBlocks(vec3, vec31, false, true, false);
		vec3 = new Vec3d(entity.posX, entity.posY, entity.posZ);
		vec31 = new Vec3d(entity.posX + entity.motionX, entity.posY + entity.motionY, entity.posZ + entity.motionZ);

		if (raytraceresult != null) 
		{
			vec31 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
		}

		Entity target = null;
		List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entity, entity.getEntityBoundingBox().expand(entity.motionX, entity.motionY, entity.motionZ).expand(1.0D, 1.0D, 1.0D));
		double d0 = 0.0D;
		//double hitBox = 0.3D;

		for (int i = 0; i < list.size(); ++i) 
		{
			Entity entity1 = (Entity) list.get(i);

			if (entity1.canBeCollidedWith() && (entity1 != shooter || flag)) 
			{
				AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(hitBox, hitBox, hitBox);
				RayTraceResult mop1 = axisalignedbb.calculateIntercept(vec3, vec31);

				if (mop1 != null) 
				{
					double d1 = vec3.distanceTo(mop1.hitVec);

					if (d1 < d0 || d0 == 0.0D) 
					{
						target = entity1;
						d0 = d1;
					}
				}
			}
		}

		if (target != null) 
		{
			raytraceresult = new RayTraceResult(target);
		}

		if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) raytraceresult.entityHit;

			if (player.capabilities.disableDamage || (shooter instanceof EntityPlayer
					&& !((EntityPlayer) shooter).canAttackPlayer(player)))
			{
				raytraceresult = null;
			}
		}

		return raytraceresult;
	}

	/**
	 * Similar to the all look target entity version, but this method returns a List of all EntityMob entities
	 * that are within the entity's field of vision, up to a certain range and distance away
	 * 
	 * Modified by Spikybum Jolteon
	 */
	//CHANGE: deprecated
	/*
	public static final List<EntityMob> acquireAllLookMobTargets(EntityLivingBase seeker, int distance, double radius) 
	{
		if (distance < 0 || distance > MAX_DISTANCE) 
		{
			distance = MAX_DISTANCE;
		}

		List<EntityMob> targets = new ArrayList<EntityMob>();
		Vec3d vec3 = seeker.getLookVec();
		double targetX = seeker.posX;
		double targetY = seeker.posY + seeker.getEyeHeight() - 0.10000000149011612D;
		double targetZ = seeker.posZ;
		double distanceTraveled = 0;

		while ((int) distanceTraveled < distance)
		{
			targetX += vec3.x;
			targetY += vec3.y;
			targetZ += vec3.z;
			distanceTraveled += vec3.lengthVector();

			List<EntityMob> list = seeker.world.getEntitiesWithinAABB(EntityMob.class,
					new AxisAlignedBB(targetX-radius, targetY-radius, targetZ-radius, targetX+radius, targetY+radius, targetZ+radius));

			for (EntityMob target : list)
			{
				if (target != seeker && target.canBeCollidedWith() && isTargetInSight(vec3, seeker, target))
				{
					if (!targets.contains(target))
					{
						targets.add(target);
					}
				}
			}
		}

		return targets;
	}
	 */

	//CHANGE: altered searching method
	public static final List<EntityMob> acquireAllLookMobTargetsAltered(EntityLivingBase seeker, double range, double radius)
	{
		List<EntityMob> targets = new ArrayList<EntityMob>();

		List<EntityMob> list = seeker.world.getEntitiesWithinAABB(EntityMob.class,
				new AxisAlignedBB(seeker.posX-range, seeker.posY-range, seeker.posZ-range,
						seeker.posX+range, seeker.posY+range, seeker.posZ+range));

		for (EntityMob target : list)
		{
			Vec3d relativePos = getRelativePos(seeker, target);
			double distance = relativePos.lengthVector();
			if (target != seeker && target.canBeCollidedWith())
			{
				if (!targets.contains(target))
				{
					if (isTargetInSight(seeker.getLookVec(), seeker, target, 30) && distance <= range) {
						targets.add(target);
					} else if (isTargetInSight(seeker.getLookVec(), seeker, target, 180) && distance <= radius) {
						targets.add(target);
					}
				}
			}
		}

		return targets;
	}
	
	/**
	 * Returns a list of EntityMobs closest to the point at which the entity is looking and within the distance and radius specified
	 * @param distance max distance to check for target, in blocks; negative value will check to MAX_DISTANCE
	 * @param radius max distance, in blocks, to search on either side of the vector's path
	 * @param numberOfTargets max number of closest targets sought
	 * @return the entity list the seeker is looking at or null if no entity within sight search range
	 * 
	 * Modified by Spikybum Jolteon
	 */
	//CHANGE: deprecated
	/*
	public static final List<EntityMob> acquireNearestLookMobTargetList(EntityLivingBase seeker, int distance, double radius, int numberOfTargets) 
	{
		List<EntityMob> targets = new ArrayList<EntityMob>();

		targets = acquireAllLookMobTargets(seeker, distance, radius);

		List<EntityMob> closestMobsArray = new ArrayList<EntityMob>();

		int counter = 0;
		boolean isListExhausted = false;

		while(counter < numberOfTargets && !isListExhausted)
		{
			double currentDistance = MAX_DISTANCE_SQ;

			EntityMob currentTarget = null;

			for (EntityMob target : targets) 
			{
				if(!closestMobsArray.contains(target))
				{
					double newDistance = target.getDistanceSq(seeker);

					if (newDistance < currentDistance) 
					{
						currentTarget = target;
						currentDistance = newDistance;

						//System.out.println( "Test Entity Detected By Dance of Arrows" );
						//System.out.println( "Target Found " + Integer.toString( target.getEntityId() ) );
					}
				}
			}

			if(currentTarget != null)
			{
				closestMobsArray.add(currentTarget);
			}
			else
			{
				isListExhausted = true;
			}

			counter++;
		}

		return closestMobsArray;
	}
	 */

	//CHANGE: altered searching method
	public static final List<EntityMob> acquireNearestLookMobTargetListAltered(EntityLivingBase seeker, double range, double radius, int numberOfTargets)
	{
		List<EntityMob> targets = new ArrayList<EntityMob>();

		targets = acquireAllLookMobTargetsAltered(seeker, range, radius);

		List<EntityMob> closestMobsArray = new ArrayList<EntityMob>();

		int counter = 0;
		boolean isListExhausted = false;

		while(counter < numberOfTargets && !isListExhausted)
		{
			double currentDistance = MAX_DISTANCE_SQ;

			EntityMob currentTarget = null;

			for (EntityMob target : targets)
			{
				if(!closestMobsArray.contains(target))
				{
					double newDistance = target.getDistanceSq(seeker);

					if (newDistance < currentDistance)
					{
						currentTarget = target;
						currentDistance = newDistance;

						//System.out.println( "Test Entity Detected By Dance of Arrows" );
						//System.out.println( "Target Found " + Integer.toString( target.getEntityId() ) );
					}
				}
			}

			if(currentTarget != null)
			{
				closestMobsArray.add(currentTarget);
			}
			else
			{
				isListExhausted = true;
			}

			counter++;
		}

		return closestMobsArray;
	}

	/**
	 * Returns whether the target is in the seeker's field of view based on relative position
	 * @param fov seeker's field of view; a wider angle returns true more often
	 */
	//CHANGE: deprecated
	/*
	public static final boolean isTargetInFrontOf(Entity seeker, Entity target, float fov) 
	{
		// thanks again to Battlegear2 for the following code snippet
		double dx = target.posX - seeker.posX;
		double dz;

		for (dz = target.posZ - seeker.posZ; dx * dx + dz * dz < 1.0E-4D; dz = (Math.random() - Math.random()) * 0.01D) 
		{
			dx = (Math.random() - Math.random()) * 0.01D;
		}

		while (seeker.rotationYaw > 360) { seeker.rotationYaw -= 360; }
		while (seeker.rotationYaw < -360) { seeker.rotationYaw += 360; }

		float yaw = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - seeker.rotationYaw;
		yaw = yaw - 90;

		while (yaw < -180) { yaw += 360; }
		while (yaw >= 180) { yaw -= 360; }

		return yaw < fov && yaw > -fov;
	}
	 */

	/**
	 * Returns true if the target's position is within the area that the seeker is facing and the target can be seen
	 */
	//CHANGE: deprecated
	/*
	public static final boolean isTargetInSight(EntityLivingBase seeker, Entity target) 
	{
		return isTargetInSight(seeker.getLookVec(), seeker, target);
	}
	 */

	/**
	 * Returns true if the target's position is within the area that the seeker is facing and the target can be seen
	 */
	//CHANGE: deprecated
	/*
	private static final boolean isTargetInSight(Vec3d vec3, EntityLivingBase seeker, Entity target) 
	{
		return seeker.canEntityBeSeen(target) && isTargetInFrontOf(seeker, target, 60);
	}
	 */

	//CHANGE: another searching method
	private static final boolean isTargetInSight(Vec3d seekerLook, EntityLivingBase seeker, Entity target, float fov)
	{
		if (!seeker.canEntityBeSeen(target)) {
			return false;
		}
		Vec3d relativePos = getRelativePos(seeker, target);
		Vec3d seekerLookNormalized = seekerLook.normalize();
		Vec3d relativePosNormalized = relativePos.normalize();
		if (relativePosNormalized.dotProduct(seekerLookNormalized)>=Math.cos(fov * Math.PI / 180.0F)) {
			return true;
		}
		return false;
	}

	//CHANGE: get relative position
	public static final Vec3d getRelativePos(Entity seeker, Entity target) {
		Vec3d seekerPos = new Vec3d(seeker.posX, seeker.posY + seeker.getEyeHeight(), seeker.posZ);
		Vec3d targetPos = new Vec3d(target.posX, target.posY + target.getEyeHeight(), target.posZ);
		Vec3d relativePos = targetPos.subtract(seekerPos);
		return relativePos;
	}

	public static final void applyHunterBoltSettings(EntityHunterBolt bolt, ItemStack bow, float charge) 
	{
		applyHunterBoltSettings(bolt, bow, charge, 1F);
	}
	
	public static final void applyHunterBoltSettings(EntityHunterBolt bolt, ItemStack bow, float charge, float modifier) 
	{
		if (charge < 0.0F) { charge = 0.0F; }
		if (charge > 1.0F) { charge = 1.0F; }

		if (charge == 1.0F) { bolt.setIsCritical(true); }

		//CHANGE: no more enchantment
		/*
		int k = (int)Math.floor(EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, bow) * modifier);

		if (k > 0) { bolt.setDamage(bolt.getDamage() + ((double) k * 0.5D + 0.5D)); }

		int l = (int)Math.floor(EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, bow) * modifier);

		if (l > 0) { bolt.setKnockbackStrength(l); }

		if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, bow) > 0 && modifier >= 1F) 
		{
			bolt.setFire(100);
		}
		*/
	}
}