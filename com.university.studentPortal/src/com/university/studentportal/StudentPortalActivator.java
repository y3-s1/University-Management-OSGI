package com.university.studentportal;

import java.util.List;
import java.util.Scanner;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.university.course.CourseService;

public class StudentPortalActivator implements BundleActivator {

    private ServiceReference courseServiceReference;
    private Thread inputThread;
    private volatile boolean running = true;
    
    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Student Portal Interactive Mode Started...");
        
        // Retrieve the CourseService from the OSGi Service Registry
        courseServiceReference = context.getServiceReference(CourseService.class.getName());
        final CourseService courseService = (CourseService) context.getService(courseServiceReference);
        
        // Start a new thread for interactive user input
        inputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            
            // Ask for the Student ID once at the start
            System.out.print("Enter your Student ID: ");
            String studentID = scanner.next();
            
            while (running) {
                System.out.println("\n----- Student Portal Course Management Menu -----");
                System.out.println("1. View All Available Courses");
                System.out.println("2. View My Enrolled Courses");
                System.out.println("3. Enroll in a Course");
                System.out.println("4. Drop a Course");
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
                            System.out.println("Available Courses:");
                            for (String course : courses) {
                                System.out.println("- " + course);
                            }
                        }
                        break;
                    }
                    case '2': {
                        List<String> enrolledCourses = courseService.getEnrolledCourses(studentID);
                        if (enrolledCourses.isEmpty()) {
                            System.out.println("You are not enrolled in any courses.");
                        } else {
                            System.out.println("Your Enrolled Courses:");
                            for (String course : enrolledCourses) {
                                System.out.println("- " + course);
                            }
                        }
                        break;
                    }
                    case '3': {
                        System.out.print("Enter course name to enroll: ");
                        String courseToEnroll = scanner.next();
                        boolean enrolled = courseService.enrollStudent(studentID, courseToEnroll);
                        if (enrolled) {
                            System.out.println("Successfully enrolled in " + courseToEnroll + ".");
                        }
                        break;
                    }
                    case '4': {
                        System.out.print("Enter course name to drop: ");
                        String courseToDrop = scanner.next();
                        boolean dropped = courseService.removeStudent(studentID, courseToDrop);
                        if (dropped) {
                            System.out.println("Successfully dropped " + courseToDrop + ".");
                        }
                        break;
                    }
                    case '5': {
                        System.out.println("Exiting interactive mode...");
                        running = false;
                        if (inputThread != null && inputThread.isAlive()) {
                            inputThread.interrupt();
                            try {
								inputThread.join(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                        }

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
        System.out.println("Student Portal Interactive Mode Stopped...");
        running = false;
        if (inputThread != null && inputThread.isAlive()) {
            inputThread.join(2000);
        }
        // Release the service reference
        context.ungetService(courseServiceReference);
    }
}
