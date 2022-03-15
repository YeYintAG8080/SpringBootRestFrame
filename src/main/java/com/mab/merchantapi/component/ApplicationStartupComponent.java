package com.mab.merchantapi.component;

import java.io.File;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupComponent {
	
	private static Logger log = LoggerFactory.getLogger(ApplicationStartupComponent.class);
	
	@Value("${app.upload.dir}")
	public String uploadDir;
	
	ApplicationContext context;
	
	@PostConstruct
	private void onStartUp() {
		try {
			createFileUploadDir();
		}catch(Exception e) {
			log.error("Intercom ServerOnStartUpException>>>>>>", e);
			e.printStackTrace();
		}
	}
	
	private void createFileUploadDir() throws Exception{
		File parentfolder = new File(uploadDir+"Merchant Service"+File.separator);
		Boolean success=true;
		if (!parentfolder.exists()) {
			success=parentfolder.mkdirs();
		}
		if(success)
			System.out.println(parentfolder.getAbsolutePath()+ " was successfully created");
		else
			System.out.println(parentfolder.getAbsolutePath()+ " was failed to created");
	}
	
}
