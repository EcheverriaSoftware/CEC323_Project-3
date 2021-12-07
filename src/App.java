//import java.util.List;
//import java.util.Scanner;

import jakarta.persistence.*;
import model.*;
public class App {
    public static void main(String[] args) throws Exception {
        // basicDemos();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CECS 323 HW 2");
        EntityManager em = factory.createEntityManager();
        Department d = new Department("Computer Science", "CECS", 1);
        Department d2 = new Department("Mathemathics", "MATH", 2);
        em.getTransaction().begin();
        em.persist(d);
        em.persist(d2);
        em.getTransaction().commit();
        System.out.println("Go check DataGrip if you don't believe me!");
        em.close();
    }
}
