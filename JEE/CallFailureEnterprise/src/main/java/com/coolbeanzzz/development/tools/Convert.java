/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Convert {

	private String inputFile;

	/**
	 * Sets the xls file to be converted to json
	 * @param inputFile new inputFile value
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	
	/**
	 * Creates a json file from each sheet in a provided xls file
	 * Creates and returns a JSON array for the base data sheet to be used for consistency checking
	 * @throws BiffException 
	 */
	public ArrayList<JSONArray> convert() throws IOException, BiffException  {
		File inputWorkbook = new File(inputFile);
		Workbook workbook;
		
		ArrayList<String> labels = new ArrayList<String>();
		
		Sheet sheet;
		Cell cell;
		CellType type;
		String cellType;
		
		ArrayList<JSONArray> datasetTables = new ArrayList<JSONArray>();
		JSONArray baseData = new JSONArray();
		JSONArray eventCauseTable = new JSONArray();
		JSONArray failureClassTable = new JSONArray();
		JSONArray ueTable = new JSONArray();
		JSONArray mccMncTable = new JSONArray();
		
		workbook = Workbook.getWorkbook(inputWorkbook);
		for(int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++){
			sheet = workbook.getSheet(sheetNumber);
			JSONObject datasetRow = new JSONObject();
			for (int row = 0; row < sheet.getRows(); row++){
				for (int column = 0; column < sheet.getColumns(); column++) {
					if(row == 0){
						cell = sheet.getCell(column, row);
						labels.add(cell.getContents());
					}
					else{
						cell = sheet.getCell(column, row);
						type = cell.getType();
						cellType = type.toString();
						if(cell.getType() == CellType.DATE){
							DateCell dateCell = (DateCell) cell;
							Date date = dateCell.getDate();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							datasetRow.put(labels.get(column), formatter.format(date));
						}
						else{
							datasetRow.put(labels.get(column), cell.getContents());
						}
					}
				}
				if(sheetNumber == 0){
					baseData.add(datasetRow);
				}
				else if(sheetNumber == 1){
					eventCauseTable.add(datasetRow);
				}
				else if(sheetNumber == 2){
					failureClassTable.add(datasetRow);
				}
				else if(sheetNumber == 3){
					ueTable.add(datasetRow);
				}
				else if(sheetNumber == 4){
					mccMncTable.add(datasetRow);
				}
				datasetRow = new JSONObject();
			}
			labels.clear();
		}
		datasetTables.add(baseData);
		datasetTables.add(eventCauseTable);
		datasetTables.add(failureClassTable);
		datasetTables.add(ueTable);
		datasetTables.add(mccMncTable);
		
		return datasetTables;
	}
}