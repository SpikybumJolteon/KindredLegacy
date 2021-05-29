package fuzzyacornindustries.kindredlegacy.common.network;

import java.util.function.Supplier;

import fuzzyacornindustries.kindredlegacy.common.recipe.ModRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Received on: CLIENT
 * Sent by server when recipes are reloaded; clear our local cache of machine recipes
 * 
 * Based on PneumaticCraft's recipe code
 */
public class PacketClearRecipeCache 
{
    public PacketClearRecipeCache() {}

    public void toBytes(PacketBuffer buf) {}

    public PacketClearRecipeCache(PacketBuffer buffer) {}

    public void handle(Supplier<NetworkEvent.Context> ctx) 
    {
        ctx.get().setPacketHandled(true);
        ctx.get().enqueueWork(ModRecipeType::clearCachedRecipes);
    }
}