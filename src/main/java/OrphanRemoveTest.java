import domain.Child;
import domain.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OrphanRemoveTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("orphan removel 트랜잭션 시작");

    try {
      tx.begin();
      Child c1 = new Child();
      c1.setName("c1");
      Child c2 = new Child();
      c2.setName("c2");

      Parent p = new Parent();
      p.setName("p");
      p.addChild(c1);
      p.addChild(c2);

      em.persist(p);
      em.persist(c1);
      em.persist(c2);

      em.flush();
      em.clear();

      //Parent parent1 = em.find(Parent.class, p.getId());
      System.out.println(p.getChildList().size());
      p.getChildList().remove(0); //자식 엔티티를 컬렉션에서 제거

      em.remove(p);

      System.out.println("비영속 상태 --------");

      System.out.println("커밋 전 --------");
      tx.commit();
      System.out.println("커밋 후 --------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
      emf.close();
    }
  }
}
