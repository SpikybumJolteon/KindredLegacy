package fuzzyacornindustries.kindredlegacy.recipe;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import fuzzyacornindustries.kindredlegacy.KindredLegacyMain;
import fuzzyacornindustries.kindredlegacy.block.KindredLegacyBlocks;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class VerdantPurifierRecipes 
{	
	private static final VerdantPurifierRecipes INSTANCE = new VerdantPurifierRecipes();
	private final Map<ItemStack, ItemStack> purificationList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, Integer> durationList = Maps.<ItemStack, Integer>newHashMap();

	public static VerdantPurifierRecipes getInstance()
	{
		return INSTANCE;
	}

	private VerdantPurifierRecipes() 
	{
		int primaryDuration = 500;
		float primaryExp = 0.1F;

		addPurificationRecipe(new ItemStack(Items.SPIDER_EYE), new ItemStack(Items.NETHER_WART), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Items.APPLE), new ItemStack(KindredLegacyItems.ORANIAN_BERRY), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Items.POISONOUS_POTATO), new ItemStack(KindredLegacyItems.PECHITA_BERRY), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Items.ROTTEN_FLESH), new ItemStack(Items.PORKCHOP), primaryDuration, primaryExp);

		addPurificationRecipe(new ItemStack(Items.PAPER), new ItemStack(Items.REEDS), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Items.SUGAR), new ItemStack(Items.REEDS), primaryDuration, primaryExp);

		addPurificationRecipe(new ItemStack(Items.GOLD_NUGGET), new ItemStack(KindredLegacyItems.AURUM_DUST), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Items.GOLD_INGOT), new ItemStack(KindredLegacyBlocks.PACKED_AURUM_DUST), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(KindredLegacyItems.TIBERIUM_SHARD), new ItemStack(Items.QUARTZ), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(KindredLegacyItems.RICH_TIBERIUM_SHARD), new ItemStack(KindredLegacyItems.TIBERIUM_SHARD), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(KindredLegacyItems.NEO_TIBERIUM_SHARD), new ItemStack(KindredLegacyItems.RICH_TIBERIUM_SHARD), primaryDuration, primaryExp);

		addPurificationRecipe(new ItemStack(Items.DYE, 1, 2), new ItemStack(Blocks.CACTUS), primaryDuration, primaryExp);

		addPurificationRecipe(new ItemStack(Blocks.COBBLESTONE), new ItemStack(Blocks.MOSSY_COBBLESTONE), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Blocks.MOSSY_COBBLESTONE), new ItemStack(Blocks.DIRT, 1, 0), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Blocks.DIRT, 1, 0), new ItemStack(Blocks.GRASS), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Blocks.STONE, 1, 0), new ItemStack(Blocks.GRAVEL), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Blocks.GRAVEL), new ItemStack(Blocks.SAND, 1, 0), primaryDuration, primaryExp);
		addPurificationRecipe(new ItemStack(Blocks.SOUL_SAND), new ItemStack(Blocks.SAND, 1, 0), primaryDuration, primaryExp);

		addPurificationRecipe(new ItemStack(Blocks.STICKY_PISTON), new ItemStack(Blocks.PISTON), primaryDuration, primaryExp);

		addPurificationRecipe(new ItemStack(Blocks.LOG, 1, 0), new ItemStack(Blocks.SAPLING, 1, 0), primaryDuration, primaryExp); // Oak
		addPurificationRecipe(new ItemStack(Blocks.LOG, 1, 1), new ItemStack(Blocks.SAPLING, 1, 1), primaryDuration, primaryExp); // Spruce
		addPurificationRecipe(new ItemStack(Blocks.LOG, 1, 2), new ItemStack(Blocks.SAPLING, 1, 2), primaryDuration, primaryExp); // Birch
		addPurificationRecipe(new ItemStack(Blocks.LOG, 1, 3), new ItemStack(Blocks.SAPLING, 1, 3), primaryDuration, primaryExp); // Jungle
		addPurificationRecipe(new ItemStack(Blocks.LOG2, 1, 0), new ItemStack(Blocks.SAPLING, 1, 4), primaryDuration, primaryExp); // Acacia
		addPurificationRecipe(new ItemStack(Blocks.LOG2, 1, 1), new ItemStack(Blocks.SAPLING, 1, 5), primaryDuration, primaryExp); // Dark Oak


		//addPurificationRecipe(new ItemStack(Items.QUARTZ), new ItemStack(KindredLegacyItems.TIBERIUM_SHARD), primaryDuration, primaryExp);

		//addPurificationRecipe(new ItemStack(KindredLegacyItems.TIBERIUM_SHARD), new ItemStack(KindredLegacyItems.RICH_TIBERIUM_SHARD), 3000, 0.2F);
		//addPurificationRecipe(new ItemStack(KindredLegacyItems.RICH_TIBERIUM_SHARD), new ItemStack(KindredLegacyItems.NEO_TIBERIUM_SHARD), 10000, 0.4F);
		//addPurificationRecipe(new ItemStack(Items.REDSTONE), new ItemStack(KindredLegacyItems.CHARGESTONE), 3000, 0.3F);


		/* *********** Twilight Forest Recipes *********** */
		if(KindredLegacyMain.isTwilightForestEnabled)
		{
			ItemStack firefly = GameRegistry.makeItemStack("twilightforest:firefly", 0, 1, null);

			if(firefly != null)
			{
				addPurificationRecipe(firefly, new ItemStack(Items.GLOWSTONE_DUST), primaryDuration, primaryExp);
			}
		}
	}

	public void addPurificationRecipe(ItemStack input1, ItemStack result, int duration, float experience) 
	{
		if(getPurificationResult(input1) != ItemStack.EMPTY) return;
		this.purificationList.put(input1, result);
		this.experienceList.put(result, Float.valueOf(experience));
		this.durationList.put(input1, duration);
	}

	public ItemStack getPurificationResult(ItemStack parItemStack)
	{
		Iterator iterator = purificationList.entrySet().iterator();
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
		return purificationList;
	}

	public int getVerdantPurifierItemDuration(ItemStack parItemStack)
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

	public float getPurificationExperience(ItemStack stack)
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