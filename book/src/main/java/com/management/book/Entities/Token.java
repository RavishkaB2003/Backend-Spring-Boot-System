package com.management.book.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// This class represents a blacklisted token entity for storing invalidated JWT tokens.
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blacklisted_token")
public class Token {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 500)
    private String token;

    private LocalDateTime addedAt;

    public Token(String token) {
        this.token = token;
        this.addedAt = LocalDateTime.now();
    }


}
