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
public class ReplyUpdateDTO {

    @NotEmpty
    private Long replyId;
    private String replyText;
    private LocalDateTime updateAt;
}
