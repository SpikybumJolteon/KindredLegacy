package fuzzyacornindustries.kindredlegacy.utility.compat.jei.vespenecondenser;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiVespeneCondenser;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractVespeneCondenserRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> 
{
	public static final ResourceLocation TEXTURE = 
			new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/vespene_condenser_gui.png");
    
	protected static final int input = 0;
	protected static final int output = 1;

	protected final IDrawableAnimated animatedCondensation;
	
	public AbstractVespeneCondenserRecipeCategory(IGuiHelper helper) 
	{
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURE, GuiVespeneCondenser.cookIconStartX, GuiVespeneCondenser.cookIconStartY, GuiVespeneCondenser.cookIconWidth, GuiVespeneCondenser.cookIconHeight);
		animatedCondensation = helper.createAnimatedDrawable(staticArrow, 400, IDrawableAnimated.StartDirection.LEFT, false);
	}
}