package com.example.scheduledevelopproject.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSessionDto {
    private final Long id;
    private final String name;
    private final String email;
}
