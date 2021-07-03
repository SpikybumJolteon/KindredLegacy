package fuzzyacornindustries.kindredlegacy.common.core;

import fuzzyacornindustries.kindredlegacy.lib.Names;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds
{
	public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Names.MOD_ID);
	
	public static final RegistryObject<SoundEvent> ARMORED_LUXRAY_DEATH = register("entity.armored_luxray.death");
	public static final RegistryObject<SoundEvent> ARMORED_LUXRAY_HURT = register("entity.armored_luxray.hurt");
	public static final RegistryObject<SoundEvent> ARMORED_LUXRAY_AMBIENT = register("entity.armored_luxray.ambient");

	public static final RegistryObject<SoundEvent> ARMORED_SHINX_DEATH = register("entity.armored_shinx.death");
	public static final RegistryObject<SoundEvent> ARMORED_SHINX_HURT = register("entity.armored_shinx.hurt");
	public static final RegistryObject<SoundEvent> ARMORED_SHINX_AMBIENT = register("entity.armored_shinx.ambient");

	public static final RegistryObject<SoundEvent> BANDERSNATCH_FENNEKIN_DEATH = register("entity.bandersnatch_fennekin.death");
	public static final RegistryObject<SoundEvent> BANDERSNATCH_FENNEKIN_HURT = register("entity.bandersnatch_fennekin.hurt");
	public static final RegistryObject<SoundEvent> BANDERSNATCH_FENNEKIN_AMBIENT = register("entity.bandersnatch_fennekin.ambient");

	public static final RegistryObject<SoundEvent> BLOODMOON_NINETALES_ATTACK = register("entity.bloodmoon_ninetales.attack");
	public static final RegistryObject<SoundEvent> BLOODMOON_NINETALES_DEATH = register("entity.bloodmoon_ninetales.death");
	public static final RegistryObject<SoundEvent> BLOODMOON_NINETALES_EXERT_SELF = register("entity.bloodmoon_ninetales.exert_self");
	public static final RegistryObject<SoundEvent> BLOODMOON_NINETALES_GASP = register("entity.bloodmoon_ninetales.gasp");
	public static final RegistryObject<SoundEvent> BLOODMOON_NINETALES_HURT = register("entity.bloodmoon_ninetales.hurt");
	public static final RegistryObject<SoundEvent> BLOODMOON_NINETALES_LAUGH = register("entity.bloodmoon_ninetales.laugh");

	public static final RegistryObject<SoundEvent> CLAY_COMMANDER_DELCATTY_DEATH = register("entity.clay_commander_delcatty.death");
	public static final RegistryObject<SoundEvent> CLAY_COMMANDER_DELCATTY_HURT = register("entity.clay_commander_delcatty.hurt");
	public static final RegistryObject<SoundEvent> CLAY_COMMANDER_DELCATTY_AMBIENT = register("entity.clay_commander_delcatty.ambient");

	public static final RegistryObject<SoundEvent> CLAY_ESPURR_DEATH = register("entity.clay_espurr.death");
	public static final RegistryObject<SoundEvent> CLAY_ESPURR_HURT = register("entity.clay_espurr.hurt");
	public static final RegistryObject<SoundEvent> CLAY_ESPURR_AMBIENT = register("entity.clay_espurr.ambient");

	public static final RegistryObject<SoundEvent> CLAY_LUXIO_DEATH = register("entity.clay_luxio.death");
	public static final RegistryObject<SoundEvent> CLAY_LUXIO_HURT = register("entity.clay_luxio.hurt");
	public static final RegistryObject<SoundEvent> CLAY_LUXIO_AMBIENT = register("entity.clay_luxio.ambient");

	public static final RegistryObject<SoundEvent> CLAY_PURRLOIN_DEATH = register("entity.clay_purrloin.death");
	public static final RegistryObject<SoundEvent> CLAY_PURRLOIN_HURT = register("entity.clay_purrloin.hurt");
	public static final RegistryObject<SoundEvent> CLAY_PURRLOIN_AMBIENT = register("entity.clay_purrloin.ambient");

	public static final RegistryObject<SoundEvent> CLAY_SHINX_DEATH = register("entity.clay_shinx.death");
	public static final RegistryObject<SoundEvent> CLAY_SHINX_HURT = register("entity.clay_shinx.hurt");
	public static final RegistryObject<SoundEvent> CLAY_SHINX_AMBIENT = register("entity.clay_shinx.ambient");

	public static final RegistryObject<SoundEvent> CLAY_SKITTY_DEATH = register("entity.clay_skitty.death");
	public static final RegistryObject<SoundEvent> CLAY_SKITTY_HURT = register("entity.clay_skitty.hurt");
	public static final RegistryObject<SoundEvent> CLAY_SKITTY_AMBIENT = register("entity.clay_skitty.ambient");

	//	public static SoundEvent DEMON_VULPIX_DEATH;
	//	public static SoundEvent DEMON_VULPIX_HURT;
	//	public static SoundEvent DEMON_VULPIX_AMBIENT;

	public static final RegistryObject<SoundEvent> FEYWOOD_ABSOL_DEATH = register("entity.feywood_absol.death");
	public static final RegistryObject<SoundEvent> FEYWOOD_ABSOL_HURT = register("entity.feywood_absol.hurt");
	public static final RegistryObject<SoundEvent> FEYWOOD_ABSOL_AMBIENT = register("entity.feywood_absol.ambient");
	public static final RegistryObject<SoundEvent> FEYWOOD_ABSOL_WHINE = register("entity.feywood_absol.whine");

	public static final RegistryObject<SoundEvent> FIRECRACKER_LITTEN_DEATH = register("entity.firecracker_litten.death");
	public static final RegistryObject<SoundEvent> FIRECRACKER_LITTEN_HURT = register("entity.firecracker_litten.hurt");
	public static final RegistryObject<SoundEvent> FIRECRACKER_LITTEN_AMBIENT = register("entity.firecracker_litten.ambient");
	public static final RegistryObject<SoundEvent> FIRECRACKER_LITTEN_WHINE = register("entity.firecracker_litten.whine");

	//	public static SoundEvent FORCEWIND_EELEKTRIK_DEATH;
	//	public static SoundEvent FORCEWIND_EELEKTRIK_HURT;
	//	public static SoundEvent FORCEWIND_EELEKTRIK_AMBIENT;

	//	public static SoundEvent FOXCRAFT_FENNEKIN_DEATH;
	//	public static SoundEvent FOXCRAFT_FENNEKIN_HURT;
	//	public static SoundEvent FOXCRAFT_FENNEKIN_AMBIENT;
	//	public static SoundEvent FOXCRAFT_FENNEKIN_WHINE;

	//	public static SoundEvent FOXFIRE_ZORUA_DEATH;
	//	public static SoundEvent FOXFIRE_ZORUA_HURT;
	//	public static SoundEvent FOXFIRE_ZORUA_AMBIENT;
	//	public static SoundEvent FOXFIRE_ZORUA_WHINE;

	//	public static SoundEvent IMMORTAL_ARCANINE_DEATH;
	//	public static SoundEvent IMMORTAL_ARCANINE_HURT;
	//	public static SoundEvent IMMORTAL_ARCANINE_AMBIENT;
	//	public static SoundEvent IMMORTAL_ARCANINE_WHINE;
	//	public static SoundEvent IMMORTAL_ARCANINE_CANNONS_SHOOT;

	public static final RegistryObject<SoundEvent> INFESTED_DEERLING_DEATH = register("entity.infested_deerling.death");
	public static final RegistryObject<SoundEvent> INFESTED_DEERLING_HURT = register("entity.infested_deerling.hurt");
	public static final RegistryObject<SoundEvent> INFESTED_DEERLING_AMBIENT = register("entity.infested_deerling.ambient");

	public static final RegistryObject<SoundEvent> MU_BUNEARY_DEATH = register("entity.mu_buneary.death");
	public static final RegistryObject<SoundEvent> MU_BUNEARY_HURT = register("entity.mu_buneary.hurt");
	public static final RegistryObject<SoundEvent> MU_BUNEARY_AMBIENT = register("entity.mu_buneary.ambient");

	public static final RegistryObject<SoundEvent> OKAMI_ESPEON_DEATH = register("entity.okami_espeon.death");
	public static final RegistryObject<SoundEvent> OKAMI_ESPEON_HURT = register("entity.okami_espeon.hurt");
	public static final RegistryObject<SoundEvent> OKAMI_ESPEON_AMBIENT = register("entity.okami_espeon.ambient");
	public static final RegistryObject<SoundEvent> OKAMI_ESPEON_WHINE = register("entity.okami_espeon.whine");

	public static final RegistryObject<SoundEvent> OKAMI_SYLVEON_DEATH = register("entity.okami_sylveon.death");
	public static final RegistryObject<SoundEvent> OKAMI_SYLVEON_HURT = register("entity.okami_sylveon.hurt");
	public static final RegistryObject<SoundEvent> OKAMI_SYLVEON_AMBIENT = register("entity.okami_sylveon.ambient");
	public static final RegistryObject<SoundEvent> OKAMI_SYLVEON_WHINE = register("entity.okami_sylveon.whine");

	public static final RegistryObject<SoundEvent> OKAMI_UMBREON_DEATH = register("entity.okami_umbreon.death");
	public static final RegistryObject<SoundEvent> OKAMI_UMBREON_HURT = register("entity.okami_umbreon.hurt");
	public static final RegistryObject<SoundEvent> OKAMI_UMBREON_AMBIENT = register("entity.okami_umbreon.ambient");
	public static final RegistryObject<SoundEvent> OKAMI_UMBREON_WHINE = register("entity.okami_umbreon.whine");

	//	public static SoundEvent PROXY_PYLON_CARBINK_DEATH;
	//	public static SoundEvent PROXY_PYLON_CARBINK_HURT;
	//	public static SoundEvent PROXY_PYLON_CARBINK_AMBIENT;

	public static final RegistryObject<SoundEvent> SNOW_SORCERESS_BRAIXEN_DEATH = register("entity.snow_sorceress_braixen.death");
	public static final RegistryObject<SoundEvent> SNOW_SORCERESS_BRAIXEN_HURT = register("entity.snow_sorceress_braixen.hurt");
	public static final RegistryObject<SoundEvent> SNOW_SORCERESS_BRAIXEN_AMBIENT = register("entity.snow_sorceress_braixen.ambient");

	//	public static SoundEvent SWORDIE_MIENSHAO_DEATH;
	//	public static SoundEvent SWORDIE_MIENSHAO_HURT;
	//	public static SoundEvent SWORDIE_MIENSHAO_AMBIENT;

	//	public static SoundEvent TIBERIUM_GROWLITHE_DEATH;
	//	public static SoundEvent TIBERIUM_GROWLITHE_HURT;
	//	public static SoundEvent TIBERIUM_GROWLITHE_AMBIENT;

	public static final RegistryObject<SoundEvent> VASTAYA_NINETALES_DEATH = register("entity.vastaya_ninetales.death");
	public static final RegistryObject<SoundEvent> VASTAYA_NINETALES_HURT = register("entity.vastaya_ninetales.hurt");
	public static final RegistryObject<SoundEvent> VASTAYA_NINETALES_AMBIENT = register("entity.vastaya_ninetales.ambient");
	public static final RegistryObject<SoundEvent> VASTAYA_NINETALES_WHINE = register("entity.vastaya_ninetales.whine");

	public static final RegistryObject<SoundEvent> VOORST_MIGHTYENA_DEATH = register("entity.voorst_mightyena.death");
	public static final RegistryObject<SoundEvent> VOORST_MIGHTYENA_HURT = register("entity.voorst_mightyena.hurt");
	public static final RegistryObject<SoundEvent> VOORST_MIGHTYENA_AMBIENT = register("entity.voorst_mightyena.ambient");

	//	public static SoundEvent ZEALOT_PAWNIARD_DEATH;
	//	public static SoundEvent ZEALOT_PAWNIARD_HURT;
	//	public static SoundEvent ZEALOT_PAWNIARD_AMBIENT;
	//	public static SoundEvent ZEALOT_PAWNIARD_SPAWN;

	public static final RegistryObject<SoundEvent> ZERGLING_NINCADA_DEATH = register("entity.zergling_nincada.death");
	public static final RegistryObject<SoundEvent> ZERGLING_NINCADA_HURT = register("entity.zergling_nincada.hurt");
	public static final RegistryObject<SoundEvent> ZERGLING_NINCADA_AMBIENT = register("entity.zergling_nincada.ambient");
	public static final RegistryObject<SoundEvent> ZERGLING_NINCADA_SOAR = register("entity.zergling_nincada.soar");

	public static final RegistryObject<SoundEvent> BIO = register("effect.bio");
	public static final RegistryObject<SoundEvent> FIREBALL_SWOOSH = register("effect.fireball_swoosh");
	public static final RegistryObject<SoundEvent> FLASH = register("effect.flash");
	public static final RegistryObject<SoundEvent> IGNITE = register("effect.ignite");

	//	public static SoundEvent BOW_OF_KINDRED_HIT;
	//	public static SoundEvent BOW_OF_KINDRED_SHOOT;

	public static void registerSounds()
	{
//		//		DEMON_VULPIX_DEATH = registerSound("entity.demon_vulpix.death");
//		//		DEMON_VULPIX_HURT = registerSound("entity.demon_vulpix.hurt");
//		//		DEMON_VULPIX_AMBIENT = registerSound("entity.demon_vulpix.ambient");
//
//		//		FORCEWIND_EELEKTRIK_DEATH = registerSound("entity.forcewind_eelektrik.death");
//		//		FORCEWIND_EELEKTRIK_HURT = registerSound("entity.forcewind_eelektrik.hurt");
//		//		FORCEWIND_EELEKTRIK_AMBIENT = registerSound("entity.forcewind_eelektrik.ambient");
//
//		//		FOXCRAFT_FENNEKIN_DEATH = registerSound("entity.foxcraft_fennekin.death");
//		//		FOXCRAFT_FENNEKIN_HURT = registerSound("entity.foxcraft_fennekin.hurt");
//		//		FOXCRAFT_FENNEKIN_AMBIENT = registerSound("entity.foxcraft_fennekin.ambient");
//		//		FOXCRAFT_FENNEKIN_WHINE = registerSound("entity.foxcraft_fennekin.whine");
//
//		//		FOXFIRE_ZORUA_DEATH = registerSound("entity.foxfire_zorua.death");
//		//		FOXFIRE_ZORUA_HURT = registerSound("entity.foxfire_zorua.hurt");
//		//		FOXFIRE_ZORUA_AMBIENT = registerSound("entity.foxfire_zorua.ambient");
//		//		FOXFIRE_ZORUA_WHINE = registerSound("entity.foxfire_zorua.whine");
//
//		//		IMMORTAL_ARCANINE_DEATH = registerSound("entity.immortal_arcanine.death");
//		//		IMMORTAL_ARCANINE_HURT = registerSound("entity.immortal_arcanine.hurt");
//		//		IMMORTAL_ARCANINE_AMBIENT = registerSound("entity.immortal_arcanine.ambient");
//		//		IMMORTAL_ARCANINE_WHINE = registerSound("entity.immortal_arcanine.whine");
//		//		IMMORTAL_ARCANINE_CANNONS_SHOOT = registerSound("entity.immortal_arcanine.cannons_shoot");
//
//		//		PROXY_PYLON_CARBINK_DEATH = registerSound("entity.proxy_pylon_carbink.death");
//		//		PROXY_PYLON_CARBINK_HURT = registerSound("entity.proxy_pylon_carbink.hurt");
//		//		PROXY_PYLON_CARBINK_AMBIENT = registerSound("entity.proxy_pylon_carbink.ambient");
//
//		//		SWORDIE_MIENSHAO_DEATH = registerSound("entity.swordie_mienshao.death");
//		//		SWORDIE_MIENSHAO_HURT = registerSound("entity.swordie_mienshao.hurt");
//		//		SWORDIE_MIENSHAO_AMBIENT = registerSound("entity.swordie_mienshao.ambient");
//
//		//		TIBERIUM_GROWLITHE_DEATH = registerSound("entity.tiberium_growlithe.death");
//		//		TIBERIUM_GROWLITHE_HURT = registerSound("entity.tiberium_growlithe.hurt");
//		//		TIBERIUM_GROWLITHE_AMBIENT = registerSound("entity.tiberium_growlithe.ambient");
//
//		//		VASTAYA_NINETALES_DEATH = registerSound("entity.vastaya_ninetales.death");
//		//		VASTAYA_NINETALES_HURT = registerSound("entity.vastaya_ninetales.hurt");
//		//		VASTAYA_NINETALES_AMBIENT = registerSound("entity.vastaya_ninetales.ambient");
//		//		VASTAYA_NINETALES_WHINE = registerSound("entity.vastaya_ninetales.whine");
//
//		//		ZEALOT_PAWNIARD_DEATH = registerSound("entity.zealot_pawniard.death");
//		//		ZEALOT_PAWNIARD_HURT = registerSound("entity.zealot_pawniard.hurt");
//		//		ZEALOT_PAWNIARD_AMBIENT = registerSound("entity.zealot_pawniard.ambient");
//		//		ZEALOT_PAWNIARD_SPAWN = registerSound("entity.zealot_pawniard.spawn");
//
		//		BOW_OF_KINDRED_HIT = registerSound("bow_of_kindred.hit");
		//		BOW_OF_KINDRED_SHOOT = registerSound("bow_of_kindred.shoot");
	}
	
	private static RegistryObject<SoundEvent> register(String name) 
	{
        return SOUNDS.register(name, () -> new SoundEvent(UtilityFunctions.location(name)));
    }
}