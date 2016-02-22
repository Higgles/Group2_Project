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
import java.util.ArrayList;
import java.util.Collection;
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
	
	@Inject
	private FailureClassService failureClassService;
	
	@Inject
	private EventCauseService eventCauseService;
	
	@Inject
	private MccMncService mccMncService;
	
	@Inject
	private UETableService ueTableService;
	
	@Inject
	private ErroneousDataService erroneousService;
	
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
					
					return Response.status(200).entity("Dataset uploaded: " + fileName).build();
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
		
//		failureClassService.populateTable(new File("/home/user1/software/jboss/bin/Failure Class Table.json"));
//		eventCauseService.populateTable(new File("/home/user1/software/jboss/bin/Event-Cause Table.json"));
//		mccMncService.populateTable(new File("/home/user1/software/jboss/bin/MCC - MNC Table.json"));
//		ueTableService.populateTable(new File("/home/user1/software/jboss/bin/UE Table.json"));
		
		Collection<Integer> uniqueEventIds = eventCauseService.getAllUniqueEventIds();
		Collection<Integer> uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
		
		Collection<Integer> uniqueFailureCodes = failureClassService.getFailureClasseCodes();
		
		Collection<Integer> mccs = mccMncService.getMCCs();
		Collection<Integer> mncs = mccMncService.getMNCs();
		
		Collection<Integer> ueTypes = ueTableService.getUETypes();

		compareData(uniqueEventIds, uniqueCauseCodes, uniqueFailureCodes, mccs, mncs, ueTypes);
		
//		baseData.populateTable(validData);
//		erroneousService.populateErroneousDataTable(erroneousData);
		
//		System.out.println(erroneousService.getCatalog().size());
	}
	
	private void compareData(Collection<Integer> uniqueEventIds, Collection<Integer> uniqueCauseCodes,
			Collection<Integer> uniqueFailureCodes, Collection<Integer> mccs,
			Collection<Integer> mncs, Collection<Integer> ueTypes){

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader(new File("/home/user1/software/jboss/bin/Base Data.json")));

			JSONArray rows = (JSONArray) obj;
			
			Iterator<Object> iterator = rows.iterator();
			
			while (iterator.hasNext()) {

				JSONObject baseData = (JSONObject) iterator.next();
				
				int eventId = checkInt(baseData.get("Event Id").toString());
				int causeCode = checkInt(baseData.get("Cause Code").toString());
				int failureCode = checkInt(baseData.get("Failure Class").toString());
				int mcc = checkInt(baseData.get("Market").toString());
				int mnc = checkInt(baseData.get("Operator").toString());
				int ueType = checkInt(baseData.get("UE Type").toString());
				
				
				if(!uniqueEventIds.contains(eventId) || !uniqueCauseCodes.contains(causeCode) ||
						!uniqueFailureCodes.contains(failureCode) || !mccs.contains(mcc) || !mncs.contains(mnc)||
						!ueTypes.contains(ueType)){
					erroneousData.add(baseData);
				}
				else{
					validData.add(baseData);
				}
			}
			
			System.out.println(validData.size());
			System.out.println(erroneousData.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private int checkInt(String test){
		if(test.contains("(null")){
			return -1;
		}
		else{
			return Integer.parseInt(test);
		}
	}
}
