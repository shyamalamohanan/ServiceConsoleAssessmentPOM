package utils;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
public static String[][] readExcelData(String fileName, String sheetName) throws IOException {
		
		XSSFWorkbook wb = new XSSFWorkbook("./repository/"+fileName+".xlsx");
		XSSFSheet ws = wb.getSheet(sheetName);
		int rowCount = ws.getLastRowNum();
		int cellCount = ws.getRow(0).getLastCellNum();
		
		String data[][] = new String[rowCount][cellCount];
		
		for (int i= 1;i<=rowCount;i++) {
			for(int j = 0;j<cellCount;j++) {
				String cellValue = ws.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j] = cellValue;
			}
		}
		wb.close();
		return data;
	}

}
