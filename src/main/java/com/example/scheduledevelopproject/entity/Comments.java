package com.example.scheduledevelopproject.entity;

import com.example.scheduledevelopproject.dto.request.CommentCreateRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comments extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(nullable = false)
    @Size(max = 200)
    private String contents;

    public Comments(CommentCreateRequestDto dto) {
        this.contents = dto.getContents();
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
