package hellojpa.section8;

import org.hibernate.Hibernate;

import javax.persistence.*;

public class JpaMain6 {

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

            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            //refMember.getUsername();
            Hibernate.initialize(refMember);  // 강제 초기화
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));



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
