package fuzzyacornindustries.kindredlegacy.capabilities;

import javax.annotation.Nullable;

import fuzzyacornindustries.kindredlegacy.reference.ModInfo;
import fuzzyacornindustries.kindredlegacy.utility.CapabilityUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Based on work by:
 * @author Choonster
 */
public final class CapabilityPlayerStartingGear 
{
	@CapabilityInject(IPlayerStartingGear.class)
	public static final Capability<IPlayerStartingGear> PLAYER_STARTING_GEAR_CAPABILITY = null;

	public static final EnumFacing DEFAULT_FACING = null;

	public static final ResourceLocation ID = new ResourceLocation(ModInfo.MOD_ID, "PlayerStartingGear");

	//public static final Marker LOG_MARKER = MarkerManager.getMarker("MaxHealth").addParents(Logger.MOD_MARKER);

	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IPlayerStartingGear.class, new Capability.IStorage<IPlayerStartingGear>() 
		{
			@Override
			public NBTBase writeNBT(final Capability<IPlayerStartingGear> capability, final IPlayerStartingGear instance, final EnumFacing side) 
			{
				return new NBTTagByte(instance.getPlayerStartingGear());
			}

			@Override
			public void readNBT(Capability<IPlayerStartingGear> capability, IPlayerStartingGear instance, EnumFacing side, NBTBase nbt) 
			{
				instance.setPlayerStartingGear(((NBTTagByte) nbt).getByte());
				
			}
		}, () -> new PlayerStartingGear(null));
	}

	@Nullable
	public static IPlayerStartingGear getPlayerStartingGear(final EntityPlayer player) 
	{
		return CapabilityUtility.getCapability(player, PLAYER_STARTING_GEAR_CAPABILITY, DEFAULT_FACING);
	}

	public static ICapabilityProvider createProvider(final IPlayerStartingGear playerStartingGear) 
	{
		return new CapabilityProviderSerializable<>(PLAYER_STARTING_GEAR_CAPABILITY, DEFAULT_FACING, playerStartingGear);
	}

	/**
	 * Event handler for the {@link IMaxHealth} capability.
	 */
	@SuppressWarnings("unused")
	@Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
	private static class EventHandler 
	{
		/**
		 * Attach the {@link IPlayerStartingGear} capability to all playerss.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void attachCapabilities(final AttachCapabilitiesEvent<Entity> event) 
		{
			if (event.getObject() instanceof EntityPlayer) 
			{
				final PlayerStartingGear maxHealth = new PlayerStartingGear((EntityPlayer) event.getObject());
				event.addCapability(ID, createProvider(maxHealth));
			}
		}

		/**
		 * Copy the player's bonus max health when they respawn after dying or returning from the end.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void playerClone(final PlayerEvent.Clone event) 
		{
			final IPlayerStartingGear oldPlayerStartingGear = getPlayerStartingGear(event.getOriginal());
			final IPlayerStartingGear newPlayerStartingGear = getPlayerStartingGear(event.getEntityPlayer());

			if (newPlayerStartingGear != null && oldPlayerStartingGear != null) 
			{
				newPlayerStartingGear.setPlayerStartingGear(oldPlayerStartingGear.getPlayerStartingGear());
			}
		}
	}
}