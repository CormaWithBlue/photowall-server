package space.xbgt.photowallserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.System;
import java.io.File;
import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
/**
 * controller
 *
 * @author xiebo
 **/
@RestController
public class GetFileList {
    
    @Value("${space.xbgt.author}")
    private String author;

    @Value("${space.xbgt.photoDirectory}")
    private String photoDirectory;

    private ArrayList<String> filelist = new ArrayList<String>();

    void getFiles(String filePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getFiles(file.getAbsolutePath());
            } else {
                String filename;
                filename = file.getAbsolutePath().replace("\\","/").replace(photoDirectory, "");
                System.out.println("filename "+filename);
                filelist.add(filename);
            }
        }
    }

    @RequestMapping("/GetFileList")
    public String method() {
        System.out.println("getfilelist received");
        getFiles(photoDirectory);
        JSONObject object = new JSONObject();
        object.put("FileList",filelist);
        return object.toJSONString();
    }

    public static void main(String[] args) {

    }
}