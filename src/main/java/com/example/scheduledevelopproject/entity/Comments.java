package com.example.scheduledevelopproject.entity;

import com.example.scheduledevelopproject.dto.request.CommentRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @ManyToOne(fetch = FetchType.LAZY)  // 사이드 이펙트를 줄일 수있음 (해당 테이블만 가져옴)
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    //ManyToOne으로 양방향을 모두 사용할 수 있음

    @Column(nullable = false)
    @Size(max = 200)
    private String contents;

    public Comments(CommentRequestDto dto) {
        this.contents = dto.getContents();
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
