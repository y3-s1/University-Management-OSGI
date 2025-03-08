package com.university.notifications;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.Dictionary;
import java.util.Hashtable;

public class NotificationServiceImpl implements NotificationService {  // ✅ Implements NotificationService
    private final BundleContext context;

    public NotificationServiceImpl(BundleContext context) {
        this.context = context;
    }

    @Override
    public void sendNotification(String message, String recipientType) {
        System.out.println("[Notification Sent] To: " + recipientType + " | Message: " + message);

        // Get EventAdmin Service
        ServiceReference<EventAdmin> ref = context.getServiceReference(EventAdmin.class);
        if (ref != null) {
            EventAdmin eventAdmin = context.getService(ref);
            
            // Create event properties
            Dictionary<String, Object> properties = new Hashtable<>();
            properties.put("message", message);
            properties.put("recipientType", recipientType);
            
            // Publish event
            Event event = new Event("university/notifications", properties);
            eventAdmin.postEvent(event);
        } else {
            System.out.println("[Error] EventAdmin service not available.");
        }
    }
}
