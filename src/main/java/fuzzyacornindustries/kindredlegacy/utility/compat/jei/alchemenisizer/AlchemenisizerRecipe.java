package fuzzyacornindustries.kindredlegacy.utility.compat.jei.alchemenisizer;

import java.awt.Color;
import java.util.List;

import fuzzyacornindustries.kindredlegacy.recipe.AlchemenisizerRecipes;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.JEICompat;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class AlchemenisizerRecipe implements IRecipeWrapper
{
	private final List<ItemStack> input;
	private final ItemStack output;

	public AlchemenisizerRecipe(List<ItemStack> input, ItemStack output) 
	{
		this.input = input;
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) 
	{
		ingredients.setInputs(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) 
	{
		AlchemenisizerRecipes recipes = AlchemenisizerRecipes.getInstance();
		float experience = recipes.getAlchemyExperience(output);

		if(experience > 0)
		{
			String experienceString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
			FontRenderer renderer = minecraft.fontRenderer;
			int stringWidth = renderer.getStringWidth(experienceString);
			renderer.drawString(experienceString, recipeWidth - stringWidth,  0, Color.GRAY.getRGB());	
		}
	}
}