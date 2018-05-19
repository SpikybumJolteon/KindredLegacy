package fuzzyacornindustries.kindredlegacy.entity;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntityBloodmoonFoxfire;
import fuzzyacornindustries.kindredlegacy.entity.ability.EntitySubstituteDoll;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityArmoredLuxray;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityArmoredShinx;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBandersnatchFennekin;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityBloodmoonNinetales;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayCommanderDelcatty;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayEspurr;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayPurrloin;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClayShinx;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityClaySkitty;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityCracklingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityDemonVulpix;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityForcewindEelektrik;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityInfestedDeerling;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityMuBuneary;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityRaptorZerglingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySnowSorceressBraixen;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntitySwordieMienshao;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityVoorstMightyena;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityZerglingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFeywoodAbsol;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFirecrackerLitten;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityFoxcraftFennekin;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiEspeon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiSylveon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityOkamiUmbreon;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.EntityVastayaNinetales;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityBloodmoonFireball;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityFireworkMissile;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityHunterBolt;
import fuzzyacornindustries.kindredlegacy.entity.projectile.EntityVastayaFireball;
import fuzzyacornindustries.kindredlegacy.reference.LibraryBiomeID;
import fuzzyacornindustries.kindredlegacy.reference.LibraryEntityID;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Optional.Method;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class KindredLegacyEntities
{
	/* ======= SPAWNS ======= */
	public static float armoredLuxraySpawnRate;
	public static float armoredShinxSpawnRate;
	public static float bandersnatchFennekinSpawnRate;
	public static float bloodmoonNinetalesSpawnRate;
	public static float clayCommanderDelcattySpawnRate;
	public static float clayEspurrSpawnRate;
	public static float clayPurrloinSpawnRate;
	public static float clayShinxSpawnRate;
	public static float claySkittySpawnRate;
	public static float cracklingNincadaSpawnRate;
	public static float demonVulpixSpawnRate;
	public static float forcewindEelektrikSpawnRate;
	public static float infestedDeerlingSpawnRate;
	public static float muBunearySpawnRate;
	public static float raptorZerglingNincadaSpawnRate;
	public static float snowSorceressBraixenSpawnRate;
	public static float swordieMienshaoSpawnRate;
	public static float voorstMightyenaSpawnRate;
	public static float zerglingNincadaSpawnRate;

	public static boolean canFullMoonMobsSpawnOnFirstNight;

	public static int dimensionSpawnsDisablerList[];
	public static int dimensionSpawnsDisablerSpaceMobs[];

	/* ======= MOB BEHAVIOR ======= */
	public static boolean mobsHostileToVanillaMobs;

	/* ======= GALACTICRAFT COMPATIBILITY ======= */
	public static int moonID;
	public static int marsID;
	public static int asteroidsID;
	public static int venusID;

	/**
	 * Initializes mod item indices from configuration file
	 */
	public static void initConfig(Configuration config) 
	{
		String category;
		Property prop;

		/*================== SPAWNS =====================*/
		category = "Mob Spawn Rates";
		config.addCustomCategoryComment(category,
				"======= Spawn Settings =======" +
						"\n" +
						"\nAdjust mob spawn rates from a value between 0-10.0 (20.0 for mini-boss mobs)." +
						"\n1.0 keeps the spawn rates at their default rate." +
				"\n");
		armoredShinxSpawnRate = config.getFloat("armoredShinxSpawnRate", category, 1.0F, 0F, 10.0F, "Armored Shinx Spawn Rate.");
		bandersnatchFennekinSpawnRate = config.getFloat("bandersnatchFennekinSpawnRate", category, 1.0F, 0F, 10.0F, "Bandersnatch Fennekin Spawn Rate.");
		clayEspurrSpawnRate = config.getFloat("clayEspurrSpawnRate", category, 1.0F, 0F, 10.0F, "Clay Espurr Spawn Rate.");
		clayPurrloinSpawnRate = config.getFloat("clayPurrloinSpawnRate", category, 1.0F, 0F, 10.0F, "Clay Purrloin Spawn Rate.");
		claySkittySpawnRate = config.getFloat("claySkittySpawnRate", category, 1.0F, 0F, 10.0F, "Clay Skitty Spawn Rate.");
		demonVulpixSpawnRate = config.getFloat("demonVulpixSpawnRate", category, 1.0F, 0F, 10.0F, "Demon Vulpix Spawn Rate.");
		forcewindEelektrikSpawnRate = config.getFloat("forcewindEelektricSpawnRate", category, 1.0F, 0F, 10.0F, "Forcewind Eelektric Spawn Rate.");
		infestedDeerlingSpawnRate = config.getFloat("infestedDeerlingSpawnRate", category, 1.0F, 0F, 10.0F, "Infested Deerling Spawn Rate.");
		muBunearySpawnRate = config.getFloat("muBunearySpawnRate", category, 1.0F, 0F, 10.0F, "Mu Buneary Spawn Rate.");
		raptorZerglingNincadaSpawnRate = config.getFloat("raptorZerglingNincadaSpawnRate", category, 1.0F, 0F, 10.0F, "Raptor Zergling Nincada Spawn Rate.");
		snowSorceressBraixenSpawnRate = config.getFloat("snowSorceressBraixenSpawnRate", category, 1.0F, 0F, 10.0F, "Snow Sorceress Braixen Spawn Rate.");
		swordieMienshaoSpawnRate = config.getFloat("swordieMienshaoSpawnRate", category, 1.0F, 0F, 10.0F, "Swordie Mienshao Spawn Rate.");
		zerglingNincadaSpawnRate = config.getFloat("zerglingNincadaSpawnRate", category, 1.0F, 0F, 10.0F, "Zergling Nincada Spawn Rate.");

		armoredLuxraySpawnRate = config.getFloat("armoredLuxraySpawnRate", category, 1.0F, 0F, 20.0F, "Armored Luxray Spawn Rate.");
		clayCommanderDelcattySpawnRate = config.getFloat("clayCommanderDelcattySpawnRate", category, 1.0F, 0F, 20.0F, "Clay Commander Delcatty Spawn Rate.");
		voorstMightyenaSpawnRate = config.getFloat("voorstMightyenaSpawnRate", category, 1.0F, 0F, 20.0F, "Voorst Mightyena Spawn Rate.");

		bloodmoonNinetalesSpawnRate = config.getFloat("bloodmoonNinetalesSpawnRate", category, 1.0F, 0F, 30.0F, "Bloodmoon Ninetales Spawn Rate.");
		
		clayShinxSpawnRate = config.getFloat("clayShinxSpawnRate", category, 1.0F, 0F, 10.0F, "Clay Shinx Spawn Rate.");
		cracklingNincadaSpawnRate = config.getFloat("cracklingNincadaSpawnRate", category, 1.0F, 0F, 10.0F, "Crackling Nincada Spawn Rate.");

		/*================== MOB SPAWNS =====================*/
		category = "Mob Spawns";

		prop = config.get(category, "Dimensions disabled for most mod mobs", new int[] {-28, -29, -30, -31});
		prop.setComment("List the dimensions regular mod mobs should not spawn in");
		prop.setRequiresMcRestart(true);
		dimensionSpawnsDisablerList = prop.getIntList();
/*
		prop = config.get(category, "Dimensions disabled for mod's space themed mobs", new int[] {});
		prop.setComment("List the dimensions the mod's space themed mobs should not spawn in");
		prop.setRequiresMcRestart(true);
		dimensionSpawnsDisablerSpaceMobs = prop.getIntList();*/

		//canFullMoonMobsSpawnOnFirstNight = config.getBoolean("canFullMoonMobsSpawnOnFirstNight", category, false, "Can Full Moon mobs spawn on first night?");
		//minDaysToSpawnDarknut = 24000 * MathHelper.clamp_int(config.get("Mob Spawns", "Minimum number of days required to pass before Darknuts may spawn [0-30]", 7).getInt(), 0, 30);

		/*================== MOBS BEHAVIOR =====================*/
		category = "Mob Behavior";
		config.addCustomCategoryComment(category,
				"\n======= Mob Behavior Settings =======");
		mobsHostileToVanillaMobs = config.getBoolean("mobsHostileToVanillaMobs", category, true, "Mod mobs with hostility to Vanilla mobs enabled?");

		/*================== GALACTICRAFT COMPATIBILITY =====================*//*
		category = "Galacticraft Compatibility";
		config.addCustomCategoryComment(category,
				"\n======= Galacticraft Settings =======");
		moonID = config.getInt("moonID", category, -28, -256, 256, "Defined Moon Dimension ID for Galacticraft?");
		marsID = config.getInt("marsID", category, -29, -256, 256, "Defined Mars Dimension ID for Galacticraft?");
		asteroidsID = config.getInt("asteroidsID", category, -30, -256, 256, "Defined Asteroids Dimension ID for Galacticraft?");
		venusID = config.getInt("venusID", category, -31, -256, 256, "Defined Venus Dimension ID for Galacticraft?");*/
	}

	public static void registerEntityMob()
	{
		createEntity(EntityArmoredLuxray.class, EntityArmoredLuxray.getMobName(), LibraryEntityID.ARMORED_LUXRAY, 0x062518, 0xd9c225);
		createEntity(EntityArmoredShinx.class, EntityArmoredShinx.getMobName(), LibraryEntityID.ARMORED_SHINX, 0xdac329, 0x062518);
		createEntity(EntityBandersnatchFennekin.class, EntityBandersnatchFennekin.getMobName(), LibraryEntityID.BANDERSNATCH_FENNEKIN, 0xccd9da, 0x29494e);
		createEntity(EntityBloodmoonNinetales.class, EntityBloodmoonNinetales.getMobName(), LibraryEntityID.BLOODMOON_NINETALES, 0x0b0101, 0xeeeeee);
		createEntity(EntityClayCommanderDelcatty.class, EntityClayCommanderDelcatty.getMobName(), LibraryEntityID.CLAY_COMMANDER_DELCATTY, 0x392f1f, 0x6d6d6d);
		createEntity(EntityClayEspurr.class, EntityClayEspurr.getMobName(), LibraryEntityID.CLAY_ESPURR, 0x322818, 0xb166da);
		createEntity(EntityClayPurrloin.class, EntityClayPurrloin.getMobName(), LibraryEntityID.CLAY_PURRLOIN, 0x3d2723, 0xd4a0c8);
		createEntity(EntityClayShinx.class, EntityClayShinx.getMobName(), LibraryEntityID.CLAY_SHINX, 0x262640, 0x00b4ff);
		createEntity(EntityClaySkitty.class, EntityClaySkitty.getMobName(), LibraryEntityID.CLAY_SKITTY, 0x2e2516, 0xe6bbbb);
		createEntity(EntityCracklingNincada.class, EntityCracklingNincada.getMobName(), LibraryEntityID.CRACKLING_NINCADA, 0x4b2230, 0xddff00);
		createEntity(EntityDemonVulpix.class, EntityDemonVulpix.getMobName(), LibraryEntityID.DEMON_VULPIX, 0x290f29, 0xffffff);
		createEntity(EntityFeywoodAbsol.class, EntityFeywoodAbsol.getMobName(), LibraryEntityID.FEYWOOD_ABSOL, 0x191214, 0xdec1cc);
		createEntity(EntityFirecrackerLitten.class, EntityFirecrackerLitten.getMobName(), LibraryEntityID.FIRECRACKER_LITTEN, 0x000000, 0xe93030);
		createEntity(EntityForcewindEelektrik.class, EntityForcewindEelektrik.getMobName(), LibraryEntityID.FORCEWIND_EELEKTRIK, 0x282954, 0xdbecff);
		createEntity(EntityFoxcraftFennekin.class, EntityFoxcraftFennekin.getMobName(), LibraryEntityID.FOXCRAFT_FENNEKIN, 0x351e3b, 0xdf3c1c);
		createEntity(EntityInfestedDeerling.class, EntityInfestedDeerling.getMobName(), LibraryEntityID.INFESTED_DEERLING, 0x857450, 0x8801aa);
		createEntity(EntityMuBuneary.class, EntityMuBuneary.getMobName(), LibraryEntityID.MU_BUNEARY, 0x3e3833, 0xaaa39c);
		createEntity(EntityOkamiEspeon.class, EntityOkamiEspeon.getMobName(), LibraryEntityID.OKAMI_ESPEON, 0xcab3d2, 0xff0000);
		createEntity(EntityOkamiSylveon.class, EntityOkamiSylveon.getMobName(), LibraryEntityID.OKAMI_SYLVEON, 0xfffff5, 0xfba4a4);
		createEntity(EntityOkamiUmbreon.class, EntityOkamiUmbreon.getMobName(), LibraryEntityID.OKAMI_UMBREON, 0x000000, 0xffe776);
		createEntity(EntityRaptorZerglingNincada.class, EntityRaptorZerglingNincada.getMobName(), LibraryEntityID.RAPTOR_ZERGLING_NINCADA, 0x7f722a, 0x59b18e);
		createEntity(EntitySnowSorceressBraixen.class, EntitySnowSorceressBraixen.getMobName(), LibraryEntityID.SNOW_SORCERESS_BRAIXEN, 0xbde9f5, 0xfff44b);
		createEntity(EntitySwordieMienshao.class, EntitySwordieMienshao.getMobName(), LibraryEntityID.SWORDIE_MIENSHAO, 0x2b2e3f, 0x096859);
		createEntity(EntityVastayaNinetales.class, EntityVastayaNinetales.getMobName(), LibraryEntityID.VASTAYA_NINETALES, 0xb7b6cc, 0xb60a0a);
		createEntity(EntityVoorstMightyena.class, EntityVoorstMightyena.getMobName(), LibraryEntityID.VOORST_MIGHTYENA, 0xbba559, 0x46390e);
		createEntity(EntityZerglingNincada.class, EntityZerglingNincada.getMobName(), LibraryEntityID.ZERGLING_NINCADA, 0x7f722a, 0xe0d9a8);
	}

	public static void registerEntityAbilities()
	{
		createEntity(EntitySubstituteDoll.class, EntitySubstituteDoll.getMobName(), LibraryEntityID.SUBSTITUTE_DOLL);
		createEntity(EntityBloodmoonFoxfire.class, EntityBloodmoonFoxfire.getMobName(), LibraryEntityID.BLOODMOON_FOXFIRE);
	}

	public static void registerEntityProjectiles()
	{
		createEntity(EntityFireworkMissile.class, "firework_missile", LibraryEntityID.FIREWORK_MISSILE);
		createEntity(EntityHunterBolt.class, "hunter_bolt", LibraryEntityID.HUNTER_BOLT);
		createEntity(EntityVastayaFireball.class, "vastaya_fireball", LibraryEntityID.VASTAYA_FIREBALL);
		createEntity(EntityBloodmoonFireball.class, "bloodmoon_fireball", LibraryEntityID.BLOODMOON_FIREBALL);
	}

	public static void addSpawnLocations()
	{
		List<Biome> biomesToSpawnIn;

		biomesToSpawnIn = Lists.newArrayList();

		for (Biome biome : Biome.REGISTRY)
		{
			Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(biome);

			//			if(!types.contains(BiomeDictionary.Type.END) || !types.contains(BiomeDictionary.Type.NETHER))
			//			{
			//				if (types.contains(BiomeDictionary.Type.))
			//				{
			//					biome.getSpawnableList(EnumCreatureType.MONSTER).add();
			//				}
			//				else if (types.contains(BiomeDictionary.Type.))
			//				{
			//					biome.getSpawnableList(EnumCreatureType.MONSTER).add();
			//				}
			//			}

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
					addSpawn(biome, EntityArmoredLuxray.class, armoredLuxraySpawnRate, 12, 1, 1);
					addSpawn(biome, EntityArmoredShinx.class, armoredShinxSpawnRate, 60, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.HOT)
						&& (types.contains(BiomeDictionary.Type.DRY) || types.contains(BiomeDictionary.Type.WASTELAND)))
				{
					addSpawn(biome, EntityArmoredLuxray.class, armoredLuxraySpawnRate, 6, 1, 1);
					addSpawn(biome, EntityArmoredShinx.class, armoredShinxSpawnRate, 40, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.SPARSE)
						&& types.contains(BiomeDictionary.Type.SNOWY))
				{
					addSpawn(biome, EntityArmoredLuxray.class, armoredLuxraySpawnRate, 10, 1, 1);
					addSpawn(biome, EntityArmoredShinx.class, armoredShinxSpawnRate, 90, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.HILLS))
				{
					addSpawn(biome, EntityArmoredLuxray.class, armoredLuxraySpawnRate, 5, 1, 1);
					addSpawn(biome, EntityArmoredShinx.class, armoredShinxSpawnRate, 35, 1, 1);
				}
			}

			/***************************
			 * Bandersnatch Fennekin
			 ***************************/
			addSpecificBiomeSpawn(LibraryBiomeID.EXTREME_HILLS, EntityBandersnatchFennekin.class, bandersnatchFennekinSpawnRate, 80, 1, 1);
			addSpecificBiomeSpawn(LibraryBiomeID.EXTREME_HILLS_EDGE, EntityBandersnatchFennekin.class, bandersnatchFennekinSpawnRate, 80, 1, 1);
			addSpecificBiomeSpawn(LibraryBiomeID.EXTREME_HILLS_PLUS, EntityBandersnatchFennekin.class, bandersnatchFennekinSpawnRate, 80, 1, 1);

			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.SNOWY))
				{
					addSpawn(biome, EntityBandersnatchFennekin.class, bandersnatchFennekinSpawnRate, 100, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.COLD) && types.contains(BiomeDictionary.Type.FOREST))
				{
					addSpawn(biome, EntityBandersnatchFennekin.class, bandersnatchFennekinSpawnRate, 80, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.COLD))
				{
					addSpawn(biome, EntityBandersnatchFennekin.class, bandersnatchFennekinSpawnRate, 50, 1, 1);
				}
			}

			/***************************
			 * Bloodmoon Ninetales
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if(types.contains(BiomeDictionary.Type.SPARSE))
				{
					addSpawn(biome, EntityBloodmoonNinetales.class, bloodmoonNinetalesSpawnRate, 6, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					addSpawn(biome, EntityBloodmoonNinetales.class, bloodmoonNinetalesSpawnRate, 6, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					addSpawn(biome, EntityBloodmoonNinetales.class, bloodmoonNinetalesSpawnRate, 6, 1, 1);
				}
			}

			/***************************
			 * Clay Army Spawns
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.JUNGLE))
				{
					addSpawn(biome, EntityClayCommanderDelcatty.class, clayCommanderDelcattySpawnRate, 12, 1, 1);
					addSpawn(biome, EntityClayEspurr.class, clayEspurrSpawnRate, 60, 1, 1);
					addSpawn(biome, EntityClayPurrloin.class, clayPurrloinSpawnRate, 120, 3, 6);
					addSpawn(biome, EntityClayShinx.class, clayShinxSpawnRate, 200, 3, 6);
					addSpawn(biome, EntityClaySkitty.class, claySkittySpawnRate, 140, 3, 6);
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					addSpawn(biome, EntityClayCommanderDelcatty.class, clayCommanderDelcattySpawnRate, 9, 1, 1);
					addSpawn(biome, EntityClayEspurr.class, clayEspurrSpawnRate, 60, 1, 1);
					addSpawn(biome, EntityClayPurrloin.class, clayPurrloinSpawnRate, 75, 3, 6);
					addSpawn(biome, EntityClayShinx.class, clayShinxSpawnRate, 80, 3, 6);
					addSpawn(biome, EntityClaySkitty.class, claySkittySpawnRate, 100, 3, 6);
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					addSpawn(biome, EntityClayCommanderDelcatty.class, clayCommanderDelcattySpawnRate, 7, 1, 1);
					addSpawn(biome, EntityClayEspurr.class, clayEspurrSpawnRate, 60, 1, 1);
					addSpawn(biome, EntityClayPurrloin.class, clayPurrloinSpawnRate, 100, 3, 6);
					addSpawn(biome, EntityClayShinx.class, clayShinxSpawnRate, 170, 3, 6);
					addSpawn(biome, EntityClaySkitty.class, claySkittySpawnRate, 130, 3, 6);
				}
				else if (types.contains(BiomeDictionary.Type.SANDY))
				{
					addSpawn(biome, EntityClayCommanderDelcatty.class, clayCommanderDelcattySpawnRate, 7, 1, 1);
					addSpawn(biome, EntityClayEspurr.class, clayEspurrSpawnRate, 60, 1, 1);
					addSpawn(biome, EntityClayPurrloin.class, clayPurrloinSpawnRate, 120, 3, 6);
					addSpawn(biome, EntityClayShinx.class, clayShinxSpawnRate, 150, 3, 6);
					addSpawn(biome, EntityClaySkitty.class, claySkittySpawnRate, 110, 3, 6);
				}
			}

			/***************************
			 * Demon Vulpix
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END))
			{
				if (types.contains(BiomeDictionary.Type.NETHER))
				{
					addSpawn(biome, EntityDemonVulpix.class, demonVulpixSpawnRate, 80, 3, 6);
				}
				else if (types.contains(BiomeDictionary.Type.HOT))
				{
					addSpawn(biome, EntityDemonVulpix.class, demonVulpixSpawnRate, 150, 3, 6);
				}
			}

			/***************************
			 * Forcewind Eelektrik
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.OCEAN) 
						|| types.contains(BiomeDictionary.Type.BEACH) 
						|| types.contains(BiomeDictionary.Type.RIVER))
				{
					addSpawn(biome, EntityForcewindEelektrik.class, forcewindEelektrikSpawnRate, 160, 2, 4);
				}
				else if (types.contains(BiomeDictionary.Type.SWAMP)
						|| types.contains(BiomeDictionary.Type.JUNGLE))
				{
					addSpawn(biome, EntityForcewindEelektrik.class, forcewindEelektrikSpawnRate, 120, 2, 4);
				}
			}

			/***************************
			 * Infested Deerling
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.SAVANNA))
				{
					addSpawn(biome, EntityInfestedDeerling.class, infestedDeerlingSpawnRate, 100, 2, 4);
				}
				else if (types.contains(BiomeDictionary.Type.JUNGLE))
				{
					addSpawn(biome, EntityInfestedDeerling.class, infestedDeerlingSpawnRate, 120, 2, 4);
				}
				else if (types.contains(BiomeDictionary.Type.FOREST) 
						|| types.contains(BiomeDictionary.Type.SWAMP) 
						|| types.contains(BiomeDictionary.Type.CONIFEROUS))
				{
					addSpawn(biome, EntityInfestedDeerling.class, infestedDeerlingSpawnRate, 140, 2, 4);
				}
			}			

			/***************************
			 * Mu Buneary
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.FOREST))
				{
					addSpawn(biome, EntityMuBuneary.class, muBunearySpawnRate, 100, 2, 4);
				}
			}

			/***************************
			 * Snow Sorceress Braixen
			 ***************************/
			addSpecificBiomeSpawn(LibraryBiomeID.EXTREME_HILLS, EntitySnowSorceressBraixen.class, snowSorceressBraixenSpawnRate, 95, 1, 2);
			addSpecificBiomeSpawn(LibraryBiomeID.EXTREME_HILLS_EDGE, EntitySnowSorceressBraixen.class, snowSorceressBraixenSpawnRate, 95, 1, 2);
			addSpecificBiomeSpawn(LibraryBiomeID.EXTREME_HILLS_PLUS, EntitySnowSorceressBraixen.class, snowSorceressBraixenSpawnRate, 95, 1, 2);

			if(!types.contains(BiomeDictionary.Type.END))
			{
				if (types.contains(BiomeDictionary.Type.SNOWY))
				{
					addSpawn(biome, EntitySnowSorceressBraixen.class, snowSorceressBraixenSpawnRate, 95, 1, 2);
				}
			}

			/***************************
			 * Swordie Mienshao
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if (types.contains(BiomeDictionary.Type.CONIFEROUS))
				{
					addSpawn(biome, EntitySwordieMienshao.class, swordieMienshaoSpawnRate, 140, 1, 2);
				}
				else if (types.contains(BiomeDictionary.Type.SNOWY))
				{
					addSpawn(biome, EntitySwordieMienshao.class, swordieMienshaoSpawnRate, 60, 1, 2);
				}
				else if (types.contains(BiomeDictionary.Type.MOUNTAIN))
				{
					addSpawn(biome, EntitySwordieMienshao.class, swordieMienshaoSpawnRate, 100, 1, 2);
				}
			}

			/***************************
			 * Voorst Mightyena
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END) && !types.contains(BiomeDictionary.Type.NETHER))
			{
				if(types.contains(BiomeDictionary.Type.SAVANNA))
				{
					addSpawn(biome, EntityVoorstMightyena.class, voorstMightyenaSpawnRate, 8, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.SWAMP))
				{
					addSpawn(biome, EntityVoorstMightyena.class, voorstMightyenaSpawnRate, 4, 1, 1);
				}
				else if (types.contains(BiomeDictionary.Type.FOREST))
				{
					addSpawn(biome, EntityVoorstMightyena.class, voorstMightyenaSpawnRate, 6, 1, 1);
				}
			}

			/***************************
			 * Pokezerg Swarm Spawns
			 ***************************/
			if(!types.contains(BiomeDictionary.Type.END))
			{
				if (types.contains(BiomeDictionary.Type.NETHER))
				{
					addSpawn(biome, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 170, 5, 8);
					addSpawn(biome, EntityRaptorZerglingNincada.class, raptorZerglingNincadaSpawnRate, 100, 3, 5);
					addSpawn(biome, EntityCracklingNincada.class, cracklingNincadaSpawnRate, 55, 3, 5);
				}
				else if (types.contains(BiomeDictionary.Type.MESA))
				{
					addSpawn(biome, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 70, 3, 6);
					addSpawn(biome, EntityRaptorZerglingNincada.class, raptorZerglingNincadaSpawnRate, 40, 3, 5);
					addSpawn(biome, EntityCracklingNincada.class, cracklingNincadaSpawnRate, 30, 3, 5);
				}
				else if (types.contains(BiomeDictionary.Type.SANDY))
				{
					addSpawn(biome, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 90, 3, 6);
					addSpawn(biome, EntityRaptorZerglingNincada.class, raptorZerglingNincadaSpawnRate, 55, 3, 5);
					addSpawn(biome, EntityCracklingNincada.class, cracklingNincadaSpawnRate, 40, 3, 5);
				}
				else if (types.contains(BiomeDictionary.Type.DEAD) 
						|| types.contains(BiomeDictionary.Type.WASTELAND))
				{
					addSpawn(biome, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 170, 5, 8);
					addSpawn(biome, EntityRaptorZerglingNincada.class, raptorZerglingNincadaSpawnRate, 100, 3, 5);
					addSpawn(biome, EntityCracklingNincada.class, cracklingNincadaSpawnRate, 75, 3, 5);
				}
				else if (types.contains(BiomeDictionary.Type.PLAINS))
				{
					addSpawn(biome, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 80, 3, 6);
					addSpawn(biome, EntityRaptorZerglingNincada.class, raptorZerglingNincadaSpawnRate, 50, 3, 5);
					addSpawn(biome, EntityCracklingNincada.class, cracklingNincadaSpawnRate, 25, 3, 5);
				}
			}
		}
