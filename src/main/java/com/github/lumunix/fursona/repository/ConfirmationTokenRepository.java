package com.github.lumunix.fursona.repository;



import com.github.lumunix.fursona.entity.ConfirmationToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, String> {
    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
