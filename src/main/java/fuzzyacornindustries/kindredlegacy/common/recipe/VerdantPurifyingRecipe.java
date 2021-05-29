package fuzzyacornindustries.kindredlegacy.common.recipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fuzzyacornindustries.kindredlegacy.common.core.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class VerdantPurifyingRecipe extends ModRecipe 
{
    private final Ingredient input;
    private final ItemStack output;
    private final Integer duration;

    public VerdantPurifyingRecipe(ResourceLocation id, @Nonnull Ingredient input, @Nonnull ItemStack output, @Nonnull Integer duration) 
    {
        super(id);

        this.input = input;
        this.output = output;
        this.duration = duration;
    }

    public Ingredient getInput() 
    {
        return input;
    }

    public int getInputAmount() 
    {
        return input.getMatchingStacks().length > 0 ? input.getMatchingStacks()[0].getCount() : 0;
    }

    public ItemStack getOutput() 
    {
        return output;
    }
    
    public Integer getDuration() 
    {
        return duration;
    }

    public boolean matches(ItemStack stack) 
    {
        return input.test(stack) && stack.getCount() >= getInputAmount();
    }

    @Override
    public void write(PacketBuffer buffer) 
    {
        input.write(buffer);
        buffer.writeItemStack(output);
        buffer.writeInt(duration);
    }

    @Override
    public IRecipeSerializer<?> getSerializer() 
    {
        return ModRecipes.VERDANT_PURFYING.get();
    }

    @Override
    public IRecipeType<?> getType() 
    {
    	return ModRecipeType.VERDANT_PURIFYING;
    }

    public static class Serializer<T extends VerdantPurifyingRecipe> extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
        private final IFactory<T> factory;

        public Serializer(IFactory<T> factory) {
            this.factory = factory;
        }

        @Override
        public T read(ResourceLocation recipeId, JsonObject json) {
            Ingredient input = Ingredient.deserialize(json.get("input"));
            ItemStack result = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            Integer duration = json.get("duration").getAsInt();
            try {
                return factory.create(recipeId, input, result, duration);
            } catch (IllegalArgumentException e) {
                throw new JsonParseException(e.getMessage());
            }
        }

        @Nullable
        @Override
        public T read(ResourceLocation recipeId, PacketBuffer buffer) 
        {
            Ingredient input = Ingredient.read(buffer);
            ItemStack out = buffer.readItemStack();
            Integer duration = buffer.readInt();
            return factory.create(recipeId, input, out, duration);
        }

        @Override
        public void write(PacketBuffer buffer, T recipe) 
        {
            recipe.write(buffer);
        }

        public interface IFactory<T extends VerdantPurifyingRecipe> 
        {
            T create(ResourceLocation id, @Nonnull Ingredient input, @Nonnull ItemStack output, @Nonnull Integer duration);
        }
    }
}