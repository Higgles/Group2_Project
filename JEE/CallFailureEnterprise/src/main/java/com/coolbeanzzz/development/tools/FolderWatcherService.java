package com.coolbeanzzz.development.tools;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class FolderWatcherService {
	
	@EJB
	private FolderWatcher folderWatcher;
	
	@PostConstruct
    public void init(){
		File dir = new File("/home/user1/datasets/");
		folderWatcher.watchDirectoryPath(dir.toPath());
	}
	
}
