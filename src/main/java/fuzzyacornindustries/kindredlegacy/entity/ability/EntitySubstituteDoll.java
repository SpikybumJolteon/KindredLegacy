package fuzzyacornindustries.kindredlegacy.entity.ability;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntitySubstituteDoll extends EntityAnimal
{
	static String mobName = "substitute_doll";

	public EntitySubstituteDoll(World par1World)
	{
		super(par1World);
		this.setSize(0.8F, 0.9F);

		this.experienceValue = 0;
	}

	public static String getMobName()
	{
		return mobName;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
	}

	@Override
    public void fall(float distance, float damageMultiplier) {}

	@Override
	protected int decreaseAirSupply(int currentAirSupply)
	{
		return currentAirSupply;
	}

	@Override
	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) 
	{
		return null;
	}
}