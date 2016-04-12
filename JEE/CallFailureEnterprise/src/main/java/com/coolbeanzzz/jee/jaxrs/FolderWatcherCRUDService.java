package com.coolbeanzzz.jee.jaxrs;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.coolbeanzzz.development.tools.FolderWatcherService;

@Path("/folderWatcher")
public class FolderWatcherCRUDService {
	
	@EJB
	private FolderWatcherService service;
	
	/**
	 * Gets all failure class records from the underlying table within the database
	 * @return a list of failure class results
	 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getFileProgress(@QueryParam("filename") String filename) {
        return "["+service.getFileProgress(filename)+"]";
    }
}
