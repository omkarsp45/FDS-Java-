package FDS_Java.Lab1;


/**
 * This is a class representing a Human with basic information.
 */
class Human {
    // Fields to store information about a Human
    private String name;
    private int age;
    private String gender;

    /**
     * Constructor to initialize a Human object.
     *
     * @param name   The name of the human.
     * @param age    The age of the human.
     * @param gender The gender of the human.
     */
    public Human(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    /**
     * Get the name of the human.
     *
     * @return The name of the human.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the age of the human.
     *
     * @return The age of the human.
     */
    public int getAge() {
        return age;
    }

    /**
     * Get the gender of the human.
     *
     * @return The gender of the human.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Set the name of the human.
     *
     * @param name The new name of the human.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the age of the human.
     *
     * @param age The new age of the human.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Set the gender of the human.
     *
     * @param gender The new gender of the human.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
}

/**
 * This is a class representing a Student, which is a subclass of Human.
 */
class Student extends Human {
    // Fields to store additional information about a Student
    private int rollNumber;
    private int semester;
    private String branch;
    private double CPI;

    /**
     * Constructor to initialize a Student object.
     *
     * @param name       The name of the student.
     * @param age        The age of the student.
     * @param gender     The gender of the student.
     * @param rollNumber The roll number of the student.
     * @param semester   The current semester of the student.
     * @param branch     The branch of study for the student.
     * @param CPI        The Cumulative Performance Index (CPI) of the student.
     */
    public Student(String name, int age, String gender, int rollNumber, int semester, String branch, double CPI) {
        super(name, age, gender);
        this.rollNumber = rollNumber;
        this.semester = semester;
        this.branch = branch;
        this.CPI = CPI;
    }

    /**
     * Set the roll number of the student.
     *
     * @param rollNumber The new roll number of the student.
     */
    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    /**
     * Set the semester of the student.
     *
     * @param semester The new semester of the student.
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }

    /**
     * Set the branch of study for the student.
     *
     * @param branch The new branch of study for the student.
     */
    public void setBranch(String branch) {
        this.branch = branch;
    }

    /**
     * Set the Cumulative Performance Index (CPI) of the student.
     *
     * @param CPI The new CPI of the student.
     */
    public void setCPI(double CPI) {
        this.CPI = CPI;
    }

    /**
     * Print information about the student.
     */
    public void getInfo() {
        System.out.println("Student Details:");
        System.out.println("Name: " + super.getName());
        System.out.println("Age: " + super.getAge());
        System.out.println("Gender: " + super.getGender());
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Semester: " + semester);
        System.out.println("Branch: " + branch);
        System.out.println("CPI: " + CPI);
    }
}

/**
 * This is a class representing a Faculty member, which is a subclass of Human.
 */
class Faculty extends Human {
    // Fields to store additional information about a Faculty member
    private int empId;
    private double salary;
    private String specialization;

    /**
     * Constructor to initialize a Faculty object.
     *
     * @param name           The name of the faculty member.
     * @param age            The age of the faculty member.
     * @param gender         The gender of the faculty member.
     * @param empId          The employee ID of the faculty member.
     * @param salary         The salary of the faculty member.
     * @param specialization The specialization of the faculty member.
     */
    public Faculty(String name, int age, String gender, int empId, double salary, String specialization) {
        super(name, age, gender);
        this.empId = empId;
        this.salary = salary;
        this.specialization = specialization;
    }

    /**
     * Set the employee ID of the faculty member.
     *
     * @param empId The new employee ID of the faculty member.
     */
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    /**
     * Set the salary of the faculty member.
     *
     * @param salary The new salary of the faculty member.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Set the specialization of the faculty member.
     *
     * @param specialization The new specialization of the faculty member.
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    /**
     * Print information about the faculty member.
     */
    public void getInfo() {
        System.out.println("Faculty Details:");
        System.out.println("Name: " + super.getName());
        System.out.println("Age: " + super.getAge());
        System.out.println("Gender: " + super.getGender());
        System.out.println("Employee ID: " + empId);
        System.out.println("Salary: " + salary);
        System.out.println("Specialization: " + specialization);
    }
}

/**
 * This is the main class that demonstrates the usage of the Student and Faculty classes.
 */
public class Main{
    public static void main(String[] args) {
        // Create a Student object
        Student student = new Student("Omkar", 19, "Male", 221080054, 2, "IT", 9.1);

        // Create a Faculty object
        Faculty faculty = new Faculty("Prof. Ramandip Chauhan", 50, "Male", 1001, 180000.0, "IT Support Specialist");

        // Display information about the Student and Faculty
        student.getInfo();
        System.out.println();
        faculty.getInfo();

        // Update Student data
        student.setName("Rahul");
        student.setAge(20);
        student.setSemester(4);
        student.setCPI(7.3);
        System.out.println("\nUpdated Details:");
        student.getInfo();

        // Update Faculty data
        faculty.setName("Dr. Himanshu Gupta");
        faculty.setAge(43);
        faculty.setEmpId(504);
        faculty.setSalary(90000.0);
        faculty.setSpecialization("Masters In Computer Science");
        System.out.println("\nUpdated Details:");
        faculty.getInfo();
    }
}
