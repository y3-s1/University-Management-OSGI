package com.university.notifications;

import org.apache.felix.service.command.Descriptor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

import java.util.Dictionary;
import java.util.Hashtable;
import org.osgi.service.event.Event;

@Component(
    property = {
        "osgi.command.scope=notify",
        "osgi.command.function=send"
    },
    service = NotificationCommand.class
)
public class NotificationCommand {

    @Reference
    private EventAdmin eventAdmin;

    @Descriptor("Send a notification")
    public void send(
        @Descriptor("Notification message") String message,
        @Descriptor("Recipient type") String recipientType
    ) {
        System.out.println("[Command] Sending notification: " + message + " to " + recipientType);

        Dictionary<String, Object> properties = new Hashtable<>();
        properties.put("message", message);
        properties.put("recipientType", recipientType);

        Event event = new Event("university/notifications", properties);
        eventAdmin.sendEvent(event);

        System.out.println("[Command] Notification sent successfully!");
    }
}
