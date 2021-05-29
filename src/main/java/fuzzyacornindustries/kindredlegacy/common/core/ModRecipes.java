package fuzzyacornindustries.kindredlegacy.common.core;

import fuzzyacornindustries.kindredlegacy.common.recipe.VerdantPurifyingRecipe;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes 
{
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = new DeferredRegister<>(ForgeRegistries.RECIPE_SERIALIZERS, Names.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<VerdantPurifyingRecipe>> VERDANT_PURFYING
            = RECIPES.register("verdant_purifying",
            () -> new VerdantPurifyingRecipe.Serializer<>(VerdantPurifyingRecipe::new));	
}