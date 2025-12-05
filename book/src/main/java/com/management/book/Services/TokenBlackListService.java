package com.management.book.Services;

import com.management.book.Entities.Token;
import com.management.book.Repository.BlackListTokenRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenBlackListService {
    public final BlackListTokenRepository blackListTokenRepository;
    public TokenBlackListService(BlackListTokenRepository blackListTokenRepository) {
        this.blackListTokenRepository = blackListTokenRepository;
    }

    //Blacklist token method
    public void blacklistToken(String token) {
        if(!blackListTokenRepository.existsByToken(token)){
            Token blacklisttoken = new Token(token);
            blackListTokenRepository.save(blacklisttoken);
        }
    }


    //check if token is blacklisted(used by JwtFilter)
    public boolean isTokenBlacklisted(String token){
        return blackListTokenRepository.existsByToken(token);
    }

    // 3. Maintenance Logic: Automated Cleanup Job
    // Runs every day at midnight (00:00:00)

    @Scheduled (cron = "0 0 0 * * ?")
    @Transactional //important for delete operation
    public void deleteExpiredTokens() {
        // We delete tokens blacklisted more than 24 hours ago
        // (Assuming your max JWT lifespan is 24 hours)
        LocalDateTime cutoffTime = LocalDateTime.now().minusHours(24);

        blackListTokenRepository.deleteByaddedAt(cutoffTime);

        System.out.println("LOG: Cleaned up expired blacklisted tokens at " + LocalDateTime.now());
    }


}
