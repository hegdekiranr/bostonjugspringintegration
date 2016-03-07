package com.hegdekiran;

import org.apache.log4j.Logger;

/**
 * @author Kiran Hegde
 *
 */
public class ServiceActivator implements ServiceActivatorInterface {

	private static Logger logger = Logger.getLogger(ServiceActivator.class);

	@Override
	public void messageBuilder(String message) {
		logger.info("Service Activator : HelloBostonJUGService got this message ==>"+ message );
	}
}
