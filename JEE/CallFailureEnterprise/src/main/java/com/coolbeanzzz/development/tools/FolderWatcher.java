package com.coolbeanzzz.development.tools;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardCopyOption.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Collection;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;

import com.coolbeanzzz.development.services.BaseDataService;
import com.coolbeanzzz.development.services.ErroneousDataService;
import com.coolbeanzzz.development.services.EventCauseService;
import com.coolbeanzzz.development.services.FailureClassService;
import com.coolbeanzzz.development.services.MccMncService;
import com.coolbeanzzz.development.services.UETableService;


@Stateless
public class FolderWatcher{
	
	private Collection<Integer> uniqueEventIds;
	private Collection<Integer> uniqueCauseCodes;
	
	private Collection<Integer> uniqueFailureCodes;
	
	private Collection<Integer> mccs;
	private Collection<Integer> mncs;
	
	private Collection<Integer> ueTypes;
	
	@Inject
	private FailureClassService failureClassService;
	
	@Inject
	private EventCauseService eventCauseService;
	
	@Inject
	private MccMncService mccMncService;
	
	@Inject
	private UETableService ueTableService;
	
	@Inject
	private BaseDataService baseDataService;
	
	@Inject
	private ErroneousDataService erroneousDataService;
	
	/**
	 * Set the directory to monitor for file additions.
	 * Once an excel file is added to the directory it is converted.
	 * 
	 * The comparison tables are added to the database and this data is
	 * compared to base data to create valid data and erroneous data tables
	 * which are then added to the database.
	 * 
	 * The dataset is then copied from the monitored directory to the upload
	 * directory
	 * 
	 * @param path Directory to monitor for changes
	 */
	@Asynchronous
	public void watchDirectoryPath(Path path){
		FileSystem fileSystem = path.getFileSystem();
		try(WatchService folderWatchService = fileSystem.newWatchService()){
			path.register(folderWatchService, ENTRY_CREATE);
            WatchKey key = null;
            while (true){
                key = folderWatchService.take();
                Kind<?> kind = null;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    if (ENTRY_CREATE == kind) {
                        Path newPath = new File("/home/user1/datasets/" + watchEvent.context()).toPath();
                        if(newPath.getFileName().toString().endsWith(".xls")){
                        	Convert convert = new Convert();
                        	convert.setInputFile(newPath.toAbsolutePath().toString());
                        	convert.convert();
                        	
                        	failureClassService.populateTable(new File("/home/user1/json/Failure Class Table.json"));
                    		eventCauseService.populateTable(new File("/home/user1/json/Event-Cause Table.json"));
                    		mccMncService.populateTable(new File("/home/user1/json/MCC - MNC Table.json"));
                    		ueTableService.populateTable(new File("/home/user1/json/UE Table.json"));
                    		
                    		uniqueEventIds = eventCauseService.getAllUniqueEventIds();
                    		uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
                    		
                    		uniqueFailureCodes = failureClassService.getFailureClassCodes();
                    		
                    		mccs = mccMncService.getAllUniqueMCCs();
                    		mncs = mccMncService.getAllUniqueMNCs();
                    		
                    		ueTypes = ueTableService.getUETypes();
                    		
                    		CompareData compare = new CompareData(uniqueEventIds, uniqueCauseCodes, uniqueFailureCodes, mccs, mncs, ueTypes);
                        	compare.compareData();
                        	
                    		baseDataService.populateTable(new File("/home/user1/json/validData.json"));
                    		erroneousDataService.populateTable(new File("/home/user1/json/erroneousData.json"));                        	
                    		
                    		Files.copy(newPath, new File("/home/user1/savedDatasets/" + newPath.getFileName()).toPath(), REPLACE_EXISTING);
                    		File jsonDir = new File("/home/user1/json");
                    		File uploadDir = new File("/home/user1/datasets");
                    		FileUtils.cleanDirectory(jsonDir);
                    		FileUtils.cleanDirectory(uploadDir);
                    		
                        }
                    }
                }
                
                Runtime.getRuntime().addShutdownHook(new Thread("Close file watcher service") {  
        			public void run(){
        				try{
        					folderWatchService.close();  
        				}catch (IOException e){
        					System.out.println("IOEXception: " + e.toString()); 
        				}  
        			}
        		});

                if (!key.reset()) {
                    break;
                }
            }
		}catch(IOException e){
			System.out.println("IOEXception: " + e.toString());
		}catch (InterruptedException e){
			System.out.println("InterruptException: " + e.toString());
		}
	}
	
}
