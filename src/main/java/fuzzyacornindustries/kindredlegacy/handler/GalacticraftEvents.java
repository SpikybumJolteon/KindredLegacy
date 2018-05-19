package fuzzyacornindustries.kindredlegacy.handler;

import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.HostilePokemon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GalacticraftEvents 
{
	@SubscribeEvent
	public void LivingJumpEvent(LivingJumpEvent event) 
	{
		if (event.getEntity() instanceof TamablePokemon || event.getEntity() instanceof HostilePokemon) 
		{
			EntityLivingBase mob = (EntityLivingBase)event.getEntityLiving();

			mob.motionY = 0.48D / WorldUtil.getGravityFactor(mob);
			if (mob.motionY < 0.24D) mob.motionY = 0.24D;

			if (mob.isSprinting())
			{
				float f = mob.rotationYaw * 0.017453292F;
				mob.motionX -= MathHelper.sin(f) * 0.2F;
				mob.motionZ += MathHelper.cos(f) * 0.2F;
			}

			mob.isAirBorne = true;
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) 
	{
		if (event.getEntity() instanceof EntityLivingBase) 
		{
			if(event.getEntity() instanceof TamablePokemon)
			{
				((TamablePokemon) event.getEntity()).worldGravity = TransformerHooks.getGravityForEntity(event.getEntity());
			}
			else if(event.getEntity() instanceof HostilePokemon)
			{
				((HostilePokemon) event.getEntity()).worldGravity = TransformerHooks.getGravityForEntity(event.getEntity());
			}
		}
	}
}