package com.bg.playground.controller;

import com.bg.playground.model.Expense;
import com.bg.playground.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> allExpenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(allExpenses);
    }

    @GetMapping(value = "/file/write")
    public ResponseEntity<InputStreamResource> getFile(HttpServletResponse response) throws IOException {
//        Path path = Paths.get("/Users/byeregowda/Downloads/1OfferLetter_COSales.docx");
//        try {
//            long size = Files.size(path);
//            String contentType = Files.probeContentType(path);
//            response.setContentType(contentType);
//            response.setHeader("Content-Disposition", "inline; filename=\"1OfferLetter_COSales.docx\"");
//            response.setContentLength(Math.toIntExact(size));
//            Files.copy(path, response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Path path = Paths.get("/Users/byeregowda/Downloads/VaibhavShukla_Resume.pdf");
        String contentType = Files.probeContentType(path);
        System.out.println("contentType = " + contentType);
        File file = new File("/Users/byeregowda/Downloads/VaibhavShukla_Resume.pdf");
        String fileName = "VaibhavShukla_Resume.pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "inline;filename=" +fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/pdf"))
                .body(resource);


    }

}
