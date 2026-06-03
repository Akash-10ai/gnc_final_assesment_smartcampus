# Build Instructions

This document provides comprehensive instructions for building and running the Smart Campus Management System.

## 📋 Prerequisites

- **Java Development Kit (JDK):** Java 8 or higher
- **Maven:** (Optional, but recommended)
- **Git:** For cloning the repository

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version (if installed)
mvn -version
```

## 🚀 Method 1: Direct Java Compilation (Simple)

### Step 1: Clone the Repository
```bash
git clone https://github.com/Akash-10ai/gnc_final_assesment_smartcampus.git
cd gnc_final_assesment_smartcampus
```

### Step 2: Compile
```bash
javac Smartcampus.java
```

### Step 3: Run
```bash
java Smartcampus
```

## 🔧 Method 2: Using Maven (Recommended)

### Step 1: Clone the Repository
```bash
git clone https://github.com/Akash-10ai/gnc_final_assesment_smartcampus.git
cd gnc_final_assesment_smartcampus
```

### Step 2: Clean and Compile
```bash
mvn clean compile
```

### Step 3: Run
```bash
mvn exec:java -Dexec.mainClass="Smartcampus"
```

### Step 4: Package as JAR
```bash
mvn package
```

### Step 5: Run the JAR
```bash
java -jar target/smartcampus-management-2.0.0-jar-with-dependencies.jar
```

## 🛠️ Method 3: Using IDE (VS Code, IntelliJ, Eclipse)

### VS Code

1. **Install Extensions:**
   - "Extension Pack for Java" (Microsoft)
   - "Maven for Java" (Microsoft)

2. **Open Project:**
   - File → Open Folder
   - Select the project directory

3. **Run:**
   - Click "Run" button above the `main` method
   - Or press `Ctrl+F5`

### IntelliJ IDEA

1. **Open Project:**
   - File → Open
   - Select the project directory

2. **Mark as Source:**
   - Right-click `src` → Mark Directory as → Sources Root

3. **Run:**
   - Right-click `Smartcampus.java` → Run

### Eclipse

1. **Import Project:**
   - File → Import → Existing Projects into Workspace
   - Select the project directory

2. **Build:**
   - Project → Build All

3. **Run:**
   - Right-click `Smartcampus.java` → Run As → Java Application

## 📦 Build Artifacts

After building with Maven, you'll find:

```
target/
├── smartcampus-management-2.0.0.jar
├── smartcampus-management-2.0.0-jar-with-dependencies.jar
└── classes/
    ├── Smartcampus.class
    ├── Student.class
    ├── Course.class
    ├── EnrollmentThread.class
    └── InvalidFeeException.class
```

## 🧪 Testing

### Manual Testing
```bash
java Smartcampus
# Follow the menu prompts
```

### Test Cases

| Test Case | Steps | Expected Result |
|-----------|-------|-----------------|
| Add Student | Enter choice 1, provide ID, name, email | Student added successfully |
| Invalid Fee | Enter choice 2, enter negative fee | Error: Fee cannot be negative |
| Duplicate ID | Add same student ID twice | Warning: Student already exists |
| Enroll Student | Enter valid student and course IDs | Enrollment successful |
| View Reports | Enter choices 4 or 5 | Display formatted reports |

## 📊 Output Examples

### Successful Compilation
```
$ javac Smartcampus.java
$ echo "Compilation successful - no errors"
```

### Program Output
```
╔══════════════════════════════════════╗
║   Smart Campus Management System    ║
╚══════════════════════════════════════╝
1. Add Student
2. Add Course
...
```

## 🐛 Troubleshooting

### Issue: Command not found: javac
**Solution:** 
- Ensure JDK (not just JRE) is installed
- Add Java to system PATH
- Verify installation: `java -version`

### Issue: NoClassDefFoundError
**Solution:**
- Ensure all files are in the same directory
- Check that Smartcampus.java is compiled first
- Try clean compilation: `javac *.java`

### Issue: Exception in thread "main"
**Solution:**
- Check for typos in class names
- Ensure main class name matches filename
- Run from correct directory

### Issue: Maven not found
**Solution:**
- Install Maven from [maven.apache.org](https://maven.apache.org)
- Add Maven to system PATH
- Verify: `mvn -version`

## 🔄 Clean Build

Remove all compiled files and rebuild:

```bash
# Using Java directly
rm *.class smartcampus.log
javac Smartcampus.java

# Using Maven
mvn clean compile
```

## 📝 Build Customization

### Compile with Verbose Output
```bash
javac -verbose Smartcampus.java
```

### Compile with Warnings
```bash
javac -Xlint:all Smartcampus.java
```

### Create Jar with Maven Profile
```bash
mvn package -P production
```

## ✅ Verification

After successful build, verify the executable:

```bash
# With Java
java Smartcampus --help
# or just
java Smartcampus

# With Maven
mvn exec:java -Dexec.mainClass="Smartcampus"

# With JAR
java -jar target/smartcampus-management-2.0.0-jar-with-dependencies.jar
```

## 📚 Additional Resources

- [Java Documentation](https://docs.oracle.com/javase/)
- [Maven Guide](https://maven.apache.org/guides/)
- [Project README](README.md)

---

**For issues or questions, please open an issue on GitHub or contact: akash.git1009@gmail.com**
