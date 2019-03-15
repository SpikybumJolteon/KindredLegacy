package fuzzyacornindustries.kindredlegacy.utility.compat.jei.verdantpurifier;

import fuzzyacornindustries.kindredlegacy.client.gui.GuiVerdantPurifier;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractVerdantPurifierRecipeCategory<T extends IRecipeWrapper> implements IRecipeCategory<T> 
{
	public static final ResourceLocation TEXTURE = 
			new ResourceLocation(ModInfo.MOD_ID + ":textures/gui/verdant_purifier_gui.png");
    
	protected static final int input = 0;
	protected static final int output = 1;

	protected final IDrawableAnimated animatedPurification;

	public AbstractVerdantPurifierRecipeCategory(IGuiHelper helper) 
	{
		IDrawableStatic staticArrow = helper.createDrawable(TEXTURE, GuiVerdantPurifier.cookIconStartX, GuiVerdantPurifier.cookIconStartY, GuiVerdantPurifier.cookIconWidth, GuiVerdantPurifier.cookIconHeight);
		animatedPurification = helper.createAnimatedDrawable(staticArrow, 400, IDrawableAnimated.StartDirection.LEFT, false);
	}
}