package jdbc;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


public class HibernateOperations implements Operations{

    private SessionFactory factory;
    private Session session;

    HibernateOperations(SessionFactory factory){
        this.factory = factory;
    }


    @PostConstruct
    public void init(){
        session = factory.openSession();
    }

    @PreDestroy
    public void destroy(){
        session.flush();
        session.close();
        factory.close();
    }

    @Override
    public void addStudent(String firstName, String lastName, String email) {
        try{
            session.beginTransaction();
            if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){
                System.out.println("Cannot have empty strings!");
                return;
            }
            session.save(new Student(firstName, lastName, email));
            session.getTransaction().commit();
            System.out.println("Student added!");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Integer ID, String firstName, String lastName, String email) {
        try {
            session.beginTransaction();
            session.createQuery("update Student s set firstName = :firstName , lastName" +
                    "= :lastName , email = :email WHERE s.id = :ID")
            .setParameter("firstName", firstName)
            .setParameter("lastName", lastName)
            .setParameter("email", email)
            .setParameter("ID", ID)
            .executeUpdate();
            session.getTransaction().commit();

            /*
            session.beginTransaction();
            Student student = session.get(Student.class, ID);
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            session.getTransaction().commit();

            As you can see you do not commit the object back after you took it and modified it.
            You just simply commit and it updates automatically.

             */

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readStudentByID(Integer ID) {
        try{
            session.beginTransaction();
            Student student = session.get(Student.class, ID);
            session.getTransaction().commit();
            printStudents(student);

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void readStudentByFirstName(String firstName) {
        try{
            session.beginTransaction();
            List list = session
                    .createQuery("FROM Student WHERE firstName = :firstName")
                    .setParameter("firstName", firstName)
                    .getResultList();
            session.getTransaction().commit();
            printStudents(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readStudentByLastName(String lastName) {
        try{
            session.beginTransaction();
            List list = session.
                    createQuery("FROM Student WHERE lastName = :lastName")
                    .setParameter("lastName", lastName)
                    .getResultList();
            session.getTransaction().commit();
            printStudents(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readStudentByEmail(String email) {
        try{
            session.beginTransaction();

            List list = session
                   .createQuery("FROM Student WHERE email = :email")
                   .setParameter("email", email).getResultList();
            session.getTransaction().commit();
            printStudents(list);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void readAllStudents() {
        try{
            session.beginTransaction();
            List<Student> students = session.createQuery("from Student").getResultList();
            session.getTransaction().commit();
            printStudents(students);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void printStudents(List<Student> students) {
        if(students.isEmpty()){
            System.out.println("No results");
        }
        for(Student i : students){
            System.out.println(i.toString());
        }
    }

    private void printStudents(Student student) {
        if(student == null){
            System.out.println("No results");
        }

        System.out.println(student);
    }

    public boolean checkID(int ID){
        try{
            session.beginTransaction();
            List list = session
                    .createQuery("FROM Student WHERE ID = :ID")
                    .setParameter("ID", ID)
                    .getResultList();
            session.getTransaction().commit();
            return list.isEmpty();
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void deleteStudent(Integer ID) {
        try {
            session.beginTransaction();

            session
                    .createQuery("delete from Student where id= :ID")
                    .setParameter("ID", ID)
                    .executeUpdate();
            session.getTransaction().commit();

            System.out.println("Student with the ID "+ ID + " has been deleted");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
