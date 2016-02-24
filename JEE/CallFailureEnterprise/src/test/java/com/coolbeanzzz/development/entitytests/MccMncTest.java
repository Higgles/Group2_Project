package com.coolbeanzzz.development.entitytests;



	import static org.junit.Assert.*;

	import java.io.File;
import java.io.IOException;

	import org.apache.commons.io.FileUtils;
import org.junit.Test;

	import com.coolbeanzzz.development.entities.EventCause;
import com.coolbeanzzz.development.entities.FailureClass;
import com.coolbeanzzz.development.entities.MccMnc;
import com.coolbeanzzz.development.entities.UETable;
import com.coolbeanzzz.development.tools.Convert;


public class MccMncTest {
		
		@Test
		public void testEquals(){
			
			MccMnc mccmncTest = new MccMnc(0,0,"desc","desc");
			MccMnc mccmncTest2 = new MccMnc(0,0,"desc","desc");

			assertFalse(mccmncTest.equals(null));

			assertTrue(mccmncTest.equals(mccmncTest));
			assertFalse(mccmncTest.equals(new String()));
			assertTrue(mccmncTest.equals(mccmncTest2));

			mccmncTest.setOperator("wrong desc");
			assertFalse(mccmncTest.equals(mccmncTest2));
			mccmncTest.setOperator(null);
			assertFalse(mccmncTest.equals(mccmncTest2));
			
			mccmncTest.setCountry("wrong desc");
			assertFalse(mccmncTest.equals(mccmncTest2));
			mccmncTest.setCountry(null);
			assertFalse(mccmncTest.equals(mccmncTest2));

			mccmncTest.setMnc(69);
			assertFalse(mccmncTest.equals(mccmncTest2));
			mccmncTest.setMcc(69);
			assertFalse(mccmncTest.equals(mccmncTest2));
			

		}
		
		
		@Test
		public void testHashcode(){
			MccMnc mccmncTest = new MccMnc(0,0,"desc","desc");
			assertEquals(99477921,mccmncTest.hashCode());
			
		}
		
	}
	
	

