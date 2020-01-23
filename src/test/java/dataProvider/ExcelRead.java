package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
	FileInputStream fis;
	Workbook book;
	Sheet sheet;
	Row row;
	Cell cell;
	public ExcelRead(String filePath, String fileName)  {
		try {
			fis=new FileInputStream(new File(filePath));
		} catch (FileNotFoundException e) {
			System.out.println(fileName+"error in file path: "+fileName);
			e.printStackTrace();
		}
		try {
			book=new XSSFWorkbook(fis);
			sheet=book.getSheetAt(1);
		} catch (IOException e) {
			System.out.println(" problem in book object creation");
			e.printStackTrace();
		}
	}
	
	public int NoOfRows() {
		int n=sheet.getLastRowNum();
		return n;
	}
	
	public String read(int rowNo, String colName)
	{ 	String val=null;
		int colNo=colNumber(colName);
		cell=sheet.getRow(rowNo).getCell(colNo);
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			return Long.toString(Math.round(cell.getNumericCellValue()));
		case STRING:
			return cell.getStringCellValue();
		default:System.out.println("is not int or string");
			break;
		}
		return val;
	}
	public int colNumber(String colName) {
		int colNo = 0;
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++) {
			cell=row.getCell(i);
			if(colName.equals(cell.getStringCellValue())) {
				colNo=i;
				break;
			}
		}
		return colNo;
	}

}
