package org.daewon.phreview.dto.Reply;

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

    private String userName;

    @NotEmpty
    private String replyText;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
