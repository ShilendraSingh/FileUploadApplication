package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UploadFileResponseDto;
import com.example.demo.exception.FileSizeException;

public interface FileService {

	public UploadFileResponseDto uploadFile (MultipartFile file) throws FileSizeException;

}
