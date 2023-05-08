package domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Orders {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private NewMember member;
  @Column(name = "order_date")
  private LocalDate orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public NewMember getMember() {
    return member;
  }

  public void setMember(NewMember member) {
    this.member = member;
  }

  public LocalDate getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(LocalDate orderDate) {
    this.orderDate = orderDate;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
