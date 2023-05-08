import domain.Child;
import domain.Item;
import domain.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ItemTest {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("Item 트랜잭션 시작");

    try {
      tx.begin();
      Integer price = new Integer(1000);

      Integer price2 = price;

      price = 2000;

      Item item1 = new Item();
      item1.setName("item1") ;
      item1.setPrice(price);

      Item item2 = new Item();
      item2.setName("item2");
      item2.setPrice(price2);

      em.persist(item1);
      em.persist(item2);

      System.out.println("비영속 상태 --------");

//      System.out.println("영속 상태 --------");
//      System.out.println("JPQL 실행 전 --------");
//      List<Parent> parentList = em.createQuery("select p from Parent p", Parent.class).getResultList();
//      System.out.println("p, child 생성 후 --------" + parentList.get(0).getChildList().size());
//
//      em.remove(p);
//      System.out.println("JPQL 실행 전 --------");
//      List<Child> childList = em.createQuery("select c from Child c", Child.class).getResultList();
//      System.out.println("p 삭제 후 --------" + childList.size());


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
