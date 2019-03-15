package fuzzyacornindustries.kindredlegacy.utility.compat.jei.verdantpurifier;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.container.ContainerVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.tileentity.TileEntityVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.utility.compat.jei.RecipeCategories;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;

public class VerdantPurifierRecipeCategory extends AbstractVerdantPurifierRecipeCategory<VerdantPurifierRecipe>
{
	private final IDrawable background;
	private final String name;
	private final IDrawable icon;
	
	public static final int guiStartX = 40;
	public static final int guiStartY = 20;

	public VerdantPurifierRecipeCategory(IGuiHelper helper) 
	{
		super(helper);
		background = helper.createDrawable(TEXTURE, guiStartX, guiStartY, 100, 50);
		icon = helper.createDrawable(new ResourceLocation(ModInfo.MOD_ID + ":textures/items/verdant_purifier.png"), 0, 0, 16, 16, 16, 16);
		name = I18n.format("container.verdant_purifier");
	}

	@Override
	public IDrawable getBackground() 
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) 
	{
		animatedPurification.draw(minecraft, GuiVerdantPurifier.cookIconDrawX - guiStartX - 1, GuiVerdantPurifier.cookIconDrawY - guiStartY - 1);
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
		return RecipeCategories.VERDANT_PURIFIER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, VerdantPurifierRecipe recipeWrapper, IIngredients ingredients) 
	{
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input, true, ContainerVerdantPurifier.smeltItemSlotX - guiStartX - 1, ContainerVerdantPurifier.smeltItemSlotY - guiStartY - 1);
		stacks.init(output, false, ContainerVerdantPurifier.productItemSlotX - guiStartX - 1, ContainerVerdantPurifier.productItemSlotY - guiStartY - 1);
		stacks.set(ingredients);
	}

	@Override
	public String getModName() 
	{
		return ModInfo.MOD_NAME;
	}
}