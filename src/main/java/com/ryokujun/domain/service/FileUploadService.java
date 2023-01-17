package com.ryokujun.domain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.ryokujun.s3.FileUploadForm;

@Service
public class FileUploadService {

	private final AmazonS3 s3Client;

	public FileUploadService(AmazonS3 s3Client) {
		this.s3Client = s3Client;
	}

	public String fileUpload(FileUploadForm fileUploadForm, String s3PathName)
			throws IOException {
		DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		String extension = FilenameUtils.getExtension(fileUploadForm.getMultipartFile().getOriginalFilename())
				.toLowerCase();
		String fileName = fileUploadForm.getCreateAt().format(fm) + "_" + UUID.randomUUID() + "." + extension;
		File uploadFile = new File(fileName);

		try (FileOutputStream uploadFileStream = new FileOutputStream(uploadFile)) {
			byte[] bytes = fileUploadForm.getMultipartFile().getBytes();
			uploadFileStream.write(bytes);

			//引数：S3の格納先オブジェクト名,ファイル名,ファイル
			s3Client.putObject(s3PathName, fileName, uploadFile);
			uploadFile.delete();
			return fileName;
		} catch (AmazonServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

}
