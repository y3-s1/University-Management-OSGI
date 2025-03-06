package com.university.notifications;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class NotificationActivator implements BundleActivator {
    private ServiceRegistration registration;

    @Override
    public void start(BundleContext context) {
        System.out.println("[Notification Service] Starting...");
        
        // Create an instance of the service and register it
        NotificationService service = new NotificationServiceImpl();
        registration = context.registerService(NotificationService.class.getName(), service, null);

        System.out.println("[Notification Service] Registered successfully.");
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("[Notification Service] Stopping...");
        
        // Unregister the service
        if (registration != null) {
            registration.unregister();
        }

        System.out.println("[Notification Service] Unregistered successfully.");
    }
}
