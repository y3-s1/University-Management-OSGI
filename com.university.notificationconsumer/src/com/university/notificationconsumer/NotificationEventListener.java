package com.university.notificationconsumer;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class NotificationEventListener implements EventHandler {
    @Override
    public void handleEvent(Event event) {
        String message = (String) event.getProperty("message");
        String recipientType = (String) event.getProperty("recipientType");

        System.out.println("[Notification Received] To: " + recipientType + " | Message: " + message);
    }
}
