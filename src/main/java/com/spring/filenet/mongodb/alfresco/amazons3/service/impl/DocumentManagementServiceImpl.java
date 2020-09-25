package com.spring.filenet.mongodb.alfresco.amazons3.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.spring.filenet.mongodb.alfresco.amazons3.config.ApplicationProperties;
import com.spring.filenet.mongodb.alfresco.amazons3.service.DocumentManagementService;

@Service
public class DocumentManagementServiceImpl implements DocumentManagementService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentManagementServiceImpl.class);

	@Autowired
	private AmazonS3 amazonS3Client;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Override
	public void uploadMultipleFiles(List<MultipartFile> files) {
		if (files != null) {
			files.forEach(multipartFile -> {
				File file = convertMultiPartFileToFile(multipartFile);
				String uniqueFileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
				uploadFileToS3bucket(uniqueFileName, file, applicationProperties.getAwsServices().getBucketName());
			});
		}
	}

	private void uploadFileToS3bucket(String fileName, File file, String bucketName) {
		amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));

	}

	@SuppressWarnings("unused")
	private S3Object downloadFileFromS3bucket(String fileName, String bucketName) {
		S3Object object = amazonS3Client.getObject(bucketName, fileName);
		return object;

	}

	@SuppressWarnings("unused")
	private void deleteFileFromS3bucket(String fileName, String bucketName) {
		amazonS3Client.deleteObject(bucketName, fileName);
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			LOGGER.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}
}
