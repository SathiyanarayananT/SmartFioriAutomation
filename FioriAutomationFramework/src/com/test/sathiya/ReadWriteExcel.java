package com.test.sathiya;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadWriteExcel {
	
	public String Read(String Field){
		String value = "" ;
		try {
			String FILE_NAME = "Data/Data.xlsx";
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
                                 
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();

                if(currentRow.getCell(0).toString().equals(Field)){
//                	Iterator<Cell> itera = currentRow.iterator();
//                	for(int i=1 ;itera.hasNext();i++){
                		value =  currentRow.getCell(1).toString();
//                    	System.out.println("Matched");
                    	break;
                }
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return value;
	}

	public List<String> ReadArray(String Field){
		List<String> ls = new ArrayList<String>();
		try {
			String FILE_NAME = "Data/Data.xlsx";
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(1);
                                 
//            Iterator<Row> iterator = datatypeSheet.iterator();
            int rows = datatypeSheet.getPhysicalNumberOfRows();
            Row row;
            for(int i=0;i<rows ; i++){
            	row = datatypeSheet.getRow(i);
            	int cells = row.getPhysicalNumberOfCells();
            	Cell Firstcell = row.getCell(0);
            	Cell cell;
            	if(Firstcell.getStringCellValue().equals(Field)){
            		for(int j=1;j<cells;j++){
                		cell = row.getCell(j);
                		if(!cell.getStringCellValue().equals("NA") && cell.getStringCellValue()!=null && !cell.getStringCellValue().isEmpty()){
                			ls.add(cell.getStringCellValue());
                		}
                	}
            		break;
            	}
            }


            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return ls;
	}
	
	public void Write(String Field , String Value){
		try {
			String FILE_NAME = "Data/Data.xlsx";
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            System.out.println(Field + " "+Value);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                System.out.println(currentRow.getCell(0).toString());
                if(currentRow.getCell(0).toString().equals(Field)){
                	System.out.println(Field + " "+ Value);
                	
                	Cell cell = currentRow.getCell(1);
                	if (cell == null) {
                        cell = currentRow.createCell(1);
                    }
                	cell.setCellValue(Value);
                	break;
                } 
            }
            
            FileOutputStream outputStream = new FileOutputStream(new File(FILE_NAME));
            workbook.write(outputStream);
            workbook.close();
            
		}catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}
