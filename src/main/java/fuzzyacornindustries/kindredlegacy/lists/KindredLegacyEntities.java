package fuzzyacornindustries.kindredlegacy.lists;

import java.util.Set;

import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ArmoredLuxrayEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ArmoredShinxEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.BandersnatchFennekinEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayCommanderDelcattyEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayEspurrEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayLuxioEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayPurrloinEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClayShinxEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ClaySkittyEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.CracklingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.InfestedDeerlingEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.MuBunearyEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.RaptorZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.SnowSorceressBraixenEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.VoorstMightyenaEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.ZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.entity.projectile.FireworkMissileEntity;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class KindredLegacyEntities 
{
	/* ======= SPAWNS ======= */
	public static ForgeConfigSpec.DoubleValue armoredLuxraySpawnRate;
	public static ForgeConfigSpec.DoubleValue armoredShinxSpawnRate;
	public static ForgeConfigSpec.DoubleValue bandersnatchFennekinSpawnRate;
	public static ForgeConfigSpec.DoubleValue bloodmoonNinetalesSpawnRate;
	public static ForgeConfigSpec.DoubleValue clayCommanderDelcattySpawnRate;
	public static ForgeConfigSpec.DoubleValue clayEspurrSpawnRate;
	public static ForgeConfigSpec.DoubleValue clayLuxioSpawnRate;
	public static ForgeConfigSpec.DoubleValue clayPurrloinSpawnRate;
	public static ForgeConfigSpec.DoubleValue clayShinxSpawnRate;
	public static ForgeConfigSpec.DoubleValue claySkittySpawnRate;
	public static ForgeConfigSpec.DoubleValue cracklingNincadaSpawnRate;
	//	public static ForgeConfigSpec.DoubleValue demonVulpixSpawnRate;
	//	public static ForgeConfigSpec.DoubleValue forcewindEelektrikSpawnRate;
	public static ForgeConfigSpec.DoubleValue infestedDeerlingSpawnRate;
	public static ForgeConfigSpec.DoubleValue muBunearySpawnRate;
	//	public static ForgeConfigSpec.DoubleValue proxyPylonCarbinkSpawnRate;
	public static ForgeConfigSpec.DoubleValue raptorZerglingNincadaSpawnRate;
	public static ForgeConfigSpec.DoubleValue snowSorceressBraixenSpawnRate;
	//	public static ForgeConfigSpec.DoubleValue swordieMienshaoSpawnRate;
	//	public static ForgeConfigSpec.DoubleValue tiberiumGrowlitheSpawnRate;
	public static ForgeConfigSpec.DoubleValue voorstMightyenaSpawnRate;
	public static ForgeConfigSpec.DoubleValue zerglingNincadaSpawnRate;

	//public static final List<EntityType<?>> ENTITIES = new ArrayList<EntityType<?>>();

//	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, ModInfo.MOD_ID);
//
//	public static final String FIREWORK_MISSILE_NAME = "firework_missile";
//	public static final RegistryObject<EntityType<FireworkMissileEntity>> FIREWORK_MISSILE = ENTITY_TYPES.register("firework_missile",
//			() -> EntityType.Builder.<FireworkMissileEntity>create(FireworkMissileEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(new ResourceLocation(ModInfo.MOD_ID, FIREWORK_MISSILE_NAME).toString()));
//

	public static EntityType<ArmoredLuxrayEntity> ARMORED_LUXRAY = (EntityType<ArmoredLuxrayEntity>) EntityType.Builder.create(ArmoredLuxrayEntity::new, EntityClassification.MONSTER).size(0.8F, 1.2F).immuneToFire().build(ModInfo.MOD_ID + ArmoredLuxrayEntity.getMobName()).setRegistryName(UtilityFunctions.location(ArmoredLuxrayEntity.getMobName()));
	public static EntityType<ArmoredShinxEntity> ARMORED_SHINX = (EntityType<ArmoredShinxEntity>) EntityType.Builder.create(ArmoredShinxEntity::new, EntityClassification.MONSTER).size(0.4F, 0.6F).build(ModInfo.MOD_ID + ArmoredShinxEntity.getMobName()).setRegistryName(UtilityFunctions.location(ArmoredShinxEntity.getMobName()));
	public static EntityType<BandersnatchFennekinEntity> BANDERSNATCH_FENNEKIN = (EntityType<BandersnatchFennekinEntity>) EntityType.Builder.create(BandersnatchFennekinEntity::new, EntityClassification.MONSTER).size(0.3F, 0.5F).immuneToFire().build(ModInfo.MOD_ID + BandersnatchFennekinEntity.getMobName()).setRegistryName(UtilityFunctions.location(BandersnatchFennekinEntity.getMobName()));
	public static EntityType<ClayCommanderDelcattyEntity> CLAY_COMMANDER_DELCATTY = (EntityType<ClayCommanderDelcattyEntity>) EntityType.Builder.create(ClayCommanderDelcattyEntity::new, EntityClassification.MONSTER).size(0.7F, 1.1F).immuneToFire().build(ModInfo.MOD_ID + ClayCommanderDelcattyEntity.getMobName()).setRegistryName(UtilityFunctions.location(ClayCommanderDelcattyEntity.getMobName()));
	public static EntityType<ClayEspurrEntity> CLAY_ESPURR = (EntityType<ClayEspurrEntity>) EntityType.Builder.create(ClayEspurrEntity::new, EntityClassification.MONSTER).size(0.3F, 0.5F).build(ModInfo.MOD_ID + ClayEspurrEntity.getMobName()).setRegistryName(UtilityFunctions.location(ClayEspurrEntity.getMobName()));
	public static EntityType<ClayLuxioEntity> CLAY_LUXIO = (EntityType<ClayLuxioEntity>) EntityType.Builder.create(ClayLuxioEntity::new, EntityClassification.MONSTER).size(0.8F, 0.85F).immuneToFire().build(ModInfo.MOD_ID + ClayLuxioEntity.getMobName()).setRegistryName(UtilityFunctions.location(ClayLuxioEntity.getMobName()));
	public static EntityType<ClayPurrloinEntity> CLAY_PURRLOIN = (EntityType<ClayPurrloinEntity>) EntityType.Builder.create(ClayPurrloinEntity::new, EntityClassification.MONSTER).size(0.3F, 0.5F).build(ModInfo.MOD_ID + ClayPurrloinEntity.getMobName()).setRegistryName(UtilityFunctions.location(ClayPurrloinEntity.getMobName()));
	public static EntityType<ClayShinxEntity> CLAY_SHINX = (EntityType<ClayShinxEntity>) EntityType.Builder.create(ClayShinxEntity::new, EntityClassification.MONSTER).size(0.3F, 0.6F).immuneToFire().build(ModInfo.MOD_ID + ClayShinxEntity.getMobName()).setRegistryName(UtilityFunctions.location(ClayShinxEntity.getMobName()));
	public static EntityType<ClaySkittyEntity> CLAY_SKITTY = (EntityType<ClaySkittyEntity>) EntityType.Builder.create(ClaySkittyEntity::new, EntityClassification.MONSTER).size(0.3F, 0.6F).build(ModInfo.MOD_ID + ClaySkittyEntity.getMobName()).setRegistryName(UtilityFunctions.location(ClaySkittyEntity.getMobName()));
	public static EntityType<CracklingNincadaEntity> CRACKLING_NINCADA = (EntityType<CracklingNincadaEntity>) EntityType.Builder.create(CracklingNincadaEntity::new, EntityClassification.MONSTER).size(0.4F, 0.5F).build(ModInfo.MOD_ID + CracklingNincadaEntity.getMobName()).setRegistryName(UtilityFunctions.location(CracklingNincadaEntity.getMobName()));
	public static EntityType<InfestedDeerlingEntity> INFESTED_DEERLING = (EntityType<InfestedDeerlingEntity>) EntityType.Builder.create(InfestedDeerlingEntity::new, EntityClassification.MONSTER).size(0.3F, 0.6F).build(ModInfo.MOD_ID + InfestedDeerlingEntity.getMobName()).setRegistryName(UtilityFunctions.location(InfestedDeerlingEntity.getMobName()));
	public static EntityType<MuBunearyEntity> MU_BUNEARY = (EntityType<MuBunearyEntity>) EntityType.Builder.create(MuBunearyEntity::new, EntityClassification.MONSTER).size(0.4F, 0.6F).build(ModInfo.MOD_ID + MuBunearyEntity.getMobName()).setRegistryName(UtilityFunctions.location(MuBunearyEntity.getMobName()));
	public static EntityType<RaptorZerglingNincadaEntity> RAPTOR_ZERGLING_NINCADA = (EntityType<RaptorZerglingNincadaEntity>) EntityType.Builder.create(RaptorZerglingNincadaEntity::new, EntityClassification.MONSTER).size(0.4F, 0.5F).build(ModInfo.MOD_ID + RaptorZerglingNincadaEntity.getMobName()).setRegistryName(UtilityFunctions.location(RaptorZerglingNincadaEntity.getMobName()));
	public static EntityType<SnowSorceressBraixenEntity> SNOW_SORCERESS_BRAIXEN = (EntityType<SnowSorceressBraixenEntity>) EntityType.Builder.create(SnowSorceressBraixenEntity::new, EntityClassification.MONSTER).size(0.5F, 0.95F).immuneToFire().build(ModInfo.MOD_ID + SnowSorceressBraixenEntity.getMobName()).setRegistryName(UtilityFunctions.location(SnowSorceressBraixenEntity.getMobName()));
	public static EntityType<VoorstMightyenaEntity> VOORST_MIGHTYENA = (EntityType<VoorstMightyenaEntity>) EntityType.Builder.create(VoorstMightyenaEntity::new, EntityClassification.MONSTER).size(0.8F, 0.9F).immuneToFire().build(ModInfo.MOD_ID + VoorstMightyenaEntity.getMobName()).setRegistryName(UtilityFunctions.location(VoorstMightyenaEntity.getMobName()));
	public static EntityType<ZerglingNincadaEntity> ZERGLING_NINCADA = (EntityType<ZerglingNincadaEntity>) EntityType.Builder.create(ZerglingNincadaEntity::new, EntityClassification.MONSTER).size(0.4F, 0.5F).build(ModInfo.MOD_ID + ZerglingNincadaEntity.getMobName()).setRegistryName(UtilityFunctions.location(ZerglingNincadaEntity.getMobName()));

	public static EntityType<FeywoodAbsolEntity> FEYWOOD_ABSOL = (EntityType<FeywoodAbsolEntity>) EntityType.Builder.create(FeywoodAbsolEntity::new, EntityClassification.CREATURE).size(0.5F, 0.95F).build(ModInfo.MOD_ID + FeywoodAbsolEntity.getMobName()).setRegistryName(UtilityFunctions.location(FeywoodAbsolEntity.getMobName()));
	public static EntityType<FirecrackerLittenEntity> FIRECRACKER_LITTEN = (EntityType<FirecrackerLittenEntity>) EntityType.Builder.create(FirecrackerLittenEntity::new, EntityClassification.CREATURE).size(0.5F, 0.5F).build(ModInfo.MOD_ID + FirecrackerLittenEntity.getMobName()).setRegistryName(UtilityFunctions.location(FirecrackerLittenEntity.getMobName()));
	public static EntityType<OkamiEspeonEntity> OKAMI_ESPEON = (EntityType<OkamiEspeonEntity>) EntityType.Builder.create(OkamiEspeonEntity::new, EntityClassification.CREATURE).size(0.5F, 0.95F).build(ModInfo.MOD_ID + OkamiEspeonEntity.getMobName()).setRegistryName(UtilityFunctions.location(OkamiEspeonEntity.getMobName()));
	public static EntityType<OkamiSylveonEntity> OKAMI_SYLVEON = (EntityType<OkamiSylveonEntity>) EntityType.Builder.create(OkamiSylveonEntity::new, EntityClassification.CREATURE).size(0.5F, 0.95F).build(ModInfo.MOD_ID + OkamiSylveonEntity.getMobName()).setRegistryName(UtilityFunctions.location(OkamiSylveonEntity.getMobName()));
	public static EntityType<OkamiUmbreonEntity> OKAMI_UMBREON = (EntityType<OkamiUmbreonEntity>) EntityType.Builder.create(OkamiUmbreonEntity::new, EntityClassification.CREATURE).size(0.5F, 0.95F).build(ModInfo.MOD_ID + OkamiUmbreonEntity.getMobName()).setRegistryName(UtilityFunctions.location(OkamiUmbreonEntity.getMobName()));

	//public static EntityType<BloodmoonNinetalesEntity> BLOODMOON_NINETALES = (EntityType<BloodmoonNinetalesEntity>) EntityType.Builder.create(BloodmoonNinetalesEntity::new, EntityClassification.MONSTER).size(0.5F, 1.9F).immuneToFire().build(ModInfo.MOD_ID + BloodmoonNinetalesEntity.getMobName()).setRegistryName(UtilityFunctions.location(BloodmoonNinetalesEntity.getMobName()));

	public static EntityType<FireworkMissileEntity> FIREWORK_MISSILE = (EntityType<FireworkMissileEntity>) EntityType.Builder.create(FireworkMissileEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(ModInfo.MOD_ID + "firework_missile").setRegistryName(UtilityFunctions.location("firework_missile"));

	/**
	 * Initializes mod item indices from configuration file
	 */
	public static void initConfig(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) 
	{
		/*================== SPAWNS =====================*/
		server.comment("Mob Spawn Rates");
		server.comment("======= Spawn Settings =======" +
				"\n" +
				"\nAdjust mob spawn rates from a value between 0-10.0 (20.0 for mini-boss mobs)." +
				"\n1.0 keeps the spawn rates at their default rate." +
				"\n");
		armoredShinxSpawnRate = server.comment("Armored Shinx Spawn Rate.")
				.defineInRange("entities.armoredShinxSpawnRate", 1.0, 0, 10);
		bandersnatchFennekinSpawnRate = server.comment("Bandersnatch Fennekin Spawn Rate.")
				.defineInRange("entities.bandersnatchFennekinSpawnRate", 1.0, 0, 10);
		clayEspurrSpawnRate = server.comment("Clay Espurr Spawn Rate.")
				.defineInRange("entities.clayEspurrSpawnRate", 1.0, 0, 10);
		clayPurrloinSpawnRate = server.comment("Clay Purrloin Spawn Rate.")
				.defineInRange("entities.clayPurrloinSpawnRate", 1.0, 0, 10);
		claySkittySpawnRate = server.comment("Clay Skitty Spawn Rate.")
				.defineInRange("entities.claySkittySpawnRate", 1.0, 0, 10);
		//demonVulpixSpawnRate = config.getFloat("demonVulpixSpawnRate", category, 1.0F, 0F, 10.0F, "Demon Vulpix Spawn Rate.");
		//forcewindEelektrikSpawnRate = config.getFloat("forcewindEelektricSpawnRate", category, 1.0F, 0F, 10.0F, "Forcewind Eelektric Spawn Rate.");
		infestedDeerlingSpawnRate = server.comment("Infested Deerling Spawn Rate.")
				.defineInRange("entities.infestedDeerlingSpawnRate", 1.0, 0, 10);
		muBunearySpawnRate = server.comment("Mu Buneary Spawn Rate.")
				.defineInRange("entities.muBunearySpawnRate", 1.0, 0, 10);
		//proxyPylonCarbinkSpawnRate = config.getFloat("proxyPylonCarbinkSpawnRate", category, 1.0F, 0F, 10.0F, "Proxy Pylon Carbink Spawn Rate.");
		raptorZerglingNincadaSpawnRate = server.comment("Raptor Zergling Nincada Spawn Rate.")
				.defineInRange("entities.raptorZerglingNincadaSpawnRate", 1.0, 0, 10);
		snowSorceressBraixenSpawnRate = server.comment("Snow Sorceress Braixen Spawn Rate.")
				.defineInRange("entities.snowSorceressBraixenSpawnRate", 1.0, 0, 10);
		//swordieMienshaoSpawnRate = config.getFloat("swordieMienshaoSpawnRate", category, 1.0F, 0F, 10.0F, "Swordie Mienshao Spawn Rate.");
		//tiberiumGrowlitheSpawnRate = config.getFloat("tiberiumGrowlitheSpawnRate", category, 1.0F, 0F, 10.0F, "Tiberium Growlithe Spawn Rate.");
		zerglingNincadaSpawnRate = server.comment("Zergling Nincada Spawn Rate.")
				.defineInRange("entities.zerglingNincadaSpawnRate", 1.0, 0, 10);

		armoredLuxraySpawnRate = server.comment("Armored Luxray Spawn Rate.")
				.defineInRange("entities.armoredLuxraySpawnRate", 1.0, 0, 20);
		clayCommanderDelcattySpawnRate = server.comment("Clay Commander Delcatty Spawn Rate.")
				.defineInRange("entities.clayCommanderDelcattySpawnRate", 1.0, 0, 20);
		voorstMightyenaSpawnRate = server.comment("Voorst Mightyena Spawn Rate.")
				.defineInRange("entities.voorstMightyenaSpawnRate", 1.0, 0, 20);

		//bloodmoonNinetalesSpawnRate = config.getFloat("bloodmoonNinetalesSpawnRate", category, 1.0F, 0F, 30.0F, "Bloodmoon Ninetales Spawn Rate.");

		clayLuxioSpawnRate = server.comment("Clay Luxio Spawn Rate.")
				.defineInRange("entities.clayLuxioSpawnRate", 1.0, 0, 10);
		clayShinxSpawnRate = server.comment("Clay Shinx Spawn Rate.")
				.defineInRange("entities.clayShinxSpawnRate", 1.0, 0, 10);
		cracklingNincadaSpawnRate = server.comment("Crackling Nincada Spawn Rate.")
				.defineInRange("entities.cracklingNincadaSpawnRate", 1.0, 0, 10);

		/*================== MOB SPAWNS =====================*/
		//category = "Mob Spawns";

		//prop = config.get(category, "Dimensions disabled for most mod mobs", new int[] {-28, -29, -30, -31});
		//prop.setComment("List the dimensions regular mod mobs should not spawn in");
		//prop.setRequiresMcRestart(true);
		//dimensionSpawnsDisablerList = prop.getIntList();
		/*
		prop = config.get(category, "Dimensions disabled for mod's space themed mobs", new int[] {});
		prop.setComment("List the dimensions the mod's space themed mobs should not spawn in");
		prop.setRequiresMcRestart(true);
		dimensionSpawnsDisablerSpaceMobs = prop.getIntList();*/

		//canFullMoonMobsSpawnOnFirstNight = config.getBoolean("canFullMoonMobsSpawnOnFirstNight", category, false, "Can Full Moon mobs spawn on first night?");
		//minDaysToSpawnDarknut = 24000 * MathHelper.clamp_int(config.get("Mob Spawns", "Minimum number of days required to pass before Darknuts may spawn [0-30]", 7).getInt(), 0, 30);

		/*================== MOBS BEHAVIOR =====================*/
		//category = "Mob Behavior";
		//config.addCustomCategoryComment(category,
		//		"\n======= Mob Behavior Settings =======");
		//mobsHostileToVanillaMobs = config.getBoolean("mobsHostileToVanillaMobs", category, true, "Mod mobs with hostility to Vanilla mobs enabled?");
	}

	public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll
		(
				KindredLegacyItems.ARMORED_LUXRAY_EGG = registerEntitySpawnEgg(ARMORED_LUXRAY, 0x062518, 0xd9c225, "armored_luxray_egg"),
				KindredLegacyItems.ARMORED_SHINX_EGG = registerEntitySpawnEgg(ARMORED_SHINX, 0xdac329, 0x062518, "armored_shinx_egg"),
				KindredLegacyItems.BANDERSNATCH_FENNEKIN_EGG = registerEntitySpawnEgg(BANDERSNATCH_FENNEKIN, 0xccd9da, 0x29494e, "bandersnatch_fennekin_egg"),
				KindredLegacyItems.CLAY_COMMANDER_DELCATTY_EGG = registerEntitySpawnEgg(CLAY_COMMANDER_DELCATTY, 0x392f1f, 0x6d6d6d, "clay_commander_delcatty_egg"),
				KindredLegacyItems.CLAY_ESPURR_EGG = registerEntitySpawnEgg(CLAY_ESPURR, 0x322818, 0xb166da, "clay_espurr_egg"),
				KindredLegacyItems.CLAY_LUXIO_EGG = registerEntitySpawnEgg(CLAY_LUXIO, 0x0eb8ff, 0xf6ff00, "clay_luxio_egg"),
				KindredLegacyItems.CLAY_PURRLOIN_EGG = registerEntitySpawnEgg(CLAY_PURRLOIN, 0x3d2723, 0xd4a0c8, "clay_purrloin_egg"),
				KindredLegacyItems.CLAY_SHINX_EGG = registerEntitySpawnEgg(CLAY_SHINX, 0x262640, 0x00b4ff, "clay_shinx_egg"),
				KindredLegacyItems.CLAY_SKITTY_EGG = registerEntitySpawnEgg(CLAY_SKITTY, 0x2e2516, 0xe6bbbb, "clay_skitty_egg"),
				KindredLegacyItems.CRACKLING_NINCADA_EGG = registerEntitySpawnEgg(CRACKLING_NINCADA, 0x4b2230, 0xddff00, "crackling_nincada_egg"),
				KindredLegacyItems.FEYWOOD_ABSOL_EGG = registerEntitySpawnEgg(FEYWOOD_ABSOL, 0x191214, 0xdec1cc, "feywood_absol_egg"),
				KindredLegacyItems.FIRECRACKER_LITTEN_EGG = registerEntitySpawnEgg(FIRECRACKER_LITTEN, 0x000000, 0xe93030, "firecracker_litten_egg"),
				KindredLegacyItems.INFESTED_DEERLING_EGG = registerEntitySpawnEgg(INFESTED_DEERLING, 0x857450, 0x8801aa, "infested_deerling_egg"),
				KindredLegacyItems.MU_BUNEARY_EGG = registerEntitySpawnEgg(MU_BUNEARY, 0x3e3833, 0xaaa39c, "mu_buneary_egg"),
				KindredLegacyItems.OKAMI_ESPEON_EGG = registerEntitySpawnEgg(OKAMI_ESPEON, 0xcab3d2, 0xff0000, "okami_espeon_egg"),	
				KindredLegacyItems.OKAMI_SYLVEON_EGG = registerEntitySpawnEgg(OKAMI_SYLVEON, 0xfffff5, 0xfba4a4, "okami_sylveon_egg"),
				KindredLegacyItems.OKAMI_UMBREON_EGG = registerEntitySpawnEgg(OKAMI_UMBREON, 0x000000, 0xffe776, "okami_umbreon_egg"),
				KindredLegacyItems.RAPTOR_ZERGLING_NINCADA_EGG = registerEntitySpawnEgg(RAPTOR_ZERGLING_NINCADA, 0x7f722a, 0x59b18e, "raptor_zergling_nincada_egg"),
				KindredLegacyItems.SNOW_SORCERESS_BRAIXEN_EGG = registerEntitySpawnEgg(SNOW_SORCERESS_BRAIXEN, 0xbde9f5, 0xfff44b, "snow_sorceress_braixen_egg"),
				KindredLegacyItems.VOORST_MIGHTYENA_EGG = registerEntitySpawnEgg(VOORST_MIGHTYENA, 0xbba559, 0x46390e, "voorst_mightyena_egg"),
				KindredLegacyItems.ZERGLING_NINCADA_EGG = registerEntitySpawnEgg(ZERGLING_NINCADA, 0x7f722a, 0xe0d9a8, "zergling_nincada_egg")
				);
	}

	public static void registerEntityWorldSpawns()
	{
		//List<Biome> biomesToSpawnIn;

		//biomesToSpawnIn = Lists.newArrayList();

		for (Biome biome : Biome.BIOMES)
		{
			Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);

			/***************************
			 * Armored Shinx Line
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER)
					&& !types.contains(BiomeDictionary.Type.SANDY)
					&& !types.contains(BiomeDictionary.Type.BEACH)
					&& !types.contains(BiomeDictionary.Type.RIVER)
					&& !types.contains(BiomeDictionary.Type.OCEAN))
			{
				if (types.contains(BiomeDictionary.Type.SAVANNA) || types.contains(BiomeDictionary.Type.PLAINS))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY, EntityClassification.MONSTER, biome, armoredLuxraySpawnRate.get(), 20, 1, 1);
					registerEntityWorldSpawn(ARMORED_SHINX, EntityClassification.MONSTER, biome, armoredShinxSpawnRate.get(), 80, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.HOT)
						&& (types.contains(BiomeDictionary.Type.DRY) || types.contains(BiomeDictionary.Type.WASTELAND)))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY, EntityClassification.MONSTER, biome, armoredLuxraySpawnRate.get(), 14, 1, 1);
					registerEntityWorldSpawn(ARMORED_SHINX, EntityClassification.MONSTER, biome, armoredShinxSpawnRate.get(), 650, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.SPARSE)
						&& types.contains(BiomeDictionary.Type.SNOWY))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY, EntityClassification.MONSTER, biome, armoredLuxraySpawnRate.get(), 20, 1, 1);
					registerEntityWorldSpawn(ARMORED_SHINX, EntityClassification.MONSTER, biome, armoredShinxSpawnRate.get(), 110, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.HILLS) && !types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY, EntityClassification.MONSTER, biome, armoredLuxraySpawnRate.get(), 14, 1, 1);
					registerEntityWorldSpawn(ARMORED_SHINX, EntityClassification.MONSTER, biome, armoredShinxSpawnRate.get(), 45, 1, 1);
				}
			}

			/***************************
			 * Bandersnatch Fennekin
			 ***************************/
			registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN, EntityClassification.MONSTER, Biomes.MOUNTAINS, bandersnatchFennekinSpawnRate.get(), 80, 1, 1);
			registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN, EntityClassification.MONSTER, Biomes.MOUNTAIN_EDGE, bandersnatchFennekinSpawnRate.get(), 80, 1, 1);

			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.SNOWY))
				{
					registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN, EntityClassification.MONSTER, biome, bandersnatchFennekinSpawnRate.get(), 100, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.COLD) && types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN, EntityClassification.MONSTER, biome, bandersnatchFennekinSpawnRate.get(), 80, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.COLD))
				{
					registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN, EntityClassification.MONSTER, biome, bandersnatchFennekinSpawnRate.get(), 50, 1, 1);
				}
			}

			/***************************
			 * Clay Army Spawns
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.JUNGLE))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY, EntityClassification.MONSTER, biome, clayCommanderDelcattySpawnRate.get(), 20, 1, 1);
					registerEntityWorldSpawn(CLAY_ESPURR, EntityClassification.MONSTER, biome, clayEspurrSpawnRate.get(), 60, 1, 1);
					registerEntityWorldSpawn(CLAY_LUXIO, EntityClassification.MONSTER, biome, clayLuxioSpawnRate.get(), 100, 1, 1);
					registerEntityWorldSpawn(CLAY_PURRLOIN, EntityClassification.MONSTER, biome, clayPurrloinSpawnRate.get(), 110, 1, 3);
					registerEntityWorldSpawn(CLAY_SHINX, EntityClassification.MONSTER, biome, clayShinxSpawnRate.get(), 90, 1, 2);
					registerEntityWorldSpawn(CLAY_SKITTY, EntityClassification.MONSTER, biome, claySkittySpawnRate.get(), 130, 1, 3);
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY, EntityClassification.MONSTER, biome, clayCommanderDelcattySpawnRate.get(), 18, 1, 1);
					registerEntityWorldSpawn(CLAY_ESPURR, EntityClassification.MONSTER, biome, clayEspurrSpawnRate.get(), 60, 1, 1);
					registerEntityWorldSpawn(CLAY_LUXIO, EntityClassification.MONSTER, biome, clayLuxioSpawnRate.get(), 60, 1, 1);
					registerEntityWorldSpawn(CLAY_PURRLOIN, EntityClassification.MONSTER, biome, clayPurrloinSpawnRate.get(), 100, 1, 3);
					registerEntityWorldSpawn(CLAY_SHINX, EntityClassification.MONSTER, biome, clayShinxSpawnRate.get(), 90, 1, 2);
					registerEntityWorldSpawn(CLAY_SKITTY, EntityClassification.MONSTER, biome, claySkittySpawnRate.get(), 120, 1, 3);
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY, EntityClassification.MONSTER, biome, clayCommanderDelcattySpawnRate.get(), 15, 1, 1);
					registerEntityWorldSpawn(CLAY_ESPURR, EntityClassification.MONSTER, biome, clayEspurrSpawnRate.get(), 60, 1, 1);
					registerEntityWorldSpawn(CLAY_LUXIO, EntityClassification.MONSTER, biome, clayLuxioSpawnRate.get(), 70, 1, 1);
					registerEntityWorldSpawn(CLAY_PURRLOIN, EntityClassification.MONSTER, biome, clayPurrloinSpawnRate.get(), 100, 1, 3);
					registerEntityWorldSpawn(CLAY_SHINX, EntityClassification.MONSTER, biome, clayShinxSpawnRate.get(), 130, 1, 2);
					registerEntityWorldSpawn(CLAY_SKITTY, EntityClassification.MONSTER, biome, claySkittySpawnRate.get(), 120, 1, 3);
				}
				else if (types.contains(BiomeDictionary.Type.SANDY))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY, EntityClassification.MONSTER, biome, clayCommanderDelcattySpawnRate.get(), 16, 1, 1);
					registerEntityWorldSpawn(CLAY_ESPURR, EntityClassification.MONSTER, biome, clayEspurrSpawnRate.get(), 60, 1, 1);
					registerEntityWorldSpawn(CLAY_LUXIO, EntityClassification.MONSTER, biome, clayLuxioSpawnRate.get(), 70, 1, 1);
					registerEntityWorldSpawn(CLAY_PURRLOIN, EntityClassification.MONSTER, biome, clayPurrloinSpawnRate.get(), 100, 1, 3);
					registerEntityWorldSpawn(CLAY_SHINX, EntityClassification.MONSTER, biome, clayShinxSpawnRate.get(), 130, 1, 2);
					registerEntityWorldSpawn(CLAY_SKITTY, EntityClassification.MONSTER, biome, claySkittySpawnRate.get(), 110, 1, 3);
				}
			}

			/***************************
			 * Infested Deerling
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.SAVANNA))
				{
					registerEntityWorldSpawn(INFESTED_DEERLING, EntityClassification.MONSTER, biome, infestedDeerlingSpawnRate.get(), 90, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.JUNGLE))
				{
					registerEntityWorldSpawn(INFESTED_DEERLING, EntityClassification.MONSTER, biome, infestedDeerlingSpawnRate.get(), 100, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.FOREST) 
						|| types.contains(BiomeDictionary.Type.SWAMP) 
						|| types.contains(BiomeDictionary.Type.CONIFEROUS))
				{
					registerEntityWorldSpawn(INFESTED_DEERLING, EntityClassification.MONSTER, biome, infestedDeerlingSpawnRate.get(), 110, 1, 1);
				}
			}	

			/***************************
			 * Mu Buneary
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(MU_BUNEARY, EntityClassification.MONSTER, biome, muBunearySpawnRate.get(), 90, 1, 2);
				}
			}

			/***************************
			 * Pokezerg Swarm Spawns
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END))
			{
				if (types.contains(BiomeDictionary.Type.NETHER))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA, EntityClassification.MONSTER, biome, zerglingNincadaSpawnRate.get(), 170, 1, 3);
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA, EntityClassification.MONSTER, biome, raptorZerglingNincadaSpawnRate.get(), 100, 1, 2);
					registerEntityWorldSpawn(CRACKLING_NINCADA, EntityClassification.MONSTER, biome, cracklingNincadaSpawnRate.get(), 75, 1, 2);
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA, EntityClassification.MONSTER, biome, zerglingNincadaSpawnRate.get(), 90, 1, 2);
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA, EntityClassification.MONSTER, biome, raptorZerglingNincadaSpawnRate.get(), 60, 1, 2);
					registerEntityWorldSpawn(CRACKLING_NINCADA, EntityClassification.MONSTER, biome, cracklingNincadaSpawnRate.get(), 50, 1, 2);
				}
				else if (types.contains(BiomeDictionary.Type.SANDY))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA, EntityClassification.MONSTER, biome, zerglingNincadaSpawnRate.get(), 110, 1, 2);
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA, EntityClassification.MONSTER, biome, raptorZerglingNincadaSpawnRate.get(), 75, 1, 2);
					registerEntityWorldSpawn(CRACKLING_NINCADA, EntityClassification.MONSTER, biome, cracklingNincadaSpawnRate.get(), 50, 1, 2);
				}
				else if (types.contains(BiomeDictionary.Type.DEAD) 
						|| types.contains(BiomeDictionary.Type.WASTELAND))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA, EntityClassification.MONSTER, biome, zerglingNincadaSpawnRate.get(), 170, 1, 3);
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA, EntityClassification.MONSTER, biome, raptorZerglingNincadaSpawnRate.get(), 120, 1, 2);
					registerEntityWorldSpawn(CRACKLING_NINCADA, EntityClassification.MONSTER, biome, cracklingNincadaSpawnRate.get(), 95, 1, 2);
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA, EntityClassification.MONSTER, biome, zerglingNincadaSpawnRate.get(), 90, 1, 2);
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA, EntityClassification.MONSTER, biome, raptorZerglingNincadaSpawnRate.get(), 60, 1, 2);
					registerEntityWorldSpawn(CRACKLING_NINCADA, EntityClassification.MONSTER, biome, cracklingNincadaSpawnRate.get(), 55, 1, 2);
				}
			}

			/***************************
			 * Snow Sorceress Braixen
			 ***************************/
