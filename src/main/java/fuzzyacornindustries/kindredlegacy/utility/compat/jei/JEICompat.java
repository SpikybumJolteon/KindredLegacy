package fuzzyacornindustries.kindredlegacy.utility.compat.jei;

import java.util.IllegalFormatException;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.client.gui.GuiVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.client.gui.GuiVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.container.ContainerAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.container.ContainerVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.container.ContainerVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.alchemenisizer.AlchemenisizerRecipeCategory;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.alchemenisizer.AlchemenisizerRecipeMaker;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.verdantpurifier.VerdantPurifierRecipeCategory;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.verdantpurifier.VerdantPurifierRecipeMaker;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.vespenecondenser.VespeneCondenserRecipeCategory;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.vespenecondenser.VespeneCondenserRecipeMaker;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin
{
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new VespeneCondenserRecipeCategory(gui));
		registry.addRecipeCategories(new AlchemenisizerRecipeCategory(gui));
		registry.addRecipeCategories(new VerdantPurifierRecipeCategory(gui));
	}
	
	@Override
	public void register(IModRegistry registry) 
	{
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipes(VespeneCondenserRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.VESPENE_CONDENSER);
		registry.addRecipeClickArea(GuiVespeneCondenser.class, GuiVespeneCondenser.cookIconDrawX, GuiVespeneCondenser.cookIconDrawY, GuiVespeneCondenser.cookIconWidth, GuiVespeneCondenser.cookIconHeight, RecipeCategories.VESPENE_CONDENSER);
		recipeTransfer.addRecipeTransferHandler(ContainerVespeneCondenser.class, RecipeCategories.VESPENE_CONDENSER, 0, 1, 2, 36);
		
		registry.addRecipes(AlchemenisizerRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.ALCHEMENISIZER);
		registry.addRecipeClickArea(GuiAlchemenisizer.class, GuiAlchemenisizer.cookIconDrawX, GuiAlchemenisizer.cookIconDrawY, GuiAlchemenisizer.cookIconWidth, GuiAlchemenisizer.cookIconHeight, RecipeCategories.ALCHEMENISIZER);
		recipeTransfer.addRecipeTransferHandler(ContainerAlchemenisizer.class, RecipeCategories.ALCHEMENISIZER, 0, 2, 4, 36);

		registry.addRecipes(VerdantPurifierRecipeMaker.getRecipes(jeiHelpers), RecipeCategories.VERDANT_PURIFIER);
		registry.addRecipeClickArea(GuiVerdantPurifier.class, GuiVerdantPurifier.cookIconDrawX, GuiVerdantPurifier.cookIconDrawY, GuiVerdantPurifier.cookIconWidth, GuiVerdantPurifier.cookIconHeight, RecipeCategories.VERDANT_PURIFIER);
		recipeTransfer.addRecipeTransferHandler(ContainerVerdantPurifier.class, RecipeCategories.VERDANT_PURIFIER, 0, 1, 2, 36);
	}

	public static String translateToLocal(String key)
	{
		if(I18n.canTranslate(key)) return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object... format)
	{
		String s = translateToLocal(key);
		try
		{
			return String.format(s, format);
		}catch(IllegalFormatException e)
		{
			return "Format error: " + s;
		}
	}
}