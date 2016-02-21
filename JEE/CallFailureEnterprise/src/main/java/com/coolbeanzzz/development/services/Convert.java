package com.coolbeanzzz.development.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Convert {

	public static void main(String[] args) throws IOException {
		Convert convert = new Convert();
		convert.setInputFile("./base_data.xls");
		convert.read();
	}

	private String inputFile;

	/**
	 * Sets the xls file to be converted to json
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	
	/**
	 * Creates a json file from each sheet in a provided xls file
	 */
	public void read() throws IOException  {
		File inputWorkbook = new File(inputFile);
		FileWriter jsonConvert = new FileWriter("/home/user1/Group2_Project/JEE/CallFailureEnterprise/convertedFile.json");
		Workbook workbook;
		ArrayList<String> labels = new ArrayList<String>();
		
		Sheet sheet;
		Cell cell;
		CellType type;
		String cellType;
		
		try {
			workbook = Workbook.getWorkbook(inputWorkbook);
			for(int sheetNumber = 0; sheetNumber < workbook.getNumberOfSheets(); sheetNumber++){
				sheet = workbook.getSheet(sheetNumber);
				jsonConvert = new FileWriter("/home/user1/Group2_Project/JEE/CallFailureEnterprise/" + sheet.getName() + ".json");
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
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}
}