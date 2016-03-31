/**
 * @author Coolbeanzzz
 */
package com.coolbeanzzz.development.tools;

import java.io.File;
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
	
	/**
	 * Creates a json araay from each sheet in a provided xls file
	 * Creates and returns a JSON array for the base data sheet to be used for consistency checking
	 * 
	 * @param inputFile Set the xls file to be converted to json arrays
	 * @throws IOException, InterruptedException 
	 */
	public static ArrayList<JSONArray> convert(File inputFile) throws IOException, InterruptedException {
		Workbook workbook;
		
		ArrayList<String[]> labelList = new ArrayList<String[]>();
		
		String[] baseDataLabels = {"Date / Time", "Event Id", "Failure Class", "UE Type", "Market",
				 "Operator", "Cell Id", "Duration", "Cause Code", "NE Version", "IMSI", 
				 "HIER3_ID", "HIER32_ID", "HIER321_ID"};
		String[] eventCauseLabels = {"Cause Code", "Event Id", "Description"};
		String[] failureClassLabels = {"Failure Class", "Description"};
		String[] ueTableLabels = {"TAC", "Marketing Name", "Manufacturer", "Access Capability", "Model", "Vendor Name", "UE Type", "OS", "Input Mode"};
		String[] mccMncLabels = {"MCC", "MNC", "Country", "Operator"};
		
		labelList.add(baseDataLabels);
		labelList.add(eventCauseLabels);
		labelList.add(failureClassLabels);
		labelList.add(ueTableLabels);
		labelList.add(mccMncLabels);
		
		Sheet sheet;
		Cell cell;
		CellType cellType;
		
		ArrayList<JSONArray> datasetTables = new ArrayList<JSONArray>();
		JSONArray baseData = new JSONArray();
		JSONArray eventCauseTable = new JSONArray();
		JSONArray failureClassTable = new JSONArray();
		JSONArray ueTable = new JSONArray();
		JSONArray mccMncTable = new JSONArray();
		
		try {
			workbook = Workbook.getWorkbook(inputFile);
			for(int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++){
				sheet = workbook.getSheet(sheetNumber);
				String[] sheetLabels = labelList.get(sheetNumber);
				JSONObject datasetRow = new JSONObject();
				for (int row = 1; row < sheet.getRows(); row++){
					for (int column = 0; column < sheet.getColumns(); column++) {
						cell = sheet.getCell(column, row);
						cellType = cell.getType();
						if(cellType == CellType.DATE){
							DateCell dateCell = (DateCell) cell;
							Date date = dateCell.getDate();
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							datasetRow.put(sheetLabels[column], formatter.format(date));
						}
						else{
							datasetRow.put(sheetLabels[column], cell.getContents());
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
		} catch (BiffException e) {
			System.out.println("BiffException: " + e.getMessage());
		}
		datasetTables.add(baseData);
		datasetTables.add(eventCauseTable);
		datasetTables.add(failureClassTable);
		datasetTables.add(ueTable);
		datasetTables.add(mccMncTable);
		
		return datasetTables;
	}
}
