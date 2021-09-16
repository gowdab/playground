package com.bg.playground.service;

import com.bg.playground.model.FileScanResponseDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface FileScanService {

    List<FileScanResponseDto> scanFiles(MultipartFile[] files);

}