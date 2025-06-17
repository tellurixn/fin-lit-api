package ru.tellurian.fin_lit_api.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @NotNull
    @Size(max = 100)
    @Column(name = "login")
    private String login;

    @NotNull
    @Size(max = 100)
    @Column(name = "password")
    private String password;


    @Size(max = 100)
    @Column(name = "comment")
    private String comment;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
