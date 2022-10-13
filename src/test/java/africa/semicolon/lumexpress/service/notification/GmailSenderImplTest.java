package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dtos.request.EmailNotificationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GmailSenderImplTest {

 @Autowired
 private EmailNotificationService emailSender;

    @Test
    void sendHtmlMailTest() {
        EmailNotificationRequest emailDetails = new EmailNotificationRequest();
        emailDetails.setUserEmail("augustineezekiel763@gmail.com");
        emailDetails.setMailContent("Hi, whimp dev!!!");
        String response = emailSender.sendHtmlMail(emailDetails);
        assertThat(response.contains("successfully")).isTrue();
    }
}