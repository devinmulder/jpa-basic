package hellojpa.section5.lec01;

import hellojpa.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team1 team = new Team1();
            team.setName("TeamA");
            em.persist(team);

            Member1 member = new Member1();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);

            Member1 findMember = em.find(Member1.class, member.getId());

            Team1 findTeam = findMember.getTeam();

            System.out.println("findTeam = " + findTeam);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
