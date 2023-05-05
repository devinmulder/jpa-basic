package hellojpa.section8;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain5 {

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

            Member2 refMember = em.getReference(Member2.class, member1.getId());
            System.out.println("refMember.getClass() = " + refMember.getClass());  // proxy

            em.detach(refMember);

            System.out.println("refMember.getUsername() = " + refMember.getUsername());




            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}
