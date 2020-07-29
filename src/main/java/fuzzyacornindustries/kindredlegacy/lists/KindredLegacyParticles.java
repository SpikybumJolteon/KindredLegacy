package fuzzyacornindustries.kindredlegacy.lists;

import fuzzyacornindustries.kindredlegacy.client.model.particle.ConfuseParticle;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class KindredLegacyParticles 
{
	public static final BasicParticleType CONFUSE_PARTICLE = createBasicParticleType(false, "confuse_particle");

	@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleTypes {

		@SubscribeEvent
		public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) 
		{
			event.getRegistry().registerAll(
					CONFUSE_PARTICLE
					);
		}

	}

	@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegisterParticleFactories {

		@SubscribeEvent
		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) {
			Minecraft.getInstance().particles.registerFactory(CONFUSE_PARTICLE, ConfuseParticle.Factory::new);
		}

	}

	private static BasicParticleType createBasicParticleType(boolean alwaysShow, String name) 
	{
		BasicParticleType particleType = new BasicParticleType(alwaysShow);
		particleType.setRegistryName(ModInfo.MOD_ID, name);
		return particleType;
	}
}