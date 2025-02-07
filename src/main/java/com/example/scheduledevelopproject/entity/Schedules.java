package com.example.scheduledevelopproject.entity;

import com.example.scheduledevelopproject.dto.request.ScheduleCreateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "schedules")
@NoArgsConstructor
public class Schedules extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(nullable = false)
    private String todoTitle;

    @Column(nullable = false, columnDefinition = "longtext")
    private String todoContents;

    public Schedules(ScheduleCreateRequestDto dto) {
        this.todoTitle = dto.getTodoTitle();
        this.todoContents = dto.getTodoContents();
    }

    public void updateTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }
    public void updateTodoContents(String todoContents) {
        this.todoContents = todoContents;
    }
}
