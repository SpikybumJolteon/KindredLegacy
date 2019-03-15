package fuzzyacornindustries.kindredlegacy.utility.compat.jei.alchemenisizer;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;

import fuzzyacornindustries.kindredlegacy.recipe.AlchemenisizerRecipes;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AlchemenisizerRecipeMaker 
{
	public static List<AlchemenisizerRecipe> getRecipes(IJeiHelpers helpers)
	{
		IStackHelper stackHelper = helpers.getStackHelper();
		AlchemenisizerRecipes instance = AlchemenisizerRecipes.getInstance();
		Map<ItemStack[], ItemStack> recipes = instance.getAlchemyList();
		List<AlchemenisizerRecipe> jeiRecipes = Lists.newArrayList();
		
		for(Entry<ItemStack[], ItemStack> entry : recipes.entrySet())
		{
			ItemStack input1 = entry.getKey()[0];
			ItemStack input2 = entry.getKey()[1];
			ItemStack input3 = entry.getKey()[2];
			ItemStack output = entry.getValue();
			List<ItemStack> inputs = Lists.newArrayList(input1, input2, input3);
			AlchemenisizerRecipe recipe = new AlchemenisizerRecipe(inputs, output);
			jeiRecipes.add(recipe);
		}
		
		return jeiRecipes;
	}
}