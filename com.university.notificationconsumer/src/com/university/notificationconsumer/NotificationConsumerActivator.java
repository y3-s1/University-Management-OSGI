package com.university.notificationconsumer;

import com.university.notifications.NotificationService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class NotificationConsumerActivator implements BundleActivator {
    private ServiceReference serviceReference;

    @Override
    public void start(BundleContext context) {
        System.out.println("[Notification Consumer] Starting...");

        // Get reference to the NotificationService
        serviceReference = context.getServiceReference(NotificationService.class.getName());

        if (serviceReference != null) {
            NotificationService notificationService = (NotificationService) context.getService(serviceReference);
            
            // Simulating a consumer receiving a notification
            notificationService.sendNotification("Hello Students! Exam registration is open.", "Student Portal");

            System.out.println("[Notification Consumer] Notification received successfully.");
        } else {
            System.out.println("[Notification Consumer] No NotificationService found!");
        }
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("[Notification Consumer] Stopping...");
        if (serviceReference != null) {
            context.ungetService(serviceReference);
        }
        System.out.println("[Notification Consumer] Unregistered.");
    }
}
