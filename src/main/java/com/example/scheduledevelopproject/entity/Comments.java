package com.example.scheduledevelopproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Comments extends BaseEntity{

    @Column(nullable = false)
    @Size(max = 200)
    private String contents;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Setter
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    public Comments(String contents) {
        this.contents = contents;
    }

    public void updateContents(String contents) {
        this.contents = contents;
    }
}
