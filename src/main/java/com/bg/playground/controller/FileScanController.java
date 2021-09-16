package com.bg.playground.controller;

import com.bg.playground.model.FileScanResponseDto;
import com.bg.playground.model.ResponseDto;
import com.bg.playground.service.FileScanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import fi.solita.clamav.ClamAVClient;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/scan")
public class FileScanController {

  private static Logger LOGGER = LoggerFactory.getLogger(FileScanController.class);

  private static final String VIRUS ="X5O!P%@AP[4\\PZX54(P^)7CC)7}$EICAR-STANDARD-ANTIVIRUS-TEST-FILE!$H+H*";

  @Autowired
  private FileScanService fileScanService;

    @PostMapping(value = "/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto<List<FileScanResponseDto>> uploadFiles(@RequestParam("fileData") MultipartFile files) {
        return new ResponseDto<>(fileScanService.scanFiles(new MultipartFile[]{files}));
    }

  @PostMapping(value = "/upload2" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseDto<List<FileScanResponseDto>> uploadFiles2(@RequestParam("files") MultipartFile files) {

      ClamAVClient clamAVClient = new ClamAVClient("",56789);
    try {
      clamAVClient.scan("byere".getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ResponseDto<>(fileScanService.scanFiles(new MultipartFile[]{files}));
  }

  @GetMapping("/pleaseWork")
  public boolean testScan() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    //headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    HttpEntity<String> entity = new HttpEntity("VIRUS".getBytes(), headers);
    ResponseEntity<Boolean> exchange = restTemplate.exchange("http://172.25.120.87:8080/jhinternal/action/scanFile", HttpMethod.POST, entity, boolean.class);
    System.out.println("exchange = " + exchange.getBody().booleanValue());
    return exchange.getBody().booleanValue();
  }



}