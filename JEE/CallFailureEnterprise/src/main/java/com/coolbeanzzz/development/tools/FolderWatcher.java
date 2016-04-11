package com.coolbeanzzz.development.tools;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.File;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
@TransactionAttribute (TransactionAttributeType.NOT_SUPPORTED)
public class FolderWatcher{
	
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
	
	private static HashMap<String, Integer> filesInProgress = new HashMap<String,Integer>();;
	
	public int getFileProgress(String filename){
		Integer progress = filesInProgress.get(filename);
		if(progress == null){
			return 0;
		}
		return progress;
	}
	
	/**
	 * Set the directory to monitor for file additions.
	 * Once an excel file is added to the directory it is converted.
	 * 
	 * The comparison tables are added to the database and this data is
	 * compared to the base data JSON array that gets returns from the convert operation.
	 * This is used to create valid data and erroneous data tables which are then
	 * added to the database.
	 * 
	 * @param path Directory to monitor for changes
	 */
	@Asynchronous
	public void watchDirectoryPath(Path path){
		FileSystem fileSystem = path.getFileSystem();
		try(WatchService folderWatchService = fileSystem.newWatchService()){
			path.register(folderWatchService, ENTRY_MODIFY);
	        
	        Runtime.getRuntime().addShutdownHook(new Thread("Close file watcher service") {  
	    		public void run(){
	    			try{
	    				folderWatchService.close();
	    			}catch (IOException e){
	    				System.out.println("IOEXception: " + e.toString()); 
	    			}  
	    		}
	    	});		
	        
			WatchKey key = null;
            while (true){
            	try{
	            	key = folderWatchService.take();
	                Kind<?> kind = null;
	                for (WatchEvent<?> watchEvent : key.pollEvents()) {
	                    kind = watchEvent.kind();
	                    String filename = watchEvent.context().toString();
	                    if (ENTRY_MODIFY == kind && this.getFileProgress(filename)==0) {
	                        File dataset = new File("/home/user1/datasets/" + filename);
	                        if(filename.endsWith(".xls")){
		                    	System.out.println("New File "+filename);
	                        	filesInProgress.put(filename, 0);
	                        	long startTime = System.currentTimeMillis();
	                        	ArrayList<JSONArray> datasetArray = Convert.convert(dataset);
	                        	failureClassService.populateTable(datasetArray.get(2));
	                        	filesInProgress.put(filename, 16);
	                    		System.out.println("1/6 tables complete");
	                        	eventCauseService.populateTable(datasetArray.get(1));
	                        	filesInProgress.put(filename, 33);
	                        	System.out.println("2/6 tables complete");
	                        	mccMncService.populateTable(datasetArray.get(4));
	                        	filesInProgress.put(filename, 50);
	                        	System.out.println("3/6 tables complete");
	                        	ueTableService.populateTable(datasetArray.get(3));
	                        	filesInProgress.put(filename, 65);
	                        	System.out.println("4/6 tables complete");
	                        	
	                        	Collection<Integer> uniqueEventIds = eventCauseService.getAllUniqueEventIds();
	                        	Collection<Integer> uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
	                    		
	                        	Collection<Integer> uniqueFailureCodes = failureClassService.getFailureClassCodes();
	                    		
	                        	Collection<Integer> mccs = mccMncService.getAllUniqueMCCs();
	                        	Collection<Integer> mncs = mccMncService.getAllUniqueMNCs();
	                    		
	                        	Collection<Integer> ueTypes = ueTableService.getUETypes();
	                    		
	                    		CompareData compare = new CompareData(uniqueEventIds, uniqueCauseCodes, uniqueFailureCodes, mccs, mncs, ueTypes);
	                        	
	                    		ArrayList<JSONArray> baseData = compare.compareData(datasetArray.get(0));
	                        	
	                    		System.out.println(baseData.get(0).size());
	                    		
	                        	JSONArray validData = baseData.get(0);
	                        	baseDataService.populateTable(validData);
	                        	filesInProgress.put(filename, 80);
	                        	System.out.println("5/6 tables complete");
	                        	
	                        	JSONArray erroneousData = baseData.get(1);
	                        	erroneousDataService.populateTable(erroneousData);
	                        	filesInProgress.put(filename, 100);
	                        	System.out.println("6/6 tables complete");
	                        	
	                    		System.out.println("Dataset import complete");
	                    		
	                    		System.out.println((System.currentTimeMillis() - startTime) / 1000 + " seconds");
	                        }
	                    }
	                }
            	}catch(IOException e){
        			System.out.println(e.getMessage());
        		}catch (InterruptedException e){
        			System.out.println(e.getMessage());
        		} catch (BiffException e) {
        			System.out.println(e.getMessage());
				}
            	if (!key.reset()) {
                    break;
                } 
            }
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch(ClosedWatchServiceException e){
			System.out.println(e.getMessage());
		}
	}
	
}
