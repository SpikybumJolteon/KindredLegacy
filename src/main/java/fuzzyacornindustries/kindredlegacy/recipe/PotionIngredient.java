package fuzzyacornindustries.kindredlegacy.recipe;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;

public class PotionIngredient extends Ingredient
{
	private final ItemStack stack;
	protected PotionIngredient(ItemStack stack)
	{
		super(Stream.of(new Ingredient.SingleItemList(stack)));
		this.stack = stack;
	}

	@Override
	public boolean test(@Nullable ItemStack input)
	{
		if (input == null)
			return false;
		else if(input.getItem() == Items.POTION && this.stack.getItem() == Items.POTION)
		{
			List<EffectInstance> potionInput = PotionUtils.getEffectsFromStack(input);
			List<EffectInstance> potionIngredient = PotionUtils.getEffectsFromStack(stack);

			return potionInput.equals(potionIngredient);
		}

		return false;
	}

	@Override
	public boolean isSimple()
	{
		return false;
	}

	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer()
	{
		return Serializer.INSTANCE;
	}

	@Override
	public JsonElement serialize()
	{
		JsonObject json = new JsonObject();
		json.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
		json.addProperty("item", stack.getItem().getRegistryName().toString());
		//		if (stack.hasTag())
		//		    json.addProperty("nbt", stack.getTag().toString());
		if (stack.getItem() == Items.POTION && stack.hasTag())
		{
			//PotionUtils.addPotionToItemStack;
			//Potion potionStack = stack;

			json.addProperty("potion", stack.getTag().toString());
		}

		return json;
	}

	public static class Serializer implements IIngredientSerializer<PotionIngredient>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public PotionIngredient parse(PacketBuffer buffer) {
			return new PotionIngredient(buffer.readItemStack());
		}

		@Override
		public PotionIngredient parse(JsonObject json) {
			final String potionName = JSONUtils.getString(json, "potion");

			Potion potiontype = Potion.getPotionTypeForName(potionName);

			if (potiontype == null) 
			{
				throw new JsonSyntaxException("Unknown potion type '" + potionName + "'");
			}

			ItemStack potion = new ItemStack(Items.POTION);

			PotionUtils.addPotionToItemStack(potion, potiontype);

			if (potion.isEmpty()) 
			{
				throw new JsonSyntaxException("No potion for '" + potionName + "'");
			}

			return new PotionIngredient(potion);
		}

		@Override
		public void write(PacketBuffer buffer, PotionIngredient ingredient) {
			buffer.writeItemStack(ingredient.stack);
		}
	}
}

//public class PotionIngredient extends Ingredient 
//{
//    private final ItemStack stack;
//    
//	protected PotionIngredient(ItemStack stack) 
//	{
//        super(Stream.of(new Ingredient.SingleItemList(stack)));
//        this.stack = stack;
//    }
//
//    @Override
//    public boolean test(@Nullable ItemStack input)
//    {
//        if (input == null)
//            return false;
//        
//        //Can't use areItemStacksEqualUsingNBTShareTag because it compares stack size as well
//        return this.stack.getItem() == input.getItem() && this.stack.getDamage() == input.getDamage() && this.stack.areShareTagsEqual(input);
//    }
//
//    @Override
//    public boolean isSimple()
//    {
//        return false;
//    }
//
//    @Override
//    public IIngredientSerializer<? extends Ingredient> getSerializer()
//    {
//        return Serializer.INSTANCE;
//    }
///*
//    @Override
//    public JsonElement serialize()
//    {
//        JsonObject json = new JsonObject();
//        json.addProperty("type", CraftingHelper.getID(Serializer.INSTANCE).toString());
//        json.addProperty("item", stack.getItem().getRegistryName().toString());
//        json.addProperty("count", stack.getCount());
//        if (stack.hasTag())
//            json.addProperty("nbt", stack.getTag().toString());
//        return json;
//    }*/
//
//    public static class Serializer implements IIngredientSerializer<PotionIngredient>
//    {
//        public static final Serializer INSTANCE = new Serializer();
//
//        @Override
//        public PotionIngredient parse(PacketBuffer buffer) 
//        {
//            return new PotionIngredient(buffer.readItemStack());
//        }
//
//        @Override
//        public PotionIngredient parse(JsonObject json) 
//        {
//        	ItemStack potion = new ItemStack(Items.POTION);
//    		String potionName = null;
//
//    		if (json.has("potion"))
//    		{
//    			if (json.get("potion").isJsonPrimitive())
//    			{
//    				potionName = json.get("potion").getAsString();
//    			}
//    		}
//
//    		if (potionName != null) 
//    		{
//    			Potion potiontype = Potion.getPotionTypeForName(potionName);
//
//    			if (potiontype != null) 
//    			{
//    				PotionUtils.addPotionToItemStack(potion, potiontype);
//
//    				if (potion.isEmpty()) 
//    				{
//    					throw new JsonSyntaxException("No potion for '" + potionName + "'");
//    				}
//    			}
//    		}
//
//    		return new PotionIngredient(potion);
//    		
//            //return new PotionIngredient(CraftingHelper.getItemStack(json, true));
//        }
//
//        @Override
//        public void write(PacketBuffer buffer, PotionIngredient ingredient) 
//        {
//            buffer.writeItemStack(ingredient.stack);
//        }
//    }
///*
//	public Ingredient parse(JsonObject json) 
//	{
//		ItemStack potion = new ItemStack(Items.POTION);
//		String potionName = null;
//
//		if (json.has("potion"))
//		{
//			if (json.get("potion").isJsonPrimitive())
//			{
//				potionName = json.get("potion").getAsString();
//			}
//		}
//
//		if (potionName != null) 
//		{
//			Potion potiontype = Potion.getPotionTypeForName(potionName);
//
//			if (potiontype != null) 
//			{
//				PotionUtils.addPotionToItemStack(potion, potiontype);
//
//				if (potion.isEmpty()) 
//				{
//					throw new JsonSyntaxException("No potion for '" + potionName + "'");
//				}
//			}
//		}
//
//		return new PotionIngredient(potion);
//	}*/
//}