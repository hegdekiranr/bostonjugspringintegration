package com.hegdekiran.service;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

/**
 * @author Kiran Hegde
 * 
 */
@MessageEndpoint
public class PrintOrders {
	
	  @ServiceActivator
	  public String upperCase(String message) {
	    return message.toUpperCase();
	  }
	
}
