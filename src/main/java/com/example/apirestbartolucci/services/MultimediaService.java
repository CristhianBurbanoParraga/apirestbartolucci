/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apirestbartolucci.services;

import com.example.apirestbartolucci.dtos.multimedia.OtherMultimediaDto;
import java.io.IOException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author criss
 */
@Service
public class MultimediaService {

    @Autowired
    CloudinaryService cloudinaryService;

    public OtherMultimediaDto SaveOtherMultimedia(MultipartFile multipartFile) {
        try {
            Map map = cloudinaryService.upload(multipartFile);
            OtherMultimediaDto otherMultimediaDto
                    = new OtherMultimediaDto((String) map.get("public_id"),
                            (String) map.get("url"));
            return otherMultimediaDto;
        } catch (IOException ioe) {
            return null;
        }
    }
}
