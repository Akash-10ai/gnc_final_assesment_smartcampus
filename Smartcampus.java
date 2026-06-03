import java.util.*;
import java.util.logging.*;

/**
 * Smart Campus Management System
 * A comprehensive Java application for managing students, courses, and enrollments
 * with multithreading support for asynchronous enrollment processing.
 * 
 * @author Akash Kumar (ID: 24665312)
 * @version 2.0
 */

// Custom Exception for invalid fee
class InvalidFeeException extends Exception {
    /**
     * Constructs InvalidFeeException with error message
     * @param msg Error message
     */
    public InvalidFeeException(String msg) {
        super(msg);
    }
}

/**
 * Student class representing a campus student
 * Encapsulates student information: ID, name, and email
 */
class Student {
    private int id;
    private String name;
    private String email;

    /**
     * Constructor for Student with validation
     * @param id Student ID (must be positive)
     * @param name Student name (cannot be empty)
     * @param email Student email (cannot be empty)
     */
    Student(int id, String name, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Student email cannot be empty");
        }
        this.id = id;
        this.name = name.trim();
        this.email = email.trim();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Email: %s", id, name, email);
    }
}

/**
 * Course class representing a campus course
 * Encapsulates course information: ID, name, and fee
 */
class Course {
    private int courseId;
    private String courseName;
    private double fee;

    /**
     * Constructor for Course with validation
     * @param courseId Unique course identifier (must be positive)
     * @param courseName Name of the course (cannot be empty)
     * @param fee Course fee (cannot be negative)
     * @throws InvalidFeeException if fee is negative
     */
    Course(int courseId, String courseName, double fee) throws InvalidFeeException {
        if (courseId <= 0) {
            throw new IllegalArgumentException("Course ID must be positive");
        }
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        if (fee < 0) {
            throw new InvalidFeeException("Fee cannot be negative!");
        }
        this.courseId = courseId;
        this.courseName = courseName.trim();
        this.fee = fee;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return String.format("Course ID: %d | Name: %s | Fee: $%.2f", courseId, courseName, fee);
    }
}

/**
 * Thread class for asynchronous enrollment processing
 * Simulates background enrollment processing without blocking the main thread
 */
class EnrollmentThread extends Thread {
    private String studentName;
    private String courseName;
    private static final Logger logger = Logger.getLogger(EnrollmentThread.class.getName());

    /**
     * Constructor for EnrollmentThread
     * @param studentName Name of the student
     * @param courseName Name of the course
     */
    EnrollmentThread(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
        this.setName("EnrollmentProcessor-" + studentName);
    }

    @Override
    public void run() {
        try {
            logger.info("Processing enrollment for: " + studentName + " in " + courseName);
            System.out.println("Processing enrollment for: " + studentName + "...");
            Thread.sleep(2000); // Simulate processing time
            System.out.println("✓ Enrollment completed for: " + studentName);
            logger.info("Enrollment completed for: " + studentName);
        } catch (InterruptedException e) {
            logger.warning("Enrollment processing interrupted for: " + studentName);
            Thread.currentThread().interrupt();
        }
    }
}

/**
 * Main class for Smart Campus Management System
 * Provides menu-driven interface for managing students, courses, and enrollments
 */
public class Smartcampus {

    private static HashMap<Integer, Student> students = new HashMap<>();
    private static HashMap<Integer, Course> courses = new HashMap<>();
    private static HashMap<Integer, ArrayList<Course>> enrollments = new HashMap<>();
    private static final Logger logger = Logger.getLogger(Smartcampus.class.getName());

    /**
     * Main method - Entry point of the application
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        setupLogging();
        Scanner sc = null;
        int choice;

        try {
            sc = new Scanner(System.in);
            do {
                displayMenu();
                choice = getValidChoice(sc);

                if (choice != 7) {
                    handleMenuChoice(choice, sc);
                }

            } while (choice != 7);

            System.out.println("\n✓ Thank you for using Smart Campus System. Goodbye!");

        } catch (Exception e) {
            logger.severe("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * Setup logging configuration
     */
    private static void setupLogging() {
        try {
            FileHandler fh = new FileHandler("smartcampus.log");
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception e) {
            logger.warning("Could not setup file logging");
        }
    }

