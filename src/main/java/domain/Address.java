package domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
  private String city;
  private String street;
  private String zipcode;

  public Address() {};
  public Address(String city, String street, String zipcode) {
  }
}