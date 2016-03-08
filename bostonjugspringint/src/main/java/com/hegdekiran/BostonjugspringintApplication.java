package com.hegdekiran;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.DirectFieldAccessor;
/**
 * @author Kiran Hegde
 * Inspired by many Spring Integration books, samples & blogs.
 * 
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@IntegrationComponentScan
@EnableAutoConfiguration
@ImportResource({"demoAppContext.xml"})
public class BostonjugspringintApplication {
	
	private static Logger logger = Logger.getLogger(BostonjugspringintApplication.class);
	private static int fileCount = 5;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplication(BostonjugspringintApplication.class,"demoAppContext.xml").run(args);
		//helloWorldOnChannels(context);
		//messageBuilder(context);
		
		try {
			fileTransformers(context);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @param context
	 */
	private static void helloWorldOnChannels(ConfigurableApplicationContext context) {
		logger.info("----------Configuring Input & Outout Channels--------------");
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);
		inputChannel.send(new GenericMessage<String>("World"));
		logger.info("==> HelloWorldDemo for Boston JUG: " + outputChannel.receive(0).getPayload());
	}
	
	private static void messageBuilder(ConfigurableApplicationContext context) {
		logger.info("----------Configuring Input & Outout Channels--------------");
		MessageChannel inputChannel = context.getBean("messageBuilderInputChannel", MessageChannel.class);
		Message<String> message = MessageBuilder.withPayload("Created using Message Builder").build();
		inputChannel.send(message);
		//Using Message Builder we can add Headers, replyChannel, Priority, Sequence etc.
	}
	
	public static void fileTransformers(ConfigurableApplicationContext context) throws Exception{
		logger.info("Populating directory with files");
		for (int i = 1; i <= fileCount; i++) {
			File file = new File("C:/myWorkJUG/sifilefolder/infolder/order_" + i + ".txt");
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			if (i % 2 == 0 ){
				out.write("staples " + i);
			}else{
				out.write("paper " + i);
			}
		    out.close();
		}
		logger.info("Populated directory with files");
		PollableChannel filesOutChannel = context.getBean("filesOutChannel", PollableChannel.class);
		String fileName = "";
		File readFile;
		for (int i = 0; i < fileCount; i++) {
			readFile = (File) filesOutChannel.receive(10000).getPayload();
 			logger.info("Files in directory =====> "+readFile );
 			BufferedReader read = new BufferedReader(new FileReader(readFile));
 			logger.info("Reading File Contexts ============>"+read.readLine());
		}
		logger.info("=======================================Stopping Content===============================================");
		context.stop();
	}
	
}

