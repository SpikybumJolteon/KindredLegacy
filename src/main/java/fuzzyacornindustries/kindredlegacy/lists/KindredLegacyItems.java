package fuzzyacornindustries.kindredlegacy.lists;

import java.util.ArrayList;
import java.util.List;

import fuzzyacornindustries.kindredlegacy.block.BlockItemBase;
import fuzzyacornindustries.kindredlegacy.item.ItemBase;
import fuzzyacornindustries.kindredlegacy.item.PokezergSampleItem;
import fuzzyacornindustries.kindredlegacy.item.PotionPowderItem;
import fuzzyacornindustries.kindredlegacy.item.RawSpiceMelangeItem;
import fuzzyacornindustries.kindredlegacy.item.RegenCreamItem;
import fuzzyacornindustries.kindredlegacy.item.TiberiumShardItem;
import fuzzyacornindustries.kindredlegacy.item.VerdantizerItem;
import fuzzyacornindustries.kindredlegacy.item.VoidScissorsItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.AttackBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.AttackOrdererItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.BiogaEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.BlessingOfArceusItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.CuragaEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.EssenceRecallerItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.FeywoodAbsolSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.FiragaEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.FirecrackerLittenSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.GravigaEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.LifeBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OkamiEspeonSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OkamiSylveonSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OkamiUmbreonSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OranianBerryItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.PhoenixHearthstoneItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.PokemonExplorationKitItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.PoketamableSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.QuakagaEssenceItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.ReviveSeedItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.SpeedBoostItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.WatergaEssenceItem;
import fuzzyacornindustries.kindredlegacy.utility.UtilityFunctions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class KindredLegacyItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();

	//================ MATERIALS ITEMS ================//
	public static Item AURUM_DUST = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("aurum_dust"));
	public static Item CHARGESTONE = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("chargestone"));
	public static Item HUNTERS_CHARGE = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("hunters_charge"));
	public static Item POKEZERG_SAMPLE = new PokezergSampleItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("pokezerg_sample"));
	public static Item RAW_SPICE_MELANGE = new RawSpiceMelangeItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("raw_spice_melange"));
	public static Item REGEN_CREAM = new RegenCreamItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("regen_cream"));
	public static Item SILKSCREEN_MESH = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("silkscreen_mesh"));
	public static Item TIBERIUM_CASING = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("tiberium_casing"));
	public static Item TIBERIUM_SHARD = new TiberiumShardItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("tiberium_shard"));
	public static Item VERDANT_CHARGE = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("verdant_charge"));
	public static Item VERDANT_POWDER = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("verdant_powder"));
	public static Item XELNAGA_CIRCUIT = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("xelnaga_circuit"));
	public static Item XELNAGA_SHARD = new ItemBase(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("xelnaga_shard"));
	
	public static Item VERDANTIZER = new VerdantizerItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("verdantizer"));
	public static Item VOID_SCISSORS = new VoidScissorsItem(new Item.Properties().group(ItemGroup.MISC).maxDamage(128)).setRegistryName(UtilityFunctions.location("void_scissors"));

	public static Item FIRE_RESISTANCE_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Fire Resistance Extended").setRegistryName(UtilityFunctions.location("fire_resistance_powder"));
	public static Item HEALING_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Healing II").setRegistryName(UtilityFunctions.location("healing_powder"));
	public static Item INVISIBILITY_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Invisibility Extended").setRegistryName(UtilityFunctions.location("invisibility_powder"));
	public static Item LEAPING_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Leaping II").setRegistryName(UtilityFunctions.location("leaping_powder"));
	public static Item POISON_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Poison II").setRegistryName(UtilityFunctions.location("poison_powder"));
	public static Item REGENERATION_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Regeneration II").setRegistryName(UtilityFunctions.location("regeneration_powder"));
	public static Item STRENGTH_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Strength II").setRegistryName(UtilityFunctions.location("strength_powder"));
	public static Item SWIFTNESS_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Swiftness II").setRegistryName(UtilityFunctions.location("swiftness_powder"));
	public static Item WATER_BREATHING_POWDER = new PotionPowderItem(new Item.Properties().group(ItemGroup.MISC), "Water Breathing Extended").setRegistryName(UtilityFunctions.location("water_breathing_powder"));
	
	//================ SPAWN EGG ITEMS ================//
	public static Item ARMORED_LUXRAY_EGG;
	public static Item ARMORED_SHINX_EGG;
	public static Item BANDERSNATCH_FENNEKIN_EGG;
	public static Item CLAY_COMMANDER_DELCATTY_EGG;
	public static Item CLAY_ESPURR_EGG;
	public static Item CLAY_LUXIO_EGG;
	public static Item CLAY_PURRLOIN_EGG;
	public static Item CLAY_SHINX_EGG;
	public static Item CLAY_SKITTY_EGG;
	public static Item CRACKLING_NINCADA_EGG;
	public static Item INFESTED_DEERLING_EGG;
	public static Item MU_BUNEARY_EGG;
	public static Item RAPTOR_ZERGLING_NINCADA_EGG;
	public static Item SNOW_SORCERESS_BRAIXEN_EGG;
	public static Item VOORST_MIGHTYENA_EGG;
	public static Item ZERGLING_NINCADA_EGG;

	public static Item FEYWOOD_ABSOL_EGG;
	public static Item FIRECRACKER_LITTEN_EGG;
	public static Item OKAMI_ESPEON_EGG;
	public static Item OKAMI_SYLVEON_EGG;
	public static Item OKAMI_UMBREON_EGG;
	
	//================ TAMABLES ITEMS ================//
	public static Item BLESSING_OF_ARCEUS = new BlessingOfArceusItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("blessing_of_arceus"));
	public static Item REVIVE_SEED = new ReviveSeedItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("revive_seed"));
	public static Item ORANIAN_BERRY = new OranianBerryItem(new Item.Properties().group(ItemGroup.MISC).food(KindredLegacyFoods.ORANIAN_BERRY), 6F).setRegistryName(UtilityFunctions.location("oranian_berry"));

	public static Item ATTACK_ORDERER = new AttackOrdererItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(UtilityFunctions.location("attack_orderer"));
	public static Item ESSENCE_RECALLER = new EssenceRecallerItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(UtilityFunctions.location("essence_recaller"));
	public static Item POKEMON_EXPLORATION_KIT = new PokemonExplorationKitItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(UtilityFunctions.location("pokemon_exploration_kit"));
	
	public static Item FEYWOOD_ABSOL_SUMMON = new FeywoodAbsolSummonItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).defaultMaxDamage(PoketamableSummonItem.maximumDamage)).setRegistryName(UtilityFunctions.location("feywood_absol_summon"));
	public static Item FIRECRACKER_LITTEN_SUMMON = new FirecrackerLittenSummonItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).defaultMaxDamage(PoketamableSummonItem.maximumDamage)).setRegistryName(UtilityFunctions.location("firecracker_litten_summon"));
	public static Item OKAMI_ESPEON_SUMMON = new OkamiEspeonSummonItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).defaultMaxDamage(PoketamableSummonItem.maximumDamage)).setRegistryName(UtilityFunctions.location("okami_espeon_summon"));
	public static Item OKAMI_SYLVEON_SUMMON = new OkamiSylveonSummonItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).defaultMaxDamage(PoketamableSummonItem.maximumDamage)).setRegistryName(UtilityFunctions.location("okami_sylveon_summon"));
	public static Item OKAMI_UMBREON_SUMMON = new OkamiUmbreonSummonItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1).defaultMaxDamage(PoketamableSummonItem.maximumDamage)).setRegistryName(UtilityFunctions.location("okami_umbreon_summon"));
	
	public static Item ATTACK_BOOST = new AttackBoostItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("attack_boost"));
	public static Item BIOGA_ESSENCE = new BiogaEssenceItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("bioga_essence"));
	public static Item CURAGA_ESSENCE = new CuragaEssenceItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("curaga_essence"));
	public static Item FIRAGA_ESSENCE = new FiragaEssenceItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("firaga_essence"));
	public static Item GRAVIGA_ESSENCE = new GravigaEssenceItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("graviga_essence"));
	public static Item LIFE_BOOST = new LifeBoostItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("life_boost"));
	public static Item QUAKAGA_ESSENCE = new QuakagaEssenceItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("quakaga_essence"));
	public static Item SPEED_BOOST = new SpeedBoostItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("speed_boost"));
	public static Item WATERGA_ESSENCE = new WatergaEssenceItem(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(UtilityFunctions.location("waterga_essence"));

	public static Item PHOENIX_HEARTHSTONE = new PhoenixHearthstoneItem(new Item.Properties().group(ItemGroup.MISC).maxStackSize(1)).setRegistryName(UtilityFunctions.location("phoenix_hearthstone"));
	
	//================ BLOCK ITEMS ================//
	public static Item PACKED_AURUM_DUST = new BlockItemBase(KindredLegacyBlocks.PACKED_AURUM_DUST, new Item.Properties().group(ItemGroup.MISC)).setRegistryName(KindredLegacyBlocks.PACKED_AURUM_DUST.getRegistryName());

	public static boolean grantBonusGear(PlayerEntity player) 
	{
		player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.POKEMON_EXPLORATION_KIT));

		return true;
	}
}