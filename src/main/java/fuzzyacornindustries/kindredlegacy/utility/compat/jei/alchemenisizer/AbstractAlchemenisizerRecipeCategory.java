package fuzzyacornindustries.kindredlegacy.utility.compat.jei.alchemenisizer;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiAlchemenisizer;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractAlchemenisizerRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> 
{
	public static final ResourceLocation TEXTURE = 
			new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/alchemenisizer_gui.png");
    
	protected static final int input1 = 0;
	protected static final int input2 = 1;
	protected static final int input3 = 2;
	protected static final int output = 3;

	protected final IDrawableAnimated animatedAlchemy;
	
	public AbstractAlchemenisizerRecipeCategory(IGuiHelper helper) 
	{
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURE, GuiAlchemenisizer.cookIconStartX, GuiAlchemenisizer.cookIconStartY, GuiAlchemenisizer.cookIconWidth, GuiAlchemenisizer.cookIconHeight);
		animatedAlchemy = helper.createAnimatedDrawable(staticArrow, 400, IDrawableAnimated.StartDirection.LEFT, false);
	}
}