/*
		if(KindredLegacyMain.isGalacticraftEnabled)
			setGalacticraftSpawns();*/
	}

	@Method(modid="galacticraftcore")
	public static void setGalacticraftSpawns()
	{
		String worldName;

		worldName = "moon";

		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 30, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, raptorZerglingNincadaSpawnRate, 120, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, cracklingNincadaSpawnRate, 75, 3, 6);

		worldName = "mars";

		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 30, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, raptorZerglingNincadaSpawnRate, 120, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, cracklingNincadaSpawnRate, 120, 3, 6);

		worldName = "asteroids";

		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 30, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, raptorZerglingNincadaSpawnRate, 120, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, cracklingNincadaSpawnRate, 150, 3, 6);

		worldName = "venus";

		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, zerglingNincadaSpawnRate, 30, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, raptorZerglingNincadaSpawnRate, 120, 3, 6);
		addGalacticraftSpawn(worldName, EntityZerglingNincada.class, cracklingNincadaSpawnRate, 180, 3, 6);
		//ReflectionHelper.findMethod(clazz, instance, methodNames, methodTypes)
	}

	public static void createEntity(Class entityClass, String entityName, int entityID)
	{
		int trackingRange = 64;
		int updateFrequency = 1;
		boolean sendsVelocityUpdates = true;

		EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.MOD_ID, entityName), entityClass, entityName, entityID, KindredLegacyMain.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}

	public static void createEntity(Class entityClass, String entityName, int entityID, int parPrimaryColor, int parSecondaryColor)
	{
		int trackingRange = 64;
		int updateFrequency = 1;
		boolean sendsVelocityUpdates = true;

		EntityRegistry.registerModEntity(new ResourceLocation(ModInfo.MOD_ID, entityName), entityClass, entityName, entityID, KindredLegacyMain.instance, trackingRange, updateFrequency, sendsVelocityUpdates, parPrimaryColor, parSecondaryColor);
	}

	public static void addSpawn(Biome biome, Class <? extends EntityLiving > entityclass, float spawnRate, int spawnWeight, int groupCountMin, int groupCountMax)
	{
		if(spawnRate > 0)
		{
			biome.getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(entityclass, (int)(spawnRate * spawnWeight), groupCountMin, groupCountMax));
		}
	}

	public static void addSpecificBiomeSpawn(int biomeID, Class <? extends EntityLiving > entityclass, float spawnRate, int spawnWeight, int groupCountMin, int groupCountMax)
	{
		if(spawnRate > 0)
		{
			Biome.getBiome(biomeID).getSpawnableList(EnumCreatureType.MONSTER).add(new SpawnListEntry(entityclass, (int)(spawnRate * spawnWeight), groupCountMin, groupCountMax));
		}
	}

	@Method(modid="galacticraftcore")
	public static void addGalacticraftSpawn(String planetName, Class <? extends EntityLiving > entityclass, float spawnRate, int spawnWeight, int groupCountMin, int groupCountMax)
	{
		if(spawnRate > 0)
		{
			//Planet.addMobToSpawn(planetName, new SpawnListEntry(entityclass, (int)(spawnRate * spawnWeight), groupCountMin, groupCountMax));
		}
	}

	public static boolean isGalacticraftDimension(int dimensionID)
	{
		return (dimensionID == moonID || dimensionID == marsID || dimensionID == asteroidsID || dimensionID == venusID) && KindredLegacyMain.isGalacticraftEnabled;
	}
}