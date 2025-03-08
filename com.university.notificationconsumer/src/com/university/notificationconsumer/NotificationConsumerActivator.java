package com.university.notificationconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class NotificationConsumerActivator implements BundleActivator {

    @Override
    public void start(BundleContext context) {
        System.out.println("[Notification Consumer] Starting...");

        // Register Event Listener for "university/notifications"
        Dictionary<String, Object> properties = new Hashtable<>();
        properties.put(EventConstants.EVENT_TOPIC, "university/notifications");

        context.registerService(EventHandler.class, new NotificationEventListener(), properties);

        System.out.println("[Notification Consumer] Listener Registered.");
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("[Notification Consumer] Stopping...");
    }
}
