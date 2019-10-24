package com.example.demo.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UploadFileResponseDto;
import com.example.demo.exception.FileSizeException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author ShilendraSingh
 * @since   2019-09-23
 * @version 1.0
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

	private static final String FILE_PATH = "C:\\Users\\User1\\Desktop\\UploadTest\\";
	private static final String FILE_EXCEPTION_MESSAGE = "File size is zero";
	private static final String FILE_UPLOAD_EXCEPTION_MESSAGE = "file could not uploaded.";
	private static final String FILE_UPLOAD_MESSAGE = "file uploaded successfully.";

	/**
	 * 
	 * @param file
	 * @return UploadFileResponseDto
	 * @throws FileSizeException
	 */
	@Override
	public UploadFileResponseDto uploadFile(MultipartFile file) throws FileSizeException {

		log.info(" :: fileUpload --- START --- ");
		UploadFileResponseDto UploadFileResponseDto = new UploadFileResponseDto();
		if (file.getSize() == 0) {
			throw new FileSizeException(FILE_EXCEPTION_MESSAGE);
		}
		BufferedOutputStream stream = null;
		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(FILE_PATH + file.getOriginalFilename());
			File filePath = new File(path.toString());
			stream = new BufferedOutputStream(new FileOutputStream(filePath));
			stream.write(bytes);
			Files.write(path, bytes);
			UploadFileResponseDto.setMessage(FILE_UPLOAD_MESSAGE);
		} catch (IOException e) {
			log.error(" :: uploadFile -- {}", e.getMessage());
			UploadFileResponseDto.setMessage(FILE_UPLOAD_EXCEPTION_MESSAGE);
		} finally {
			try {
				stream.flush();
				stream.close();
			} catch (Exception e) {
				log.error(" :: uploadFile -- {}", e.getMessage());
				UploadFileResponseDto.setMessage(FILE_UPLOAD_EXCEPTION_MESSAGE);
			}
		}
		UploadFileResponseDto.setStatusCode(200);
		log.info(" :: fileUpload --- end --- ");
		return UploadFileResponseDto;
	}
}
