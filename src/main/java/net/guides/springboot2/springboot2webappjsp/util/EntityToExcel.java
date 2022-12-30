package com.csme.upgradation.tool;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * Utility class to extract JSP fields and Description.
 * @author CSME-krishna.prasad
 *
 */
public class EntityToExcel {

	private Workbook writeWorkbook = new HSSFWorkbook();
	private CellStyle backGroundStyle =null;
	private CellStyle leftDataCellStyle=null;
	private CellStyle rightDataCellStyle=null;

	private Font font ;

	public EntityToExcel() {
		font= writeWorkbook.createFont();
		font.setColor(IndexedColors.WHITE.getIndex());
		rightDataCellStyle=rightCellStyle();
		leftDataCellStyle=leftCellStyle();
	}
	private void changeCellBackgroundColor(Cell cell) {
		if(backGroundStyle==null) {
			backGroundStyle = writeWorkbook.createCellStyle(); 
		}
		backGroundStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
		backGroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		backGroundStyle.setFont(font);
		cell.setCellStyle(backGroundStyle);
	}

	private void setDataCellStyle(Cell cell, boolean... rightAlign) {
		boolean rightAlignNeeded = rightAlign.length > 0 ? rightAlign[0] : false;
		if(rightAlignNeeded) {
			cell.setCellStyle(rightDataCellStyle);
		}else {
			cell.setCellStyle(leftDataCellStyle);
		}
	}



	private CellStyle rightCellStyle() {
		rightDataCellStyle = writeWorkbook.createCellStyle();
		rightDataCellStyle.setAlignment(HorizontalAlignment.RIGHT);
		rightDataCellStyle.setBorderBottom(BorderStyle.THIN);
		rightDataCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		rightDataCellStyle.setBorderLeft(BorderStyle.THIN);
		rightDataCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		rightDataCellStyle.setBorderRight(BorderStyle.THIN);
		rightDataCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		rightDataCellStyle.setBorderTop(BorderStyle.THIN);
		rightDataCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return rightDataCellStyle;
	}
	private CellStyle leftCellStyle() {
		leftDataCellStyle = writeWorkbook.createCellStyle();
		leftDataCellStyle.setAlignment(HorizontalAlignment.LEFT);
		leftDataCellStyle.setBorderBottom(BorderStyle.THIN);
		leftDataCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		leftDataCellStyle.setBorderLeft(BorderStyle.THIN);
		leftDataCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		leftDataCellStyle.setBorderRight(BorderStyle.THIN);
		leftDataCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		leftDataCellStyle.setBorderTop(BorderStyle.THIN);
		leftDataCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		return leftDataCellStyle;
	}
	/**
	 * Method to write the JSP fields into a Sheet
	 * @param sheetName
	 * @param jspFieldList
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void writeIntoExcelSheet(String sheetName,List<JSPFieldData> jspFieldList) throws FileNotFoundException, IOException {
		try {
			Sheet sheet1 = writeWorkbook.createSheet(sheetName);
			sheet1.setColumnWidth(0, 8000);
			sheet1.setColumnWidth(1, 12000);
			sheet1.setColumnWidth(2, 3500);
			sheet1.setColumnWidth(3, 20000);
			sheet1.setColumnWidth(4, 3500);
			
			Row headerRow = sheet1.createRow(0);
			Cell cell1;
			cell1 = headerRow.createCell(0);changeCellBackgroundColor(cell1);  cell1.setCellValue("ID");
			cell1 = headerRow.createCell(1);changeCellBackgroundColor(cell1);  cell1.setCellValue("JSP Description");
			cell1 = headerRow.createCell(2);changeCellBackgroundColor(cell1);  cell1.setCellValue("JSP Type");
			cell1 = headerRow.createCell(3);changeCellBackgroundColor(cell1);  cell1.setCellValue("List of Values in JSP");
			cell1 = headerRow.createCell(4);changeCellBackgroundColor(cell1);  cell1.setCellValue("DB Type");
			cell1 = headerRow.createCell(5);changeCellBackgroundColor(cell1);  cell1.setCellValue("DB Length");
			int i=1;
			for (JSPFieldData jspFieldData : jspFieldList) {
				Row dataRow = sheet1.createRow(i++);
				cell1 = dataRow.createCell(0);setDataCellStyle(cell1);  cell1.setCellValue(jspFieldData.getId());
				cell1 = dataRow.createCell(1);setDataCellStyle(cell1);  cell1.setCellValue(jspFieldData.getDescription());
				cell1 = dataRow.createCell(2);setDataCellStyle(cell1,true);  cell1.setCellValue(jspFieldData.getClassName());
				cell1 = dataRow.createCell(3);setDataCellStyle(cell1);  cell1.setCellValue(jspFieldData.getOptionValues());
				cell1 = dataRow.createCell(4);setDataCellStyle(cell1,true);  cell1.setCellValue(jspFieldData.getAmount());
			}
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				writeWorkbook.write(bos);
			} finally {
			    bos.close();
			}
			byte[] bytes = bos.toByteArray();

            OutputStream os = new FileOutputStream(new File("_Fields_Data_From Butes.xls"));
            os.write(bytes);
            os.close();
			FileOutputStream fileOut = new FileOutputStream("_Fields_Data.xls");
			writeWorkbook.write(fileOut);
			fileOut.close();
			writeWorkbook.close();
		} catch (java.lang.IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		JSPFieldData data=new JSPFieldData();
		data.setId("10001");
		data.setDescription("my description");
		data.setClassName("kpclass");
		data.setOptionValues("1,343,8099");
		List<JSPFieldData> li=new ArrayList<JSPFieldData>();
		li.add(data);
		EntityToExcel e=new EntityToExcel();
		e.writeIntoExcelSheet("a", li);
	}
}
