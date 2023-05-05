package hellojpa.section8;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member2 member1 = new Member2();
            member1.setUsername("member1");
            em.persist(member1);

            Member2 member2 = new Member2();
            member2.setUsername("member1");
            em.persist(member2);

            em.flush();
            em.clear();

            Member2 m1 = em.find(Member2.class, member1.getId());
            Member2 m2 = em.getReference(Member2.class, member2.getId());

            logic(m1, m2);


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member2 m1, Member2 m2) {
        System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));

        System.out.println("m1 == m2: " + (m1 instanceof Member2));
        System.out.println("m1 == m2: " + (m2 instanceof Member2));


    }
}
