package com.example.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestDocument
{
    private static ObjectMapper jackson2ObjectMapper;
    
    public static void main(String[] args)
    {
        Logger logger = LoggerFactory.getLogger(TestDocument.class);
        
        jackson2ObjectMapper = new ObjectMapper();
        jackson2ObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        jackson2ObjectMapper.setSerializationInclusion(Include.NON_NULL);
        jackson2ObjectMapper.setSerializationInclusion(Include.NON_DEFAULT);
        
        try {
            byte[] array = read(new File("C:\\Users\\sdjscheah\\Desktop\\Professional JavaScript for Web Developers 3rd Edition.pdf"));
            
            MultipartFile testMulti = fileUpload("C:\\Users\\sdjscheah\\Desktop\\sample.jpg");
//            MultipartFile testMulti = fileUpload("C:\\\\Users\\\\sdjscheah\\\\Desktop\\\\Professional JavaScript for Web Developers 3rd Edition.pdf");
            
            OBTest testDetail = new OBTest();
            testDetail.setId("test");
            testDetail.setDocument(testMulti.getBytes());
            
            try {
                String data = getJackson2ObjectMapper().writeValueAsString(testDetail);
                String m = LogCensorUtil.censorSensitiveInformation(data, "document",  "JSON");
                
                logger.info(m);
                logger.info(data);
                    
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    
    public static byte[] read(File file) throws IOException {
//        if (file.length() > MAX_FILE_SIZE) {
//            throw new FileTooBigException(file);
//        }
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        }finally {
            try {
                if (ous != null)
                    ous.close();
            } catch (IOException e) {
            }

            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
            }
        }
        return ous.toByteArray();
    }
    
    public static ObjectMapper getJackson2ObjectMapper()
    {
        return jackson2ObjectMapper;
    }

    public static void setJackson2ObjectMapper(ObjectMapper jackson2ObjectMapper)
    {
        jackson2ObjectMapper = jackson2ObjectMapper;
    }
    
    
    public static MultipartFile fileUpload(String locationFile) {

        Path path = Paths.get(locationFile);
        String name = "Code.txt";
        String originalFileName = "Code.txt";
        String contentType = "text/plain";
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        
        MultipartFile file = new MockMultipartFile(name, originalFileName, contentType, content);

        return file;
    }
    
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
     
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
     
        return sb.toString();
    }
    
}
