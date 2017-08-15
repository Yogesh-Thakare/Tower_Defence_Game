package td.game.model;

import td.game.constants.MapConstants;

/**
 * <b>This class is used for the type of critters that can not decide by themselves and by game strategy and AI Algorithms</b>
 * @author Team3
 */
@SuppressWarnings("serial")
public class NormalCritter extends Critter 
{
	CritterNormalMove normalMove;
	
	/**
	 * This method is used to instantiate a new foolish critter.
	 * @param initalPosition initial position 
	 * @param path correct phase in the map
	 */
	public NormalCritter(Location initalPosition, Location[] path) 
	{
		this.normalMove = new CritterNormalMove(path, initalPosition);
		setMovingBehaviour(normalMove);
		this.description = "NormalCritter";
		setPath(path);
		this.setResistance(1);
		this.setWaveNumber(1);
		this.setLife(100);
	}
	
	/**
	 * This method is used to return regular critter movement
	 * @return regularMove
	 */
	public CritterNormalMove getRegularMove() 
	{
		return normalMove;
	}

	/**
	 * <b>This method is used return life for the tower </b>
	 */
	@Override
	public double returnTowerHealth() 
	{
		// TODO Auto-generated method stub
		return 5;
	}
	
	/**
	 * <b>This method is used to move method will be used in the second build to display critters and it is not complete yet</b>
	 */
	@Override
	public String display() 
	{
		return MapConstants.NORMAL_CRITTER_IMG;
		
	}
}