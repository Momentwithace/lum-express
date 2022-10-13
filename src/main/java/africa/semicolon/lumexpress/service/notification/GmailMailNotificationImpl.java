package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dtos.request.EmailNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@Service
public class GmailMailNotificationImpl implements EmailNotificationService {

    private final JavaMailSender javaMailSender;

    @Override
    public String sendHtmlMail(EmailNotificationRequest emailDetails) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom("no-reply@email.LumExpress.com.ng");
            mimeMessageHelper.setTo(emailDetails.getUserEmail());
            mimeMessageHelper.setText(emailDetails.getMailContent(), true);
            javaMailSender.send(mimeMessage);
            return String.format("email sent to %s successfully", emailDetails.getUserEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return String.format("email not sent to %s", emailDetails.getUserEmail());
    }
}
