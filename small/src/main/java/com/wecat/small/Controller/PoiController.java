/**
 * 
 */
package com.wecat.small.Controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wecat.small.Service.EmpBasicService;
import com.wecat.small.entity.EmployeeEntity;
import com.wecat.small.entity.EmployeeecEntity;
import com.wecat.small.entity.PageInfoSmall;
import com.wecat.small.utils.BaseRespMsg;
import com.wecat.small.utils.PoiUtils;

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/employee/basic")
public class PoiController {
	
	@Autowired PoiUtils poiUtils;
	
	@Autowired EmpBasicService empBasicService;
	
	
	@RequestMapping("exportEmp")
	public ResponseEntity<byte[]> exportEmp(){
		HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;

    	String category="员工信息";
    	String manager="江南一点雨";
		String company="XXX集团";
		String subject="员工信息表";
		String title="员工信息";
		String author="XXX集团";
		String comments="备注信息暂无";
		String sheetName="XXX集团员工信息表";
		String[] cellValue={"编号","姓名","工号","性别","出生日期","身份证号码","婚姻状况"
				            ,"民族","籍贯","政治面貌","电话号码","联系地址","所属部门",
				            "职称","职位","聘用形式","最高学历","专业","毕业院校","入职日期"
				            ,"在职状态","邮箱","合同期限(年)","合同起始日期","合同终止日期"};
        try {	
        Map<String, Object> map=poiUtils.exportUtils(category, manager, company, subject, title, author, comments, sheetName,cellValue);
        //6.装数据
        PageInfoSmall<EmployeeEntity> pageInfo=new PageInfoSmall<>();
        List<EmployeeEntity> emps=empBasicService.selectAllNoPage(pageInfo);
        for (int i = 0; i < emps.size(); i++) {
            HSSFRow row = ((HSSFSheet) map.get("sheet")).createRow(i + 1);
            EmployeeEntity emp = emps.get(i);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getName());
            row.createCell(2).setCellValue(emp.getWorkID());
            row.createCell(3).setCellValue(emp.getGender());
            HSSFCell birthdayCell = row.createCell(4);
            birthdayCell.setCellValue(emp.getBirthday());
            birthdayCell.setCellStyle((HSSFCellStyle) map.get("dateCellStyle"));
            row.createCell(5).setCellValue(emp.getIdCard());
            row.createCell(6).setCellValue(emp.getWedlock());
            row.createCell(7).setCellValue(emp.getNationId());
            row.createCell(8).setCellValue(emp.getNativePlace());
            row.createCell(9).setCellValue(emp.getPoliticId());
            row.createCell(10).setCellValue(emp.getPhone());
            row.createCell(11).setCellValue(emp.getAddress());
            row.createCell(12).setCellValue(emp.getDepartmentId());
            row.createCell(13).setCellValue(emp.getJobLevelId());
            row.createCell(14).setCellValue(emp.getPosId());
            row.createCell(15).setCellValue(emp.getEngageForm());
            row.createCell(16).setCellValue(emp.getTiptopDegree());
            row.createCell(17).setCellValue(emp.getSpecialty());
            row.createCell(18).setCellValue(emp.getSchool());
            HSSFCell beginDateCell = row.createCell(19);
            beginDateCell.setCellValue(emp.getBeginDate());
            beginDateCell.setCellStyle((HSSFCellStyle) map.get("dateCellStyle"));
            row.createCell(20).setCellValue(emp.getWorkState());
            row.createCell(21).setCellValue(emp.getEmail());
            row.createCell(22).setCellValue(emp.getContractTerm());
            HSSFCell beginContractCell = row.createCell(23);
            beginContractCell.setCellValue(emp.getBeginContract());
            beginContractCell.setCellStyle((HSSFCellStyle) map.get("dateCellStyle"));
            HSSFCell endContractCell = row.createCell(24);
            endContractCell.setCellValue(emp.getEndContract());
            endContractCell.setCellStyle((HSSFCellStyle) map.get("dateCellStyle"));
        }
        headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment",
			        new String("员工表.xls".getBytes("UTF-8"), "iso-8859-1"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        baos = new ByteArrayOutputStream();
        ((HSSFWorkbook) map.get("workbook")).write(baos);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
      }
	
	@RequestMapping(value = "/importEmp", method = RequestMethod.POST)
	public BaseRespMsg importEmp(MultipartFile file) {
        List<EmployeeEntity> emps = poiUtils.importUtils(file);
        int x = 0;
        for(EmployeeEntity entity:emps){    
        	x=empBasicService.insertSelective(entity);
        }
        if (x==1) {
			return new BaseRespMsg(0,"导入成功");
		}
        return BaseRespMsg.error("导入失败");
    }


}
