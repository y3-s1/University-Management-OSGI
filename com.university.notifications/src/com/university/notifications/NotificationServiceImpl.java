package com.university.notifications;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendNotification(String message, String recipientType) {
        System.out.println("[Notification Sent] To: " + recipientType + " | Message: " + message);
    }
}
