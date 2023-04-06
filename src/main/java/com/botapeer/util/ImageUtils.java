package com.botapeer.util;

import com.botapeer.domain.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {
    static Logger logger = LoggerFactory.getLogger(ImageUtils.class);
    public static boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();
        if(contentType != null && contentType.startsWith("image/")) {
            return false;
        } else {
            logger.error("this file is not image: " + file.getName());
            return true;
        }
    }
}
