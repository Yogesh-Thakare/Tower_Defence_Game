package td.game.Junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class is the Critter Wave Suite Test
 * @author Team3
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ CritterFeaturesTest.class,WaveFactoryTest.class })
public class CritterWaveSuiteTest 
{
}
