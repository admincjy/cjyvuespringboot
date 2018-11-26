/**
 * 
 */
package com.wecat.small.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.PageInfoSmall;

/**
 * @author Administrator
 *
 */
@Component
public class PoiUtils {
	
	public Map<String, Object> exportUtils(String category,String manager,
			String company,String subject,String title,String author,String comments,
			String sheetName,String[] cellValue){
		 Map<String, Object> map = new HashMap<String, Object>();
        //1.创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2.创建文档摘要
        workbook.createInformationProperties();
        //3.获取文档信息，并配置
        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
        //3.1文档类别
        dsi.setCategory(category);
        //3.2设置文档管理员
        dsi.setManager(manager);
        //3.3设置组织机构
        dsi.setCompany(company);
        //4.获取摘要信息并配置
        SummaryInformation si = workbook.getSummaryInformation();
        //4.1设置文档主题
        si.setSubject(subject);
        //4.2.设置文档标题
        si.setTitle(title);
        //4.3 设置文档作者
        si.setAuthor(author);
        //4.4设置文档备注
        si.setComments(comments);
        //创建Excel表单
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //创建日期显示格式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //定义列的宽度
        for (int i = 0; i <= cellValue.length; i++) {
            sheet.setColumnWidth(i, 15 * 256);
		}
        //5.设置表头
        HSSFRow headerRow = sheet.createRow(0);
        List<HSSFCell> cells=new ArrayList<>();
        for(int i = 0; i <= cellValue.length; i++){
        	 HSSFCell cell= headerRow.createCell(i);
    	     cell.setCellValue(cellValue[i]);
             cell.setCellStyle(headerStyle);
             cells.add(cell);
        }
        map.put("sheet", sheet);
        map.put("workbook", workbook);
        map.put("dateCellStyle", dateCellStyle);
        return map;
      }
	  
	
	  public List<EmployeeEntity> importUtils(MultipartFile file){
		  List<EmployeeEntity> emps = new ArrayList<>();
		  try {
		        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
	            //获取一共有多少sheet，然后遍历
		        int numberOfSheets = workbook.getNumberOfSheets();
		        for (int i = 0; i < numberOfSheets; i++) {
			        HSSFSheet sheet = workbook.getSheetAt(i);
			        //获取sheet中一共有多少行，遍历行（注意第一行是标题）
			        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
			        EmployeeEntity employeeEntity;
			        for (int j = 0; j < physicalNumberOfRows; j++) {
				        if (j == 0) {
				            continue;//标题行
				        }
				        HSSFRow row = sheet.getRow(j);
				        if (row == null) {
				            continue;//没数据
				        }
				        int physicalNumberOfCells = row.getPhysicalNumberOfCells();
				        employeeEntity = new EmployeeEntity();
				        for (int k = 0; k < physicalNumberOfCells; k++) {
				        HSSFCell cell = row.getCell(k);
				        switch (cell.getCellTypeEnum()) {
					        case STRING: {
					            String cellValue = cell.getStringCellValue();
					            if (cellValue == null) {
					                cellValue = "";
					            }
					            switch (k) {
					                case 1:
					                	employeeEntity.setName(cellValue);
					                    break;
					                case 2:
					                	employeeEntity.setWorkID(cellValue);
					                    break;
					                case 3:
					                	employeeEntity.setGender(cellValue);
					                    break;
					                case 5:
					                	employeeEntity.setIdCard(cellValue);
					                    break;
					                case 6:
					                	employeeEntity.setWedlock(cellValue);
					                    break;
					                case 7:
					                    employeeEntity.setNationId(5);
					                    break;
					                case 8:
					                	employeeEntity.setNativePlace(cellValue);
					                    break;
					                case 9:
					                    employeeEntity.setPoliticId(5);
					                    break;
					                case 10:
					                	employeeEntity.setPhone(cellValue);
					                    break;
					                case 11:
					                	employeeEntity.setAddress(cellValue);
					                    break;
					                case 12:
					                    employeeEntity.setDepartmentId(5);
					                    break;
					                case 13:
					                    employeeEntity.setJobLevelId(5);
					                    break;
					                case 14:
					                	employeeEntity.setPosId(5);
					                    break;
					                case 15:
					                	employeeEntity.setEngageForm(cellValue);
					                    break;
					                case 16:
					                	employeeEntity.setTiptopDegree(cellValue);
					                    break;
					                case 17:
					                	employeeEntity.setSpecialty(cellValue);
					                    break;
					                case 18:
					                	employeeEntity.setSchool(cellValue);
					                    break;
					                case 19:
					                case 20:
					                	employeeEntity.setWorkState(cellValue);
					                    break;
					                case 21:
					                	employeeEntity.setEmail(cellValue);
					                    break;
					            }
					        }
				        break;
				        default: {
				            switch (k) {
				                case 4:
				                	employeeEntity.setBirthday(cell.getDateCellValue());
				                    break;
				                case 19:
				                	employeeEntity.setBeginDate(cell.getDateCellValue());
				                    break;
				                case 22:
				                	employeeEntity.setContractTerm(cell.getNumericCellValue());
				                    break;
				                case 23:
				                	employeeEntity.setBeginContract(cell.getDateCellValue());
				                    break;
				                case 24:
				                	employeeEntity.setEndContract(cell.getDateCellValue());
				                    break;
				            }
				        }
				            break;
				        }
				        }
				            emps.add(employeeEntity);
			        }
	            }
		    } catch (IOException e) {
	            e.printStackTrace();
	        }
		return emps;
	  }

	
	   
	
}


	

