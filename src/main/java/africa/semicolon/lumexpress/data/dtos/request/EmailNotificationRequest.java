package africa.semicolon.lumexpress.service.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailNotification {
    private String userEmail;
    private String mailContent;
}
