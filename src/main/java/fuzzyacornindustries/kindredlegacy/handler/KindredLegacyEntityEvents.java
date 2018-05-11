package fuzzyacornindustries.kindredlegacy.handler;

import java.util.List;

import fuzzyacornindustries.kindredlegacy.entity.KindredLegacyEntities;
import fuzzyacornindustries.kindredlegacy.entity.mob.hostile.EntityRaptorZerglingNincada;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFeywoodAbsolSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFirecrackerLittenSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemFoxcraftFennekinSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiEspeonSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiSylveonSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemOkamiUmbreonSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemPoketamableSummon;
import fuzzyacornindustries.kindredlegacy.item.tamable.ItemVastayaNinetalesSummon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class KindredLegacyEntityEvents 
{
	@SubscribeEvent
	public void onItemToss(ItemTossEvent event)
	{
		if (!event.getEntity().world.isRemote)
		{
			EntityItem itemDropped = event.getEntityItem();
			//System.out.println("PlayerDropsEvent iterating over drop "+itemDropped);

			final Item id = itemDropped.getItem().getItem();

			if (id instanceof ItemPoketamableSummon)
			{
				TamablePokemon poketamable = null;

				if(id == KindredLegacyItems.OKAMI_SYLVEON_SUMMON)
				{
					poketamable = ItemOkamiSylveonSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.OKAMI_UMBREON_SUMMON)
				{
					poketamable = ItemOkamiUmbreonSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.OKAMI_ESPEON_SUMMON)
				{
					poketamable = ItemOkamiEspeonSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.FIRECRACKER_LITTEN_SUMMON)
				{
					poketamable = ItemFirecrackerLittenSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.FOXCRAFT_FENNEKIN_SUMMON)
				{
					poketamable = ItemFoxcraftFennekinSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.FEYWOOD_ABSOL_SUMMON)
				{
					poketamable = ItemFeywoodAbsolSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.VASTAYA_NINETALES_SUMMON)
				{
					poketamable = ItemVastayaNinetalesSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}

				if(poketamable != null)
				{
					revivePoketamable(event, poketamable, itemDropped);
				}
			}
			else if(id == KindredLegacyItems.REVIVE_SEED)
			{
				final List nearEnts = itemDropped.world.getEntitiesWithinAABBExcludingEntity(itemDropped, itemDropped.getEntityBoundingBox().grow(8D, 8D, 8D));

				for (Object o : nearEnts)
				{
					if (o instanceof EntityItem)
					{
						EntityItem foundItem;

						foundItem = (EntityItem) o;

						if (foundItem.getItem().getItem() instanceof ItemPoketamableSummon)
						{
							TamablePokemon poketamable = null;

							if(foundItem.getItem().getItem() == KindredLegacyItems.OKAMI_SYLVEON_SUMMON)
							{
								poketamable = ItemOkamiSylveonSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.OKAMI_ESPEON_SUMMON)
							{
								poketamable = ItemOkamiEspeonSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.OKAMI_UMBREON_SUMMON)
							{
								poketamable = ItemOkamiUmbreonSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FIRECRACKER_LITTEN_SUMMON)
							{
								poketamable = ItemFirecrackerLittenSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FOXCRAFT_FENNEKIN_SUMMON)
							{
								poketamable = ItemFoxcraftFennekinSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FEYWOOD_ABSOL_SUMMON)
							{
								poketamable = ItemFeywoodAbsolSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.VASTAYA_NINETALES_SUMMON)
							{
								poketamable = ItemVastayaNinetalesSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}

							if(poketamable != null)
							{
								poketamable.setPosition(itemDropped.posX, itemDropped.posY, itemDropped.posZ);
								itemDropped.world.spawnEntity(poketamable);
								poketamable.setHealth(poketamable.getMaxHealth()); // set full entity health
								event.setCanceled(true);
								foundItem.getItem().shrink(1); // Decrease itemStack by 1
							}

							break;
						}
					}
				}
			}
		}
	}

	public void revivePoketamable(ItemTossEvent event, TamablePokemon poketamable, EntityItem itemDropped)
	{
		// poketamable is ko'd. see if it was tossed onto revive seed for revival

		final List nearEnts = event.getEntityItem().world.getEntitiesWithinAABBExcludingEntity(itemDropped, itemDropped.getEntityBoundingBox().grow(8D, 8D, 8D));

		for (Object o : nearEnts)
		{
			System.out.println( "Testing code at EntityEvents" );
			System.out.println( "List length? " + (nearEnts.size()));
			System.out.println( "EntityItem? " + (o instanceof EntityItem));
			System.out.println( "Item? " + (o instanceof Item));
			System.out.println( "ItemStack? " + (o instanceof ItemStack));
			System.out.println( "EntityPlayer? " + (o instanceof EntityPlayer));
			
			if (o instanceof EntityItem)
			{
				EntityItem foundItem;

				foundItem = (EntityItem) o;
				
				if (foundItem.getItem().getItem() == KindredLegacyItems.REVIVE_SEED)
				{
					poketamable.setPosition(itemDropped.posX, itemDropped.posY, itemDropped.posZ);
					itemDropped.world.spawnEntity(poketamable);
					poketamable.setHealth(poketamable.getMaxHealth()); // set full entity health
					event.setCanceled(true);
					foundItem.getItem().shrink(1); // Decrease itemStack by 1

					break;
				}
			}
		}
	}

	@SubscribeEvent
	public void onAttacked(LivingAttackEvent event) 
	{
		if(event.getSource() == DamageSource.WITHER || event.getSource() == DamageSource.MAGIC)
		{
			if(event.getEntity() instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) event.getEntity();

				if(player.inventory.armorInventory.get(3) != null && player.inventory.armorInventory.get(3).getItem() == KindredLegacyItems.SILKSCREEN_RIBBON)
				{
					if(event.getSource() == DamageSource.WITHER || (event.getSource() == DamageSource.MAGIC && player.isPotionActive(MobEffects.POISON)))
					{
						event.setCanceled(true);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void addMobDrop(LivingDropsEvent event)
	{
		if (event.getEntityLiving() instanceof EntityRaptorZerglingNincada)
		{
			if(KindredLegacyEntities.isGalacticraftDimension(event.getEntity().world.provider.getDimension()) && event.getEntity().getEntityWorld().rand.nextInt(2) == 1)
			{
				event.getEntityLiving().entityDropItem(new ItemStack(Items.GLOWSTONE_DUST), 1);
			}
		}
	}

	@SubscribeEvent
	public void addSquidDrop(LivingDropsEvent event)
	{
		if (event.getEntityLiving() instanceof EntitySquid)
		{
			if(event.getEntity().getEntityWorld().rand.nextInt(2) == 1)
			{
				event.getEntityLiving().entityDropItem(new ItemStack(KindredLegacyItems.HUGE_SQUID_TENTACLE), 1);
			}
		}
	}
}