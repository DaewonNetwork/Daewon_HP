package org.daewon.phreview.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daewon.phreview.domain.Users;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDTO {
    private Long replyId;

    private Long reviewId;
    private Long userId;    // 특정한 유저 id를 선언

    @NotEmpty
    private String replyText;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
