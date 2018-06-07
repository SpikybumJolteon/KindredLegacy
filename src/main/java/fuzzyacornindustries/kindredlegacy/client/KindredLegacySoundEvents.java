package fuzzyacornindustries.kindredlegacy.client;

import java.util.HashSet;
import java.util.Set;

import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class KindredLegacySoundEvents 
{
	public static final SoundEvent ARMORED_LUXRAY_DEATH;
	public static final SoundEvent ARMORED_LUXRAY_HURT;
	public static final SoundEvent ARMORED_LUXRAY_AMBIENT;
	
	public static final SoundEvent ARMORED_SHINX_DEATH;
	public static final SoundEvent ARMORED_SHINX_HURT;
	public static final SoundEvent ARMORED_SHINX_AMBIENT;

	public static final SoundEvent BANDERSNATCH_FENNEKIN_DEATH;
	public static final SoundEvent BANDERSNATCH_FENNEKIN_HURT;
	public static final SoundEvent BANDERSNATCH_FENNEKIN_AMBIENT;

	public static final SoundEvent BLOODMOON_NINETALES_ATTACK;
	public static final SoundEvent BLOODMOON_NINETALES_DEATH;
	public static final SoundEvent BLOODMOON_NINETALES_EXERT_SELF;
	public static final SoundEvent BLOODMOON_NINETALES_GASP;
	public static final SoundEvent BLOODMOON_NINETALES_HURT;
	public static final SoundEvent BLOODMOON_NINETALES_LAUGH;
	
	public static final SoundEvent CLAY_COMMANDER_DELCATTY_DEATH;
	public static final SoundEvent CLAY_COMMANDER_DELCATTY_HURT;
	public static final SoundEvent CLAY_COMMANDER_DELCATTY_AMBIENT;

	public static final SoundEvent CLAY_ESPURR_DEATH;
	public static final SoundEvent CLAY_ESPURR_HURT;
	public static final SoundEvent CLAY_ESPURR_AMBIENT;

	public static final SoundEvent CLAY_LUXIO_DEATH;
	public static final SoundEvent CLAY_LUXIO_HURT;
	public static final SoundEvent CLAY_LUXIO_AMBIENT;

	public static final SoundEvent CLAY_PURRLOIN_DEATH;
	public static final SoundEvent CLAY_PURRLOIN_HURT;
	public static final SoundEvent CLAY_PURRLOIN_AMBIENT;

	public static final SoundEvent CLAY_SHINX_DEATH;
	public static final SoundEvent CLAY_SHINX_HURT;
	public static final SoundEvent CLAY_SHINX_AMBIENT;

	public static final SoundEvent CLAY_SKITTY_DEATH;
	public static final SoundEvent CLAY_SKITTY_HURT;
	public static final SoundEvent CLAY_SKITTY_AMBIENT;

	public static final SoundEvent DEMON_VULPIX_DEATH;
	public static final SoundEvent DEMON_VULPIX_HURT;
	public static final SoundEvent DEMON_VULPIX_AMBIENT;

	public static final SoundEvent FEYWOOD_ABSOL_DEATH;
	public static final SoundEvent FEYWOOD_ABSOL_HURT;
	public static final SoundEvent FEYWOOD_ABSOL_AMBIENT;
	public static final SoundEvent FEYWOOD_ABSOL_WHINE;

	public static final SoundEvent FIRECRACKER_LITTEN_DEATH;
	public static final SoundEvent FIRECRACKER_LITTEN_HURT;
	public static final SoundEvent FIRECRACKER_LITTEN_AMBIENT;
	public static final SoundEvent FIRECRACKER_LITTEN_WHINE;

	public static final SoundEvent FORCEWIND_EELEKTRIK_DEATH;
	public static final SoundEvent FORCEWIND_EELEKTRIK_HURT;
	public static final SoundEvent FORCEWIND_EELEKTRIK_AMBIENT;

	public static final SoundEvent FOXCRAFT_FENNEKIN_DEATH;
	public static final SoundEvent FOXCRAFT_FENNEKIN_HURT;
	public static final SoundEvent FOXCRAFT_FENNEKIN_AMBIENT;
	public static final SoundEvent FOXCRAFT_FENNEKIN_WHINE;

	public static final SoundEvent FOXFIRE_ZORUA_DEATH;
	public static final SoundEvent FOXFIRE_ZORUA_HURT;
	public static final SoundEvent FOXFIRE_ZORUA_AMBIENT;
	public static final SoundEvent FOXFIRE_ZORUA_WHINE;

	public static final SoundEvent INFESTED_DEERLING_DEATH;
	public static final SoundEvent INFESTED_DEERLING_HURT;
	public static final SoundEvent INFESTED_DEERLING_AMBIENT;

	public static final SoundEvent MU_BUNEARY_DEATH;
	public static final SoundEvent MU_BUNEARY_HURT;
	public static final SoundEvent MU_BUNEARY_AMBIENT;

	public static final SoundEvent OKAMI_ESPEON_DEATH;
	public static final SoundEvent OKAMI_ESPEON_HURT;
	public static final SoundEvent OKAMI_ESPEON_AMBIENT;
	public static final SoundEvent OKAMI_ESPEON_WHINE;

	public static final SoundEvent OKAMI_SYLVEON_DEATH;
	public static final SoundEvent OKAMI_SYLVEON_HURT;
	public static final SoundEvent OKAMI_SYLVEON_AMBIENT;
	public static final SoundEvent OKAMI_SYLVEON_WHINE;

	public static final SoundEvent OKAMI_UMBREON_DEATH;
	public static final SoundEvent OKAMI_UMBREON_HURT;
	public static final SoundEvent OKAMI_UMBREON_AMBIENT;
	public static final SoundEvent OKAMI_UMBREON_WHINE;

	public static final SoundEvent SNOW_SORCERESS_BRAIXEN_DEATH;
	public static final SoundEvent SNOW_SORCERESS_BRAIXEN_HURT;
	public static final SoundEvent SNOW_SORCERESS_BRAIXEN_AMBIENT;

	public static final SoundEvent SWORDIE_MIENSHAO_DEATH;
	public static final SoundEvent SWORDIE_MIENSHAO_HURT;
	public static final SoundEvent SWORDIE_MIENSHAO_AMBIENT;

	public static final SoundEvent VASTAYA_NINETALES_DEATH;
	public static final SoundEvent VASTAYA_NINETALES_HURT;
	public static final SoundEvent VASTAYA_NINETALES_AMBIENT;
	public static final SoundEvent VASTAYA_NINETALES_WHINE;

	public static final SoundEvent VOORST_MIGHTYENA_DEATH;
	public static final SoundEvent VOORST_MIGHTYENA_HURT;
	public static final SoundEvent VOORST_MIGHTYENA_AMBIENT;

	public static final SoundEvent ZERGLING_NINCADA_DEATH;
	public static final SoundEvent ZERGLING_NINCADA_HURT;
	public static final SoundEvent ZERGLING_NINCADA_AMBIENT;
	public static final SoundEvent ZERGLING_NINCADA_SOAR;

	public static final SoundEvent BIO;
	public static final SoundEvent FIREBALL_SWOOSH;
	public static final SoundEvent FLASH;
	public static final SoundEvent IGNITE;

	public static final SoundEvent BOW_OF_KINDRED_HIT;
	public static final SoundEvent BOW_OF_KINDRED_SHOOT;

	static 
	{
		ARMORED_LUXRAY_DEATH = registerSound("armored_luxray.death");
		ARMORED_LUXRAY_HURT = registerSound("armored_luxray.hurt");
		ARMORED_LUXRAY_AMBIENT = registerSound("armored_luxray.ambient");
		
		ARMORED_SHINX_DEATH = registerSound("armored_shinx.death");
		ARMORED_SHINX_HURT = registerSound("armored_shinx.hurt");
		ARMORED_SHINX_AMBIENT = registerSound("armored_shinx.ambient");

		BANDERSNATCH_FENNEKIN_DEATH = registerSound("bandersnatch_fennekin.death");
		BANDERSNATCH_FENNEKIN_HURT = registerSound("bandersnatch_fennekin.hurt");
		BANDERSNATCH_FENNEKIN_AMBIENT = registerSound("bandersnatch_fennekin.ambient");

		BLOODMOON_NINETALES_ATTACK = registerSound("bloodmoon_ninetales.attack");
		BLOODMOON_NINETALES_DEATH = registerSound("bloodmoon_ninetales.death");
		BLOODMOON_NINETALES_EXERT_SELF = registerSound("bloodmoon_ninetales.exert_self");
		BLOODMOON_NINETALES_GASP = registerSound("bloodmoon_ninetales.gasp");
		BLOODMOON_NINETALES_HURT = registerSound("bloodmoon_ninetales.hurt");
		BLOODMOON_NINETALES_LAUGH = registerSound("bloodmoon_ninetales.laugh");
		
		CLAY_COMMANDER_DELCATTY_DEATH = registerSound("clay_commander_delcatty.death");
		CLAY_COMMANDER_DELCATTY_HURT = registerSound("clay_commander_delcatty.hurt");
		CLAY_COMMANDER_DELCATTY_AMBIENT = registerSound("clay_commander_delcatty.ambient");

		CLAY_ESPURR_DEATH = registerSound("clay_espurr.death");
		CLAY_ESPURR_HURT = registerSound("clay_espurr.hurt");
		CLAY_ESPURR_AMBIENT = registerSound("clay_espurr.ambient");

		CLAY_LUXIO_DEATH = registerSound("clay_luxio.death");
		CLAY_LUXIO_HURT = registerSound("clay_luxio.hurt");
		CLAY_LUXIO_AMBIENT = registerSound("clay_luxio.ambient");

		CLAY_PURRLOIN_DEATH = registerSound("clay_purrloin.death");
		CLAY_PURRLOIN_HURT = registerSound("clay_purrloin.hurt");
		CLAY_PURRLOIN_AMBIENT = registerSound("clay_purrloin.ambient");

		CLAY_SHINX_DEATH = registerSound("clay_shinx.death");
		CLAY_SHINX_HURT = registerSound("clay_shinx.hurt");
		CLAY_SHINX_AMBIENT = registerSound("clay_shinx.ambient");

		CLAY_SKITTY_DEATH = registerSound("clay_skitty.death");
		CLAY_SKITTY_HURT = registerSound("clay_skitty.hurt");
		CLAY_SKITTY_AMBIENT = registerSound("clay_skitty.ambient");

		DEMON_VULPIX_DEATH = registerSound("demon_vulpix.death");
		DEMON_VULPIX_HURT = registerSound("demon_vulpix.hurt");
		DEMON_VULPIX_AMBIENT = registerSound("demon_vulpix.ambient");

		FIRECRACKER_LITTEN_DEATH = registerSound("firecracker_litten.death");
		FIRECRACKER_LITTEN_HURT = registerSound("firecracker_litten.hurt");
		FIRECRACKER_LITTEN_AMBIENT = registerSound("firecracker_litten.ambient");
		FIRECRACKER_LITTEN_WHINE = registerSound("firecracker_litten.whine");

		FEYWOOD_ABSOL_DEATH = registerSound("feywood_absol.death");
		FEYWOOD_ABSOL_HURT = registerSound("feywood_absol.hurt");
		FEYWOOD_ABSOL_AMBIENT = registerSound("feywood_absol.ambient");
		FEYWOOD_ABSOL_WHINE = registerSound("feywood_absol.whine");

		FORCEWIND_EELEKTRIK_DEATH = registerSound("forcewind_eelektrik.death");
		FORCEWIND_EELEKTRIK_HURT = registerSound("forcewind_eelektrik.hurt");
		FORCEWIND_EELEKTRIK_AMBIENT = registerSound("forcewind_eelektrik.ambient");

		FOXCRAFT_FENNEKIN_DEATH = registerSound("foxcraft_fennekin.death");
		FOXCRAFT_FENNEKIN_HURT = registerSound("foxcraft_fennekin.hurt");
		FOXCRAFT_FENNEKIN_AMBIENT = registerSound("foxcraft_fennekin.ambient");
		FOXCRAFT_FENNEKIN_WHINE = registerSound("foxcraft_fennekin.whine");

		FOXFIRE_ZORUA_DEATH = registerSound("foxfire_zorua.death");
		FOXFIRE_ZORUA_HURT = registerSound("foxfire_zorua.hurt");
		FOXFIRE_ZORUA_AMBIENT = registerSound("foxfire_zorua.ambient");
		FOXFIRE_ZORUA_WHINE = registerSound("foxfire_zorua.whine");

		INFESTED_DEERLING_DEATH = registerSound("infested_deerling.death");
		INFESTED_DEERLING_HURT = registerSound("infested_deerling.hurt");
		INFESTED_DEERLING_AMBIENT = registerSound("infested_deerling.ambient");

		MU_BUNEARY_DEATH = registerSound("mu_buneary.death");
		MU_BUNEARY_HURT = registerSound("mu_buneary.hurt");
		MU_BUNEARY_AMBIENT = registerSound("mu_buneary.ambient");

		OKAMI_ESPEON_DEATH = registerSound("okami_espeon.death");
		OKAMI_ESPEON_HURT = registerSound("okami_espeon.hurt");
		OKAMI_ESPEON_AMBIENT = registerSound("okami_espeon.ambient");
		OKAMI_ESPEON_WHINE = registerSound("okami_espeon.whine");

		OKAMI_SYLVEON_DEATH = registerSound("okami_sylveon.death");
		OKAMI_SYLVEON_HURT = registerSound("okami_sylveon.hurt");
		OKAMI_SYLVEON_AMBIENT = registerSound("okami_sylveon.ambient");
		OKAMI_SYLVEON_WHINE = registerSound("okami_sylveon.whine");

		OKAMI_UMBREON_DEATH = registerSound("okami_umbreon.death");
		OKAMI_UMBREON_HURT = registerSound("okami_umbreon.hurt");
		OKAMI_UMBREON_AMBIENT = registerSound("okami_umbreon.ambient");
		OKAMI_UMBREON_WHINE = registerSound("okami_umbreon.whine");

		SNOW_SORCERESS_BRAIXEN_DEATH = registerSound("snow_sorceress_braixen.death");
		SNOW_SORCERESS_BRAIXEN_HURT = registerSound("snow_sorceress_braixen.hurt");
		SNOW_SORCERESS_BRAIXEN_AMBIENT = registerSound("snow_sorceress_braixen.ambient");

		SWORDIE_MIENSHAO_DEATH = registerSound("swordie_mienshao.death");
		SWORDIE_MIENSHAO_HURT = registerSound("swordie_mienshao.hurt");
		SWORDIE_MIENSHAO_AMBIENT = registerSound("swordie_mienshao.ambient");

		VASTAYA_NINETALES_DEATH = registerSound("vastaya_ninetales.death");
		VASTAYA_NINETALES_HURT = registerSound("vastaya_ninetales.hurt");
		VASTAYA_NINETALES_AMBIENT = registerSound("vastaya_ninetales.ambient");
		VASTAYA_NINETALES_WHINE = registerSound("vastaya_ninetales.whine");

		VOORST_MIGHTYENA_DEATH = registerSound("voorst_mightyena.death");
		VOORST_MIGHTYENA_HURT = registerSound("voorst_mightyena.hurt");
		VOORST_MIGHTYENA_AMBIENT = registerSound("voorst_mightyena.ambient");

		ZERGLING_NINCADA_DEATH = registerSound("zergling_nincada.death");
		ZERGLING_NINCADA_HURT = registerSound("zergling_nincada.hurt");
		ZERGLING_NINCADA_AMBIENT = registerSound("zergling_nincada.ambient");
		ZERGLING_NINCADA_SOAR = registerSound("zergling_nincada.soar");

		BIO = registerSound("effect.bio");
		FIREBALL_SWOOSH = registerSound("effect.fireball_swoosh");
		FLASH = registerSound("effect.flash");
		IGNITE = registerSound("effect.ignite");

		BOW_OF_KINDRED_HIT = registerSound("bow_of_kindred.hit");
		BOW_OF_KINDRED_SHOOT = registerSound("bow_of_kindred.shoot");
	}

	public static void registerSounds() 
	{
		// Dummy method to make sure the static initialiser runs
	}

	/**
	 * @param soundName The SoundEvent's name without the KindredLegacy prefix
	 * @return The SoundEvent
	 */
	private static SoundEvent registerSound(String soundName) 
	{
		final ResourceLocation soundID = new ResourceLocation(ModInfo.MOD_ID, soundName);
		return new SoundEvent(soundID).setRegistryName(soundID);
	}
	

	@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
	public static class RegistrationHandler 
	{
		public static final Set<SoundEvent> SOUNDS = new HashSet<>();

		/**
		 * Register this mod's {@link Blocks}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<SoundEvent> event) 
		{
			final SoundEvent[] sounds = {
					ARMORED_LUXRAY_DEATH,
					ARMORED_LUXRAY_HURT,
					ARMORED_LUXRAY_AMBIENT,
					ARMORED_SHINX_DEATH,
					ARMORED_SHINX_HURT,
					ARMORED_SHINX_AMBIENT,
					BANDERSNATCH_FENNEKIN_DEATH,
					BANDERSNATCH_FENNEKIN_HURT,
					BANDERSNATCH_FENNEKIN_AMBIENT,
					BLOODMOON_NINETALES_ATTACK,
					BLOODMOON_NINETALES_DEATH,
					BLOODMOON_NINETALES_EXERT_SELF,
					BLOODMOON_NINETALES_GASP,
					BLOODMOON_NINETALES_HURT,
					BLOODMOON_NINETALES_LAUGH,
					CLAY_COMMANDER_DELCATTY_DEATH,
					CLAY_COMMANDER_DELCATTY_HURT,
					CLAY_COMMANDER_DELCATTY_AMBIENT,
					CLAY_ESPURR_DEATH,
					CLAY_ESPURR_HURT,
					CLAY_ESPURR_AMBIENT,
					CLAY_PURRLOIN_DEATH,
					CLAY_PURRLOIN_HURT,
					CLAY_PURRLOIN_AMBIENT,
					CLAY_SHINX_DEATH,
					CLAY_SHINX_HURT,
					CLAY_SHINX_AMBIENT,
					CLAY_SKITTY_DEATH,
					CLAY_SKITTY_HURT,
					CLAY_SKITTY_AMBIENT,
					DEMON_VULPIX_DEATH,
					DEMON_VULPIX_HURT,
					DEMON_VULPIX_AMBIENT,
					FEYWOOD_ABSOL_DEATH,
					FEYWOOD_ABSOL_HURT,
					FEYWOOD_ABSOL_AMBIENT,
					FEYWOOD_ABSOL_WHINE,
					FIRECRACKER_LITTEN_DEATH,
					FIRECRACKER_LITTEN_HURT,
					FIRECRACKER_LITTEN_AMBIENT,
					FIRECRACKER_LITTEN_WHINE,
					FORCEWIND_EELEKTRIK_DEATH,
					FORCEWIND_EELEKTRIK_HURT,
					FORCEWIND_EELEKTRIK_AMBIENT,
					FOXCRAFT_FENNEKIN_DEATH,
					FOXCRAFT_FENNEKIN_HURT,
					FOXCRAFT_FENNEKIN_AMBIENT,
					FOXCRAFT_FENNEKIN_WHINE,
					INFESTED_DEERLING_DEATH,
					INFESTED_DEERLING_HURT,
					INFESTED_DEERLING_AMBIENT,
					MU_BUNEARY_DEATH,
					MU_BUNEARY_HURT,
					MU_BUNEARY_AMBIENT,
					OKAMI_ESPEON_DEATH,
					OKAMI_ESPEON_HURT,
					OKAMI_ESPEON_AMBIENT,
					OKAMI_ESPEON_WHINE,
					OKAMI_SYLVEON_DEATH,
					OKAMI_SYLVEON_HURT,
					OKAMI_SYLVEON_AMBIENT,
					OKAMI_SYLVEON_WHINE,
					OKAMI_UMBREON_DEATH,
					OKAMI_UMBREON_HURT,
					OKAMI_UMBREON_AMBIENT,
					OKAMI_UMBREON_WHINE,
					SNOW_SORCERESS_BRAIXEN_DEATH,
					SNOW_SORCERESS_BRAIXEN_HURT,
					SNOW_SORCERESS_BRAIXEN_AMBIENT,
					SWORDIE_MIENSHAO_DEATH,
					SWORDIE_MIENSHAO_HURT,
					SWORDIE_MIENSHAO_AMBIENT,
					VASTAYA_NINETALES_DEATH,
					VASTAYA_NINETALES_HURT,
					VASTAYA_NINETALES_AMBIENT,
					VASTAYA_NINETALES_WHINE,
					VOORST_MIGHTYENA_DEATH,
					VOORST_MIGHTYENA_HURT,
					VOORST_MIGHTYENA_AMBIENT,
					ZERGLING_NINCADA_DEATH,
					ZERGLING_NINCADA_HURT,
					ZERGLING_NINCADA_AMBIENT,
					ZERGLING_NINCADA_SOAR,
					BIO,
					FIREBALL_SWOOSH,
					FLASH,
					IGNITE,
					BOW_OF_KINDRED_HIT,
					BOW_OF_KINDRED_SHOOT,
			};
			
			final IForgeRegistry<SoundEvent> registry = event.getRegistry();

			for (final SoundEvent sound : sounds) 
			{
				registry.register(sound);
				SOUNDS.add(sound);
			}
		}
	}
}