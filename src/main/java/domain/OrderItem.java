package domain;

import javax.persistence.*;

@Entity
public class OrderItem {
  @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "item_id")
  private Item item;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Orders orders;
  private int orderPrice;
  private int count;
}
