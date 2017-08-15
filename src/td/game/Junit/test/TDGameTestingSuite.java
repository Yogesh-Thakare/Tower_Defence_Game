package td.game.Junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is a test suite class for all tests suits
 * @author Team 3
 */
@RunWith(Suite.class)
@SuiteClasses({ GlobalGameItemsSuiteTest.class, LocationSuiteTest.class,
		MapServiceSuiteTest.class, SpecialEffectsSuiteTest.class,
		TowersOperationsSuiteTest.class,CritterWaveSuiteTest.class })
public class TDGameTestingSuite 
{
}
