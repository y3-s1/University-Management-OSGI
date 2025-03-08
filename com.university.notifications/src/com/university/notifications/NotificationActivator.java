package com.university.notifications;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.event.EventAdmin;

public class NotificationActivator implements BundleActivator {
    private ServiceRegistration registration;
    private NotificationServiceImpl notificationService;

    @Override
    public void start(BundleContext context) {
        System.out.println("[Notification Service] Starting...");
        
        // Register Notification Service
        notificationService = new NotificationServiceImpl(context);
        registration = context.registerService(NotificationService.class, notificationService, null);
        
        System.out.println("[Notification Service] Registered successfully.");
        
        // Example: Send a test notification
        notificationService.sendNotification("Welcome to the university!", "All");
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("[Notification Service] Stopping...");
        registration.unregister();
        System.out.println("[Notification Service] Unregistered successfully.");
    }
}
