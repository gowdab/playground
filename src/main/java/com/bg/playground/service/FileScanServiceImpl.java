package com.bg.playground.service;

import com.bg.playground.clamav.ClamAVClient;
import com.bg.playground.clamav.exceptions.ClamAVSizeLimitException;
import com.bg.playground.model.FileScanResponseDto;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.bg.playground.clamav.ClamAVClient.*;

@Service
public class FileScanServiceImpl implements FileScanService {

    private static Logger LOGGER = LoggerFactory.getLogger(FileScanServiceImpl.class);

    @Autowired
    private ClamAVClient clamAVClient;

    public List<FileScanResponseDto> scanFiles(MultipartFile[] files) {
        LOGGER.info("File received = {} ", (files != null ? files.length : null));
        return Arrays.stream(files).map(multipartFile -> {
            FileScanResponseDto fileScanResponseDto = new FileScanResponseDto();
            long startTime = System.currentTimeMillis();
            fileScanResponseDto.setUploadTime(startTime);
            try {
                //TODO: THIS IS WHERE THE SCANNING HAPPENS
                byte[] response = clamAVClient.scan(multipartFile.getInputStream(), fileScanResponseDto);
                System.out.println("response = " + response);
                Boolean status = isCleanReply(response);
                fileScanResponseDto.setDetected(status != null ? !status : status);
                LOGGER.info("File Scanned = {} Clam AV Response = {} ", multipartFile.getOriginalFilename(), (status != null ? status : null));
            } catch (ClamAVSizeLimitException exception) {
                LOGGER.error("Limit error while scanning using clam av = {} ", exception.getMessage());
                fileScanResponseDto.setErrorMessage(exception.getMessage());
            } catch (Exception e) {
                LOGGER.error("Exception occurred while scanning using clam av = {} ", e.getMessage());
                fileScanResponseDto.setErrorMessage(e.getMessage());
            }
            fileScanResponseDto.setFileName(multipartFile.getOriginalFilename());
            fileScanResponseDto.setFileType(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
            fileScanResponseDto.setSize(multipartFile.getSize());
            fileScanResponseDto.setScanTimeInMilliSec(System.currentTimeMillis() - startTime);
            return fileScanResponseDto;
        }).collect(Collectors.toList());
    }
}