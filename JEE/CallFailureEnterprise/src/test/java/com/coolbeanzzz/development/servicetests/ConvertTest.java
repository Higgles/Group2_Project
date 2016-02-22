package com.coolbeanzzz.development.servicetests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.coolbeanzzz.development.tools.Convert;

public class ConvertTest {
	
	Convert convert = new Convert();
	
	//Compare sheet 5 of the data for JSON comparison
	@Test
	public void testConversion() throws IOException {
		convert.setInputFile("./testData.xls");
		convert.convert();
		assertEquals(FileUtils.readLines(new File("./testCheck.json")), FileUtils.readLines(new File("./MCC - MNC Table.json")));
	}

}

