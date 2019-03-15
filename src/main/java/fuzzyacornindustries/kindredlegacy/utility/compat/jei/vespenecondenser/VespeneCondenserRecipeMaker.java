package fuzzyacornindustries.kindredlegacy.utility.compat.jei.vespenecondenser;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.recipe.VespeneCondenserRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class VespeneCondenserRecipeMaker 
{
	public static List<VespeneCondenserRecipe> getRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		VespeneCondenserRecipes instance = VespeneCondenserRecipes.getInstance();
		Map<ItemStack, ItemStack> recipes = instance.getCondensingList();
		List<VespeneCondenserRecipe> jeiRecipes = Lists.newArrayList();
		
		for(Entry<ItemStack, ItemStack> entry : recipes.entrySet())
		{
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();
			VespeneCondenserRecipe recipe = new VespeneCondenserRecipe(input, output);
			jeiRecipes.add(recipe);
		}
		
		return jeiRecipes;
	}
}