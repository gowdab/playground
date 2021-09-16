package com.bg.playground.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class FileTestWriteController {


    @GetMapping("/file/write")
    public ModelAndView getFile(HttpServletResponse response) {

        Path path = Paths.get("/Users/byeregowda/Downloads/1OfferLetter_COSales.docx");

        try {
            long size = Files.size(path);
            String contentType = Files.probeContentType(path);
            response.setContentType(contentType);
            response.setContentLength(Math.toIntExact(size));
            Files.copy(path, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            IOUtils.write(sampleHtmlContent, response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return null;
    }


}
