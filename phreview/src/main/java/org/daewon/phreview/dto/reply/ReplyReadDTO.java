package org.daewon.phreview.dto.reply;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyReadDTO {

    private Long replyId;
    private String userName;

    @NotEmpty
    private String replyText;

    private boolean isReply;

    private String createAt;
    private String updateAt;
}
