package logisticsExcelTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		FileInputStream fis=new FileInputStream("D:\\Workspace\\Logistics\\GilbertFiles\\Gilbert2.0\\gilbert11-7-2022\\TEST NEW XPO RMA Order Upload-2022-09-08-20-49-09 (1).xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		int sheets=workbook.getNumberOfSheets();
		System.out.println(sheets);
		
		for(int i=0;i<sheets;i++) {
			
			if(workbook.getSheetName(i).equalsIgnoreCase("TEST NEW XPO RMA Order Upload")) {
				XSSFSheet sheet=workbook.getSheetAt(i);
				//System.out.println(sheet);
				Iterator<Row> rows=sheet.iterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce=firstrow.cellIterator();
				int k=0;
				while(ce.hasNext())
				{
					Cell value=ce.next();
					System.out.println(value.getStringCellValue());
				}
				k++;
			}
		}
		
	}

}
