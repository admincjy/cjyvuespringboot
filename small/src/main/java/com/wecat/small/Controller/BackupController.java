package com.wecat.small.Controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wecat.small.common.BaseRespMsg;
import com.wecat.small.common.SqlType;
import com.wecat.small.common.SystemControllerLog;
import com.wecat.small.service.BackupService;

@RestController
@RequestMapping("backup")
public class BackupController {
	
	@Autowired BackupService backupService;
	
	@Value("${upload.path}")
    private String UPLOADED_FOLDER;
	
	@SystemControllerLog(description="备份数据库",type=SqlType.OTHER)
	@RequestMapping(value = "/databasebackup",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseRespMsg databasebackup(HttpServletResponse response) throws Exception{
        String filePath=UPLOADED_FOLDER;
        String dbName="vhr";
        try {
                Runtime.getRuntime().exec(
                "cmd  /c  mysqldump -uroot -p123456 -P3306" + dbName + " > "
                        + filePath + "/" + dbName+new java.util.Date().getTime()
                        + ".sql");
            //备份的数据库名字为vhr，数据库账号是root和密码为123456
            System.out.println("success!!!");
           return new BaseRespMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseRespMsg.error();
        }
    }
	
	
	@SystemControllerLog(description="还原数据库",type=SqlType.OTHER)
	@RequestMapping(value = "/backData",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseRespMsg backData(HttpServletResponse response) throws Exception{
        String filePath=UPLOADED_FOLDER;
        String dbName="vhr";
        try {
                Runtime.getRuntime().exec(
               "cmd  /c  mysql -uroot -p123456 " + dbName + " < "
               + filePath + "/" + "vhr1545104078270"
               + ".sql");
             //还原的数据库名字为vhr，数据库账号是root和密码为123456
            System.out.println("success!!!");
           return new BaseRespMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return BaseRespMsg.error();
        }
    }
	
	
	@SystemControllerLog(description="清空非必要数据",type=SqlType.DELETE)
	@RequestMapping(value = "/cleanData",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public BaseRespMsg cleanData() throws Exception{
		try {
			int i=backupService.clean();
			if(i>0){
	            return new BaseRespMsg();
			}
			return BaseRespMsg.error();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return BaseRespMsg.error();
	    }
    }

}
