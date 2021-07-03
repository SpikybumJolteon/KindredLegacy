package fuzzyacornindustries.kindredlegacy.common.core;

import java.util.Set;
import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.VoorstMightyenaEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.SpawnInfo;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ArmoredLuxrayEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ArmoredShinxEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.BandersnatchFennekinEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayCommanderDelcattyEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayEspurrEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayLuxioEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayPurrloinEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClayShinxEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ClaySkittyEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.CracklingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.InfestedDeerlingEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.MuBunearyEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.RaptorZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ZerglingNincadaEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiEspeonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiSylveonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiUmbreonEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.VastayaNinetalesEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.projectile.FireworkMissileEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.projectile.VastayaFireballEntity;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import fuzzyacornindustries.kindredlegacy.lib.SpawnRate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities 
{
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Names.MOD_ID);

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
	public static final String FIREWORK_MISSILE_NAME = "firework_missile";
	public static final RegistryObject<EntityType<FireworkMissileEntity>> FIREWORK_MISSILE = ENTITIES.register("firework_missile",
			() -> EntityType.Builder.<FireworkMissileEntity>create(FireworkMissileEntity::new, EntityClassification.MISC).size(0.5F, 0.5F).build(new ResourceLocation(Names.MOD_ID, FIREWORK_MISSILE_NAME).toString()));

	public static final String VASTAYA_FIREBALL_NAME = "vastaya_fireball";
	public static final RegistryObject<EntityType<VastayaFireballEntity>> VASTAYA_FIREBALL = ENTITIES.register("vastaya_fireball",
			() -> EntityType.Builder.<VastayaFireballEntity>create(VastayaFireballEntity::new, EntityClassification.MISC).size(1.0F, 1.0F).build(new ResourceLocation(Names.MOD_ID, VASTAYA_FIREBALL_NAME).toString()));

	public static final RegistryObject<EntityType<ArmoredLuxrayEntity>> ARMORED_LUXRAY = register(ArmoredLuxrayEntity.getMobName(), ModEntities::armoredLuxary);
	public static final RegistryObject<EntityType<ArmoredShinxEntity>> ARMORED_SHINX = register(ArmoredShinxEntity.getMobName(), ModEntities::armoredShinx);
	public static final RegistryObject<EntityType<BandersnatchFennekinEntity>> BANDERSNATCH_FENNEKIN = register(BandersnatchFennekinEntity.getMobName(), ModEntities::bandersnatchFennekin);
	public static final RegistryObject<EntityType<ClayCommanderDelcattyEntity>> CLAY_COMMANDER_DELCATTY = register(ClayCommanderDelcattyEntity.getMobName(), ModEntities::clayCommanderDelcatty);
	public static final RegistryObject<EntityType<ClayEspurrEntity>> CLAY_ESPURR = register(ClayEspurrEntity.getMobName(), ModEntities::clayEspurr);
	public static final RegistryObject<EntityType<ClayLuxioEntity>> CLAY_LUXIO = register(ClayLuxioEntity.getMobName(), ModEntities::clayLuxio);
	public static final RegistryObject<EntityType<ClayPurrloinEntity>> CLAY_PURRLOIN = register(ClayPurrloinEntity.getMobName(), ModEntities::clayPurrloin);
	public static final RegistryObject<EntityType<ClayShinxEntity>> CLAY_SHINX = register(ClayShinxEntity.getMobName(), ModEntities::clayShinx);
	public static final RegistryObject<EntityType<ClaySkittyEntity>> CLAY_SKITTY = register(ClaySkittyEntity.getMobName(), ModEntities::claySkitty);
	public static final RegistryObject<EntityType<CracklingNincadaEntity>> CRACKLING_NINCADA = register(CracklingNincadaEntity.getMobName(), ModEntities::cracklingNincada);
	public static final RegistryObject<EntityType<InfestedDeerlingEntity>> INFESTED_DEERLING = register(InfestedDeerlingEntity.getMobName(), ModEntities::infestedDeerling);
	public static final RegistryObject<EntityType<MuBunearyEntity>> MU_BUNEARY = register(MuBunearyEntity.getMobName(), ModEntities::muBuneary);
	public static final RegistryObject<EntityType<RaptorZerglingNincadaEntity>> RAPTOR_ZERGLING_NINCADA = register(RaptorZerglingNincadaEntity.getMobName(), ModEntities::raptorZerglingNincada);
	//	public static final RegistryObject<EntityType<SnowSorceressBraixenEntity>> SNOW_SORCERESS_BRAIXEN = register(SnowSorceressBraixenEntity.getMobName(), ModEntities::snowSorceressBraixen);
	public static final RegistryObject<EntityType<VoorstMightyenaEntity>> VOORST_MIGHTYENA = register(VoorstMightyenaEntity.getMobName(), ModEntities::voorstMightyena);
	public static final RegistryObject<EntityType<ZerglingNincadaEntity>> ZERGLING_NINCADA = register(ZerglingNincadaEntity.getMobName(), ModEntities::zerglingNincada);

	public static final RegistryObject<EntityType<FeywoodAbsolEntity>> FEYWOOD_ABSOL = register(FeywoodAbsolEntity.getMobName(), ModEntities::feywoodAbsol);
	public static final RegistryObject<EntityType<FirecrackerLittenEntity>> FIRECRACKER_LITTEN = register(FirecrackerLittenEntity.getMobName(), ModEntities::firecrackerLitten);
	public static final RegistryObject<EntityType<OkamiEspeonEntity>> OKAMI_ESPEON = register(OkamiEspeonEntity.getMobName(), ModEntities::okamiEspeon);
	public static final RegistryObject<EntityType<OkamiSylveonEntity>> OKAMI_SYLVEON = register(OkamiSylveonEntity.getMobName(), ModEntities::okamiSylveon);
	public static final RegistryObject<EntityType<OkamiUmbreonEntity>> OKAMI_UMBREON = register(OkamiUmbreonEntity.getMobName(), ModEntities::okamiUmbreon);
	public static final RegistryObject<EntityType<VastayaNinetalesEntity>> VASTAYA_NINETALES = register(VastayaNinetalesEntity.getMobName(), ModEntities::vastayaNinetales);

	private static <E extends Entity> RegistryObject<EntityType<E>> register(final String name, final Supplier<EntityType.Builder<E>> sup) 
	{
		return ENTITIES.register(name, () -> sup.get().build(name));
	}

	/* ***********************
	 *  FACTORY BUILDERS HOSTILES
	 ************************/
	private static EntityType.Builder<ArmoredLuxrayEntity> armoredLuxary() 
	{
		return EntityType.Builder.<ArmoredLuxrayEntity>create(ArmoredLuxrayEntity::new, EntityClassification.MONSTER)
				.size(0.8F, 1.2F)
				.immuneToFire();
	}

	private static EntityType.Builder<ArmoredShinxEntity> armoredShinx() 
	{
		return EntityType.Builder.<ArmoredShinxEntity>create(ArmoredShinxEntity::new, EntityClassification.MONSTER)
				.size(0.4F, 0.6F);
	}

	private static EntityType.Builder<BandersnatchFennekinEntity> bandersnatchFennekin() 
	{
		return EntityType.Builder.<BandersnatchFennekinEntity>create(BandersnatchFennekinEntity::new, EntityClassification.MONSTER)
				.size(0.3F, 0.5F)
				.immuneToFire();
	}

	private static EntityType.Builder<ClayCommanderDelcattyEntity> clayCommanderDelcatty() 
	{
		return EntityType.Builder.<ClayCommanderDelcattyEntity>create(ClayCommanderDelcattyEntity::new, EntityClassification.MONSTER)
				.size(0.7F, 1.1F)
				.immuneToFire();
	}

	private static EntityType.Builder<ClayEspurrEntity> clayEspurr() 
	{
		return EntityType.Builder.<ClayEspurrEntity>create(ClayEspurrEntity::new, EntityClassification.MONSTER)
				.size(0.3F, 0.5F);
	}

	private static EntityType.Builder<ClayLuxioEntity> clayLuxio() 
	{
		return EntityType.Builder.<ClayLuxioEntity>create(ClayLuxioEntity::new, EntityClassification.MONSTER)
				.size(0.8F, 0.85F)
				.immuneToFire();
	}

	private static EntityType.Builder<ClayPurrloinEntity> clayPurrloin() 
	{
		return EntityType.Builder.<ClayPurrloinEntity>create(ClayPurrloinEntity::new, EntityClassification.MONSTER)
				.size(0.3F, 0.5F);
	}

	private static EntityType.Builder<ClayShinxEntity> clayShinx() 
	{
		return EntityType.Builder.<ClayShinxEntity>create(ClayShinxEntity::new, EntityClassification.MONSTER)
				.size(0.3F, 0.6F)
				.immuneToFire();
	}

	private static EntityType.Builder<ClaySkittyEntity> claySkitty() 
	{
		return EntityType.Builder.<ClaySkittyEntity>create(ClaySkittyEntity::new, EntityClassification.MONSTER)
				.size(0.3F, 0.6F);
	}

	private static EntityType.Builder<CracklingNincadaEntity> cracklingNincada() 
	{
		return EntityType.Builder.<CracklingNincadaEntity>create(CracklingNincadaEntity::new, EntityClassification.MONSTER)
				.size(0.4F, 0.5F);
	}

	private static EntityType.Builder<InfestedDeerlingEntity> infestedDeerling() 
	{
		return EntityType.Builder.<InfestedDeerlingEntity>create(InfestedDeerlingEntity::new, EntityClassification.MONSTER)
				.size(0.3F, 0.6F);
	}

	private static EntityType.Builder<MuBunearyEntity> muBuneary() 
	{
		return EntityType.Builder.<MuBunearyEntity>create(MuBunearyEntity::new, EntityClassification.MONSTER)
				.size(0.4F, 0.6F);
	}

	private static EntityType.Builder<RaptorZerglingNincadaEntity> raptorZerglingNincada() 
	{
		return EntityType.Builder.<RaptorZerglingNincadaEntity>create(RaptorZerglingNincadaEntity::new, EntityClassification.MONSTER)
				.size(0.4F, 0.5F);
	}

	//	private static EntityType.Builder<SnowSorceressBraixenEntity> snowSorceressBraixen() 
	//	{
	//        return EntityType.Builder.<SnowSorceressBraixenEntity>create(SnowSorceressBraixenEntity::new, EntityClassification.MONSTER)
	//        		.size(0.5F, 0.95F)
	//        		.immuneToFire();
	//    }

	private static EntityType.Builder<VoorstMightyenaEntity> voorstMightyena() 
	{
		return EntityType.Builder.<VoorstMightyenaEntity>create(VoorstMightyenaEntity::new, EntityClassification.MONSTER)
				.size(0.8F, 0.9F)
				.immuneToFire();
	}

	private static EntityType.Builder<ZerglingNincadaEntity> zerglingNincada() 
	{
		return EntityType.Builder.<ZerglingNincadaEntity>create(ZerglingNincadaEntity::new, EntityClassification.MONSTER)
				.size(0.4F, 0.5F);
	}

	/* ***********************
	 *  FACTORY BUILDERS - TAMABLES
	 ************************/
	private static EntityType.Builder<FeywoodAbsolEntity> feywoodAbsol() 
	{
		return EntityType.Builder.<FeywoodAbsolEntity>create(FeywoodAbsolEntity::new, EntityClassification.CREATURE)
				.size(0.5F, 0.95F);
	}

	private static EntityType.Builder<FirecrackerLittenEntity> firecrackerLitten() 
	{
		return EntityType.Builder.<FirecrackerLittenEntity>create(FirecrackerLittenEntity::new, EntityClassification.CREATURE)
				.size(0.5F, 0.5F);
	}

	private static EntityType.Builder<OkamiEspeonEntity> okamiEspeon() 
	{
		return EntityType.Builder.<OkamiEspeonEntity>create(OkamiEspeonEntity::new, EntityClassification.CREATURE)
				.size(0.5F, 0.95F);
	}

	private static EntityType.Builder<OkamiSylveonEntity> okamiSylveon() 
	{
		return EntityType.Builder.<OkamiSylveonEntity>create(OkamiSylveonEntity::new, EntityClassification.CREATURE)
				.size(0.5F, 0.95F);
	}

	private static EntityType.Builder<OkamiUmbreonEntity> okamiUmbreon() 
	{
		return EntityType.Builder.<OkamiUmbreonEntity>create(OkamiUmbreonEntity::new, EntityClassification.CREATURE)
				.size(0.5F, 0.95F);
	}

	private static EntityType.Builder<VastayaNinetalesEntity> vastayaNinetales() 
	{
		return EntityType.Builder.<VastayaNinetalesEntity>create(VastayaNinetalesEntity::new, EntityClassification.CREATURE)
				.size(0.5F, 1.9F);
	}

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

	public static void registerEntityWorldSpawns()
	{
		/***************************
		 *      Spawn Rates        *
		 ***************************/
		SpawnRate spawnRates = new SpawnRate();

		spawnRates.setInfo(BANDERSNATCH_FENNEKIN.get(), 300, 1, 3);
		spawnRates.setInfo(INFESTED_DEERLING.get(), 300, 1, 3);
		spawnRates.setInfo(MU_BUNEARY.get(), 260, 1, 2);
		spawnRates.setInfo(VOORST_MIGHTYENA.get(), 70, 1, 1);

		/* Armored Shinx Line */
		spawnRates.setInfo(ARMORED_LUXRAY.get(), 70, 1, 1);
		spawnRates.setInfo(ARMORED_SHINX.get(), 300, 1, 3);

		/* Clay Army */
		spawnRates.setInfo(CLAY_COMMANDER_DELCATTY.get(), 70, 1, 1);
		spawnRates.setInfo(CLAY_ESPURR.get(), 150, 1, 1);
		spawnRates.setInfo(CLAY_LUXIO.get(), 150, 1, 1);
		spawnRates.setInfo(CLAY_PURRLOIN.get(), 325, 1, 3);
		spawnRates.setInfo(CLAY_SHINX.get(), 250, 1, 3);
		spawnRates.setInfo(CLAY_SKITTY.get(), 350, 1, 3);

		/* Pokezerg Swarm */
		spawnRates.setInfo(ZERGLING_NINCADA.get(), 260, 1, 3);
		spawnRates.setInfo(RAPTOR_ZERGLING_NINCADA.get(), 180, 1, 3);
		spawnRates.setInfo(CRACKLING_NINCADA.get(), 150, 1, 2);

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
					registerEntityWorldSpawn(ARMORED_LUXRAY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_LUXRAY.get()), 1.0D, armoredLuxraySpawnRate.get());
					registerEntityWorldSpawn(ARMORED_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_SHINX.get()), 1.0D, armoredShinxSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.HOT)
						&& (types.contains(BiomeDictionary.Type.DRY) || types.contains(BiomeDictionary.Type.WASTELAND)))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_LUXRAY.get()), 0.75D, armoredLuxraySpawnRate.get());
					registerEntityWorldSpawn(ARMORED_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_SHINX.get()), 0.75D, armoredShinxSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.SPARSE)
						&& types.contains(BiomeDictionary.Type.SNOWY))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_LUXRAY.get()), 1.25D, armoredLuxraySpawnRate.get());
					registerEntityWorldSpawn(ARMORED_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_SHINX.get()), 1.25D, armoredShinxSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.HILLS) && !types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(ARMORED_LUXRAY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_LUXRAY.get()), 0.75D, armoredLuxraySpawnRate.get());
					registerEntityWorldSpawn(ARMORED_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ARMORED_SHINX.get()), 0.75D, armoredShinxSpawnRate.get());
				}
			}

			/***************************
			 * Bandersnatch Fennekin
			 ***************************/
			registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN.get(), EntityClassification.MONSTER, Biomes.MOUNTAINS, 
					spawnRates.getInfo(BANDERSNATCH_FENNEKIN.get()), 1.0D, bandersnatchFennekinSpawnRate.get());
			registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN.get(), EntityClassification.MONSTER, Biomes.MOUNTAIN_EDGE, 
					spawnRates.getInfo(BANDERSNATCH_FENNEKIN.get()), 1.0D, bandersnatchFennekinSpawnRate.get());

			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.SNOWY))
				{
					registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(BANDERSNATCH_FENNEKIN.get()), 1.25D, bandersnatchFennekinSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.COLD) && types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(BANDERSNATCH_FENNEKIN.get()), 1.0D, bandersnatchFennekinSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.COLD))
				{
					registerEntityWorldSpawn(BANDERSNATCH_FENNEKIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(BANDERSNATCH_FENNEKIN.get()), 0.5D, bandersnatchFennekinSpawnRate.get());
				}
			}

			/***************************
			 * Clay Army Spawns
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.JUNGLE))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_COMMANDER_DELCATTY.get()), 1.25D, clayCommanderDelcattySpawnRate.get());
					registerEntityWorldSpawn(CLAY_ESPURR.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_ESPURR.get()), 1.0D, clayEspurrSpawnRate.get());
					registerEntityWorldSpawn(CLAY_LUXIO.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_LUXIO.get()), 1.5D, clayLuxioSpawnRate.get());
					registerEntityWorldSpawn(CLAY_PURRLOIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_PURRLOIN.get()), 1.25D, clayPurrloinSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SHINX.get()), 1.25D, clayShinxSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SKITTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SKITTY.get()), 1.0D, claySkittySpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_COMMANDER_DELCATTY.get()), 1.0D, clayCommanderDelcattySpawnRate.get());
					registerEntityWorldSpawn(CLAY_ESPURR.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_ESPURR.get()), 1.0D, clayEspurrSpawnRate.get());
					registerEntityWorldSpawn(CLAY_LUXIO.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_LUXIO.get()), 1.0D, clayLuxioSpawnRate.get());
					registerEntityWorldSpawn(CLAY_PURRLOIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_PURRLOIN.get()), 1.0D, clayPurrloinSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SHINX.get()), 1.0D, clayShinxSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SKITTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SKITTY.get()), 1.0D, claySkittySpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_COMMANDER_DELCATTY.get()), 0.75D, clayCommanderDelcattySpawnRate.get());
					registerEntityWorldSpawn(CLAY_ESPURR.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_ESPURR.get()), 1.0D, clayEspurrSpawnRate.get());
					registerEntityWorldSpawn(CLAY_LUXIO.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_LUXIO.get()), 1.25D, clayLuxioSpawnRate.get());
					registerEntityWorldSpawn(CLAY_PURRLOIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_PURRLOIN.get()), 1.0D, clayPurrloinSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SHINX.get()), 1.5D, clayShinxSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SKITTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SKITTY.get()), 1.25D, claySkittySpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.SANDY))
				{
					registerEntityWorldSpawn(CLAY_COMMANDER_DELCATTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_COMMANDER_DELCATTY.get()), 0.75D, clayCommanderDelcattySpawnRate.get());
					registerEntityWorldSpawn(CLAY_ESPURR.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_ESPURR.get()), 1.0D, clayEspurrSpawnRate.get());
					registerEntityWorldSpawn(CLAY_LUXIO.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_LUXIO.get()), 1.25D, clayLuxioSpawnRate.get());
					registerEntityWorldSpawn(CLAY_PURRLOIN.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_PURRLOIN.get()), 1.0D, clayPurrloinSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SHINX.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SHINX.get()), 1.5D, clayShinxSpawnRate.get());
					registerEntityWorldSpawn(CLAY_SKITTY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CLAY_SKITTY.get()), 1.0D, claySkittySpawnRate.get());
				}
			}

			/***************************
			 * Infested Deerling
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.SAVANNA))
				{
					registerEntityWorldSpawn(INFESTED_DEERLING.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(INFESTED_DEERLING.get()), 0.75D, infestedDeerlingSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.JUNGLE))
				{
					registerEntityWorldSpawn(INFESTED_DEERLING.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(INFESTED_DEERLING.get()), 1.0D, infestedDeerlingSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.FOREST) 
						|| types.contains(BiomeDictionary.Type.SWAMP) 
						|| types.contains(BiomeDictionary.Type.CONIFEROUS))
				{
					registerEntityWorldSpawn(INFESTED_DEERLING.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(INFESTED_DEERLING.get()), 1.25D, infestedDeerlingSpawnRate.get());
				}
			}	

			/***************************
			 * Mu Buneary
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(MU_BUNEARY.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(MU_BUNEARY.get()), 1.0D, muBunearySpawnRate.get());
				}
			}

			/***************************
			 * Pokezerg Swarm Spawns
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END))
			{
				if (types.contains(BiomeDictionary.Type.NETHER))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ZERGLING_NINCADA.get()), 1.75D, zerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(RAPTOR_ZERGLING_NINCADA.get()), 1.5D, raptorZerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(CRACKLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CRACKLING_NINCADA.get()), 1.5D, cracklingNincadaSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ZERGLING_NINCADA.get()), 1.0D, zerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(RAPTOR_ZERGLING_NINCADA.get()), 1.0D, raptorZerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(CRACKLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CRACKLING_NINCADA.get()), 1.0D, cracklingNincadaSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.SANDY))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ZERGLING_NINCADA.get()), 1.25D, zerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(RAPTOR_ZERGLING_NINCADA.get()), 1.25D, raptorZerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(CRACKLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CRACKLING_NINCADA.get()), 1.0D, cracklingNincadaSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.DEAD) 
						|| types.contains(BiomeDictionary.Type.WASTELAND))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ZERGLING_NINCADA.get()), 1.75D, zerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(RAPTOR_ZERGLING_NINCADA.get()), 1.5D, raptorZerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(CRACKLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CRACKLING_NINCADA.get()), 2.0D, cracklingNincadaSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					registerEntityWorldSpawn(ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(ZERGLING_NINCADA.get()), 1.0D, zerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(RAPTOR_ZERGLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(RAPTOR_ZERGLING_NINCADA.get()), 1.0D, raptorZerglingNincadaSpawnRate.get());
					registerEntityWorldSpawn(CRACKLING_NINCADA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(CRACKLING_NINCADA.get()), 1.0D, cracklingNincadaSpawnRate.get());
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
					registerEntityWorldSpawn(VOORST_MIGHTYENA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(VOORST_MIGHTYENA.get()), 2.0D, voorstMightyenaSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.SWAMP))
				{
					registerEntityWorldSpawn(VOORST_MIGHTYENA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(VOORST_MIGHTYENA.get()), 0.75D, voorstMightyenaSpawnRate.get());
				}
				else if (types.contains(BiomeDictionary.Type.FOREST))
				{
					registerEntityWorldSpawn(VOORST_MIGHTYENA.get(), EntityClassification.MONSTER, biome, 
							spawnRates.getInfo(VOORST_MIGHTYENA.get()), 1.0D, voorstMightyenaSpawnRate.get());
				}
			}
		}
	}

	public static void registerEntityWorldSpawn(EntityType<?> entity, EntityClassification classification, Biome biome, SpawnInfo spawnInfo, double biomeModifierRate, double spawnRate)
	{
		if(spawnRate > 0)
		{
			if(biome != null)
			{
				biome.getSpawns(classification).add(new SpawnListEntry(entity, (int)(biomeModifierRate * spawnRate * spawnInfo.getRate()), spawnInfo.getMin(), spawnInfo.getMax()));
			}
		}
	}
}