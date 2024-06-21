package demo.cafe.common;

import org.apache.log4j.Logger;

public class logging {

	public static Logger logger = Logger.getLogger(logging.class);

	public static void LoggerDebug(String message) {
		logger.debug(message);
	}

	public static void LoggerInfo(String message) {
		logger.info(message);
	}

	public static void LoggerWarn(String message) {
		logger.warn(message);
	}

	public static void LoggerError(String message) {
		logger.error(message);
	}

	public static void LoggerFatal(String message) {
		logger.fatal(message);
	}
}