package com.coolbeanzzz.development.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.dao.jpa.JPAFailureClassDAO;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.UETable;
import com.coolbeanzzz.development.tools.Convert;

@Path("/file")
public class FileUploadService {
	
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
	 * Upload the user's selected file to the server and return the file location and
	 * success message
	 * 
	 * @param input file details from upload form input on the html page
	 * @return
	 * @throws IOException
	 */
	@POST
	@Path("/upload")
	@Consumes("multipart/form-data")
	public Response uploadFile(MultipartFormDataInput input) throws IOException {

		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		
		List<InputPart> inputParts = uploadForm.get("uploadFile");

		for (InputPart inputPart : inputParts) {
			try {
				MultivaluedMap<String, String> header = inputPart.getHeaders();
				String fileName = getFileName(header);
				if(fileName.contains(".xls")){
					InputStream inputStream = inputPart.getBody(InputStream.class, null);
	
					byte[] bytes = IOUtils.toByteArray(inputStream);
					
					writeFile(bytes, fileName);
					
					java.net.URI location = new java.net.URI("http://localhost:8080/CallFailureEnterprise/erroneous.html");
					return Response.temporaryRedirect(location).build();
//					return Response.status(200).entity("Dataset uploaded: " + fileName + 
//							"<p><a href='http://localhost:8080/CallFailureEnterprise/erroneous.html'</a>View erroneous data" +
//							"<p><a href='http://localhost:8080/CallFailureEnterprise/valid.html'</a>View valid data").build();
				}
				else{
					return null;
//					return Response.status(200).entity("Please return to the upload page and provide an xls file").build();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Get filename from uploaded file
	 * 
	 * @param header
	 * @return
	 */
	private String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	/**
	 * Write file being uploaded to the server directory
	 * Conversion of the xls file sheets to json files is then run
	 * 
	 * @param content the content of the file to be uploaded and written
	 * @param filename name of the uploaded file
	 * @throws IOException
	 */
	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			System.out.println("not exist> " + file.getAbsolutePath());
			file.createNewFile();
		}
		FileOutputStream fileOutput = new FileOutputStream(file);
		fileOutput.write(content);
		fileOutput.flush();
		fileOutput.close();
		
		Convert convert = new Convert();
		convert.setInputFile("/home/user1/software/jboss/bin/" + filename);
		convert.convert();
		
		failureClassService.populateTable(new File("/home/user1/software/jboss/bin/Failure Class Table.json"));
		eventCauseService.populateTable(new File("/home/user1/software/jboss/bin/Event-Cause Table.json"));
		mccMncService.populateTable(new File("/home/user1/software/jboss/bin/MCC - MNC Table.json"));
		ueTableService.populateTable(new File("/home/user1/software/jboss/bin/UE Table.json"));
		
		uniqueEventIds = eventCauseService.getAllUniqueEventIds();
		uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
		
		uniqueFailureCodes = failureClassService.getFailureClasseCodes();
		
		mccs = mccMncService.getMCCs();
		mncs = mccMncService.getMNCs();
		
		ueTypes = ueTableService.getUETypes();

		compareData();
		
		baseDataService.populateBaseDataTable(new File("/home/user1/software/jboss/bin/validData.json"));
		erroneousDataService.populateErroneousDataTable(new File("/home/user1/software/jboss/bin/erroneousData.json"));
		
//		System.out.println(baseDataService.getCatalog().size());
//		System.out.println(erroneousDataService.getCatalog().size());
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
			
			Iterator<Object> iterator = rows.iterator();
			
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
			Iterator<Object> iteratorValid = validData.iterator();
			fwValid.write("[");
			while (iteratorValid.hasNext()) {
				JSONObject validObject = (JSONObject) iteratorValid.next();
				fwValid.write(validObject.toJSONString() + ",");
			}
			fwValid.write("]");
			fwValid.close();
			
			FileWriter fwErroneous = new FileWriter(new File("erroneousData.json"));
			Iterator<Object> iteratorErroneous = erroneousData.iterator();
			fwErroneous.write("[");
			while (iteratorErroneous.hasNext()) {
				JSONObject erroneousObject = (JSONObject) iteratorErroneous.next();
				fwErroneous.write(erroneousObject.toJSONString() + ",");
			}
			fwErroneous.write("]");
			fwErroneous.close();
			
			
//			System.out.println(validData.size());
//			System.out.println(erroneousData.size());

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
