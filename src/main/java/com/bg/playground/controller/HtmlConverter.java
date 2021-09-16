package com.bg.playground.controller;

import com.jobvite.service.document.client.DocumentService;
import com.jobvite.service.document.client.DocumentServiceImpl;
import com.jobvite.service.model.FileType;
import com.jobvite.service.model.PreviewResult;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/html-converter")
public class HtmlConverter {

    @GetMapping("/{file}")
    public String convertToHtml(@PathVariable("file") String file, HttpServletResponse response) throws IOException {

        Path path = Paths.get("/Users/byeregowda/Downloads/"+file);
        byte[] data = Files.readAllBytes(path);
        DocumentServiceImpl service = new DocumentServiceImpl();
        service.setDocumentServiceConversionUrl("http://dc.qa.jobvite.net/dc/convert");
        PreviewResult preview = service.preview(data, FileType.DOCX);
        String convertedResult = preview.getConvertedResult();
        System.out.println("convertedResult = " + convertedResult);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        response.setContentType("text/html");
        response.setContentLength(convertedResult.length());
        try {
            IOUtils.write(convertedResult, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;



    }


}
