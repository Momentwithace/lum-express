package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.NotificationRequest;

public interface NotificationService {
    String send(NotificationRequest notificationRequest);
}
