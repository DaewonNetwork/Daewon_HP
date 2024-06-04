package org.daewon.phreview.dto.review;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Data
public class ReviewUploadDTO {

    private ReviewDTO reviewDTO;
    private Optional<MultipartFile> files;
}
