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
	 */
	public JSONArray convert() throws IOException  {
		File inputWorkbook = new File(inputFile);
		FileWriter jsonConvert = new FileWriter("/home/user1/json/convertedFile.json");
		Workbook workbook;
		ArrayList<String> labels = new ArrayList<String>();
		
		Sheet sheet;
		Cell cell;
		CellType type;
		String cellType;
		JSONArray baseData = new JSONArray();
		
		try {
			workbook = Workbook.getWorkbook(inputWorkbook);
			for(int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++){
				if(sheetNumber == 0){
					sheet = workbook.getSheet(sheetNumber);
					JSONObject obj = new JSONObject();
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
									obj.put(labels.get(column), formatter.format(date));
								}
								else{
									obj.put(labels.get(column), cell.getContents());
								}
							}
						}
						baseData.add(obj);
						obj = new JSONObject();
					}
					labels.clear();
				}
				else{
					sheet = workbook.getSheet(sheetNumber);
					jsonConvert = new FileWriter("/home/user1/json/" + sheetNumber + ".json");
					jsonConvert.append("[");
					for (int row = 0; row < sheet.getRows(); row++){
						if(row != 0){
							jsonConvert.append("{");
						}
						for (int column = 0; column < sheet.getColumns(); column++) {
							if(row == 0){
								cell = sheet.getCell(column, row);
								labels.add(cell.getContents());
							}
							else{
								cell = sheet.getCell(column, row);
								type = cell.getType();
								cellType = type.toString();
								
								jsonConvert.append("\"" + labels.get(column) + "\":");
								
								if(cellType.equals("Number")){
									jsonConvert.append(cell.getContents());
								}
								else if(cell.getType() == CellType.DATE){
									DateCell dateCell = (DateCell) cell;
									Date date = dateCell.getDate();
									SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
									jsonConvert.append("\"" + formatter.format(date) + "\"");
								}
								else{
									jsonConvert.append("\"" + cell.getContents() + "\"");
								}
								
								if(column != sheet.getColumns() - 1){
									jsonConvert.append(",");
								}
							}
						}
						if(row != 0 && row !=sheet.getRows() - 1){
							jsonConvert.append("},");
						}
						if(row == sheet.getRows() - 1){
							jsonConvert.append("}");
						}
					}
				
					labels.clear();
				
					jsonConvert.append("]");
					jsonConvert.close();
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return baseData;
	}
}