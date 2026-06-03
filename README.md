# Smart Campus Management System

**Author:** Akash Kumar (ID: 24665312)  
**Email:** akash.git1009@gmail.com  
**Course:** B.Tech CSE  
**Version:** 2.0

---

## 📋 Project Overview

The **Smart Campus Management System** is a comprehensive Java application designed to streamline student enrollment, course management, and administrative tasks for educational institutions. This system demonstrates core Object-Oriented Programming (OOP) principles combined with advanced Java features like multithreading, exception handling, and collections framework.

---

## ✨ Key Features

### Core Features
- **Student Management:** Add, store, and retrieve student information (ID, Name, Email)
- **Course Management:** Create and manage courses with fee information
- **Enrollment System:** Enroll students in courses with validation
- **View Reports:** Display all students, courses, and enrollment details
- **Asynchronous Processing:** Background enrollment processing using threads

### Technical Features
- **Collections Framework:** 
  - `HashMap` for O(1) quick lookups of students and courses
  - `ArrayList` for mapping multiple courses to students
- **Custom Exception Handling:** `InvalidFeeException` for data validation
- **Multithreading:** Asynchronous enrollment processing with `Thread` class
- **Logging:** Built-in logging support for audit trails
- **Input Validation:** Comprehensive validation for all user inputs
- **Error Recovery:** Graceful error handling with user-friendly messages

---

## 🏗️ System Architecture

### Classes

#### 1. **Student**
```java
class Student {
    private int id;
    private String name;
    private String email;
}
```
Represents a campus student with validation for ID, name, and email.

#### 2. **Course**
```java
class Course {
    private int courseId;
    private String courseName;
    private double fee;
}
```
Represents a course offering with fee validation (prevents negative fees).

#### 3. **EnrollmentThread**
```java
class EnrollmentThread extends Thread {
    void run()
}
```
Handles asynchronous enrollment processing without blocking the main thread.

#### 4. **Smartcampus (Main)**
Central hub managing:
- HashMap of Students
- HashMap of Courses
- HashMap of Enrollments (Student → Courses mapping)

---

## 🐛 Bug Fixes (Version 2.0)

| Issue | Fix |
|-------|-----|
| Empty exception catch blocks | Added proper logging and exception handling |
| No input validation | Added validation for IDs, names, emails, and fees |
| Null pointer risks | Added null checks and boundary validation |
| Duplicate enrollments not prevented | Added duplicate check before enrollment |
| Duplicate student/course IDs allowed | Added existence checks |
| Generic exception catching | Specific exception handling for different scenarios |
| No logging capability | Added Java Logger for audit trails |
| Poor user experience | Added visual separators and status indicators |
| Missing Javadoc comments | Added comprehensive documentation |
| Thread naming not informative | Added descriptive thread names |

---

## 🚀 How to Run

### Prerequisites
- Java 8 or higher installed on your system
- A terminal or IDE (VS Code, IntelliJ IDEA, Eclipse, etc.)

### Compilation & Execution

```bash
# Navigate to project directory
cd gnc_final_assesment_smartcampus

# Compile the program
javac Smartcampus.java

# Run the program
java Smartcampus
```

### Sample Menu Interaction

```
╔══════════════════════════════════════╗
║   Smart Campus Management System    ║
╚══════════════════════════════════════╝
1. Add Student
2. Add Course
3. Enroll Student in Course
4. View All Students
5. View All Enrollments
6. Process Enrollment (Async)
7. Exit

Enter your choice (1-7): 1
Enter Student ID: 101
Enter Name: John Doe
Enter Email: john@example.com
✓ Student added successfully!
```

---

## 📊 Data Flow

```
┌─────────────────────────────────────┐
│         Main Application            │
│        (Smartcampus.java)           │
└─────────────────────────────────────┘
              ↓
    ┌────────┴────────┐
    ↓                 ↓
Students HashMap   Courses HashMap
    ↓                 ↓
  Student          Course
  Objects          Objects
    ↑                 ↑
    └────────┬────────┘
             ↓
      Enrollments HashMap
      (StudentID → ArrayList<Course>)
             ↓
      EnrollmentThread
      (Async Processing)
```

---

## 🎯 Use Cases

1. **Educational Administrator**
   - Add new students to the system
   - Create new course offerings
   - Track student enrollments
   - Generate enrollment reports

2. **Automated Processing**
   - Background enrollment processing without blocking the UI
   - Asynchronous course registration

3. **Data Validation**
   - Prevent negative course fees
   - Ensure unique student and course IDs
   - Validate email formats and non-empty fields

---

## 🔒 Exception Handling

### Exceptions Handled

| Exception | Scenario | Recovery |
|-----------|----------|----------|
| `InvalidFeeException` | Negative course fee | Prompt user to re-enter valid fee |
| `InputMismatchException` | Invalid numeric input | Clear buffer and re-prompt |
| `IllegalArgumentException` | Invalid student/course data | Display error message and retry |
| `InterruptedException` | Thread interruption | Log warning and restore interrupt status |

---

## 📈 Performance Characteristics

| Operation | Time Complexity | Space Complexity |
|-----------|-----------------|------------------|
| Add Student | O(1) | O(n) |
| Add Course | O(1) | O(m) |
| Enroll Student | O(1) | O(k) |
| View Students | O(n) | O(1) |
| View Enrollments | O(n*k) | O(1) |

Where:
- n = number of students
- m = number of courses
- k = enrollments per student

---

## 📝 Logging

The application generates logs in:
- **Console Output:** Real-time user interactions
- **File Output:** `smartcampus.log` - Persistent audit trail

### Log Examples
```
[INFO] Student added: ID=101, Name=John Doe
[INFO] Course added: ID=201, Name=Java Programming, Fee=5000.0
[INFO] Student ID=101 enrolled in Course ID=201
[INFO] Processing enrollment for: John Doe in Java Programming
[INFO] Enrollment completed for: John Doe
```

---

## 🔧 Future Enhancements

- [ ] Database integration (MySQL/PostgreSQL)
- [ ] GUI using JavaFX or Swing
- [ ] Student dashboard with enrollment history
- [ ] Payment processing module
- [ ] Certificate generation
- [ ] Email notifications
- [ ] Role-based access control (Student, Admin, Instructor)
- [ ] REST API for mobile applications
- [ ] Advanced reporting and analytics

---

## 📄 Code Quality Metrics

- **Javadoc Coverage:** 100%
- **Exception Handling:** Comprehensive
- **Input Validation:** All user inputs validated
- **Code Organization:** Well-structured with clear separation of concerns
- **Naming Conventions:** Follows Java naming standards

---

## 📞 Support & Contact

For issues, questions, or suggestions:
- **Email:** akash.git1009@gmail.com
- **GitHub:** [Akash-10ai](https://github.com/Akash-10ai)

---

## 📜 License

This project is open source and available for educational purposes.

---

## 🙏 Acknowledgments

- GNC (Greater noida collage) Assessment Framework
- Java Collections Framework Documentation
- Multithreading Best Practices

---

**Last Updated:** June 3, 2026  
**Version:** 2.0 (Enhanced with bug fixes and improvements)
