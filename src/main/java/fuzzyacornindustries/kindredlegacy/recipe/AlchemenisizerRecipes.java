package fuzzyacornindustries.kindredlegacy.recipe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class AlchemenisizerRecipes 
{	
	private static final AlchemenisizerRecipes INSTANCE = new AlchemenisizerRecipes();
	//Table<ItemStack, ItemStack, ItemStack> ingrediantsList = HashBasedTable.<ItemStack, ItemStack, ItemStack>create();
	private final Map<ItemStack[], ItemStack> alchemyList = Maps.<ItemStack[], ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, Integer> durationList = Maps.<ItemStack, Integer>newHashMap();

	public static AlchemenisizerRecipes getInstance()
	{
		return INSTANCE;
	}

	private AlchemenisizerRecipes() 
	{
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.SPECKLED_MELON), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.HEALING_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.SPIDER_EYE), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.POISON_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.MAGMA_CREAM), new ItemStack(Items.REDSTONE), new ItemStack(KindredLegacyItems.FIRE_RESISTANCE_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.GOLDEN_CARROT), new ItemStack(Items.REDSTONE), new ItemStack(KindredLegacyItems.INVISIBILITY_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.RABBIT_FOOT), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.LEAPING_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.GHAST_TEAR), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.REGENERATION_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.BLAZE_POWDER), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.STRENGTH_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.SUGAR), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.SWIFTNESS_POWDER, 3), 1000, 0.2F);
		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(Items.FISH, 1, 3), new ItemStack(Items.REDSTONE), new ItemStack(KindredLegacyItems.WATER_BREATHING_POWDER, 3), 1000, 0.2F);

		addAlchemyRecipe(new ItemStack(Items.NETHER_WART), new ItemStack(KindredLegacyItems.POKEZERG_SAMPLE), new ItemStack(Items.GLOWSTONE_DUST), new ItemStack(KindredLegacyItems.REGENERATION_POWDER, 6), 1000, 0.4F);
	}

	public void addAlchemyRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack result, int duration, float experience) 
	{
		ItemStack ingrediantsList[] = new ItemStack[] {input1, input2, input3};

		//if(getAlchemyResult(ingrediantsList) != ItemStack.EMPTY) return;

		this.alchemyList.put(ingrediantsList, result);
		this.experienceList.put(input2, Float.valueOf(experience));
		this.durationList.put(input2, duration);
	}

	public ItemStack getAlchemyResult(ItemStack[] ingrediantsList)
	{	
		for (Entry<ItemStack[], ItemStack> entry : this.alchemyList.entrySet())
		{
			if(compareIngrediantArrays(ingrediantsList, entry.getKey()))
			{
				return (ItemStack)entry.getValue();
			}
		}

		return ItemStack.EMPTY;
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	private boolean compareIngrediantArrays(ItemStack[] array1, ItemStack[] array2)
	{
		for(int i = 0; i < array1.length; i++)
		{
			if(!compareItemStacks(array1[i], array2[i]))
				return false;
		}

		return true;
	}
	
	public boolean itemValidForInputSlot(int slotIndex, ItemStack itemStack)
	{
		for (Entry<ItemStack[], ItemStack> entry : this.alchemyList.entrySet()) 
		{
			if(compareItemStacks(itemStack, (ItemStack)entry.getKey()[slotIndex]))
			{
				return true;
			}
		}
		
		return false;
	}

	public Map getAlchemyList()
	{
		return alchemyList;
	}

	private boolean areItemStacksEqual(ItemStack parItemStack1, ItemStack parItemStack2)
	{
		return parItemStack2.getItem() == parItemStack1.getItem() 
				&& (parItemStack2.getItemDamage() == 32767 
				|| parItemStack2.getItemDamage() == parItemStack1.getItemDamage());
	}

	public int getAlchemenisizerItemDuration(ItemStack parItemStack)
	{
		Iterator iterator = durationList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return 200; // Dummy value
			}

			entry = (Entry)iterator.next();
		}
		while (!areItemStacksEqual(parItemStack, (ItemStack)entry.getKey()));

		return ((Integer)entry.getValue()).intValue();
	}

	public float getAlchemyExperience(ItemStack stack)
	{
		for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()) 
		{
			if(this.compareItemStacks(stack, (ItemStack)entry.getKey())) 
			{
				return ((Float)entry.getValue()).floatValue();
			}
		}
		
		return 0.0F;
	}
}