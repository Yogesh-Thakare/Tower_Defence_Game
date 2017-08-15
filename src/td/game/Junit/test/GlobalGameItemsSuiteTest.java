package td.game.Junit.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class is used as Global Game suite test
 * @author Team3
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ MoneyCheckerTest.class,LifeCheckerTest.class,GameControllerTest.class })
public class GlobalGameItemsSuiteTest 
{
}
