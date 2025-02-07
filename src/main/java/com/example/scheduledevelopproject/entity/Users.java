package com.example.scheduledevelopproject.entity;

import com.example.scheduledevelopproject.config.PasswordEncoder;
import com.example.scheduledevelopproject.dto.request.UserSignUpRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public Users(UserSignUpRequestDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.password = PasswordEncoder.encode(dto.getPassword());
    }

    public void updateUsers(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
