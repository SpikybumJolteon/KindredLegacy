package fuzzyacornindustries.kindredlegacy.recipe.ingredient;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class PotionIngredientFactory implements IIngredientFactory 
{
	@Override
	public Ingredient parse(final JsonContext context, final JsonObject json) 
	{
		final String potionName = JsonUtils.getString(json, "potion");

        PotionType potiontype = PotionType.REGISTRY.getObject(new ResourceLocation(potionName));

		if (potiontype == null) 
		{
			throw new JsonSyntaxException("Unknown potion type '" + potionName + "'");
		}
		
		ItemStack potion = new ItemStack(Items.POTIONITEM);

		PotionUtils.addPotionToItemStack(potion, potiontype);

		if (potion.isEmpty()) 
		{
			throw new JsonSyntaxException("No potion for '" + potionName + "'");
		}

		return new IngredientNBTKindredLegacy(potion);
	}
}