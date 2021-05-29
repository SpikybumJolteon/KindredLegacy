package fuzzyacornindustries.kindredlegacy.client.animation;

public class IdleAnimationClock
{
	private int durationX[];
	private int durationY[];
	private int durationZ[];

	private int clockX;
	private int clockY;
	private int clockZ;
	
	private int numberOfDurationsForX;
	private int numberOfDurationsForY;
	private int numberOfDurationsForZ;

	/* Initiate clock with number of duration phases for each X, Y, Z axis.
	 *    Set zero durations to ignore that particular axis.
	 */
	public IdleAnimationClock(int numberOfDurationsX, int numberOfDurationsY, int numberOfDurationsZ)
	{
		numberOfDurationsForX = numberOfDurationsX;
		numberOfDurationsForY = numberOfDurationsY;
		numberOfDurationsForZ = numberOfDurationsZ;
		
		if(numberOfDurationsForX != 0)
		{
			durationX = new int[numberOfDurationsForX];
		}

		if(numberOfDurationsForY != 0)
		{
			durationY = new int[numberOfDurationsForY];
		}

		if(numberOfDurationsForZ != 0)
		{
			durationZ = new int[numberOfDurationsForZ];
		}
	}

	public void setPhaseDurationX(int phaseNumber, int phaseDuration)
	{
		durationX[phaseNumber] = phaseDuration;
	}

	public void setPhaseDurationY(int phaseNumber, int phaseDuration)
	{
		durationY[phaseNumber] = phaseDuration;
	}

	public void setPhaseDurationZ(int phaseNumber, int phaseDuration)
	{
		durationZ[phaseNumber] = phaseDuration;
	}

	public int getTotalDurationLengthX()
	{
		if(numberOfDurationsForX == 0)
		{
			return 0;
		}
		
		int totalDuration = 0;

		for(int i = 0; i < durationX.length; i++)
		{
			totalDuration += durationX[i];
		}

		return totalDuration;
	}

	public int getTotalDurationLengthY()
	{
		if(numberOfDurationsForY == 0)
		{
			return 0;
		}
		
		int totalDuration = 0;

		for(int i = 0; i < durationY.length; i++)
		{
			totalDuration += durationY[i];
		}

		return totalDuration;
	}

	public int getTotalDurationLengthZ()
	{
		if(numberOfDurationsForZ == 0)
		{
			return 0;
		}
		
		int totalDuration = 0;

		for(int i = 0; i < durationZ.length; i++)
		{
			totalDuration += durationZ[i];
		}

		return totalDuration;
	}
	
	public int getPhaseDurationLengthX(int phaseNumber)
	{
		return durationX[phaseNumber];
	}
	
	public int getPhaseDurationLengthY(int phaseNumber)
	{
		return durationY[phaseNumber];
	}
	
	public int getPhaseDurationLengthZ(int phaseNumber)
	{
		return durationZ[phaseNumber];
	}

	public int getCurrentDurationPhaseX()
	{
		if(numberOfDurationsForX == 0)
		{
			return 0;
		}
		
		for(int i = 0; i < durationX.length; i++)
		{
			if(clockX <= durationX[i])
			{
				return i;
			}
		}

		return 0;
	}

	public int getCurrentDurationPhaseY()
	{
		if(numberOfDurationsForY == 0)
		{
			return 0;
		}
		
		for(int i = 0; i < durationY.length; i++)
		{
			if(clockY <= durationY[i])
			{
				return i;
			}
		}

		return 0;
	}

	public int getCurrentDurationPhaseZ()
	{
		if(numberOfDurationsForZ == 0)
		{
			return 0;
		}
		
		for(int i = 0; i < durationZ.length; i++)
		{
			if(clockZ <= durationZ[i])
			{
				return i;
			}
		}

		return 0;
	}
	
	public float getPhaseDurationCoveredX(int phaseNumber)
	{
		int durationTime = clockX;

		for(int i = 0; i < phaseNumber; i++)
		{
			durationTime -= durationX[i];
		}
		
		if(durationTime <= 0)
		{
			return 0F;
		}
		else if(durationTime >= durationX[phaseNumber])
		{
			return 1F;
		}
		else
		{
			return (float)durationTime / (float)durationX[phaseNumber];
		}
	}
	
	public float getPhaseDurationCoveredY(int phaseNumber)
	{
		int durationTime = clockY;
		
		for(int i = 0; i < phaseNumber; i++)
		{
			durationTime -= durationY[i];
		}
		
		if(durationTime <= 0)
		{
			return 0F;
		}
		else if(durationTime >= durationY[phaseNumber])
		{
			return 1F;
		}
		else
		{
			return (float)durationTime / (float)durationY[phaseNumber];
		}
	}
	
	public float getPhaseDurationCoveredZ(int phaseNumber)
	{
		int durationTime = clockZ;
		
		for(int i = 0; i < phaseNumber; i++)
		{
			durationTime -= durationZ[i];
		}
		
		if(durationTime <= 0)
		{
			return 0F;
		}
		else if(durationTime >= durationZ[phaseNumber])
		{
			return 1F;
		}
		else
		{
			return (float)durationTime / (float)durationZ[phaseNumber];
		}
	}
	
	public void setClockX(int newValue)
	{
		clockX = newValue;
	}
	
	public void setClockY(int newValue)
	{
		clockY = newValue;
	}
	
	public void setClockZ(int newValue)
	{
		clockZ = newValue;
	}
	
	public int getClockX()
	{
		return clockX;
	}
	
	public int getClockY()
	{
		return clockY;
	}
	
	public int getClockZ()
	{
		return clockZ;
	}

	public void incrementClocks()
	{
		clockX++;
		clockY++;
		clockZ++;

		if(clockX >= getTotalDurationLengthX())
		{
			clockX = 0;
		}

		if(clockY >= getTotalDurationLengthY())
		{
			clockY = 0;
		}

		if(clockZ >= getTotalDurationLengthZ())
		{
			clockZ = 0;
		}
	}
}