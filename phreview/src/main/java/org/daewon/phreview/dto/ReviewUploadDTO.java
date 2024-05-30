package org.daewon.phreview.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ReviewUploadDTO {

    private ReviewDTO reviewDTO;
    private List<MultipartFile> files;
}
