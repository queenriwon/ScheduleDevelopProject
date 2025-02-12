package com.example.scheduledevelopproject.dto;

import com.example.scheduledevelopproject.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionUserDto {
    private Long id;
    private String name;
    private String email;

    public SessionUserDto(Users users) {
        this.id = users.getId();
        this.name = users.getName();
        this.email = users.getName();
    }

    public Users toUsers() {
        return new Users(this.id, this.name, this.email);
    }

    public SessionUserDto setUserName(String name) {
        this.name = name;
        return this;
    }
}
