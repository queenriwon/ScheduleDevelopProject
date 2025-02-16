package com.example.scheduledevelopproject.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;


// 리퀘스트DTO에는 final을 작성해주지 말 것, reflect 되기 때문에 생성자도 만들어줄 필요없음
@Getter
@AllArgsConstructor
public class CommentRequestDto {

    @NotBlank(message = "댓글 내용은 필수 입력값 입니다.")
    @Size(max = 200, message = "댓글 내용은 200자까지 작성할 수 있습니다.")
    private final String contents;
}
