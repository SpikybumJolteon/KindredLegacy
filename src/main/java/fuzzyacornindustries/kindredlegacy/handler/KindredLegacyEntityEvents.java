package fuzzyacornindustries.kindredlegacy.handler;

import java.util.List;

import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.FirecrackerLittenEntity;
import fuzzyacornindustries.kindredlegacy.entity.mob.tamable.TamablePokemon;
import fuzzyacornindustries.kindredlegacy.item.tamable.FeywoodAbsolSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.FirecrackerLittenSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OkamiEspeonSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OkamiSylveonSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.OkamiUmbreonSummonItem;
import fuzzyacornindustries.kindredlegacy.item.tamable.PoketamableSummonItem;
import fuzzyacornindustries.kindredlegacy.lists.KindredLegacyItems;
import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class KindredLegacyEntityEvents 
{
	@SubscribeEvent
	public void playerLoggedIn(PlayerLoggedInEvent event) 
	{
		final PlayerEntity player = event.getPlayer();

		final CompoundNBT entityData = player.getPersistentData();
		final CompoundNBT persistedData = entityData.getCompound(PlayerEntity.PERSISTED_NBT_TAG);
		entityData.put(PlayerEntity.PERSISTED_NBT_TAG, persistedData);

		final String key = ModInfo.MOD_ID + ":startingitems";

		if (!persistedData.getBoolean(key)) 
		{
			persistedData.putBoolean(key, true);

			ItemHandlerHelper.giveItemToPlayer(player, new ItemStack(KindredLegacyItems.POKEMON_EXPLORATION_KIT));

			final ITextComponent textComponent = new StringTextComponent("A new Pokemon adventure awaits...");
			textComponent.getStyle().setColor(TextFormatting.LIGHT_PURPLE);
			player.sendMessage(textComponent);
		}
	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event)
	{
		if (!event.getEntity().world.isRemote)
		{
			ItemEntity itemDropped = event.getEntityItem();
			//System.out.println("PlayerDropsEvent iterating over drop "+itemDropped);

			final Item id = itemDropped.getItem().getItem();

			if (id instanceof PoketamableSummonItem)
			{
				TamablePokemon poketamable = null;

				if(id == KindredLegacyItems.OKAMI_SYLVEON_SUMMON)
				{
					poketamable = OkamiSylveonSummonItem.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.OKAMI_ESPEON_SUMMON)
				{
					poketamable = OkamiEspeonSummonItem.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.OKAMI_UMBREON_SUMMON)
				{
					poketamable = OkamiUmbreonSummonItem.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.FEYWOOD_ABSOL_SUMMON)
				{
					poketamable = FeywoodAbsolSummonItem.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.FIRECRACKER_LITTEN_SUMMON)
				{
					poketamable = FirecrackerLittenSummonItem.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}/*
				else if(id == KindredLegacyItems.FOXCRAFT_FENNEKIN_SUMMON)
				{
					poketamable = ItemFoxcraftFennekinSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.FOXFIRE_ZORUA_SUMMON)
				{
					poketamable = ItemFoxfireZoruaSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.VASTAYA_NINETALES_SUMMON)
				{
					poketamable = ItemVastayaNinetalesSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}
				else if(id == KindredLegacyItems.IMMORTAL_ARCANINE_SUMMON)
				{
					poketamable = ItemImmortalArcanineSummon.toPoketamableEntity(itemDropped.world, itemDropped.getItem(), event.getPlayer());
				}*/

				if(poketamable != null)
				{
					revivePoketamable(event, poketamable, itemDropped);
				}
			}
			else if(id == KindredLegacyItems.REVIVE_SEED)
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

							if(foundItem.getItem().getItem() == KindredLegacyItems.OKAMI_SYLVEON_SUMMON)
							{
								poketamable = OkamiSylveonSummonItem.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.OKAMI_ESPEON_SUMMON)
							{
								poketamable = OkamiEspeonSummonItem.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.OKAMI_UMBREON_SUMMON)
							{
								poketamable = OkamiUmbreonSummonItem.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FEYWOOD_ABSOL_SUMMON)
							{
								poketamable = FeywoodAbsolSummonItem.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FIRECRACKER_LITTEN_SUMMON)
							{
								poketamable = FirecrackerLittenSummonItem.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}/*
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FOXCRAFT_FENNEKIN_SUMMON)
							{
								poketamable = ItemFoxcraftFennekinSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.FOXFIRE_ZORUA_SUMMON)
							{
								poketamable = ItemFoxfireZoruaSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.VASTAYA_NINETALES_SUMMON)
							{
								poketamable = ItemVastayaNinetalesSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}
							else if(foundItem.getItem().getItem() == KindredLegacyItems.IMMORTAL_ARCANINE_SUMMON)
							{
								poketamable = ItemImmortalArcanineSummon.toPoketamableEntity(itemDropped.world, foundItem.getItem(), event.getPlayer());
							}*/

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
			//System.out.println( "Testing code at EntityEvents" );
			//System.out.println( "List length? " + (nearEnts.size()));
			//System.out.println( "EntityItem? " + (o instanceof EntityItem));
			//System.out.println( "Item? " + (o instanceof Item));
			//System.out.println( "ItemStack? " + (o instanceof ItemStack));
			//System.out.println( "EntityPlayer? " + (o instanceof EntityPlayer));

			if (o instanceof ItemEntity)
			{
				ItemEntity foundItem;

				foundItem = (ItemEntity) o;

				if (foundItem.getItem().getItem() == KindredLegacyItems.REVIVE_SEED)
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


	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) 
	{
		if(event.getEntity() instanceof CreeperEntity)
		{
			((CreeperEntity)event.getEntity()).goalSelector.addGoal(3, new AvoidEntityGoal<>(((CreeperEntity)event.getEntity()), FirecrackerLittenEntity.class, 8.0F, 1.0D, 1.2D));
		}
	}

	/*
	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) 
	{
		if(event.getEntity() instanceof PlayerEntity)
		{
			ITextComponent textComponent = new StringTextComponent("Oh no you died!");
			textComponent.getStyle().setColor(TextFormatting.LIGHT_PURPLE);
			event.getEntity().sendMessage(textComponent);

			//PlayerEntity player = (PlayerEntity) event.getEntity();

			for(int i = 0; i < ((PlayerEntity) event.getEntity()).inventory.getSizeInventory(); i++)
			{
				if(((PlayerEntity) event.getEntity()).inventory.getStackInSlot(i).getItem() instanceof OkamiSylveonSummonItem)
				{
					textComponent = new StringTextComponent("Oh no Sylvanna!");
					textComponent.getStyle().setColor(TextFormatting.RED);
					event.getEntity().sendMessage(textComponent);
				}
			}
		}
	}

	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) 
	{
		if(event.getEntity() instanceof PlayerEntity)
		{
			final ITextComponent textComponent = new StringTextComponent("Oh no your items!");
			textComponent.getStyle().setColor(TextFormatting.DARK_RED);
			event.getEntity().sendMessage(textComponent);
		}
	}

	@SubscribeEvent
	public void onPlayerClone(Clone event) 
	{
		if(event.isWasDeath())
		{
			ITextComponent textComponent = new StringTextComponent("Flarevautia used Phoenix Down!");
			textComponent.getStyle().setColor(TextFormatting.YELLOW);
			event.getEntity().sendMessage(textComponent);

			for(int i = 0; i < event.getOriginal().inventory.getSizeInventory(); i++)
			{
				if(event.getOriginal().inventory.getStackInSlot(i).getItem() instanceof OkamiSylveonSummonItem)
				{
					textComponent = new StringTextComponent("Oh Sylvanna?");
					textComponent.getStyle().setColor(TextFormatting.BLUE);
					event.getEntity().sendMessage(textComponent);
				}

				if(event.getOriginal().inventory.getStackInSlot(i).getItem() == Items.STRING) 
				{
					textComponent = new StringTextComponent("Found String");
					textComponent.getStyle().setColor(TextFormatting.BLUE);
					event.getEntity().sendMessage(textComponent);
				}
			}

			for(int i = 0; i < event.getPlayer().inventory.getSizeInventory(); i++)
			{
				if(event.getPlayer().inventory.getStackInSlot(i).getItem() instanceof OkamiSylveonSummonItem)
				{
					textComponent = new StringTextComponent("Oh Sylvanna? (getPlayer())");
					textComponent.getStyle().setColor(TextFormatting.BLUE);
					event.getEntity().sendMessage(textComponent);
				}

				if(event.getPlayer().inventory.getStackInSlot(i).getItem() == Items.STRING) 
				{
					textComponent = new StringTextComponent("Found String (getPlayer())");
					textComponent.getStyle().setColor(TextFormatting.BLUE);
					event.getEntity().sendMessage(textComponent);
				}
			}

			event.getPlayer().inventory.setInventorySlotContents(1, new ItemStack(KindredLegacyItems.ORANIAN_BERRY));
		}
	}*/

	/*
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

/*
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
	}*/
}