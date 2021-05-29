package fuzzyacornindustries.kindredlegacy.common.tileentity;

import java.util.Random;

import fuzzyacornindustries.kindredlegacy.client.animation.IAnimatedEntity;
import fuzzyacornindustries.kindredlegacy.client.animation.IdleAnimationClock;
import fuzzyacornindustries.kindredlegacy.common.core.ModTileEntities;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class XelNagaPylonTileEntity extends TileEntity implements IAnimatedEntity, ITickableTileEntity
{
	private IdleAnimationClock rotationAnimationClock;
	private IdleAnimationClock verticalShiftIdleAnimationClock[] = new IdleAnimationClock[3];

	public static final int actionIDNone = 0;

	private int animationID;
	private int animationTick;

	public XelNagaPylonTileEntity()
	{
		super(ModTileEntities.XELNAGA_PYLON.get());

		setClockDefaults();

		animationID = actionIDNone;
		animationTick = 0;
	}

	@Override
	public void tick() 
	{
		if(!this.getWorld().isBlockPowered(pos))
			incrementPartClocks();
	}

	/************************************
	 * Animation dependent code follows.*
	 ************************************/
	public void incrementPartClocks()
	{
		rotationAnimationClock.incrementClocks();

		for(int i = 0; i < verticalShiftIdleAnimationClock.length; i++)
		{
			verticalShiftIdleAnimationClock[i].incrementClocks();
		}
	}

	public IdleAnimationClock getIdleAnimationClockRotation()
	{
		return rotationAnimationClock;
	}

	public IdleAnimationClock getIdleAnimationClockVerticalShift(int partNumber) 
	{
		return verticalShiftIdleAnimationClock[partNumber];
	}

	public void setClockDefaults()
	{
		setRotationClockDefaults();
		setVerticalShiftDefaults();
	}

	public void setRotationClockDefaults()
	{
		Random rand = new Random();

		int startingClockY = rand.nextInt(100);

		rotationAnimationClock = new IdleAnimationClock(0, 1, 0);

		rotationAnimationClock.setPhaseDurationY(0, 100);

		while(startingClockY > rotationAnimationClock.getTotalDurationLengthY())
		{
			startingClockY -= rotationAnimationClock.getTotalDurationLengthY();
		}

		rotationAnimationClock.setClockY(startingClockY);
	}

	public void setVerticalShiftDefaults()
	{
		Random rand = new Random();

		int randomInt = rand.nextInt(100);

		for(int i = 0; i < verticalShiftIdleAnimationClock.length; i++)
		{
			verticalShiftIdleAnimationClock[i] = new IdleAnimationClock(1, 1, 0);

			verticalShiftIdleAnimationClock[i].setPhaseDurationX(0, 80);
			verticalShiftIdleAnimationClock[i].setPhaseDurationY(0, 80);

			float frequencyY = 0.35F;

			int startingClockX = (int)(((float)(verticalShiftIdleAnimationClock.length - i) / (float)verticalShiftIdleAnimationClock.length) * (float)verticalShiftIdleAnimationClock[i].getTotalDurationLengthX() * frequencyY) + randomInt;
			int startingClockY = (int)(((float)(verticalShiftIdleAnimationClock.length - i) / (float)verticalShiftIdleAnimationClock.length) * (float)verticalShiftIdleAnimationClock[i].getTotalDurationLengthY() * frequencyY) + randomInt;

			while(startingClockX > verticalShiftIdleAnimationClock[i].getTotalDurationLengthX())
			{
				startingClockX -= verticalShiftIdleAnimationClock[i].getTotalDurationLengthX();
			}

			while(startingClockY > verticalShiftIdleAnimationClock[i].getTotalDurationLengthY())
			{
				startingClockY -= verticalShiftIdleAnimationClock[i].getTotalDurationLengthY();
			}

			verticalShiftIdleAnimationClock[i].setClockX(startingClockX);
			verticalShiftIdleAnimationClock[i].setClockY(startingClockY);
		}
	}

	@Override
	public void setAnimationID(int id) 
	{
		animationID = id;
	}

	@Override
	public void setAnimationTick(int tick) 
	{
		animationTick = tick;	
	}

	@Override
	public int getAnimationID() 
	{
		return animationID;
	}

	@Override
	public int getAnimationTick() 
	{
		return animationTick;
	}
}