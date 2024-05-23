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
public class ReplyDTO {
    private Long replyId;
    private Users users;
    private Long reviewId;
    private Long userId;    // 특정한 유저 id를 선언

    @NotEmpty
    private String replyText;

    private Date createAt;
}
