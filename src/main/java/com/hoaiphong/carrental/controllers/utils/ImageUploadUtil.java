package com.hoaiphong.carrental.controllers.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;

import java.nio.file.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class ImageUploadUtil {

    public String uploadImage(MultipartFile imageFile, String oldImagePath, Model model, BindingResult bindingResult) {
        if (imageFile.getOriginalFilename().isEmpty()) {
            // Nếu không có tệp mới, trả về đường dẫn ảnh cũ
            return oldImagePath;
        } else {
            try {
                byte[] bytes = imageFile.getBytes();
                LocalDateTime date = LocalDateTime.now();
                Path folder = Paths.get("src/main/resources/static/images/cars/" + date.getYear() + "/"
                        + date.getMonthValue() + "/" + date.getDayOfMonth());
                if (!Files.exists(folder)) {
                    Files.createDirectories(folder);
                }
                String originalFileName = imageFile.getOriginalFilename();
                Long epochTime = Instant.now().getEpochSecond();
                String fileName = originalFileName.substring(0, originalFileName.lastIndexOf(".")) + "-" + epochTime
                        + originalFileName.substring(originalFileName.lastIndexOf("."));
                Path path = Paths.get(folder.toString(), fileName);
                Files.write(path, bytes);
                return folder.toString().replace("src\\main\\resources\\static", "") + "/" + fileName;
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("message", "Failed to upload image");
                bindingResult.rejectValue("image", "image", "Failed to upload image");
                return null; // Trả về null nếu upload thất bại
            }
        }
    }
}
