package com.university.facultydashboard;

import java.util.List;
import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.university.course.CourseService;

public class FacultyDashboardActivator implements BundleActivator {

    private ServiceReference courseServiceReference;
    private Thread inputThread;
    private volatile boolean running = true;
    
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Faculty Dashboard Interactive Mode Started...");
        
        // Retrieve the CourseService from the OSGi Service Registry
        courseServiceReference = context.getServiceReference(CourseService.class.getName());
        final CourseService courseService = (CourseService) context.getService(courseServiceReference);
        
        // Start a new thread for interactive user input
        inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (running) {
                System.out.println("\n----- Faculty Dashboard Course Management Menu -----");
                System.out.println("1. View Course List");
                System.out.println("2. Add New Course");
                System.out.println("3. Update Course");
                System.out.println("4. Remove Course");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                
                // Read the next token and use its first character as the selection
                char choice = scanner.next().charAt(0);
                
                switch (choice) {
                    case '1': {
                        List<String> courses = courseService.getAllCourses();
                        if (courses.isEmpty()) {
                            System.out.println("No courses available.");
                        } else {
                            System.out.println("Course List:");
                            for (String course : courses) {
                                System.out.println("- " + course);
                            }
                        }
                        break;
                    }
                    case '2': {
                        System.out.print("Enter course name to add: ");
                        String courseToAdd = scanner.next(); // Reads a single token
                        courseService.addCourse(courseToAdd);
                        break;
                    }
                    case '3': {
                        System.out.print("Enter the current course name: ");
                        String oldCourseName = scanner.next();
                        System.out.print("Enter the new course name: ");
                        String newCourseName = scanner.next();
                        courseService.updateCourse(oldCourseName, newCourseName);
                        break;
                    }
                    case '4': {
                        System.out.print("Enter course name to remove: ");
                        String courseToRemove = scanner.next();
                        courseService.removeCourse(courseToRemove);
                        break;
                    }
                    case '5': {
                        System.out.println("Exiting interactive mode...");
                        running = false;
                        break;
                    }
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
            scanner.close();
        });
        
        inputThread.start();
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Faculty Dashboard Interactive Mode Stopped...");
        running = false;
        if (inputThread != null && inputThread.isAlive()) {
            inputThread.join(2000);
        }
        // Release the service reference
        context.ungetService(courseServiceReference);
    }
}
