package com.coolbeanzzz.development.integrationtests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
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

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.ExecutionException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.PathConfigProcessor;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.servlet.AbstractFilter;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.servlet.NameableFilter;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.apache.shiro.web.servlet.ServletContextSupport;
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
import com.coolbeanzzz.development.entities.Users;
import com.coolbeanzzz.development.services.BaseDataService;
import com.coolbeanzzz.development.services.ErroneousDataService;
import com.coolbeanzzz.development.services.EventCauseService;
import com.coolbeanzzz.development.services.FailureClassService;
import com.coolbeanzzz.development.services.MccMncService;
import com.coolbeanzzz.development.services.UETableService;
import com.coolbeanzzz.development.services.UsersService;
import com.coolbeanzzz.development.tools.CompareData;
import com.coolbeanzzz.development.tools.Convert;
import com.coolbeanzzz.development.tools.QueryOptions;

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
	
	@Inject
	UsersService usersService;

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
		jar.addClass(ServletContextSupport.class)
				.addClass(AbstractFilter.class)
				.addClass(NameableFilter.class)
				.addClass(OncePerRequestFilter.class)
				.addClass(AdviceFilter.class)
				.addClass(PathMatchingFilter.class)
				.addClass(AccessControlFilter.class)
				.addClass(AuthorizationFilter.class)
				.addClass(RolesAuthorizationFilter.class)
				.addClass(Nameable.class)
				.addClass(PathConfigProcessor.class)
				.addClass(PatternMatcher.class)
				.addClass(SubjectContext.class)
				.addClass(Subject.class)
				.addClass(SecurityManager.class)
				.addClass(Authenticator.class)
				.addClass(Authorizer.class)
				.addClass(SessionManager.class)
				.addClass(Session.class)
				.addClass(InvalidSessionException.class)
				.addClass(SessionException.class)
				.addClass(ShiroException.class)
				.addClass(PrincipalCollection.class)
				.addClass(Permission.class)
				.addClass(AuthorizationException.class)
				.addClass(AuthenticationToken.class)
				.addClass(AuthenticationInfo.class)
				.addClass(AuthenticationException.class)
				.addClass(ExecutionException.class)
				.addClass(SessionKey.class)
				.addClass(SessionContext.class);
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
		Collection<Users> usersResults = usersService.getAllUsers(1, "", -1);
		
		assertEquals(0, failureClassResults.size());
		assertEquals(1, baseDataResults.size());
		assertEquals(0, erroneousDataResults.size());
		assertEquals(0, eventCauseResults.size());
		assertEquals(0, mccmncResults.size());
		assertEquals(0, ueTableResults.size());
		assertEquals(0, usersResults.size());
	}

	@Test
	@InSequence(2)
	public void addFailureClassTest() {
		FailureClass fc = new FailureClass(0, "");
		failureClassService.addNewEntry(fc);
		Collection<FailureTable> failureClasses = failureClassService.getCatalog();
		assert(failureClasses.contains(fc)==true);
		Collection<Integer> failureClassCodes=failureClassService.getFailureClassCodes();
		assertEquals(1,failureClassCodes.size());
		assert(failureClassCodes.contains(0));
	}
	
	@Test
	@InSequence(3)
	public void addeventCauseTest() {
		EventCause ec = new EventCause(0, 0, "");
		eventCauseService.addNewEntry(ec);
		Collection<FailureTable> eventCauses = eventCauseService.getCatalog();
		assert(eventCauses.contains(ec));
	}
	
	@Test
	@InSequence(4)
	public void addmccmncTest() {
		MccMnc mccmnc = new MccMnc(0,0,"","");
		mccmncService.addNewEntry(mccmnc);
		Collection<FailureTable> mccmncs = mccmncService.getCatalog();
		assert(mccmncs.contains(mccmnc));
	}
	
	@Test
	@InSequence(5)
	public void addueTableTest() {
		UETable ueTable = new UETable(0,"","","","","","","","");
		ueTableService.addNewEntry(ueTable);
		Collection<FailureTable> ueTables = ueTableService.getCatalog();
		assert(ueTables.contains(ueTable)==true);
		Collection<Integer> ueTypes=ueTableService.getUETypes();
		assertEquals(1,ueTypes.size());
		assert(ueTypes.contains(0));
	}
	
	@Test
	@InSequence(6)
	public void addErroneousDataTest() {
		ErroneousData erroneousData = new ErroneousData(0,"2013-11-10",0,"",0,0,0,"","","","","","","","");
		erroneousDataService.addNewEntry(erroneousData);
		Collection<FailureTable> erroneousDatas = erroneousDataService.getCatalog();
		assert(erroneousDatas.contains(erroneousData));
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
		assert(baseDatas.contains(baseData));
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
		assertEquals(2,uniqueCauseCodes.size());
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
		assertEquals(2,uniqueEventIds.size());
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
		assertEquals(2,uniqueMccs.size());
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
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getEventIdsCauseCodeForIMSI("2344930000000011",options);
		assertEquals(2, res.size());
	}
	
	@Test
	@InSequence(13)
	public void getFailCountByPhoneModelTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getFailCountByPhoneModel("S.A.R.L. B  & B International", "VEA3", "2000-01-11 17:15:00", "2015-01-11 17:15:00", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(14)
	public void getImsiListBetween2DatesTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getImsiListBetween2Dates( "2000-01-11 17:15:00", "2015-01-11 17:15:00", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(15)
	public void getNoOfCallFailuresAndDurationForImsiInDateRangeTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getNoOfCallFailuresAndDurationForImsiInDateRange( "2000-01-11 17:15:00", "2015-01-11 17:15:00", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(16)
	public void getUniqueEventIdsCauseCodeForPhoneTypeTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getUniqueEventIdsCauseCodeForPhoneType("S.A.R.L. B  & B International", "VEA3", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(17)
	public void getFailCountByImsiAndDateTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getFailCountByImsiAndDate("344930000000011","2000-01-11 17:15:00", "2015-01-11 17:15:00", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(18)
	public void getTop10MarketOperatorCellBetween2DatesTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getTop10MarketOperatorCellBetween2Dates("2000-01-11 17:15:00", "2015-01-11 17:15:00", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(19)
	public void getIMSIsforFailureClassTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getIMSIsforFailureClass("HIGH PRIORITY ACCESS", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(20)
	public void getUniqueCauseCodeForIMSITest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getUniqueCauseCodeForIMSI("2344930000000011", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(21)
	public void getTop10ImsiListBetween2DatesTest() {
		QueryOptions options=new QueryOptions(0, 0, 0, false, "", 0, "asc");
		Collection<?> res=baseDataService.getTop10ImsiListBetween2Dates( "2000-01-11 17:15:00", "2015-01-11 17:15:00", options);
		assertEquals(2,res.size());
	}
	
	@Test
	@InSequence(22)
	public void getAllManufacturersTest() {
		UETable ueTable = new UETable(0,"","a","","b","","","","");
		ueTableService.addNewEntry(ueTable);
		Collection<?> res=ueTableService.getAllManufacturers(1, "", -1);
		assertEquals(1,res.size());
		assertEquals("a",res.iterator().next());
	}
	
	@Test
	@InSequence(23)
	public void getAllModelsForManufacturerTest() {
		UETable ueTable = new UETable(0,"","a","","b","","","","");
		ueTableService.addNewEntry(ueTable);
		Collection<?> res=ueTableService.getModelsForManufacturer("a", 1, "", -1);
		assertEquals(1,res.size());
		assertEquals("b",res.iterator().next());
	}
	
	@Test
	@InSequence(24)
	public void getAllFailureClassDescriptionsTest() {
		FailureClass fc = new FailureClass(0, "Test");
		failureClassService.addNewEntry(fc);
		Collection<?> res=failureClassService.getAllDescriptions(1, "", -1);
		assertEquals(1,res.size());
		assertEquals("Test",res.iterator().next());
	}
	
	@Test
	@InSequence(25)
	public void addUserTest() {
		Users user = new Users("Test", "a", "SysAd");
		usersService.addUser(user);
		Collection<Users> usersResults = usersService.getAllUsers(1, "", -1);
		assertEquals(user, usersResults.iterator().next());
	}
	
	@Test
	@InSequence(26)
	public void editUserTest() {
		Users user = new Users("Test", "b", "CSR");
		usersService.updateUser(user);
		Collection<Users> usersResults = usersService.getAllUsers(1, "", -1);
		assertEquals(1, usersResults.size());
		assertEquals(user, usersResults.iterator().next());
	}
	
	@Test
	@InSequence(27)
	public void removeUserTest() {
		usersService.removeUser("Test");
		Collection<Users> usersResults = usersService.getAllUsers(1, "", -1);
		assertEquals(0, usersResults.size());
	}
	
	@Test @InSequence(28)
	public void uploadFromFileTest() {
		ArrayList<JSONArray> datasetArray;
		try {
			File dataset = new File("test_data.xls");
			datasetArray = Convert.convert(dataset);
		
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
		} catch (InterruptedException e) {
			fail("File reading interrupted");
		} catch (BiffException e) {
			fail("Problem extracting excel file");
		}
	}
	 
	@Test
	@InSequence(29)
	public void tablesEndEmptyTest() {
		Collection<FailureTable> failureClassResults = failureClassService.getCatalog();
		Collection<FailureTable> baseDataResults = baseDataService.getCatalog();
		Collection<FailureTable> erroneousDataResults = erroneousDataService.getCatalog();
		Collection<FailureTable> eventCauseResults = eventCauseService.getCatalog();
		Collection<FailureTable> mccmncResults = mccmncService.getCatalog();
		Collection<FailureTable> ueTableResults = ueTableService.getCatalog();
		Collection<Users> usersResults = usersService.getAllUsers(1, "", -1);
		
		assertEquals(0, failureClassResults.size());
		assertEquals(1, baseDataResults.size());
		assertEquals(0, erroneousDataResults.size());
		assertEquals(0, eventCauseResults.size());
		assertEquals(0, mccmncResults.size());
		assertEquals(0, ueTableResults.size());
		assertEquals(0, usersResults.size());
	}
}
