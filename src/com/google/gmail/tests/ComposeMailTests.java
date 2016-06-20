package com.google.gmail.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jxl.read.biff.BiffException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gmail.ComposeMailPage;


public class ComposeMailTests {
	@DataProvider(name="composemaildata")
	public Object[][] readComposemailData() throws FileNotFoundException, IOException, BiffException{
		/*Properties props = new Properties();
		props.load(new FileReader("LoginTestData.properties"));
		 */
		List<String> lscellvalues = new ArrayList<String>();
/*		String to="";
		String subject="";
		String msgbody="";*/
		File file = new File("D:\\LoginTestData.xls");
		if(file.exists()){

			/*		Workbook workbook = Workbook.getWorkbook(new FileInputStream(file));
		Sheet sheet = workbook.getSheet(1);
		Cell[] cell = sheet.getRow(1);
		to = cell[0].getContents();
		subject = cell[1].getContents();
		msgbody = cell[2].getContents();*/
		

			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = workbook.getSheetAt(1);
			XSSFRow row = sheet.getRow(1);
			Iterator<org.apache.poi.ss.usermodel.Cell> celliterator = row.cellIterator();
			while(celliterator.hasNext())
			{
				org.apache.poi.ss.usermodel.Cell cell = celliterator.next();
				lscellvalues.add(cell.getStringCellValue());
			}
			
			/*XSSFCell cell1 = row.getCell(0);
			XSSFCell cell2 = row.getCell(1);
			XSSFCell cell3 = row.getCell(2);*/

			/*to = cell1.getStringCellValue();
			subject = cell2.getStringCellValue();
			msgbody = cell3.getStringCellValue();*/
		}
		String[] str= new String[3];
		str[0] = lscellvalues.get(0);
		str[1] = lscellvalues.get(1);
		str[2] = lscellvalues.get(2);

		String[][] str2 = new String[1][2];
		str2[0] = str;

		return str2;
	}

	@Test(dataProvider="composemaildata")
	public void composemailTest(String toaddress, String subject, String msgbody) throws FileNotFoundException, IOException{
		//Declare test data
		/*String username="anji.it26@gmail.com";
		String password = "abcdef";*/

		/*Properties props = new Properties();
		props.load(new FileReader("LoginTestData.properties"));*/

		//Do a required action
		ComposeMailPage composemailpage = new ComposeMailPage();
		composemailpage.composemail(toaddress, subject,msgbody);

		//Verify and Validate

	}
}
