package com.coolbeanzzz.development.tools;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jxl.read.biff.BiffException;

import org.json.simple.JSONArray;

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
	 * compared to the base data JSON array that gets returns from the convert operation.
	 * This is used to create valid data and erroneous data tables which are then
	 * added to the database.
	 * 
	 * The dataset is copied to a savedDatasets directory. The dataset and json directories
	 * are then cleared
	 * 
	 * @param path Directory to monitor for changes
	 * @throws BiffException 
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
                        	String inputFile = newPath.toAbsolutePath().toString();
                        	ArrayList<JSONArray> datasetArray = Convert.convert(inputFile);
                        	
                        	failureClassService.populateTable(datasetArray.get(2));
                    		System.out.println("1/6 tables complete");
                        	eventCauseService.populateTable(datasetArray.get(1));
                        	System.out.println("2/6 tables complete");
                        	mccMncService.populateTable(datasetArray.get(4));
                        	System.out.println("3/6 tables complete");
                        	ueTableService.populateTable(datasetArray.get(3));
                        	System.out.println("4/6 tables complete");
                        	
                    		uniqueEventIds = eventCauseService.getAllUniqueEventIds();
                    		uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
                    		
                    		uniqueFailureCodes = failureClassService.getFailureClassCodes();
                    		
                    		mccs = mccMncService.getAllUniqueMCCs();
                    		mncs = mccMncService.getAllUniqueMNCs();
                    		
                    		ueTypes = ueTableService.getUETypes();
                    		
                    		CompareData compare = new CompareData(uniqueEventIds, uniqueCauseCodes, uniqueFailureCodes, mccs, mncs, ueTypes);
                        	
                    		ArrayList<JSONArray> baseData = compare.compareData(datasetArray.get(0));
                        	
                        	JSONArray validData = baseData.get(0);
                        	baseDataService.populateTable(validData);
                        	System.out.println("5/6 tables complete");
                        	
                        	JSONArray erroneousData = baseData.get(1);
                        	erroneousDataService.populateTable(erroneousData);
                        	System.out.println("6/6 tables complete");
                        	
                    		System.out.println("Dataset import complete");
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
