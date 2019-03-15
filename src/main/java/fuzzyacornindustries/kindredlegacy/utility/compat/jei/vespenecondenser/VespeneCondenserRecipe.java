package fuzzyacornindustries.kindredlegacy.utility.compat.jei.vespenecondenser;

import java.awt.Color;

import fuzzyacornindustries.kindredlegacy.recipe.VespeneCondenserRecipes;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.JEICompat;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class VespeneCondenserRecipe implements IRecipeWrapper
{
	private final ItemStack input;
	private final ItemStack output;

	public VespeneCondenserRecipe(ItemStack input, ItemStack output) 
	{
		this.input = input;
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) 
	{
		ingredients.setInput(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) 
	{
		VespeneCondenserRecipes recipes = VespeneCondenserRecipes.getInstance();
		float experience = recipes.getCondensationExperience(output);

		if(experience > 0)
		{
			String experienceString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
			FontRenderer renderer = minecraft.fontRenderer;
			int stringWidth = renderer.getStringWidth(experienceString);
			renderer.drawString(experienceString, recipeWidth - stringWidth,  0, Color.GRAY.getRGB());	
		}
	}
}