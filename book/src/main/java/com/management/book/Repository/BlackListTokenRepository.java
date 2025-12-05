package com.management.book.Repository;

import com.management.book.Entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BlackListTokenRepository extends JpaRepository<Token,Long> {
    boolean existsByToken(String token);

    // Spring Data JPA magic: It converts this method name into SQL DELETE logic
    void deleteByaddedAt(LocalDateTime addedAt);
}
