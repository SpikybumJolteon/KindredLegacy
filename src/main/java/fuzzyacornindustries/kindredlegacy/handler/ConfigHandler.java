package fuzzyacornindustries.kindredlegacy.handler;

import java.io.File;

import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.recipe.CraftingManager;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler 
{
	public static Configuration config;

	public static void preInit(FMLPreInitializationEvent event) 
	{
		config = new Configuration(new File(event.getModConfigurationDirectory().getAbsolutePath() + ModInfo.CONFIG_PATH));
		config.load();

		KindredLegacyEntities.initConfig(config);
		CraftingManager.initConfig(config);
		
		//config = new Configuration(file);
		config.save();
	}

	///make Blessing of Arceus more likely to drop on full moons
}