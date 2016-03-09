/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.coolbeanzzz.development.tools.CompareData;
import com.coolbeanzzz.development.tools.Convert;

@Path("/file")
public class FileUploadService {
	
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
	 * @return Rest response
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
					
					java.net.URI location = new java.net.URI("http://localhost:8080/CallFailureEnterprise/uploadComplete.html");
					return Response.temporaryRedirect(location).build();
				}
				else{
					return null;
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
	 * @return filename
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
		
		uniqueFailureCodes = failureClassService.getFailureClassCodes();
		
		mccs = mccMncService.getAllUniqueMCCs();
		mncs = mccMncService.getAllUniqueMNCs();
		
		ueTypes = ueTableService.getUETypes();
		
		CompareData compare = new CompareData(uniqueEventIds, uniqueCauseCodes, uniqueFailureCodes, mccs, mncs, ueTypes);
		compare.compareData();
		
		baseDataService.populateTable(new File("/home/user1/software/jboss/bin/validData.json"));
		erroneousDataService.populateTable(new File("/home/user1/software/jboss/bin/erroneousData.json"));
	}
	
}
