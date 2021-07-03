package fuzzyacornindustries.kindredlegacy.common.entity.mob.ai;

import fuzzyacornindustries.kindredlegacy.common.entity.mob.tamable.OkamiPokemon;
import fuzzyacornindustries.kindredlegacy.lib.action.LibraryOkamiPokemonAttackID;

public class OkamiGlaiveSlashReverseGoal extends OkamiGlaiveSlashGoal
{
	/*
	 * For some reason, the tick read in the model class lags behind the AI tick.
	 * Only known fix for now is to make the attack duration longer than end animation.
	 */
	public int attackDuration = 9;

	public OkamiGlaiveSlashReverseGoal(OkamiPokemon entityOkamiPokemon, float range) 
	{
		super(entityOkamiPokemon, range);
	}

	@Override
	public int getAnimationID() 
	{
		return LibraryOkamiPokemonAttackID.GLAIVE_SLASH_REVERSE;
	}
}