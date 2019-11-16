package space.xbgt.photowallserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;

import java.lang.System;
import java.io.File;
/**
 * controller
 *
 * @author xiebo
 **/
@RestController
public class UpLoadFileSuc {
    
    @Value("${space.xbgt.photoDirectory}")
    private String photoDirectory;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)      
    @ResponseBody      
    public String handleFileUpload(HttpServletRequest request) {
     
        MultipartHttpServletRequest mreq = ((MultipartHttpServletRequest) request);
        Map<String,String[]> pmap = mreq.getParameterMap();
        for(String key : pmap.keySet()){
            String filename = key;
            String filepath = pmap.get(key)[0];
            
            File file = new File(filepath);
            String newname = file.getParent() + "/" + filename;
            file.renameTo(new File(newname));

            System.out.println(filepath);
            System.out.println(newname);
        }  
        return "upload successful";    
    }     

    public static void main(String[] args) {

    }
}