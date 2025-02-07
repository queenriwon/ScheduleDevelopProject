package com.example.scheduledevelopproject.dto.response;

import com.example.scheduledevelopproject.entity.Comments;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String todoTitle;
    private String name;
    private String contents;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comments comments) {
        this.id = comments.getId();
        this.todoTitle = comments.getSchedules().getTodoTitle();
        this.name = comments.getUsers().getName();
        this.contents = comments.getContents();
        this.createdAt = comments.getCreatedAt();
        this.modifiedAt = comments.getModifiedAt();
    }
}
