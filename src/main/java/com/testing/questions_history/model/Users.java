package com.testing.questions_history.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @NaturalId(mutable = true)
    private String email;

    private String passwordOrigin;

    private String password;

    private String role;

    public Users(String firstName, String lastName, String email, String passwordOrigin, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordOrigin = passwordOrigin;
        this.password = password;
        this.role = role;
    }
}
