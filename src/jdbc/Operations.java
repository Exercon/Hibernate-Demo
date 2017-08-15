package jdbc;


public interface Operations {
    public void addStudent(String firstName, String lastName, String email);
    public void updateStudent(Integer ID, String firstName, String lastName, String email);
    public void readStudentByID(Integer ID);
    public void readStudentByFirstName(String firstName);
    public void readStudentByLastName(String lastName);
    public void readStudentByEmail(String email);
    public void readAllStudents();
    public void deleteStudent(Integer ID);
}
