package fuzzyacornindustries.kindredlegacy.common.network;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * MineChess
 *
 * @author MineMaarten
 *         www.minemaarten.com
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public abstract class LocationIntPacket 
{
	public static final double PACKET_UPDATE_DISTANCE = 64D;//maximum client distance before clients aren't being sent anymore from the server.
	
    protected BlockPos pos;

    public LocationIntPacket() {}

    public LocationIntPacket(PacketBuffer buffer) 
    {
        pos = buffer.readBlockPos();
    }

    public LocationIntPacket(BlockPos pos) 
    {
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) 
    {
        buf.writeBlockPos(pos);
    }

    PacketDistributor.TargetPoint getTargetPoint(World world) 
    {
        return getTargetPoint(world, PACKET_UPDATE_DISTANCE);
    }

    PacketDistributor.TargetPoint getTargetPoint(World world, double updateDistance) 
    {
        return new PacketDistributor.TargetPoint(pos.getX(), pos.getY(), pos.getZ(), updateDistance, world.getDimension().getType());
    }
}