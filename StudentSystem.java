import java.util.ArrayList;
import java.util.Scanner;

// Student Class
class Student {
    private int id;
    private String name;
    private int age;
    private ArrayList<Course> courses;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void enrollCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(int courseId) {
        courses.removeIf(course -> course.getId() == courseId);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Courses: " + courses.size();
    }
}

// Course Class
class Course {
    private int id;
    private String name;
    private int grade;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
        this.grade = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Course ID: " + id + ", Name: " + name + ", Grade: " + grade;
    }
}

// Student Management System
class StudentManager {
    private ArrayList<Student> students;
    private int studentIdCounter = 1;
    private int courseIdCounter = 101;

    public StudentManager() {
        students = new ArrayList<>();
    }

    // Add Student
    public void addStudent(String name, int age) {
        students.add(new Student(studentIdCounter++, name, age));
        System.out.println("Student added successfully!");
    }

    // View Students
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Update Student
    public void updateStudent(int id, String newName, int newAge) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            students.add(new Student(id, newName, newAge));
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Delete Student
    public void deleteStudent(int id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Enroll Course
    public void enrollCourse(int studentId, String courseName) {
        Student student = findStudentById(studentId);
        if (student != null) {
            student.enrollCourse(new Course(courseIdCounter++, courseName));
            System.out.println("Course enrolled successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Assign Grade
    public void assignGrade(int studentId, int courseId, int grade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            for (Course course : student.getCourses()) {
                if (course.getId() == courseId) {
                    course.setGrade(grade);
                    System.out.println("Grade assigned successfully!");
                    return;
                }
            }
            System.out.println("Course not found!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // View Courses
    public void viewCourses(int studentId) {
        Student student = findStudentById(studentId);
        if (student != null) {
            for (Course course : student.getCourses()) {
                System.out.println(course);
            }
        } else {
            System.out.println("Student not found!");
        }
    }

    // Find Student by ID
    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}

// Main Class
public class StudentManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Enroll Course");
            System.out.println("6. Assign Grade");
            System.out.println("7. View Courses");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    enrollCourse();
                    break;
                case 6:
                    assignGrade();
                    break;
                case 7:
                    viewCourses();
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Age: ");
        int age = scanner.nextInt();
        manager.addStudent(name, age);
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Age: ");
        int age = scanner.nextInt();
        manager.updateStudent(id, name, age);
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        manager.deleteStudent(id);
    }

    private static void enrollCourse() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = scanner.nextLine();
        manager.enrollCourse(id, courseName);
    }

    private static void assignGrade() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Enter Grade: ");
        int grade = scanner.nextInt();
        manager.assignGrade(studentId, courseId, grade);
    }

    private static void viewCourses() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        manager.viewCourses(id);
    }
}
