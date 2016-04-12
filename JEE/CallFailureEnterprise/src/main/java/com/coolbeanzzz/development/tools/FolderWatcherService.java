package com.coolbeanzzz.development.tools;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class FolderWatcherService {
	
	@Inject
	private FolderWatcher folderWatcher;
	
	@PostConstruct
    public void init(){
		File dir = new File("/home/user1/datasets/");
		folderWatcher.watchDirectoryPath(dir.toPath());
	}
	
	public int getFileProgress(String filename){
		return folderWatcher.getFileProgress(filename);
	}
}
