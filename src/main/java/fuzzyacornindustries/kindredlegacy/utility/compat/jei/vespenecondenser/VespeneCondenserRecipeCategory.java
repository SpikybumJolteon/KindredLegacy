package fuzzyacornindustries.kindredlegacy.utility.compat.jei.vespenecondenser;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.container.ContainerVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class VespeneCondenserRecipeCategory extends AbstractVespeneCondenserRecipeCategory<VespeneCondenserRecipe>
{
	private final IDrawable background;
	private final String name;
	private final IDrawable icon;
	
	public static final int guiStartX = 40;
	public static final int guiStartY = 20;

	public VespeneCondenserRecipeCategory(IGuiHelper helper) 
	{
		super(helper);
		background = helper.createDrawable(TEXTURE, guiStartX, guiStartY, 100, 50);
		icon = helper.createDrawable(new ResourceLocation(ModInfo.MOD_ID + ":textures/items/vespene_condenser.png"), 0, 0, 16, 16, 16, 16);
		name = I18n.format("container.vespene_condenser");
	}

	@Override
	public IDrawable getBackground() 
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) 
	{
		animatedCondensation.draw(minecraft, GuiVespeneCondenser.cookIconDrawX - guiStartX - 1, GuiVespeneCondenser.cookIconDrawY - guiStartY - 1);
	}

	@Override
	public String getTitle() 
	{
		return name;
	}

	@Override
	public IDrawable getIcon() 
	{
		return icon;
	}

	@Override
	public String getUid() 
	{
		return RecipeCategories.VESPENE_CONDENSER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, VespeneCondenserRecipe recipeWrapper, IIngredients ingredients) 
	{
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input, true, ContainerVespeneCondenser.smeltItemSlotX - guiStartX - 1, ContainerVespeneCondenser.smeltItemSlotY - guiStartY - 1);
		stacks.init(output, false, ContainerVespeneCondenser.productItemSlotX - guiStartX - 1, ContainerVespeneCondenser.productItemSlotY - guiStartY - 1);
		stacks.set(ingredients);
	}

	@Override
	public String getModName() 
	{
		return ModInfo.MOD_NAME;
	}
}