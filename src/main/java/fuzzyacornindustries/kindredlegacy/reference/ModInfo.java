package fuzzyacornindustries.kindredlegacy.reference;

import fuzzyacornindustries.kindredlegacy.creativetab.KindredLegacyCreativeTabs;
import net.minecraft.item.Item;

public class ModInfo 
{
	public static final String MOD_ID = "kindredlegacy";
	public static final String MOD_NAME = "Kindred Legacy";
	public static final String MOD_VERSION = "1.12.2-Dev-2.6";//"pre-1.2_1";//"Dev-2.2";
	
	public static final String CHANNEL = "kindredlegacy_chan";
	public static final String CHANNEL2 = "kindredlegacy_chan2";
	public static final String CLIENT_PROXY = "fuzzyacornindustries.kindredlegacy.ClientProxy";
	public static final String COMMON_PROXY = "fuzzyacornindustries.kindredlegacy.CommonProxy";
	public static final String CONFIG_PATH = ("/" + MOD_ID + ".cfg");

	public static enum KindredLegacyBlock
	{
		GUARIDAN_FIELD("guardian_field", "guardian_field"),
		ORANIAN_BERRY("oranian_berry_plant", "oranian_berry_plant"),
		PACKED_AURUM_DUST("packed_aurum_dust", "packed_aurum_dust"),
		PECHITA_BERRY("pechita_berry_plant", "pechita_berry_plant"),
		SILKSCREEN_BLOCK("silkscreen_block", "silkscreen_block");
		
		private String unlocalizedName;
		private String registryName;
		
		KindredLegacyBlock(String unlocalizedName, String registryName)
		{
			this.unlocalizedName = unlocalizedName;
			this.registryName = registryName;
		}
		
		public String getUnlocalizedName() 
		{
			return unlocalizedName;
		}
		
		public String getRegistryName() 
		{
			return registryName;
		}
	}
}