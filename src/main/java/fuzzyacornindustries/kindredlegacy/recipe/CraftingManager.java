package fuzzyacornindustries.kindredlegacy.recipe;

import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingManager 
{
	/* ======= CRAFTING CONFIG ======= */
	//public static boolean isBlessingOfArceusCraftable;

	/**
	 * Initializes mod item indices from configuration file
	 */
	public static void initConfig(Configuration config) 
	{
		String category;

		/*================== ITEMS =====================*//*
		category = "crafting";
		config.addCustomCategoryComment(category,
				"\n======= Crafting Settings =======");
		isBlessingOfArceusCraftable = config.getBoolean("isBlessingOfArceusCraftable", category, false, "Blessing of Arceus craftable?");*/
	}

	public static void registerRecipes()
	{
		//addCraftingRecipes();
		addSmeltingRecipes();
	}

	public static void addCraftingRecipes()
	{
		/* *********** Biomes'O'Plenty Recipes *********** *//*
		if(KindredLegacyMain.isBiomesOPlentyEnabled)
		{
			ItemStack flowerCactus = GameRegistry.makeItemStack("biomesoplenty:plant_1", 6, 1, null);

			ItemStack bamboo = GameRegistry.makeItemStack("biomesoplenty:bamboo", 0, 1, null);
			ItemStack bambooSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_0", 2, 1, null);

			ItemStack magicWood = GameRegistry.makeItemStack("biomesoplenty:log_1", 5, 1, null);
			ItemStack magicSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_0", 3, 1, null);

			ItemStack umbranWood = GameRegistry.makeItemStack("biomesoplenty:log_0", 6, 1, null);
			ItemStack umbranSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_0", 4, 1, null);

			ItemStack firWood = GameRegistry.makeItemStack("biomesoplenty:log_0", 7, 1, null);
			ItemStack firSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_0", 6, 1, null);

			ItemStack cherryWood = GameRegistry.makeItemStack("biomesoplenty:log_0", 5, 1, null);
			ItemStack pinkCherrySapling = GameRegistry.makeItemStack("biomesoplenty:sapling_1", 1, 1, null);

			ItemStack jacarandaWood = GameRegistry.makeItemStack("biomesoplenty:log_3", 4, 1, null);
			ItemStack jacarandaSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_1", 6, 1, null);

			ItemStack mangroveWood = GameRegistry.makeItemStack("biomesoplenty:log_1", 6, 1, null);
			ItemStack mangroveSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 0, 1, null);

			ItemStack palmWood = GameRegistry.makeItemStack("biomesoplenty:log_1", 7, 1, null);
			ItemStack palmSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 1, 1, null);

			ItemStack redwoodWood = GameRegistry.makeItemStack("biomesoplenty:log_2", 4, 1, null);
			ItemStack redwoodSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 2, 1, null);

			ItemStack willowWood = GameRegistry.makeItemStack("biomesoplenty:log_2", 5, 1, null);
			ItemStack willowSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 3, 1, null);

			ItemStack pineWood = GameRegistry.makeItemStack("biomesoplenty:log_2", 6, 1, null);
			ItemStack pineSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 4, 1, null);

			ItemStack mahoganyWood = GameRegistry.makeItemStack("biomesoplenty:log_3", 5, 1, null);
			ItemStack mahoganySapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 5, 1, null);

			ItemStack ebonyWood = GameRegistry.makeItemStack("biomesoplenty:log_3", 6, 1, null);
			ItemStack ebonySapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 6, 1, null);

			ItemStack eucalyptusWood = GameRegistry.makeItemStack("biomesoplenty:log_3", 7, 1, null);
			ItemStack eucalyptusSapling = GameRegistry.makeItemStack("biomesoplenty:sapling_2", 7, 1, null);

			if(flowerCactus != null)
			{
				GameRegistry.addShapelessRecipe(new ItemStack(KindredLegacyItems.sahra_specialty, 1), new Object[]{flowerCactus, Items.BOWL, KindredLegacyItems.oranian_berry});

				GameRegistry.addShapelessRecipe(new ItemStack(KindredLegacyItems.verdant_powder, 1), new Object[]{KindredLegacyItems.verdantizer, flowerCactus});
			}

			if(bambooSapling != null && bamboo != null)
			{
				GameRegistry.addShapelessRecipe(bambooSapling, new Object[]{KindredLegacyItems.verdant_powder, bamboo});
			}

			if(magicSapling != null && magicWood != null)
			{
				GameRegistry.addShapelessRecipe(magicSapling, new Object[]{KindredLegacyItems.verdant_powder, magicWood});
			}

			if(umbranSapling != null && umbranWood != null)
			{
				GameRegistry.addShapelessRecipe(umbranSapling, new Object[]{KindredLegacyItems.verdant_powder, umbranWood});
			}

			if(firSapling != null && firWood != null)
			{
				GameRegistry.addShapelessRecipe(firSapling, new Object[]{KindredLegacyItems.verdant_powder, firWood});
			}

			if(pinkCherrySapling != null && cherryWood != null)
			{
				GameRegistry.addShapelessRecipe(pinkCherrySapling, new Object[]{KindredLegacyItems.verdant_powder, cherryWood});
			}

			if(jacarandaSapling != null && jacarandaWood != null)
			{
				GameRegistry.addShapelessRecipe(jacarandaSapling, new Object[]{KindredLegacyItems.verdant_powder, jacarandaWood});
			}

			if(mangroveSapling != null && mangroveWood != null)
			{
				GameRegistry.addShapelessRecipe(mangroveSapling, new Object[]{KindredLegacyItems.verdant_powder, mangroveWood});
			}

			if(palmSapling != null && palmWood != null)
			{
				GameRegistry.addShapelessRecipe(palmSapling, new Object[]{KindredLegacyItems.verdant_powder, palmWood});
			}

			if(redwoodSapling != null && redwoodWood != null)
			{
				GameRegistry.addShapelessRecipe(redwoodSapling, new Object[]{KindredLegacyItems.verdant_powder, redwoodWood});
			}

			if(willowSapling != null && willowWood != null)
			{
				GameRegistry.addShapelessRecipe(willowSapling, new Object[]{KindredLegacyItems.verdant_powder, willowWood});
			}

			if(pineSapling != null && pineWood != null)
			{
				GameRegistry.addShapelessRecipe(pineSapling, new Object[]{KindredLegacyItems.verdant_powder, pineWood});
			}

			if(mahoganySapling != null && mahoganyWood != null)
			{
				GameRegistry.addShapelessRecipe(mahoganySapling, new Object[]{KindredLegacyItems.verdant_powder, mahoganyWood});
			}

			if(ebonySapling != null && ebonyWood != null)
			{
				GameRegistry.addShapelessRecipe(ebonySapling, new Object[]{KindredLegacyItems.verdant_powder, ebonyWood});
			}

			if(eucalyptusSapling != null && eucalyptusWood != null)
			{
				GameRegistry.addShapelessRecipe(eucalyptusSapling, new Object[]{KindredLegacyItems.verdant_powder, eucalyptusWood});
			}
		}*/
	}

	public static void addSmeltingRecipes()
	{
		GameRegistry.addSmelting(KindredLegacyItems.AURUM_DUST, new ItemStack(Items.GOLD_NUGGET), 0.2F);
		GameRegistry.addSmelting(KindredLegacyBlocks.PACKED_AURUM_DUST, new ItemStack(Items.GOLD_INGOT), 1.0F);

		GameRegistry.addSmelting(KindredLegacyItems.HUGE_SQUID_TENTACLE, new ItemStack(KindredLegacyItems.CRISPY_SQUID_TENTACLE), 0.2F);
	}
}