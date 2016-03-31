package com.coolbeanzzz.development.servicetests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.coolbeanzzz.development.tools.Convert;

public class ConvertTest {
	
	//Compare sheet 5 of the data for JSON comparison
	@Test
	public void testConversion() throws IOException {
		try {
			File dataset = new File("./testData.xls");
			Convert.convert(dataset);
			assertEquals(FileUtils.readLines(new File("./testCheck.json")), FileUtils.readLines(new File("./MCC - MNC Table.json")));
		} catch (InterruptedException e) {
			fail("Interrupted conversion");
		} catch (BiffException e) {
			fail("File not found");
		}	
	}

}

