package fuzzyacornindustries.kindredlegacy.item;

import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class KindredLegacyLootTables 
{
	public static final ResourceLocation ARMORED_LUXRAY_LOOT_TABLE = register("armored_luxray");
	public static final ResourceLocation ARMORED_SHINX_LOOT_TABLE = register("armored_shinx");
	public static final ResourceLocation BANDERSNATCH_FENNEKIN_LOOT_TABLE = register("bandersnatch_fennekin");
	public static final ResourceLocation CLAY_COMMANDER_DELCATTY_LOOT_TABLE = register("clay_commander_delcatty");
	public static final ResourceLocation CLAY_ESPURR_LOOT_TABLE = register("clay_espurr");
	public static final ResourceLocation CLAY_PURRLOIN_LOOT_TABLE = register("clay_purrloin");
	public static final ResourceLocation CLAY_SHINX_LOOT_TABLE = register("clay_shinx");
	public static final ResourceLocation CLAY_SKITTY_LOOT_TABLE = register("clay_skitty");
	public static final ResourceLocation CRACKLING_NINCADA_LOOT_TABLE = register("crackling_nincada");
	public static final ResourceLocation DEMON_VULPIX_LOOT_TABLE = register("demon_vulpix");
	public static final ResourceLocation FORCEWIND_EELEKTRIK_LOOT_TABLE = register("forcewind_eelektrik");
	public static final ResourceLocation INFESTED_DEERLING_LOOT_TABLE = register("infested_deerling");
	public static final ResourceLocation MU_BUNEARY_LOOT_TABLE = register("mu_buneary");
	public static final ResourceLocation RAPTOR_ZERGLING_NINCADA_LOOT_TABLE = register("raptor_zergling_nincada");
	public static final ResourceLocation SNOW_SORCERESS_BRAIXEN_LOOT_TABLE = register("snow_sorceress_braixen");
	public static final ResourceLocation SWORDIE_MIENSHAO_LOOT_TABLE = register("swordie_mienshao");
	public static final ResourceLocation VOORST_MIGHTYENA_LOOT_TABLE = register("voorst_mightyena");
	public static final ResourceLocation ZERGLING_NINCADA_LOOT_TABLE = register("zergling_nincada");

	private static ResourceLocation register(String id) 
	{
		return LootTableList.register(new ResourceLocation(ModInfo.MOD_ID, id));
	}
}