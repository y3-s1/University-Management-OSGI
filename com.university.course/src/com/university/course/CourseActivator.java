package com.university.course;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class CourseActivator implements BundleActivator {

    private ServiceRegistration registration;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Extended Course Management Service Started...");
        CourseService courseService = new CourseServiceImpl();
        registration = context.registerService(CourseService.class.getName(), courseService, null);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Extended Course Management Service Stopped...");
        registration.unregister();
    }
}
