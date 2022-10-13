package africa.semicolon.lumexpress.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LumExpressUtilsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void generateToken() {
        String token = LumExpressUtils.generateToken();
        assertThat(token).isNotNull();
        assertThat(token.length()).isEqualTo(5);
    }
}