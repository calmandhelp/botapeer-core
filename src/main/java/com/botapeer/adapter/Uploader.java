package com.botapeer.adapter;

import java.io.IOException;
import java.time.LocalDateTime;

import com.botapeer.constants.AppConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Uploader implements IUploader{

	private final FileUploadService fileUploadService;

	Logger logger = LoggerFactory.getLogger(Uploader.class);

	@Override
	public String uploadImage(MultipartFile image) throws IOException {
		if (!ObjectUtils.isEmpty(image)) {
			FileUploadForm fileUploadForm = new FileUploadForm();
			fileUploadForm.setMultipartFile(image);
			fileUploadForm.setCreateAt(LocalDateTime.now());
			try {
				String fileName = fileUploadService.fileUpload(fileUploadForm, AppConstants.s3Path);
				return fileName;
			} catch (IOException e) {
				throw new IOException(e.getMessage());
			}
		}
		return null;
	}

}
