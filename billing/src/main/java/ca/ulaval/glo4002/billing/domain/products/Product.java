package ca.ulaval.glo4002.billing.domain.products;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "Product")
public class Product {

  @Id
  @Column(name = "index")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int index;

  @Embedded
  private ProductId id;

  @Column(name = "name")
  private String name;

  @Column(name = "unitPrice")
  private BigDecimal unitPrice;

  @Column(name = "quantity")
  private int quantity;

  public Product() {
  }

  public Product(ProductId id) {
    this.id = id;
    this.name = "";
    this.unitPrice = new BigDecimal(0);
    this.quantity = 0;
  }

  public Product(ProductId id, String name, BigDecimal unitPrice, int quantity) {
    this.id = id;
    this.name = name;
    this.unitPrice = unitPrice;
    this.quantity = quantity;
  }

  public ProductId getProductId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getUnitPrice() {
    return unitPrice;
  }

  public int getQuantity() {
    return quantity;
  }
}
