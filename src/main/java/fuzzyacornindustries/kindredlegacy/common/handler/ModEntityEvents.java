package fuzzyacornindustries.kindredlegacy.common.handler;

import java.util.List;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.common.item.PoketamableSummonItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityEvents 
{
	//	@SubscribeEvent
	//	public void playerLoggedIn(PlayerLoggedInEvent event) 
	//	{
	//		final PlayerEntity player = event.getPlayer();
	//
	//		final CompoundNBT entityData = player.getPersistentData();
	//		final CompoundNBT persistedData = entityData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
	//		entityData.put(PlayerEntity.PERSISTED_NBT_TAG, persistedData);
	//
	//		final String key = Names.MOD_ID + ":startingitems";
	//
	//		if (!persistedData.getBoolean(key)) 
	//		{
	//			persistedData.putBoolean(key, true);
	//
	//			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(ModItems.POKEMON_EXPLORATION_KIT.get()));
	//
	//			final ITextComponent textComponent = new StringTextComponent("A new Pokemon adventure awaits...");
	//			textComponent.getStyle().setColor(TextFormatting.LIGHT_PURPLE);
	//			player.sendMessage(textComponent);
	//		}
	//	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event)
	{
		if (!event.getEntity().world.isRemote)
		{
			ItemEntity itemDropped = event.getEntityItem();

			final Item id = itemDropped.getItem().getItem();

			if (id instanceof PoketamableSummonItem)
			{
				TamablePokemon poketamable = null;

				poketamable = PoketamableSummonItem.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer(), ((PoketamableSummonItem) id).getPoketamable().apply(itemDropped.world));

				if(poketamable != null)
				{
					revivePoketamable(event, poketamable, itemDropped);
				}
			}
			else if(id == ModItems.REVIVE_SEED.get())
			{
				final List<Entity> nearEnts = itemDropped.world.getEntitiesWithinAABBExcludingEntity(itemDropped, itemDropped.getBoundingBox().grow(8D, 8D, 8D));

				for (Object o : nearEnts)
				{
					if (o instanceof ItemEntity)
					{
						ItemEntity foundItem;

						foundItem = (ItemEntity) o;

						if (foundItem.getItem().getItem() instanceof PoketamableSummonItem)
						{
							TamablePokemon poketamable = null;

							poketamable = PoketamableSummonItem.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer(), ((PoketamableSummonItem)foundItem.getItem().getItem()).getPoketamable().apply(itemDropped.world));

							if(poketamable != null)
							{
								poketamable.setPosition(itemDropped.getPosX(), itemDropped.getPosY(), itemDropped.getPosZ());
								itemDropped.world.addEntity(poketamable);
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

	public void revivePoketamable(ItemTossEvent event, TamablePokemon poketamable, ItemEntity itemDropped)
	{
		// poketamable is ko'd. see if it was tossed onto revive seed for revival

		final List<Entity> nearEnts = event.getEntityItem().world.getEntitiesWithinAABBExcludingEntity(itemDropped, itemDropped.getBoundingBox().grow(8D, 8D, 8D));

		for (Object o : nearEnts)
		{
			if (o instanceof ItemEntity)
			{
				ItemEntity foundItem;

				foundItem = (ItemEntity) o;

				if (foundItem.getItem().getItem() == ModItems.REVIVE_SEED.get())
				{
					poketamable.setPosition(itemDropped.getPosX(), itemDropped.getPosY(), itemDropped.getPosZ());
					itemDropped.world.addEntity(poketamable);
					poketamable.setHealth(poketamable.getMaxHealth()); // set full entity health
					event.setCanceled(true);
					foundItem.getItem().shrink(1); // Decrease itemStack by 1

					break;
				}
			}
		}
	}


	//	@SubscribeEvent
	//	public void onEntityJoinWorld(EntityJoinWorldEvent event) 
	//	{
	//		if(event.getEntity() instanceof CreeperEntity)
	//		{
	//			((CreeperEntity)event.getEntity()).goalSelector.addGoal(3, new AvoidEntityGoal<>(((CreeperEntity)event.getEntity()), FirecrackerLittenEntity.class, 8.0F, 1.0D, 1.2D));
	//		}
	//	}
}