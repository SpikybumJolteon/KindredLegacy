package fuzzyacornindustries.kindredlegacy.recipe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class VespeneCondenserRecipes 
{	
	private static final VespeneCondenserRecipes INSTANCE = new VespeneCondenserRecipes();
	private final Map<ItemStack, ItemStack> condensationList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, Integer> durationList = Maps.<ItemStack, Integer>newHashMap();

	public static VespeneCondenserRecipes getInstance()
	{
		return INSTANCE;
	}

	private VespeneCondenserRecipes() 
	{
		addCondensationRecipe(new ItemStack(Items.QUARTZ), new ItemStack(KindredLegacyItems.TIBERIUM_SHARD), 500, 0.1F);
		addCondensationRecipe(new ItemStack(KindredLegacyItems.TIBERIUM_SHARD), new ItemStack(KindredLegacyItems.RICH_TIBERIUM_SHARD), 5000, 0.2F);
		addCondensationRecipe(new ItemStack(KindredLegacyItems.RICH_TIBERIUM_SHARD), new ItemStack(KindredLegacyItems.NEO_TIBERIUM_SHARD), 15000, 0.4F);
		addCondensationRecipe(new ItemStack(Items.REDSTONE), new ItemStack(KindredLegacyItems.CHARGESTONE), 3000, 0.3F);
		addCondensationRecipe(new ItemStack(KindredLegacyItems.AURUM_DUST), new ItemStack(KindredLegacyItems.VESPENE_CRYSTAL), 3000, 0.2F);
		addCondensationRecipe(new ItemStack(KindredLegacyItems.AURUM_ROD), new ItemStack(KindredLegacyItems.VESPENE_FUEL_ROD), 8000, 0.2F);
		addCondensationRecipe(new ItemStack(Items.EMERALD), new ItemStack(KindredLegacyItems.EMERALD_OF_CHAOS), 26000 * 3, 0.9F); // 26000 equals a day
	}

	public void addCondensationRecipe(ItemStack input1, ItemStack result, int duration, float experience) 
	{
		if(getCondensationResult(input1) != ItemStack.EMPTY) return;
		this.condensationList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
		this.durationList.put(input1, duration);
	}

	public ItemStack getCondensationResult(ItemStack parItemStack)
	{
		Iterator iterator = condensationList.entrySet().iterator();
		Entry entry;

		do
		{
			if (!iterator.hasNext())
			{
				return ItemStack.EMPTY;
			}

			entry = (Entry)iterator.next();
		}
		while (!areItemStacksEqual(parItemStack, (ItemStack)entry.getKey()));

		return (ItemStack)entry.getValue();
	}

	private boolean areItemStacksEqual(ItemStack parItemStack1, ItemStack parItemStack2)
	{
		return parItemStack2.getItem() == parItemStack1.getItem() 
				&& (parItemStack2.getItemDamage() == 32767 
				|| parItemStack2.getItemDamage() == parItemStack1.getItemDamage());
	}

	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
	{
		return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
	}

	public Map getCondensingList()
	{
		return condensationList;
	}

	public int getVespeneCondenserItemDuration(ItemStack parItemStack)
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

	public float getCondensationExperience(ItemStack stack)
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