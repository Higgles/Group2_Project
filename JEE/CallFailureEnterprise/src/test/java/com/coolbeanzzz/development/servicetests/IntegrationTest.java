package com.coolbeanzzz.development.servicetests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.JsonArray;

import junit.framework.Assert;

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
	 FailureClassService fcservice;
	 
	 @Inject
	 EventCauseService ecservice;
	 
	 @Inject
	 MccMncService mccmncservice;
	 
	 @Inject
	 UETableService uetableservice;
	 
	 @Inject
	 BaseDataService basedataservice;
	 
	 @Inject
	 ErroneousDataService erroneousdataservice;
	
	 @Deployment
	 public static JavaArchive createDeployment(){
	      JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "callfailureenterprise.jar");
	      jar.addPackage(FailureClass.class.getPackage()).addPackage(FailureClassService.class.getPackage())
	      .addPackage(JPAFailureClassDAO.class.getPackage()).addPackage(FailureClassDAO.class.getPackage());
	      jar.addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
	      jar.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
	      .addPackage(JSONArray.class.getPackage()).addPackage(ParseException.class.getPackage());
	      //jar.addAsManifestResource("ejb-jar.xml", "ejb-jar.xml");
	     System.out.println(jar.toString(true));
  
	     return jar;
    }
 
 
	 @Test
	 public void callServiceToGetFailureClassFromDB() {
		 Collection<FailureClass> f=fcservice.getCatalog();
		 
	     assertNotNull("not be null!", f);
//	     FailureClass fc=f.iterator().next();
//	     assertTrue(fc.equals(fc));	     
	 }
	 
	 @Test
	 public void uploadFailureClassFromFile() {		 
	     fcservice.populateTable(new File("/home/user1/software/jboss/bin/Failure Class Table.json"));
	     Collection<FailureClass> f=fcservice.getCatalog();
	     assertTrue(f.size()>0);
	     
	 }
	 
	 @Test
	 public void callServiceToGetEventCauseFromDB() {
		 Collection<EventCause> f=ecservice.getCatalog();
	     assertNotNull("not be null!", f);
//	     EventCause fc=f.iterator().next();
//	     assertTrue(fc.equals(fc));
	 }
	 
	 @Test
	 public void uploadEventCauseFromFile() {		 
		 ecservice.populateTable(new File("/home/user1/software/jboss/bin/Event-Cause Table.json"));
	     Collection<FailureClass> f=fcservice.getCatalog();
	     assertTrue(f.size()>0);
	     
	 }
	 
	 @Test
	 public void callServiceToGetMccMncFromDB() {
		 Collection<MccMnc> f=mccmncservice.getCatalog();
	     assertNotNull("not be null!", f);
//	     MccMnc fc=f.iterator().next();
//	     assertTrue(fc.equals(fc));
	 }
	 
	 @Test
	 public void uploadMccMncFromFile() {		 
		 mccmncservice.populateTable(new File("/home/user1/software/jboss/bin/MCC - MNC Table.json"));
	     Collection<MccMnc> f=mccmncservice.getCatalog();
	     assertTrue(f.size()>0);
	     
	 }
	 
	 @Test
	 public void callServiceToGetUETableFromDB() {
		 Collection<UETable> f=uetableservice.getCatalog();
	     assertNotNull("not be null!", f);
//	     UETable fc=f.iterator().next();
//	     assertTrue(fc.equals(fc));
	 }
	 
	 @Test
	 public void uploadUETableFromFile() {		 
		 uetableservice.populateTable(new File("/home/user1/software/jboss/bin/UE Table.json"));
	     Collection<UETable> f=uetableservice.getCatalog();
	     assertTrue(f.size()>0);
	     
	 }
	 
	 @Test
	 public void callServiceToGetBaseDataFromDB() {
		 Collection<BaseData> f=basedataservice.getCatalog();
	     assertNotNull("not be null!", f);
//	     BaseData fc=f.iterator().next();
//	     assertTrue(fc.equals(fc));
	 }
	 
	 @Test
	 public void uploadBaseDataFromFile() {		 
		 basedataservice.populateBaseDataTable(new File("/home/user1/software/jboss/bin/validData.json"));
	     Collection<BaseData> f=basedataservice.getCatalog();
	     assertTrue(f.size()>0);
	     
	 }
	 
	 @Test
	 public void callServiceToGetErroneousDataFromDB() {
		 Collection<ErroneousData> f=erroneousdataservice.getCatalog();
	     assertNotNull("not be null!", f);
//	     ErroneousData fc=f.iterator().next();
//	     assertTrue(fc.equals(fc));
	     
	 }
	 
	 @Test
	 public void uploadErroneousDataFromFile() {		 
		 erroneousdataservice.populateErroneousDataTable(new File("/home/user1/software/jboss/bin/erroneousData.json"));
	     Collection<ErroneousData> f=erroneousdataservice.getCatalog();
	     assertTrue(f.size()>0);
	     
	 }
	 
	 
}