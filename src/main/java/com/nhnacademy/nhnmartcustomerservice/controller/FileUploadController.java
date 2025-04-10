package com.nhnacademy.nhnmartcustomerservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    private static final String UPLOAD_DIR = "/Users/kimdoyun/Desktop/";

    @PostMapping("/fileUpload")
    public String processUpload(@RequestParam("file")MultipartFile file, Model model) throws IOException {

        file.transferTo(Paths.get(UPLOAD_DIR + file.getOriginalFilename()));

        model.addAttribute("fileName", file.getOriginalFilename());
        model.addAttribute("size", file.getSize());

        return "";
    }

}
