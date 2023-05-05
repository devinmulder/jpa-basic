package hellojpa.section8;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member2 member = new Member2();
            member.setUsername("hello");

            em.persist(member);
            System.out.println("member.getClass() = " + member.getClass());

            em.flush();
            em.clear();

            //
            Member2 findMember = em.getReference(Member2.class, member.getId());
            System.out.println("findMember.getClass() = " + findMember.getClass());
            System.out.println("findMember.getId()  = " + findMember.getId());
            System.out.println("findMember.getUsername() = " + findMember.getUsername());



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
