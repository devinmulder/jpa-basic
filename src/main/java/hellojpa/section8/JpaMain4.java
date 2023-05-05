package hellojpa.section8;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member2 member1 = new Member2();
            member1.setUsername("member1");
            em.persist(member1);
            
            em.flush();
            em.clear();

            Member2 reference = em.getReference(Member2.class, member1.getId());
            System.out.println("reference.getClass() = " + reference.getClass());

            Member2 m1 = em.find(Member2.class, member1.getId());
            System.out.println("m1.getClass() = " + m1.getClass());


            System.out.println("(m1 == reference) = " + (m1 == reference));


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
