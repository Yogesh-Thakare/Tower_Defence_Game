package td.game.Junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This is a test suite for tower operations
 * @author Team 3
 */
@RunWith(Suite.class)
@SuiteClasses({ TowerCostCalculatorTest.class, TowerFeaturesTest.class, TargettingStrategiesTest.class })
public class TowersOperationsSuiteTest 
{
}
