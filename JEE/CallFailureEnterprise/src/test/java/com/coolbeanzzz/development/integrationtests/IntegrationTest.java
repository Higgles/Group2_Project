package com.coolbeanzzz.development.integrationtests;

import static org.junit.Assert.*;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.coolbeanzzz.development.dao.FailureClassDAO;
import com.coolbeanzzz.development.dao.jpa.JPAFailureClassDAO;
import com.coolbeanzzz.development.entities.BaseData;
import com.coolbeanzzz.development.entities.ErroneousData;
import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.FailureTable;
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
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class,
				"callfailureenterprise.jar");
		jar.addPackage(FailureClass.class.getPackage())
				.addPackage(FailureClassService.class.getPackage())
				.addPackage(JPAFailureClassDAO.class.getPackage())
				.addPackage(FailureClassDAO.class.getPackage());
		jar.addAsManifestResource("test_persistence.xml", "persistence.xml");
		jar.addAsManifestResource(EmptyAsset.INSTANCE,
				ArchivePaths.create("beans.xml"))
				.addPackage(JSONArray.class.getPackage())
				.addPackage(ParseException.class.getPackage());
		System.out.println(jar.toString(true));

		return jar;
	}

	@Before
	public void setUpDatabase() {
		baseDataService.clearAllEntries();
		failureClassService.clearAllEntries();
		erroneousDataService.clearAllEntries();
		eventCauseService.clearAllEntries();
		mccmncService.clearAllEntries();
		ueTableService.clearAllEntries();
	}
	
	@Test
	@InSequence(1)
	public void tablesBeginEmptyTest() {
		Collection<FailureTable> failureClassResults = failureClassService.getCatalog();
		Collection<FailureTable> baseDataResults = baseDataService.getCatalog();
		Collection<FailureTable> erroneousDataResults = erroneousDataService.getCatalog();
		Collection<FailureTable> eventCauseResults = eventCauseService.getCatalog();
		Collection<FailureTable> mccmncResults = mccmncService.getCatalog();
		Collection<FailureTable> ueTableResults = ueTableService.getCatalog();
		
		assert(failureClassResults.isEmpty()==true);
		assert(baseDataResults.isEmpty()==true);
		assert(erroneousDataResults.isEmpty()==true);
		assert(eventCauseResults.isEmpty()==true);
		assert(mccmncResults.isEmpty()==true);
		assert(ueTableResults.isEmpty()==true);
	}

	@Test
	@InSequence(2)
	public void addFailureClassTest() {
		FailureClass fc = new FailureClass(0, "");
		failureClassService.addNewEntry(fc);
		Collection<FailureTable> failureClasses = failureClassService.getCatalog();
		assert(failureClasses.contains(fc)==true);
		Collection<Integer> failureClassCodes=failureClassService.getFailureClassCodes();
		assert(failureClassCodes.size()==1);
		assert(failureClassCodes.contains(0));
	}
	
	@Test
	@InSequence(3)
	public void addeventCauseTest() {
		EventCause ec = new EventCause(0, 0, "");
		eventCauseService.addNewEntry(ec);
		Collection<FailureTable> eventCauses = eventCauseService.getCatalog();
		assert(eventCauses.contains(ec)==true);
	}
	
	@Test
	@InSequence(4)
	public void addmccmncTest() {
		MccMnc mccmnc = new MccMnc(0,0,"","");
		mccmncService.addNewEntry(mccmnc);
		Collection<FailureTable> mccmncs = mccmncService.getCatalog();
		assert(mccmncs.contains(mccmnc)==true);
	}
	
	@Test
	@InSequence(5)
	public void addueTableTest() {
		UETable ueTable = new UETable(0,"","","","","","","","");
		ueTableService.addNewEntry(ueTable);
		Collection<FailureTable> ueTables = ueTableService.getCatalog();
		assert(ueTables.contains(ueTable)==true);
		Collection<Integer> ueTypes=ueTableService.getUETypes();
		assert(ueTypes.size()==1);
		assert(ueTypes.contains(0));
	}
	
	@Test
	@InSequence(6)
	public void addErroneousDataTest() {
		ErroneousData erroneousData = new ErroneousData(0,"2013-11-10",0,"",0,0,0,"","","","","","","","");
		erroneousDataService.addNewEntry(erroneousData);
		Collection<FailureTable> erroneousDatas = erroneousDataService.getCatalog();
		assert(erroneousDatas.contains(erroneousData)==true);
	}
	
	@Test
	@InSequence(7)
	public void addBaseDataTest() {
		FailureClass fc = new FailureClass(0, "");
		failureClassService.addNewEntry(fc);
		EventCause ec = new EventCause(0, 0, "");
		eventCauseService.addNewEntry(ec);
		MccMnc mccmnc = new MccMnc(0,0,"","");
		mccmncService.addNewEntry(mccmnc);
		UETable ueTable = new UETable(0,"","","","","","","","");
		ueTableService.addNewEntry(ueTable);
		BaseData baseData = new BaseData(0,"2013-11-10","","","","","","","",ec,mccmnc,ueTable,fc);
		baseDataService.addNewEntry(baseData);
		Collection<FailureTable> baseDatas = baseDataService.getCatalog();
		assert(baseDatas.contains(baseData)==true);
	}
	
	@Test
	@InSequence(8)
	public void getUniqueCauseCodeTest() {
		EventCause ec = new EventCause(0, 0, "");
		EventCause ec2 = new EventCause(0, 0, "");
		EventCause ec3 = new EventCause(1, 0, "");
		EventCause ec4 = new EventCause(1, 0, "");
		eventCauseService.addNewEntry(ec);
		eventCauseService.addNewEntry(ec2);
		eventCauseService.addNewEntry(ec3);
		eventCauseService.addNewEntry(ec4);
		Collection<Integer> uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
		assert(uniqueCauseCodes.size()==2);
		assert(uniqueCauseCodes.contains(0));
		assert(uniqueCauseCodes.contains(1));
	}
	
	@Test
	@InSequence(9)
	public void getUniqueEventIdTest() {
		EventCause ec = new EventCause(0, 0, "");
		EventCause ec2 = new EventCause(0, 0, "");
		EventCause ec3 = new EventCause(0, 1, "");
		EventCause ec4 = new EventCause(0, 1, "");
		eventCauseService.addNewEntry(ec);
		eventCauseService.addNewEntry(ec2);
		eventCauseService.addNewEntry(ec3);
		eventCauseService.addNewEntry(ec4);
		Collection<Integer> uniqueEventIds = eventCauseService.getAllUniqueEventIds();
		assert(uniqueEventIds.size()==2);
		assert(uniqueEventIds.contains(0));
		assert(uniqueEventIds.contains(1));
	}
	
	@Test
	@InSequence(10)
	public void getMccsTest() {
		MccMnc mccmnc = new MccMnc(0,0,"","");
		MccMnc mccmnc2 = new MccMnc(0,0,"","");
		MccMnc mccmnc3 = new MccMnc(1,0,"","");
		MccMnc mccmnc4 = new MccMnc(1,0,"","");
		mccmncService.addNewEntry(mccmnc);
		mccmncService.addNewEntry(mccmnc2);
		mccmncService.addNewEntry(mccmnc3);
		mccmncService.addNewEntry(mccmnc4);
		Collection<Integer> uniqueMccs = mccmncService.getAllUniqueMCCs();
		assert(uniqueMccs.size()==2);
		assert(uniqueMccs.contains(0));
		assert(uniqueMccs.contains(1));
	}
	
	@Test
	@InSequence(11)
	public void getMncsTest() {
		MccMnc mccmnc = new MccMnc(0,0,"","");
		MccMnc mccmnc2 = new MccMnc(0,0,"","");
		MccMnc mccmnc3 = new MccMnc(0,1,"","");
		MccMnc mccmnc4 = new MccMnc(0,1,"","");
		mccmncService.addNewEntry(mccmnc);
		mccmncService.addNewEntry(mccmnc2);
		mccmncService.addNewEntry(mccmnc3);
		mccmncService.addNewEntry(mccmnc4);
		Collection<Integer> uniqueMncs = mccmncService.getAllUniqueMNCs();
		assert(uniqueMncs.size()==2);
		assert(uniqueMncs.contains(0));
		assert(uniqueMncs.contains(1));
	}
	
	@Test
	@InSequence(12)
	public void tablesEndEmptyTest() {
		Collection<FailureTable> failureClassResults = failureClassService.getCatalog();
		Collection<FailureTable> baseDataResults = baseDataService.getCatalog();
		Collection<FailureTable> erroneousDataResults = erroneousDataService.getCatalog();
		Collection<FailureTable> eventCauseResults = eventCauseService.getCatalog();
		Collection<FailureTable> mccmncResults = mccmncService.getCatalog();
		Collection<FailureTable> ueTableResults = ueTableService.getCatalog();
		
		assert(failureClassResults.isEmpty()==true);
		assert(baseDataResults.isEmpty()==true);
		assert(erroneousDataResults.isEmpty()==true);
		assert(eventCauseResults.isEmpty()==true);
		assert(mccmncResults.isEmpty()==true);
		assert(ueTableResults.isEmpty()==true);
	}
}