package com.coolbeanzzz.development.tools;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardCopyOption.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.coolbeanzzz.development.services.BaseDataService;
import com.coolbeanzzz.development.services.ErroneousDataService;
import com.coolbeanzzz.development.services.EventCauseService;
import com.coolbeanzzz.development.services.FailureClassService;
import com.coolbeanzzz.development.services.MccMncService;
import com.coolbeanzzz.development.services.UETableService;


@Stateless
public class FolderWatcher{
	private JSONArray erroneousData = new JSONArray();
	private JSONArray validData = new JSONArray();
	
	private JSONObject baseData;
	private boolean dateCheck = true;
	
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
	 * @param path
	 */
	@Asynchronous
	public void watchDirectoryPath(Path path){
		FileSystem fs = path.getFileSystem();
		try (WatchService service = fs.newWatchService()){
			path.register(service, ENTRY_CREATE);
			
            WatchKey key = null;
            while (true){
                key = service.take();
                Kind<?> kind = null;
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    kind = watchEvent.kind();
                    if (ENTRY_CREATE == kind) {
//                        Path newPath = ((WatchEvent<Path>) watchEvent).context();
                        Path newPath = new File("/home/user1/testing2/" + watchEvent.context()).toPath();
                        if(newPath.getFileName().toString().endsWith(".xls")){
                        	Convert convert = new Convert();
                        	convert.setInputFile(newPath.toAbsolutePath().toString());
                        	convert.convert();
                        	
                        	
                        	failureClassService.populateTable(new File("/home/user1/software/jboss/bin/Failure Class Table.json"));
                    		eventCauseService.populateTable(new File("/home/user1/software/jboss/bin/Event-Cause Table.json"));
                    		mccMncService.populateTable(new File("/home/user1/software/jboss/bin/MCC - MNC Table.json"));
                    		ueTableService.populateTable(new File("/home/user1/software/jboss/bin/UE Table.json"));
                    		
                    		uniqueEventIds = eventCauseService.getAllUniqueEventIds();
                    		uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
                    		
                    		uniqueFailureCodes = failureClassService.getFailureClassCodes();
                    		
                    		mccs = mccMncService.getAllUniqueMCCs();
                    		mncs = mccMncService.getAllUniqueMNCs();
                    		
                    		ueTypes = ueTableService.getUETypes();
                    		
                    		compareData();
                    		baseDataService.populateTable(new File("/home/user1/software/jboss/bin/validData.json"));
                    		erroneousDataService.populateTable(new File("/home/user1/software/jboss/bin/erroneousData.json"));
                    		
                    		Files.copy(newPath, new File("/home/user1/software/jboss/bin/" + newPath.getFileName()).toPath(), REPLACE_EXISTING);
//                    		Files.delete(new File("/home/user1/testing/" + newPath).toPath());
                        }
                    }
                }

                if (!key.reset()) {
                    break;
                }
            }
		}catch(IOException e){
			e.printStackTrace();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Compare imported base data table against the reference tables
	 * to split it into erroneous data and valid data.
	 */
	private void compareData(){

		JSONParser parser = new JSONParser();
		
		int eventId;
		int causeCode;
		int failureCode;
		int mcc;
		int mnc;
		int ueType;
		
		String dateTime;

		try {

			Object obj = parser.parse(new FileReader(new File("/home/user1/software/jboss/bin/Base Data.json")));

			JSONArray rows = (JSONArray) obj;
			
			Iterator<?> iterator = rows.iterator();
			
			while (iterator.hasNext()) {

				baseData = (JSONObject) iterator.next();
				
				dateTime = baseData.get("Date / Time").toString();
				
				dateFormatter(dateTime);
				
				eventId = checkInt(baseData.get("Event Id").toString());
				causeCode = checkInt(baseData.get("Cause Code").toString());
				failureCode = checkInt(baseData.get("Failure Class").toString());
				mcc = checkInt(baseData.get("Market").toString());
				mnc = checkInt(baseData.get("Operator").toString());
				ueType = checkInt(baseData.get("UE Type").toString());
				
				if(!uniqueEventIds.contains(eventId) || !uniqueCauseCodes.contains(causeCode) ||
						!uniqueFailureCodes.contains(failureCode) || !mccs.contains(mcc) || !mncs.contains(mnc)||
						!ueTypes.contains(ueType) || dateCheck == false){
					erroneousData.add(baseData);
				}
				else{
					validData.add(baseData);
				}
			}
			
			FileWriter fwValid = new FileWriter(new File("validData.json"));
			Iterator<?> iteratorValid = validData.iterator();
			fwValid.write("[");
			while (iteratorValid.hasNext()) {
				JSONObject validObject = (JSONObject) iteratorValid.next();
				fwValid.write(validObject.toJSONString() + ",");
			}
			fwValid.write("]");
			fwValid.close();
			
			FileWriter fwErroneous = new FileWriter(new File("erroneousData.json"));
			Iterator<?> iteratorErroneous = erroneousData.iterator();
			fwErroneous.write("[");
			while (iteratorErroneous.hasNext()) {
				JSONObject erroneousObject = (JSONObject) iteratorErroneous.next();
				fwErroneous.write(erroneousObject.toJSONString() + ",");
			}
			fwErroneous.write("]");
			fwErroneous.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void dateFormatter(String dateTime) {
		
		Calendar cal = Calendar.getInstance();
		
		String output="";
		
		SimpleDateFormat inFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date date = new Date();
		inFormat.setLenient(false);
		
		try {
			output = outFormat.format(inFormat.parse(dateTime));
			date = outFormat.parse(output);
			
			if(cal.getTime().after(date)){
				baseData.put("Date / Time", output);
				dateCheck = true;
			}
			else{
				dateCheck = false;
			}
			
		} catch (java.text.ParseException e) {
			dateCheck = false;
		}
		
	}

	private int checkInt(String test){
		if(test.contains("null")){
			return -1;
		}
		else{
			return Integer.parseInt(test);
		}
	}
	
}
