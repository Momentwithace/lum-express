package africa.semicolon.lumexpress.data.repository;

import africa.semicolon.lumexpress.data.models.VerificationToken;
import africa.semicolon.lumexpress.exception.VerificationTokenException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VerificationTokenRepositoryTest {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private VerificationToken verificationToken;

    @BeforeEach
    void setUp() {
        verificationToken = new VerificationToken();
        verificationToken.setToken("12345");
        verificationToken.setUserEmail("test@email.com");
    }


    @Test
    void findByUserEmail() {
        verificationTokenRepository.save(verificationToken);
        VerificationToken foundToken =
                verificationTokenRepository.findByUserEmail("test@email.com").orElseThrow(()->
        new VerificationTokenException("token not found"));
        assertThat(foundToken).isNotNull();
        assertThat(foundToken.getToken()).isEqualTo(verificationToken.getToken());
    }

    @Test
    void findByToken() {
    }
}