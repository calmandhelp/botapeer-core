package com.botapeer.adapter;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUploader {
    String uploadImage(MultipartFile image) throws IOException;
}
