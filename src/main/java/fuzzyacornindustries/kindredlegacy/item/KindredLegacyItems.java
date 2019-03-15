package fuzzyacornindustries.kindredlegacy.item;

import java.util.ArrayList;
import java.util.List;

import fuzzyacornindustries.kindredlegacy.item.tamable.ItemAttackBoost;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemAttackOrderer;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemBiogaEssence;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemCometEssence;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemCuragaEssence;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemEssenceRecaller;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFeywoodAbsolSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFiragaEssence;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFirecrackerLittenSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFoxcraftFennekinSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFoxfireZoruaSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemGravigaEssence;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemImmortalArcanineSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemLifeBoost;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiEspeonSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiSylveonSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiUmbreonSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemPokemonExplorationKit;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemQuakagaEssence;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemSpeedBoost;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemVastayaNinetalesSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemWatergaEssence;
import fuzzyacornindustries.kindredlegacy.item.tools.ItemBiosteelAxe;
import fuzzyacornindustries.kindredlegacy.item.tools.ItemBiosteelHoe;
import fuzzyacornindustries.kindredlegacy.item.tools.ItemBiosteelPickaxe;
import fuzzyacornindustries.kindredlegacy.item.tools.ItemBiosteelShovel;
import fuzzyacornindustries.kindredlegacy.item.tools.ItemBiosteelSword;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class KindredLegacyItems
{
	public static final ArmorMaterial SILKSCREEN_RIBBON_MATERIAL = EnumHelper.addArmorMaterial("SilkscreenRibbonArmor", "kindredlegacy:silkscreen_ribbon", 5, new int[] {1,3,2,1}, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

	public static final List<Item> ITEMS = new ArrayList<Item>();

	//================ MATERIAL ================//
	public static final ToolMaterial BIOSTEEL = EnumHelper.addToolMaterial("regenerative_biosteel", 2, 250, 6.0F, 2.0F, 14);
	
	//================ MATERIALS ITEMS ================//
	public static final Item AURUM_DUST = new ItemBase("aurum_dust");
	public static final Item AURUM_ROD = new ItemBase("aurum_rod");
	public static final Item CHARGESTONE = new ItemBase("chargestone");
	public static final Item EMERALD_OF_CHAOS = new ItemEmeraldOfChaos("emerald_of_chaos");
	public static final Item HUNTERS_CHARGE = new ItemBase("hunters_charge");
	public static final Item HUNTERS_CHARM = new ItemBase("hunters_charm");
	public static final Item INFERNO_FUEL_ROD = new ItemBase("inferno_fuel_rod");
	public static final Item SILKSCREEN_MESH = new ItemBase("silkscreen_mesh");
	public static final Item RAW_SPICE_MELANGE = new ItemRawSpiceMelange("raw_spice_melange");
	public static final Item REGEN_CREAM = new ItemRegenCream("regen_cream");
	public static final Item TELEPORT_BARREL = new ItemBase("teleport_barrel");
	public static final Item VERDANT_CHARGE = new ItemBase("verdant_charge");
	public static final Item VERDANT_GOO = new ItemBase("verdant_goo");
	public static final Item VERDANT_POWDER = new ItemBase("verdant_powder");
	public static final Item VERDANTIZER = new ItemVerdantizer("verdantizer");
	public static final Item VESPENE_CRYSTAL = new ItemBase("vespene_crystal");
	public static final Item VESPENE_FUEL_ROD = new ItemBase("vespene_fuel_rod");
	public static final Item VOID_SCISSORS = new ItemVoidScissors("void_scissors");
	public static final Item WARP_TECH = new ItemBase("warp_tech");
	public static final Item WILL_OF_THE_ANCIENTS = new ItemBase("will_of_the_ancients").setMaxStackSize(1);
	public static final Item XELNAGA_CIRCUIT = new ItemBase("xelnaga_circuit");
	public static final Item XELNAGA_DYNAMO = new ItemBase("xelnaga_dynamo");
	public static final Item XELNAGA_SHARD = new ItemBase("xelnaga_shard");

	public static final Item TIBERIUM_SHARD = new ItemTiberiumShard("tiberium_shard");
	public static final Item RICH_TIBERIUM_SHARD = new ItemRichTiberiumShard("rich_tiberium_shard");
	public static final Item NEO_TIBERIUM_SHARD = new ItemNeoTiberiumShard("neo_tiberium_shard");
	public static final Item TIBERIUM_CASING = new ItemBase("tiberium_casing");

	public static final Item POKEZERG_SAMPLE = new ItemPokezergSample("pokezerg_sample");
	public static final Item REGENERATIVE_BIOSTEEL = new ItemRegenerativeBiosteel("regenerative_biosteel");

	public static final Item FIRE_RESISTANCE_POWDER = new ItemPotionPowder("fire_resistance_powder", "Fire Resistance Extended");
	public static final Item HEALING_POWDER = new ItemPotionPowder("healing_powder", "Healing II");
	public static final Item INVISIBILITY_POWDER = new ItemPotionPowder("invisibility_powder", "Invisibility Extended");
	public static final Item LEAPING_POWDER = new ItemPotionPowder("leaping_powder", "Leaping II");
	public static final Item POISON_POWDER = new ItemPotionPowder("poison_powder", "Poison II");
	public static final Item REGENERATION_POWDER = new ItemPotionPowder("regeneration_powder", "Regeneration II");
	public static final Item STRENGTH_POWDER = new ItemPotionPowder("strength_powder", "Strength II");
	public static final Item SWIFTNESS_POWDER = new ItemPotionPowder("swiftness_powder", "Swiftness II");
	public static final Item WATER_BREATHING_POWDER = new ItemPotionPowder("water_breathing_powder", "Water Breathing Extended");

	//================ FOODS ITEMS ================//
	public static final Item CRISPY_SQUID_TENTACLE = new ItemCrispySquidTentacle("crispy_squid_tentacle");
	public static final Item HUGE_SQUID_TENTACLE = new ItemHugeSquidTentacle("huge_squid_tentacle");
	public static final Item SAHRA_SPECIALTY = new ItemSahraSpecialty("sahra_specialty");

	//================ EQUIPMENT/TOOLS ITEMS ================//
	public static final Item BOW_OF_KINDRED = new ItemBowOfKindred("bow_of_kindred");
	public static final Item SILKSCREEN_RIBBON = new ItemSilkscreenRibbon("silkscreen_ribbon");

	public static final Item BIOSTEEL_AXE = new ItemBiosteelAxe("biosteel_axe", BIOSTEEL, 8.0F, -3.1F);
	public static final Item BIOSTEEL_HOE = new ItemBiosteelHoe("biosteel_hoe", BIOSTEEL);
	public static final Item BIOSTEEL_PICKAXE = new ItemBiosteelPickaxe("biosteel_pickaxe", BIOSTEEL);
	public static final Item BIOSTEEL_SHOVEL = new ItemBiosteelShovel("biosteel_shovel", BIOSTEEL);
	public static final Item BIOSTEEL_SWORD = new ItemBiosteelSword("biosteel_sword", BIOSTEEL);
	
	//================ TAMABLES ITEMS ================//
	public static final Item FEYWOOD_ABSOL_SUMMON = new ItemFeywoodAbsolSummon();
	public static final Item FIRECRACKER_LITTEN_SUMMON = new ItemFirecrackerLittenSummon();
	public static final Item FOXCRAFT_FENNEKIN_SUMMON = new ItemFoxcraftFennekinSummon();
	public static final Item FOXFIRE_ZORUA_SUMMON = new ItemFoxfireZoruaSummon();
	public static final Item IMMORTAL_ARCANINE_SUMMON = new ItemImmortalArcanineSummon();
	public static final Item OKAMI_ESPEON_SUMMON = new ItemOkamiEspeonSummon();
	public static final Item OKAMI_SYLVEON_SUMMON = new ItemOkamiSylveonSummon();
	public static final Item OKAMI_UMBREON_SUMMON = new ItemOkamiUmbreonSummon();
	public static final Item VASTAYA_NINETALES_SUMMON = new ItemVastayaNinetalesSummon();

	public static final Item POKEMON_EXPLORATION_KIT = new ItemPokemonExplorationKit("pokemon_exploration_kit");

	public static final Item ESSENCE_RECALLER = new ItemEssenceRecaller("essence_recaller");
	public static final Item ATTACK_ORDERER = new ItemAttackOrderer("attack_orderer");

	public static final Item BLESSING_OF_ARCEUS = new ItemBlessingOfArceus("blessing_of_arceus");

	public static final Item ORANIAN_BERRY = new ItemOranianBerry("oranian_berry", 1, 0.1F);
	public static final Item PECHITA_BERRY = new ItemPechitaBerry("pechita_berry", 1, 0.1F);
	public static final Item REVIVE_SEED = new ItemReviveSeed("revive_seed");

	public static final Item ATTACK_BOOST = new ItemAttackBoost("attack_boost");
	public static final Item BIOGA_ESSENCE = new ItemBiogaEssence("bioga_essence");
	public static final Item CURAGA_ESSENCE = new ItemCuragaEssence("curaga_essence");
	public static final Item FIRAGA_ESSENCE = new ItemFiragaEssence("firaga_essence");
	public static final Item GRAVIGA_ESSENCE = new ItemGravigaEssence("graviga_essence");
	public static final Item LIFE_BOOST = new ItemLifeBoost("life_boost");
	public static final Item QUAKAGA_ESSENCE = new ItemQuakagaEssence("quakaga_essence");
	public static final Item SPEED_BOOST = new ItemSpeedBoost("speed_boost");
	public static final Item WATERGA_ESSENCE = new ItemWatergaEssence("waterga_essence");

	public static final Item COMET_ESSENCE = new ItemCometEssence("comet_essence");

	public static final Item POKERASER = new ItemPokeraser("pokeraser");
	public static final Item SUBSTITUTE_DOLL = new ItemSubstituteDoll("substitute_doll");

	@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
	public static class ItemRegistrationHandler 
	{
		@SubscribeEvent
		public static void onItemRegister(RegistryEvent.Register<Item> event)
		{	
			event.getRegistry().registerAll(KindredLegacyItems.ITEMS.toArray(new Item[0]));
		}
	}

	public static boolean grantBonusGear(EntityPlayer player) 
	{
		player.inventory.addItemStackToInventory(new ItemStack(KindredLegacyItems.POKEMON_EXPLORATION_KIT));

		return true;
	}
}