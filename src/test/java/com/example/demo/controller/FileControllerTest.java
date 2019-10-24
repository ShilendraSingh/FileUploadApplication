package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.UploadFileResponseDto;
import com.example.demo.service.FileService;
import com.example.demo.service.FileServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class FileControllerTest {

	@Mock
	FileService fileService;

	@Mock
	FileServiceImpl fileServiceImpl;

	@InjectMocks
	FileController fileController;

	@Mock
	MultipartFile file;

	UploadFileResponseDto uploadFileResponseDto = null;

	@Before
	public void setUp() {
		uploadFileResponseDto = new UploadFileResponseDto();
		uploadFileResponseDto.setMessage("file uploaded successfully.");
		uploadFileResponseDto.setStatusCode(200);
	}

	@Test
	void test() throws Exception {
		Mockito.when(fileService.uploadFile(file)).thenReturn(uploadFileResponseDto);
//		ResponseEntity<UploadFileResponseDto> actulaResult = fileController.uploadFile(file);
//		assertEquals(200, actulaResult);
	}

}
