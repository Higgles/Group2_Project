package com.coolbeanzzz.development.integrationtests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import jxl.Cell;
import jxl.biff.RecordData;
import jxl.biff.drawing.Drawing;
import jxl.biff.formula.ExternalSheet;
import jxl.common.Logger;
import jxl.common.log.SimpleLogger;
import jxl.format.Font;
import jxl.read.biff.BiffException;
import jxl.write.WritableCell;
import jxl.write.biff.BooleanRecord;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
import com.coolbeanzzz.development.entities.ResultList;
import com.coolbeanzzz.development.entities.UETable;
import com.coolbeanzzz.development.services.BaseDataService;
import com.coolbeanzzz.development.services.ErroneousDataService;
import com.coolbeanzzz.development.services.EventCauseService;
import com.coolbeanzzz.development.services.FailureClassService;
import com.coolbeanzzz.development.services.MccMncService;
import com.coolbeanzzz.development.services.UETableService;
import com.coolbeanzzz.development.tools.CompareData;
import com.coolbeanzzz.development.tools.Convert;

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
				.addPackage(FailureClassDAO.class.getPackage())
				.addPackage(Convert.class.getPackage())
				.addPackage(Cell.class.getPackage())
				.addPackage(BiffException.class.getPackage())
				.addPackage(RecordData.class.getPackage())
				.addPackage(Font.class.getPackage())
				.addPackage(ExternalSheet.class.getPackage())
				.addPackage(WritableCell.class.getPackage())
				.addPackage(BooleanRecord.class.getPackage())
				.addPackage(Drawing.class.getPackage())
				.addPackage(Logger.class.getPackage())
				.addPackage(SimpleLogger.class.getPackage());
		jar.addAsManifestResource("test_persistence.xml", "persistence.xml");
		jar.addAsManifestResource(EmptyAsset.INSTANCE,
				ArchivePaths.create("beans.xml"))
				.addPackage(JSONArray.class.getPackage())
				.addPackage(ParseException.class.getPackage());
		//jar.addAsResource("test_data.xls", "test_data.xls");
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
	public void getEventIdsCauseCodeForIMSITest() {
		Collection<?> res=baseDataService.getEventIdsCauseCodeForIMSI("2344930000000011");
		assertEquals(res.size(),1);
	}
	
	@Test
	@InSequence(13)
	public void getFailCountByPhoneModelTest() {
		Collection<?> res=baseDataService.getFailCountByPhoneModel("S.A.R.L. B  & B International", "VEA3", "2000-01-11 17:15:00", "2015-01-11 17:15:00");
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(14)
	public void getImsiListBetween2DatesTest() {
		Collection<?> res=baseDataService.getImsiListBetween2Dates( "2000-01-11 17:15:00", "2015-01-11 17:15:00");
		assertEquals(1,res.size());
	}
	
	@Test
	@InSequence(15)
	public void getNoOfCallFailuresAndDurationForImsiInDateRangeTest() {
		Collection<?> res=baseDataService.getNoOfCallFailuresAndDurationForImsiInDateRange( "2000-01-11 17:15:00", "2015-01-11 17:15:00");
		assertEquals(1,res.size());
	}
	
	@Test
	@InSequence(16)
	public void getUniqueEventIdsCauseCodeForPhoneTypeTest() {
		Collection<?> res=baseDataService.getUniqueEventIdsCauseCodeForPhoneType("S.A.R.L. B  & B International", "VEA3");
		assertEquals(1,res.size());
	}
	
	
	
	@Test @InSequence(17)
	public void uploadFailureClassFromFile() {
		ArrayList<JSONArray> datasetArray;
		try {
			datasetArray = Convert.convert("test_data.xls");
		
			failureClassService.populateTable(datasetArray.get(2));
			Collection<FailureTable> fcTest = failureClassService.getCatalog();
			assertEquals(datasetArray.get(2).size(), fcTest.size());
			
	    	eventCauseService.populateTable(datasetArray.get(1));
	    	Collection<FailureTable> ecTest = eventCauseService.getCatalog();
			assertEquals(datasetArray.get(1).size(), ecTest.size());
			
	    	mccmncService.populateTable(datasetArray.get(4));
	    	Collection<FailureTable> mccmncTest = mccmncService.getCatalog();
			assertEquals(datasetArray.get(4).size(), mccmncTest.size());
			
	    	ueTableService.populateTable(datasetArray.get(3));
	    	Collection<FailureTable> ueTableTest = ueTableService.getCatalog();
			assertEquals(datasetArray.get(3).size(), ueTableTest.size());
	    	
			Collection<Integer> uniqueEventIds = eventCauseService.getAllUniqueEventIds();
			Collection<Integer> uniqueCauseCodes = eventCauseService.getAllUniqueCauseCodes();
			Collection<Integer> uniqueFailureCodes = failureClassService.getFailureClassCodes();
			Collection<Integer> mccs = mccmncService.getAllUniqueMCCs();
			Collection<Integer> mncs = mccmncService.getAllUniqueMNCs();	
			Collection<Integer> ueTypes = ueTableService.getUETypes();
			
			CompareData compare = new CompareData(uniqueEventIds, uniqueCauseCodes, uniqueFailureCodes, mccs, mncs, ueTypes);
	    	
			ArrayList<JSONArray> baseData = compare.compareData(datasetArray.get(0));
			
	    	
	    	JSONArray validData = baseData.get(0);
	    	baseDataService.populateTable(validData);
	    	Collection<FailureTable> validDataTest = baseDataService.getCatalog();
			assertEquals(validData.size(), validDataTest.size()-1);
	    	
	    	JSONArray erroneousData = baseData.get(1);
	    	erroneousDataService.populateTable(erroneousData);
	    	Collection<FailureTable> erroneousDataTest = erroneousDataService.getCatalog();
			assertEquals(erroneousData.size(), erroneousDataTest.size());
		} catch (IOException e) {
			fail("Problem with test file");
		} catch (BiffException e) {
			fail("Unable to parse test file");
		}
	}
	
	@Test
	@InSequence(18)
	public void getUniqueCauseCodeForIMSITest() {
		Collection<?> res=baseDataService.getUniqueCauseCodeForIMSI("2344930000000011");
		assertEquals(res.size(),1);
	}
	
	@Test
	@InSequence(19)
	public void getTop10ImsiListBetween2DatesTest() {
		Collection<?> res=baseDataService.getTop10ImsiListBetween2Dates( "2000-01-11 17:15:00", "2015-01-11 17:15:00");
		assertEquals(1,res.size());
	}
	 
	@Test
	@InSequence(23)
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
