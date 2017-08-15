package td.game.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Provides infrastructure logging services
 * @author Team3
 */
public class Log4jLogger 
{
	private static final Logger logger = LogManager.getLogger(Log4jLogger.class.getName());
	
	/**
	 * This function is used to log error
	 * @param message object
	 * @param t exception
	 */
	public void writer(Object message, Throwable t) 
	{
		logger.error(message, t);
	}
}
