package com.example.scheduledevelopproject.dto;


import com.example.scheduledevelopproject.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionUserDto {
    private final Long id;
    private final String name;
    private final String email;

    public SessionUserDto(Users users) {
        this.id = users.getId();
        this.name = users.getName();
        this.email = users.getName();
    }
}
