package fuzzyacornindustries.kindredlegacy.capabilities;

import fuzzyacornindustries.kindredlegacy.item.KindredLegacyItems;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerStartingGear implements IPlayerStartingGear
{
	private final EntityPlayer player;

	/** Set to true when the player receives the bonus starting gear */
	private byte receivedGear = 0;

	public PlayerStartingGear(EntityPlayer player) 
	{
		this.player = player;
	}

	@Override
	public byte verifyStartingGear()
	{
		if ((receivedGear & 1) == 0 && KindredLegacyItems.grantBonusGear(player)) 
		{
			setPlayerStartingGear((byte)1);
		}
		
		return getPlayerStartingGear();
	}

	@Override
	public byte getPlayerStartingGear()
	{
		return receivedGear;
	}

	@Override
	public void setPlayerStartingGear(byte startingGearCheck) 
	{
		receivedGear = startingGearCheck;
	}
}