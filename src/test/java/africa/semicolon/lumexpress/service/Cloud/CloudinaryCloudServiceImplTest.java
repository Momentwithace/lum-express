package africa.semicolon.lumexpress.service.Cloud;

import com.cloudinary.utils.ObjectUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CloudinaryCloudServiceImplTest {
    @Autowired
    private CloudService cloudService;
    private MultipartFile file;

    @BeforeEach
    void setUp() throws IOException {
        Path path = Paths.get("src/main/resources/Img/milk.png");
        file = new MockMultipartFile("milk", Files.readAllBytes(path));

    }

    @Test
    @DisplayName("Cloudinary upload test")
    void upload() {
        try {
           String response = cloudService.upload(file.getBytes(), ObjectUtils.emptyMap());
           assertThat(response).isNotNull();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}