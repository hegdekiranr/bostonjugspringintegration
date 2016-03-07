package com.hegdekiran.service;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * @author Kiran Hegde
 * 
 */
public class FileHandler {
	
	private Logger logger = Logger.getLogger(FileHandler.class);
	
	public File process(File file) throws Exception{	
		
		logger.info("Found File: " + file);
		return file;
	}
}
