package fuzzyacornindustries.kindredlegacy.common.handler;

import java.io.File;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import fuzzyacornindustries.kindredlegacy.common.core.ModEntities;
import fuzzyacornindustries.kindredlegacy.lib.Log;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ConfigHandler 
{
	private static final ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec SERVER_CONFIG;
	
	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT_CONFIG;

	static
	{
		ModEntities.initConfig(SERVER_BUILDER, CLIENT_BUILDER);
		
		SERVER_CONFIG = SERVER_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}

	public static void loadConfig(ForgeConfigSpec config, String path)
	{
		Log.info("Loading Config " + path);
		
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();

		Log.info("Built config " + path);
		
		file.load();

		Log.info("Loaded config " + path);
		
		config.setConfig(file);
		}
/*
	public static void preInit(FMLPreInitializationEvent event) 
	{
		config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + ModInfo.CONFIG_PATH));
		config.load();

		KindredLegacyEntities.initConfig(config);
		//CraftingManager.initConfig(config);

		//config = new Configuration(file);
		config.save();
	}

	///make Blessing of Arceus more likely to drop on full moons
	 * */
}