    /**
     * Display main menu options
     */
    private static void displayMenu() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║   Smart Campus Management System    ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("1. Add Student");
        System.out.println("2. Add Course");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. View All Students");
        System.out.println("5. View All Enrollments");
        System.out.println("6. Process Enrollment (Async)");
        System.out.println("7. Exit");
        System.out.print("\nEnter your choice (1-7): ");
    }

    /**
     * Get valid menu choice from user
     * @param sc Scanner object
     * @return Valid choice between 1-7
     */
    private static int getValidChoice(Scanner sc) {
        try {
            int choice = sc.nextInt();
            if (choice < 1 || choice > 7) {
                System.out.println("⚠ Invalid choice! Please enter a number between 1 and 7.");
                return getValidChoice(sc);
            }
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter a number.");
            sc.nextLine(); // Clear buffer
            return getValidChoice(sc);
        }
    }

    /**
     * Handle menu choice selection
     * @param choice User's menu choice
     * @param sc Scanner object
     */
    private static void handleMenuChoice(int choice, Scanner sc) {
        try {
            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    addCourse(sc);
                    break;
                case 3:
                    enrollStudent(sc);
                    break;
                case 4:
                    viewStudents();
                    break;
                case 5:
                    viewEnrollments();
                    break;
                case 6:
                    processEnrollmentAsync(sc);
                    break;
            }
        } catch (InvalidFeeException e) {
            System.out.println("❌ Error: " + e.getMessage());
            logger.warning("Invalid fee exception: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please try again.");
            sc.nextLine(); // Clear buffer
        } catch (Exception e) {
            System.out.println("❌ An unexpected error occurred: " + e.getMessage());
            logger.severe("Unexpected error in handleMenuChoice: " + e.getMessage());
        }
    }

    /**
     * Add a new student to the system
     * @param sc Scanner object
     */
    private static void addStudent(Scanner sc) {
        try {
            System.out.print("\nEnter Student ID: ");
            int sid = sc.nextInt();
            
            if (students.containsKey(sid)) {
                System.out.println("⚠ Student with ID " + sid + " already exists!");
                return;
            }

            sc.nextLine(); // Clear buffer
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            Student student = new Student(sid, name, email);
            students.put(sid, student);
            System.out.println("✓ Student added successfully!");
            logger.info("Student added: ID=" + sid + ", Name=" + name);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid input: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter numeric values.");
            sc.nextLine();
        }
    }

    /**
     * Add a new course to the system
     * @param sc Scanner object
     * @throws InvalidFeeException if fee is invalid
     */
    private static void addCourse(Scanner sc) throws InvalidFeeException {
        try {
            System.out.print("\nEnter Course ID: ");
            int cid = sc.nextInt();
            
            if (courses.containsKey(cid)) {
                System.out.println("⚠ Course with ID " + cid + " already exists!");
                return;
            }

            sc.nextLine(); // Clear buffer
            System.out.print("Enter Course Name: ");
            String cname = sc.nextLine();
            System.out.print("Enter Fee: $");
            double fee = sc.nextDouble();

            Course course = new Course(cid, cname, fee);
            courses.put(cid, course);
            System.out.println("✓ Course added successfully!");
            logger.info("Course added: ID=" + cid + ", Name=" + cname + ", Fee=" + fee);

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid input: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter numeric values.");
            sc.nextLine();
        }
    }

    /**
     * Enroll a student in a course
     * @param sc Scanner object
     */
    private static void enrollStudent(Scanner sc) {
        try {
            System.out.print("\nEnter Student ID: ");
            int esid = sc.nextInt();
            System.out.print("Enter Course ID: ");
            int ecid = sc.nextInt();

            if (!students.containsKey(esid)) {
                System.out.println("❌ Student with ID " + esid + " not found!");
                return;
            }

            if (!courses.containsKey(ecid)) {
                System.out.println("❌ Course with ID " + ecid + " not found!");
                return;
            }

            // Check if student is already enrolled in this course
            ArrayList<Course> studentCourses = enrollments.getOrDefault(esid, new ArrayList<>());
            if (studentCourses.stream().anyMatch(c -> c.getCourseId() == ecid)) {
                System.out.println("⚠ Student is already enrolled in this course!");
                return;
            }

            enrollments.putIfAbsent(esid, new ArrayList<>());
            enrollments.get(esid).add(courses.get(ecid));

            System.out.println("✓ Enrollment successful!");
            logger.info("Student ID=" + esid + " enrolled in Course ID=" + ecid);

        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter numeric values.");
            sc.nextLine(); // Clear buffer
        }
    }

    /**
     * Display all registered students
     */
    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("\n⚠ No students registered yet.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        Registered Students            ║");
        System.out.println("╚════════════════════════════════════════╝");
        students.values().forEach(System.out::println);
    }

    /**
     * Display all enrollments
     */
    private static void viewEnrollments() {
        if (enrollments.isEmpty()) {
            System.out.println("\n⚠ No enrollments yet.");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          Enrollment Details           ║");
        System.out.println("╚════════════════════════════════════════╝");

        for (int studentId : enrollments.keySet()) {
            Student student = students.get(studentId);
            if (student != null) {
                System.out.println("\n📚 " + student.getName() + " (ID: " + studentId + ")");
                ArrayList<Course> courseList = enrollments.get(studentId);
                double totalFee = 0;
                for (Course course : courseList) {
                    System.out.println("   └─ " + course);
                    totalFee += course.getFee();
                }
                System.out.printf("   Total Fee: $%.2f\n", totalFee);
            }
        }
    }

    /**
     * Process enrollment asynchronously using threads
     * @param sc Scanner object
     */
    private static void processEnrollmentAsync(Scanner sc) {
        try {
            System.out.print("\nEnter Student ID: ");
            int sid = sc.nextInt();
            System.out.print("Enter Course ID: ");
            int cid = sc.nextInt();

            if (!students.containsKey(sid) || !courses.containsKey(cid)) {
                System.out.println("❌ Invalid Student or Course ID!");
                return;
            }

            Student student = students.get(sid);
            Course course = courses.get(cid);

            EnrollmentThread thread = new EnrollmentThread(student.getName(), course.getCourseName());
            thread.start();

            System.out.println("✓ Enrollment processing started in background...");

        } catch (InputMismatchException e) {
            System.out.println("⚠ Invalid input! Please enter numeric values.");
            sc.nextLine(); // Clear buffer
        }
    }
}
