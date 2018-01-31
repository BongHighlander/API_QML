package ca.ulaval.glo4002.billing.domain.products;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductId implements Serializable {

  private static final long serialVersionUID = 1L;

  private int productId;

  public ProductId() {
    // Hibernate default constructor. Do not call.
  }

  public ProductId(int id) {
    this.productId = id;
  }

  @Override
  public String toString() {
    return Integer.toString(productId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductId productId = (ProductId) o;

    return this.productId == productId.productId;
  }

  @Override
  public int hashCode() {
    return productId;
  }
}
