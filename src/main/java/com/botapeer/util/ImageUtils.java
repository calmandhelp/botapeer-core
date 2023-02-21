package com.botapeer.util;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.botapeer.domain.service.FileUploadService;
import com.botapeer.s3.FileUploadForm;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ImageUtils {

	private final FileUploadService fileUploadService;

	Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	public String uploadImage(MultipartFile image) {
		if (!ObjectUtils.isEmpty(image)) {
			FileUploadForm fileUploadForm = new FileUploadForm();
			fileUploadForm.setMultipartFile(image);
			fileUploadForm.setCreateAt(LocalDateTime.now());
			try {
				String fileName = fileUploadService.fileUpload(fileUploadForm, "image.botapeer.com/images");
				return fileName;
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return null;
	}

}
