package fuzzyacornindustries.kindredlegacy.utility.compat.jei.alchemenisizer;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.container.ContainerAlchemenisizer;
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

public class AlchemenisizerRecipeCategory extends AbstractAlchemenisizerRecipeCategory<AlchemenisizerRecipe>
{
	private final IDrawable background;
	private final String name;
	private final IDrawable icon;
	
	public static final int guiStartX = 40;
	public static final int guiStartY = 16;

	public AlchemenisizerRecipeCategory(IGuiHelper helper) 
	{
		super(helper);
		background = helper.createDrawable(TEXTURE, guiStartX, guiStartY, 100, 58);
		icon = helper.createDrawable(new ResourceLocation(ModInfo.MOD_ID + ":textures/items/alchemenisizer.png"), 0, 0, 16, 16, 16, 16);
		name = I18n.format("container.alchemenisizer");
	}

	@Override
	public IDrawable getBackground() 
	{
		return background;
	}

	@Override
	public void drawExtras(Minecraft minecraft) 
	{
		animatedAlchemy.draw(minecraft, GuiAlchemenisizer.cookIconDrawX - guiStartX - 1, GuiAlchemenisizer.cookIconDrawY - guiStartY - 1);
	}

	@Override
	public IDrawable getIcon() 
	{
		return icon;
	}

	@Override
	public String getTitle() 
	{
		return name;
	}

	@Override
	public String getUid() 
	{
		return RecipeCategories.ALCHEMENISIZER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlchemenisizerRecipe recipeWrapper, IIngredients ingredients) 
	{
		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input1, true, ContainerAlchemenisizer.netherwartSlotX - guiStartX - 1, ContainerAlchemenisizer.netherwartSlotY - guiStartY - 1);
		stacks.init(input2, true, ContainerAlchemenisizer.mainItemSlotX - guiStartX - 1, ContainerAlchemenisizer.mainItemSlotY - guiStartY - 1);
		stacks.init(input3, true, ContainerAlchemenisizer.dustSlotX - guiStartX - 1, ContainerAlchemenisizer.dustSlotY - guiStartY - 1);
		stacks.init(output, false, ContainerAlchemenisizer.productItemSlotX - guiStartX - 1, ContainerAlchemenisizer.productItemSlotY - guiStartY - 1);
		stacks.set(ingredients);
	}

	@Override
	public String getModName() 
	{
		return ModInfo.MOD_NAME;
	}
}