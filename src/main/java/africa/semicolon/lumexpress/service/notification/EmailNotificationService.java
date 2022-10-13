package africa.semicolon.lumexpress.service.notification;

import africa.semicolon.lumexpress.data.dtos.request.EmailNotificationRequest;

public interface EmailNotificationService {
    String sendHtmlMail(EmailNotificationRequest emailDetails);
}
