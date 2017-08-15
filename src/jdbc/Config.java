package jdbc;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

@Controller
public class Config {

    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

/*@Bean To use properties file in a spring version 4.2 or lower you have +to use this code block.
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }*/

    @Bean
    public HibernateOperations hibernateOperations(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        return new HibernateOperations(factory);}

}
