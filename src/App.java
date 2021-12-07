//import java.util.List;
//import java.util.Scanner;

import jakarta.persistence.*;
import model.*;
public class App {
    public static void main(String[] args) throws Exception {
        // basicDemos();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CECS 323 HW 2");
        EntityManager em = factory.createEntityManager();
        Department d = new Department("Mathemathics1", "MATH1");
        //Department d2 = new Department("Mathemathics2", "MATH2");
        //Department d3 = new Department("Mathemathics2", "MATH2");
        em.getTransaction().begin();
        em.persist(d);
        //em.persist(d2);
        //em.persist(d3);
        em.getTransaction().commit();
        System.out.println("Go check DataGrip if you don't believe me!");
        em.close();
    }
}
