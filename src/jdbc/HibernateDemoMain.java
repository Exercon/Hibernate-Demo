package jdbc;



import java.util.Scanner;

public class HibernateDemoMain {

    public static void mainMenu(HibernateOperations hibernateOperations){
            while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please select [1-5]");
            System.out.println("1- Read Students \n" +
                    "2- Add Student \n" +
                    "3- Update Student \n" +
                    "4- Delete Student \n" +
                    "5- Quit");
            try{

            int choice = scanner.nextInt();


            switch (choice) {
                case 1:
                    case1(hibernateOperations);
                    break;
                case 2:
                    case2(hibernateOperations);
                    break;
                case 3:
                    case3(hibernateOperations);
                    break;
                case 4:
                    case4(hibernateOperations);
                    break;
                case 5:
                    System.exit(1);
                default:
                    System.out.println("Invalid choice. Please try again...");
            }
            }catch(Exception e){
                System.out.println("An exception occurred. Please try again...");
            }
        }

    }

    public static void main(String... args) {
       mainMenu(Config.context.getBean("hibernateOperations", HibernateOperations.class));
    }

    public static void case1(HibernateOperations hibernateOperations){
        System.out.println("1- All Students \n" +
                "2- Student By First Name \n" +
                "3- Student By Last Name \n" +
                "4- Student By Email \n" +
                "5- Student By ID \n" +
                "6- Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                hibernateOperations.readAllStudents();
                break;
            case 2:


                System.out.println("Student first name");
                String tempFirstName = scanner.next();

                hibernateOperations.readStudentByFirstName(tempFirstName);

                break;
            case 3:
                System.out.println("Student last name");
                String tempLastName = scanner.next();

                hibernateOperations.readStudentByLastName(tempLastName);
                break;
            case 4:
                System.out.println("Student's email");
                String tempEmail = scanner.next();

                hibernateOperations.readStudentByEmail(tempEmail);
                break;

            case 5:
                System.out.println("Student's ID");
                int tempID = scanner.nextInt();

                hibernateOperations.readStudentByID(tempID);
                break;

            case 6:
                break;
            default:
                System.out.println("Invalid choice!");
        }


    }

    public static void case2(HibernateOperations hibernateOperations){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Student first name");
        String tempFirstName = scanner.next();
        System.out.println("Student last name");
        String tempLastName = scanner.next();
        System.out.println("Student Email");
        String tempEmail = scanner.next();

        hibernateOperations.addStudent(tempFirstName, tempLastName, tempEmail);

    }

    public static void case3(HibernateOperations hibernateOperations){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student's ID");
        int tempID = scanner.nextInt();
        if(hibernateOperations.checkID(tempID)){
            System.out.println("No student found with ID: "+ tempID);
            return;
        }
        System.out.println("Student is ");
        hibernateOperations.readStudentByID(tempID);

        System.out.println("Student's first name");
        String tempFirstName = scanner.next();
        System.out.println("Student's last name");
        String tempLastName = scanner.next();
        System.out.println("Student's email");
        String tempEmail = scanner.next();

        hibernateOperations.updateStudent(tempID, tempFirstName, tempLastName, tempEmail);


    }

    public static void case4(HibernateOperations hibernateOperations){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Student's ID");
        int tempID = scanner.nextInt();
        if(hibernateOperations.checkID(tempID)){
            System.out.println("No student found with ID: "+ tempID);
            return;
        }
        System.out.println("Student is ");
        hibernateOperations.readStudentByID(tempID);

        System.out.println("Do you want to delete [ Y / N ]");
        String choice = scanner.next().substring(0,1);

        if(choice.equalsIgnoreCase("Y")){
            hibernateOperations.deleteStudent(tempID);
        }

    }

}