//			registerEntityWorldSpawn(SNOW_SORCERESS_BRAIXEN, EntityClassification.MONSTER, Biomes.MOUNTAINS, snowSorceressBraixenSpawnRate.get(), 80, 1, 1);
//			registerEntityWorldSpawn(SNOW_SORCERESS_BRAIXEN, EntityClassification.MONSTER, Biomes.MOUNTAIN_EDGE, snowSorceressBraixenSpawnRate.get(), 80, 1, 1);
//
//			if(!types.contains(BiomeDictionary.Type.END))
//			{
//				if (types.contains(BiomeDictionary.Type.SNOWY))
//				{
//					registerEntityWorldSpawn(SNOW_SORCERESS_BRAIXEN, EntityClassification.MONSTER, biome, snowSorceressBraixenSpawnRate.get(), 80, 1, 1);
//				}
//			}

			/***************************
			 * Voorst Mightyena
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if(types.contains(BiomeDictionary.Type.SAVANNA))
				{
					registerEntityWorldSpawn(VOORST_MIGHTYENA, EntityClassification.MONSTER, biome, voorstMightyenaSpawnRate.get(), 25, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.SWAMP))
				{
					registerEntityWorldSpawn(VOORST_MIGHTYENA, EntityClassification.MONSTER, biome, voorstMightyenaSpawnRate.get(), 14, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(VOORST_MIGHTYENA, EntityClassification.MONSTER, biome, voorstMightyenaSpawnRate.get(), 18, 1, 1);
				}
			}

		}



		//registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA, EntityClassification.MONSTER, Biomes.PLAINS);
		//registerEntityWorldSpawn(ZERGLING_NINCADA, EntityClassification.MONSTER, Biomes.PLAINS);

		//BiomeDictionary.Type.PLAINS;
	}
	/*
	public static void addSpawn(Biome biome, Class <? extends EntityLiving > entityclass, float spawnRate, int spawnWeight, int groupCountMin, int groupCountMax)
	{
		if(spawnRate > 0)
		{
			biome.getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(entityclass, (int)(spawnRate * spawnWeight), groupCountMin, groupCountMax));
		}
	}*/
	/*
	public static void addSpecificBiomeSpawn(int biomeID, Class <? extends EntityLiving > entityclass, float spawnRate, int spawnWeight, int groupCountMin, int groupCountMax)
	{
		if(spawnRate > 0)
		{
			Biome.getBiome(biomeID).getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(entityclass, (int)(spawnRate * spawnWeight), groupCountMin, groupCountMax));
		}
	}*/

	public static void registerEntityWorldSpawn(EntityType<?> entity, EntityClassification classification, Biome biome, double spawnRate, int spawnWeight, int groupCountMin, int groupCountMax)
	{
		if(spawnRate > 0)
		{
			if(biome != null)
			{
				biome.getSpawns(classification).add(new SpawnListEntry(entity, (int)(spawnRate * spawnWeight), groupCountMin, groupCountMax));
			}
		}
	}

	/*
	public static void addSpawnLocations()
	{
		List<Biome> biomesToSpawnIn;

		biomesToSpawnIn = Lists.newArrayList();

		for (Biome biome : Biome.REGISTRY)
		{
			Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);
		}
	}*/


	public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
	{
		SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ItemGroup.MISC));
		item.setRegistryName(UtilityFunctions.location(name));
		return item;
	}


}