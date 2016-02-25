package com.coolbeanzzz.development.servicetests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
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
import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.UETable;
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
	      jar.addPackage(FailureClass.class.getPackage()).addPackage(FailureClassService.class.getPackage())
	      .addPackage(JPAFailureClassDAO.class.getPackage()).addPackage(FailureClassDAO.class.getPackage());
	      jar.addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
	      jar.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
	      .addPackage(JSONArray.class.getPackage()).addPackage(ParseException.class.getPackage());
	     System.out.println(jar.toString(true));
  
	     return jar;
    }
 
	 @Test
	public void callServiceToGetFailureClassFromDB() {
		Collection<FailureClass> failureClasses=failureClassService.getCatalog();
		assertTrue(failureClasses.size()>0); 
	}
	 
	@Test
	public void uploadFailureClassFromFile() {		 
		failureClassService.populateTable(new File("/home/user1/software/jboss/bin/test_Failure Class Table.json"));
	    Collection<FailureClass> failureClasses=failureClassService.getCatalog();
	    assertTrue(failureClasses.size()>0); 
	}
	 
	@Test
	public void callServiceToGetEventCauseFromDB() {
		Collection<EventCause> eventCauses=eventCauseService.getCatalog();
		assertTrue(eventCauses.size()>0); 
	}
	 
	@Test
	public void uploadEventCauseFromFile() {		 
		eventCauseService.populateTable(new File("/home/user1/software/jboss/bin/test_Event-Cause Table.json"));
		Collection<EventCause> eventCauses=eventCauseService.getCatalog();
		assertTrue(eventCauses.size()>0);     
	}
	 
	@Test
	public void callServiceToGetMccMncFromDB() {
		Collection<MccMnc> mccmncs=mccmncService.getCatalog();
		assertTrue(mccmncs.size()>0);   
	}
	 
	@Test
	public void uploadMccMncFromFile() {		 
		mccmncService.populateTable(new File("/home/user1/software/jboss/bin/test_MCC - MNC Table.json"));
	    Collection<MccMnc> mccmncs=mccmncService.getCatalog();
	    assertTrue(mccmncs.size()>0); 
	}
	 
	@Test
	public void callServiceToGetUETableFromDB() {
		Collection<UETable> ueTables=ueTableService.getCatalog();
		assertTrue(ueTables.size()>0); 
	}
	 
	@Test
	public void uploadUETableFromFile() {		 
		ueTableService.populateTable(new File("/home/user1/software/jboss/bin/test_UE Table.json"));
	    Collection<UETable> ueTables=ueTableService.getCatalog();
	    assertTrue(ueTables.size()>0); 
	}
	 
	@Test
	public void callServiceToGetBaseDataFromDB() {
		Collection<BaseData> baseDatas=baseDataService.getCatalog();
		assertTrue(baseDatas.size()>0); 
	}
	 
	@Test
	public void uploadBaseDataFromFile() {		 
		baseDataService.populateBaseDataTable(new File("/home/user1/software/jboss/bin/test_validData.json"));
	    Collection<BaseData> baseDatas=baseDataService.getCatalog();
	    assertTrue(baseDatas.size()>0); 
	}
	 
	@Test
	public void callServiceToGetErroneousDataFromDB() {
		Collection<ErroneousData> erroneousData=erroneousDataService.getCatalog();
		assertTrue(erroneousData.size()>0); 
	}
	 
	@Test
	public void uploadErroneousDataFromFile() {		 
		erroneousDataService.populateErroneousDataTable(new File("/home/user1/software/jboss/bin/test_erroneousData.json"));
	    Collection<ErroneousData> erroneousData=erroneousDataService.getCatalog();
	    assertTrue(erroneousData.size()>0); 
	} 
}