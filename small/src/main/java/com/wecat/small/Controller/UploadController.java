package com.wecat.small.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.wecat.small.common.BaseRespMsg;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${upload.path}")
    private String UPLOADED_FOLDER;



    @PostMapping("/upload")
    public BaseRespMsg singleFileUpload(@RequestParam("file") MultipartFile file
                                     ,@RequestParam(value = "folder",required = false,defaultValue = "")String folder) {

        if (file.isEmpty()) {
            return BaseRespMsg.error("请选择需要的上传文件");
        }
      
        try {
            String filename = UUID.randomUUID().toString().toLowerCase() + file.getOriginalFilename()
                    .substring(file.getOriginalFilename()
                                    .lastIndexOf("."),
                            file.getOriginalFilename().length());
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + folder +  filename);

            File dir = new File(UPLOADED_FOLDER + folder);
            if(!dir.exists()){
                dir.mkdirs();
            }


            Files.write(path, bytes);

            try {
                Runtime.getRuntime().exec("chmod 755 " + path);
            }catch (Exception e){

            }

            return new BaseRespMsg(0,UPLOADED_FOLDER + folder +  filename);
        } catch (IOException e) {
            return BaseRespMsg.error(e.getMessage());
        }

    }
    @GetMapping("/deletefile")
    public BaseRespMsg singleFileUpload(@RequestParam(value = "folderfile",required = true)String folderfile) {

        File dir = new File(UPLOADED_FOLDER + folderfile);
        if(dir.exists()){
            dir.delete();
        }

        return new BaseRespMsg(0,folderfile);
    }
}

