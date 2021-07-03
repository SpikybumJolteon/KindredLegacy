package fuzzyacornindustries.kindredlegacy.common.core;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.common.particle.ConfuseParticleData;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//public class ModParticleTypes 
//{
//	public static final BasicParticleType CONFUSE_PARTICLE = createBasicParticleType(false, "confuse_particle");
//
//	@Mod.EventBusSubscriber(modid = Names.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//	public static class RegisterParticleTypes {
//
//		@SubscribeEvent
//		public static void registerParticleTypes(RegistryEvent.Register<ParticleType<?>> event) 
//		{
//			event.getRegistry().registerAll(
//					CONFUSE_PARTICLE
//					);
//		}
//	}
//
//	@Mod.EventBusSubscriber(modid = Names.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//	public static class RegisterParticleFactories {
//
//		@SubscribeEvent
//		public static void registerParticleTypes(ParticleFactoryRegisterEvent event) 
//		{
//			Minecraft.getInstance().particles.registerFactory(CONFUSE_PARTICLE, ConfuseParticle.Factory::new);
//		}
//
//	}
//
//	private static BasicParticleType createBasicParticleType(boolean alwaysShow, String name) 
//	{
//		BasicParticleType particleType = new BasicParticleType(alwaysShow);
//		particleType.setRegistryName(Names.MOD_ID, name);
//		return particleType;
//	}
//}

public class ModParticleTypes 
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = new DeferredRegister<>(ForgeRegistries.PARTICLE_TYPES, Names.MOD_ID);

    public static final RegistryObject<ParticleType<ConfuseParticleData>> CONFUSE_PARTICLE = register("confuse_particle",
            () -> new ParticleType<>(false, ConfuseParticleData.DESERIALIZER));

    private static <T extends ParticleType<?>> RegistryObject<T> register(String name, Supplier<T> sup) 
    {
        return PARTICLES.register(name, sup);
    }
}