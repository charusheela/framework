package utils;

import java.io.FileInputStream;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class FileInput {

	/**
	 * Method to Extract Data from Excel Sheet
	 */
	public Sheet SheetData() throws BiffException, IOException, NullPointerException {

		String FilePath = "...\\mobileTest\\resources\\TestData.xlsx";// excelpath
																															

		FileInputStream fileInputStream = new FileInputStream(FilePath);
		Workbook wb = Workbook.getWorkbook(fileInputStream);
		Sheet sheet = wb.getSheet(0);// To get access of sheet1

		return sheet;
	}

	/**
	 * Method to Extract Username from obtained data
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public String Username() throws BiffException, IOException, NullPointerException {

		String username = SheetData().getCell(0, 1).getContents();
		return username;
	}

	/**
	 * Method to Extract Password from obtained data
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public String Password() throws BiffException, IOException, NullPointerException {

		String password = SheetData().getCell(1, 1).getContents();
		return password;
	}

	/**
	 * Method to Extract SearchItem(Item to be searched) from obtained data
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public String SearchItem() throws BiffException, IOException {

		String searchItem = SheetData().getCell(2, 1).getContents();
		return searchItem;
	}

	/**
	 * Method to Extract Bank Name from obtained data
	 * 
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 * @throws NullPointerException
	 */
	public String BankName() throws BiffException, IOException, NullPointerException {

		String bankName = SheetData().getCell(3, 1).getContents();
		return bankName;
	}

}
