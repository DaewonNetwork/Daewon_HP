package org.daewon.phreview.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daewon.phreview.domain.Users;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private Long reviewId;
    private Users users;
    private Long phId;      // 특정한 병원 번호를 선언
    private Long userId;    // 특정한 유저 id를 선언
    @NotEmpty
    private String reviewText;

    private int star;

    private Date createAt;
}
