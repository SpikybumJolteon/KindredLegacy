package fuzzyacornindustries.kindredlegacy.utility.compat.jei.verdantpurifier;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.recipe.VerdantPurifierRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class VerdantPurifierRecipeMaker 
{
	public static List<VerdantPurifierRecipe> getRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		VerdantPurifierRecipes instance = VerdantPurifierRecipes.getInstance();
		Map<ItemStack, ItemStack> recipes = instance.getCondensingList();
		List<VerdantPurifierRecipe> jeiRecipes = Lists.newArrayList();
		
		for(Entry<ItemStack, ItemStack> entry : recipes.entrySet())
		{
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();
			VerdantPurifierRecipe recipe = new VerdantPurifierRecipe(input, output);
			jeiRecipes.add(recipe);
		}
		
		return jeiRecipes;
	}
}