package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UploadFileResponseDto;
import com.example.demo.exception.FileSizeException;
import com.example.demo.service.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0 
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 * 
	 * @param file
	 * @return UploadFileResponseDto
	 * @throws FileSizeException 
	 */
	@PostMapping("/upload")
	public ResponseEntity<UploadFileResponseDto> fileUpload(@RequestParam("file") MultipartFile file) throws FileSizeException {

		log.info(" :: fileUpload --- ");
		UploadFileResponseDto message = fileService.uploadFile(file);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
