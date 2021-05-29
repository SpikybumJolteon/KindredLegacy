package fuzzyacornindustries.kindredlegacy.common.core;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.common.entity.mob.hostile.ArmoredShinxEntity;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.FeywoodAbsolEntity;
import fuzzyacornindustries.kindredlegacy.common.item.AttackBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.AttackOrdererItem;
import fuzzyacornindustries.kindredlegacy.common.item.BiogaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.CuragaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.EssenceRecallerItem;
import fuzzyacornindustries.kindredlegacy.common.item.FiragaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.GravigaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.LifeBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.ModSpawnEggItem;
import fuzzyacornindustries.kindredlegacy.common.item.OranianBerryItem;
import fuzzyacornindustries.kindredlegacy.common.item.PokeraserItem;
import fuzzyacornindustries.kindredlegacy.common.item.PoketamableSummonItem;
import fuzzyacornindustries.kindredlegacy.common.item.QuakagaEssenceItem;
import fuzzyacornindustries.kindredlegacy.common.item.RawSpiceMelangeItem;
import fuzzyacornindustries.kindredlegacy.common.item.RegenCreamItem;
import fuzzyacornindustries.kindredlegacy.common.item.ReviveSeedItem;
import fuzzyacornindustries.kindredlegacy.common.item.SpeedBoostItem;
import fuzzyacornindustries.kindredlegacy.common.item.WatergaEssenceItem;
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
	//	public static final RegistryObject<PokezergSampleItem> POKEZERG_SAMPLE = register("pokezerg_sample", PokezergSampleItem::new);
	//	public static final RegistryObject<RawSpiceMelangeItem> RAW_SPICE_MELANGE = register("raw_spice_melange", RawSpiceMelangeItem::new);
	//	public static final RegistryObject<RegenCreamItem> REGEN_CREAM = register("regen_cream", RegenCreamItem::new);
	//	public static final RegistryObject<Item> SILKSCREEN_MESH = register("silkscreen_mesh");
	//	public static final RegistryObject<Item> TIBERIUM_CASING = register("tiberium_casing");
	//	public static final RegistryObject<TiberiumShardItem> TIBERIUM_SHARD = register("tiberium_shard", TiberiumShardItem::new);
	//	public static final RegistryObject<Item> VERDANT_CHARGE = register("verdant_charge");
	//	public static final RegistryObject<Item> VERDANT_POWDER = register("verdant_powder");
	//	public static final RegistryObject<XelNagaCircuitItem> XELNAGA_CIRCUIT = register("xelnaga_circuit", XelNagaCircuitItem::new);
	//	public static final RegistryObject<XelNagaDynamoItem> XELNAGA_DYNAMO = register("xelnaga_dynamo", XelNagaDynamoItem::new);
	//	public static final RegistryObject<XelNagaShardItem> XELNAGA_SHARD = register("xelnaga_shard", XelNagaShardItem::new);
	public static final RegistryObject<PokeraserItem> POKERASER = register("pokeraser", PokeraserItem::new);

	//================ TAMABLES ITEMS ================//
	//public static final RegistryObject<BlessingOfArceusItem> BLESSING_OF_ARCEUS = register("blessing_of_arceus", BlessingOfArceusItem::new);
	public static final RegistryObject<ReviveSeedItem> REVIVE_SEED = register("revive_seed", ReviveSeedItem::new);
	public static final RegistryObject<OranianBerryItem> ORANIAN_BERRY = register("oranian_berry", () -> new OranianBerryItem(6F));
	public static final RegistryObject<RawSpiceMelangeItem> RAW_SPICE_MELANGE = register("raw_spice_melange", RawSpiceMelangeItem::new);
	public static final RegistryObject<RegenCreamItem> REGEN_CREAM = register("regen_cream", RegenCreamItem::new);

	public static final RegistryObject<AttackOrdererItem> ATTACK_ORDERER = register("attack_orderer", AttackOrdererItem::new);
	public static final RegistryObject<EssenceRecallerItem> ESSENCE_RECALLER = register("essence_recaller", EssenceRecallerItem::new);
	//public static final RegistryObject<PokemonExplorationKitItem> POKEMON_EXPLORATION_KIT = register("pokemon_exploration_kit", PokemonExplorationKitItem::new);

	public static final RegistryObject<PoketamableSummonItem> FEYWOOD_ABSOL_SUMMON = register("feywood_absol_summon", () -> new PoketamableSummonItem(FeywoodAbsolEntity::new));
	//	public static final RegistryObject<FirecrackerLittenSummonItem> FIRECRACKER_LITTEN_SUMMON = register("firecracker_litten_summon", FirecrackerLittenSummonItem::new);
	//	public static final RegistryObject<OkamiEspeonSummonItem> OKAMI_ESPEON_SUMMON = register("okami_espeon_summon", OkamiEspeonSummonItem::new);
	//	public static final RegistryObject<OkamiSylveonSummonItem> OKAMI_SYLVEON_SUMMON = register("okami_sylveon_summon", OkamiSylveonSummonItem::new);
	//	public static final RegistryObject<OkamiUmbreonSummonItem> OKAMI_UMBREON_SUMMON = register("okami_umbreon_summon", OkamiUmbreonSummonItem::new);

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
	//	public static final RegistryObject<SpawnEggItem> ARMORED_LUXRAY_EGG = registerTest("armored_luxray_egg", 
	//			() -> new SpawnEggItem(KindredLegacyEntities.ARMORED_LUXRAY.get(), 0x062518, 0xd9c225, defaultProps()));
	public static final RegistryObject<ModSpawnEggItem> ARMORED_SHINX_SPAWN_EGG = register("armored_shinx_spawn_egg", 
			() -> new ModSpawnEggItem(ArmoredShinxEntity::new));
	public static final RegistryObject<ModSpawnEggItem> FEYWOOD_ABSOL_SPAWN_EGG = register("feywood_absol_spawn_egg",
			() -> new ModSpawnEggItem(FeywoodAbsolEntity::new));


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
				return new ItemStack(ModItems.FEYWOOD_ABSOL_SUMMON.get());
			}
		};
	}
}