package fuzzyacornindustries.kindredlegacy.common.core;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.client.renderer.entity.VoorstMightyenaEntity;
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
import fuzzyacornindustries.kindredlegacy.common.item.AttackBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.AttackOrdererItem;
import fuzzyacornindustries.kindredlegacy.common.item.BiogaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.BlessingOfArceusItem;
import fuzzyacornindustries.kindredlegacy.common.item.CuragaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.EssenceRecallerItem;
import fuzzyacornindustries.kindredlegacy.common.item.FiragaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.GravigaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.LifeBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.ModSpawnEggItem;
import fuzzyacornindustries.kindredlegacy.common.item.OranianBerryItem;
import fuzzyacornindustries.kindredlegacy.common.item.PhoenixHearthstoneItem;
import fuzzyacornindustries.kindredlegacy.common.item.PokemonExplorationKitItem;
import fuzzyacornindustries.kindredlegacy.common.item.PokeraserItem;
import fuzzyacornindustries.kindredlegacy.common.item.PoketamableSummonItem;
import fuzzyacornindustries.kindredlegacy.common.item.PokezergSampleItem;
import fuzzyacornindustries.kindredlegacy.common.item.PotionPowderItem;
import fuzzyacornindustries.kindredlegacy.common.item.QuakagaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.RawSpiceMelangeItem;
import fuzzyacornindustries.kindredlegacy.common.item.RegenCreamItem;
import fuzzyacornindustries.kindredlegacy.common.item.ReviveSeedItem;
import fuzzyacornindustries.kindredlegacy.common.item.SpeedBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.TiberiumShardItem;
import fuzzyacornindustries.kindredlegacy.common.item.VerdantizerItem;
import fuzzyacornindustries.kindredlegacy.common.item.VoidScissorsItem;
import fuzzyacornindustries.kindredlegacy.common.item.WatergaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.XelNagaCircuitItem;
import fuzzyacornindustries.kindredlegacy.common.item.XelNagaDynamoItem;
import fuzzyacornindustries.kindredlegacy.common.item.XelNagaShardItem;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems 
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Names.MOD_ID);

	//================ MATERIALS ITEMS ================//
	public static final RegistryObject<Item> AURUM_DUST = register("aurum_dust");
	public static final RegistryObject<Item> CHARGESTONE = register("chargestone");
	public static final RegistryObject<Item> HUNTERS_CHARGE = register("hunters_charge");
	public static final RegistryObject<PokezergSampleItem> POKEZERG_SAMPLE = register("pokezerg_sample", PokezergSampleItem::new);
	public static final RegistryObject<Item> SILKSCREEN_MESH = register("silkscreen_mesh");
	public static final RegistryObject<Item> TIBERIUM_CASING = register("tiberium_casing");
	public static final RegistryObject<TiberiumShardItem> TIBERIUM_SHARD = register("tiberium_shard", TiberiumShardItem::new);
	public static final RegistryObject<Item> VERDANT_CHARGE = register("verdant_charge");
	public static final RegistryObject<Item> VERDANT_POWDER = register("verdant_powder");
	public static final RegistryObject<Item> WILL_OF_THE_ANCIENTS = register("will_of_the_ancients");
	public static final RegistryObject<XelNagaCircuitItem> XELNAGA_CIRCUIT = register("xelnaga_circuit", XelNagaCircuitItem::new);
	public static final RegistryObject<XelNagaDynamoItem> XELNAGA_DYNAMO = register("xelnaga_dynamo", XelNagaDynamoItem::new);
	public static final RegistryObject<XelNagaShardItem> XELNAGA_SHARD = register("xelnaga_shard", XelNagaShardItem::new);

	public static final RegistryObject<PotionPowderItem> FIRE_RESISTANCE_POWDER = register("fire_resistance_powder", () -> new PotionPowderItem("Fire Resistance Extended"));
	public static final RegistryObject<PotionPowderItem> HEALING_POWDER = register("healing_powder", () -> new PotionPowderItem("Healing II"));
	public static final RegistryObject<PotionPowderItem> INVISIBILITY_POWDER = register("invisibility_powder", () -> new PotionPowderItem("Invisibility Extended"));
	public static final RegistryObject<PotionPowderItem> LEAPING_POWDER = register("leaping_powder", () -> new PotionPowderItem("Leaping II"));
	public static final RegistryObject<PotionPowderItem> POISON_POWDER = register("poison_powder", () -> new PotionPowderItem("Poison II"));
	public static final RegistryObject<PotionPowderItem> REGENERATION_POWDER = register("regeneration_powder", () -> new PotionPowderItem("Regeneration II"));
	public static final RegistryObject<PotionPowderItem> STRENGTH_POWDER = register("strength_powder", () -> new PotionPowderItem("Strength II"));
	public static final RegistryObject<PotionPowderItem> SWIFTNESS_POWDER = register("swiftness_powder", () -> new PotionPowderItem("Swiftness II"));
	public static final RegistryObject<PotionPowderItem> WATER_BREATHING_POWDER = register("water_breathing_powder", () -> new PotionPowderItem("Water Breathing Extended"));

	//================ TOOLS & GEAR ITEMS ================//
	public static final RegistryObject<PhoenixHearthstoneItem> PHOENIX_HEARTHSTONE = register("phoenix_hearthstone", PhoenixHearthstoneItem::new);
	public static final RegistryObject<PokeraserItem> POKERASER = register("pokeraser", PokeraserItem::new);
	public static final RegistryObject<VerdantizerItem> VERDANTIZER = register("verdantizer", VerdantizerItem::new);
	public static final RegistryObject<VoidScissorsItem> VOID_SCISSORS = register("void_scissors", VoidScissorsItem::new);

	//================ TAMABLES ITEMS ================//
	public static final RegistryObject<BlessingOfArceusItem> BLESSING_OF_ARCEUS = register("blessing_of_arceus", BlessingOfArceusItem::new);
	public static final RegistryObject<ReviveSeedItem> REVIVE_SEED = register("revive_seed", ReviveSeedItem::new);
	public static final RegistryObject<OranianBerryItem> ORANIAN_BERRY = register("oranian_berry", () -> new OranianBerryItem(6F));
	public static final RegistryObject<RawSpiceMelangeItem> RAW_SPICE_MELANGE = register("raw_spice_melange", RawSpiceMelangeItem::new);
	public static final RegistryObject<RegenCreamItem> REGEN_CREAM = register("regen_cream", RegenCreamItem::new);

	public static final RegistryObject<AttackOrdererItem> ATTACK_ORDERER = register("attack_orderer", AttackOrdererItem::new);
	public static final RegistryObject<EssenceRecallerItem> ESSENCE_RECALLER = register("essence_recaller", EssenceRecallerItem::new);
	public static final RegistryObject<PokemonExplorationKitItem> POKEMON_EXPLORATION_KIT = register("pokemon_exploration_kit", PokemonExplorationKitItem::new);

	public static final RegistryObject<PoketamableSummonItem> FEYWOOD_ABSOL_SUMMON = register("feywood_absol_summon", () -> new PoketamableSummonItem(FeywoodAbsolEntity::new));
	public static final RegistryObject<PoketamableSummonItem> FIRECRACKER_LITTEN_SUMMON = register("firecracker_litten_summon", () -> new PoketamableSummonItem(FirecrackerLittenEntity::new));
	public static final RegistryObject<PoketamableSummonItem> OKAMI_ESPEON_SUMMON = register("okami_espeon_summon", () -> new PoketamableSummonItem(OkamiEspeonEntity::new));
	public static final RegistryObject<PoketamableSummonItem> OKAMI_SYLVEON_SUMMON = register("okami_sylveon_summon", () -> new PoketamableSummonItem(OkamiSylveonEntity::new));
	public static final RegistryObject<PoketamableSummonItem> OKAMI_UMBREON_SUMMON = register("okami_umbreon_summon", () -> new PoketamableSummonItem(OkamiUmbreonEntity::new));
	public static final RegistryObject<PoketamableSummonItem> VASTAYA_NINETALES_SUMMON = register("vastaya_ninetales_summon", () -> new PoketamableSummonItem(VastayaNinetalesEntity::new));

	public static final RegistryObject<AttackBoostItem> ATTACK_BOOST = register("attack_boost", AttackBoostItem::new);
	public static final RegistryObject<BiogaEssenceItem> BIOGA_ESSENCE = register("bioga_essence", BiogaEssenceItem::new);
	public static final RegistryObject<CuragaEssenceItem> CURAGA_ESSENCE = register("curaga_essence", CuragaEssenceItem::new);
	public static final RegistryObject<FiragaEssenceItem> FIRAGA_ESSENCE = register("firaga_essence", FiragaEssenceItem::new);
	public static final RegistryObject<GravigaEssenceItem> GRAVIGA_ESSENCE = register("graviga_essence", GravigaEssenceItem::new);
	public static final RegistryObject<LifeBoostItem> LIFE_BOOST = register("life_boost", LifeBoostItem::new);
	public static final RegistryObject<QuakagaEssenceItem> QUAKAGA_ESSENCE = register("quakaga_essence", QuakagaEssenceItem::new);
	public static final RegistryObject<SpeedBoostItem> SPEED_BOOST = register("speed_boost", SpeedBoostItem::new);
	public static final RegistryObject<WatergaEssenceItem> WATERGA_ESSENCE = register("waterga_essence", WatergaEssenceItem::new);

	//================ MOD FOODS ================//
	public static final Food ORANIAN_BERRY_FOOD = (new Food.Builder()).hunger(1).saturation(0.1F).fastToEat().setAlwaysEdible().build();

	//================ SPAWN EGG ITEMS ================//
	public static final RegistryObject<ModSpawnEggItem> ARMORED_LUXRAY_SPAWN_EGG = register("armored_luxray_spawn_egg", 
			() -> new ModSpawnEggItem(ArmoredLuxrayEntity::new));
	public static final RegistryObject<ModSpawnEggItem> ARMORED_SHINX_SPAWN_EGG = register("armored_shinx_spawn_egg", 
			() -> new ModSpawnEggItem(ArmoredShinxEntity::new));
	public static final RegistryObject<ModSpawnEggItem> BANDERSNATCH_FENNEKIN_SPAWN_EGG = register("bandersnatch_fennekin_spawn_egg", 
			() -> new ModSpawnEggItem(BandersnatchFennekinEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CLAY_COMMANDER_DELCATTY_SPAWN_EGG = register("clay_commander_delcatty_spawn_egg", 
			() -> new ModSpawnEggItem(ClayCommanderDelcattyEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CLAY_ESPURR_SPAWN_EGG = register("clay_espurr_spawn_egg", 
			() -> new ModSpawnEggItem(ClayEspurrEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CLAY_LUXIO_SPAWN_EGG = register("clay_luxio_spawn_egg", 
			() -> new ModSpawnEggItem(ClayLuxioEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CLAY_PURRLOIN_SPAWN_EGG = register("clay_purrloin_spawn_egg", 
			() -> new ModSpawnEggItem(ClayPurrloinEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CLAY_SHINX_SPAWN_EGG = register("clay_shinx_spawn_egg", 
			() -> new ModSpawnEggItem(ClayShinxEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CLAY_SKITTY_SPAWN_EGG = register("clay_skitty_spawn_egg", 
			() -> new ModSpawnEggItem(ClaySkittyEntity::new));
	public static final RegistryObject<ModSpawnEggItem> CRACKLING_NINCADA_SPAWN_EGG = register("crackling_nincada_spawn_egg", 
			() -> new ModSpawnEggItem(CracklingNincadaEntity::new));
	public static final RegistryObject<ModSpawnEggItem> FEYWOOD_ABSOL_SPAWN_EGG = register("feywood_absol_spawn_egg",
			() -> new ModSpawnEggItem(FeywoodAbsolEntity::new));
	public static final RegistryObject<ModSpawnEggItem> INFESTED_DEERLING_SPAWN_EGG = register("infested_deerling_spawn_egg",
			() -> new ModSpawnEggItem(InfestedDeerlingEntity::new));
	public static final RegistryObject<ModSpawnEggItem> MU_BUNEARY_SPAWN_EGG = register("mu_buneary_spawn_egg",
			() -> new ModSpawnEggItem(MuBunearyEntity::new));
	public static final RegistryObject<ModSpawnEggItem> OKAMI_ESPEON_SPAWN_EGG = register("okami_espeon_spawn_egg",
			() -> new ModSpawnEggItem(OkamiEspeonEntity::new));
	public static final RegistryObject<ModSpawnEggItem> OKAMI_SYLVEON_SPAWN_EGG = register("okami_sylveon_spawn_egg",
			() -> new ModSpawnEggItem(OkamiSylveonEntity::new));
	public static final RegistryObject<ModSpawnEggItem> RAPTOR_ZERGLING_NINCADA_SPAWN_EGG = register("raptor_zergling_nincada_spawn_egg", 
			() -> new ModSpawnEggItem(RaptorZerglingNincadaEntity::new));
	public static final RegistryObject<ModSpawnEggItem> VASTAYA_NINETALES_SPAWN_EGG = register("vastaya_ninetales_spawn_egg",
			() -> new ModSpawnEggItem(VastayaNinetalesEntity::new));
	public static final RegistryObject<ModSpawnEggItem> VOORST_MIGHTYENA_SPAWN_EGG = register("voorst_mightyena_spawn_egg",
			() -> new ModSpawnEggItem(VoorstMightyenaEntity::new));
	public static final RegistryObject<ModSpawnEggItem> ZERGLING_NINCADA_SPAWN_EGG = register("zergling_nincada_spawn_egg", 
			() -> new ModSpawnEggItem(ZerglingNincadaEntity::new));


	/* -----------------------*/

	public static Item.Properties defaultProps() 
	{
		return new Item.Properties().group(ItemGroups.KINDRED_LEGACY_CREATIVE_TAB);
	}

	public static Item.Properties defaultUnstackableProps()
	{
		return defaultProps().maxStackSize(1);
	}

	public static Item.Properties defaultSummonProps() 
	{
		return defaultUnstackableProps().defaultMaxDamage(PoketamableSummonItem.maximumDamage);
	}

	public static Item.Properties toolProps() 
	{
		return defaultProps().maxStackSize(1);
	}

	public static Item.Properties bucketProps() 
	{
		return defaultProps().maxStackSize(1).containerItem(Items.BUCKET);
	}

	private static RegistryObject<Item> register(final String name) 
	{
		return register(name, () -> new Item(ModItems.defaultProps()));
	}

	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> sup) 
	{
		return ITEMS.register(name, sup);
	}

	static class ItemGroups 
	{
		static final ItemGroup KINDRED_LEGACY_CREATIVE_TAB = new ItemGroup(Names.MOD_ID) 
		{
			@Override
			public ItemStack createIcon() 
			{
				return new ItemStack(ModItems.OKAMI_SYLVEON_SUMMON.get());
			}
		};
	}
}