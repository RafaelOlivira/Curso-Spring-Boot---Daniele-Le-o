package com.example.demo.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_usernames")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class usernameModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
