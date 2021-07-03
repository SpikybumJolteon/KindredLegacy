package fuzzyacornindustries.kindredlegacy.common.capability;

import java.util.ArrayList;

import fuzzyacornindustries.kindredlegacy.common.core.ModItems;
import fuzzyacornindustries.kindredlegacy.lib.Names;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * Capability for {@link IInventoryBackup}.
 * 
 * Based on code:
 * @author Choonster
 */
public final class InventoryRestoreCapability 
{
	/**
	 * The {@link Capability} instance.
	 */
	@CapabilityInject(IInventoryBackup.class)
	public static final Capability<IInventoryBackup> INVENTORY_RESTORE_CAPABILITY = null;

	/**
	 * The default {@link Direction} to use for this capability.
	 */
	public static final Direction DEFAULT_FACING = Direction.DOWN;

	/**
	 * The ID of the capability.
	 */
	public static final ResourceLocation ID = new ResourceLocation(Names.MOD_ID, "inventory_backup");

	/**
	 * Register the capability.
	 */
	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IInventoryBackup.class, new Capability.IStorage<IInventoryBackup>()  
		{
			@Override
			public INBT writeNBT(Capability<IInventoryBackup> capability, IInventoryBackup instance, Direction side) 
			{
				ListNBT taglist = new ListNBT();
				ItemStack[][] inv = instance.getInv();
				ItemStack[] main = inv[0];
				ItemStack[] armor = inv[1];
				ItemStack[] offhand = inv[2];
				byte slot = 0;
				for (int i = 0; i < main.length; i++) 
				{
					ItemStack its = main[i];
					if(its==null)its=ItemStack.EMPTY;
					CompoundNBT tag = new CompoundNBT();
					tag.putByte("Slot", slot);
					its.write(tag);
					taglist.add(slot, tag);
					slot++;
				}
				for (int i = 0; i < armor.length; i++) 
				{
					ItemStack its = armor[i];
					if(its==null)its=ItemStack.EMPTY;
					CompoundNBT tag = new CompoundNBT();
					tag.putByte("Slot", slot);
					its.write(tag);
					taglist.add(slot, tag);
					slot++;
				}
				for(int i = 0; i < offhand.length;i++)
				{
					ItemStack its = offhand[i];
					if(its==null)its=ItemStack.EMPTY;
					CompoundNBT tag = new CompoundNBT();
					tag.putByte("Slot", slot);
					its.write(tag);
					taglist.add(slot, tag);
				}
				return taglist;
			}

			@Override
			public void readNBT(Capability<IInventoryBackup> capability, IInventoryBackup instance, Direction side, INBT nbt) 
			{
				ListNBT taglist = (ListNBT)nbt;
				ItemStack[] main = new ItemStack[36];
				ItemStack[] armor = new ItemStack[4];
				ItemStack offhand = ItemStack.EMPTY;
				for(int i = 0;i<taglist.size();i++)
				{
					CompoundNBT tag = (CompoundNBT)taglist.get(i);
					byte slot = tag.getByte("Slot");
					if(slot>=0&&slot<36) 
					{
						main[slot] = ItemStack.read(tag);
						if(main[slot]==null)main[slot]=ItemStack.EMPTY;
					} 
					else if(slot>=36&&slot<40) 
					{
						armor[slot - 36] = ItemStack.read(tag);
						if(armor[slot - 36]==null)armor[slot - 36]=ItemStack.EMPTY;
					}
					else if(slot==40) 
					{
						offhand = ItemStack.read(tag);
					}
				}
				instance.storeInv(new ItemStack[][]{main, armor, new ItemStack[]{offhand}});
			}
		}, () -> new DefaultInventoryBackup());
	}

	/**
	 * Get the {@link IInventoryBackup} from the specified {@link ItemStack}'s capabilities, if any.
	 *
	 * @param itemStack The ItemStack
	 * @return A lazy optional containing the IInventoryBackup, if any
	 */
	public static LazyOptional<IInventoryBackup> getInventoryBackup(final PlayerEntity player) 
	{
		return player.getCapability(INVENTORY_RESTORE_CAPABILITY, DEFAULT_FACING);
	}

	/**
	 * Create a provider for the default {@link IInventoryBackup} instance.
	 *
	 * @return The provider
	 */
	public static ICapabilityProvider createProvider() 
	{
		return new SerializableCapabilityProvider<>(INVENTORY_RESTORE_CAPABILITY, DEFAULT_FACING);
	}

	/**
	 * Create a provider for the specified {@link IPigSpawner} instance.
	 *
	 * @param inventoryBackup The IInventoryBackup
	 * @return The provider
	 */
	public static ICapabilityProvider createProvider(final IInventoryBackup inventoryBackup) 
	{
		return new SerializableCapabilityProvider<>(INVENTORY_RESTORE_CAPABILITY, DEFAULT_FACING, inventoryBackup);
	}

	/**
	 * Event handler for the {@link IInventoryBackup} capability.
	 */
	@Mod.EventBusSubscriber(modid = Names.MOD_ID)
	private static class EventHandler {

		/**
		 * Attach the {@link IInventoryBackup} capability to vanilla items.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) 
		{
			if (event.getObject() instanceof PlayerEntity) 
			{
				event.addCapability(ID, createProvider());
			}
		}

		/*
		 * Original author: Venrob - RobsStuff
		 */
		@SubscribeEvent
		public static void saveInvOnDeath(LivingDeathEvent event) 
		{
			if(event.getEntity() instanceof PlayerEntity) 
			{
				PlayerEntity player = (PlayerEntity) event.getEntity();
				getInventoryBackup(player).ifPresent(invCapa -> {
					PlayerInventory newInv = new PlayerInventory(player);
					boolean hasHearthstone = false;//Assume no hearthstone
					for (ItemStack stack : player.inventory.mainInventory) 
					{//Check each stack of inventory for hearthstone
						if (stack.getItem() == ModItems.PHOENIX_HEARTHSTONE.get()) 
						{
							hasHearthstone = true;
							break;
						}
					}
					ItemStack offhand = player.inventory.offHandInventory.get(0);
					if (!hasHearthstone&&offhand.getItem().equals(ModItems.PHOENIX_HEARTHSTONE.get())) 
					{
						hasHearthstone = true;
					}
					if (hasHearthstone) {
						newInv.copyInventory(player.inventory);
					}

					invCapa.storeInv(newInv);
					if(hasHearthstone)player.inventory.clear();
				});

			}
		}

		/*
		 * Original author: Venrob - RobsStuff
		 */
		@SubscribeEvent
		public static void restoreInvOnRespawn(PlayerEvent.Clone event)
		{
			if(event.isWasDeath())
			{
				PlayerEntity player = event.getPlayer();
				PlayerEntity original = event.getOriginal();

				getInventoryBackup(original).ifPresent(invCapa -> {
					ItemStack[][] newInv = invCapa.getInv();
					ArrayList<ItemStack> extra = new ArrayList<>();
					NonNullList<ItemStack> main = player.inventory.mainInventory;
					for(int i = 0;i<main.size();i++) 
					{
						if(main.get(i)!=ItemStack.EMPTY)
							extra.add(main.get(i));
						main.set(i,newInv[0][i]);
					}
					NonNullList<ItemStack> armor = player.inventory.armorInventory;
					for(int i = 0;i<armor.size();i++) 
					{
						if(armor.get(i)!=ItemStack.EMPTY)
							extra.add(armor.get(i));
						armor.set(i,newInv[1][i]);
					}
					NonNullList<ItemStack> off = player.inventory.offHandInventory;
					for(int i = 0;i<off.size();i++) 
					{
						if(off.get(i)!=ItemStack.EMPTY)
							extra.add(off.get(i));
						off.set(i,newInv[2][i]);
					}
				});
			}
		} 
	}
}