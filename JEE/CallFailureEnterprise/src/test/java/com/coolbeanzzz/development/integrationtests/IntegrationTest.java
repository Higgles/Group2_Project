package com.coolbeanzzz.development.integrationtests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.dao.jpa.JPAFailureClassDAO;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.FailureTable;
import com.coolbeanzzz.development.services.BaseDataService;
import com.coolbeanzzz.development.services.ErroneousDataService;
import com.coolbeanzzz.development.services.EventCauseService;
import com.coolbeanzzz.development.services.FailureClassService;
import com.coolbeanzzz.development.services.MccMncService;
import com.coolbeanzzz.development.services.UETableService;

@RunWith(Arquillian.class)
public class IntegrationTest {
	
	 @Inject
	 FailureClassService failureClassService;
	 
	 @Inject
	 EventCauseService eventCauseService;
	 
	 @Inject
	 MccMncService mccmncService;
	 
	 @Inject
	 UETableService ueTableService;
	 
	 @Inject
	 BaseDataService baseDataService;
	 
	 @Inject
	 ErroneousDataService erroneousDataService;
	
	 @Deployment
	 public static JavaArchive createDeployment(){
	      JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "callfailureenterprise.jar");
	      jar.addPackage(FailureClass.class.getPackage())
	      .addPackage(FailureClassService.class.getPackage())
	      .addPackage(JPAFailureClassDAO.class.getPackage())
	      .addPackage(FailureClassDAO.class.getPackage());
	      jar.addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
	      jar.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
	      .addPackage(JSONArray.class.getPackage()).addPackage(ParseException.class.getPackage());
	     System.out.println(jar.toString(true));
  
	     return jar;
    }
 
	@Test @InSequence(1)
	public void callServiceToGetFailureClassFromDB() {
		Collection<FailureTable> failureClasses=failureClassService.getCatalog();
		assertNotNull(failureClasses); 
	}
	 
	@Test @InSequence(2)
	public void uploadFailureClassFromFile() {		 
		failureClassService.populateTable(new File("/home/user1/software/jboss/bin/test_Failure Class Table.json"));
	    Collection<FailureTable> failureClasses=failureClassService.getCatalog();
	    assertTrue(failureClasses.size()>0); 
	}
	 
	@Test @InSequence(3)
	public void callServiceToGetEventCauseFromDB() {
		Collection<FailureTable> eventCauses=eventCauseService.getCatalog();
		assertNotNull(eventCauses); 
	}
	 
	@Test @InSequence(4)
	public void uploadEventCauseFromFile() {		 
		eventCauseService.populateTable(new File("/home/user1/software/jboss/bin/test_Event-Cause Table.json"));
		Collection<FailureTable> eventCauses=eventCauseService.getCatalog();
		assertTrue(eventCauses.size()>0);     
	}
	 
	@Test @InSequence(5)
	public void callServiceToGetMccMncFromDB() {
		Collection<FailureTable> mccmncs=mccmncService.getCatalog();
		assertNotNull(mccmncs);   
	}
	 
	@Test @InSequence(6)
	public void uploadMccMncFromFile() {		 
		mccmncService.populateTable(new File("/home/user1/software/jboss/bin/test_MCC - MNC Table.json"));
	    Collection<FailureTable> mccmncs=mccmncService.getCatalog();
	    assertTrue(mccmncs.size()>0); 
	}
	 
	@Test @InSequence(7)
	public void callServiceToGetUETableFromDB() {
		Collection<FailureTable> ueTables=ueTableService.getCatalog();
		assertNotNull(ueTables); 
	}
	 
	@Test @InSequence(8)
	public void uploadUETableFromFile() {		 
		ueTableService.populateTable(new File("/home/user1/software/jboss/bin/test_UE Table.json"));
	    Collection<FailureTable> ueTables=ueTableService.getCatalog();
	    assertTrue(ueTables.size()>0); 
	}
	 
	@Test @InSequence(9)
	public void callServiceToGetBaseDataFromDB() {
		Collection<FailureTable> baseDatas=baseDataService.getCatalog();
		assertNotNull(baseDatas); 
	}
	 
	@Test @InSequence(10)
	public void uploadBaseDataFromFile() {		 
		baseDataService.populateTable(new File("/home/user1/software/jboss/bin/test_validData.json"));
	    Collection<FailureTable> baseDatas=baseDataService.getCatalog();
	    assertTrue(baseDatas.size()>0); 
	}
	 
	@Test @InSequence(11)
	public void callServiceToGetErroneousDataFromDB() {
		Collection<FailureTable> erroneousData=erroneousDataService.getCatalog();
		assertNotNull(erroneousData); 
	}
	 
	@Test @InSequence(12)
	public void uploadErroneousDataFromFile() {		 
		erroneousDataService.populateTable(new File("/home/user1/software/jboss/bin/test_erroneousData.json"));
	    Collection<FailureTable> erroneousData=erroneousDataService.getCatalog();
	    assertTrue(erroneousData.size()>0); 
	} 
}