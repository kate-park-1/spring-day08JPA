package domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Delivery {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
  private Orders orders;
  @Embedded
  private Address address;

  private LocalDate deliveryDate;
  @Enumerated(EnumType.STRING)
  private DeliveryStatus DeliveryStatus;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Orders getOrders() {
    return orders;
  }

  public void setOrders(Orders orders) {
    this.orders = orders;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public LocalDate getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(LocalDate deliveryDate) {
    this.deliveryDate = deliveryDate;
  }

  public domain.DeliveryStatus getDeliveryStatus() {
    return DeliveryStatus;
  }

  public void setDeliveryStatus(domain.DeliveryStatus deliveryStatus) {
    DeliveryStatus = deliveryStatus;
  }
}
