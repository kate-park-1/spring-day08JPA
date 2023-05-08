import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Member_Order_Create {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPASample");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    System.out.println("멤버 - 주문 : 생성 트랜잭션 시작");

    try {
      tx.begin();
      NewMember member = new NewMember();
      member.setName("member1");
      member.setCity("Seoul");
      member.setStreet("Doksan");
      member.setZipcode("11111");

      Orders order = new Orders();
      order.setMember(member);
      order.setOrderDate(LocalDate.now());
      order.setStatus(OrderStatus.ORDER);

      member.orders.add(order);

      em.persist(member);
      em.persist(order);

      System.out.println(member.orders.size());

      System.out.println("비영속 상태 --------");
      System.out.println("영속 상태 --------");
      System.out.println("커밋 전 --------");
      tx.commit();
      System.out.println("커밋 후 --------");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();
  }
}
